/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.Filter;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.SceneProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.filters.TranslucentBucketFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.util.SafeArrayList;
import com.sun.jna.ptr.LongByReference;
import jmevr.app.VRApplication;
import static jmevr.app.VRApplication.isInVR;
import jmevr.input.OpenVR;
import jmevr.post.CartoonSSAO;
import jmevr.post.FastSSAO;
import jmevr.shadow.VRDirectionalLightShadowRenderer;
import jmevr.util.FilterUtil;
import jmevr.util.MeshUtil;
import jmevr.util.OpenVRUtil;
import jmevr.util.VRGuiNode;
import jopenvr.JOpenVRLibrary;

/**
 *
 * @author reden
 */
public class OpenVRViewManager {

    private VRApplication app;
    private Camera camLeft,camRight;
    private ViewPort viewPortLeft, viewPortRight;
    private FilterPostProcessor ppLeft, ppRight;
    
    private boolean mirrorEnabled;
    private int mirrorFrame;
    private float heightAdjustment;
    
    private Texture leftEyeTex, rightEyeTex;
    
    private final static String LEFT_VIEW_NAME = "Left View";
    private final static String RIGHT_VIEW_NAME = "Right View";

    public OpenVRViewManager(VRApplication forVRApp){
        app = forVRApp;
    }
    
    private int getRightTexId() {
        return (int)rightEyeTex.getImage().getId();
    }
    
    private int getLeftTexId() {
        return (int)leftEyeTex.getImage().getId();
    }
    
    public void setMirroring(boolean set) {
        mirrorEnabled = set;
    }
    
    public boolean getMirroring() {
        return mirrorEnabled;
    }
    
    public float getHeightAdjustment() {
        return heightAdjustment;
    }
    
    public void setHeightAdjustment(float amount) {
        heightAdjustment = amount;
    }
    
    public void postRender() {
        if( isInVR() ) {
            if( OpenVR.getVRCompositorInstance() != null ) {
                // using the compositor...
                JOpenVRLibrary.VR_IVRCompositor_Submit(OpenVR.getVRCompositorInstance(), JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left,
                                                       JOpenVRLibrary.GraphicsAPIConvention.GraphicsAPIConvention_API_OpenGL, getLeftTexId(), null);
                JOpenVRLibrary.VR_IVRCompositor_Submit(OpenVR.getVRCompositorInstance(), JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right,
                                                       JOpenVRLibrary.GraphicsAPIConvention.GraphicsAPIConvention_API_OpenGL, getRightTexId(), null);
                // mirroring?
                if( mirrorEnabled ) {
                    // mirror once every 3 frames, to prioritize performance for the VR headset
                    mirrorFrame = (mirrorFrame + 1) % 3;
                    if( mirrorFrame == 0 ) {
                        Renderer r = app.getRenderManager().getRenderer();
                        r.copyFrameBuffer(viewPortLeft.getOutputFrameBuffer(), app.getViewPort().getOutputFrameBuffer(), false);
                    }
                }
            }
        }        
    }
    
    public Camera getCamLeft() {
        return camLeft;
    }
    
    public Camera getCamRight() {
        return camRight;
    }
    
    public ViewPort getViewPortLeft() {
        return viewPortLeft;
    }
    
    public ViewPort getViewPortRight() {
        return viewPortRight;
    }
    
    public void initialize(VRApplication app) {            
        setupCamerasAndViews();
        
        setupVRScene();
                    
        moveScreenProcessingToEyes();
        
        if( OpenVR.getVRCompositorInstance() == null && OpenVR.getVRSystemInstance() != null ) {
            // tell IVR_System what window we are using (not sure this has any use)
            long hWnd = OpenVRUtil.getNativeWindow();            
            if( hWnd > 0 ) {
                LongByReference phWnd = new LongByReference();
                phWnd.setValue(hWnd);
                JOpenVRLibrary.VR_IVRSystem_AttachToWindow(OpenVR.getVRSystemInstance(), phWnd.getPointer());
            }
        }
        
        VRMouseManager.init();
        
        // update the pose to position the gui correctly on start
        update(0f);        
        VRApplication.getVRGuiNode().positionGui();
    }
    
    private void prepareCameraSize(Camera cam) {
        Vector2f size = new Vector2f();
        OpenVR vrhmd = VRApplication.getVRHardware();
        
        vrhmd.getRenderSize(size);
        
        if( size.x < app.getContext().getSettings().getWidth() ) {
            size.x = app.getContext().getSettings().getWidth();
        }
        if( size.y < app.getContext().getSettings().getHeight() ) {
            size.y = app.getContext().getSettings().getHeight();
        }
        if( cam.getWidth() != size.x || cam.getHeight() != size.y ) cam.resize((int)size.x, (int)size.y, true);
    }
    
