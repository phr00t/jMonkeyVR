/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package oculusvr.post;

import com.jme3.asset.AssetManager;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.shader.VarType;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import java.io.IOException;
import java.util.ArrayList;

public class FastSSAO extends Filter{
private Pass normalPass;
private Vector3f frustumCorner;
private Vector2f frustumNearFar;
// Wide area occlusion settings
private float sampleRadius = 3.0f;
private float intensity = 10.2f;
private float scale = 3.15f;
private float bias = 0.025f;

private Pass ssaoPass;

//private float downSampleFactor = 1f;
RenderManager renderManager;
ViewPort viewPort;

/**
* Create a Screen Space Ambient Occlusion Filter
*/
public FastSSAO() {
super("FastSSAO");
}

/**
* Create a Screen Space Ambient Occlusion Filter
* @param sampleRadius The radius of the area where random samples will be picked. default 5.1f
* @param intensity intensity of the resulting AO. default 1.2f
* @param scale distance between occluders and occludee. default 0.2f
* @param bias the width of the occlusion cone considered by the occludee. default 0.1f
*/
public FastSSAO(float sampleRadius, float intensity, float scale, float bias) {
this();
this.sampleRadius = sampleRadius;
this.intensity = intensity;
this.scale = scale;
this.bias = bias;
}

public FastSSAO(FastSSAO cloneFrom) {
    this();
    this.sampleRadius = cloneFrom.sampleRadius;
    this.intensity = cloneFrom.intensity;
    this.scale = cloneFrom.scale;
    this.bias = cloneFrom.bias;
}

@Override
protected boolean isRequiresDepthTexture() {
return true;
}

@Override
protected void postQueue(RenderQueue renderQueue) {
    Renderer r = renderManager.getRenderer();
    r.setFrameBuffer(normalPass.getRenderFrameBuffer());
    renderManager.getRenderer().clearBuffers(true, true, true);
    renderManager.setForcedTechnique("PreNormalPass");
    renderManager.renderViewPortQueues(viewPort, false);
    renderManager.setForcedTechnique(null);
    renderManager.getRenderer().setFrameBuffer(viewPort.getOutputFrameBuffer());
}

@Override
protected Material getMaterial() {
return material;
}

@Override
protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
this.renderManager = renderManager;
this.viewPort = vp;

int screenWidth = w;
int screenHeight = h;
postRenderPasses = new ArrayList<Pass>();

normalPass = new Pass();
normalPass.init(renderManager.getRenderer(), screenWidth, screenHeight, Format.RGBA8, Format.Depth);

frustumNearFar = new Vector2f();

float farY = (vp.getCamera().getFrustumTop() / vp.getCamera().getFrustumNear()) * vp.getCamera().getFrustumFar();
float farX = farY * ((float) screenWidth / (float) screenHeight);
frustumCorner = new Vector3f(farX, farY, vp.getCamera().getFrustumFar());
frustumNearFar.x = vp.getCamera().getFrustumNear();
frustumNearFar.y = vp.getCamera().getFrustumFar();

//ssao Pass
material = new Material(manager, "oculusvr/shaders/FastSSAO.j3md");
material.setTexture("Normals", normalPass.getRenderedTexture());

ssaoPass = new Pass() {
@Override
public boolean requiresDepthAsTexture() {
return true;
}
};
ssaoPass.init(renderManager.getRenderer(), screenWidth, screenHeight, Format.RGBA8, Format.Depth, 1, material);
ssaoPass.getRenderedTexture().setMinFilter(Texture.MinFilter.Trilinear);
ssaoPass.getRenderedTexture().setMagFilter(Texture.MagFilter.Bilinear);
postRenderPasses.add(ssaoPass);

material.setFloat("SampleRadius", sampleRadius);
material.setFloat("Intensity", intensity);
material.setFloat("Scale", scale);
material.setFloat("Bias", bias);


material.setVector3("FrustumCorner", frustumCorner);
material.setVector2("FrustumNearFar", frustumNearFar);
}

/**
* Return the wide area bias<br>
* see {@link  #setBias(float bias)}
* @return  bias
*/
public float getBias() {
return bias;
}

/**
* Sets the the width of the wide area occlusion cone considered by the occludee default is 0.025f
* @param bias
*/
public void setBias(float bias) {
this.bias = bias;
if (material != null) {
material.setFloat("Bias", bias);
}
}

/**
/**
* returns the ambient occlusion intensity
* @return intensity
*/
public float getIntensity() {
return intensity;
}

/**
* Sets the Ambient occlusion intensity default is 10.2f
* @param intensity
*/
public void setIntensity(float intensity) {
this.intensity = intensity;
if (material != null) {
material.setFloat("Intensity", intensity);
}

}


/**
* returns the sample radius<br>
* see {link setSampleRadius(float sampleRadius)}
* @return the sample radius
*/
public float getSampleRadius() {
return sampleRadius;
}

/**
* Sets the radius of the area where random samples will be picked dafault 3.0f
* @param sampleRadius
*/
public void setSampleRadius(float sampleRadius) {
this.sampleRadius = sampleRadius;
if (material != null) {
material.setFloat("SampleRadius", sampleRadius);
}

}

/**
* returns the scale<br>
* see {@link #setScale(float scale)}
* @return scale
*/
public float getScale() {
return scale;
}

/**
*
* Returns the distance between occluders and occludee. default 3.15f
* @param scale
*/
public void setScale(float scale) {
this.scale = scale;
if (material != null) {
material.setFloat("Scale", scale);
}
}


public void scaleSettings(float aoScale) {
    setBias(getBias()*aoScale);
    //setDetailBias(getDetailBias()*aoScale);
    setIntensity(getIntensity()*aoScale);
    //setDetailIntensity(getDetailIntensity()*aoScale);
    setScale(getScale()*aoScale);
    //setDetailScale(getDetailScale()*aoScale);
    setScale(getScale()*aoScale);
    //setDetailScale(getDetailScale()*aoScale);
    setSampleRadius(getSampleRadius()*aoScale);
    //setDetailSampleRadius(getDetailSampleRadius()*aoScale);
}

@Override
public void write(JmeExporter ex) throws IOException {
super.write(ex);

OutputCapsule oc = ex.getCapsule(this);
    oc.write(sampleRadius, "sampleRadius", 3.0f);
    oc.write(intensity, "intensity", 10.2f);
    oc.write(scale, "scale", 3.15f);
    oc.write(bias, "bias", 0.025f);
    //oc.write(sampleRadiusFD, "sampleRadiusFD", 0.55f);
    //oc.write(intensityFD, "intensityFD", 2.5f);
    //oc.write(scaleFD, "scaleFD", 1.15f);
    //oc.write(biasFD, "biasFD", 0.025f);
}

@Override
public void read(JmeImporter im) throws IOException {
super.read(im);
InputCapsule ic = im.getCapsule(this);
sampleRadius = ic.readFloat("sampleRadius", 3.0f);
intensity = ic.readFloat("intensity", 10.2f);
scale = ic.readFloat("scale", 3.15f);
bias = ic.readFloat("bias", 0.025f);

}
}