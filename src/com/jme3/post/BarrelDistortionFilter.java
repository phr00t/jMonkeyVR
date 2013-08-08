package com.jme3.post;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import oculusvr.input.HMDInfo;

/**
 *
 * @author reden
 */
public class BarrelDistortionFilter extends Filter{

    private Vector2f lensCenter;
    private Vector2f screenCenter;
    private Vector2f scale;
    private Vector2f scaleIn;
    private float eyeProjectionShift = 0.25f;
    private float sizeVert = 0.0935f;
    private float sizeHor = 0.14976f;
    private float eyeToScreenDistance = 0.041f;
    private float interPupillaryDistance = 0.0624f;
    private boolean isLeft = true;
    private float scaleFactor = 0.88f;
    private HMDInfo deviceInfo;
    private float projectionCenterOffset;
    
    public BarrelDistortionFilter(HMDInfo deviceInfo, boolean isLeft){
        this.deviceInfo = deviceInfo;
        this.isLeft = isLeft;
    }
    
    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int width, int height) {
        material = new Material(manager, "MatDefs/Post/BarrelDistortion.j3md");

        float aspectRatio = (float)(deviceInfo.getHResolution() * 0.5f) / (float)deviceInfo.getVResolution();

        float halfScreenDistance = (deviceInfo.getVScreenSize() * 0.5f);
        float yfov = 1.0f * FastMath.atan(halfScreenDistance/deviceInfo.getEyeToScreenDistance());

        vp.getCamera().setFrustumPerspective(FastMath.RAD_TO_DEG * yfov, aspectRatio, 0.1f, 10000f);
        float viewCenter = deviceInfo.getHScreenSize() * 0.25f;
//        Matrix4f projLeft = Matrix4f::Translation(projectionCenterOffset, 0, 0) * projCenter;
        
        
                eyeProjectionShift = viewCenter - deviceInfo.getLensSeparationDistance() * 0.5f;
        projectionCenterOffset = 4f * eyeProjectionShift / (deviceInfo.getHScreenSize());
        if(!isLeft){
            projectionCenterOffset = - projectionCenterOffset;
        } else {
        }
        //pixel.LensCenterX = x + (w + Distortion.XCenterOffset * 0.5f)*0.5f;
        lensCenter = new Vector2f(0.5f + projectionCenterOffset, 0.5f);
        
        Matrix4f mat = new Matrix4f();
        mat.setTranslation(projectionCenterOffset, 0 ,0);
        mat.multLocal(vp.getCamera().getProjectionMatrix());
        vp.getCamera().setProjectionMatrix(mat);
        
//        lensCenter = new Vector2f(xOffset + (0.5f + projectionCenterOffset * 0.5f) * 0.5f, 0.5f);
        float[] K = deviceInfo.getDistortionK();
        screenCenter = new Vector2f(0.5f, 0.5f);
        scale = new Vector2f(scaleFactor, scaleFactor * aspectRatio );
        scaleIn = new Vector2f(1f, 1f / aspectRatio );
        material.setVector2("LensCenter", lensCenter);
        material.setVector2("ScreenCenter", screenCenter);
        material.setVector2("Scale", scale);
        material.setVector2("ScaleIn", scaleIn);

        material.setVector4("HmdWarpParam", new Vector4f(K[0], K[1], K[2], K[3]));

//        material.setTexture("WarpTexture", manager.loadTexture("Textures/oculus/warp2.png"));
    }

    @Override
    protected Material getMaterial() {
        return material;
    }
    
    
}
