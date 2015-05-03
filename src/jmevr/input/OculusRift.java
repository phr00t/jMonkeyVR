/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.input;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.system.JmeSystem;
import com.jme3.system.Platform;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.OvrLibrary;
import com.oculusvr.capi.OvrQuaternionf;
import com.oculusvr.capi.OvrVector3f;
import com.oculusvr.capi.Posef;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmevr.util.OculusRiftUtil;

/**
 *
 * @author Rickard
 */
public class OculusRift implements VRHMD {

    private final Quaternion orientation = new Quaternion(), orientationi = new Quaternion();
    private final Vector3f position = new Vector3f(), positioni = new Vector3f(), posAcc = new Vector3f(), angAcc = new Vector3f();
    
    private Hmd loadedHmd;
    private HMDInfo info = new HMDInfo();
    private boolean initHMDSuccess;
    private EyeRenderDesc[] eyeRenderDesc;
    private boolean renderInit;
    
    public int frameIndex;
    private final OvrVector3f eyeOffsets[] = (OvrVector3f[]) new OvrVector3f().toArray(2);
    private Posef[] eyePoses;
    
    public Hmd getHmd() {
        return loadedHmd;
    }
    
    // returns true if it was successfully loaded
    public boolean initialize() {
        if( JmeSystem.getPlatform() != Platform.Linux32 ) {
            Hmd.initialize();
            loadedHmd = Hmd.create(0);
        } else {
            loadedHmd = null;
        }
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
    public void forceInitializeSuccess() {
        initHMDSuccess = true;
    }
    
    public void initRendering(int width, int height, int samples) {
        if( eyeRenderDesc == null )
            eyeRenderDesc = OculusRiftUtil.configureRendering(loadedHmd, width, height, samples); // we will use debug hmd at least here   
        for(int eyeIndex  = 0; eyeIndex < 2; eyeIndex++){
            eyeOffsets[eyeIndex].x = eyeRenderDesc[eyeIndex].HmdToEyeViewOffset.x;
            eyeOffsets[eyeIndex].y = eyeRenderDesc[eyeIndex].HmdToEyeViewOffset.y;
            eyeOffsets[eyeIndex].z = eyeRenderDesc[eyeIndex].HmdToEyeViewOffset.z;
        }
        
        renderInit = true;
    }

    public HMDInfo updateHMDInfo() {
        info.HResolution = getHResolution();
        info.VResolution = getVResolution();
        info.InterpupillaryDistance = getInterpupillaryDistance();
        info.DesktopX = getDesktopX();
        info.DesktopY = getDesktopY();
        return info;
    }

    public HMDInfo getHMDInfo() {
        return info;
    }

    public void main(String[] args) {
        try {
            initialize();
            System.out.println(isInitialized());
            destroy();
        } catch (Exception ex) {
            Logger.getLogger(OculusRift.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void destroy() {
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

    public boolean isInitialized() {
        return initHMDSuccess;
    }

    // HMDInfo
    public int getHResolution() {
        return loadedHmd == null ? 1280 : loadedHmd.Resolution.w;
    }
    
    public float getFOV() {
        return loadedHmd == null ? 130f : (float)Math.atan(loadedHmd.MaxEyeFov[0].DownTan) * 2f * 57.2957795f;
    }

    public int getVResolution() {
        return loadedHmd == null ? 800 : loadedHmd.Resolution.h;
    }
    
    public float getInterpupillaryDistance() {
        return loadedHmd == null ? 0.0624f : loadedHmd.getFloat(OvrLibrary.OVR_KEY_IPD, 0.0624f);
    }

    public int getDesktopX() {
        return loadedHmd == null ? 0 : loadedHmd.WindowsPos.x;
    }

    public int getDesktopY() {
        return loadedHmd == null ? 0 : loadedHmd.WindowsPos.y;
    }

    public Quaternion getOrientation() {
        if (eyePoses == null) {
            return Quaternion.DIRECTION_Z;
        }
        OvrQuaternionf rot = eyePoses[0].Orientation;//loadedHmd.getEyePose(0).Orientation;
        orientation.set(rot.x, -rot.y, rot.z, -rot.w);
        return orientation;
    }

    public Quaternion getInstantOrientation() {
        if (loadedHmd == null) {
            return Quaternion.DIRECTION_Z;
        }
        OvrQuaternionf rot = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).HeadPose.Pose.Orientation;
        orientationi.set(rot.x, -rot.y, rot.z, -rot.w);
        return orientationi;
    }
    
    public Vector3f getPosition() {
        if (eyePoses == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = eyePoses[0].Position;
        position.set(-pos.x, pos.y, -pos.z);
        return position;
    }
    
    public Vector3f getInstantPosition() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).HeadPose.Pose.Position;
        positioni.set(pos.x, pos.y, pos.z);
        return positioni;
    }
    
    public Vector3f getAngularAcceleration() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).RawSensorData.Gyro;
        angAcc.set(pos.x, pos.y, pos.z);
        return angAcc;
    }

    public Vector3f getPositionalAcceleration() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).RawSensorData.Magnetometer;
        posAcc.set(pos.x, pos.y, pos.z);
        return posAcc;
    }
    
    public Vector3f getAccelerometer() {
        if (loadedHmd == null) {
            return Vector3f.ZERO;
        }
        OvrVector3f pos = loadedHmd.getSensorState(loadedHmd.getFrameTiming(0).ScanoutMidpointSeconds).RawSensorData.Accelerometer;
        angAcc.set(pos.x, pos.y, pos.z);
        return angAcc;
    }

    public void reset() {
        if (loadedHmd == null) {
            return;
        }
        loadedHmd.recenterPose();
    }

    public EyeRenderDesc getEyeRenderDesc(int eyeIndex) {
        return eyeRenderDesc[eyeIndex];
    }
    
    public float getEyeHeight(){
        float eyeHeight = 0;
        
        eyeHeight = loadedHmd.getFloat(OvrLibrary.OVR_KEY_EYE_HEIGHT, eyeHeight);
        return eyeHeight;
    }
    
    public void beginFrameTiming(){
        loadedHmd.beginFrameTiming(0);
    }
    
    public void endFrameTiming() {
        loadedHmd.endFrameTiming();
    }
    
    public boolean getRenderInit(){
        return renderInit;
    }

    public Posef[] getEyePoses() {
        return eyePoses;
    }

    public void setEyePoses(Posef[] eyePoses) {
        this.eyePoses = eyePoses;
    }

    public OvrVector3f[] getEyeOffsets() {
        return eyeOffsets;
    }
    
    public void toggleLowPersistence(){
        if( loadedHmd == null ) return;
        int caps = loadedHmd.getEnabledCaps();
        if (0 != (caps & OvrLibrary.ovrHmdCaps.ovrHmdCap_LowPersistence)) {
            System.out.println("LOW PERSISTENCE OFF");
            loadedHmd.setEnabledCaps(caps & ~OvrLibrary.ovrHmdCaps.ovrHmdCap_LowPersistence);
        } else {
            System.out.println("LOW PERSISTENCE ON");
            loadedHmd.setEnabledCaps(caps | OvrLibrary.ovrHmdCaps.ovrHmdCap_LowPersistence);
        }
    }

    @Override
    public String getName() {
        return "Oculus Rift";
    }
}
