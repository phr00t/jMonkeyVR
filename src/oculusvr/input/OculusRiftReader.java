/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.input;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.system.JmeSystem;
import com.jme3.system.Natives;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rickard
 */
public class OculusRiftReader {
    
    private static HMDInfo info = new HMDInfo();
    
    private static float roll;
    private static float pitch;
    private static float yaw;
    private static float acc_x;
    private static float acc_y;
    private static float acc_z;
    private static float rot_x;
    private static float rot_y;
    private static float rot_z;
    private static float rot_w;
    
    private static Quaternion rotation = new Quaternion();
    private static Vector3f position = new Vector3f();
    private static boolean initialized;
//    private static OculusRift oculusRift;
    
    
    static {
        String platform = JmeSystem.getPlatform().name();
        if(platform.startsWith("Win")){
            try {
                if(platform.endsWith("64")){
                    Natives.extractNativeLib("windows","OculusLib64", false, false);
                } else {
                    Natives.extractNativeLib("windows","OculusLib", false, false);
                }
                
            } catch (IOException ex) {
                System.out.println("failed to extract " + ex);
                Logger.getLogger(OculusRiftReader.class.getName()).log(Level.SEVERE, null, "Could not extract Oculus Rift library" + ex);
            }
            if(System.getProperty("sun.arch.data.model").equals("32")){
                System.loadLibrary("OculusLib");
            } else if (System.getProperty("sun.arch.data.model").equals("64")){
                System.loadLibrary("OculusLib64");
            }
        } else if(platform.startsWith("Linux")){
            try {
                if(platform.endsWith("64")){
                    Natives.extractNativeLib("linux","ovr64", false, false);
                } else {
                    Natives.extractNativeLib("linux","ovr", false, false);
                }
                
            } catch (IOException ex) {
                System.out.println("failed to extract " + ex);
                Logger.getLogger(OculusRiftReader.class.getName()).log(Level.SEVERE, null, "Could not extract Oculus Rift library" + ex);
            }            
            if(System.getProperty("sun.arch.data.model").equals("32")){
                System.loadLibrary("ovr");
            } else if (System.getProperty("sun.arch.data.model").equals("64")){
                System.loadLibrary("ovr64");
            }
        } else {
            try {
                throw new Exception("Sorry, platform not supported yet!");
            } catch (Exception ex) {
                Logger.getLogger(OculusRiftReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public OculusRiftReader() throws Exception{
//        oculusRift = new OculusRift();
        
        
    }
    
    public static void initialize(){
        initialized = OculusRift.initialize();
        if(initialized){
            updateHMDInfo();
            System.out.println(info);
        } else {
            System.out.println("Oculus Rift could not be initialized; faking values.");
            info.createFakeValues();
        }
    }
    
    public static HMDInfo updateHMDInfo(){
        info.HResolution = OculusRift.getHResolution();
        info.VResolution = OculusRift.getVResolution();
        info.HScreenSize = OculusRift.getHScreenSize();
        info.VScreenSize = OculusRift.getVScreenSize();
        info.VScreenCenter = OculusRift.getVScreenCenter();
        info.EyeToScreenDistance = OculusRift.getEyeToScreenDistance();
        info.LensSeparationDistance = OculusRift.getLensSeparationDistance();
        info.InterpupillaryDistance = OculusRift.getInterpupillaryDistance();
        info.DistortionK = OculusRift.getDistortionK();
        info.DesktopX = OculusRift.getDesktopX();
        info.DesktopY = OculusRift.getDesktopY();
        info.DisplayDeviceName = OculusRift.getDisplayDeviceName();
        info.DisplayId = OculusRift.getDisplayId();
        return info;
    }
    
    public static void update(){
        if( OculusRiftReader.isInitialized() ) {
            float[] data = OculusRift.update();
            rot_x = data[0];
            rot_y = data[1];
            rot_z = data[2];
            rot_w = data[3];
            acc_x = data[4];
            acc_y = data[5];
            acc_z = data[6];
            rotation.set(rot_x, -rot_y, rot_z, -rot_w);
            // TODO: pick up position tracking info here
        }
    }

    public static HMDInfo getHMDInfo() {
        return info;
    }

    public static float getRoll() {
        return roll;
    }

    public static float getPitch() {
        return pitch;
    }
    
    public static float getX() {
        return acc_x;
    }

    public static float getYaw() {
        return yaw;
    }

    public static float getY() {
        return acc_y;
    }

    public static float getZ() {
        return acc_z;
    }
    
    /**
     * Returns the last received orientation data from the Oculus Rift
     * @return 
     */
    public static Quaternion getLocalOrientation(){
        return rotation;
    }
    
    /**
     * Returns the last received position data from the Oculus Rift
     * @return 
     */
    public static Vector3f getPositionalTracking() {
        return position;
    }    
    
    public static void main(String[] args){
        try {
            OculusRiftReader.initialize();
            System.out.println(OculusRiftReader.initialized);
            OculusRiftReader.update();
            OculusRiftReader.destroy();
        } catch (Exception ex) {
            Logger.getLogger(OculusRiftReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void destroy(){
        Logger.getLogger(OculusRiftReader.class.getName()).log(Level.INFO, "Cleaning up");
        info = null;
        OculusRift.destroy();
//        oculusRift = null;
    }
    
    public static boolean isInitialized(){
        return initialized;
    }
    
    /**
     * Returns orientation data directly from the Oculus Rift
     * @return a float array containing Quaternion data(x, y, z, w)
     */
    public static float[] getOrientation(){
        return OculusRift.getOrientation();
    }
    
    /**
     * Returns acceleration data directly from the Oculus Rft
     * @return a float array containing Vector3f data(x, y, z)
     */
    public static float[] getAcceleration(){
        return OculusRift.getAcceleration();
    }
    
    public static void reset(){
        OculusRift.reset();
    }
    public static void predictive(float value, boolean on){
        OculusRift.predictive(value, on);
    }
    
    public static float[] latencyTestColor(){
        return OculusRift.latencyTestColor();
    }
    
    public static String latencyTestResult(){
        return OculusRift.latencyTestResult();
    }
}