    /**
     * Replaces rootNode as the main cameras scene with the distortion mesh
     */
    private void setupVRScene(){
        leftEyeTex = app.getRenderManager().getPreView(LEFT_VIEW_NAME).getOutputFrameBuffer().getColorBuffer().getTexture();
        rightEyeTex = app.getRenderManager().getPreView(RIGHT_VIEW_NAME).getOutputFrameBuffer().getColorBuffer().getTexture();
        
        // main viewport is either going to be a distortion scene or nothing
        // mirroring is handled by copying framebuffers
        app.getViewPort().detachScene(app.getRootNode());
        
        // only setup distortion scene if compositor isn't running
        if( OpenVR.getVRCompositorInstance() == null ) {
            Node distortionScene = new Node();
            Material leftMat = new Material(app.getAssetManager(), "jmevr/shaders/OpenVR.j3md");
            leftMat.setTexture("Texture", leftEyeTex);
            Geometry leftEye = new Geometry("box", MeshUtil.setupDistortionMesh(JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left));
            leftEye.setMaterial(leftMat);
            distortionScene.attachChild(leftEye);

            Material rightMat = new Material(app.getAssetManager(), "jmevr/shaders/OpenVR.j3md");
            rightMat.setTexture("Texture", rightEyeTex);
            Geometry rightEye = new Geometry("box", MeshUtil.setupDistortionMesh(JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right));
            rightEye.setMaterial(rightMat);
            distortionScene.attachChild(rightEye);

            distortionScene.updateGeometricState();

            app.getViewPort().attachScene(distortionScene);
        } else {
            // don't clear between mirroring frames
            app.getViewPort().setClearFlags(false, false, false);            
        }
    }
    
    //final & temp values for camera calculations
    private final Vector3f finalPosition = new Vector3f();
    private final Quaternion finalRotation = new Quaternion();
    private final Vector3f hmdPos = new Vector3f();
    private final Quaternion hmdRot = new Quaternion();
    
    public void update(float tpf) {
        
        // grab the observer
        Spatial obs = VRApplication.getObserver();
        // grab the OpenVR handle
        OpenVR dev = VRApplication.getVRHardware();
        // update the HMD's position & orientation
        dev.updatePose(tpf);
        Matrix4f posAndRot = dev.getPositionAndOrientation();
        // prepare head rotation & position for updateCamera
        posAndRot.toTranslationVector(hmdPos);               
        hmdPos.y = -hmdPos.y; // flip Y access to account for different convention
        OpenVRUtil.convertMatrix4toQuat(posAndRot, hmdRot);
        // hmdPos comes in rotated by hmdRot, fix that here
        finalRotation.set(hmdRot).inverseLocal().mult(hmdPos, hmdPos);
        if( obs != null ) {
            // update hmdPos based on obs rotation
            finalRotation.set(obs.getWorldRotation());
            finalRotation.mult(hmdPos, hmdPos);
            finalRotation.multLocal(hmdRot);
        } else {
            finalRotation.set(hmdRot);
        }
        finalizeCamera(dev.getHMDVectorPoseLeftEye(), obs, camLeft);
        finalizeCamera(dev.getHMDVectorPoseRightEye(), obs, camRight);
        
        // update the mouse?
        VRMouseManager.update(tpf);
        
        // update GUI position?
        VRGuiNode vrgn = VRApplication.getVRGuiNode();
        if( vrgn != null && (vrgn.wantsReposition || vrgn.getPositioningMode() != VRGuiNode.POSITIONING_MODE.MANUAL) ) {
            VRApplication.getVRGuiNode().positionGuiNow();
            VRApplication.getVRGuiNode().updateGeometricState();
        }
    }
    
    private void finalizeCamera(Vector3f eyePos, Spatial obs, Camera cam) {
        finalRotation.mult(eyePos, finalPosition);
        finalPosition.addLocal(hmdPos);
        if( obs != null ) finalPosition.addLocal(obs.getWorldTranslation());
        finalPosition.y += heightAdjustment;
        cam.setFrame(finalPosition, finalRotation);
    }
    
    /*
        handles moving filters from the main view to each eye
    */
    public void moveScreenProcessingToEyes() {
        syncScreenProcessing(VRApplication.getMainVRApp().getViewPort().getProcessors());
        VRApplication.getMainVRApp().getViewPort().clearProcessors();
    }
    
