/*
 * Copyright (c) 2014 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jmevr.util;

import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.GeometryGroupNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.UserData;
import com.jme3.scene.control.Control;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.MatParam;
import com.jme3.scene.instancing.InstancedGeometry;
import com.jme3.scene.instancing.InstancedNode;
import java.io.IOException;
import java.util.HashMap;
import jmevr.app.VRApplication;

public class VRInstanceNode extends GeometryGroupNode {
    
    private boolean autoInstance = true;
    
    public void setAutoInstancing(boolean val) {
        autoInstance = val;
    }
    
    public boolean isAutoInstancing() {
        return autoInstance;
    }

    private static class InstancedNodeControl implements Control {

        private VRInstanceNode node;
        
        public InstancedNodeControl() {
        }
        
        public InstancedNodeControl(VRInstanceNode node) {
            this.node = node;
        }
        
        @Override
        public Control cloneForSpatial(Spatial spatial) {
            return this; 
            // WARNING: Sets wrong control on spatial. Will be
            // fixed automatically by InstancedNode.clone() method.
        }
        
        public void setSpatial(Spatial spatial){
        }
        
        public void update(float tpf){
        }
        
        public void render(RenderManager rm, ViewPort vp) {
            node.renderFromControl();
        }
        
        public void write(JmeExporter ex) throws IOException {
        }

        public void read(JmeImporter im) throws IOException {
        }
    }
    
    protected InstancedNodeControl control;
    
    protected HashMap<Geometry, InstancedGeometry> igByGeom = new HashMap<>();
    
    protected void enableInstanceVR() {
        if( control != null ) return;
        control = new InstancedNodeControl(this);
        addControl(control);        
    }
    
    public VRInstanceNode(String name) {
        super(name);
        if( VRApplication.isInstanceVRRendering() ) {
            enableInstanceVR();
        }
    }
    
    public void renderFromControl() {
        for (InstancedGeometry ig : igByGeom.values()) {
            ig.updateInstances();
        }
    }
    
    private void addToInstancedGeometry(Geometry geom) {
        Material material = geom.getMaterial();
        MatParam param = material.getMaterialDef().getMaterialParam("UseInstancing");
        MatParam param2 = material.getMaterialDef().getMaterialParam("RightEyeViewProjectionMatrix");
        if (param == null || param2 == null) {
            System.out.println("VR instance failed on geo '" + geom.getName() + "', material '" + material.getMaterialDef().getAssetName() + "' Check material params!");
            return;
        }
        geom.getMaterial().setMatrix4("RightEyeViewProjectionMatrix", VRApplication.getVRViewManager().getCamRight().getViewProjectionMatrix());
        material.setBoolean("UseInstancing", true);
        InstancedGeometry ig = new InstancedGeometry(geom.getName() + "-instance", false, 2);
        ig.forceBoundFrom(geom);
        ig.setMaterial(geom.getMaterial());
        ig.setMesh(geom.getMesh());
        ig.setUserData(UserData.JME_PHYSICSIGNORE, true);
        Geometry clone = geom.clone(false);
        geom.setCullHint(CullHint.Always); // hide the original
        clone.setCullHint(CullHint.Always);
        igByGeom.put(geom, ig);
        igByGeom.put(clone, ig);
        geom.associateWithGroupNode(this, 0);
        clone.associateWithGroupNode(this, 1);
        ig.addInstance(geom);
        ig.addInstance(clone);
        Node myparent = geom.getParent();
        if( myparent == null ) myparent = this;
        myparent.attachChild(clone);
        myparent.attachChild(ig);
    }
    
    private void removeFromInstancedGeometry(Geometry geom) {
        InstancedGeometry ig = igByGeom.remove(geom);
        if (ig != null) {
            ig.deleteInstance(geom);
        }
    }
    
    private void ungroupSceneGraph(Spatial s) {
        if (s instanceof Node) {
            for (Spatial sp : ((Node) s).getChildren()) {
                ungroupSceneGraph(sp);
            }
        } else if (s instanceof Geometry) {
            Geometry g = (Geometry) s;
            if (g.isGrouped()) {
                // Will invoke onGeometryUnassociated automatically.
                g.unassociateFromGroupNode();
                
                if (InstancedNode.getGeometryStartIndex(g) != -1) {
                    throw new AssertionError();
                }
            }
        }
    }
    
    @Override
    public int attachChildAt(Spatial child, int index) {
        if( autoInstance ) instance(child);
        int retval = super.attachChildAt(child, index);
        return retval;
    }
    
    @Override
    public Spatial detachChildAt(int index) {
        Spatial s = super.detachChildAt(index);
        if( VRApplication.isInstanceVRRendering() == false ) return s;
        if (s instanceof Node) {
            ungroupSceneGraph(s);
        }
        return s;
    }
    
    private void instance(Spatial n) {
        if( VRApplication.isInstanceVRRendering() == false ) return;
        if (n instanceof Geometry) {
            Geometry g = (Geometry) n;
            if (!g.isGrouped() && g.getBatchHint() != Spatial.BatchHint.Never) {
                addToInstancedGeometry(g);
            }
        } else if (n instanceof Node) {
            for (Spatial child : ((Node) n).getChildren()) {
                if (child instanceof GeometryGroupNode) {
                    continue;
                }
                instance(child);
            }
        }
    }
    
    public void instance() {
        if( VRApplication.isInstanceVRRendering() == false ) return;
        instance(this);
    }
    
    @Override
    public void onTransformChange(Geometry geom) {
        // Handled automatically
    }

    @Override
    public void onMaterialChange(Geometry geom) {
        //relocateInInstancedGeometry(geom);
    }

    @Override
    public void onMeshChange(Geometry geom) {
        //relocateInInstancedGeometry(geom);
    }

    @Override
    public void onGeoemtryUnassociated(Geometry geom) {
        removeFromInstancedGeometry(geom);
    }
}
