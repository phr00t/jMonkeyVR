/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.input;

import com.jme3.app.state.StereoCamAppState;
import com.jme3.system.JmeSystem;
import com.jme3.system.Natives;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rickard
 */
public class OculusRift {
    
    
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
                Logger.getLogger(StereoCamAppState.class.getName()).log(Level.SEVERE, null, "Could not extract Oculus Rift library" + ex);
            }
        } else {
            try {
                throw new Exception("Sorry, platform not supported yet!");
            } catch (Exception ex) {
                Logger.getLogger(StereoCamAppState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(System.getProperty("sun.arch.data.model").equals("32")){
            System.loadLibrary("OculusLib");
        } else if (System.getProperty("sun.arch.data.model").equals("64")){
            System.loadLibrary("OculusLib64");
        }
    
    }
   
    public static native boolean initialize();
    
    /**
     * 
     * @return array of [roll, pitch, yaw, acc x, acc y, acc z]
     */
    public static native float[] update();
    
    // HMDInfo
    public static native int getHResolution();
    public static native int getVResolution();
    public static native float getHScreenSize();
    public static native float getVScreenSize();
    public static native float getVScreenCenter();
    public static native float getEyeToScreenDistance();
    public static native float getLensSeparationDistance();
    public static native float getInterpupillaryDistance();
    public static native float[] getDistortionK();
    public static native int getDesktopX();
    public static native int getDesktopY();
    public static native String getDisplayDeviceName();
    public static native long getDisplayId();
    public static native void destroy();
}
