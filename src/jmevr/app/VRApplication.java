package jmevr.app;

import com.jme3.app.Application;
import com.jme3.app.LostFocusBehavior;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeSystem;
import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import jmevr.input.OpenVR;
import jmevr.input.VRInput;
import jmevr.post.PreNormalCaching;
import jmevr.util.OpenVRViewManager;
import jmevr.util.VRGuiManager;
import jmevr.util.VRGuiManager.POSITIONING_MODE;
import static jopenvr.JOpenVRLibrary.VR_IsHmdPresent;

/**
 *
 * @author reden
 */
public abstract class VRApplication extends Application {

    public static enum PRECONFIG_PARAMETER {
        USE_STEAMVR_COMPOSITOR, USE_JFRAME_EXTENDED_BACKUP, USE_CUSTOM_DISTORTION, FORCE_VR_MODE, FLIP_EYES
    }
    
    private static OpenVR VRhardware;    
    private static Camera dummyCam;
    private static OpenVRViewManager VRviewmanager;
    private static VRApplication mainApp;
    private static Spatial observer;
    private static boolean VRSupportedOS, forceVR;
    private static final ArrayList<VRInput> VRinput = new ArrayList<>();
    
    private static JFrame VRwindow;
    
    protected Node guiNode;
    protected Node rootNode;
    
    private float fFar = 1000f, fNear = 1f;
    private int xWin = 1280, yWin = 720;
    
    private static boolean useCompositor = true, compositorOS, useJFrame = true;
    private final String RESET_HMD = "ResetHMD", MIRRORING = "Mirror";
    
