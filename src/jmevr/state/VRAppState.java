/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector2f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import jmevr.control.StereoCameraControl;
import com.jme3.post.Filter;
import com.jme3.post.SceneProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.filters.TranslucentBucketFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.scene.control.CameraControl;
import jmevr.util.FilterUtil;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.system.AppSettings;
import com.jme3.util.SafeArrayList;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.OvrSizei;
import com.oculusvr.capi.OvrVector3f;
import jmevr.shadow.VRDirectionalLightShadowRenderer;
import java.util.List;
import jmevr.app.VRApplication;
import jmevr.input.HMDInfo;
import jmevr.input.OculusRift;
import jmevr.input.OpenVR;
import jmevr.input.VRHMD;
import jmevr.post.FastSSAO;
import jmevr.post.OculusFilter;
import jmevr.post.OpenVRFilter;
import jmevr.util.VRGuiNode;
import jmevr.util.OculusRiftUtil;

/**
 *
 * @author reden
 */
public class VRAppState extends AbstractAppState {
    
    private SimpleApplication app;
    private FilterPostProcessor ppLeft, ppRight;
    private Filter filterLeft, filterRight;
    Camera camLeft,camRight;
    ViewPort viewPortLeft, viewPortRight;
    private StereoCameraControl camControl = new StereoCameraControl();
    private HMDInfo info;
    private boolean flipEyes;
    private VRGuiNode guiNode;
    
    public VRAppState(VRGuiNode guiNode, boolean flipEyes) {
        this.flipEyes = flipEyes;
        this.guiNode = guiNode;
    }
        
    public VRAppState(boolean flipEyes) {
        this(null, flipEyes);
    }
    
    public VRAppState(VRGuiNode guiNode) {
        this(guiNode, false);
    }   
    
    public VRAppState() {
        this(null, false);
        
    }
    
    public VRGuiNode getGuiNode() {
        return guiNode;
    }

    private Vector2f prepareCameraResolution(int eyeIndex, Camera cam) {
        Matrix4f projMat = null;
        Vector2f size = new Vector2f();
        VRHMD vrhmd = VRApplication.getVRHardware();
        
        if( vrhmd instanceof OculusRift ) {
            OculusRift or = (OculusRift)VRApplication.getVRHardware();
            projMat = OculusRiftUtil.toMatrix4f(Hmd.getPerspectiveProjection(
                      or.getEyeRenderDesc(eyeIndex).Fov, cam.getFrustumNear(), cam.getFrustumFar(), true));              
            OvrSizei osize = or.getHmd().getFovTextureSize(eyeIndex, or.getEyeRenderDesc(eyeIndex).Fov, 1.0f); 
            size.x = osize.w;
            size.y = osize.h;
        } else if( vrhmd instanceof OpenVR ) {
            projMat = ((OpenVR)vrhmd).getHMDMatrixProjectionEye(eyeIndex, cam);
            ((OpenVR)vrhmd).getRenderSize(size);
        }
        
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
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        VRHMD vrhmd = VRApplication.getVRHardware();
        viewPortLeft = app.getViewPort();
        camLeft = app.getCamera();
        
        camLeft.setFrustumPerspective(vrhmd.getFOV(), 
                                      (float) camLeft.getWidth() / camLeft.getHeight(),
                                      camLeft.getFrustumNear(), camLeft.getFrustumFar());                       
        
        if(camControl != null) camControl.setCamera(camLeft);
                
        info = VRApplication.getVRHardware().getHMDInfo();            
        
        AppSettings settings = this.app.getContext().getSettings();
        VRApplication.getVRHardware().initRendering(settings.getWidth(), settings.getHeight(), settings.getSamples());
        
        int origWidth = camLeft.getWidth();
        int origHeight = camLeft.getHeight();
        
        setupFiltersAndViews(vrhmd);
        
        if( guiNode != null ) guiNode.setupGui(viewPortLeft, viewPortRight, origWidth, origHeight);
                        
        float offset = info.getInterpupillaryDistance() * 0.5f;
        camControl.setCamHalfDistance(offset);
        
        cloneProcessors();        
        if( flipEyes ) camControl.swapCameras();

        app.setPauseOnLostFocus(false);
    }  
    
    private void setupFiltersAndViews(VRHMD vrhmd) {      
        Vector2f leftsize = prepareCameraResolution(0, camLeft);

        camRight = camLeft.clone();
        Vector2f rightsize = prepareCameraResolution(1, camRight);
        camControl.setCamera2(camRight);
        camControl.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //camLeft.setViewPort(0.0f, 0.5f, 0.0f, 1.0f);
        //camRight.setViewPort(0.5f, 1f, 0.0f, 1f);
        viewPortRight = app.getRenderManager().createMainView("Right viewport", camRight);
        viewPortRight.setClearFlags(true, true, true);
        viewPortRight.setBackgroundColor(viewPortLeft.getBackgroundColor());
        viewPortRight.attachScene(this.app.getRootNode());

        if( vrhmd instanceof OculusRift ) {
            OculusRift or = (OculusRift)VRApplication.getVRHardware();
            filterLeft=new OculusFilter(or.getHmd(), 0);
            ((OculusFilter)filterLeft).setEyeTextureSize(leftsize);
            filterRight =new OculusFilter(or.getHmd(), 1);
            ((OculusFilter)filterRight).setEyeTextureSize(rightsize);
        } else if( vrhmd instanceof OpenVR ) {
            filterLeft = new OpenVRFilter();
            filterRight = new OpenVRFilter();
        }

        ppRight =new FilterPostProcessor(app.getAssetManager());               
        ppLeft =new FilterPostProcessor(app.getAssetManager());
        
        ppLeft.addFilter(filterLeft);
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
        viewPortRight.addProcessor(ppRight);        
        ppRight.addFilter(filterRight);          
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);        
        if( VRApplication.getVRHardware() instanceof OculusRift ) {
            OculusRift or = ((OculusRift)VRApplication.getVRHardware());
            or.getHmd().beginFrame(or.frameIndex++);
            or.setEyePoses(or.getHmd().getEyePoses(or.frameIndex, or.getEyeOffsets()));
        }
    }
    
    @Override
    public void postRender() {
        super.postRender();
    }   
    
    public void setCameraControl(StereoCameraControl control){
        this.camControl = control;
        camRight = control.getCamera2();
    }
    
    public StereoCameraControl getCameraControl(){
        return camControl;
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
    
    public ViewPort getLeftViewPort(){
        return viewPortLeft;
    }
    
    public ViewPort getRightViewPort(){
        return viewPortRight;
    }
}
