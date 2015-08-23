/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import jmevr.app.VRApplication;

/**
 *
 * @author
 * phr00t
 */
public class VRGuiNode extends Node {
    
    public enum POSITIONING_MODE {
        MANUAL, AUTO, AUTO_OBSERVER
    }
    
    private Camera camLeft, camRight;
    private float guiDistance, guiScale = 1f;
    private POSITIONING_MODE posMode = POSITIONING_MODE.AUTO;
    
    private final Vector3f look = new Vector3f(), left = new Vector3f(), temppos = new Vector3f();
    private final Matrix3f orient = new Matrix3f();
    private final Quaternion tempq = new Quaternion();
    protected boolean wantsReposition;
    
    private int oHeight, oWidth;
    
    public VRGuiNode() {
        super("vrgui");
        guiDistance = 2f;
        if( VRApplication.isInVR() ) {
            setQueueBucket(Bucket.Translucent);
        } else setQueueBucket(Bucket.Gui);
        setCullHint(CullHint.Never);
    }
    
    public VRGuiNode(float dist) {
        super("vrgui");
        guiDistance = dist;
        if( VRApplication.isInVR() ) {
            setQueueBucket(Bucket.Translucent);
        } else setQueueBucket(Bucket.Gui);
        setCullHint(CullHint.Never);
    }
    
    public void setPositioningMode(POSITIONING_MODE mode) {
        posMode = mode;
    }
    
    public POSITIONING_MODE getPositioningMode() {
        return posMode;
    }    
    
    public void positionGui() {
        wantsReposition = true;
    }
    
    private void positionTo(Vector3f pos, Quaternion dir) {
        Vector3f guiPos = getLocalTranslation();
        guiPos.set(guiDistance * 1.405f * oWidth / 800f * guiScale * 0.6f,
                  -guiDistance * oHeight * guiScale * 0.63f / 600f, guiDistance);
        dir.mult(guiPos, guiPos);
        guiPos.x += pos.x;
        guiPos.y += pos.y;
        guiPos.z += pos.z;        
    }
    
    protected void positionGuiNow() {
        wantsReposition = false;
        if( VRApplication.isInVR() == false ) return;
        float useScale = guiScale * 0.6f * 0.0035f;
        setLocalScale(guiDistance * useScale, guiDistance * useScale, 0.05f);
        switch( posMode ) {
            case MANUAL:
            case AUTO:
                if( camLeft != null && camRight != null ) {
                    // get middle point
                    temppos.set(camLeft.getLocation()).interpolateLocal(camRight.getLocation(), 0.5f);
                    positionTo(temppos, camLeft.getRotation());
                }
                break;
            case AUTO_OBSERVER:
                Spatial obs = VRApplication.getObserver();
                if( obs != null ) {
                    positionTo(obs.getWorldTranslation(), camLeft.getRotation());
                }
                break;
        }
        rotateScreenToCamera();
    }
    
    private void rotateScreenToCamera() {
        // coopt diff for our in direction:
        look.set(camLeft.getDirection()).negateLocal();
        // coopt loc for our left direction:
        left.set(camLeft.getLeft()).negateLocal();
        orient.fromAxes(left, camLeft.getUp(), look);
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
    
    public float getGuiDistance() {
        return guiDistance;
    }
    
    public void adjustGuiDistance(float adjustAmount) {
        guiDistance += adjustAmount;
    }
    
    public void setupGui(ViewPort left, ViewPort right, int origWidth, int origHeight) {
        left.attachScene(this);
        right.attachScene(this);
        camLeft = left.getCamera();
        camRight = right.getCamera();
        oHeight = origHeight;
        oWidth = origWidth;
        setPositioningMode(posMode);
    }
     
    public void fixBrokenElements() {
        convertAll(this);
    }
    
    private void convertAll(Spatial s) {
        if( VRApplication.isInVR() == false ) return;
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
        if( VRApplication.isInVR() ) convertAll(child);
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
        if( VRApplication.isInVR() ) convertAll(child);
        return super.attachChildAt(child, index);
    }    
}
