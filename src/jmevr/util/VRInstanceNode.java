/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.instancing.InstancedGeometry;
import com.jme3.scene.instancing.InstancedNode;
import com.jme3.util.SafeArrayList;
import java.util.HashMap;
import jmevr.app.VRApplication;

/**
 *
 * @author Phr00t
 */
public class VRInstanceNode extends InstancedNode {
    
    private final HashMap<Spatial, Spatial> myOtherInstance = new HashMap<>();

    public VRInstanceNode(String name) {
        super(name);
    }
    
    private boolean makeInstancing(Spatial s) {        
        if( s instanceof Geometry ) {
            if( myOtherInstance.containsKey(s) ) return true;
            Geometry g = ((Geometry)s);
            if (!g.isGrouped()) {
                Material m = g.getMaterial();
                if( m.getMaterialDef().getMaterialParam("UseInstancing") == null ) {
                    System.out.println("Trying instanced VR with a non-instanced shader! Geometry '" + s.getName() + "', material '" + m.getMaterialDef().getName() + "'");
                    return false;
                } else {
                    m.setBoolean("UseInstancing", true);
                    Geometry sclone = g.clone(false);
                    myOtherInstance.put(s, sclone);
                    InstancedGeometry ig = super.lookUpByGeometry(sclone);
                    igByGeom.put(sclone, ig);
                    g.associateWithGroupNode(this, 0);
                    ig.addInstance(sclone);
                    return true;
                }
            }
        } else if( s instanceof Node ) {
            SafeArrayList<Spatial> ss = (SafeArrayList<Spatial>)((Node)s).getChildren();
            for(int i=0;i<ss.size();i++) {
                return makeInstancing(ss.get(i));
            }
        }
        return true;
    }
    
    @Override
    public int attachChild(Spatial s) {
        if( VRApplication.isInstanceVRRendering() == false ) {
            return super.attachChild(s);
        } else if( makeInstancing(s) ) {
            return super.attachChild(s);
        }
        return children.size();
    }
    
    @Override
    public void renderFromControl() {
        for (InstancedGeometry ig : instancesMap.values()) {
            Geometry first = ig.getGeometries()[1];
            Geometry second = ig.getGeometries()[0];
            second.setLocalTransform(first.getLocalTransform());
            ig.updateInstances();            
        }
    }    
    
}
