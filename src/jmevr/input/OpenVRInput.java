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
import jmevr.util.VRUtil;
import jmevr.util.VRViewManager;
import jopenvr.JOpenVRLibrary;
import jopenvr.VRControllerState_t;
import jopenvr.VR_IVRSystem_FnTable;

/*
make helper functions to pull the following easily from raw data (DONE)

trigger:
Controller#1, Axis#0 X: 0.0, Y: 0.0
Controller#1, Axis#1 X: 1.0, Y: 0.0
Controller#1, Axis#2 X: 0.0, Y: 0.0
Controller#1, Axis#3 X: 0.0, Y: 0.0
Controller#1, Axis#4 X: 0.0, Y: 0.0
Button press: 8589934592 (when full), touch: 8589934592

touchpad (upper left):
Controller#1, Axis#0 X: -0.6059755, Y: 0.2301706
Controller#1, Axis#1 X: 0.0, Y: 0.0
Controller#1, Axis#2 X: 0.0, Y: 0.0
Controller#1, Axis#3 X: 0.0, Y: 0.0
Controller#1, Axis#4 X: 0.0, Y: 0.0
Button press: 4294967296 (when pressed in), touch: 4294967296

grip:
Controller#1, Axis#0 X: 0.0, Y: 0.0
Controller#1, Axis#1 X: 0.0, Y: 0.0
Controller#1, Axis#2 X: 0.0, Y: 0.0
Controller#1, Axis#3 X: 0.0, Y: 0.0
Controller#1, Axis#4 X: 0.0, Y: 0.0
Button press: 4, touch: 4

thumb:
Controller#1, Axis#0 X: 0.0, Y: 0.0
Controller#1, Axis#1 X: 0.0, Y: 0.0
Controller#1, Axis#2 X: 0.0, Y: 0.0
Controller#1, Axis#3 X: 0.0, Y: 0.0
Controller#1, Axis#4 X: 0.0, Y: 0.0
Button press: 2, touch: 2

*/

/**
 *
 * @author phr00t
 * 
 * null values will be returned if no valid pose exists, or that input device isn't available
 * your code should check for null values
 */
public class OpenVRInput implements VRInputAPI {
        
