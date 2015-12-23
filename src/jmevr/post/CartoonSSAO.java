/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package jmevr.post;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.texture.Image.Format;

public class CartoonSSAO extends Filter{
    private Pass normalPass;
    private Vector3f frustumCorner;
    private Vector2f frustumNearFar;
    // Wide area occlusion settings
    private float downsample = 1f, applyDistance = 0.0005f;

    //private float downSampleFactor = 1f;
    RenderManager renderManager;
    ViewPort viewPort;

    /**
    * Create a Screen Space Ambient Occlusion Filter
    */
    public CartoonSSAO() {
        super("CartoonSSAO");
    }

    /**
    * Create a Screen Space Ambient Occlusion Filter
    * @param downsample factor to divide resolution by for filter, >1 increases speed but degrades quality
    */
    public CartoonSSAO(float downsample) {
        this();
        this.downsample = downsample;
    }
    
    public CartoonSSAO(CartoonSSAO cloneFrom) {
        this(cloneFrom.downsample);
    }

    @Override
    protected boolean isRequiresDepthTexture() {
        return true;
    }

    @Override
    protected void postQueue(RenderQueue renderQueue) {
        PreNormalCaching.getPreNormals(renderManager, normalPass, viewPort);
    }

    public void setDownsampling(float downsample) {
        this.downsample = downsample;
    }
    
    public float getDownsampling() {
        return this.downsample;
    }

    @Override
    protected Material getMaterial() {
        return material;
    }

    public void setDistance(float dist) {
        applyDistance = dist;
        if( material != null ) material.setFloat("Distance", dist);
    }
    
    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        this.renderManager = renderManager;
        this.viewPort = vp;

        int screenWidth = Math.round(w / downsample);
        int screenHeight = Math.round(h / downsample);

        normalPass = new Pass();
        normalPass.init(renderManager.getRenderer(), screenWidth, screenHeight, Format.RGBA8, Format.Depth);

        frustumNearFar = new Vector2f();

        float farY = (vp.getCamera().getFrustumTop() / vp.getCamera().getFrustumNear()) * vp.getCamera().getFrustumFar();
        float farX = farY * ((float) screenWidth / (float) screenHeight);
        frustumCorner = new Vector3f(farX, farY, vp.getCamera().getFrustumFar());
        frustumNearFar.x = vp.getCamera().getFrustumNear();
        frustumNearFar.y = vp.getCamera().getFrustumFar();

        //ssao Pass
        material = new Material(manager, "jmevr/shaders/CartoonSSAO.j3md");
        material.setTexture("Normals", normalPass.getRenderedTexture());

        material.setVector3("FrustumCorner", frustumCorner);
        material.setVector2("FrustumNearFar", frustumNearFar);
        material.setFloat("Distance", applyDistance);
    }

}