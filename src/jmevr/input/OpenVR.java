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
import com.jme3.system.lwjgl.LwjglAbstractDisplay;
import com.sun.jna.Pointer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import jmevr.util.OpenVRUtil;
import jopenvr.HmdMatrix34_t;
import jopenvr.HmdMatrix44_t;
import jopenvr.JOpenVRLibrary;
import jopenvr.TrackedDevicePose_t;
import org.lwjgl.Sys;

/**
 *
 * @author phr00t
 */
public class OpenVR {

    private static Pointer vrsystem;
    private static Pointer vrCompositor;
    private static boolean forceInitialize = false, initSuccess = false;
    
    private static IntBuffer hmdDisplayFrequency;
    private static TrackedDevicePose_t.ByReference hmdTrackedDevicePoseReference;
    private static TrackedDevicePose_t[] hmdTrackedDevicePoses;
    
    private static IntBuffer hmdErrorStore;
    
    private static final Quaternion rotStore = new Quaternion();
    private static final Vector3f posStore = new Vector3f();
    
    private static FloatBuffer tlastVsync;
    public static LongBuffer _tframeCount;
    
    // for debugging latency
    private int frames = 0;    
    
    private static Matrix4f[] poseMatrices;
    
    private static final Matrix4f hmdPose = Matrix4f.IDENTITY.clone();
    private static Matrix4f hmdProjectionLeftEye;
    private static Matrix4f hmdProjectionRightEye;
    private static Matrix4f hmdPoseLeftEye;
    private static Matrix4f hmdPoseRightEye;
    
    private static Vector3f hmdPoseLeftEyeVec, hmdPoseRightEyeVec;
    
    private static float vsyncToPhotons, timePerFrame;
    public static long _enteringVSyncTime;
    public static long _timerResolution, _frameCount;
    
    public static Pointer getVRSystemInstance() {
        return vrsystem;
    }
    
    public static Pointer getVRCompositorInstance() {
        return vrCompositor;
    }
    
    public String getName() {
        return "OpenVR";
    }
    
    // this will dynamically get larger until we are making vsync reliably
    private static float latencyBufferTime = 0f;       
    
    /*
        set this lower to decrease latency, but risk dropping frames during frametime fluctuations
        set heigher to increase latency, but allow more time for frames to complete
        defaults to 0.006f, which is 6ms
    */
    public static void setLatencySafetyTime(float time) {
        latencyBufferTime = time;
    }
    
    public static float getLatencySafetyTime() {
        return latencyBufferTime;
    }
    
    private static boolean enableDebugLatency = false;
    public static void printLatencyInfoToConsole(boolean set) {
        enableDebugLatency = set;
    }

    public boolean initialize() {
        hmdErrorStore = IntBuffer.allocate(1);
        vrsystem = JOpenVRLibrary.VR_Init(hmdErrorStore);
        if( vrsystem == null || hmdErrorStore.get(0) != 0 ) {
            Pointer errstr = JOpenVRLibrary.VR_GetStringForHmdError(hmdErrorStore.get(0));
            System.out.println("OpenVR Initialize Result: " + errstr.getString(0));
            return false;
        } else {
            System.out.println("OpenVR initialized & VR connected.");
            
            tlastVsync = FloatBuffer.allocate(1);
            _tframeCount = LongBuffer.allocate(1);
            _timerResolution = Sys.getTimerResolution();
            _enteringVSyncTime = 1; // set a >0 number so it doesn't try and wait the first frame
            
            hmdDisplayFrequency = IntBuffer.allocate(1);
            hmdDisplayFrequency.put( (int) JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_DisplayFrequency_Float);
            hmdDisplayFrequency = IntBuffer.allocate(1);
            hmdDisplayFrequency.put( (int) JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_SecondsFromVsyncToPhotons_Float);
            hmdTrackedDevicePoseReference = new TrackedDevicePose_t.ByReference();
            hmdTrackedDevicePoses = (TrackedDevicePose_t[])hmdTrackedDevicePoseReference.toArray(JOpenVRLibrary.k_unMaxTrackedDeviceCount);
            poseMatrices = new Matrix4f[JOpenVRLibrary.k_unMaxTrackedDeviceCount];
            for(int i=0;i<poseMatrices.length;i++) poseMatrices[i] = new Matrix4f();

            vsyncToPhotons = JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_SecondsFromVsyncToPhotons_Float, hmdErrorStore);
            timePerFrame = 1f / JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_DisplayFrequency_Float, hmdErrorStore);
            
