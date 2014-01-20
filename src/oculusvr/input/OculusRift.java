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
    public static native float[] getOrientation();
    public static native float[] getAcceleration();
    public static native void reset();
    public static native void predictive(float value, boolean on);
    public static native float[] latencyTestColor();
    public static native String latencyTestResult();

}
