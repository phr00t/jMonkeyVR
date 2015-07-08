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
import com.sun.jna.Pointer;
import java.nio.IntBuffer;
import jmevr.util.OpenVRUtil;
import jopenvr.HmdMatrix34_t;
import jopenvr.HmdMatrix44_t;
import jopenvr.JOpenVRLibrary;
import jopenvr.TrackedDevicePose_t;

/**
 *
 * @author phr00t
 */
public class OpenVR implements VRHMD {

    private static Pointer vrsystem;
    private static Pointer vrCompositor;
    private static boolean forceInitialize = false, initSuccess = false;
    
    private static IntBuffer hmdDisplayFrequency;
    private static final TrackedDevicePose_t.ByReference hmdTrackedDevicePoseReference = new TrackedDevicePose_t.ByReference();
    private static TrackedDevicePose_t[] hmdTrackedDevicePoses;
    
    private static IntBuffer hmdErrorStore;
    
    private static final Quaternion rotStore = new Quaternion();
    private static final Vector3f posStore = new Vector3f();
    
    private static final HMDInfo hmdinfo = new HMDInfo();
    private static Matrix4f[] poseMatrices;
    
    private static final Matrix4f hmdPose = Matrix4f.IDENTITY.clone();
    private static final Matrix4f hmdProjectionLeftEye = Matrix4f.IDENTITY.clone();
    private static final Matrix4f hmdProjectionRightEye = Matrix4f.IDENTITY.clone();
    private static final Matrix4f hmdPoseLeftEye = Matrix4f.IDENTITY.clone();
    private static final Matrix4f hmdPoseRightEye = Matrix4f.IDENTITY.clone();
    
    public static Pointer getVRSystemInstance() {
        return vrsystem;
    }
    
    public static Pointer getVRCompositorInstance() {
        return vrCompositor;
    }
    
    @Override
    public String getName() {
        return "OpenVR";
    }

    @Override
    public boolean initialize() {
        hmdErrorStore = IntBuffer.allocate(1);
        vrsystem = JOpenVRLibrary.VR_Init(hmdErrorStore);
        if( hmdErrorStore.get(0) != 0 ) {
            Pointer errstr = JOpenVRLibrary.VR_GetStringForHmdError(hmdErrorStore.get(0));
            System.out.println("OpenVR Initialize Result: " + errstr.getString(0));
            return false;
        } else {
            System.out.println("OpenVR initialized & VR connected.");
            
            hmdDisplayFrequency = IntBuffer.allocate(1);
            hmdDisplayFrequency.put( (int) JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_DisplayFrequency_Float);
            hmdDisplayFrequency = IntBuffer.allocate(1);
            hmdDisplayFrequency.put( (int) JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_SecondsFromVsyncToPhotons_Float);
            hmdTrackedDevicePoses = (TrackedDevicePose_t[])hmdTrackedDevicePoseReference.toArray(JOpenVRLibrary.k_unMaxTrackedDeviceCount);
            
            initSuccess = true;
            return true;
        }
    }
    
    public boolean initOpenVRCompositor() {
        // this was taken straight from https://raw.githubusercontent.com/ValveSoftware/openvr/master/headers/openvr_capi.h
        // char * const IVRCompositor_Version = "IVRCompositor_006";

        vrCompositor = JOpenVRLibrary.VR_GetGenericInterface("IVRCompositor_006", hmdErrorStore);
        if(vrCompositor != null && hmdErrorStore.get(0) == 0){                
            System.out.println("OpenVR Compositor initialized OK.");
            return true;
        } else {
            System.out.println("OpenVR Compositor error: " + JOpenVRLibrary.VR_GetStringForHmdError(hmdErrorStore.get(0)).getString(0));
            return false;
        }   
    }

    @Override
    public void forceInitializeSuccess() {
        forceInitialize = true;
    }

    @Override
    public void initRendering(int width, int height, int samples) {
        // TODO: this
    }

    @Override
    public HMDInfo updateHMDInfo() {
         // i think this is the only value used...
        hmdinfo.InterpupillaryDistance = getInterpupillaryDistance();
        return hmdinfo;
    }

    @Override
    public HMDInfo getHMDInfo() {
        return hmdinfo;
    }

    @Override
    public void destroy() {
        JOpenVRLibrary.VR_Shutdown();
    }

    @Override
    public boolean isInitialized() {
        return forceInitialize || initSuccess;
    }

    @Override
    public void reset() {
        JOpenVRLibrary.VR_IVRSystem_ResetSeatedZeroPose(vrsystem);
    }

    public void getRenderSize(Vector2f store) {
        if( vrsystem == null ) {
            store.x = 1280f;
            store.y = 800f;
        } else {
            IntBuffer x = IntBuffer.allocate(1);
            IntBuffer y = IntBuffer.allocate(1);
            JOpenVRLibrary.VR_IVRSystem_GetRecommendedRenderTargetSize(vrsystem, x, y);
            store.x = x.get(0);
            store.y = y.get(0);
        }
    }
    
