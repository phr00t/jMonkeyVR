/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.asset.AssetManager;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.StencilOperation;
import com.jme3.material.RenderState.TestFunction;
import com.jme3.post.SceneProcessor;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.texture.FrameBuffer;

/**
 *
 * @author Phr00t
 */
public class DistanceOptimizer implements SceneProcessor {

    private ViewPort myViewport;
    private RenderManager myRM;
    private ViewPort leftEye, rightEye;
    private RenderState rs;
    
    public void setup(AssetManager am, ViewPort left, ViewPort right) {
        leftEye = left;
        rightEye = right;
    }
    
    @Override
    public void initialize(RenderManager rm, ViewPort vp) {
        myViewport = vp;
        myRM = rm;
        //rs = new RenderState();
    }

    @Override
    public void reshape(ViewPort vp, int i, int i1) {
        // nothing needed here
    }

    @Override
    public boolean isInitialized() {
        return myViewport != null && myRM != null;
    }

    @Override
    public void preFrame(float f) {
        myRM.getRenderer().copyFrameBuffer(leftEye.getOutputFrameBuffer(), rightEye.getOutputFrameBuffer(), true);
        //myRM.setForcedRenderState(rs);
    }

    @Override
    public void postQueue(RenderQueue rq) {
        // nothing
    }

    @Override
    public void postFrame(FrameBuffer fb) {
        //myRM.setForcedRenderState(null);
    }

    @Override
    public void cleanup() {
        // nothing
    }
    
}
