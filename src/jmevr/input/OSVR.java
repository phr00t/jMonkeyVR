/*

https://github.com/sensics/OSVR-RenderManager/blob/master/examples/RenderManagerOpenGLCAPIExample.cpp

- JVM crashes often.. placing breakpoints during initialization clears it up most of the time (WHY!?)
- no output to standard window (mirror) -- just black
- no output to render manager -- just white (might be due to no OSVR hooked up / not able to time warp)

 */
package jmevr.input;

import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.ochafik.lang.jnaerator.runtime.NativeSizeByReference;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import jmevr.util.VRUtil;
import jopenvr.JOpenVRLibrary;
import osvrclientkit.OsvrClientKitLibrary;
import osvrdisplay.OsvrDisplayLibrary;
import osvrdisplay.OsvrDisplayLibrary.OSVR_DisplayConfig;
import osvrmatrixconventions.OSVR_Pose3;
import osvrmatrixconventions.OsvrMatrixConventionsLibrary;
import osvrrendermanageropengl.OSVR_OpenResultsOpenGL;
import osvrrendermanageropengl.OSVR_RenderBufferOpenGL;
import osvrrendermanageropengl.OSVR_RenderInfoOpenGL;
import osvrrendermanageropengl.OSVR_RenderParams;
import osvrrendermanageropengl.OSVR_ViewportDescription;
import osvrrendermanageropengl.OsvrRenderManagerOpenGLLibrary;

/**
 *
 * @author Phr00t
 */
public class OSVR implements VRAPI {

    public static final int FIRST_VIEWER = 0, EYE_LEFT = 0, EYE_RIGHT = 1;
    public static final NativeSize EYE_LEFT_SIZE = new NativeSize(EYE_LEFT), EYE_RIGHT_SIZE = new NativeSize(EYE_RIGHT);
    
    private final Matrix4f[] eyeMatrix = new Matrix4f[2];
    
    OSVR_RenderParams.ByValue renderParams;
    OsvrClientKitLibrary.OSVR_ClientContext context;
    osvrrendermanageropengl.OSVR_GraphicsLibraryOpenGL.ByValue graphicsLibrary;
    Pointer renderManager, renderManagerOpenGL, renderInfoCollection, registerBufferState;
    OSVRInput VRinput;
    NativeSize numRenderInfo;
    OSVR_RenderInfoOpenGL.ByValue eyeLeftInfo, eyeRightInfo;
    Matrix4f hmdPoseLeftEye;
    Matrix4f hmdPoseRightEye;    
    Vector3f hmdPoseLeftEyeVec, hmdPoseRightEyeVec, hmdSeatToStand;
    OSVR_DisplayConfig displayConfig;
    OSVR_Pose3 hmdPose = new OSVR_Pose3();
    Vector3f storePos = new Vector3f();
    Quaternion storeRot = new Quaternion();
    PointerByReference presentState = new PointerByReference();
    long glfwContext, renderManagerContext, wglGLFW, wglRM;
    
    boolean initSuccess = false, flipEyes = false;
    
    public boolean handleRenderBufferPresent(OSVR_ViewportDescription.ByValue leftView, OSVR_ViewportDescription.ByValue rightView,
                                             OSVR_RenderBufferOpenGL.ByValue leftBuffer, OSVR_RenderBufferOpenGL.ByValue rightBuffer) {
        if( eyeLeftInfo == null || eyeRightInfo == null ) return false;
        byte retval;
        org.lwjgl.opengl.WGL.wglMakeCurrent(wglRM, wglGLFW);
        org.lwjgl.glfw.GLFW.glfwMakeContextCurrent(renderManagerContext);
        OsvrRenderManagerOpenGLLibrary.osvrRenderManagerStartPresentRenderBuffers(presentState);
        //OsvrRenderManagerOpenGLLibrary.osvrRenderManagerGetRenderInfoFromCollectionOpenGL(renderInfoCollection, eye==EYE_LEFT?EYE_LEFT_SIZE:EYE_RIGHT_SIZE, useEye); //already done...
        OsvrRenderManagerOpenGLLibrary.osvrRenderManagerPresentRenderBufferOpenGL(presentState.getValue(), leftBuffer, eyeLeftInfo, leftView);
        OsvrRenderManagerOpenGLLibrary.osvrRenderManagerPresentRenderBufferOpenGL(presentState.getValue(), rightBuffer, eyeRightInfo, rightView);
        retval = OsvrRenderManagerOpenGLLibrary.osvrRenderManagerFinishPresentRenderBuffers(renderManager, presentState.getValue(), renderParams, (byte)0);
        org.lwjgl.opengl.WGL.wglMakeCurrent(wglGLFW, wglRM);
        org.lwjgl.glfw.GLFW.glfwMakeContextCurrent(glfwContext);
        return retval == 0; // only check the last error, since if something errored above, the last call won't work & all calls will log to syserr
    }
    
