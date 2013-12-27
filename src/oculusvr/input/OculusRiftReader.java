/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.input;

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
    private static float x;
    private static float y;
    private static float z;
    private static boolean initialized;
//    private static OculusRift oculusRift;
    
    
    static {
        
        
    }
    
    public OculusRiftReader() throws Exception{
//        oculusRift = new OculusRift();
        
        
    }
    
    public static void initialize(){
        initialized = OculusRift.initialize();
        if(initialized){
//            info = new HMDInfo();
            updateHMDInfo();
            System.out.println(info);
        } else {
            System.out.println("Oculus Rift could not be initialized");
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
        float[] data = OculusRift.update();
        //System.out.println(data[0] + ", " + data[1] + ", " + data[2]);
        roll = -data[0];
        pitch = -data[1];
        yaw = data[2];
        x = data[3];
        y = data[4];
        z = data[5];
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
        return x;
    }

    public static float getYaw() {
        return yaw;
    }

    public static float getY() {
        return y;
    }

    public static float getZ() {
        return z;
    }
    
    public static float[] getRotation(){
        return new float[]{pitch, yaw, roll};
    }
    
    
    public static void main(String[] args){
        try {
            OculusRiftReader orr = new OculusRiftReader();
            
            System.out.println(orr.initialized);
            orr.update();
//            float[] update = orr.oculusRift.update();
//            for(float f: update){
//                System.out.println(f);
//            }
            orr.destroy();
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
}
