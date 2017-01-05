package jmevr.app;

import com.jme3.app.AppTask;
import com.jme3.app.Application;
import com.jme3.app.LegacyApplication;
import com.jme3.app.LostFocusBehavior;
import com.jme3.app.ResetStatsState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioContext;
import com.jme3.audio.AudioRenderer;
import com.jme3.audio.Listener;
import com.jme3.input.InputManager;
import com.jme3.input.JoyInput;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.TouchInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.profile.AppProfiler;
import com.jme3.profile.AppStep;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeSystem;
import com.jme3.system.NanoTimer;
import com.jme3.system.SystemListener;
import com.jme3.system.Timer;
import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmevr.input.OSVR;
import jmevr.input.OpenVR;
import jmevr.input.OpenVRInput;
import jmevr.input.VRAPI;
import jmevr.input.VRInputAPI;
import jmevr.post.PreNormalCaching;
import jmevr.util.VRViewManager;
import jmevr.util.VRGuiManager;
import jmevr.util.VRGuiManager.POSITIONING_MODE;
import jopenvr.JOpenVRLibrary;
import org.lwjgl.system.Platform;

/**
 *
 * @author reden
 */
public abstract class VRApplication implements Application, SystemListener {

    private static final Logger logger = Logger.getLogger(LegacyApplication.class.getName());

    public static float DEFAULT_FOV = 108f, DEFAULT_ASPECT = 1f;
    
    public static boolean CONSTRUCT_WITH_OSVR = false, DISABLE_VR;
    
    public static enum PRECONFIG_PARAMETER {
        USE_VR_COMPOSITOR, /*USE_CUSTOM_DISTORTION,*/ FORCE_VR_MODE, FLIP_EYES,
        SET_GUI_OVERDRAW, SET_GUI_CURVED_SURFACE, ENABLE_MIRROR_WINDOW, PREFER_OPENGL3, DISABLE_VR,
        SEATED_EXPERIENCE, NO_GUI, INSTANCE_VR_RENDERING, FORCE_DISABLE_MSAA
    }
    
    private static String OS;
    private static VRAPI VRhardware;    
    private static Camera dummyCam;
    private static VRViewManager VRviewmanager;
    private static VRApplication mainApp;
    private static Spatial observer;
    private static boolean VRSupportedOS, forceVR, disableSwapBuffers = true, tryOpenGL3 = true, seated, nogui, instanceVR, forceDisableMSAA;
    
    // things taken from LegacyApplication
    private AppStateManager stateManager;    
    private Camera cam;    
    private AppSettings settings;
    private JmeContext context;    
    private float speed = 1f;
    private AudioRenderer audioRenderer;    
    private LostFocusBehavior lostFocusBehavior = LostFocusBehavior.ThrottleOnLostFocus;
    private final ConcurrentLinkedQueue<AppTask<?>> taskQueue = new ConcurrentLinkedQueue<AppTask<?>>();
    private Timer timer = new NanoTimer();
    private boolean paused = false, inputEnabled = true;
    private InputManager inputManager;
    private RenderManager renderManager;    
    private ViewPort viewPort;
    private ViewPort guiViewPort;
    private AssetManager assetManager;
    private Renderer renderer;
    private Listener listener;
    private MouseInput mouseInput;
    private KeyInput keyInput;
    private JoyInput joyInput;
    private TouchInput touchInput;

    protected Node guiNode, rootNode;
    
    private float fFar = 1000f, fNear = 1f;
    private int xWin = 1280, yWin = 720;
    
    private static float distanceOfOptimization = 0f, resMult = 1f;
    
    private static boolean useCompositor = true, compositorOS;
    private final String RESET_HMD = "ResetHMD";
    
    // no longer using LwjglCanvas, and this sometimes broke the graphics settings
    /*static {
        if( VR_IsHmdPresent() != 0 ) {
            System.setProperty("sun.java2d.opengl", "True");
        }                        
    } */   
    
