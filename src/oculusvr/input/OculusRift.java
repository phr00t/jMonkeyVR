/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.input;

/**
 *
 * @author Rickard
 */
public class OculusRift {
    
    
    static {
        if(System.getProperty("sun.arch.data.model").equals("32")){
            System.loadLibrary("OculusLib");
        } else if (System.getProperty("sun.arch.data.model").equals("64")){
            System.loadLibrary("OculusLib64");
        }
    
    }
    
    public native boolean initialize();
    
    /**
     * 
     * @return array of [roll, pitch, yaw, acc x, acc y, acc z]
     */
    public native float[] update();
    
    // HMDInfo
    public native int getHResolution();
    public native int getVResolution();
    public native float getHScreenSize();
    public native float getVScreenSize();
    public native float getVScreenCenter();
    public native float getEyeToScreenDistance();
    public native float getLensSeparationDistance();
    public native float getInterpupillaryDistance();
    public native float[] getDistortionK();
    public native int getDesktopX();
    public native int getDesktopY();
    public native String getDisplayDeviceName();
    public native long getDisplayId();
    public native void destroy();
}
