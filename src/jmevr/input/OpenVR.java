/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.input;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import openvr_api.IVRSystem;
import openvr_api.Openvr_apiLibrary;
import org.bridj.Pointer;

/**
 *
 * @author phr00t
 */
public class OpenVR implements VRHMD {

    private static final Openvr_apiLibrary openvr = new Openvr_apiLibrary();
    private static IVRSystem vrsystem;
    private static boolean forceInitialize = false;
    
    private static Pointer hmdDeviceIndex;
    private static Pointer hmdErrorStore;
    
    @Override
    public String getName() {
        return "OpenVR";
    }

    @Override
    public boolean initialize() {
        hmdErrorStore = Pointer.allocateLong();
        Pointer pvr = openvr.vRInit(hmdErrorStore);
        if( pvr != null ) vrsystem = (IVRSystem)pvr.get();
        if( hmdErrorStore.getLong() != 0 ) {
            String errstr = openvr.vRGetStringForHmdError(hmdErrorStore);
            System.out.println("OpenVR Initialize Result: " + errstr);
            return false;
        } else {
            System.out.println("OpenVR initialized & VR connected.");
            hmdDeviceIndex = Pointer.allocateLong();
            hmdDeviceIndex.setLong(Openvr_apiLibrary.k_unTrackedDeviceIndex_Hmd);
            return true;
        }
    }

    @Override
    public void forceInitializeSuccess() {
        forceInitialize = true;
    }

    @Override
    public void initRendering(int width, int height, int samples) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HMDInfo updateHMDInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HMDInfo getHMDInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        openvr.vRShutdown();
    }

    @Override
    public boolean isInitialized() {
        return forceInitialize || vrsystem != null && openvr.vRIsHmdPresent();
    }

    @Override
    public void reset() {
        if( vrsystem == null ) return;
        vrsystem.resetSeatedZeroPose();
    }

    @Override
    public int getHResolution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getFOV() {
        if( vrsystem == null ) return 130f;
        return vrsystem.getFloatTrackedDeviceProperty(hmdDeviceIndex, Openvr_apiLibrary.TrackedDeviceProperty.Prop_FieldOfViewBottomDegrees_Float, hmdErrorStore);
    }

    @Override
    public int getVResolution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getInterpupillaryDistance() {
        if( vrsystem == null ) return 0.064f;
        return vrsystem.getFloatTrackedDeviceProperty(hmdDeviceIndex, Openvr_apiLibrary.TrackedDeviceProperty.Prop_UserIpdMeters_Float, hmdErrorStore);
    }

    @Override
    public float getEyeHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quaternion getOrientation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getAngularAcceleration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getPositionalAcceleration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getAccelerometer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
