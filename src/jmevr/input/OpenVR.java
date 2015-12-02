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
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.concurrent.TimeUnit;
import jmevr.app.VRApplication;
import jmevr.util.OpenVRUtil;
import jopenvr.HmdMatrix34_t;
import jopenvr.HmdMatrix44_t;
import jopenvr.JOpenVRLibrary;
import jopenvr.JOpenVRLibrary.VREvent_t;
import jopenvr.TrackedDevicePose_t;

/**
 *
 * @author phr00t
 */
public class OpenVR {

    private static Pointer vrsystem;
    private static Pointer vrCompositor;
    private static VREvent_t tempEvent = new VREvent_t();
    private static boolean initSuccess = false, flipEyes = false;
    
    private static IntBuffer hmdDisplayFrequency;
    private static TrackedDevicePose_t.ByReference hmdTrackedDevicePoseReference;
    protected static TrackedDevicePose_t[] hmdTrackedDevicePoses;
    
    protected static IntBuffer hmdErrorStore;
    
    private static final Quaternion rotStore = new Quaternion();
    private static final Vector3f posStore = new Vector3f();
    
    private static FloatBuffer tlastVsync;
    public static LongBuffer _tframeCount;
    
    // for debugging latency
    private int frames = 0;    
    
    protected static Matrix4f[] poseMatrices;
    
    private static final Matrix4f hmdPose = Matrix4f.IDENTITY.clone();
    private static Matrix4f hmdProjectionLeftEye;
    private static Matrix4f hmdProjectionRightEye;
    private static Matrix4f hmdPoseLeftEye;
    private static Matrix4f hmdPoseRightEye;
    
    private static Vector3f hmdPoseLeftEyeVec, hmdPoseRightEyeVec;
    
    private static float vsyncToPhotons;
    private static double timePerFrame, frameCountRun;
    private static long frameCount;
    
    public static Pointer getVRSystemInstance() {
        return vrsystem;
    }
    
    public static Pointer getVRCompositorInstance() {
        return vrCompositor;
    }
    
    public String getName() {
        return "OpenVR";
    }
    
    private static long latencyWaitTime = 0;
    
    /*
        do not use. set via preconfigure routine in VRApplication
    */
    public static void _setFlipEyes(boolean set) {
        flipEyes = set;
    }
    
    private static boolean enableDebugLatency = false;
    public static void printLatencyInfoToConsole(boolean set) {
        enableDebugLatency = set;
    }

    public static int getDisplayFrequency() {
        if( hmdDisplayFrequency == null ) return 0;
        return hmdDisplayFrequency.get(0);
    }
    
    public boolean initialize() {
        hmdErrorStore = IntBuffer.allocate(1);
        vrsystem = JOpenVRLibrary.VR_Init(hmdErrorStore, JOpenVRLibrary.EVRApplicationType.EVRApplicationType_VRApplication_Scene);
        if( vrsystem == null || hmdErrorStore.get(0) != 0 ) {
            Pointer errstr = JOpenVRLibrary.VR_GetStringForHmdError(hmdErrorStore.get(0));
            System.out.println("OpenVR Initialize Result: " + errstr.getString(0));
            return false;
        } else {
            System.out.println("OpenVR initialized & VR connected.");
            
            tlastVsync = FloatBuffer.allocate(1);
            _tframeCount = LongBuffer.allocate(1);
            
            hmdDisplayFrequency = IntBuffer.allocate(1);
            hmdDisplayFrequency.put( (int) JOpenVRLibrary.ETrackedDeviceProperty.ETrackedDeviceProperty_Prop_DisplayFrequency_Float);
            hmdTrackedDevicePoseReference = new TrackedDevicePose_t.ByReference();
            hmdTrackedDevicePoses = (TrackedDevicePose_t[])hmdTrackedDevicePoseReference.toArray(JOpenVRLibrary.k_unMaxTrackedDeviceCount);
            poseMatrices = new Matrix4f[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
            for(int i=0;i<poseMatrices.length;i++) poseMatrices[i] = new Matrix4f();

            vsyncToPhotons = JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.ETrackedDeviceProperty.ETrackedDeviceProperty_Prop_SecondsFromVsyncToPhotons_Float, hmdErrorStore);
            timePerFrame = 1.0 / hmdDisplayFrequency.get(0);
            
            // disable all this stuff which kills performance
            hmdTrackedDevicePoseReference.setAutoRead(false);
            hmdTrackedDevicePoseReference.setAutoWrite(false);
            hmdTrackedDevicePoseReference.setAutoSynch(false);
            for(int i=0;i<JOpenVRLibrary.k_unMaxTrackedDeviceCount;i++) {
                hmdTrackedDevicePoses[i].setAutoRead(false);
                hmdTrackedDevicePoses[i].setAutoWrite(false);
                hmdTrackedDevicePoses[i].setAutoSynch(false);
            }
            
            // init controllers for the first time
            VRInput._updateConnectedControllers();
            
            // init bounds & chaperone info
            VRBounds.init();
            
            initSuccess = true;
            return true;
        }
    }
    