    @Override
    public int getHResolution() {        
        return 1280; // i don't think this is used/needed... (shader perhaps)
    }

    @Override
    public float getFOV() {
        if( vrsystem == null ) return 130f;
        float val = JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_FieldOfViewBottomDegrees_Float, hmdErrorStore);
        if( val <= 0f ) return 130f;
        return val;
    }

    @Override
    public int getVResolution() {
        return 800; // i don't think this is used/needed... (shader perhaps)
    }

    @Override
    public float getInterpupillaryDistance() {
        if( vrsystem == null ) return 0.064f;
        return JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_UserIpdMeters_Float, hmdErrorStore);
    }

    @Override
    public float getEyeHeight() {
        return 1f;
    }

    @Override
    public Quaternion getOrientation() {
        hmdPose.toRotationQuat(rotStore);
        return rotStore;
    }

    @Override
    public Vector3f getPosition() {
        hmdPose.toTranslationVector(posStore);
        return posStore;
    }

    @Override
    public Vector3f getAngularAcceleration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getPositionalAcceleration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getAccelerometer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void updatePose(){
        if(vrsystem == null){
            return;
        }
        if(vrCompositor != null){
           JOpenVRLibrary.VR_IVRCompositor_WaitGetPoses(vrCompositor, hmdTrackedDevicePoseReference, hmdTrackedDevicePoses.length, null, 0);
        } else {
            // We just got done with the glFinish - the seconds since last vsync should be 0.
            float fSecondsSinceLastVsync = 0.0f;
                
            float fFrameDuration = 1.0f / JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_DisplayFrequency_Float, hmdErrorStore);
            float fSecondsUntilPhotons = fFrameDuration - fSecondsSinceLastVsync + JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_SecondsFromVsyncToPhotons_Float, hmdErrorStore);
            
            JOpenVRLibrary.VR_IVRSystem_GetDeviceToAbsoluteTrackingPose(vrsystem, JOpenVRLibrary.TrackingUniverseOrigin.TrackingUniverseOrigin_TrackingUniverseSeated, fSecondsUntilPhotons, hmdTrackedDevicePoseReference, JOpenVRLibrary.k_unMaxTrackedDeviceCount);
        }
        for (int nDevice = 0; nDevice < JOpenVRLibrary.k_unMaxTrackedDeviceCount; ++nDevice ){
            if( hmdTrackedDevicePoses[nDevice].bPoseIsValid != 0 ){
                OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(hmdTrackedDevicePoses[nDevice].mDeviceToAbsoluteTracking, poseMatrices[nDevice]);
            }
        }
        if ( hmdTrackedDevicePoses[JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd].bPoseIsValid != 0 ){
            poseMatrices[JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd].invert(hmdPose);
        }
    }

    @Override
    public Matrix4f getPositionAndOrientation() {
        return hmdPose;
    }

    @Override
    public Matrix4f getEyeTransform(int eye) {
        return null;
    }
    
    public Matrix4f getHMDMatrixProjectionEye(int eye, Camera cam){
        if(vrsystem == null){
            return new Matrix4f();
        }
        HmdMatrix44_t mat = JOpenVRLibrary.VR_IVRSystem_GetProjectionMatrix(vrsystem, eye == 0 ? JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left :JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right, cam.getFrustumNear(), cam.getFrustumFar(), JOpenVRLibrary.GraphicsAPIConvention.GraphicsAPIConvention_API_OpenGL);
        return OpenVRUtil.convertSteamVRMatrix4ToMatrix4f(mat, eye == 0 ? hmdProjectionLeftEye : hmdProjectionRightEye);
    }
        
    public Matrix4f getHMDMatrixPoseEye(int eye){
        if(vrsystem == null){
            return new Matrix4f();
        }
        HmdMatrix34_t mat = JOpenVRLibrary.VR_IVRSystem_GetEyeToHeadTransform(vrsystem, eye == 0 ? JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left :JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right);
        
        return OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(mat, eye == 0 ? hmdPoseLeftEye : hmdPoseRightEye);
    }

    
////-----------------------------------------------------------------------------
//// Purpose:
////-----------------------------------------------------------------------------
//Matrix4 CMainApplication::GetCurrentViewProjectionMatrix( vr::Hmd_Eye nEye )
//{
//	Matrix4 matMVP;
//	if( nEye == vr::Eye_Left )
//	{
//		matMVP = m_mat4ProjectionLeft * m_mat4eyePosLeft * m_mat4HMDPose;
//	}
//	else if( nEye == vr::Eye_Right )
//	{
//		matMVP = m_mat4ProjectionRight * m_mat4eyePosRight *  m_mat4HMDPose;
//	}
//
//	return matMVP;
//}   
}
