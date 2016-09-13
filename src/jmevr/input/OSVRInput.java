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
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import jmevr.app.VRApplication;
import osvrclientkit.OsvrClientKitLibrary;
import osvrclientkit.OsvrClientKitLibrary.OSVR_ClientInterface;

/**
 *
 * @author Phr00t
 */
public class OSVRInput implements VRInputAPI {

    OSVR_ClientInterface leftButton, rightButton;
    
    Callback buttonHandler;
    
    @Override
    public boolean isButtonDown(int controllerIndex, OpenVRInput.VRINPUT_TYPE checkButton) {
        return false;
    }

    @Override
    public boolean wasButtonPressedSinceLastCall(int controllerIndex, OpenVRInput.VRINPUT_TYPE checkButton) {
        return false;
    }

    @Override
    public void resetInputSinceLastCall() {
        
    }

    @Override
    public Vector2f getAxisDeltaSinceLastCall(int controllerIndex, OpenVRInput.VRINPUT_TYPE forAxis) {
        return Vector2f.ZERO;
    }

    @Override
    public Vector3f getVelocity(int controllerIndex) {
        return Vector3f.ZERO;
    }

    @Override
    public Vector3f getAngularVelocity(int controllerIndex) {
        return Vector3f.ZERO;
    }

    @Override
    public Vector2f getAxis(int controllerIndex, OpenVRInput.VRINPUT_TYPE forAxis) {
        return Vector2f.ZERO;
    }

    @Override
    public boolean init() {
        /*OsvrClientKitLibrary.OSVR_ClientContext context = (OsvrClientKitLibrary.OSVR_ClientContext)VRApplication.getVRHardware().getVRSystem();
        PointerByReference pbr = new PointerByReference();
        byte result = OsvrClientKitLibrary.osvrClientGetInterface(context, OSVR.defaultNullString, pbr);
        if( result == 0 ) {
            leftButton = new OSVR_ClientInterface(pbr.getValue());
            buttonHandler = new Callback() {
                public void invoke(Pointer userdata, Pointer timeval, Pointer report) {
                    System.out.println("Callback called!");
                }                
            };
            //OsvrClientKitLibrary.osvrRegisterButtonCallback(leftButton, buttonHandler, Pointer.NULL);
            //rightButton1.registerCallback(&myButtonCallback, &quit);        
            return true;
        }*/
        return true;
    }

    @Override
    public int getTrackedControllerCount() {
        return 0;
    }

    @Override
    public void _updateConnectedControllers() {
        
    }

    @Override
    public void _updateControllerStates() {
        
    }

    @Override
    public Object getRawControllerState(int index) {
        return null;
    }

    @Override
    public Matrix4f getPoseForInputDevice(int index) {
        return Matrix4f.IDENTITY;
    }

    @Override
    public boolean doWeHaveInputFocus() {
        return true;
    }

    @Override
    public boolean isInputDeviceTracking(int index) {
        return false;
    }

    @Override
    public Quaternion getOrientation(int index) {
        return Quaternion.DIRECTION_Z;
    }

    @Override
    public Vector3f getPosition(int index) {
        return Vector3f.ZERO;
    }

    @Override
    public Quaternion getFinalObserverRotation(int index) {
        return Quaternion.DIRECTION_Z;
    }

    @Override
    public Vector3f getFinalObserverPosition(int index) {
        return Vector3f.ZERO;
    }

    @Override
    public void triggerHapticPulse(int controllerIndex, float seconds) {
        
    }
    
}
