/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.post;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.texture.FrameBuffer;

/**
 *
 * @author reden
 */
public class OpenVRFilter extends Filter {

    public OpenVRFilter() {
        
    }

    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        material = new Material(manager, "jmevr/shaders/OpenVR.j3md");

    }

    @Override
    protected Material getMaterial() {
        return material;

    }
    
    @Override
    protected void preFrame(float tpf) {
        super.preFrame(tpf);
    }

    @Override
    protected void postFrame(RenderManager renderManager, ViewPort viewPort, FrameBuffer prevFilterBuffer, FrameBuffer sceneBuffer) {
        super.postFrame(renderManager, viewPort, prevFilterBuffer, sceneBuffer);
    }

    @Override
    protected void postFilter(Renderer r, FrameBuffer buffer) {
        super.postFilter(r, buffer);
    }
}
