/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.post;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Matrix4f;
import com.jme3.post.Filter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.FovPort;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.HmdDesc;
import com.oculusvr.capi.OvrSizei;
import com.oculusvr.capi.OvrVector2i;
import com.oculusvr.capi.Posef;
import com.oculusvr.capi.Texture;
import com.oculusvr.capi.TextureHeader;
import oculusvr.util.OculusRiftUtil;

/**
 *
 * @author reden
 */
public class OculusFilter extends Filter {

    private int eyeIndex = 0;
    private Hmd hmd;
    private HmdDesc hmdDesc;
    private Texture eyeTexture;
    private Posef pose;
    int textureId = -1;
    private int eye; // redundant??
    private static int frameIndex;

    public OculusFilter(Hmd hmd, int eyeIndex) {
        this.hmd = hmd;
        hmdDesc = hmd.getDesc();
        this.eyeIndex = eyeIndex;
        eyeTexture = new Texture();
    }

    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        material = new Material(manager, "oculusvr/shaders/Oculus.j3md");
    }

    @Override
    protected Material getMaterial() {
        return material;
    }

    public void setEyeTextureSize(OvrSizei size) {
        TextureHeader eth = eyeTexture.Header;        
        eyeTexture.Header.TextureSize = size;
        eth.RenderViewport.Size = eth.TextureSize;
        eth.RenderViewport.Pos = new OvrVector2i(0, 0);        
    }
    
    @Override
    protected void preFrame(float tpf) {
        super.preFrame(tpf);
        if (textureId == -1) {
            // try to assign the texture id from the material
            if (material.getTextureParam("Texture") != null) {
                com.jme3.texture.Texture t = material.getTextureParam("Texture").getTextureValue();      
                int id = t.getImage().getId();
                eyeTexture.TextureId = id;
                textureId = id;
            }
        }
        if(eyeIndex == 0){
            hmd.beginFrame(frameIndex++);
        }
    }

    @Override
    protected void postFrame(RenderManager renderManager, ViewPort viewPort, FrameBuffer prevFilterBuffer, FrameBuffer sceneBuffer) {
        super.postFrame(renderManager, viewPort, prevFilterBuffer, sceneBuffer);
        // for both:
        eye = hmdDesc.EyeRenderOrder[eyeIndex];
        pose = hmd.beginEyeRender(eye);
    }

    @Override
    protected void postFilter(Renderer r, FrameBuffer buffer) {
        super.postFilter(r, buffer);

        // for both:
        hmd.endEyeRender(eye, pose, eyeTexture);
        
        if(eyeIndex == 1){
            hmd.endFrame();
        }
    }

}