    public static byte[] defaultNullString = { (byte)32, (byte)0 };
    
    @Override
    public boolean initialize() {
        context = OsvrClientKitLibrary.osvrClientInit(defaultNullString, 0);
        VRinput = new OSVRInput();
        initSuccess = context != null && VRinput.init();
        if( initSuccess ) {
            PointerByReference grabDisplay = new PointerByReference();
            byte retval = OsvrDisplayLibrary.osvrClientGetDisplay(context, grabDisplay);
            if( retval != 0 ) {
                System.out.println("OSVR Get Display Error: " + retval);
                initSuccess = false;
                return false;
            }
            displayConfig = new OSVR_DisplayConfig(grabDisplay.getValue());
            System.out.println("Waiting for the display to fully start up, including receiving initial pose update...");
            int i = 400;
            while (OsvrDisplayLibrary.osvrClientCheckDisplayStartup(displayConfig) != 0) {
                if( i-- < 0 ) {
                    System.out.println("Couldn't get display startup update in time, continuing anyway...");
                    break;
                }
                OsvrClientKitLibrary.osvrClientUpdate(context);
                try {
                    Thread.sleep(5);
                } catch(Exception e) { }
            }
            System.out.println("OK, display startup status is good!");
        }
        return initSuccess;
    }

    private PointerByReference grabRM, grabRMOGL, grabRIC;
    
    @Override
    public boolean initVRCompositor(boolean allowed) {
        if( !allowed || renderManager != null ) return false;
        wglGLFW = org.lwjgl.opengl.WGL.wglGetCurrentContext();
        glfwContext = org.lwjgl.glfw.GLFW.glfwGetCurrentContext();
        graphicsLibrary = new osvrrendermanageropengl.OSVR_GraphicsLibraryOpenGL.ByValue();
        graphicsLibrary.toolkit = null;
        grabRM = new PointerByReference(); grabRMOGL = new PointerByReference();
        byte retval = OsvrRenderManagerOpenGLLibrary.osvrCreateRenderManagerOpenGL(context, ("OpenGL").getBytes(), graphicsLibrary, grabRM, grabRMOGL);
        if( retval == 0 ) {
            renderManager = grabRM.getValue(); renderManagerOpenGL = grabRMOGL.getValue();
            if( renderManager == null || renderManagerOpenGL == null ) {
                System.out.println("Render Manager Created NULL, error!");
                return false;
            }
            OSVR_OpenResultsOpenGL openResults = new OSVR_OpenResultsOpenGL();
            retval = OsvrRenderManagerOpenGLLibrary.osvrRenderManagerOpenDisplayOpenGL(renderManager, openResults);
            if( retval == 0 ) {
                wglRM = org.lwjgl.opengl.WGL.wglGetCurrentContext();
                renderManagerContext = org.lwjgl.glfw.GLFW.glfwGetCurrentContext();
                org.lwjgl.glfw.GLFW.glfwMakeContextCurrent(glfwContext);
                if( org.lwjgl.opengl.WGL.wglShareLists(wglRM, wglGLFW) ) {
                    System.out.println("Context sharing success!");
                } else {
                    System.out.println("Context sharing problem...");
                    
                }
                OsvrClientKitLibrary.osvrClientUpdate(context);
                renderParams = new OSVR_RenderParams.ByValue();
                OsvrRenderManagerOpenGLLibrary.osvrRenderManagerGetDefaultRenderParams(renderParams);
                grabRIC = new PointerByReference();
                retval = OsvrRenderManagerOpenGLLibrary.osvrRenderManagerGetRenderInfoCollection(renderManager, renderParams, grabRIC);
                if( retval == 0 ) {
                    renderInfoCollection = grabRIC.getValue();
                    NativeSizeByReference grabNumInfo = new NativeSizeByReference();
                    OsvrRenderManagerOpenGLLibrary.osvrRenderManagerGetNumRenderInfoInCollection(renderInfoCollection, grabNumInfo);         
                    numRenderInfo = grabNumInfo.getValue();
                    eyeLeftInfo = new OSVR_RenderInfoOpenGL.ByValue();
                    eyeRightInfo = new OSVR_RenderInfoOpenGL.ByValue();
                    return true;
                }
                OsvrRenderManagerOpenGLLibrary.osvrDestroyRenderManager(renderManager);
                System.out.println("OSVR Render Manager Info Collection Error: " + retval);
                return false;
            }                
            OsvrRenderManagerOpenGLLibrary.osvrDestroyRenderManager(renderManager);
            System.out.println("OSVR Open Render Manager Display Error: " + retval);
            return false;
        }
        System.out.println("OSVR Create Render Manager Error: " + retval);
        return false;
    }

