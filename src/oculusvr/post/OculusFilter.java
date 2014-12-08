/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.post;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.texture.FrameBuffer;
import com.oculusvr.capi.GLTexture;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.OvrSizei;
import com.oculusvr.capi.OvrVector2i;
import com.oculusvr.capi.Posef;
import com.oculusvr.capi.TextureHeader;
import oculusvr.input.OculusRift;

/**
 *
 * @author reden
 */
public class OculusFilter extends Filter {

    private int eyeIndex = 0;
    private Hmd hmd;
    private GLTexture eyeTexture;
    private Posef pose;
    int textureId = -1;
    int eye;
    private static final Posef poses[] = (Posef[]) new Posef().toArray(2);
    private static final GLTexture eyeTextures[] = (GLTexture[]) new GLTexture().toArray(2);
    
    private static int STATE = 0;

    public OculusFilter(Hmd hmd, int eyeIndex) {
        this.hmd = hmd;
        this.eyeIndex = eyeIndex;
        eyeTexture = eyeTextures[eyeIndex];
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
        TextureHeader eth = eyeTexture.ogl.Header;
        eth.TextureSize.w = size.w;
        eth.TextureSize.h = size.h;
        eth.RenderViewport.Size.w = eth.TextureSize.w;
        eth.RenderViewport.Size.h = eth.TextureSize.h;
        eth.RenderViewport.Pos.x = 0;
        eth.RenderViewport.Pos.y = 0;
    }

    @Override
    protected void preFrame(float tpf) {
        super.preFrame(tpf);
        if (textureId == -1) {
            // try to assign the texture id from the material
            if (material.getTextureParam("Texture") != null) {
                com.jme3.texture.Texture t = material.getTextureParam("Texture").getTextureValue();
                int id = t.getImage().getId();
                eyeTexture.ogl.TexId = id;
                textureId = id;
            }
        }
        eye = hmd.EyeRenderOrder[eyeIndex];
        if (STATE == 0) {
            // anything to do?
            STATE++;
        }
    }

    @Override
    protected void postFrame(RenderManager renderManager, ViewPort viewPort, FrameBuffer prevFilterBuffer, FrameBuffer sceneBuffer) {
        super.postFrame(renderManager, viewPort, prevFilterBuffer, sceneBuffer);
        // for both:
        if (STATE != 0) {
            pose = OculusRift.getEyePoses()[eye];
            poses[eye].Orientation = pose.Orientation;
            poses[eye].Position = pose.Position;
            STATE++;
        }
    }

    @Override
    protected void postFilter(Renderer r, FrameBuffer buffer) {
        super.postFilter(r, buffer);
        STATE++;
        if (STATE == 5) {
            hmd.endFrame(poses, eyeTextures);
            STATE = 0;
        }
    }

    public GLTexture getEyeTexture() {
        return eyeTexture;
    }

}
