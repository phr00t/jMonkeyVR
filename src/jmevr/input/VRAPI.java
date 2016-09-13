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
import jmevr.input.OpenVR.HMD_TYPE;

/**
 *
 * @author Phr00t
 */
public interface VRAPI {
    
    public boolean initialize();
    
    public boolean initVRCompositor(boolean allowed);
    
    public Object getVRSystem();
    
    public Object getCompositor();
    
    public String getName();
    
    public VRInputAPI getVRinput();
    
    public void _setFlipEyes(boolean set);
    
    public void printLatencyInfoToConsole(boolean set);

    public int getDisplayFrequency();
    
    public void destroy();

    public boolean isInitialized();

    public void reset();

    public void getRenderSize(Vector2f store);
    
    public float getFOV(int dir);

    public float getInterpupillaryDistance();
    
    public Quaternion getOrientation();

    public Vector3f getPosition();
    
    public void getPositionAndOrientation(Vector3f storePos, Quaternion storeRot);
    
    public void updatePose();

    public Matrix4f getHMDMatrixProjectionLeftEye(Camera cam);
        
    public Matrix4f getHMDMatrixProjectionRightEye(Camera cam);
    
    public Vector3f getHMDVectorPoseLeftEye();
    
    public Vector3f getHMDVectorPoseRightEye();
    
    public Vector3f getSeatedToAbsolutePosition();
    
    public Matrix4f getHMDMatrixPoseLeftEye();
    
    public HMD_TYPE getType();
    
    public Matrix4f getHMDMatrixPoseRightEye();
    
}
