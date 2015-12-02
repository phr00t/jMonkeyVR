package jmevr.app;

import com.jme3.app.Application;
import com.jme3.app.LostFocusBehavior;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.system.AppSettings;
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
import jmevr.input.OpenVR;
import jmevr.input.VRInput;
import jmevr.post.PreNormalCaching;
import jmevr.util.OpenVRViewManager;
import jmevr.util.VRGuiManager;
import jmevr.util.VRGuiManager.POSITIONING_MODE;
import jmevr.util.VRInstanceNode;
import static jopenvr.JOpenVRLibrary.VR_IsHmdPresent;

/**
 *
 * @author reden
 */
public abstract class VRApplication extends Application {

    public static float DEFAULT_FOV = 110f, DEFAULT_ASPECT = 1f;
    
    public static enum PRECONFIG_PARAMETER {
        USE_STEAMVR_COMPOSITOR, USE_CUSTOM_DISTORTION, FORCE_VR_MODE, FLIP_EYES,
        SET_GUI_OVERDRAW, SET_GUI_CURVED_SURFACE, DISABLE_SWAPBUFFERS_COMPLETELY, PREFER_OPENGL3, DISABLE_VR,
        SEATED_EXPERIENCE, NO_GUI, INSTANCE_VR_RENDERING
    }
    
    private static OpenVR VRhardware;    
    private static Camera dummyCam;
    private static OpenVRViewManager VRviewmanager;
    private static VRApplication mainApp;
    private static Spatial observer;
    private static boolean VRSupportedOS, forceVR, disableSwapBuffers, tryOpenGL3 = true, disableVR, seated, nogui, instanceVR;
    private static final ArrayList<VRInput> VRinput = new ArrayList<>();
    
    protected Node guiNode;
    protected VRInstanceNode rootNode;
    
    private float fFar = 1000f, fNear = 1f;
    private int xWin = 1280, yWin = 720;
    
    private static float distanceOfOptimization = 0f;
    
    private static boolean useCompositor = true, compositorOS;
    private final String RESET_HMD = "ResetHMD", MIRRORING = "Mirror";
    
    static {
        if( VR_IsHmdPresent() != 0 ) {
            System.setProperty("sun.java2d.opengl", "True");
        }                        
    }    
    
    public static float getOptimizationDistance() {
        return distanceOfOptimization;
    }
    
    private class VRListener implements ActionListener{

        public void onAction(String name, boolean isPressed, float tpf) {
            if( isPressed ) {
                if (name.equals(RESET_HMD)){
                    resetSeatedPose();
                } else if( name.equals(MIRRORING) ) {
                    VRApplication.setMirroring(!VRApplication.getMirroring());
                }
            }
        }
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
        rootNode = new VRInstanceNode("root");
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
        if( VRSupportedOS && disableVR == false ) VRhardware.initialize();
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

