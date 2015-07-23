
// TODO: check if SteamVR is even installed!

package jmevr.app;

import com.jme3.app.LostFocusBehavior;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeSystem;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;
import jmevr.input.OpenVR;
import jmevr.input.VRInput;
import jmevr.post.PreNormalCaching;
import jmevr.state.OpenVRViewManager;
import jmevr.util.VRGuiNode;

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
    
    private static JFrame VRwindow;
    
    private static boolean useCompositor = true, compositorOS, useJFrame = true;
    private final String RESET_HMD = "ResetHMD", MIRRORING = "Mirror";
        
    static {
        // make sure we are using opengl acceleration
        System.setProperty("sun.java2d.transaccel", "True");
        System.setProperty("sun.java2d.opengl", "True");
    }
    
    private class VRListener implements ActionListener{

        public void onAction(String name, boolean isPressed, float tpf) {
            if( isPressed ) {
                if (name.equals(RESET_HMD)){
                    reset();
                } else if( name.equals(MIRRORING) ) {
                    VRApplication.setMirroring(!VRApplication.getMirroring());
                }
            }
        }
    }
    
    /*
        using "easy extended" mode
    */
    public static boolean usingJFrame() {
        return VRwindow != null && isInVR() && useJFrame;
    }
    
    /*
        did we disable the SteamVR Compositor?
    */
    public static boolean compositorAllowed() {
        return useCompositor && compositorOS;
    }
    
    /*
        does the current OS support VR?
    */
    public static boolean isOSVRSupported() {
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
        compositorOS = OS.contains("indows");
        
        VRhardware = new OpenVR();
        if( VRSupportedOS ) VRhardware.initialize();
    }
    
    /*
        we do NOT want to get & modify the distortion scene camera, so
        return the left viewport camera instead if we are in VR mode
    */
    @Override
    public Camera getCamera() {
        if( isInVR() && VRappstate != null && VRappstate.getViewPortLeft() != null ) {
            return VRappstate.getViewPortLeft().getCamera();
        }
        return super.getCamera();
    }
    
    public Camera getRightCamera() {
        if( isInVR() && VRappstate != null && VRappstate.getViewPortRight() != null ) {
            return VRappstate.getViewPortRight().getCamera();
        }
        return super.getCamera();        
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

        if( isInVR() && !compositorAllowed() && useJFrame ) {
            // setup experimental JFrame on external device
            // first, find the VR device
            GraphicsDevice VRdev = null;
            GraphicsDevice[] devs = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            GraphicsDevice defDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            // pick the display that isn't the default one
            for(GraphicsDevice gd : devs) {
                if( gd != defDev ) {
                    VRdev = gd;
                    break;
                }
            }
            // did we get the VR device?
            if( VRdev != null ) {
                try {   
                    java.awt.DisplayMode useDM = null;
                    int max = 0;
                    for(java.awt.DisplayMode dm : VRdev.getDisplayModes()) {
                        int check = dm.getHeight() + dm.getWidth() + dm.getRefreshRate();
                        if( check > max ) {
                            max = check;
                            useDM = dm;
                        }
                    }
                    // create a window for the VR device
                    VRwindow = new JFrame(VRdev.getDefaultConfiguration());
                    VRwindow.setSize(useDM.getWidth(), useDM.getHeight());
                    VRwindow.setUndecorated(true);
                    VRwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    settings.setWidth(useDM.getWidth());
                    settings.setHeight(useDM.getHeight());
                    settings.setBitsPerPixel(useDM.getBitDepth());
                    settings.setFrequency(useDM.getRefreshRate());
                    settings.setSwapBuffers(true);
                    settings.setVSync(true);
                    setSettings(settings);
                    VRdev.setFullScreenWindow(VRwindow);
                    // make sure we are in the right display mode
                    if( VRdev.getDisplayMode().equals(useDM) == false ) {
                        VRdev.setDisplayMode(useDM);
                    }
                    createCanvas();
                    JmeCanvasContext jmeCanvas = (JmeCanvasContext)getContext();
                    jmeCanvas.setSystemListener(this);
                    jmeCanvas.getCanvas().setPreferredSize(VRwindow.getSize());
                    jmeCanvas.getCanvas().setIgnoreRepaint(true);
                    VRwindow.add(jmeCanvas.getCanvas());
                    VRwindow.pack();
                    VRwindow.setVisible(true);
                    startCanvas();
                    return;
                } catch(Exception e) { 
                    useJFrame = false;
                }
            } else {
                // disable JFrame mode, start normally
                useJFrame = false;
            }
        }
        
        if (showSettings) {
            if (!JmeSystem.showSettingsDialog(settings, loadSettings)) {
                return;
            }
        }
        
        //re-setting settings they can have been merged from the registry.
        settings.setSwapBuffers(true); // make sure this is set to true
        setSettings(settings);
        start(JmeContext.Type.Display, false);
    }    
    
    /*
        use the SteamVR Compositor? (defaults to true)
        use the JFrame "easy extended" mode, if SteamVR Compositor isn't used? (defaults to true)
        force VR mode, even if a device isn't detected? Good for testing (defaults to false)
    */
    public void preconfigureVRApp(boolean useCompositor, boolean useJFrame, boolean forceVR) {        
        VRApplication.useCompositor = useCompositor;
        VRApplication.useJFrame = useJFrame;
        
        if( forceVR ) {
            // this will make it work even if an HMD isn't present
            VRhardware.forceInitializeSuccess();
        }               
    }
    
    public static ArrayList<VRInput> getVRinputs() {
        return VRinput;
    }
    
    /*
        quick check if VR mode is enabled
    */
    public static boolean isInVR() {
        return VRSupportedOS && VRhardware != null && VRhardware.isInitialized();
    }
    
    /*
        move filters out of the main scene & into the eye's
        this removes filters from the main scene!
    */
    public static void moveScreenProcessingToVR() {
        if( isInVR() ) {
            VRappstate.moveScreenProcessingToEyes();
        }
    }
    
    /*
        access to the OpenVR stuff
    */
    public static OpenVR getVRHardware() {
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

    /*
        important to set this! the VR headset will be linked to it
    */
    public static void setObserver(Spatial observer) {
        VRApplication.observer = observer;
    }
    
    /*
        where is the headset pointing, after all rotations are combined?
        depends on observer rotation, if any
    */
    public static Quaternion getFinalOberserverRotation() {
        if( VRappstate == null ) {
            if( VRApplication.observer == null ) {
                return mainApp.getCamera().getRotation();
            } else return VRApplication.observer.getWorldRotation();
        }
        return VRappstate.getFinalRotation();
    }
    
    /*
        quick way to adjust the height of the VR view
    */
    public static void setVRHeightAdjustment(float amount) {
        if( VRappstate != null ) VRappstate.setHeightAdjustment(amount);
    }
    
    public static float getVRHeightAdjustment() {
        if( VRappstate != null ) return VRappstate.getHeightAdjustment();
        return 0f;
    }
       
    /*
        where is the headset, after all positional tracking is complete?
        includes observer position, if any
    */
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
    
    public static VRApplication getMainVRApp() {
        return mainApp;
    }
    
    /*
        mirror output to main screen (only works with SteamVR Compositor)
    */
    public static void setMirroring(boolean set) {
        if( VRappstate == null ) return;
        VRappstate.setMirroring(set);
    }
    
    public static boolean getMirroring() {
        if( VRappstate == null ) return false;
        return VRappstate.getMirroring();
    }
    
    @Override
    public void simpleInitApp() {
        if( isInVR() ) {
            if( compositorAllowed() == false || OpenVR.getVRSystemInstance() == null ) {
                System.out.println("Skipping SteamVR compositor!");
            } else {
                VRhardware.initOpenVRCompositor();
            }
            VRappstate = new OpenVRViewManager(this);
            stateManager.attach(VRappstate);
            inputManager.addListener(new VRListener(), new String[]{RESET_HMD, MIRRORING});
            inputManager.addMapping(RESET_HMD, new KeyTrigger(KeyInput.KEY_F9));
            inputManager.addMapping(MIRRORING, new KeyTrigger(KeyInput.KEY_F10));
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
    
    /*
        reset headset pose. defaults to F9 key!
    */
    public void reset(){
        if( VRSupportedOS == false ) return;
        VRhardware.reset();
        for(int i=0;i<VRinput.size();i++) {
            VRinput.get(i).reset();
        }
    }
}