    public static float getOptimizationDistance() {
        return distanceOfOptimization;
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

    public VRApplication(AppState... initialStates) {
        this();
        
        if (initialStates != null) {
            for (AppState a : initialStates) {
                if (a != null) {
                    stateManager.attach(a);
                }
            }
        }
    }
    
    public void preconfigureFrustrumNearFar(float near, float far) {
        fNear = near;
        fFar = far;
    }
    
    public void preconfigureMirrorWindowSize(int width, int height) {
        xWin = width;
        yWin = height;
    }
    
    public void preconfigureResolutionMultiplier(float val) {
        resMult = val;
        if( VRviewmanager != null ) VRviewmanager.setResolutionMultiplier(resMult);
    }

    public VRApplication() {
        super();
        initStateManager();
        
        rootNode = new Node("root");
        guiNode = new Node("guiNode");
        guiNode.setQueueBucket(Bucket.Gui);
        guiNode.setCullHint(CullHint.Never);
        dummyCam = new Camera();
        mainApp = this;
        
        // we are going to use OpenVR now, not the Oculus Rift
        // OpenVR does support the Rift
        OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        VRSupportedOS = !OS.contains("nux") && System.getProperty("sun.arch.data.model").equalsIgnoreCase("64"); //for the moment, linux/unix causes crashes, 64-bit only
        compositorOS = OS.contains("indows");
        
        if( !VRSupportedOS ) {
            System.out.println("Non-supported OS: " + OS + ", architecture: " + System.getProperty("sun.arch.data.model"));
        } else if( DISABLE_VR ) {
            System.out.println("VR disabled via code.");
        } else if( VRSupportedOS && DISABLE_VR == false ) {
            if( CONSTRUCT_WITH_OSVR ) {
                System.out.println("Initializing OSVR...");
                VRhardware = new OSVR();
            } else {
                System.out.println("Initializing OpenVR...");
                VRhardware = new OpenVR();
            }
            if( VRhardware.initialize() ) {
                setPauseOnLostFocus(false);
            }
        }
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
        return cam;
    }
    
    public Camera getBaseCamera() {
        return cam;
    }

    /**
     * Starts the application in {@link Type#Display display} mode.
     *
     * @see #start(com.jme3.system.JmeContext.Type)
     */
    public void start(boolean waitFor){
        start(JmeContext.Type.Display, waitFor);
    }    

    /**
     * @return The {@link JmeContext display context} for the application
     */
    public JmeContext getContext(){
        return context;
    }

    /**
     * @return The {@link AssetManager asset manager} for this application.
     */
    public AssetManager getAssetManager(){
        return assetManager;
    }

    /**
     * @return the {@link InputManager input manager}.
     */
    public InputManager getInputManager(){
        return inputManager;
    }

    /**
     * @return the {@link AppStateManager app state manager}
     */
    public AppStateManager getStateManager() {
        return stateManager;
    }

    /**
     * @return the {@link RenderManager render manager}
     */
    public RenderManager getRenderManager() {
        return renderManager;
    }

    /**
     * @return The {@link Renderer renderer} for the application
     */
    public Renderer getRenderer(){
        return renderer;
    }

    /**
     * @return The {@link AudioRenderer audio renderer} for the application
     */
    public AudioRenderer getAudioRenderer() {
        return audioRenderer;
    }

    /**
     * @return The {@link Listener listener} object for audio
     */
    public Listener getListener() {
        return listener;
    }
    
    public Timer getTimer(){
        return timer;
    }    

    /**
     * Internal use only.
     */
    public void handleError(String errMsg, Throwable t){
        // Print error to log.
        logger.log(Level.SEVERE, errMsg, t);
        // Display error message on screen if not in headless mode
        if (context.getType() != JmeContext.Type.Headless) {
            if (t != null) {
                JmeSystem.showErrorDialog(errMsg + "\n" + t.getClass().getSimpleName() +
                        (t.getMessage() != null ? ": " +  t.getMessage() : ""));
            } else {
                JmeSystem.showErrorDialog(errMsg);
            }
        }

        stop(); // stop the application
    }


    /**
     * Internal use only.
     */
    public void gainFocus(){
        if (lostFocusBehavior != LostFocusBehavior.Disabled) {
            if (lostFocusBehavior == LostFocusBehavior.PauseOnLostFocus) {
                paused = false;
            }
            context.setAutoFlushFrames(true);
            if (inputManager != null) {
                inputManager.reset();
            }
        }
    }
    
    /**
     * Internal use only.
     */
    public void reshape(int w, int h){
        if (renderManager != null) {
            renderManager.notifyReshape(w, h);
        }
    }    

    /**
     * Internal use only.
     */
    public void loseFocus(){
        if (lostFocusBehavior != LostFocusBehavior.Disabled){
            if (lostFocusBehavior == LostFocusBehavior.PauseOnLostFocus) {
                paused = true;
            }
            context.setAutoFlushFrames(false);
        }
    }

    /**
     * Internal use only.
     */
    public void requestClose(boolean esc){
        context.destroy(false);
    }
    
    /**
     * Set the display settings to define the display created.
     * <p>
     * Examples of display parameters include display pixel width and height,
     * color bit depth, z-buffer bits, anti-aliasing samples, and update frequency.
     * If this method is called while the application is already running, then
     * {@link #restart() } must be called to apply the settings to the display.
     *
     * @param settings The settings to set.
     */
    public void setSettings(AppSettings settings){
        this.settings = settings;
        if (context != null && settings.useInput() != inputEnabled){
            // may need to create or destroy input based
            // on settings change
            inputEnabled = !inputEnabled;
            if (inputEnabled){
                initInput();
            }else{
                destroyInput();
            }
        }else{
            inputEnabled = settings.useInput();
        }
    }    
    
    /**
     * Sets the Timer implementation that will be used for calculating
     * frame times.  By default, Application will use the Timer as returned
     * by the current JmeContext implementation.
     */
    public void setTimer(Timer timer){
        this.timer = timer;

        if (timer != null) {
            timer.reset();
        }

        if (renderManager != null) {
            renderManager.setTimer(timer);
        }
    }
    

    /**
     * Determine the application's behavior when unfocused.
     *
     * @return The lost focus behavior of the application.
     */
    public LostFocusBehavior getLostFocusBehavior() {
        return lostFocusBehavior;
    }

    /**
     * Change the application's behavior when unfocused.
     *
     * By default, the application will
     * {@link LostFocusBehavior#ThrottleOnLostFocus throttle the update loop}
     * so as to not take 100% CPU usage when it is not in focus, e.g.
     * alt-tabbed, minimized, or obstructed by another window.
     *
     * @param lostFocusBehavior The new lost focus behavior to use.
     *
     * @see LostFocusBehavior
     */
    public void setLostFocusBehavior(LostFocusBehavior lostFocusBehavior) {
        this.lostFocusBehavior = lostFocusBehavior;
    }

    /**
     * Returns true if pause on lost focus is enabled, false otherwise.
     *
     * @return true if pause on lost focus is enabled
     *
     * @see #getLostFocusBehavior()
     */
    public boolean isPauseOnLostFocus() {
        return getLostFocusBehavior() == LostFocusBehavior.PauseOnLostFocus;
    }

    /**
     * Enable or disable pause on lost focus.
     * <p>
     * By default, pause on lost focus is enabled.
     * If enabled, the application will stop updating
     * when it loses focus or becomes inactive (e.g. alt-tab).
     * For online or real-time applications, this might not be preferable,
     * so this feature should be set to disabled. For other applications,
     * it is best to keep it on so that CPU usage is not used when
     * not necessary.
     *
     * @param pauseOnLostFocus True to enable pause on lost focus, false
     * otherwise.
     *
     * @see #setLostFocusBehavior(com.jme3.app.LostFocusBehavior)
     */
    public void setPauseOnLostFocus(boolean pauseOnLostFocus) {
        if (pauseOnLostFocus) {
            setLostFocusBehavior(LostFocusBehavior.PauseOnLostFocus);
        } else {
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
        
        GraphicsDevice defDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                                    
        if( isInVR() && !compositorAllowed() ) {
            // "easy extended" mode
            // TO-DO: JFrame was removed in LWJGL 3, need to use new GLFW library to pick "monitor" display of VR device
            // first, find the VR device
            GraphicsDevice VRdev = null;
            GraphicsDevice[] devs = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
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
            if( Platform.get() != Platform.MACOSX ) {
                if (!JmeSystem.showSettingsDialog(settings, loadSettings)) {
                    return;
                }            
            } else {
                // GLFW workaround on macs
                settings.setFrequency(defDev.getDisplayMode().getRefreshRate());
                settings.setDepthBits(24);
                settings.setVSync(true);
                // try and read resolution from file in local dir
                File resfile = new File("resolution.txt");
                if( resfile.exists() ) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(resfile));
                        settings.setWidth(Integer.parseInt(br.readLine()));
                        settings.setHeight(Integer.parseInt(br.readLine()));
                        try {
                            settings.setFullscreen(br.readLine().toLowerCase(Locale.ENGLISH).contains("full"));
                        } catch(Exception e) {
                            settings.setFullscreen(false);
                        }
                        br.close();
                    } catch(Exception e) {
                        settings.setWidth(1280);
                        settings.setHeight(720);
                    }
                } else {
                    settings.setWidth(1280);
                    settings.setHeight(720);
                    settings.setFullscreen(false);
                }
                settings.setResizable(false);
            }
            settings.setSwapBuffers(true);
        } else {
            // use basic mirroring window, skip settings window
            settings.setWidth(xWin);
            settings.setHeight(yWin);
            settings.setBitsPerPixel(24);     
            settings.setFrameRate(0); // never sleep in main loop
            settings.setFrequency(VRhardware.getDisplayFrequency());
            settings.setFullscreen(false);
            settings.setVSync(false); // stop vsyncing on primary monitor!
            settings.setSwapBuffers(!disableSwapBuffers || VRhardware instanceof OSVR);
            settings.setTitle("Put Headset On Now: " + settings.getTitle());
            settings.setResizable(true);
        }
        
