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
private Vector3f[] samples = {
new Vector3f(0.5f, 0.0f, 0.5f),
new Vector3f(-0.5f, 0.0f, 0.5f),
new Vector3f(0.0f, 0.5f, 0.5f),
new Vector3f(0.0f, -0.5f, 0.5f),
new Vector3f(0.5f, 0.0f, 0.0f),
new Vector3f(-0.5f, 0.0f, 0.0f),
new Vector3f(0.0f, 0.5f, 0.0f),
new Vector3f(0.0f, -0.5f, 0.0f),
new Vector3f(0.5f, 0.0f, -0.5f),
new Vector3f(-0.5f, 0.0f, -0.5f),
new Vector3f(0.0f, 0.5f, -0.5f),
new Vector3f(0.0f, -0.5f, -0.5f)
};
// Wide area occlusion settings
private float sampleRadius = 3.0f;
private float intensity = 10.2f;
private float scale = 3.15f;
private float bias = 0.025f;

// Fine detail occlusion settings
/*private boolean enableFD = false;
private float sampleRadiusFD = 0.55f;
private float intensityFD = 2.5f;
private float scaleFD = 1.15f;
private float biasFD = 0.025f;*/

// Distance falloff
//private boolean useDistanceFalloff = false;
//private float falloffStartDistance = 800f, falloffRate = 2.0f;

//private boolean useSmoothing = false;
//private boolean smoothMore = false;
//private boolean useOnlyAo = false;
//private boolean useAo = true;
private Material ssaoMat;
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
ssaoMat = new Material(manager, "oculusvr/shaders/FastSSAO.j3md");
ssaoMat.setTexture("Normals", normalPass.getRenderedTexture());

ssaoPass = new Pass() {
@Override
public boolean requiresDepthAsTexture() {
return true;
}
};
ssaoPass.init(renderManager.getRenderer(), screenWidth, screenHeight, Format.RGBA8, Format.Depth, 1, ssaoMat);
ssaoPass.getRenderedTexture().setMinFilter(Texture.MinFilter.Trilinear);
ssaoPass.getRenderedTexture().setMagFilter(Texture.MagFilter.Bilinear);
postRenderPasses.add(ssaoPass);

//float xScale = 1.0f / w;
//float yScale = 1.0f / h;
//float blurScale = 6.75f;

material = new Material(manager, "oculusvr/shaders/FastSSAOBlur.j3md");
material.setTexture("SSAOMap", ssaoPass.getRenderedTexture());
//material.setVector2("FrustumNearFar", frustumNearFar);
//material.setBoolean("UseAo", useAo);
//material.setBoolean("UseOnlyAo", useOnlyAo);
//material.setBoolean("UseSmoothing", useSmoothing);
//material.setBoolean("SmoothMore", smoothMore);
//material.setFloat("XScale", blurScale * xScale);
//material.setFloat("YScale", blurScale * yScale);

ssaoMat.setFloat("SampleRadius", sampleRadius);
ssaoMat.setFloat("Intensity", intensity);
ssaoMat.setFloat("Scale", scale);
ssaoMat.setFloat("Bias", bias);

/*ssaoMat.setBoolean("EnableFD", enableFD);
ssaoMat.setFloat("SampleRadiusFD", sampleRadiusFD);
ssaoMat.setFloat("IntensityFD", intensityFD);
ssaoMat.setFloat("ScaleFD", scaleFD);
ssaoMat.setFloat("BiasFD", biasFD);*/

/*ssaoMat.setBoolean("UseDistanceFalloff", useDistanceFalloff);
ssaoMat.setFloat("FalloffStartDistance", falloffStartDistance);
ssaoMat.setFloat("FalloffRate", falloffRate);*/

ssaoMat.setVector3("FrustumCorner", frustumCorner);
ssaoMat.setVector2("FrustumNearFar", frustumNearFar);
ssaoMat.setParam("Samples", VarType.Vector3Array, samples);
}

/**
* Enables fine detail pass for help in blending out artifacting in the wider area pass without losing detail
* @param useDetailPass
*/
/*public void setUseDetailPass(boolean useDetailPass) {
this.enableFD = useDetailPass;
if (ssaoMat != null) {
ssaoMat.setBoolean("EnableFD", useDetailPass);
}
}*/

/**
* Returns the use fine detail setting
* @return enableFD
*/
//public boolean getUseDetailPass() {
//return this.enableFD;
//}

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
if (ssaoMat != null) {
ssaoMat.setFloat("Bias", bias);
}
}

/**
* Return the fine detail bias<br>
* see {@link  #setDetailBias(float biasFD)}
* @return  biasFD
*/
//public float getDetailBias() {
//return biasFD;
//}

/**
* Sets the the width of the fine detail occlusion cone considered by the occludee default is 0.025f
* @param biasFD
*/
//public void setDetailBias(float biasFD) {
//this.biasFD = biasFD;
//if (ssaoMat != null) {
//ssaoMat.setFloat("BiasFD", biasFD);
//}
//}

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
if (ssaoMat != null) {
ssaoMat.setFloat("Intensity", intensity);
}

}

/**
* returns the fine detail ambient occlusion intensity
* @return intensityFD
*/
//public float getDetailIntensity() {
//return intensityFD;
//}

/**
* Sets the fine detail ambient occlusion intensity default is 2.5f
* @param intensityFD
*/
/*public void setDetailIntensity(float intensityFD) {
this.intensityFD = intensityFD;
if (ssaoMat != null) {
ssaoMat.setFloat("IntensityFD", intensityFD);
}
}*/

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
if (ssaoMat != null) {
ssaoMat.setFloat("SampleRadius", sampleRadius);
}

}

