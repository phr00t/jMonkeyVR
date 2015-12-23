/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.shadow;

import com.jme3.asset.AssetManager;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector4f;
import com.jme3.shadow.DirectionalLightShadowFilter;
import jmevr.app.VRApplication;

/**
 *
 * @author Phr00t
 */
public class InstancedDirectionalShadowFilter extends DirectionalLightShadowFilter {    
    
    private final Vector4f temp4f = new Vector4f(), temp4f2 = new Vector4f();
    
    public InstancedDirectionalShadowFilter(AssetManager assetManager, int shadowMapSize, int nbSplits) {
        super(assetManager, shadowMapSize, nbSplits, "jmevr/shaders/PostShadowFilter.j3md");
    }        

    @Override    
    protected void preFrame(float tpf) {
        shadowRenderer.preFrame(tpf);
        if( VRApplication.isInstanceVRRendering() ) {
            material.setMatrix4("ViewProjectionMatrixInverseRight", VRApplication.getVRViewManager().getCamRight().getViewProjectionMatrix().invert());
            Matrix4f m = VRApplication.getVRViewManager().getCamRight().getViewProjectionMatrix();
            material.setVector4("ViewProjectionMatrixRow2Right", temp4f2.set(m.m20, m.m21, m.m22, m.m23));
        }
        material.setMatrix4("ViewProjectionMatrixInverse", viewPort.getCamera().getViewProjectionMatrix().invert());
        Matrix4f m = viewPort.getCamera().getViewProjectionMatrix();
        material.setVector4("ViewProjectionMatrixRow2", temp4f.set(m.m20, m.m21, m.m22, m.m23));
    }
}
