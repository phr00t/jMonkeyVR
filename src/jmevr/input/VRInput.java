/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.input;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author phr00t
 */
public interface VRInput {
    
    // identification for input
    public String getName();
    
    // init & de-init
    public boolean initialize();    
    public boolean destroy();    
    public boolean isInitialized();
    public void reset();
    
    // orientation/pos info
    public Quaternion getOrientation();    
    public Vector3f getPosition();    
    public Vector3f getAngularAcceleration();
    public Vector3f getPositionalAcceleration();    
    public Vector3f getAccelerometer();
    
    // controller input options
    public Vector3f getAnalogButton(int index, Vector3f store);
    public boolean getDigitalButton(int index);
    public int getDigitalButtonCount();
    public int getAnalogButtonCount();
}
