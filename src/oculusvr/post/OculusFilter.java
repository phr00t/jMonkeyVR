/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.post;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix4f;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.jme3.texture.Texture2D;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.FovPort;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.HmdDesc;
import com.oculusvr.capi.OvrLibrary;
import com.oculusvr.capi.OvrVector2i;
import com.oculusvr.capi.Posef;
import com.oculusvr.capi.Texture;
import com.oculusvr.capi.TextureHeader;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
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
    private EyeRenderDesc eyeRenderDesc;

    public OculusFilter(Hmd hmd, int eyeIndex) {
        this.hmd = hmd;
        hmdDesc = hmd.getDesc();
        this.eyeIndex = eyeIndex;
        eyeTexture = new Texture();
    }

    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        material = new Material(manager, "MatDefs/Post/Oculus.j3md");
        // JNA weirdness 1
        FovPort defaultEyeFov = eyeRenderDesc.Fov;

        FovPort fovPort = new FovPort();
        fovPort.DownTan = defaultEyeFov.DownTan;
        fovPort.UpTan = defaultEyeFov.UpTan;
        fovPort.LeftTan = defaultEyeFov.LeftTan;
        fovPort.RightTan = defaultEyeFov.RightTan;
        Matrix4f projMat = OculusRiftUtil.toMatrix4f(Hmd.getPerspectiveProjection(
                fovPort, 0.1f, 1000000f, true));
        vp.getCamera().setProjectionMatrix(projMat);
        TextureHeader eth = eyeTexture.Header;
        eth.TextureSize = hmd.getFovTextureSize(eyeIndex, fovPort, 1.0f);
        eth.RenderViewport.Size = eth.TextureSize;
        eth.RenderViewport.Pos = new OvrVector2i(0, 0);

    }

    @Override
    protected Material getMaterial() {
        return material;
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
    }

    public void setEyeRenderDesc(EyeRenderDesc desc) {
        this.eyeRenderDesc = desc;
    }
}