    public boolean initOpenVRCompositor() {
        vrCompositor = JOpenVRLibrary.VR_GetGenericInterface(JOpenVRLibrary.IVRCompositor_Version, hmdErrorStore);
        if(vrCompositor != null && hmdErrorStore.get(0) == 0){                
            System.out.println("OpenVR Compositor initialized OK.");
            if( VRApplication.isSeatedExperience() ) {
                JOpenVRLibrary.VR_IVRCompositor_SetTrackingSpace(vrCompositor, JOpenVRLibrary.ETrackingUniverseOrigin.ETrackingUniverseOrigin_TrackingUniverseSeated);
            } else {
                JOpenVRLibrary.VR_IVRCompositor_SetTrackingSpace(vrCompositor, JOpenVRLibrary.ETrackingUniverseOrigin.ETrackingUniverseOrigin_TrackingUniverseStanding);                
            }
            return true;
        } else {
            System.out.println("OpenVR Compositor error: " + JOpenVRLibrary.VR_GetStringForHmdError(hmdErrorStore.get(0)).getString(0));
            return false;
        }
    }

    public void destroy() {
        JOpenVRLibrary.VR_Shutdown();
    }

    public boolean isInitialized() {
        return initSuccess;
    }

    public void reset() {
        if( vrsystem == null ) return;
        JOpenVRLibrary.VR_IVRSystem_ResetSeatedZeroPose(vrsystem);
    }

    public void getRenderSize(Vector2f store) {
        if( vrsystem == null ) {
            // 1344x1512
            store.x = 1344f;
            store.y = 1512f;
        } else {
            IntBuffer x = IntBuffer.allocate(1);
            IntBuffer y = IntBuffer.allocate(1);
            JOpenVRLibrary.VR_IVRSystem_GetRecommendedRenderTargetSize(vrsystem, x, y);
            store.x = x.get(0);
            store.y = y.get(0);
        }
    }
    