/**
* returns the sample radius<br>
* see {link setDetailSampleRadius(float sampleRadiusFD)}
* @return the sample radius for detail pass
*/
/*public float getDetailSampleRadius() {
return sampleRadiusFD;
}*/

/**
* Sets the radius of the area where random samples will be picked for fine detail pass dafault 0.55f
* @param sampleRadiusFD
*/
/*public void setDetailSampleRadius(float sampleRadiusFD) {
this.sampleRadiusFD = sampleRadiusFD;
if (ssaoMat != null) {
ssaoMat.setFloat("SampleRadiusFD", sampleRadiusFD);
}

}*/

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
if (ssaoMat != null) {
ssaoMat.setFloat("Scale", scale);
}
}

/**
* returns the detail pass scale<br>
* see {@link #setDetailScale(float scaleFD)}
* @return scaleFD
*/
//public float getDetailScale() {
//return scaleFD;
//}

/**
*
* Returns the distance between detail pass occluders and occludee. default 1.55f
* @param scaleFD
*/
/*public void setDetailScale(float scaleFD) {
this.scaleFD = scaleFD;
if (ssaoMat != null) {
ssaoMat.setFloat("ScaleFD", scaleFD);
}
}*/

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

/**
* debugging only , will be removed
* @return Whether or not
*/
//public boolean isUseAo() {
//return useAo;
//}

/**
* debugging only
*/
//public void setUseAo(boolean useAo) {
//this.useAo = useAo;
//if (material != null) {
//material.setBoolean("UseAo", useAo);
//}

//}

/**
* debugging only , will be removed
* @return useOnlyAo
*/
//public boolean isUseOnlyAo() {
//return useOnlyAo;
//}

/**
* debugging only
*/
/*public void setUseOnlyAo(boolean useOnlyAo) {
this.useOnlyAo = useOnlyAo;
if (material != null) {
material.setBoolean("UseOnlyAo", useOnlyAo);
}
}

public void setUseSmoothing(boolean useSmoothing) {
this.useSmoothing = useSmoothing;
if (material != null) {
material.setBoolean("UseSmoothing", useSmoothing);
}
}

public boolean isUseSmoothing() {
return useSmoothing;
}

public void setSmoothMore(boolean smoothMore) {
this.smoothMore = smoothMore;
if (material != null) {
material.setBoolean("SmoothMore", smoothMore);
}
}

public boolean isSmoothMore() {
return smoothMore;
}*/

/**
* Enable distance falloff
* @param useDistanceFalloff
*/
/*public void setUseDistanceFalloff(boolean useDistanceFalloff) {
this.useDistanceFalloff = useDistanceFalloff;
if (ssaoMat != null) {
ssaoMat.setBoolean("UseDistanceFalloff", useDistanceFalloff);
}
}*/

/**
* Returns distance falloff setting
* @return useDistanceFalloff
*/
//public boolean getUseDistanceFalloff() {
//return this.useDistanceFalloff;
//}

/**
* Sets the start distance for distance falloff.  Default is 800f
* @param falloffStartDistance
*/
/*public void setFalloffStartDistance(float falloffStartDistance) {
this.falloffStartDistance = falloffStartDistance;
if (ssaoMat != null) {
ssaoMat.setFloat("FalloffStart", falloffStartDistance);
}
}*/

/**
* Returns the start distance for distance falloff.
* @return falloffStartDistance
*/
//public float getFalloffStartDistance() {
//return this.falloffStartDistance;
//}

/**
* Sets the rate at which distance falloff increases past the start distance.  Default is 2.0f
* @param falloffRate
*/
/*public void setFalloffRate(float falloffRate) {
this.falloffRate = falloffRate;
if (ssaoMat != null) {
ssaoMat.setFloat("FalloffAmount", falloffRate);
}
}*/

/**
* Returns the rate at which distance falloff increases past the start distance.
* @return falloffRate
*/
/*public float getFalloffRate() {
return this.falloffRate;
}*/

/**
* Used for debugging.  toggles between shadowmap, colormap & colormap+shadowmap
*/
/*public void toggleSSAO() {
if (!useOnlyAo && useAo) { // BasicSSAO Disabled
useOnlyAo = false;
useAo = false;

} else if (useOnlyAo && useAo) { // BasicSSAO Map Only
useOnlyAo = false;
useAo = true;
} else if (!useOnlyAo && !useAo) { // BasicSSAO Blended
useOnlyAo = true;
useAo = true;
}
if (material != null) {
material.setBoolean("UseAo", useAo);
material.setBoolean("UseOnlyAo", useOnlyAo);
}
}*/

/**
* Used for debugging.  toggles between no smoothing, 1 pass smoothing & 2 pass smoothing
*/
/*public void toggleSmoothing() {
if (smoothMore && useSmoothing) { // Smoothing Disabled
useSmoothing = false;
smoothMore = false;

} else if (useSmoothing && !smoothMore) { // 2 pass Smoothing
useSmoothing = true;
smoothMore = true;
} else if (!useSmoothing && !smoothMore) { // 1 pass Smoothing
useSmoothing = true;
smoothMore = false;
}
if (material != null) {
material.setBoolean("UseSmoothing", useSmoothing);
material.setBoolean("SmoothMore", smoothMore);
}
}*/

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
//sampleRadiusFD = ic.readFloat("sampleRadiusFD", 0.55f);
//intensityFD = ic.readFloat("intensityFD", 2.5f);
//scaleFD = ic.readFloat("scaleFD", 1.15f);
//biasFD = ic.readFloat("biasFD", 0.025f);
}
}