/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.input;

import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import jmevr.app.VRApplication;
import jmevr.util.OpenVRUtil;
import jmevr.util.OpenVRViewManager;
import jopenvr.JOpenVRLibrary;
import jopenvr.VRControllerState_t;

/*
make helper functions to pull the following easily from raw data (DONE)

Controller#1, Axis#0 X: 2.8E-45, Y: 0.0 <--- axis 0, x seems to have bit flags for touchpad press & full trigger press
Controller#1, Axis#1 X: 4.2E-45, Y: 0.39417708 <--- touchpad on Y
Controller#1, Axis#2 X: -0.7597278, Y: 1.0 <--- touchpad on X, trigger on Y
Controller#1, Axis#3 X: 0.0, Y: 0.0
Controller#1, Axis#4 X: 0.0, Y: 0.0
Button press: 0, touch: 0 <--- touch 4 for grip button, 2 for little black button

*/

/**
 *
 * @author phr00t
 * 
 * null values will be returned if no valid pose exists, or that input device isn't available
 * your code should check for null values
 */
public class VRInput {
        
    private static final VRControllerState_t[] cStates = new VRControllerState_t[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final Quaternion[] rotStore = new Quaternion[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final Vector3f[] posStore = new Vector3f[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final int[] controllerIndex = new int[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static int controllerCount = 0;
    private static final Vector2f tempAxis = new Vector2f();
    private static final Quaternion quatFlip = (new Quaternion()).fromAngles(0f, (float)Math.PI, 0f);
    
    public enum VRINPUT_TYPE {
        ViveTriggerAxis, ViveTouchpadAxis, ViveGripButton, ViveThumbButton
    }
    
    public static boolean buttonDown(int controllerIndex, VRINPUT_TYPE checkButton) {
        VRControllerState_t cs = cStates[VRInput.controllerIndex[controllerIndex]];
        switch( checkButton ) {
            default:
                return false;
            case ViveGripButton:
                return (cs.ulButtonTouched.longValue() & 4) != 0;
            case ViveThumbButton:
                return (cs.ulButtonTouched.longValue() & 2) != 0;                
        }
    }
    
    public static Vector2f getAxis(int controllerIndex, VRINPUT_TYPE forAxis) {
        VRControllerState_t cs = cStates[VRInput.controllerIndex[controllerIndex]];
        switch( forAxis ) {
            default:
                return null;
            case ViveTriggerAxis:
                tempAxis.x = cs.rAxis[2].y;
                tempAxis.y = tempAxis.x;
                break;
            case ViveTouchpadAxis:
                tempAxis.x = cs.rAxis[1].y;
                tempAxis.y = cs.rAxis[2].x;
                break;
        }        
        return tempAxis;
    }
    
    static {
        for(int i=0;i<JOpenVRLibrary.k_unMaxTrackedDeviceCount;i++) {
            rotStore[i] = new Quaternion();
            posStore[i] = new Vector3f();
            cStates[i] = new VRControllerState_t();
            cStates[i].setAutoSynch(false);
            cStates[i].setAutoRead(false);
            cStates[i].setAutoWrite(false);
        }
    }
    
    public static int getTrackedControllerCount() {
        return controllerCount;
    }
    
    /*
        do not call, called internally
    */
    public static void _updateConnectedControllers() {
        controllerCount = 0;
        for(int i=0;i<JOpenVRLibrary.k_unMaxTrackedDeviceCount;i++) {
            if( JOpenVRLibrary.VR_IVRSystem_GetTrackedDeviceClass(OpenVR.getVRSystemInstance(), i) == JOpenVRLibrary.TrackedDeviceClass.TrackedDeviceClass_Controller ) {
                controllerIndex[controllerCount] = i;
                controllerCount++;
            }
        }
    }
    
    /*
        do not call, called internally
    */
    public static void _updateControllerStates() {
        for(int i=0;i<controllerCount;i++) {
            int index = controllerIndex[i];
            JOpenVRLibrary.VR_IVRSystem_GetControllerState(OpenVR.getVRSystemInstance(), index, cStates[index]);
            cStates[index].readField("ulButtonTouched");
            cStates[index].readField("rAxis");
        }
    }
    
    public static VRControllerState_t getRawControllerState(int index) {
        if( isInputDeviceTracking(index) == false ) return null;
        return cStates[controllerIndex[index]];
    }
    
    public static Matrix4f getPoseForInputDevice(int index) {
        if( isInputDeviceTracking(index) == false ) return null;
        return OpenVR.poseMatrices[controllerIndex[index]];
    }
    
    public static boolean doWeHaveInputFocus() {
        return JOpenVRLibrary.VR_IVRSystem_IsInputFocusCapturedByAnotherProcess(OpenVR.getVRSystemInstance()) == 0;       
    }
    
    public static boolean isInputDeviceTracking(int index) {
        if( index < 0 || index >= controllerCount ) return false;
        return OpenVR.hmdTrackedDevicePoses[controllerIndex[index]].bPoseIsValid != 0;
    }
    
    public static Quaternion getOrientation(int index) {
        if( isInputDeviceTracking(index) == false ) return null;
        index = controllerIndex[index];
        OpenVRUtil.convertMatrix4toQuat(OpenVR.poseMatrices[index], rotStore[index]);
        return rotStore[index].multLocal(quatFlip);
    }

    public static Vector3f getPosition(int index) {
        if( isInputDeviceTracking(index) == false ) return null;
        // the hmdPose comes in rotated funny, fix that here
        index = controllerIndex[index];
        OpenVR.poseMatrices[index].toTranslationVector(posStore[index]);
        posStore[index].x = -posStore[index].x;
        posStore[index].z = -posStore[index].z;
        return posStore[index];
    }
    
    public static void getPositionAndOrientation(int index, Vector3f storePos, Quaternion storeRot) {
        if( isInputDeviceTracking(index) == false ) {
            storePos.set(Vector3f.ZERO);
            storeRot.set(Quaternion.DIRECTION_Z);
        } else {
            index = controllerIndex[index];
            OpenVR.poseMatrices[index].toTranslationVector(storePos);
            storePos.x = -storePos.x;
            storePos.z = -storePos.z;
            OpenVRUtil.convertMatrix4toQuat(OpenVR.poseMatrices[index], storeRot);
            storeRot.multLocal(quatFlip);
        }
    }    
    
    /*
        where is the controller pointing, after all rotations are combined?
        depends on observer rotation, if any
    */
    public static Quaternion getFinalObserverRotation(int index) {
        OpenVRViewManager vrvm = VRApplication.getVRViewManager();
        if( vrvm == null || isInputDeviceTracking(index) == false ) return null;
        Object obs = VRApplication.getObserver();
        if( obs instanceof Camera ) {
            return getOrientation(index).multLocal(((Camera)obs).getRotation());
        } else {
            return getOrientation(index).multLocal(((Spatial)obs).getWorldRotation());
        }
    }
    
    /*
        where is the controller, after all positional tracking is complete?
        includes observer position, if any
    */    
    public static Vector3f getFinalObserverPosition(int index) {
        OpenVRViewManager vrvm = VRApplication.getVRViewManager();
        if( vrvm == null || isInputDeviceTracking(index) == false ) return null;
        Object obs = VRApplication.getObserver();
        Vector3f pos = getPosition(index);
        if( obs instanceof Camera ) {
            ((Camera)obs).getRotation().mult(pos, pos);
            return pos.addLocal(((Camera)obs).getLocation());
        } else {
            ((Spatial)obs).getWorldRotation().mult(pos, pos);
            return pos.addLocal(((Spatial)obs).getWorldTranslation());
        }
    }    
}
