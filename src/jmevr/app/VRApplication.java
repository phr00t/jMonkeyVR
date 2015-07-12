/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.app;

import com.jme3.app.LostFocusBehavior;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture2D;
import com.sun.jna.Pointer;
import java.util.ArrayList;
import java.util.Locale;
import jmevr.input.OpenVR;
import jmevr.input.VRHMD;
import jmevr.input.VRInput;
import jmevr.post.PreNormalCaching;
import jmevr.state.OpenVRViewManager;
import jmevr.util.VRGuiNode;
import jopenvr.JOpenVRLibrary;

/**
 *
 * @author reden
 */
public class VRApplication extends SimpleApplication{

    private static OpenVR VRhardware;    
    private static OpenVRViewManager VRappstate;
    private static VRApplication mainApp;
    private static Spatial observer;
    private static boolean VRSupportedOS;
    private static final ArrayList<VRInput> VRinput = new ArrayList<>();
    
    protected boolean useFOVMax, flipEyes, disable_vignette;
    private final String RESET_HMD = "ResetHMD";
        
    private class VRListener implements ActionListener{

        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(RESET_HMD) && !isPressed){
                reset();
            }
        }
    }
    
    public boolean isOSVRSupported() {
        return VRSupportedOS;
    }
    
    @Override
    public void simpleRender(RenderManager renderManager) {
        super.simpleRender(renderManager);
        PreNormalCaching.resetCache();
    }

    public VRApplication() {
        super();
        guiNode = new VRGuiNode();   
        mainApp = this;
        
        // we are going to use OpenVR now, not the Oculus Rift
        // OpenVR does support the Rift
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        VRSupportedOS = true; //!OS.contains("nux"); //for the moment, linux/unix is supported enough to run
        
        VRhardware = new OpenVR();
        if( VRSupportedOS ) VRhardware.initialize();
    }
    
    public void preconfigureVRApp(boolean disable_vignette, boolean force_max_fov, boolean flip_eyes, boolean force_vr) {        
        useFOVMax = force_max_fov;
        flipEyes = flip_eyes;
        this.disable_vignette = disable_vignette;
        
        if( force_vr ) {
            // this will make it work even if an HMD isn't present
            VRhardware.forceInitializeSuccess();
        }               
    }
    
    public static ArrayList<VRInput> getVRinputs() {
        return VRinput;
    }
    
    public static boolean isInVR() {
        return VRSupportedOS && VRhardware != null && VRhardware.isInitialized();
    }
    
    public static VRHMD getVRHardware() {
        return VRhardware;
    }
    
    private static void initVRinput() {
        // try and detect any VR input controllers
        // check for Vive controllers, add as needed etc.
    }
    
    public static VRGuiNode getVRGuiNode(){
        return (VRGuiNode)mainApp.guiNode;
    }
    
    public static Spatial getObserver() {
        return observer;
    }

    public static void setObserver(Spatial observer) {
        VRApplication.observer = observer;
    }
    
    public static Quaternion getFinalOberserverRotation() {
        if( VRappstate == null ) {
            if( VRApplication.observer == null ) {
                return mainApp.getCamera().getRotation();
            } else return VRApplication.observer.getWorldRotation();
        }
        return VRappstate.getFinalRotation();
    }
    
    public static Vector3f getFinalOberserverPosition() {
        if( VRappstate == null ) {
            if( VRApplication.observer == null ) {
                return mainApp.getCamera().getLocation();
            } else return VRApplication.observer.getWorldTranslation();
        }
        return VRappstate.getFinalPosition();
    }
    
    public static ViewPort getLeftViewPort() {
        if( VRappstate == null ) return mainApp.getViewPort();
        return VRappstate.getViewPortLeft();
    }
    
    public static ViewPort getRightViewPort() {
        if( VRappstate == null ) return mainApp.getViewPort();
        return VRappstate.getViewPortRight();
    }
    
    @Override
    public void simpleInitApp() {
        // run this function before OVRAppState gets initialized to force
        // maximum FOV rendering
        if( VRSupportedOS && VRhardware.isInitialized() ) {
            if( VRhardware instanceof OpenVR ) {
                ((OpenVR)VRhardware).initOpenVRCompositor();
            }
            // TODO: implement flipeyes?
            VRappstate = new OpenVRViewManager(this);
            stateManager.attach(VRappstate);
            inputManager.addListener(new VRListener(), new String[]{RESET_HMD});
            inputManager.addMapping(RESET_HMD, new KeyTrigger(KeyInput.KEY_F9));
            initVRinput();
            setLostFocusBehavior(LostFocusBehavior.Disabled);
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        if( VRSupportedOS ) {
            VRhardware.destroy();
            for(int i=0;i<VRinput.size();i++) {
                VRinput.get(i).destroy();
            }
        }
    }
    
    public void reset(){
        if( VRSupportedOS == false ) return;
        VRhardware.reset();
        for(int i=0;i<VRinput.size();i++) {
            VRinput.get(i).reset();
        }
    }
}