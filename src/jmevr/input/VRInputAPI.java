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
import jmevr.input.OpenVRInput.VRINPUT_TYPE;

/**
 *
 * @author Phr00t
 */
public interface VRInputAPI {

    public boolean isButtonDown(int controllerIndex, VRINPUT_TYPE checkButton);
    
    public boolean wasButtonPressedSinceLastCall(int controllerIndex, VRINPUT_TYPE checkButton);
    
    public void resetInputSinceLastCall();
    
    public Vector2f getAxisDeltaSinceLastCall(int controllerIndex, VRINPUT_TYPE forAxis);
    
    public Vector3f getVelocity(int controllerIndex);
    
    public Vector3f getAngularVelocity(int controllerIndex);
    
    public Vector2f getAxis(int controllerIndex, VRINPUT_TYPE forAxis);
    
    public Vector2f getAxisRaw(int controllerIndex, VRINPUT_TYPE forAxis);

    public boolean init();
    
    public int getTrackedControllerCount();
    
    public void _updateConnectedControllers();
    
    public void _updateControllerStates();
    
    public Object getRawControllerState(int index);
    
    public void swapHands();
    
    public void setAxisMultiplier(float set);
    
    //public Matrix4f getPoseForInputDevice(int index);
    
    public boolean doWeHaveInputFocus();
    
    public boolean isInputDeviceTracking(int index);
    
    public Quaternion getOrientation(int index);

    public Vector3f getPosition(int index);
    
    public Quaternion getFinalObserverRotation(int index);
    
    public Vector3f getFinalObserverPosition(int index);
    
    public void triggerHapticPulse(int controllerIndex, float seconds);
}
