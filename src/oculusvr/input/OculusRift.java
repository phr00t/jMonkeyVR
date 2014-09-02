/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.input;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.util.TempVars;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.FrameTiming;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.OvrLibrary;
import com.oculusvr.capi.OvrQuaternionf;
import com.oculusvr.capi.OvrVector3f;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static FrameTiming frameTiming;

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
    private static Quaternion orientation = new Quaternion();
    private static Vector3f position = new Vector3f(), posAcc = new Vector3f(), angAcc = new Vector3f();
    private static float predictValue;

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
        if (loadedHmd == null) {
            return Quaternion.DIRECTION_Z;
        }
        OvrQuaternionf rot = loadedHmd.getSensorState(frameTiming.ScanoutMidpointSeconds).HeadPose.Pose.Orientation;
        orientation.set(rot.x, -rot.y, rot.z, -rot.w);
        return orientation;
    }

    public static Vector3f getPosition() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(Hmd.getTimeInSeconds() + predictValue).HeadPose.Pose.Position;
        position.set(pos.x, pos.y, pos.z);
        return position;
    }

    public static Vector3f getAngularAcceleration() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(Hmd.getTimeInSeconds() + predictValue).RawSensorData.Gyro;
        angAcc.set(pos.x, pos.y, pos.z);
        return angAcc;
    }

    public static Vector3f getPositionalAcceleration() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(Hmd.getTimeInSeconds() + predictValue).RawSensorData.Magnetometer;
        posAcc.set(pos.x, pos.y, pos.z);
        return posAcc;
    }
    
    public static Vector3f getAccelerometer() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(Hmd.getTimeInSeconds() + predictValue).RawSensorData.Accelerometer;
        angAcc.set(pos.x, pos.y, pos.z);
        return angAcc;
    }

    public static void reset() {
        if (loadedHmd == null) {
            return;
        }
        loadedHmd.recenterPose();
    }

    public static void predictive(float value) {
        predictValue = value;
    }

    public static EyeRenderDesc getEyeRenderDesc(int eyeIndex) {
        return eyeRenderDesc[eyeIndex];
    }

    public static FrameTiming startFrameTiming() {
        frameTiming = loadedHmd.beginFrameTiming(0);
        return frameTiming;
    }

    public static void endFrameTiming() {
        loadedHmd.endFrameTiming();
    }
}
