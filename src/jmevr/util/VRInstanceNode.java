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
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.UserData;
import com.jme3.scene.control.Control;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.MatParam;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.instancing.InstancedGeometry;
import java.io.IOException;
import java.util.ArrayList;
import jmevr.app.VRApplication;

public class VRInstanceNode extends Node {

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
    
    private boolean isGui(Spatial s) {
        if( s.getQueueBucket() == Bucket.Gui ) return true;
        Spatial p = s.getParent();
        if( p != null ) return isGui(p);
        return s == VRApplication.getMainVRApp().getGuiNode() ||
               s.getCullHint() == CullHint.Never;
    }
    
    public void handleChangedGeometry() {
        if( VRApplication.isInstanceVRRendering() == false ) return;
        while(Node.trackedAddedGeometry.isEmpty() == false) {
            try {
                Geometry g = Node.trackedAddedGeometry.pop();
                if( !isGui(g) ) {
                    InstancedGeometry ig;
                    if( g.getBatchHint() == BatchHint.Never ) { 
                        ig = g.getUserData("instanced");
                        if( ig != null ) g.getParent().attachChild(ig);
                    } else {
                        ig = addToInstancedGeometry(g);
                    }
                    if( ig != null && igToRender.contains(ig) == false ) igToRender.add(ig);
                }
            } catch(Exception e) { }
        }
        while(Node.trackedRemovedGeometry.isEmpty() == false) {
            try {
                Geometry g = Node.trackedRemovedGeometry.pop();
                InstancedGeometry ig = g.getUserData("instanced");
                if( ig != null ) {
                    ig.removeFromParent();
                    igToRender.remove(ig);
                }
            } catch(Exception e) { }
        }
    }
    
    protected InstancedNodeControl control;
    
    protected ArrayList<InstancedGeometry> igToRender;
    
    protected void enableInstanceVR() {
        if( control != null ) return;        
        if( igToRender == null ) igToRender = new ArrayList<>();
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
        for (int i=0;i<igToRender.size();i++) {
            InstancedGeometry ig = igToRender.get(i);
            ig.updateInstances();
        }
    }
    
    private InstancedGeometry addToInstancedGeometry(Geometry geom) {
        Material material = geom.getMaterial();
        MatParam param = material.getMaterialDef().getMaterialParam("UseInstancing");
        MatParam param2 = material.getMaterialDef().getMaterialParam("RightEyeViewProjectionMatrix");
        if (param == null || param2 == null) {
            System.out.println("VR instance failed on geo '" + geom.getName() + "', material '" + material.getMaterialDef().getAssetName() + "' Check material params!");
            return null;
        }
        geom.getMaterial().setMatrix4("RightEyeViewProjectionMatrix", VRApplication.getVRViewManager().getCamRight().getViewProjectionMatrix());
        material.setBoolean("UseInstancing", true);
        InstancedGeometry ig = new InstancedGeometry(geom.getName() + "-instance", false, 2);
        ig.forceLinkedGeometry(geom);
        ig.setMaterial(geom.getMaterial());
        ig.setMesh(geom.getMesh());
        ig.setUserData(UserData.JME_PHYSICSIGNORE, true);
        Geometry clone = geom.clone(false);
        clone.setUserData(UserData.JME_PHYSICSIGNORE, true);
        geom.setCullHint(CullHint.Always);
        geom.setBatchHint(BatchHint.Never);
        clone.setBatchHint(BatchHint.Never);
        clone.setCullHint(CullHint.Always);
        geom.setUserData("instanced", ig);
        ig.addInstance(geom);
        ig.addInstance(clone);
        Node myparent = geom.getParent();
        if( myparent == null ) myparent = this;
        myparent.attachChild(clone);
        myparent.attachChild(ig);
        return ig;
    }

}
