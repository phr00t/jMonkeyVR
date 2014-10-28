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
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.OvrSizei;
import com.oculusvr.capi.OvrVector2i;
import com.oculusvr.capi.OvrVector3f;
import com.oculusvr.capi.Posef;
import com.oculusvr.capi.Texture;
import com.oculusvr.capi.TextureHeader;
import oculusvr.input.OculusRift;

/**
 *
 * @author reden
 */
public class OculusFilter extends Filter {

    private int eyeIndex = 0;
    private Hmd hmd;
    private Texture eyeTexture;
    private Posef pose;
    int textureId = -1;
    int eye;
    private static int frameIndex;
    private static final Posef poses[] = (Posef[]) new Posef().toArray(2);
    private static Posef[] eyePoses;
    private static final Texture eyeTextures[] = (Texture[]) new Texture().toArray(2);
    private static final OvrVector3f eyeOffsets[] = (OvrVector3f[]) new OvrVector3f().toArray(2);
    private static int STATE = 0;

    public OculusFilter(Hmd hmd, int eyeIndex) {
        this.hmd = hmd;
        this.eyeIndex = eyeIndex;
        eyeTexture = eyeTextures[eyeIndex];
        EyeRenderDesc eyeRenderDesc = OculusRift.getEyeRenderDesc(eyeIndex);
        this.eyeOffsets[eyeIndex].x = eyeRenderDesc.HmdToEyeViewOffset.x;
        this.eyeOffsets[eyeIndex].y = eyeRenderDesc.HmdToEyeViewOffset.y;
        this.eyeOffsets[eyeIndex].z = eyeRenderDesc.HmdToEyeViewOffset.z;
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
        //System.out.println(eth.TextureSize.w + " " + eth.TextureSize.h);
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
        if (STATE == 0) {
            hmd.beginFrame(frameIndex++);
            eye = hmd.EyeRenderOrder[eyeIndex];
            eyePoses = hmd.getEyePoses(frameIndex, eyeOffsets);
            STATE++;
        }
    }

    @Override
    protected void postFrame(RenderManager renderManager, ViewPort viewPort, FrameBuffer prevFilterBuffer, FrameBuffer sceneBuffer) {
        super.postFrame(renderManager, viewPort, prevFilterBuffer, sceneBuffer);
        // for both:
        if (STATE != 0) {
            pose = eyePoses[eye];
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

    public Texture getEyeTexture() {
        return eyeTexture;
    }

    public Posef getPosef() {
        return pose;
    }
}
