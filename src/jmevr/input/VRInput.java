/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.input;

import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import jmevr.app.VRApplication;
import jmevr.util.OpenVRUtil;
import jmevr.util.OpenVRViewManager;
import jopenvr.JOpenVRLibrary;

/**
 *
 * @author phr00t
 * 
 * index 0 will be the first input device, (internal +1 to device index is made to skip the headset)
 * 
 * null values will be returned if no valid pose exists, or that input device isn't available
 * your code should check for null values
 */
public class VRInput {
        
    private static final Quaternion[] rotStore = new Quaternion[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    private static final Vector3f[] posStore = new Vector3f[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
    
    static {
        for(int i=0;i<JOpenVRLibrary.k_unMaxTrackedDeviceCount;i++) {
            rotStore[i] = new Quaternion();
            posStore[i] = new Vector3f();
        }
    }
    
    public static Matrix4f getPoseForInputDevice(int index) {
        if( isInputDeviceTracked(index) == false ) return null;
        return OpenVR.poseMatrices[index+1];
    }
    
    public static boolean isInputDeviceTracked(int index) {
        index++; // skip the HMD
        return index >= 0 && index < OpenVR.hmdTrackedDevicePoses.length ||
               OpenVR.hmdTrackedDevicePoses[index].bPoseIsValid != 0;
    }
    
    public static Quaternion getOrientation(int index) {
        if( isInputDeviceTracked(index) == false ) return null;
        OpenVRUtil.convertMatrix4toQuat(OpenVR.poseMatrices[index+1], rotStore[index]);
        return rotStore[index];
    }

    public static Vector3f getPosition(int index) {
        if( isInputDeviceTracked(index) == false ) return null;
        // the hmdPose comes in rotated funny, fix that here
        OpenVR.poseMatrices[index+1].toTranslationVector(posStore[index]);
        posStore[index].x = -posStore[index].x;
        posStore[index].z = -posStore[index].z;
        return posStore[index];
    }
    
    public static void getPositionAndOrientation(int index, Vector3f storePos, Quaternion storeRot) {
        if( isInputDeviceTracked(index) == false ) {
            storePos.set(Vector3f.ZERO);
            storeRot.set(Quaternion.DIRECTION_Z);
        } else {
            OpenVR.poseMatrices[index+1].toTranslationVector(storePos);
            storePos.x = -storePos.x;
            storePos.z = -storePos.z;
            OpenVRUtil.convertMatrix4toQuat(OpenVR.poseMatrices[index+1], storeRot);
        }
    }    
    
    /*
        where is the headset pointing, after all rotations are combined?
        depends on observer rotation, if any
    */
    public static Quaternion getFinalObserverRotation(int index) {
        OpenVRViewManager vrvm = VRApplication.getVRViewManager();
        if( vrvm == null || isInputDeviceTracked(index) == false ) return null;
        Object obs = VRApplication.getObserver();
        if( obs instanceof Camera ) {
            return getOrientation(index).multLocal(((Camera)obs).getRotation());
        } else {
            return getOrientation(index).multLocal(((Spatial)obs).getWorldRotation());
        }
    }
    
    /*
        where is the headset, after all positional tracking is complete?
        includes observer position, if any
    */    
    public static Vector3f getFinalObserverPosition(int index) {
        OpenVRViewManager vrvm = VRApplication.getVRViewManager();
        if( vrvm == null || isInputDeviceTracked(index) == false ) return null;
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
