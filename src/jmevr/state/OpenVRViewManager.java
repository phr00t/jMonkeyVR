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
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.Filter;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.SceneProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.util.TempVars;
import java.util.List;
import jmevr.app.VRApplication;
import jmevr.input.OpenVR;
import jmevr.input.VRHMD;
import jmevr.post.FastSSAO;
import jmevr.post.OpenVRFilter;
import jmevr.shadow.VRDirectionalLightShadowRenderer;
import jmevr.util.FilterUtil;
import jmevr.util.MeshUtil;
import jmevr.util.VRGuiNode;

/**
 *
 * @author reden
 */
public class OpenVRViewManager extends AbstractAppState {

    private VRApplication app;
    private Camera camLeft,camRight;
    private ViewPort viewPortLeft, viewPortRight;
    private Matrix4f transformMatrix, leftMatrix, rightMatrix;
    private FilterPostProcessor ppLeft, ppRight;
    
    private final static String LEFT_VIEW_NAME = "Left View";
    private final static String RIGHT_VIEW_NAME = "Right View";

    public OpenVRViewManager(VRApplication forVRApp){
        transformMatrix = new Matrix4f();
        app = forVRApp;
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
        Node distortionScene = new Node();
        Material leftMat = new Material(app.getAssetManager(), "jmevr/shaders/OpenVR.j3md");
        leftMat.setTexture("Texture", app.getRenderManager().getPreView(LEFT_VIEW_NAME).getOutputFrameBuffer().getColorBuffer().getTexture());
        Geometry leftEye = new Geometry("box", MeshUtil.setupDistortionMesh(0));
        leftEye.setMaterial(leftMat);
        distortionScene.attachChild(leftEye);
        
        Material rightMat = new Material(app.getAssetManager(), "jmevr/shaders/OpenVR.j3md");
        rightMat.setTexture("Texture", app.getRenderManager().getPreView(RIGHT_VIEW_NAME).getOutputFrameBuffer().getColorBuffer().getTexture());
        Geometry rightEye = new Geometry("box", MeshUtil.setupDistortionMesh(1));
        rightEye.setMaterial(rightMat);
        distortionScene.attachChild(rightEye);
        
        distortionScene.updateGeometricState();
        
        app.getViewPort().detachScene(app.getRootNode());
        app.getViewPort().attachScene(distortionScene);
    }
    
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        // first grab our observer's position & orientation
        Spatial obs = VRApplication.getObserver();
        if( obs == null ) {
            transformMatrix.set(Matrix4f.IDENTITY);
        } else {
            transformMatrix.setRotationQuaternion(obs.getWorldRotation());
            transformMatrix.setTranslation(obs.getWorldTranslation());
        }        
        // update the HMD's position & orientation
        VRApplication.getVRHardware().updatePose();
        Matrix4f posAndRot = ((OpenVR)VRApplication.getVRHardware()).getPositionAndOrientation();
        // combine the two for each eye
        TempVars tempVars = TempVars.get();
        // left eye
        tempVars.tempMat4.set(leftMatrix);
        tempVars.tempMat4.multLocal(posAndRot);
        tempVars.tempMat4.multLocal(transformMatrix);
        tempVars.tempMat4.toTranslationVector(tempVars.vect1);
        tempVars.tempMat4.toRotationQuat(tempVars.quat1);
        camLeft.setFrame(tempVars.vect1, tempVars.quat1);
        // right eye
        tempVars.tempMat4.set(rightMatrix);
        tempVars.tempMat4.multLocal(posAndRot);
        tempVars.tempMat4.multLocal(transformMatrix);
        tempVars.tempMat4.toTranslationVector(tempVars.vect1);
        tempVars.tempMat4.toRotationQuat(tempVars.quat1);
        camRight.setFrame(tempVars.vect1, tempVars.quat1);
        tempVars.release();
        
        // update GUI position?
        VRGuiNode vrgn = VRApplication.getVRGuiNode();
        if( vrgn != null && vrgn.getPositioningMode() != VRGuiNode.POSITIONING_MODE.MANUAL ) {
            VRApplication.getVRGuiNode().positionGui();
        }
    }
    
    private void setupCamerasAndViews() {              
        camLeft = app.getCamera().clone();        
        float origWidth = camLeft.getWidth();
        float origHeight = camLeft.getHeight();
        camLeft.setFrustumPerspective(VRApplication.getVRHardware().getFOV(), 
                                      origWidth / origHeight,
                                      camLeft.getFrustumNear(), camLeft.getFrustumFar());                       
        
        prepareCameraResolution(0, camLeft);
        camLeft.setLocation(Vector3f.ZERO);
        camLeft.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        camRight = camLeft.clone();
        prepareCameraResolution(1, camRight);
        viewPortLeft = setupViewBuffers(camLeft, LEFT_VIEW_NAME);
        viewPortRight = setupViewBuffers(camRight, RIGHT_VIEW_NAME);
        
        // setup gui
        VRApplication.getVRGuiNode().setupGui(viewPortLeft, viewPortRight, (int)origWidth, (int)origHeight);
        // make sure the gui node isn't in the distortion scene
        for(ViewPort v : app.getRenderManager().getPostViews()) {
            v.detachScene(VRApplication.getVRGuiNode());
        }
        
        leftMatrix = ((OpenVR)VRApplication.getVRHardware()).getHMDMatrixPoseEye(0);
        rightMatrix = ((OpenVR)VRApplication.getVRHardware()).getHMDMatrixPoseEye(1);
    }
    
    private ViewPort setupViewBuffers(Camera cam, String viewName){
        // create offscreen framebuffer
        FrameBuffer offBufferLeft = new FrameBuffer(cam.getWidth(), cam.getHeight(), 1);

        //setup framebuffer's texture
        Texture2D offTex = new Texture2D(cam.getWidth(), cam.getHeight(), Image.Format.RGBA8);
        offTex.setMinFilter(Texture.MinFilter.Trilinear);
        offTex.setMagFilter(Texture.MagFilter.Bilinear);

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
