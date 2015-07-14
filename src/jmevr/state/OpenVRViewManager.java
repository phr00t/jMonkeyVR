/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.state;

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
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
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
import java.util.List;
import jmevr.app.VRApplication;
import static jmevr.app.VRApplication.isInVR;
import jmevr.input.OpenVR;
import jmevr.input.VRHMD;
import jmevr.post.FastSSAO;
import jmevr.post.OpenVRFilter;
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
public class OpenVRViewManager extends AbstractAppState {

    private VRApplication app;
    private Camera camLeft,camRight;
    private ViewPort viewPortLeft, viewPortRight;
    private Vector3f leftEye, rightEye;
    private Quaternion leftEyeRot, rightEyeRot;
    private FilterPostProcessor ppLeft, ppRight;
    
    private boolean mirrorEnabled;
    private int mirrorFrame;
    
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
    
    @Override
    public void postRender() {
        if( isInVR() && OpenVR.getVRCompositorInstance() != null ) {
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
    
    public ViewPort getViewPortLeft() {
        return viewPortLeft;
    }
    
    public ViewPort getViewPortRight() {
        return viewPortRight;
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
            
        setupCamerasAndViews();
        
        setupVRScene();
        
        // setup post processing filters
        // TODO: post processing
        /*ppRight = new FilterPostProcessor(app.getAssetManager());               
        ppLeft =  new FilterPostProcessor(app.getAssetManager());
        
        boolean hasTransFilter = false;
        SafeArrayList<SceneProcessor> processors = viewPortLeft.getProcessors();
        for(SceneProcessor sceneProcessor : processors){
            if(sceneProcessor instanceof FilterPostProcessor){
                for(Filter f : ((FilterPostProcessor)sceneProcessor).getFilterList() ) {
                    ppLeft.addFilter(f);
                    if( f instanceof TranslucentBucketFilter ) {
                        hasTransFilter = true;
                    }
                }
                viewPortLeft.removeProcessor(sceneProcessor);
                break;
            }
        }
        
        if( hasTransFilter == false ) {
            ppLeft.addFilter(new TranslucentBucketFilter());
        }
                
        viewPortLeft.addProcessor(ppLeft);
        viewPortRight.addProcessor(ppRight);        */
    }
    
    private Vector2f prepareCameraResolution(int eyeIndex, Camera cam) {
        Matrix4f projMat = null;
        Vector2f size = new Vector2f();
        VRHMD vrhmd = VRApplication.getVRHardware();
        
        projMat = ((OpenVR)vrhmd).getHMDMatrixProjectionEye(eyeIndex, cam);
        ((OpenVR)vrhmd).getRenderSize(size);
        
        if( size.x < app.getContext().getSettings().getWidth() ) {
            size.x = app.getContext().getSettings().getWidth();
        }
        if( size.y < app.getContext().getSettings().getHeight() ) {
            size.y = app.getContext().getSettings().getHeight();
        }
        if( cam.getWidth() != size.x || cam.getHeight() != size.y ) cam.resize((int)size.x, (int)size.y, true);
        
        cam.setProjectionMatrix(projMat);
        return size;   
    }
    
    /**
     * Replaces rootNode as the main cameras scene with the distortion mesh
     */
    private void setupVRScene(){
        app.getFlyByCamera().setEnabled(false);
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
            Geometry leftEye = new Geometry("box", MeshUtil.setupDistortionMesh(0));
            leftEye.setMaterial(leftMat);
            distortionScene.attachChild(leftEye);

            Material rightMat = new Material(app.getAssetManager(), "jmevr/shaders/OpenVR.j3md");
            rightMat.setTexture("Texture", rightEyeTex);
            Geometry rightEye = new Geometry("box", MeshUtil.setupDistortionMesh(1));
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
    private final Vector3f tempHead = new Vector3f();
    private final Quaternion tempHeadRot = new Quaternion();
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        // grab the observer
        Spatial obs = VRApplication.getObserver();
        // update the HMD's position & orientation
        VRApplication.getVRHardware().updatePose(tpf);
        Matrix4f posAndRot = ((OpenVR)VRApplication.getVRHardware()).getPositionAndOrientation();
        // prepare head rotation & position for updateCamera
        OpenVRUtil.convertMatrix4toQuat(posAndRot, tempHeadRot);
        posAndRot.toTranslationVector(tempHead);
        tempHead.y = -tempHead.y; // why is Y position flipped? who knows! corrected here
        // combine the two for each eye
        updateCamera(camLeft, obs, leftEye, leftEyeRot);
        updateCamera(camRight, obs, rightEye, rightEyeRot);
        
        // update GUI position?
        VRGuiNode vrgn = VRApplication.getVRGuiNode();
        if( vrgn != null && vrgn.getPositioningMode() != VRGuiNode.POSITIONING_MODE.MANUAL ) {
            VRApplication.getVRGuiNode().positionGui();
        }
    }
    
    private void updateCamera(Camera cam, Spatial obs, Vector3f eyePos, Quaternion eyeRot) {
        finalPosition.set(eyePos);
        finalRotation.set(eyeRot);
        finalPosition.addLocal(tempHead);
        finalRotation.multLocal(tempHeadRot);
        if( obs != null ) {
            finalPosition.addLocal(obs.getWorldTranslation());
            finalRotation.multLocal(obs.getWorldRotation());
        }
        cam.setFrame(finalPosition, finalRotation);
    }
    
    public Vector3f getFinalPosition() {
        return finalPosition;
    }
    
    public Quaternion getFinalRotation() {
        return finalRotation;
    }
    
    private void setupCamerasAndViews() {
        camLeft = app.getCamera().clone();        
        float origWidth = camLeft.getWidth();
        float origHeight = camLeft.getHeight();
        camLeft.setFrustumPerspective(VRApplication.getVRHardware().getFOV(), 
                                      origWidth / origHeight,
                                      camLeft.getFrustumNear(), camLeft.getFrustumFar());                       
        
        prepareCameraResolution(JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left, camLeft);
        viewPortLeft = setupViewBuffers(camLeft, LEFT_VIEW_NAME);
        
        camRight = camLeft.clone();
        prepareCameraResolution(JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right, camRight);
        viewPortRight = setupViewBuffers(camRight, RIGHT_VIEW_NAME);
        
        // setup gui
        VRApplication.getVRGuiNode().setupGui(viewPortLeft, viewPortRight, (int)origWidth, (int)origHeight);
        // make sure the gui node isn't in the distortion scene
        for(ViewPort v : app.getRenderManager().getPostViews()) {
            v.detachScene(VRApplication.getVRGuiNode());
        }
        
        // eyes were swapped for some reason.. that is why they are swapped here to correct
        Matrix4f leftMatrix = ((OpenVR)VRApplication.getVRHardware()).getHMDMatrixPoseEye(JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Right);
        Matrix4f rightMatrix = ((OpenVR)VRApplication.getVRHardware()).getHMDMatrixPoseEye(JOpenVRLibrary.Hmd_Eye.Hmd_Eye_Eye_Left);
        leftEyeRot = leftMatrix.toRotationQuat();
        leftEye = leftMatrix.toTranslationVector();
        rightEyeRot = rightMatrix.toRotationQuat();
        rightEye = rightMatrix.toTranslationVector();
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
        
    private void cloneProcessors(){
        List<SceneProcessor> processors = viewPortLeft.getProcessors();
        for(SceneProcessor sp: processors){
            if(sp instanceof FilterPostProcessor){
                FilterPostProcessor fpp1 = (FilterPostProcessor) sp;
                OpenVRFilter bdf = ppRight.getFilter(OpenVRFilter.class);
                ppRight.removeFilter(bdf);
                for(Filter filter: fpp1.getFilterList()){
                                        
                    Filter f2 = null;
                    if(filter instanceof FogFilter){
                        f2 = FilterUtil.cloneFogFilter((FogFilter)filter);
                        
                    } 
                    else if (filter instanceof FastSSAO) {
                        f2 = new FastSSAO((FastSSAO)filter);
                    }
                    //else if (filter instanceof WaterFilter){
                    //    f2 = ((WaterFilter)filter) //doesn't seem to be a clone function ready to go?
                    //} 
                    else if (filter instanceof SSAOFilter){
                        f2 = FilterUtil.cloneSSAOFilter((SSAOFilter)filter);
                    } else if (filter instanceof DirectionalLightShadowFilter){
                        f2 = FilterUtil.cloneDirectionalLightShadowFilter(app.getAssetManager(), (DirectionalLightShadowFilter)filter);
                    } 
                    else if (!(filter instanceof OpenVRFilter)){
                        f2 = filter; // dof, bloom, lightscattering
                    }
                    
                    if(f2 != null) ppRight.addFilter(f2);                    
                }
                ppRight.addFilter(bdf);
            } else if (sp instanceof VRDirectionalLightShadowRenderer){
                VRDirectionalLightShadowRenderer dlsr = (VRDirectionalLightShadowRenderer) sp;
                
                VRDirectionalLightShadowRenderer dlsrRight = dlsr.clone();
                dlsrRight.setLight(dlsr.getLight());
                
                viewPortRight.getProcessors().add(0, dlsrRight);
            }
        }
    }    
}
