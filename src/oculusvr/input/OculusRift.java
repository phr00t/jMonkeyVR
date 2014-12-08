/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.input;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.FrameTiming;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.OvrLibrary;
import com.oculusvr.capi.OvrQuaternionf;
import com.oculusvr.capi.OvrVector3f;
import com.oculusvr.capi.Posef;
import java.util.logging.Level;
import java.util.logging.Logger;
import oculusvr.post.OculusFilter;
import oculusvr.state.OVRAppState;
import oculusvr.util.OculusRiftUtil;

/**
 *
 * @author Rickard
 */
public class OculusRift {

    private static HMDInfo info = new HMDInfo();
    private static boolean initHMDSuccess;
    private static EyeRenderDesc[] eyeRenderDesc;
    private static OVRAppState appState;
    private static boolean renderInit;
    
    public static int frameIndex;
    private static final OvrVector3f eyeOffsets[] = (OvrVector3f[]) new OvrVector3f().toArray(2);
    private static Posef[] eyePoses;
    
    // returns true if it was successfully loaded
    public static boolean initialize() {
        Hmd.initialize();
        loadedHmd = Hmd.create(0);
        initHMDSuccess = loadedHmd != null;
        if (initHMDSuccess) {
            updateHMDInfo();
            System.out.println("Oculus Rift initialized: " + info);
            
            
        } else {
            System.out.println("Oculus Rift NOT found or initialized; virtual DK2 created.");
            loadedHmd = Hmd.createDebug(OvrLibrary.ovrHmdType.ovrHmd_DK2);
            info.createFakeValues();
        }
        
        loadedHmd.configureTracking(OvrLibrary.ovrTrackingCaps.ovrTrackingCap_Orientation | OvrLibrary.ovrTrackingCaps.ovrTrackingCap_MagYawCorrection
                              | OvrLibrary.ovrTrackingCaps.ovrTrackingCap_Position, 0);
        return initHMDSuccess;
    }
    
    // can be useful for debugging
    public static void forceInitializeSuccess() {
        initHMDSuccess = true;
    }
    
    public static void setAppState(OVRAppState appState) {
        OculusRift.appState = appState;
    }
    
    public static OVRAppState getAppState() {
        return OculusRift.appState;
    }
    
    public static void initRendering(int width, int height, int samples) {
        if( eyeRenderDesc == null )
            eyeRenderDesc = OculusRiftUtil.configureRendering(loadedHmd, width, height, samples); // we will use debug hmd at least here   
        for(int eyeIndex  = 0; eyeIndex < 2; eyeIndex++){
            eyeOffsets[eyeIndex].x = eyeRenderDesc[eyeIndex].HmdToEyeViewOffset.x;
            eyeOffsets[eyeIndex].y = eyeRenderDesc[eyeIndex].HmdToEyeViewOffset.y;
            eyeOffsets[eyeIndex].z = eyeRenderDesc[eyeIndex].HmdToEyeViewOffset.z;
        }
        
        renderInit = true;
    }

    public static HMDInfo updateHMDInfo() {
        info.HResolution = OculusRift.getHResolution();
        info.VResolution = OculusRift.getVResolution();
        info.InterpupillaryDistance = OculusRift.getInterpupillaryDistance();
        info.DesktopX = OculusRift.getDesktopX();
        info.DesktopY = OculusRift.getDesktopY();
        return info;
    }

    public static HMDInfo getHMDInfo() {
        return info;
    }