    private static final VRControllerState_t[] cStates = new VRControllerState_t[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final Quaternion[] rotStore = new Quaternion[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final Vector3f[] posStore = new Vector3f[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final int[] controllerIndex = new int[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static int controllerCount = 0;
    private static final Vector2f tempAxis = new Vector2f(), temp2Axis = new Vector2f();
    private static final Vector2f lastCallAxis[] = new Vector2f[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final boolean needsNewVelocity[] = new boolean[JOpenVRLibrary.k_unMaxTrackedDeviceCount],
                                 needsNewAngVelocity[] = new boolean[JOpenVRLibrary.k_unMaxTrackedDeviceCount],
                                 buttonDown[][] = new boolean[JOpenVRLibrary.k_unMaxTrackedDeviceCount][16];
    private static float axisMultiplier = 1f;
    private static final Vector3f tempVel = new Vector3f();
    private static final Quaternion tempq = new Quaternion();

    @Override
    public void setAxisMultiplier(float set) {
        axisMultiplier = set;
    }
    
    public enum VRINPUT_TYPE {
        ViveTriggerAxis(0), ViveTouchpadAxis(1), ViveGripButton(2), ViveMenuButton(3);
        
        private final int value;
        private VRINPUT_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }        
    }
    
    public void swapHands() {
        if( controllerCount != 2 ) return; 
        int temp = controllerIndex[0];
        controllerIndex[0] = controllerIndex[1];
        controllerIndex[1] = temp;
    }
    
    public boolean isButtonDown(int controllerIndex, VRINPUT_TYPE checkButton) {
        VRControllerState_t cs = cStates[OpenVRInput.controllerIndex[controllerIndex]];
        switch( checkButton ) {
            default:
                return false;
            case ViveGripButton:
                return (cs.ulButtonPressed & 4) != 0;
            case ViveMenuButton:
                return (cs.ulButtonPressed & 2) != 0;                
            case ViveTouchpadAxis:
                return (cs.ulButtonPressed & 4294967296l) != 0;
            case ViveTriggerAxis:
                return (cs.ulButtonPressed & 8589934592l) != 0;                
        }
    }
    
    public boolean wasButtonPressedSinceLastCall(int controllerIndex, VRINPUT_TYPE checkButton) {
        boolean buttonDownNow = isButtonDown(controllerIndex, checkButton);
        int checkButtonValue = checkButton.getValue();
        int cIndex = OpenVRInput.controllerIndex[controllerIndex];
        boolean retval = buttonDownNow == true && buttonDown[cIndex][checkButtonValue] == false;
        buttonDown[cIndex][checkButtonValue] = buttonDownNow;
        return retval;
    }
    
    public void resetInputSinceLastCall() {
        for(int i=0;i<lastCallAxis.length;i++) {
            lastCallAxis[i].x = 0f;
            lastCallAxis[i].y = 0f;
        }
        for(int i=0;i<JOpenVRLibrary.k_unMaxTrackedDeviceCount;i++) {
            for(int j=0;j<16;j++) {
                buttonDown[i][j] = false;
            }
        }
    }
    
    public Vector2f getAxisDeltaSinceLastCall(int controllerIndex, VRINPUT_TYPE forAxis) {                
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
    
    public Vector3f getVelocity(int controllerIndex) {
        int index = OpenVRInput.controllerIndex[controllerIndex];
        if( needsNewVelocity[index] ) {
            OpenVR.hmdTrackedDevicePoses[index].readField("vVelocity");
            needsNewVelocity[index] = false;
        }
        tempVel.x = OpenVR.hmdTrackedDevicePoses[index].vVelocity.v[0];
        tempVel.y = OpenVR.hmdTrackedDevicePoses[index].vVelocity.v[1];
        tempVel.z = OpenVR.hmdTrackedDevicePoses[index].vVelocity.v[2];
        return tempVel;
    }
    
    public Vector3f getAngularVelocity(int controllerIndex) {
        int index = OpenVRInput.controllerIndex[controllerIndex];
        if( needsNewAngVelocity[index] ) {
            OpenVR.hmdTrackedDevicePoses[index].readField("vAngularVelocity");
            needsNewAngVelocity[index] = false;
        }
        tempVel.x = OpenVR.hmdTrackedDevicePoses[index].vAngularVelocity.v[0];
        tempVel.y = OpenVR.hmdTrackedDevicePoses[index].vAngularVelocity.v[1];
        tempVel.z = OpenVR.hmdTrackedDevicePoses[index].vAngularVelocity.v[2];
        return tempVel;
    }
    
    public Vector2f getAxisRaw(int controllerIndex, VRINPUT_TYPE forAxis) {
        VRControllerState_t cs = cStates[OpenVRInput.controllerIndex[controllerIndex]];
        switch( forAxis ) {
            default:
                return null;
            case ViveTriggerAxis:
                tempAxis.x = cs.rAxis[1].x;
                tempAxis.y = tempAxis.x;
                break;
            case ViveTouchpadAxis:
                tempAxis.x = cs.rAxis[0].x;
                tempAxis.y = cs.rAxis[0].y;
                break;
        }       
        return tempAxis;
    }

    public Vector2f getAxis(int controllerIndex, VRINPUT_TYPE forAxis) {
        VRControllerState_t cs = cStates[OpenVRInput.controllerIndex[controllerIndex]];
        switch( forAxis ) {
            default:
                return null;
            case ViveTriggerAxis:
                tempAxis.x = cs.rAxis[1].x;
                tempAxis.y = tempAxis.x;
                break;
            case ViveTouchpadAxis:
                tempAxis.x = cs.rAxis[0].x;
                tempAxis.y = cs.rAxis[0].y;
                break;
        }       
        tempAxis.x *= axisMultiplier;
        tempAxis.y *= axisMultiplier;
        return tempAxis;
    }
    
    public boolean init() {
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
        return true;
    }
    
    public int getTrackedControllerCount() {
        return controllerCount;
    }
    
    /*
        do not call, called internally
    */
    public void _updateConnectedControllers() {
        controllerCount = 0;
        for(int i=0;i<JOpenVRLibrary.k_unMaxTrackedDeviceCount;i++) {
            if( ((OpenVR)VRApplication.getVRHardware()).getVRSystem().GetTrackedDeviceClass.apply(i) == JOpenVRLibrary.ETrackedDeviceClass.ETrackedDeviceClass_TrackedDeviceClass_Controller ) {
                controllerIndex[controllerCount] = i;
                controllerCount++;
            }
        }
    }
    
    /*
        do not call, called internally
    */
    public void _updateControllerStates() {
        for(int i=0;i<controllerCount;i++) {
            int index = controllerIndex[i];
            ((OpenVR)VRApplication.getVRHardware()).getVRSystem().GetControllerState.apply(index, cStates[index]);
            cStates[index].readField("ulButtonPressed");
            cStates[index].readField("rAxis");
            needsNewVelocity[index] = true;
            needsNewAngVelocity[index] = true;
        }
    }
    
    public VRControllerState_t getRawControllerState(int index) {
        if( isInputDeviceTracking(index) == false ) return null;
        return cStates[controllerIndex[index]];
    }
    
    //public Matrix4f getPoseForInputDevice(int index) {
    //    if( isInputDeviceTracking(index) == false ) return null;
    //    return OpenVR.poseMatrices[controllerIndex[index]];
    //}
    
    public boolean doWeHaveInputFocus() {
        return ((VR_IVRSystem_FnTable)VRApplication.getVRHardware().getVRSystem()).IsInputFocusCapturedByAnotherProcess.apply() == 0;       
    }
    
    public boolean isInputDeviceTracking(int index) {
        if( index < 0 || index >= controllerCount ) return false;
        return OpenVR.hmdTrackedDevicePoses[controllerIndex[index]].bPoseIsValid != 0;
    }
    
    public Quaternion getOrientation(int index) {
        if( isInputDeviceTracking(index) == false ) return null;
        index = controllerIndex[index];
        VRUtil.convertMatrix4toQuat(OpenVR.poseMatrices[index], rotStore[index]);
        return rotStore[index];
    }

    public Vector3f getPosition(int index) {
        if( isInputDeviceTracking(index) == false ) return null;
        // the hmdPose comes in rotated funny, fix that here
        index = controllerIndex[index];
        OpenVR.poseMatrices[index].toTranslationVector(posStore[index]);
        posStore[index].x = -posStore[index].x;
        posStore[index].z = -posStore[index].z;
        return posStore[index];
    }
    
    /*
        where is the controller pointing, after all rotations are combined?
        depends on observer rotation, if any
    */
    public Quaternion getFinalObserverRotation(int index) {
        VRViewManager vrvm = VRApplication.getVRViewManager();
        if( vrvm == null || isInputDeviceTracking(index) == false ) return null;
        Object obs = VRApplication.getObserver();
        if( obs instanceof Camera ) {
            tempq.set(((Camera)obs).getRotation());
        } else {
            tempq.set(((Spatial)obs).getWorldRotation());
        }
        return tempq.multLocal(getOrientation(index));
    }
    
    /*
        where is the controller, after all positional tracking is complete?
        includes observer position, if any
    */    
    public Vector3f getFinalObserverPosition(int index) {
        VRViewManager vrvm = VRApplication.getVRViewManager();
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
    
    public void triggerHapticPulse(int controllerIndex, float seconds) {
        if( VRApplication.isInVR() == false || isInputDeviceTracking(controllerIndex) == false ) return;
        // apparently only axis ID of 0 works
        ((VR_IVRSystem_FnTable)VRApplication.getVRHardware().getVRSystem()).TriggerHapticPulse.apply(OpenVRInput.controllerIndex[controllerIndex],
                                                                                                     0, (short)Math.round(3f * seconds / 1e-3f));
    }
}
