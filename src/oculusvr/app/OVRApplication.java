/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.app;

import com.jme3.app.SimpleApplication;
import static com.jme3.app.SimpleApplication.INPUT_MAPPING_EXIT;
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
import com.jme3.system.JmeContext;
import com.oculusvr.capi.OvrLibrary;
import oculusvr.input.OculusRift;
import oculusvr.state.OVRAppState;
import oculusvr.util.OculusGuiNode;
import oculusvr.util.OculusRiftUtil;

/**
 *
 * @author reden
 */
public class OVRApplication extends SimpleApplication{

    protected boolean useFOVMax, flipEyes;
    protected OVRAppState ovrAppState;
    
    private DismissWarningListener oculusListener = new DismissWarningListener();
    
    private boolean directMode;
    private boolean autoFlushFrames = true;
    public static String TOGGLE_DIRECT_MODE = "F12";
    public static String TOGGLE_AUTO_FLUSH = "F11";
    
    private class DismissWarningListener implements RawInputListener {

        public void beginInput() {
        }

        public void endInput() {
        }

        public void onJoyAxisEvent(JoyAxisEvent evt) {
        }

        public void onJoyButtonEvent(JoyButtonEvent evt) {
            dismissWarning();
        }

        public void onMouseMotionEvent(MouseMotionEvent evt) {
        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {
            dismissWarning();
        }

        public void onKeyEvent(KeyInputEvent evt) {
            dismissWarning();
        }

        public void onTouchEvent(TouchEvent evt) {
            dismissWarning();
        }
    }
    
    private class OculusListener implements ActionListener{

        public void onAction(String name, boolean isPressed, float tpf) {
            if(name.equals(TOGGLE_DIRECT_MODE) && !isPressed){
//                directMode = !directMode;
                
            } else if(name.equals(TOGGLE_AUTO_FLUSH) && !isPressed){
                autoFlushFrames = !autoFlushFrames;
                
                context.setAutoFlushFrames(autoFlushFrames);
            }
        }
    }
    

    public OVRApplication() {
        guiNode = new OculusGuiNode();        
        OculusRift.initialize();
    }
    
    public void configureOVRApp(boolean fovMax, boolean flipEyes, boolean forceOculus) {
        
        useFOVMax = fovMax;
        this.flipEyes = flipEyes;
        
        if( forceOculus ) {
            // this will make it work even if an HMD isn't present
            OculusRift.forceInitializeSuccess();
        }               
    }
    
    public OVRAppState getOVRAppState() {
        return ovrAppState;
    }
    
    @Override
    public void simpleInitApp() {
        // run this function before OVRAppState gets initialized to force
        // maximum FOV rendering
        if( OculusRift.isInitialized() ) {
            OculusRiftUtil.useMaxEyeFov(useFOVMax);

            ovrAppState = new OVRAppState((OculusGuiNode)guiNode, flipEyes);
            ovrAppState.getGuiNode().setPositioningMode(OculusGuiNode.POSITIONING_MODE.AUTO);
            inputManager.addRawInputListener(oculusListener);
            inputManager.addListener(new OculusListener(), new String[]{TOGGLE_AUTO_FLUSH, TOGGLE_DIRECT_MODE});
            inputManager.addMapping(TOGGLE_AUTO_FLUSH, new KeyTrigger(KeyInput.KEY_F11));
            inputManager.addMapping(TOGGLE_DIRECT_MODE, new KeyTrigger(KeyInput.KEY_F12));
            stateManager.attach(ovrAppState);
        }
        
        
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        OculusRift.destroy();
    }
    
    public void dismissWarning(){
        OculusRift.loadedHmd.dismissHSWDisplay();
        inputManager.removeRawInputListener(oculusListener);
    }

}