    /*
        sets the two views to use the list of processors
    */
    public void syncScreenProcessing(SafeArrayList<SceneProcessor> processors) {
        // setup post processing filters
        if( ppRight == null ) {
            ppRight = new FilterPostProcessor(app.getAssetManager());               
            ppLeft =  new FilterPostProcessor(app.getAssetManager());
        }
        // clear out all filters & processors, to start from scratch
        ppRight.removeAllFilters();
        ppLeft.removeAllFilters();
        viewPortLeft.clearProcessors();
        viewPortRight.clearProcessors();
        // add post processors we just made, which are empty
        viewPortLeft.addProcessor(ppLeft);
        viewPortRight.addProcessor(ppRight);
        // go through all of the filters in the processors list
        // add them to the left viewport processor & clone them to the right
        for(SceneProcessor sceneProcessor : processors) {
            if (sceneProcessor instanceof FilterPostProcessor) {
                for(Filter f : ((FilterPostProcessor)sceneProcessor).getFilterList() ) {
                    ppLeft.addFilter(f);
                    // clone to the right
                    Filter f2;
                    if(f instanceof FogFilter){
                        f2 = FilterUtil.cloneFogFilter((FogFilter)f); 
                    } else if (f instanceof CartoonSSAO ) {
                        f2 = new CartoonSSAO((CartoonSSAO)f);
                    } else if (f instanceof FastSSAO) {
                        f2 = new FastSSAO((FastSSAO)f);
                    } else if (f instanceof SSAOFilter){
                        f2 = FilterUtil.cloneSSAOFilter((SSAOFilter)f);
                    } else if (f instanceof DirectionalLightShadowFilter){
                        f2 = FilterUtil.cloneDirectionalLightShadowFilter(app.getAssetManager(), (DirectionalLightShadowFilter)f);
                    } else {
                        f2 = f; // dof, bloom, lightscattering etc.
                    }                    
                    ppRight.addFilter(f2);
                }
            } else if (sceneProcessor instanceof VRDirectionalLightShadowRenderer) {
                // shadow processing
                // TODO: make right shadow processor use same left shadow maps for performance
                VRDirectionalLightShadowRenderer dlsr = (VRDirectionalLightShadowRenderer) sceneProcessor;
                VRDirectionalLightShadowRenderer dlsrRight = dlsr.clone();
                dlsrRight.setLight(dlsr.getLight());
                viewPortRight.getProcessors().add(0, dlsrRight);
            }
        }
        // make sure each has a translucent filter renderer
        ppLeft.addFilter(new TranslucentBucketFilter());
        ppRight.addFilter(new TranslucentBucketFilter());
    }
    
    private void setupCamerasAndViews() {        
        // get desired frustrum from original camera
        Camera origCam = app.getCamera();
        float fFar = origCam.getFrustumFar();
        float fNear = origCam.getFrustumNear();
        // restore frustrum on distortion scene cam
        origCam.setFrustumFar(100f);
        origCam.setFrustumNear(1f);
        
        camLeft = origCam.clone();  
        float origWidth = camLeft.getWidth();
        float origHeight = camLeft.getHeight();
        
        camLeft.setFrustumPerspective(VRApplication.getVRHardware().getFOV(), 
                                      origWidth / origHeight,
                                      fNear, fFar);                       
        
        prepareCameraSize(camLeft);
        camLeft.setProjectionMatrix(VRApplication.getVRHardware().getHMDMatrixProjectionLeftEye(camLeft));
        viewPortLeft = setupViewBuffers(camLeft, LEFT_VIEW_NAME);
        
        camRight = camLeft.clone();
        prepareCameraSize(camRight);
        camRight.setProjectionMatrix(VRApplication.getVRHardware().getHMDMatrixProjectionRightEye(camRight));
        viewPortRight = setupViewBuffers(camRight, RIGHT_VIEW_NAME);
                
        // setup gui
        VRApplication.getVRGuiNode().setupGui(viewPortLeft, viewPortRight, (int)origWidth, (int)origHeight);
        // make sure the gui node isn't in the distortion scene
        for(ViewPort v : app.getRenderManager().getPostViews()) {
            v.detachScene(VRApplication.getVRGuiNode());
        }
        
        // call these to cache the results internally
        VRApplication.getVRHardware().getHMDMatrixPoseLeftEye();
        VRApplication.getVRHardware().getHMDMatrixPoseRightEye();
    }
    
    private ViewPort setupViewBuffers(Camera cam, String viewName){
        // create offscreen framebuffer
        FrameBuffer offBufferLeft = new FrameBuffer(cam.getWidth(), cam.getHeight(), 1);
        
        //setup framebuffer's texture
        Texture2D offTex = new Texture2D(cam.getWidth(), cam.getHeight(), Image.Format.RGBA8);
        offTex.setMinFilter(Texture.MinFilter.NearestNoMipMaps);
        offTex.setMagFilter(Texture.MagFilter.Nearest);

        //setup framebuffer to use texture
        offBufferLeft.setDepthBuffer(Image.Format.Depth);
        offBufferLeft.setColorTexture(offTex);        
        
        ViewPort viewPort = app.getRenderManager().createPreView(viewName, cam);
        viewPort.setClearFlags(true, true, true);
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);
        viewPort.attachScene(this.app.getRootNode());
        //set viewport to render to offscreen framebuffer
        viewPort.setOutputFrameBuffer(offBufferLeft);
        return viewPort;
    }
}
