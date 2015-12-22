/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.post;

import com.jme3.post.Filter.Pass;
import com.jme3.renderer.Caps;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Texture2D;
import jmevr.app.VRApplication;

/**
 *
 * @author phr00t
 */
public class PreNormalCaching {
    
    private static FrameBuffer cachedPreNormals;
    private static int lastNormalPassesCount, curCount;
    
    public static void getPreNormals(RenderManager renderManager, Pass normalPass, ViewPort viewPort) {
        curCount++;
        // do we already have a valid cache to set the framebuffer to?
        Renderer r = renderManager.getRenderer();
        if( cachedPreNormals != null ) {
            r.copyFrameBuffer(cachedPreNormals, normalPass.getRenderFrameBuffer(), false);
        } else {
            // lets make the prenormals
            r.setFrameBuffer(normalPass.getRenderFrameBuffer());
            renderManager.getRenderer().clearBuffers(true, true, true);
            if( renderManager.getRenderer().getCaps().contains(Caps.GLSL150) ) {
                renderManager.setForcedTechnique("PreNormalPass15");
            } else {
                renderManager.setForcedTechnique("PreNormalPass");                
            }
            renderManager.renderViewPortQueues(viewPort, false);
            renderManager.setForcedTechnique(null);
            // if we should cache this, do it now
            if( lastNormalPassesCount > 1 ) {
                cachedPreNormals = normalPass.getRenderFrameBuffer();
            }
        }
        renderManager.getRenderer().setFrameBuffer(viewPort.getOutputFrameBuffer());
    }
    
    public static void resetCache() {
        if( VRApplication.isInVR() == false ) {
            // only use this feature if we are NOT in VR
            // we can't use the same normal information for another eye,
            // because it will be different!
            lastNormalPassesCount = curCount;
        }
        cachedPreNormals = null;
        curCount = 0;
    }
    
}