    @Override
    public OsvrClientKitLibrary.OSVR_ClientContext getVRSystem() {
        return context;
    }

    @Override
    public Pointer getCompositor() {
        return renderManager;
    }

    @Override
    public String getName() {
        return "OSVR";
    }

    @Override
    public VRInputAPI getVRinput() {
        return VRinput;
    }

    @Override
    public void _setFlipEyes(boolean set) {
        flipEyes = set;
    }

    @Override
    public void printLatencyInfoToConsole(boolean set) {
        
    }

    @Override
    public int getDisplayFrequency() {
        return 60; //debug display frequency
    }

    @Override
    public void destroy() {
        if( renderManager != null ) OsvrRenderManagerOpenGLLibrary.osvrDestroyRenderManager(renderManager);
        if( displayConfig != null ) OsvrDisplayLibrary.osvrClientFreeDisplay(displayConfig);
    }

    @Override
    public boolean isInitialized() {
        return initSuccess;
    }

    @Override
    public void reset() {
        // uhh.. how do you reset pose?
    }

    @Override
    public void getRenderSize(Vector2f store) {
        if( eyeLeftInfo == null || eyeLeftInfo.viewport.width == 0.0 ) {
            store.x = 1280f; store.y = 720f;            
        } else {
            store.x = (float)eyeLeftInfo.viewport.width;
            store.y = (float)eyeLeftInfo.viewport.height;
        }
    }
    
    public void getEyeInfo() {
        OsvrRenderManagerOpenGLLibrary.osvrRenderManagerGetRenderInfoFromCollectionOpenGL(renderInfoCollection, EYE_LEFT_SIZE, eyeLeftInfo);
        OsvrRenderManagerOpenGLLibrary.osvrRenderManagerGetRenderInfoFromCollectionOpenGL(renderInfoCollection, EYE_RIGHT_SIZE, eyeRightInfo);
    }

    @Override
    public float getFOV(int dir) {
        return 105f; //default FOV
    }

    @Override
    public float getInterpupillaryDistance() {
        return 0.065f; //default IPD
    }

    @Override
    public Quaternion getOrientation() {
        storeRot.set((float)hmdPose.rotation.data[0],
                     (float)hmdPose.rotation.data[1],
                     (float)hmdPose.rotation.data[2],
                     (float)hmdPose.rotation.data[3]);
        if( storeRot.equals(Quaternion.ZERO) ) storeRot.set(Quaternion.DIRECTION_Z);
        return storeRot;
    }

    @Override
    public Vector3f getPosition() {
        storePos.x = (float)hmdPose.translation.data[0];
        storePos.y = (float)hmdPose.translation.data[1];
        storePos.z = (float)hmdPose.translation.data[2];
        return storePos;
    }

    @Override
    public void getPositionAndOrientation(Vector3f storePos, Quaternion storeRot) {
        storePos.x = (float)hmdPose.translation.data[0];
        storePos.y = (float)hmdPose.translation.data[1];
        storePos.z = (float)hmdPose.translation.data[2];
        storeRot.set((float)hmdPose.rotation.data[0],
                     (float)hmdPose.rotation.data[1],
                     (float)hmdPose.rotation.data[2],
                     (float)hmdPose.rotation.data[3]);
        if( storeRot.equals(Quaternion.ZERO) ) storeRot.set(Quaternion.DIRECTION_Z);
    }

    @Override
    public void updatePose() {
        OsvrClientKitLibrary.osvrClientUpdate(context);
        OsvrDisplayLibrary.osvrClientGetViewerPose(displayConfig, FIRST_VIEWER, hmdPose.getPointer());
    }

