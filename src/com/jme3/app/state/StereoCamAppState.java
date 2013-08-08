/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.app.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.StereoCameraControl;
import com.jme3.system.JmeSystem;
import com.jme3.system.Natives;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jme3.post.BarrelDistortionFilter;
import com.jme3.scene.control.CameraControl;
import oculusvr.input.HMDInfo;
import oculusvr.input.OculusRiftReader;

/**
 *
 * @author reden
 */
public class StereoCamAppState extends AbstractAppState{
    
    private SimpleApplication app;
    private FilterPostProcessor ppLeft, ppRight;
    private BarrelDistortionFilter filterLeft, filterRight;
    Camera camLeft,camRight;
    ViewPort viewPortLeft, viewPortRight, guiViewPortRight;
    private static OculusRiftReader oculus;
    private StereoCameraControl camControl = new StereoCameraControl();
    private HMDInfo info;
    
    static {
        String platform = JmeSystem.getPlatform().name();
        
        if(platform.startsWith("Win")){
            try {
                if(platform.endsWith("64")){
                    Natives.extractNativeLib("windows","OculusLib64", false, false);
                } else {
                    Natives.extractNativeLib("windows","OculusLib", false, false);
                }
                
            } catch (IOException ex) {
                System.out.println("failed to extract " + ex);
                Logger.getLogger(StereoCamAppState.class.getName()).log(Level.SEVERE, null, "Could not extract Oculus Rift library" + ex);
            }
        } else {
            try {
                throw new Exception("Sorry, platform not supported yet!");
            } catch (Exception ex) {
                Logger.getLogger(StereoCamAppState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            oculus = new OculusRiftReader();
            camControl.setOculus(oculus);
            info = oculus.getHMDInfo();
            
        } catch (Exception ex) {
            info = new HMDInfo();
            info.createFakeValues();
            Logger.getLogger(StereoCamAppState.class.getName()).log(Level.SEVERE, null, ex);
        }
        filterRight =new BarrelDistortionFilter(info, false);
        filterLeft=new BarrelDistortionFilter(info, true);
        ppRight =new FilterPostProcessor(app.getAssetManager());
        ppLeft =new FilterPostProcessor(app.getAssetManager());
        ppRight.addFilter(filterRight);
        ppLeft.addFilter(filterLeft);
        viewPortRight.addProcessor(ppRight);
        viewPortLeft.addProcessor(ppLeft);
        
        float offset = info.getInterpupillaryDistance() * 0.5f;
        camControl.setCamHalfDistance(offset);
        setupGuiViewports(0.045f);
    }
    
   
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        
        if(oculus != null){
            oculus.update();
        }
    }
    
    public void setCameraControl(StereoCameraControl control){
        this.camControl = control;
        camRight = control.getCamera2();
    }
    
    public StereoCameraControl getCameraControl(){
        return camControl;
    }
    
    public OculusRiftReader getOculus(){
        return oculus;
    }
    
    public static void setOculusRiftReader(OculusRiftReader orr){
        oculus = orr;
    }

    @Override
    public void cleanup() {
        super.cleanup();
        if(oculus != null){
            oculus.destroy();
        }
    }

    private void setupGuiViewports(float diff){
        Camera guiCam = app.getGuiViewPort().getCamera();
        
        ViewPort guiViewPortLeft = app.getGuiViewPort();
   
        Camera guiCamRight = guiCam.clone();
        
        guiCam.setViewPort(0.0f + diff, 0.5f + diff, 0.0f, 1.0f);
        guiCamRight.setViewPort(0.5f - diff, 1f - diff, 0.0f, 1f); // l,r,b,t

        guiViewPortRight = app.getRenderManager().createPostView("Gui Default Right", guiCamRight);
        guiViewPortRight.setClearFlags(false, false, false);
        guiViewPortRight.attachScene(((SimpleApplication)app).getGuiNode());
    }
    
}