    public float getFOV(int dir) {
        float val = 0f;
        if( vrsystem != null ) {      
            val = JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, dir, hmdErrorStore);
        }
        // verification of number
        if( val == 0f ) {
            return 55f;
        } else if( val <= 10f ) {
            // most likely a radian number
            return val * 57.2957795f;
        }
        return val;
    }

    public float getInterpupillaryDistance() {
        if( vrsystem == null ) return 0.065f;
        return JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.ETrackedDeviceProperty.ETrackedDeviceProperty_Prop_UserIpdMeters_Float, hmdErrorStore);
    }
    
    public Quaternion getOrientation() {
        OpenVRUtil.convertMatrix4toQuat(hmdPose, rotStore);
        return rotStore;
    }

    public Vector3f getPosition() {
        // the hmdPose comes in rotated funny, fix that here
        hmdPose.toTranslationVector(posStore);
        posStore.x = -posStore.x;
        posStore.z = -posStore.z;
        return posStore;
    }
    
    public void getPositionAndOrientation(Vector3f storePos, Quaternion storeRot) {
        hmdPose.toTranslationVector(storePos);
        storePos.x = -storePos.x;
        storePos.z = -storePos.z;
        storeRot.set(getOrientation());
    }    
    
    public void updatePose(){
        if(vrsystem == null) return;
        if(vrCompositor != null) {
           JOpenVRLibrary.VR_IVRCompositor_WaitGetPoses(vrCompositor, hmdTrackedDevicePoseReference, JOpenVRLibrary.k_unMaxTrackedDeviceCount, null, 0);
        } else {
            // wait
            if( latencyWaitTime > 0 ) OpenVRUtil.sleepNanos(latencyWaitTime);
                        
            JOpenVRLibrary.VR_IVRSystem_GetTimeSinceLastVsync(vrsystem, tlastVsync, _tframeCount);
            float fSecondsUntilPhotons = (float)timePerFrame - tlastVsync.get(0) + vsyncToPhotons;
            
            if( enableDebugLatency ) {
                if( frames == 10 ) {
                    System.out.println("Waited (nanos): " + Long.toString(latencyWaitTime));
                    System.out.println("Predict ahead time: " + Float.toString(fSecondsUntilPhotons));
                }
                frames = (frames + 1) % 60;            
            }            
            
            // handle skipping frame stuff
            long nowCount = _tframeCount.get(0);
            if( nowCount - frameCount > 1 ) {
                // skipped a frame!
                if( enableDebugLatency ) System.out.println("Frame skipped!");
                frameCountRun = 0;
                if( latencyWaitTime > 0 ) {
                    latencyWaitTime -= TimeUnit.MILLISECONDS.toNanos(1);
                    if( latencyWaitTime < 0 ) latencyWaitTime = 0;
                }
            } else if( latencyWaitTime < timePerFrame * 1000000000.0 ) {
                // didn't skip a frame, lets try waiting longer to improve latency
                frameCountRun++;
                latencyWaitTime += Math.round(Math.pow(frameCountRun / 10.0, 2.0));
            }

            frameCount = nowCount;
            
            JOpenVRLibrary.VR_IVRSystem_GetDeviceToAbsoluteTrackingPose(vrsystem,
                    VRApplication.isSeatedExperience()?JOpenVRLibrary.ETrackingUniverseOrigin.ETrackingUniverseOrigin_TrackingUniverseSeated:
                                                       JOpenVRLibrary.ETrackingUniverseOrigin.ETrackingUniverseOrigin_TrackingUniverseStanding,
                    fSecondsUntilPhotons, hmdTrackedDevicePoseReference, JOpenVRLibrary.k_unMaxTrackedDeviceCount);   
        }
        
        // deal with controllers being plugged in and out
        boolean hasEvent = false;
        while( JOpenVRLibrary.VR_IVRSystem_PollNextEvent(OpenVR.getVRSystemInstance(), tempEvent) != 0 ) {
            // wait until the events are clear..
            hasEvent = true;
        }
        if( hasEvent ) {
            // an event probably changed controller state
            VRInput._updateConnectedControllers();
        }
        //update controllers pose information
        VRInput._updateControllerStates();
                
        // read pose data from native
        for (int nDevice = 0; nDevice < JOpenVRLibrary.k_unMaxTrackedDeviceCount; ++nDevice ){
            hmdTrackedDevicePoses[nDevice].readField("bPoseIsValid");
            if( hmdTrackedDevicePoses[nDevice].bPoseIsValid != 0 ){
                hmdTrackedDevicePoses[nDevice].readField("mDeviceToAbsoluteTracking");
                OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(hmdTrackedDevicePoses[nDevice].mDeviceToAbsoluteTracking, poseMatrices[nDevice]);
            }            
        }
        
        if ( hmdTrackedDevicePoses[JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd].bPoseIsValid != 0 ){
            hmdPose.set(poseMatrices[JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd]);
        } else {
            hmdPose.set(Matrix4f.IDENTITY);
        }
    }

    public Matrix4f getHMDMatrixProjectionLeftEye(Camera cam){
        if( hmdProjectionLeftEye != null ) {
            return hmdProjectionLeftEye;
        } else if(vrsystem == null){
            return cam.getProjectionMatrix();
        } else {
            HmdMatrix44_t mat = JOpenVRLibrary.VR_IVRSystem_GetProjectionMatrix(vrsystem, JOpenVRLibrary.EVREye.EVREye_Eye_Left, cam.getFrustumNear(), cam.getFrustumFar(), JOpenVRLibrary.EGraphicsAPIConvention.EGraphicsAPIConvention_API_OpenGL);
            hmdProjectionLeftEye = new Matrix4f();
            OpenVRUtil.convertSteamVRMatrix4ToMatrix4f(mat, hmdProjectionLeftEye);
            return hmdProjectionLeftEye;
        }
    }
        
    public Matrix4f getHMDMatrixProjectionRightEye(Camera cam){
        if( hmdProjectionRightEye != null ) {
            return hmdProjectionRightEye;
        } else if(vrsystem == null){
            return cam.getProjectionMatrix();
        } else {
            HmdMatrix44_t mat = JOpenVRLibrary.VR_IVRSystem_GetProjectionMatrix(vrsystem, JOpenVRLibrary.EVREye.EVREye_Eye_Right, cam.getFrustumNear(), cam.getFrustumFar(), JOpenVRLibrary.EGraphicsAPIConvention.EGraphicsAPIConvention_API_OpenGL);
            hmdProjectionRightEye = new Matrix4f();
            OpenVRUtil.convertSteamVRMatrix4ToMatrix4f(mat, hmdProjectionRightEye);
            return hmdProjectionRightEye;
        }
    }
    
    public Vector3f getHMDVectorPoseLeftEye() {
        if( hmdPoseLeftEyeVec == null ) {
            hmdPoseLeftEyeVec = getHMDMatrixPoseLeftEye().toTranslationVector();
            // set default IPD if none or broken
            if( hmdPoseLeftEyeVec.x <= 0.080f * -0.5f || hmdPoseLeftEyeVec.x >= 0.040f * -0.5f ) {
                hmdPoseLeftEyeVec.x = 0.065f * -0.5f;
            }
            if( flipEyes == false ) hmdPoseLeftEyeVec.x *= -1f; // it seems these need flipping
        }
        return hmdPoseLeftEyeVec;
    }
    
    public Vector3f getHMDVectorPoseRightEye() {
        if( hmdPoseRightEyeVec == null ) {
            hmdPoseRightEyeVec = getHMDMatrixPoseRightEye().toTranslationVector();
            // set default IPD if none or broken
            if( hmdPoseRightEyeVec.x >= 0.080f * 0.5f || hmdPoseRightEyeVec.x <= 0.040f * 0.5f ) {
                hmdPoseRightEyeVec.x = 0.065f * 0.5f;
            }
            if( flipEyes == false ) hmdPoseRightEyeVec.x *= -1f; // it seems these need flipping
        }
        return hmdPoseRightEyeVec;
    }
    
    public Matrix4f getHMDMatrixPoseLeftEye(){
        if( hmdPoseLeftEye != null ) {
            return hmdPoseLeftEye;
        } else if(vrsystem == null) {
            return Matrix4f.IDENTITY;
        } else {
            HmdMatrix34_t mat = JOpenVRLibrary.VR_IVRSystem_GetEyeToHeadTransform(vrsystem, JOpenVRLibrary.EVREye.EVREye_Eye_Left);
            hmdPoseLeftEye = new Matrix4f();
            return OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(mat, hmdPoseLeftEye);
        }
    }
    
    public Matrix4f getHMDMatrixPoseRightEye(){
        if( hmdPoseRightEye != null ) {
            return hmdPoseRightEye;
        } else if(vrsystem == null) {
            return Matrix4f.IDENTITY;
        } else {
            HmdMatrix34_t mat = JOpenVRLibrary.VR_IVRSystem_GetEyeToHeadTransform(vrsystem, JOpenVRLibrary.EVREye.EVREye_Eye_Right);
            hmdPoseRightEye = new Matrix4f();
            return OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(mat, hmdPoseRightEye);
        }
    }
    
}