        if( forceDisableMSAA ) {
            // disable multisampling, which is more likely to break things than be useful
            settings.setSamples(1);
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
    
    /**
     * Starts the application.
     * Creating a rendering context and executing
     * the main loop in a separate thread.
     */
    public void start(JmeContext.Type contextType, boolean waitFor){
        if (context != null && context.isCreated()){
            logger.warning("start() called when application already created!");
            return;
        }

        if (settings == null){
            settings = new AppSettings(true);
        }

        logger.log(Level.FINE, "Starting application: {0}", getClass().getName());
        context = JmeSystem.newContext(settings, contextType);
        context.setSystemListener(this);
        context.create(waitFor);
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
            //case USE_CUSTOM_DISTORTION: //deprecated, always using a render manager
            //    VRViewManager._setCustomDistortion(value);
            //    break;
            case USE_VR_COMPOSITOR:
                VRApplication.useCompositor = value;
                if( value == false ) disableSwapBuffers = false;
                break;
            case FLIP_EYES:
                if( VRhardware == null ) return;
                VRhardware._setFlipEyes(value);
                break;
            case INSTANCE_VR_RENDERING:
                instanceVR = value;
                break;
            case ENABLE_MIRROR_WINDOW:
                if( VRApplication.useCompositor == false ) {
                    disableSwapBuffers = false;
                } else disableSwapBuffers = !value;
                break;
            case PREFER_OPENGL3:
                tryOpenGL3 = value;
                break;
            case DISABLE_VR:
                DISABLE_VR = value;
                break;
            case NO_GUI:
                nogui = value;
                break;
            case SEATED_EXPERIENCE:
                seated = value;
                break;
            case FORCE_DISABLE_MSAA:
                forceDisableMSAA = value;
                break;
        }
    }
    