    @Override
    public Matrix4f getHMDMatrixProjectionLeftEye(Camera cam) {
        if( true || eyeLeftInfo == null ) return cam.getProjectionMatrix(); //debug projection (OSVR gathered projection seems very off)
        if( eyeMatrix[EYE_LEFT] == null ) {
            eyeMatrix[EYE_LEFT] = new Matrix4f();
            eyeMatrix[EYE_LEFT].fromFrustum(cam.getFrustumNear(), cam.getFrustumFar(), 
                                            (float)eyeLeftInfo.projection.left,
                                            (float)eyeLeftInfo.projection.right,
                                            (float)eyeLeftInfo.projection.top,
                                            (float)eyeLeftInfo.projection.bottom, false);
        }
        return eyeMatrix[EYE_LEFT];
    }

    @Override
    public Matrix4f getHMDMatrixProjectionRightEye(Camera cam) {
        if( true || eyeRightInfo == null ) return cam.getProjectionMatrix(); //debug projection (OSVR gathered projection seems very off)
        if( eyeMatrix[EYE_RIGHT] == null ) {
            eyeMatrix[EYE_RIGHT] = new Matrix4f();
            eyeMatrix[EYE_RIGHT].fromFrustum(cam.getFrustumNear(), cam.getFrustumFar(), 
                                            (float)eyeRightInfo.projection.left,
                                            (float)eyeRightInfo.projection.right,
                                            (float)eyeRightInfo.projection.top,
                                            (float)eyeRightInfo.projection.bottom, false);
        }
        return eyeMatrix[EYE_RIGHT];
    }

    @Override
    public Vector3f getHMDVectorPoseLeftEye() {
        if( hmdPoseLeftEyeVec == null ) {
            hmdPoseLeftEyeVec = new Vector3f();
            hmdPoseLeftEyeVec.x = 0.065f * -0.5f;
            if( flipEyes == false ) hmdPoseLeftEyeVec.x *= -1f; // it seems these need flipping
        }
        return hmdPoseLeftEyeVec;
    }

    @Override
    public Vector3f getHMDVectorPoseRightEye() {
        if( hmdPoseRightEyeVec == null ) {
            hmdPoseRightEyeVec = new Vector3f();
            hmdPoseRightEyeVec.x = 0.065f * 0.5f;
            if( flipEyes == false ) hmdPoseRightEyeVec.x *= -1f; // it seems these need flipping
        }
        return hmdPoseRightEyeVec;
    }

    @Override
    public Vector3f getSeatedToAbsolutePosition() {
        return Vector3f.ZERO;
    }

    @Override
    public Matrix4f getHMDMatrixPoseLeftEye() {
        // not actually used internally...
        /*if( hmdPoseLeftEye != null ) {
            return hmdPoseLeftEye;
        } else {
            FloatBuffer mat = FloatBuffer.allocate(16);
            OsvrDisplayLibrary.osvrClientGetViewerEyeViewMatrixf(displayConfig, FIRST_VIEWER, (byte)EYE_LEFT,
                     (short)(OsvrMatrixConventionsLibrary.OSVR_MatrixVectorFlags.OSVR_MATRIX_COLVECTORS |
                             OsvrMatrixConventionsLibrary.OSVR_MatrixOrderingFlags.OSVR_MATRIX_COLMAJOR), tempfb);
            hmdPoseLeftEye = new Matrix4f(tempfb.array());
            return hmdPoseLeftEye;
        }*/
        return null;
    }

    @Override
    public Matrix4f getHMDMatrixPoseRightEye() {
        // not actually used internally...
        /*if( hmdPoseRightEye != null ) {
            return hmdPoseRightEye;
        } else {
            OsvrDisplayLibrary.osvrClientGetViewerEyeViewMatrixf(displayConfig, FIRST_VIEWER, (byte)EYE_RIGHT,
                     (short)(OsvrMatrixConventionsLibrary.OSVR_MatrixVectorFlags.OSVR_MATRIX_COLVECTORS |
                             OsvrMatrixConventionsLibrary.OSVR_MatrixOrderingFlags.OSVR_MATRIX_COLMAJOR), tempfb);
            hmdPoseRightEye = new Matrix4f(tempfb.array());
            return hmdPoseRightEye;
        }*/
        return null;
    }
    
    @Override
    public OpenVR.HMD_TYPE getType() {
        return OpenVR.HMD_TYPE.OSVR;
    }

}