    static {
        if( VR_IsHmdPresent() != 0 ) {
            System.setProperty("sun.java2d.opengl", "True");
        }                        
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
    public static JFrame getJFrame() {
        return VRwindow;
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
    
    public void simpleUpdate(float tpf) {   }
    
    public void simpleRender(RenderManager renderManager) {
        PreNormalCaching.resetCache();
    }

    public VRApplication( AppState... initialStates ) {
        this();
        
        if (initialStates != null) {
            for (AppState a : initialStates) {
                if (a != null) {
                    stateManager.attach(a);
                }
            }
        }
    }
    
    public void setFrustrumNearFar(float near, float far) {
        fNear = near;
        fFar = far;
    }
    
    public void setMirrorWindowSize(int width, int height) {
        xWin = width;
        yWin = height;
    }

    public VRApplication() {
        super();
        
        rootNode = new Node("root");
        guiNode = new Node("guiNode");
        guiNode.setQueueBucket(Bucket.Gui);
        guiNode.setCullHint(CullHint.Never);
        dummyCam = new Camera();
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
        if( isInVR() && VRviewmanager != null && VRviewmanager.getCamLeft() != null ) {
            return dummyCam;
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
            // "easy extended" mode
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
                // set properties for VR acceleration
                try {   
                    java.awt.DisplayMode useDM = null;
                    int max = 0;
                    for(java.awt.DisplayMode dm : VRdev.getDisplayModes()) {
                        int check = dm.getHeight() + dm.getWidth() + dm.getRefreshRate() + dm.getBitDepth();
                        if( check > max ) {
                            max = check;
                            useDM = dm;
                        }
                    }
                    // create a window for the VR device
                    VRwindow = new JFrame(VRdev.getDefaultConfiguration());
                    VRwindow.setSize(useDM.getWidth(), useDM.getHeight());
                    VRwindow.setUndecorated(true);
                    VRwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                    // make a blank cursor to hide it
                    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");                    
                    VRwindow.setCursor(blankCursor);
                    jmeCanvas.getCanvas().setCursor(blankCursor);
                    VRwindow.pack();
                    VRwindow.setVisible(true);
                    startCanvas();
                    return;
                } catch(Exception e) { 
                    System.out.println("JFrame exception: " + e.toString());
                    useJFrame = false;
                }
            } else {
                // disable JFrame mode, start normally
                useJFrame = false;
            }
        }
        
        if( !isInVR() ) {
            // not in VR, show settings dialog
            if (!JmeSystem.showSettingsDialog(settings, loadSettings)) {
                return;
            }
        } else {
            // use basic mirroring window, skip settings window
            settings.setSamples(1);
            settings.setWidth(xWin);
            settings.setHeight(yWin);
            settings.setBitsPerPixel(32);
            settings.setVSync(true);
        }
        
        //re-setting settings they can have been merged from the registry.
        settings.setSwapBuffers(true);
        setSettings(settings);
        start(JmeContext.Type.Display, false);
    }    
    
    /*
        if making changes to default values, this must be called before the VRApplication starts
        sets paramters seen in VRApplication.PRECONFIG_PARAMETER enumeration
    */
    public void preconfigureVRApp(PRECONFIG_PARAMETER parm, boolean value) {        
        switch( parm ) {
            case FORCE_VR_MODE:
                forceVR = value;
                break;
            case USE_CUSTOM_DISTORTION:
                OpenVRViewManager._setCustomDistortion(value);
                break;
            case USE_JFRAME_EXTENDED_BACKUP:
                VRApplication.useJFrame = value;
                break;
            case USE_STEAMVR_COMPOSITOR:
                VRApplication.useCompositor = value;
                break;
            case FLIP_EYES:
                OpenVR._setFlipEyes(value);
                break;
        }
    }
    
    public static ArrayList<VRInput> getVRinputs() {
        return VRinput;
    }
    
    /*
        quick check if VR mode is enabled
    */
    public static boolean isInVR() {
        return forceVR || VRSupportedOS && VRhardware != null && VRhardware.isInitialized();
    }
    
    /*
        move filters out of the main scene & into the eye's
        this removes filters from the main scene!
    */
    public static void moveScreenProcessingToVR() {
        if( isInVR() ) {
            VRviewmanager.moveScreenProcessingToEyes();
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
    
    public Node getGuiNode(){
        return guiNode;
    }

    public Node getRootNode() {
        return rootNode;
    }
    
    public static Object getObserver() {
        if( observer == null ) {
            return mainApp.getCamera();
        }
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
    public static Quaternion getFinalObserverRotation() {
        if( VRviewmanager == null ) {
            if( VRApplication.observer == null ) {
                return mainApp.getCamera().getRotation();
            } else return VRApplication.observer.getWorldRotation();
        }        
        if( VRApplication.observer == null ) {
            return VRhardware.getOrientation().multLocal(mainApp.getCamera().getRotation());
        } else {
            return VRhardware.getOrientation().multLocal(VRApplication.observer.getWorldRotation());
        }
    }
    
    /*
        where is the headset, after all positional tracking is complete?
        includes observer position, if any
    */    
    public static Vector3f getFinalObserverPosition() {
        if( VRviewmanager == null ) {
            if( VRApplication.observer == null ) {
                return mainApp.getCamera().getLocation();
            } else return VRApplication.observer.getWorldTranslation();            
        }
        if( VRApplication.observer == null ) {
            Vector3f pos = VRhardware.getPosition();
            mainApp.getCamera().getRotation().mult(pos, pos);
            return pos.addLocal(mainApp.getCamera().getLocation());
        } else {
            Vector3f pos = VRhardware.getPosition();
            VRApplication.observer.getWorldRotation().mult(pos, pos);
            return pos.addLocal(VRApplication.observer.getWorldTranslation());
        }
    }
    
    /*
        quick way to adjust the height of the VR view
    */
    public static void setVRHeightAdjustment(float amount) {
        if( VRviewmanager != null ) VRviewmanager.setHeightAdjustment(amount);
    }
    
    public static float getVRHeightAdjustment() {
        if( VRviewmanager != null ) return VRviewmanager.getHeightAdjustment();
        return 0f;
    }
       
    public static ViewPort getLeftViewPort() {
        if( VRviewmanager == null ) return mainApp.getViewPort();
        return VRviewmanager.getViewPortLeft();
    }
    
    public static ViewPort getRightViewPort() {
        if( VRviewmanager == null ) return mainApp.getViewPort();
        return VRviewmanager.getViewPortRight();
    }
    
    public static VRApplication getMainVRApp() {
        return mainApp;
    }
    
    /*
        mirror output to main screen (only works with SteamVR Compositor)
    */
    public static void setMirroring(boolean set) {
        if( VRviewmanager == null ) return;
        VRviewmanager.setMirroring(set);
    }
    
    public static boolean getMirroring() {
        if( VRviewmanager == null ) return false;
        return VRviewmanager.getMirroring();
    }
        
    @Override
    public void update() {    
        super.update(); // makes sure to execute AppTasks
        
        if (speed == 0 || paused) {
            try {
                Thread.sleep(50); // throttle the CPU when paused
            } catch (InterruptedException ex) {
                Logger.getLogger(SimpleApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        // if we are using the VRwindow and it was disposed, exit the app
        if( VRwindow != null && VRwindow.isDisplayable() == false ) {
            destroy();
        }
        
        float tpf = timer.getTimePerFrame() * speed;
        
        // update states
        stateManager.update(tpf);

        // simple update and root node
        simpleUpdate(tpf);
 
        rootNode.updateLogicalState(tpf);
        guiNode.updateLogicalState(tpf);
        
        rootNode.updateGeometricState();
        
        if( VRApplication.isInVR() == false || VRGuiManager.getPositioningMode() == POSITIONING_MODE.MANUAL ) {
            // only update geometric state here if GUI is in manual mode, or not in VR
            // it will get updated automatically in the viewmanager update otherwise
            guiNode.updateGeometricState();
        }
        
        // render states
        stateManager.render(renderManager);
        
        // update VR pose & cameras
        if( VRviewmanager != null ) {
            VRviewmanager.update(tpf);           
        } else if( VRApplication.observer != null ) {
            getCamera().setFrame(VRApplication.observer.getWorldTranslation(), VRApplication.observer.getWorldRotation());
        }
        
        renderManager.render(tpf, context.isRenderable());
        simpleRender(renderManager);
        stateManager.postRender();
        
        // update compositor?
        if( VRviewmanager != null ) {
            VRviewmanager.postRender();
        }
    }

    @Override
    public void initialize() {
        super.initialize();
        cam.setFrustumFar(fFar);
        cam.setFrustumNear(fNear);
        dummyCam = cam.clone();
        if( isInVR() ) {
            if( compositorAllowed() == false || OpenVR.getVRSystemInstance() == null ) {
                System.out.println("Skipping SteamVR compositor!");
            } else {
                VRhardware.initOpenVRCompositor();
            }
            VRviewmanager = new OpenVRViewManager(this);
            inputManager.addListener(new VRListener(), new String[]{RESET_HMD, MIRRORING});
            inputManager.addMapping(RESET_HMD, new KeyTrigger(KeyInput.KEY_F9));
            inputManager.addMapping(MIRRORING, new KeyTrigger(KeyInput.KEY_F10));
            initVRinput();
            setLostFocusBehavior(LostFocusBehavior.Disabled);
        } else {
            viewPort.attachScene(rootNode);
            guiViewPort.attachScene(guiNode);
        }
        
        simpleInitApp();
        
        if( VRviewmanager != null ) VRviewmanager.initialize(this);
    }
    
    public abstract void simpleInitApp();
    
    @Override
    public void destroy() {
        if( VRhardware != null ) {
            VRhardware.destroy();
            for(int i=0;i<VRinput.size();i++) {
                VRinput.get(i).destroy();
            }
            VRhardware = null;
        }        
        super.destroy();
        Runtime.getRuntime().exit(0);
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