    /**
     * can be used to change seated experience during runtime
     * 
     * @param isSeated true if designed for sitting, false for standing/roomscale
     */
    public static void setSeatedExperience(boolean isSeated) {
        seated = isSeated;
        if( VRhardware instanceof OpenVR ) {
            if( VRhardware.getCompositor() == null ) return;
            if( seated ) {
                ((OpenVR)VRhardware).getCompositor().SetTrackingSpace.apply(JOpenVRLibrary.ETrackingUniverseOrigin.ETrackingUniverseOrigin_TrackingUniverseSeated);
            } else {
                ((OpenVR)VRhardware).getCompositor().SetTrackingSpace.apply(JOpenVRLibrary.ETrackingUniverseOrigin.ETrackingUniverseOrigin_TrackingUniverseStanding);                
            }        
        }
    }
    
    public static boolean isSeatedExperience() {
        return seated;
    }
    
    public static VRInputAPI getVRinput() {
        if( VRhardware == null ) return null;
        return VRhardware.getVRinput();
    }
    
    public static boolean isInstanceVRRendering() {
        return instanceVR && isInVR();
    }
    
    /*
        quick check if VR mode is enabled
    */
    public static boolean isInVR() {
        return DISABLE_VR == false && (forceVR || VRSupportedOS && VRhardware != null && VRhardware.isInitialized());
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
    public static VRAPI getVRHardware() {
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
    public static VRViewManager getVRViewManager() {
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

    /**
     * Runs tasks enqueued via {@link #enqueue(Callable)}
     */
    protected void runQueuedTasks() {
	  AppTask<?> task;
        while( (task = taskQueue.poll()) != null ) {
            if (!task.isCancelled()) {
                task.invoke();
            }
        }
    }
    
    @Override
    public void update() {    
        // Make sure the audio renderer is available to callables
        AudioContext.setAudioRenderer(audioRenderer);

        runQueuedTasks();

        if (speed != 0 && !paused) {

            timer.update();

            if (inputEnabled){
                inputManager.update(timer.getTimePerFrame());
            }

            if (audioRenderer != null){
                audioRenderer.update(timer.getTimePerFrame());
            }
        }
        
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
            VRviewmanager.sendTextures();
        }
    }

    private void initAssetManager(){
        URL assetCfgUrl = null;

        if (settings != null){
            String assetCfg = settings.getString("AssetConfigURL");
            if (assetCfg != null){
                try {
                    assetCfgUrl = new URL(assetCfg);
                } catch (MalformedURLException ex) {
                }
                if (assetCfgUrl == null) {
                    assetCfgUrl = LegacyApplication.class.getClassLoader().getResource(assetCfg);
                    if (assetCfgUrl == null) {
                        logger.log(Level.SEVERE, "Unable to access AssetConfigURL in asset config:{0}", assetCfg);
                        return;
                    }
                }
            }
        }
        if (assetCfgUrl == null) {
            assetCfgUrl = JmeSystem.getPlatformAssetConfigURL();
        }
        if (assetManager == null){
            assetManager = JmeSystem.newAssetManager(assetCfgUrl);
        }
    }
    

    private void initDisplay(){
        // aquire important objects
        // from the context
        settings = context.getSettings();

        // Only reset the timer if a user has not already provided one
        if (timer == null) {
            timer = context.getTimer();
        }

        renderer = context.getRenderer();
    }

    private void initAudio(){
        if (settings.getAudioRenderer() != null && context.getType() != JmeContext.Type.Headless){
            audioRenderer = JmeSystem.newAudioRenderer(settings);
            audioRenderer.initialize();
            AudioContext.setAudioRenderer(audioRenderer);

            listener = new Listener();
            audioRenderer.setListener(listener);
        }
    }

    /**
     * Creates the camera to use for rendering. Default values are perspective
     * projection with 45° field of view, with near and far values 1 and 1000
     * units respectively.
     */
    private void initCamera(){
        cam = new Camera(settings.getWidth(), settings.getHeight());

        cam.setFrustumPerspective(45f, (float)cam.getWidth() / cam.getHeight(), 1f, 1000f);
        cam.setLocation(new Vector3f(0f, 0f, 10f));
        cam.lookAt(new Vector3f(0f, 0f, 0f), Vector3f.UNIT_Y);

        renderManager = new RenderManager(renderer);
        //Remy - 09/14/2010 setted the timer in the renderManager
        renderManager.setTimer(timer);

        viewPort = renderManager.createMainView("Default", cam);
        viewPort.setClearFlags(true, true, true);

        // Create a new cam for the gui
        Camera guiCam = new Camera(settings.getWidth(), settings.getHeight());
        guiViewPort = renderManager.createPostView("Gui Default", guiCam);
        guiViewPort.setClearFlags(false, false, false);
    }

    /**
     * Initializes mouse and keyboard input. Also
     * initializes joystick input if joysticks are enabled in the
     * AppSettings.
     */
    private void initInput(){
        mouseInput = context.getMouseInput();
        if (mouseInput != null)
            mouseInput.initialize();

        keyInput = context.getKeyInput();
        if (keyInput != null)
            keyInput.initialize();

        touchInput = context.getTouchInput();
        if (touchInput != null)
            touchInput.initialize();

        if (!settings.getBoolean("DisableJoysticks")){
            joyInput = context.getJoyInput();
            if (joyInput != null)
                joyInput.initialize();
        }

        inputManager = new InputManager(mouseInput, keyInput, joyInput, touchInput);
    }

    private void initStateManager(){
        stateManager = new AppStateManager(this);

        // Always register a ResetStatsState to make sure
        // that the stats are cleared every frame
        stateManager.attach(new ResetStatsState());
    }    

    /**
     * Do not call manually.
     * Callback from ContextListener.
     * <p>
     * Initializes the <code>Application</code>, by creating a display and
     * default camera. If display settings are not specified, a default
     * 640x480 display is created. Default values are used for the camera;
     * perspective projection with 45° field of view, with near
     * and far values 1 and 1000 units respectively.
     */
    private void initialize_internal(){
        if (assetManager == null){
            initAssetManager();
        }

        initDisplay();
        initCamera();

        if (inputEnabled){
            initInput();
        }
        initAudio();

        // update timer so that the next delta is not too large
//        timer.update();
        timer.reset();

        // user code here..
    }
    
    @Override
    public void initialize() {
        initialize_internal();
        cam.setFrustumFar(fFar);
        cam.setFrustumNear(fNear);
        dummyCam = cam.clone();
        if( isInVR() ) {
            if( VRhardware != null ) {
                VRhardware.initVRCompositor(compositorAllowed());
            }
            VRviewmanager = new VRViewManager(this);
            VRviewmanager.setResolutionMultiplier(resMult);
            inputManager.addMapping(RESET_HMD, new KeyTrigger(KeyInput.KEY_F9));
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
            
            // print out camera information
            if( isInVR() ) {
                System.out.println("<--- VR Initialization Information --->");
                if( VRviewmanager.getCamLeft() != null ) System.out.println("camLeft: " + VRviewmanager.getCamLeft().toString());
                if( VRviewmanager.getCamRight() != null ) System.out.println("camRight: " + VRviewmanager.getCamRight().toString());
                System.out.println("<--- ----------------------------- --->");
            }
        }
    }
    
    public abstract void simpleInitApp();
    
    public void destroy() {
        if( VRhardware != null ) {
            VRhardware.destroy();
            VRhardware = null;
        }        
        DISABLE_VR = true;
        stateManager.cleanup();

        destroyInput();
        if (audioRenderer != null)
            audioRenderer.cleanup();

        timer.reset();
        Runtime.getRuntime().exit(0);
    }
    
    protected void destroyInput(){
        if (mouseInput != null)
            mouseInput.destroy();

        if (keyInput != null)
            keyInput.destroy();

        if (joyInput != null)
            joyInput.destroy();

        if (touchInput != null)
            touchInput.destroy();

        inputManager = null;
    }
    
    /*
        reset headset pose if seating experience. defaults to F9 key!
    */
    public static void resetSeatedPose(){
        if( VRSupportedOS == false || isSeatedExperience() == false ) return;
        VRhardware.reset();
    }
    
    /**
     * @return The GUI viewport. Which is used for the on screen
     * statistics and FPS.
     */
    @Override
    public ViewPort getGuiViewPort() {
        return guiViewPort;
    }

    @Override
    public ViewPort getViewPort() {
        return viewPort;
    }
    
    /**
     * Enqueues a task/callable object to execute in the jME3
     * rendering thread.
     * <p>
     * Callables are executed right at the beginning of the main loop.
     * They are executed even if the application is currently paused
     * or out of focus.
     *
     * @param callable The callable to run in the main jME3 thread
     */
    @Override
    public <V> Future<V> enqueue(Callable<V> callable) {
        AppTask<V> task = new AppTask<V>(callable);
        taskQueue.add(task);
        return task;
    }
    
    /**
     * Enqueues a runnable object to execute in the jME3
     * rendering thread.
     * <p>
     * Runnables are executed right at the beginning of the main loop.
     * They are executed even if the application is currently paused
     * or out of focus.
     *
     * @param runnable The runnable to run in the main jME3 thread
     */
    public void enqueue(Runnable runnable){
        enqueue(new RunnableWrapper(runnable));
    }

    private class RunnableWrapper implements Callable{
        private final Runnable runnable;

        public RunnableWrapper(Runnable runnable){
            this.runnable = runnable;
        }

        @Override
        public Object call(){
            runnable.run();
            return null;
        }

    }    

    /**
     * Requests the context to close, shutting down the main loop
     * and making necessary cleanup operations.
     *
     * Same as calling stop(false)
     *
     * @see #stop(boolean)
     */
    @Override
    public void stop(){
        stop(false);
    }

    /**
     * Requests the context to close, shutting down the main loop
     * and making necessary cleanup operations.
     * After the application has stopped, it cannot be used anymore.
     */
    @Override
    public void stop(boolean waitFor){
        logger.log(Level.FINE, "Closing application: {0}", getClass().getName());
        context.destroy(waitFor);
    }

    /**
     * Restarts the context, applying any changed settings.
     * <p>
     * Changes to the {@link AppSettings} of this Application are not
     * applied immediately; calling this method forces the context
     * to restart, applying the new settings.
     */
    @Override
    public void restart(){
        context.setSettings(settings);
        context.restart();
    }

    /**
     * Sets an AppProfiler hook that will be called back for
     * specific steps within a single update frame.  Value defaults
     * to null.
     */
    
    public void setAppProfiler(AppProfiler prof) {
        return;
    }

    /**
     * Returns the current AppProfiler hook, or null if none is set.
     */
    public AppProfiler getAppProfiler() {
        return null;
    }
}