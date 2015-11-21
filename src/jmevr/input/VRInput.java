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
    private static final Vector2f tempAxis = new Vector2f(), temp2Axis = new Vector2f();
    private static final Vector2f lastCallAxis[] = new Vector2f[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final boolean needsNewVelocity[] = new boolean[JOpenVRLibrary.k_unMaxTrackedDeviceCount],
                                 needsNewAngVelocity[] = new boolean[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final Vector3f tempVel = new Vector3f();
    
    public enum VRINPUT_TYPE {
        ViveTriggerAxis(0), ViveTouchpadAxis(1), ViveGripButton(2), ViveThumbButton(3);
        
        private final int value;
        private VRINPUT_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }        
    }
    
    public static boolean isButtonDown(int controllerIndex, VRINPUT_TYPE checkButton) {
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
    
    public static void resetAxisDeltas() {
        for(int i=0;i<lastCallAxis.length;i++) {
            lastCallAxis[i].x = 0f;
            lastCallAxis[i].y = 0f;
        }
    }
    
    public static Vector2f getAxisDeltaSinceLastCall(int controllerIndex, VRINPUT_TYPE forAxis) {                
        int axisIndex = forAxis.getValue();
        temp2Axis.set(lastCallAxis[axisIndex]);
        lastCallAxis[axisIndex].set(getAxis(controllerIndex, forAxis));
        if( (temp2Axis.x != 0f || temp2Axis.y != 0f) && (lastCallAxis[axisIndex].x != 0f || lastCallAxis[axisIndex].y != 0f) ) {
            temp2Axis.subtractLocal(lastCallAxis[axisIndex]);        
        } else {
            // move made from rest, don't count as a delta move
            temp2Axis.x = 0f;
            temp2Axis.y = 0f;
        }
        return temp2Axis;
    }
    
    public static Vector3f getVelocity(int controllerIndex) {
        int index = VRInput.controllerIndex[controllerIndex];
        if( needsNewVelocity[index] ) {
            OpenVR.hmdTrackedDevicePoses[index].readField("vVelocity");
            needsNewVelocity[index] = false;
        }
        tempVel.x = OpenVR.hmdTrackedDevicePoses[index].vVelocity.v[0];
        tempVel.y = OpenVR.hmdTrackedDevicePoses[index].vVelocity.v[1];
        tempVel.z = OpenVR.hmdTrackedDevicePoses[index].vVelocity.v[2];
        return tempVel;
    }
    
    public static Vector3f getAngularVelocity(int controllerIndex) {
        int index = VRInput.controllerIndex[controllerIndex];
        if( needsNewAngVelocity[index] ) {
            OpenVR.hmdTrackedDevicePoses[index].readField("vAngularVelocity");
            needsNewAngVelocity[index] = false;
        }
        tempVel.x = OpenVR.hmdTrackedDevicePoses[index].vAngularVelocity.v[0];
        tempVel.y = OpenVR.hmdTrackedDevicePoses[index].vAngularVelocity.v[1];
        tempVel.z = OpenVR.hmdTrackedDevicePoses[index].vAngularVelocity.v[2];
        return tempVel;
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
            lastCallAxis[i] = new Vector2f();
            needsNewVelocity[i] = true;
            needsNewAngVelocity[i] = true;
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
            needsNewVelocity[index] = true;
            needsNewAngVelocity[index] = true;
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
        return rotStore[index];
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
    
    public static void triggerHapticPulse(int controllerIndex, float seconds) {
        if( VRApplication.isInVR() == false || VRInput.isInputDeviceTracking(controllerIndex) == false ) return;
        // apparently only axis ID of 0 works
        JOpenVRLibrary.VR_IVRSystem_TriggerHapticPulse(OpenVR.getVRSystemInstance(),
                                                       VRInput.controllerIndex[controllerIndex],
                                                       0, (short)Math.round(3f * seconds / 1e-3f));
    }
}
