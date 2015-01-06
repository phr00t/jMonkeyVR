/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.util;

import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import oculusvr.input.OculusRift;

/**
 *
 * @author
 * phr00t
 */
public class OculusGuiNode extends Node {
    
    public enum POSITIONING_MODE {
        MANUAL, AUTO
    }
    
    private Camera cam;
    private float guiDistance, guiScale = 1f;
    private POSITIONING_MODE posMode = POSITIONING_MODE.MANUAL;
    
    private final Vector3f look = new Vector3f(), left = new Vector3f();
    private final Matrix3f orient = new Matrix3f();
    private final Quaternion tempq = new Quaternion();
    
    private int oHeight, oWidth;
    
    public OculusGuiNode() {
        super("ogui");
        guiDistance = 2f;
    }
    
    public OculusGuiNode(float dist) {
        super("ogui");
        guiDistance = dist;
    }
    
    public void setPositioningMode(POSITIONING_MODE mode) {
        posMode = mode;
    }
    
    public POSITIONING_MODE getPositioningMode() {
        return posMode;
    }    
    
    public void positionGui() {
        Vector3f guiPos = getLocalTranslation();
        setLocalScale(guiDistance * 0.0035f * guiScale,
                      guiDistance * 0.0035f * guiScale, 0.05f);
        if( cam != null ) {
            Vector3f campos = cam.getLocation();
            guiPos.set(guiDistance * 1.375f * oWidth / 800f * guiScale,
                      -guiDistance * oHeight * guiScale / 600f, guiDistance);
            cam.getRotation().mult(guiPos, guiPos);
            guiPos.x += campos.x;
            guiPos.y += campos.y;
            guiPos.z += campos.z;
            rotateScreen();
        }
    }
    
    private void rotateScreen() {
        // coopt diff for our in direction:
        look.set(cam.getDirection()).negateLocal();
        // coopt loc for our left direction:
        left.set(cam.getLeft()).negateLocal();
        orient.fromAxes(left, cam.getUp(), look);
        Node myparent = getParent();
        Quaternion rot = tempq.fromRotationMatrix(orient);
        if ( myparent != null ) {
            rot =  myparent.getWorldRotation().inverse().multLocal(rot);
            rot.normalizeLocal();
        }
        setLocalRotation(rot);
    }
    
    public void setGuiDistance(float newGuiDistance) {
        guiDistance = newGuiDistance;                
    }
    
    public void setGuiScale(float scale) {
        guiScale = scale;
    }
    
    public void adjustGuiDistance(float adjustAmount) {
        guiDistance += adjustAmount;
    }
    
    public void setupGui(ViewPort left, ViewPort right, int origWidth, int origHeight) {
        left.attachScene(this);
        right.attachScene(this);
        cam = left.getCamera();
        oHeight = origHeight;
        oWidth = origWidth;
        setPositioningMode(posMode);
        positionGui();
    }
     
    public void fixBrokenElements() {
        convertAll(this);
    }
    
    private void convertAll(Spatial s) {
        s.setQueueBucket(Bucket.Translucent);
        if( s instanceof Node ) {
            for(Spatial ns : ((Node)s).getChildren()) {
                convertAll(ns);
            }
        }
    }
    
    /**
     * <code>attachChild</code> attaches a child to this node. This node
     * becomes the child's parent. The current number of children maintained is
     * returned.
     * <br>
     * If the child already had a parent it is detached from that former parent.
     * 
     * @param child
     *            the child to attach to this node.
     * @return the number of children maintained by this node.
     * @throws IllegalArgumentException if child is null.
     */
    @Override
    public int attachChild(Spatial child) {
        if( OculusRift.isInitialized() ) convertAll(child);
        return super.attachChild(child);
    }
    
    /**
     * 
     * <code>attachChildAt</code> attaches a child to this node at an index. This node
     * becomes the child's parent. The current number of children maintained is
     * returned.
     * <br>
     * If the child already had a parent it is detached from that former parent.
     * 
     * @param child
     *            the child to attach to this node.
     * @return the number of children maintained by this node.
     * @throws NullPointerException if child is null.
     */
    @Override
    public int attachChildAt(Spatial child, int index) {
        if( OculusRift.isInitialized() ) convertAll(child);
        return super.attachChildAt(child, index);
    }    
}