            // disable all this stuff which kills performance
            hmdTrackedDevicePoseReference.setAutoRead(false);
            hmdTrackedDevicePoseReference.setAutoWrite(false);
            hmdTrackedDevicePoseReference.setAutoSynch(false);
            
            initSuccess = true;
            return true;
        }
    }
    
    public boolean initOpenVRCompositor() {
        vrCompositor = JOpenVRLibrary.VR_GetGenericInterface(JOpenVRLibrary.IVRCompositor_Version, hmdErrorStore);
        LwjglAbstractDisplay.runRightBeforeVSync = null;
        if(vrCompositor != null && hmdErrorStore.get(0) == 0){                
            System.out.println("OpenVR Compositor initialized OK.");
            return true;
        } else {
            System.out.println("OpenVR Compositor error: " + JOpenVRLibrary.VR_GetStringForHmdError(hmdErrorStore.get(0)).getString(0));
            return false;
        }
    }

    public void forceInitializeSuccess() {
        forceInitialize = true;
    }

    public void destroy() {
        JOpenVRLibrary.VR_Shutdown();
    }

    public boolean isInitialized() {
        return forceInitialize || initSuccess;
    }

    public void reset() {
        if( vrsystem == null ) return;
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
    
    public float getFOV() {
        if( vrsystem == null ) return 70f;
        float val = JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_FieldOfViewBottomDegrees_Float, hmdErrorStore);
        if( val <= 0f ) return 95f;
        return val;
    }

    public float getInterpupillaryDistance() {
        if( vrsystem == null ) return 0.064f;
        return JOpenVRLibrary.VR_IVRSystem_GetFloatTrackedDeviceProperty(vrsystem, JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd, JOpenVRLibrary.TrackedDeviceProperty.TrackedDeviceProperty_Prop_UserIpdMeters_Float, hmdErrorStore);
    }
    
    public Quaternion getOrientation() {
        OpenVRUtil.convertMatrix4toQuat(hmdPose, rotStore);
        return rotStore;
    }

    public Vector3f getPosition() {
        hmdPose.toTranslationVector(posStore);
        return posStore;
    }
    
    public void updatePose(){
        if(vrsystem == null) return;
        if(vrCompositor != null) {
           JOpenVRLibrary.VR_IVRCompositor_WaitGetPoses(vrCompositor, hmdTrackedDevicePoseReference, hmdTrackedDevicePoses.length, null, 0);
        } else {
            // wait a bit before getting a pose
            // how long do we have to wait?
            if( _enteringVSyncTime <= 0 ) {
                float renderTime = (float)-_enteringVSyncTime / _timerResolution;
                float waitAvailable = timePerFrame - renderTime;                                
                if( frames == 10 ) {
                    System.out.println("Time available to wait: " + Float.toString(waitAvailable));
                    System.out.println("Current buffer time: " + Float.toString(latencyBufferTime));
                    System.out.println("Render time: " + Float.toString(renderTime));
                }
                if( waitAvailable > latencyBufferTime ) {
                    long waitTime = Math.round(1000f * (waitAvailable - latencyBufferTime));
                    if( frames == 10 ) {
                        System.out.println("WAITING - Time: " + Long.toString(waitTime));
                    }
                    try {
                        Thread.sleep(waitTime);
                    } catch(Exception e) { }
                }
            }
            
            _enteringVSyncTime = Sys.getTime();
            
            JOpenVRLibrary.VR_IVRSystem_GetTimeSinceLastVsync(vrsystem, tlastVsync, _tframeCount);
            float fSecondsUntilPhotons = timePerFrame - tlastVsync.get(0) + vsyncToPhotons;
            
            // handle skipping frame adjustment
            long nowCount = _tframeCount.get(0);
            if( nowCount - _frameCount > 1 ) {
                // skipped a frame!
                if( enableDebugLatency ) System.out.println("Frame skipped!");                
                if( latencyBufferTime < timePerFrame ) latencyBufferTime += 0.002f;
            } else if( latencyBufferTime > 0f ) {
                // no frame skipped
                if( frames == 10 ) System.out.println("No frame skipped.");
                latencyBufferTime -= 0.000002f;
            }            
            _frameCount = nowCount;
            
            if( enableDebugLatency ) {
                if( frames == 10 ) {
                    System.out.println("Predict ahead time: " + Float.toString(fSecondsUntilPhotons));
                }
                frames = (frames + 1) % 60;
            }
            
            JOpenVRLibrary.VR_IVRSystem_GetDeviceToAbsoluteTrackingPose(vrsystem, JOpenVRLibrary.TrackingUniverseOrigin.TrackingUniverseOrigin_TrackingUniverseSeated, fSecondsUntilPhotons, hmdTrackedDevicePoseReference, JOpenVRLibrary.k_unMaxTrackedDeviceCount);   
        }
        
        hmdTrackedDevicePoseReference.read(); // pull updated pose information set in native memory from functions above
        
        for (int nDevice = 0; nDevice < JOpenVRLibrary.k_unMaxTrackedDeviceCount; ++nDevice ){
            if( hmdTrackedDevicePoses[nDevice].bPoseIsValid != 0 ){
                OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(hmdTrackedDevicePoses[nDevice].mDeviceToAbsoluteTracking, poseMatrices[nDevice]);
            }
        }
        if ( hmdTrackedDevicePoses[JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd].bPoseIsValid != 0 ){
            poseMatrices[JOpenVRLibrary.k_unTrackedDeviceIndex_Hmd].invert(hmdPose);
        } else {
            hmdPose.set(Matrix4f.IDENTITY);
        }
    }

    public Matrix4f getPositionAndOrientation() {
        return hmdPose;
    }

    public Matrix4f getHMDMatrixProjectionLeftEye(Camera cam){
        if( hmdProjectionLeftEye != null ) {
            return hmdProjectionLeftEye;
        } else if(vrsystem == null){
            return cam.getProjectionMatrix();
        } else {
            // eyes seem to be flipped, swap them here
            HmdMatrix44_t mat = JOpenVRLibrary.VR_IVRSystem_GetProjectionMatrix(vrsystem, JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left, cam.getFrustumNear(), cam.getFrustumFar(), JOpenVRLibrary.GraphicsAPIConvention.GraphicsAPIConvention_API_OpenGL);
            hmdProjectionLeftEye = new Matrix4f();
            return OpenVRUtil.convertSteamVRMatrix4ToMatrix4f(mat, hmdProjectionLeftEye);
        }
    }
        
    public Matrix4f getHMDMatrixProjectionRightEye(Camera cam){
        if( hmdProjectionRightEye != null ) {
            return hmdProjectionRightEye;
        } else if(vrsystem == null){
            return cam.getProjectionMatrix();
        } else {
            // eyes seem to be swapped, flip them here
            HmdMatrix44_t mat = JOpenVRLibrary.VR_IVRSystem_GetProjectionMatrix(vrsystem, JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right, cam.getFrustumNear(), cam.getFrustumFar(), JOpenVRLibrary.GraphicsAPIConvention.GraphicsAPIConvention_API_OpenGL);
            hmdProjectionRightEye = new Matrix4f();
            return OpenVRUtil.convertSteamVRMatrix4ToMatrix4f(mat, hmdProjectionRightEye);
        }
    }
    
    public Vector3f getHMDVectorPoseLeftEye() {
        if( hmdPoseLeftEyeVec == null ) {
            hmdPoseLeftEyeVec = getHMDMatrixPoseLeftEye().toTranslationVector();
            // set default IPD if none
            if( hmdPoseLeftEyeVec.x == 0f ) hmdPoseLeftEyeVec.x = 0.065f * -0.5f;
        }
        return hmdPoseLeftEyeVec;
    }
    
    public Vector3f getHMDVectorPoseRightEye() {
        if( hmdPoseRightEyeVec == null ) {
            hmdPoseRightEyeVec = getHMDMatrixPoseRightEye().toTranslationVector();
            // set default IPD if none
            if( hmdPoseRightEyeVec.x == 0f ) hmdPoseRightEyeVec.x = 0.065f * 0.5f;
        }
        return hmdPoseRightEyeVec;
    }
    
    public Matrix4f getHMDMatrixPoseLeftEye(){
        if( hmdPoseLeftEye != null ) {
            return hmdPoseLeftEye;
        } else if(vrsystem == null) {
            return Matrix4f.IDENTITY;
        } else {
            HmdMatrix34_t mat = JOpenVRLibrary.VR_IVRSystem_GetEyeToHeadTransform(vrsystem, JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left);
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
            HmdMatrix34_t mat = JOpenVRLibrary.VR_IVRSystem_GetEyeToHeadTransform(vrsystem, JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right);
            hmdPoseRightEye = new Matrix4f();
            return OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(mat, hmdPoseRightEye);
        }
    }
    
}
