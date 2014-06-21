/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import oculusvr.control.StereoCameraControl;
import com.jme3.system.JmeSystem;
import com.jme3.system.Natives;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oculusvr.post.BarrelDistortionFilter;
import com.jme3.post.Filter;
import com.jme3.post.SceneProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.scene.control.CameraControl;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.water.WaterFilter;
import com.oculusvr.capi.HmdDesc;
import com.oculusvr.capi.Posef;
import com.oculusvr.capi.FrameTiming;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.SensorState;
import java.util.List;
import oculusvr.input.HMDInfo;
import oculusvr.input.OculusRift;
import oculusvr.post.OculusFilter;
import oculusvr.shadow.OculusDirectionalLightShadowRenderer;
import oculusvr.util.FilterUtil;

/**
 *
 * @author reden
 */
public class OVRAppState extends AbstractAppState{
    
    private SimpleApplication app;
    private FilterPostProcessor ppLeft, ppRight;
    private OculusFilter filterLeft, filterRight;
    Camera camLeft,camRight;
    ViewPort viewPortLeft, viewPortRight, guiViewPortRight;
//    private static OculusRiftReader oculus;
    private StereoCameraControl camControl = new StereoCameraControl();
    private HMDInfo info;
    private Hmd ovrHmd;
    private HmdDesc hmdDesc;
    
    private Posef pose;
    private int frameIndex;
    
    static {
        
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        
        viewPortLeft = app.getViewPort();
        camLeft = app.getCamera();
        if(camControl != null){
            camControl.setCamera(camLeft);
        }
        
        if(camRight == null){
            camRight = camLeft.clone();
            camControl.setCamera2(camRight);
            
        }
        camControl.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        camLeft.setViewPort(0.0f, 0.5f, 0.0f, 1.0f);
        camRight.setViewPort(0.5f, 1f, 0.0f, 1f);
        viewPortRight = app.getRenderManager().createPostView("Right viewport", camRight);
        viewPortRight.setClearFlags(true, true, true);
        viewPortRight.attachScene(((SimpleApplication)app).getRootNode());

        try {
            OculusRift.initialize();
            info = OculusRift.getHMDInfo();

            ovrHmd = OculusRift.loadedHmd;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            info = new HMDInfo();
            info.createFakeValues();
            Logger.getLogger(OVRAppState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        filterLeft=new OculusFilter(ovrHmd, 0);
        filterLeft.setEyeRenderDesc(OculusRift.getEyeRenderDesc(0));
        filterRight =new OculusFilter(ovrHmd, 1);
        filterRight.setEyeRenderDesc(OculusRift.getEyeRenderDesc(1));
        ppRight =new FilterPostProcessor(app.getAssetManager());
        for(SceneProcessor sceneProcessor : viewPortLeft.getProcessors()){
            if(sceneProcessor instanceof FilterPostProcessor){
                ppLeft = (FilterPostProcessor) sceneProcessor;
                break;
            }
        }
        if(ppLeft == null){
            ppLeft =new FilterPostProcessor(app.getAssetManager());
            viewPortLeft.addProcessor(ppLeft);
        }
        ppLeft.addFilter(filterLeft);
        ppRight.addFilter(filterRight);
        
        viewPortRight.addProcessor(ppRight);
        
        System.out.println(info.getInterpupillaryDistance());
        float offset = info.getInterpupillaryDistance() * 0.5f;
        camControl.setCamHalfDistance(offset);
        setupGuiViewports(0.045f);
        
        cloneProcessors();
    }
    
   
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        
        frameIndex++;

        ovrHmd.beginFrame(frameIndex++);
        FrameTiming frameTiming = ovrHmd.getFrameTiming(frameIndex);

        SensorState state = ovrHmd.getSensorState(frameTiming.ScanoutMidpointSeconds);
        pose = state.Predicted.Pose;
    }

    @Override
    public void postRender() {
        super.postRender();
        ovrHmd.endFrame();
    }
    
    
    
    public void setCameraControl(StereoCameraControl control){
        this.camControl = control;
        camRight = control.getCamera2();
    }
    
    public StereoCameraControl getCameraControl(){
        return camControl;
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
        OculusRift.destroy();
    }

    
    private void setupGuiViewports(float diff){
        ViewPort guiViewPortLeft = app.getGuiViewPort();
        Camera guiCamLeft = guiViewPortLeft.getCamera();
        
        
   
        Camera guiCamRight = guiCamLeft.clone();
        
        guiCamLeft.setViewPort(0.0f + diff, 0.5f + diff, 0.0f, 1.0f);
        guiCamRight.setViewPort(0.5f - diff, 1f - diff, 0.0f, 1f); // l,r,b,t

        guiViewPortRight = app.getRenderManager().createPostView("Gui Default Right", guiCamRight);
        guiViewPortRight.setClearFlags(false, false, false);
        guiViewPortRight.attachScene(((SimpleApplication)app).getGuiNode());
        
//        List<SceneProcessor> processors = guiViewPortLeft.getProcessors();
//        for(SceneProcessor sp: processors){
//            if(sp instanceof NiftyJmeDisplay){
//                
//                ClonedNiftyJmeDisplay niftyRight = new ClonedNiftyJmeDisplay((NiftyJmeDisplay)sp,
//                        viewPortRight);
//                guiViewPortRight.addProcessor(niftyRight);
//                ((NiftyJmeDisplay)sp).reshape(guiViewPortLeft, guiCamLeft.getWidth(), guiCamRight.getHeight());
//            }
//        }
    }
    
    public void cloneProcessors(){
        List<SceneProcessor> processors = viewPortLeft.getProcessors();
        for(SceneProcessor sp: processors){
            if(sp instanceof FilterPostProcessor){
                FilterPostProcessor fpp1 = (FilterPostProcessor) sp;
                OculusFilter bdf = ppRight.getFilter(OculusFilter.class);
                for(Filter filter: fpp1.getFilterList()){
                    
                    
                    Filter f2 = null;
                    if(filter instanceof FogFilter){
                        f2 = FilterUtil.cloneFogFilter((FogFilter)filter);
                        
                    } 
                    //else if (filter instanceof WaterFilter){
                    //    f2 = ((WaterFilter)filter).clone(); //doesn't seem to be a clone function ready to go?
                    //} 
                    else if (filter instanceof SSAOFilter){
                        f2 = FilterUtil.cloneSSAOFilter((SSAOFilter)filter);
                    } else if (filter instanceof DirectionalLightShadowFilter){
                        f2 = FilterUtil.cloneDirectionalLightShadowFilter(app.getAssetManager(), (DirectionalLightShadowFilter)filter); //new DirectionalLightShadowFilter(app.getAssetManager(), 512, 3);//
//                        ((DirectionalLightShadowFilter)f2).setLight(((DirectionalLightShadowFilter)filter).getLight());
                    } 
                    else if (!(filter instanceof OculusFilter)){
                        f2 = filter; // dof, bloom, lightscattering
                    }
                    
                    if(f2 != null){
                        ppRight.removeFilter(bdf);
                        ppRight.addFilter(f2);
                        ppRight.addFilter(bdf);
                    }
                    
                }
            } else if (sp instanceof OculusDirectionalLightShadowRenderer){
                OculusDirectionalLightShadowRenderer dlsr = (OculusDirectionalLightShadowRenderer) sp;
                
                OculusDirectionalLightShadowRenderer dlsrRight = dlsr.clone();
                dlsrRight.setLight(dlsr.getLight());
                
                viewPortRight.addProcessor(dlsrRight);
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
