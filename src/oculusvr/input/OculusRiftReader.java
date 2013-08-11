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
    
    private HMDInfo info = new HMDInfo();
    
    private float roll;
    private float pitch;
    private float yaw;
    private float x;
    private float y;
    private float z;
    private boolean initialized;
    private static OculusRift oculusRift;
    
    public OculusRiftReader() throws Exception{
        
        oculusRift = new OculusRift();
        initialized = oculusRift.initialize();
        if(initialized){
//            info = new HMDInfo();
            updateHMDInfo();
            System.out.println(info);
        } else {
            throw new Exception("Oculus Rift could not be initialized");
        }
    }
    
    
    public HMDInfo updateHMDInfo(){
        info.HResolution = oculusRift.getHResolution();
        info.VResolution = oculusRift.getVResolution();
        info.HScreenSize = oculusRift.getHScreenSize();
        info.VScreenSize = oculusRift.getVScreenSize();
        info.VScreenCenter = oculusRift.getVScreenCenter();
        info.EyeToScreenDistance = oculusRift.getEyeToScreenDistance();
        info.LensSeparationDistance = oculusRift.getLensSeparationDistance();
        info.InterpupillaryDistance = oculusRift.getInterpupillaryDistance();
        info.DistortionK = oculusRift.getDistortionK();
        info.DesktopX = oculusRift.getDesktopX();
        info.DesktopY = oculusRift.getDesktopY();
        info.DisplayDeviceName = oculusRift.getDisplayDeviceName();
        info.DisplayId = oculusRift.getDisplayId();
        return info;
    }
    
    public void update(){
        float[] data = oculusRift.update();
        //System.out.println(data[0] + ", " + data[1] + ", " + data[2]);
        roll = -data[0];
        pitch = -data[1];
        yaw = data[2];
        x = data[3];
        y = data[4];
        z = data[5];
    }

    public HMDInfo getHMDInfo() {
        return info;
    }

    public float getRoll() {
        return roll;
    }

    public float getPitch() {
        return pitch;
    }
    
    public float getX() {
        return x;
    }

    public float getYaw() {
        return yaw;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
    
    public float[] getRotation(){
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
    
    public void destroy(){
        Logger.getLogger(OculusRiftReader.class.getName()).log(Level.INFO, "Cleaning up");
        info = null;
        oculusRift.destroy();
        oculusRift = null;
    }
    
    public boolean isInitialized(){
        return initialized;
    }
}