    public static void main(String[] args) {
        try {
            initialize();
            System.out.println(isInitialized());
            destroy();
        } catch (Exception ex) {
            Logger.getLogger(OculusRift.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void destroy() {
        //Logger.getLogger(OculusRift.class.getName()).log(Level.INFO, "Cleaning up");
        System.out.println("Destroying Oculus Rift");
        info = null;
        if (loadedHmd == null) {
            return;
        }
        loadedHmd.destroy();
        Hmd.shutdown();
        loadedHmd = null;
    }

    public static boolean isInitialized() {
        return initHMDSuccess;
    }
    public static Hmd loadedHmd;
    private final static Quaternion orientation = new Quaternion(), orientationi = new Quaternion();
    private final static Vector3f position = new Vector3f(), positioni = new Vector3f(), posAcc = new Vector3f(), angAcc = new Vector3f();

    // HMDInfo
    public static int getHResolution() {
        return loadedHmd == null ? 1280 : loadedHmd.Resolution.w;
    }
    
    public static float getFOV() {
        return loadedHmd == null ? 130f : (float)Math.atan(loadedHmd.MaxEyeFov[0].DownTan) * 2f * 57.2957795f;
    }

    public static int getVResolution() {
        return loadedHmd == null ? 800 : loadedHmd.Resolution.h;
    }

    public static float getInterpupillaryDistance() {
        return loadedHmd == null ? 0.0624f : loadedHmd.getFloat(OvrLibrary.OVR_KEY_IPD, 0.0624f);
    }

    public static int getDesktopX() {
        return loadedHmd == null ? 0 : loadedHmd.WindowsPos.x;
    }

    public static int getDesktopY() {
        return loadedHmd == null ? 0 : loadedHmd.WindowsPos.y;
    }

    public static Quaternion getOrientation() {
        if (eyePoses == null) {
            return Quaternion.DIRECTION_Z;
        }
        OvrQuaternionf rot = eyePoses[0].Orientation;//loadedHmd.getEyePose(0).Orientation;
        orientation.set(rot.x, -rot.y, rot.z, -rot.w);
        return orientation;
    }

    public static Quaternion getInstantOrientation() {
        if (loadedHmd == null) {
            return Quaternion.DIRECTION_Z;
        }
        OvrQuaternionf rot = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).HeadPose.Pose.Orientation;
        orientationi.set(rot.x, -rot.y, rot.z, -rot.w);
        return orientationi;
    }
    
    public static Vector3f getPosition() {
        if (eyePoses == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = eyePoses[0].Position;
        position.set(-pos.x, pos.y, -pos.z);
        return position;
    }
    
    public static Vector3f getInstantPosition() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).HeadPose.Pose.Position;
        positioni.set(pos.x, pos.y, pos.z);
        return positioni;
    }
    
    public static Vector3f getAngularAcceleration() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).RawSensorData.Gyro;
        angAcc.set(pos.x, pos.y, pos.z);
        return angAcc;
    }

    public static Vector3f getPositionalAcceleration() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).RawSensorData.Magnetometer;
        posAcc.set(pos.x, pos.y, pos.z);
        return posAcc;
    }
    
    public static Vector3f getAccelerometer() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).RawSensorData.Accelerometer;
        angAcc.set(pos.x, pos.y, pos.z);
        return angAcc;
    }

    public static void reset() {
        if (loadedHmd == null) {
            return;
        }
        loadedHmd.recenterPose();
    }

    public static EyeRenderDesc getEyeRenderDesc(int eyeIndex) {
        return eyeRenderDesc[eyeIndex];
    }
    
    public static float getEyeHeight(){
        float eyeHeight = 0;
        
        eyeHeight = loadedHmd.getFloat(OvrLibrary.OVR_KEY_EYE_HEIGHT, eyeHeight);
        return eyeHeight;
    }
    
    public static void beginFrameTiming(){
        loadedHmd.beginFrameTiming(0);
    }
    
    public static void endFrameTiming() {
        loadedHmd.endFrameTiming();
    }
    
    public static boolean getRenderInit(){
        return renderInit;
    }

    public static Posef[] getEyePoses() {
        return eyePoses;
    }

    public static void setEyePoses(Posef[] eyePoses) {
        OculusRift.eyePoses = eyePoses;
    }

    public static OvrVector3f[] getEyeOffsets() {
        return eyeOffsets;
    }
    
    public static void toggleLowPersistence(){
        int caps = loadedHmd.getEnabledCaps();
        if (0 != (caps & OvrLibrary.ovrHmdCaps.ovrHmdCap_LowPersistence)) {
            System.out.println("LOW PERSISTENCE OFF");
            loadedHmd.setEnabledCaps(caps & ~OvrLibrary.ovrHmdCaps.ovrHmdCap_LowPersistence);
        } else {
            System.out.println("LOW PERSISTENCE ON");
            loadedHmd.setEnabledCaps(caps | OvrLibrary.ovrHmdCaps.ovrHmdCap_LowPersistence);
        }
    }
}