        if( isInVR() && !compositorAllowed() ) {
            // "easy extended" mode
            // TO-DO: JFrame was removed in LWJGL 3, need to use new GLFW library to pick "monitor" display of VR device
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
                    settings.setWidth(useDM.getWidth());
                    settings.setHeight(useDM.getHeight());
                    settings.setBitsPerPixel(useDM.getBitDepth());
                    settings.setFrequency(useDM.getRefreshRate());
                    settings.setSwapBuffers(true);
                    settings.setVSync(true); // allow vsync on this display
                    setSettings(settings);
                    //VRdev.setFullScreenWindow(VRwindow);
                    // make sure we are in the right display mode
                    if( VRdev.getDisplayMode().equals(useDM) == false ) {
                        VRdev.setDisplayMode(useDM);
                    }
                    // make a blank cursor to hide it
                    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");                    
                    //VRwindow.setCursor(blankCursor);
                    //jmeCanvas.getCanvas().setCursor(blankCursor);
                    //VRwindow.pack();
                    //VRwindow.setVisible(true);
                    //startCanvas();
                    return;
                } catch(Exception e) { 
                    
                }
            }
        }
        
        if( !isInVR() ) {
            // not in VR, show settings dialog
            if (!JmeSystem.showSettingsDialog(settings, loadSettings)) {
                return;
            }            
            settings.setSwapBuffers(true);
        } else {
            // use basic mirroring window, skip settings window
            settings.setSamples(1);
            settings.setWidth(xWin);
            settings.setHeight(yWin);
            settings.setBitsPerPixel(32);     
            settings.setFrameRate(0);
            settings.setFrequency(OpenVR.getDisplayFrequency());
            settings.setFullscreen(false);
            settings.setVSync(false); // stop vsyncing on primary monitor!
            settings.setSwapBuffers(!disableSwapBuffers);
        }
        
        // set opengl mode
        if( tryOpenGL3 ) {
            settings.setRenderer(AppSettings.LWJGL_OPENGL3);
        } else {
            settings.setRenderer(AppSettings.LWJGL_OPENGL2);
        }
        
        setSettings(settings);
        start(JmeContext.Type.Display, false);
        
        // disable annoying warnings about GUI stuff being updated, which is normal behavior
        // for late GUI placement for VR purposes
        Logger.getLogger("com.jme3").setLevel(Level.SEVERE);        
    }    
    
    /*
        if making changes to default values, this must be called before the VRApplication starts
        sets paramters seen in VRApplication.PRECONFIG_PARAMETER enumeration
    */
    public void preconfigureVRApp(PRECONFIG_PARAMETER parm, boolean value) {        
        switch( parm ) {
            case SET_GUI_OVERDRAW:
                VRGuiManager._enableGuiOverdraw(value);
                break;
            case SET_GUI_CURVED_SURFACE:
                VRGuiManager._enableCurvedSuface(value);
                break;
            case FORCE_VR_MODE:
                forceVR = value;
                break;
            case USE_CUSTOM_DISTORTION:
                OpenVRViewManager._setCustomDistortion(value);
                break;
            case USE_STEAMVR_COMPOSITOR:
                VRApplication.useCompositor = value;
                break;
            case FLIP_EYES:
                OpenVR._setFlipEyes(value);
                break;
            case INSTANCE_VR_RENDERING:
                instanceVR = value;
                break;
            case DISABLE_SWAPBUFFERS_COMPLETELY:
                disableSwapBuffers = value;
                break;
            case PREFER_OPENGL3:
                tryOpenGL3 = value;
                break;
            case DISABLE_VR:
                disableVR = value;
                break;
            case NO_GUI:
                nogui = value;
                break;
            case SEATED_EXPERIENCE:
                seated = value;
                break;
        }
    }
    
    public static boolean isSeatedExperience() {
        return seated;
    }
    
    public static ArrayList<VRInput> getVRinputs() {
        return VRinput;
    }
    
    public static boolean isInstanceVRRendering() {
        return instanceVR && isInVR();
    }
    
    /*
        quick check if VR mode is enabled
    */
    public static boolean isInVR() {
        return disableVR == false && (forceVR || VRSupportedOS && VRhardware != null && VRhardware.isInitialized());
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
    
    public Node getGuiNode(){
        return guiNode;
    }
    
    public static boolean hasTraditionalGUIOverlay() {
        return !nogui;
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
        get view manager
    */
    public static OpenVRViewManager getVRViewManager() {
        return VRviewmanager;
    }
    
    /*
        where is the headset pointing, after all rotations are combined?
        depends on observer rotation, if any
    */
    private static Quaternion tempq = new Quaternion();
    public static Quaternion getFinalObserverRotation() {
        if( VRviewmanager == null ) {
            if( VRApplication.observer == null ) {
                return mainApp.getCamera().getRotation();
            } else return VRApplication.observer.getWorldRotation();
        }        
        if( VRApplication.observer == null ) {
            tempq.set(dummyCam.getRotation());
        } else {
            tempq.set(VRApplication.observer.getWorldRotation());
        }
        return tempq.multLocal(VRhardware.getOrientation());
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
        Vector3f pos = VRhardware.getPosition();
        if( VRApplication.observer == null ) {
            dummyCam.getRotation().mult(pos, pos);
            return pos.addLocal(dummyCam.getLocation());
        } else {
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
    
    /*
        helper function for setting both eye backgrounds the same color,
        or just the normal viewport background color if not in VR
    */
    public static void setBackgroundColors(ColorRGBA clr) {
        if( VRviewmanager == null ) {
            mainApp.getViewPort().setBackgroundColor(clr);
        } else if( VRviewmanager.getViewPortLeft() != null ) {
            VRviewmanager.getViewPortLeft().setBackgroundColor(clr);
            if( VRviewmanager.getViewPortRight() != null ) VRviewmanager.getViewPortRight().setBackgroundColor(clr);
        }
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
            setLostFocusBehavior(LostFocusBehavior.Disabled);
        } else {
            viewPort.attachScene(rootNode);
            guiViewPort.attachScene(guiNode);
        }
        
        if( VRviewmanager != null ) {
            VRviewmanager.initialize(this);
        }
        
        simpleInitApp();
        
        // any filters created, move them now
        if( VRviewmanager != null ) {
            VRviewmanager.moveScreenProcessingToEyes();
        }
    }
    
    public abstract void simpleInitApp();
    
    @Override
    public void destroy() {
        if( VRhardware != null ) {
            VRhardware.destroy();
            VRhardware = null;
        }        
        super.destroy();
        Runtime.getRuntime().exit(0);
    }
    
    /*
        reset headset pose if seating experience. defaults to F9 key!
    */
    public static void resetSeatedPose(){
        if( VRSupportedOS == false || isSeatedExperience() == false ) return;
        VRhardware.reset();
    }
}