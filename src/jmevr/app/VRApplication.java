/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.app;

import com.jme3.app.Application;
import com.jme3.app.LostFocusBehavior;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeSystem;
import com.jme3.system.Platform;
import java.util.ArrayList;
import jmevr.input.OculusRift;
import jmevr.input.VRHMD;
import jmevr.input.VRInput;
import jmevr.state.VRAppState;
import jmevr.util.VRGuiNode;
import jmevr.util.OculusRiftUtil;

/**
 *
 * @author reden
 */
public class VRApplication extends SimpleApplication{

    private static VRHMD VRhardware;    
    private static VRAppState VRappstate;
    private static final ArrayList<VRInput> VRinput = new ArrayList<>();
    
    protected boolean useFOVMax, flipEyes, disable_vignette;
    private final String TOGGLE_LOW_PERSISTENCE = "ToggleLowPersistence";
    private final String RESET_HMD = "ResetHMD";
    
    private final DismissWarningListener oculusWarningListener = new DismissWarningListener();
    
    private class DismissWarningListener implements RawInputListener {

        @Override
        public void beginInput() {
        }

        @Override
        public void endInput() {
        }

        @Override
        public void onJoyAxisEvent(JoyAxisEvent evt) {
        }

        @Override
        public void onJoyButtonEvent(JoyButtonEvent evt) {
            dismissWarning();
        }

        @Override
        public void onMouseMotionEvent(MouseMotionEvent evt) {
        }

        @Override
        public void onMouseButtonEvent(MouseButtonEvent evt) {
            dismissWarning();
        }

        @Override
        public void onKeyEvent(KeyInputEvent evt) {
            dismissWarning();
        }

        @Override
        public void onTouchEvent(TouchEvent evt) {
            dismissWarning();
        }
    }
    
    private class OculusListener implements ActionListener{

        public void onAction(String name, boolean isPressed, float tpf) {
            if(name.equals(TOGGLE_LOW_PERSISTENCE) && !isPressed && VRhardware instanceof OculusRift ){
                ((OculusRift)VRhardware).toggleLowPersistence();
            } else if (name.equals(RESET_HMD) && !isPressed){
                reset();
            }
        }
    }
    

    public VRApplication() {
        super();
        guiNode = new VRGuiNode();       
        
        // have to detect & pick which hardware we are going to use
        // for now, just pick the Oculus Rift for obvious reasons :)
        VRhardware = new OculusRift();
        
        // if we are linux 32bit, don't try and init Rift, since no native exists
        if( JmeSystem.getPlatform() != Platform.Linux32 ) {
            VRhardware.initialize();
        }
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
    
    public static VRAppState getVRAppState() {
        return VRappstate;
    }
    
    public static VRHMD getVRHardware() {
        return VRhardware;
    }
    
    private static void initVRinput() {
        // try and detect any VR input controllers
        // check for Vive controllers, add as needed etc.
    }
    
    @Override
    public void simpleInitApp() {
        // run this function before OVRAppState gets initialized to force
        // maximum FOV rendering
        if( VRhardware.isInitialized() ) {
            if( VRhardware instanceof OculusRift ) {
                OculusRiftUtil.useMaxEyeFov(useFOVMax);
                OculusRiftUtil.disableVignette(disable_vignette);
            }
            VRappstate = new VRAppState((VRGuiNode)guiNode, flipEyes);
            VRappstate.getGuiNode().setPositioningMode(VRGuiNode.POSITIONING_MODE.AUTO);
            inputManager.addRawInputListener(oculusWarningListener);
            inputManager.addListener(new OculusListener(), new String[]{TOGGLE_LOW_PERSISTENCE, RESET_HMD});
            inputManager.addMapping(TOGGLE_LOW_PERSISTENCE, new KeyTrigger(KeyInput.KEY_F10));
            inputManager.addMapping(RESET_HMD, new KeyTrigger(KeyInput.KEY_F9));
            stateManager.attach(VRappstate);
            initVRinput();
            setLostFocusBehavior(LostFocusBehavior.Disabled);
        }
    }

    @Override
    public void start() {
        // set some default settings in-case
        // settings dialog is not shown
        boolean loadSettings = false;
        if (settings == null) {
            setSettings(new AppSettings(true));
            loadSettings = true;
        }

        // show settings dialog
        if (showSettings) {
            if (!JmeSystem.showSettingsDialog(settings, loadSettings)) {
                return;
            }
        }
        //swap buffers setting
        settings.setSwapBuffers(!VRhardware.isInitialized());
        //re-setting settings they can have been merged from the registry.
        setSettings(settings);
        start(JmeContext.Type.Display, false);
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        VRhardware.destroy();
        for(int i=0;i<VRinput.size();i++) {
            VRinput.get(i).destroy();
        }
    }
    
    public void dismissWarning(){
        if( VRhardware instanceof OculusRift ) {
            ((OculusRift)VRhardware).getHmd().dismissHSWDisplay();
            reset(); // reset position when the warning gets removed
            inputManager.removeRawInputListener(oculusWarningListener);
        }
    }
    
    public void reset(){
        VRhardware.reset();
        for(int i=0;i<VRinput.size();i++) {
            VRinput.get(i).reset();
        }
    }
}