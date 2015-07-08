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
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import jmevr.input.OpenVR;
import jmevr.input.VRHMD;
import jmevr.input.VRInput;
import jmevr.post.PreNormalCaching;
import jmevr.state.OpenVRCamControl;
import jmevr.util.VRGuiNode;

/**
 *
 * @author reden
 */
public class VRApplication extends SimpleApplication{

    private static OpenVR VRhardware;    
    private static OpenVRCamControl VRappstate;
    private static VRGuiNode primaryGuiNode;
    private static Spatial observer;
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
    
    @Override
    public void simpleRender(RenderManager renderManager) {
        super.simpleRender(renderManager);
        PreNormalCaching.resetCache();
    }

    public VRApplication() {
        super();
        guiNode = new VRGuiNode();   
        primaryGuiNode = (VRGuiNode)guiNode;
        
        // we are going to use OpenVR now, not the Oculus Rift
        // OpenVR does support the Rift
        VRhardware = new OpenVR();
        VRhardware.initialize();
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
    
    public static VRHMD getVRHardware() {
        return VRhardware;
    }
    
    private static void initVRinput() {
        // try and detect any VR input controllers
        // check for Vive controllers, add as needed etc.
    }
    
    public static VRGuiNode getVRGuiNode(){
        return primaryGuiNode;
    }
    
    public static Spatial getObserver() {
        return observer;
    }

    public static void setObserver(Spatial observer) {
        VRApplication.observer = observer;
    }
    
    @Override
    public void simpleInitApp() {
        // run this function before OVRAppState gets initialized to force
        // maximum FOV rendering
        if( VRhardware.isInitialized() ) {
            if( VRhardware instanceof OpenVR ) {
                ((OpenVR)VRhardware).initOpenVRCompositor();
            }
            // TODO: implement flipeyes?
            VRappstate = new OpenVRCamControl(this);
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
        VRhardware.destroy();
        for(int i=0;i<VRinput.size();i++) {
            VRinput.get(i).destroy();
        }
    }
    
    public void reset(){
        VRhardware.reset();
        for(int i=0;i<VRinput.size();i++) {
            VRinput.get(i).reset();
        }
    }
}