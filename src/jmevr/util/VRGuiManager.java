/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Spatial;
import com.jme3.scene.Geometry;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import jmevr.app.VRApplication;

/**
 *
 * @author
 * phr00t
 */
public class VRGuiManager {
    
    public enum POSITIONING_MODE {
        MANUAL, AUTO_CAM_ALL, AUTO_OBSERVER_POS_CAM_ROTATION, AUTO_OBSERVER_ALL
    }
    
    private static Camera camLeft, camRight;
    private static float guiDistance = 1.5f, guiScale = 1f;
    private static POSITIONING_MODE posMode = POSITIONING_MODE.AUTO_CAM_ALL;
    
    private static final Vector3f look = new Vector3f(), left = new Vector3f(), temppos = new Vector3f();
    private static final Matrix3f orient = new Matrix3f();
    private static final Quaternion tempq = new Quaternion();
    protected static boolean wantsReposition;
    
    private static int oHeight, oWidth;
    
    public static void setPositioningMode(POSITIONING_MODE mode) {
        posMode = mode;
    }
    
    public static POSITIONING_MODE getPositioningMode() {
        return posMode;
    }    
    
    public static void positionGui() {
        wantsReposition = true;
    }
    
    private static void positionTo(Vector3f pos, Quaternion dir) {
        Vector3f guiPos = guiQuad.getLocalTranslation();
        guiPos.set(0f, 0f, guiDistance);
        dir.mult(guiPos, guiPos);
        guiPos.x += pos.x;
        guiPos.y += pos.y;
        guiPos.z += pos.z;        
    }
    
    protected static void updateGuiQuadGeometricState() {
        guiQuad.updateGeometricState();
    }
    
    protected static void positionGuiNow() {
        wantsReposition = false;
        if( VRApplication.isInVR() == false ) return;
        guiQuad.setLocalScale(guiDistance * guiScale * 4f, 4f * guiDistance * guiScale * oHeight/oWidth, 1f);
        switch( posMode ) {
            case MANUAL:
            case AUTO_CAM_ALL:
                if( camLeft != null && camRight != null ) {
                    // get middle point
                    temppos.set(camLeft.getLocation()).interpolateLocal(camRight.getLocation(), 0.5f);
                    positionTo(temppos, camLeft.getRotation());
                }
                break;
            case AUTO_OBSERVER_POS_CAM_ROTATION:
                Object obs = VRApplication.getObserver();
                if( obs != null ) {
                    if( obs instanceof Camera ) {
                        positionTo(((Camera)obs).getLocation(), camLeft.getRotation());
                    } else {
                        positionTo(((Spatial)obs).getWorldTranslation(), camLeft.getRotation());                        
                    }
                }
                break;
            case AUTO_OBSERVER_ALL:
                obs = VRApplication.getObserver();
                if( obs != null ) {
                    if( obs instanceof Camera ) {
                        positionTo(((Camera)obs).getLocation(), ((Camera)obs).getRotation());
                    } else {
                        positionTo(((Spatial)obs).getWorldTranslation(), ((Spatial)obs).getWorldRotation());                        
                    }
                }                
                break;
        }
        rotateScreenToCamera();
    }
    
    private static void rotateScreenToCamera() {
        // coopt diff for our in direction:
        look.set(camLeft.getDirection()).negateLocal();
        // coopt loc for our left direction:
        left.set(camLeft.getLeft()).negateLocal();
        orient.fromAxes(left, camLeft.getUp(), look);
        Quaternion rot = tempq.fromRotationMatrix(orient);
        guiQuad.setLocalRotation(rot);
    }
    
    public static void setGuiDistance(float newGuiDistance) {
        guiDistance = newGuiDistance;                
    }
    
    public static void setGuiScale(float scale) {
        guiScale = scale;
    }
    
    public static float getGuiDistance() {
        return guiDistance;
    }
    
    public static void adjustGuiDistance(float adjustAmount) {
        guiDistance += adjustAmount;
    }
    
    protected static void setupGui(Camera origCam, ViewPort left, ViewPort right, int origWidth, int origHeight) {
        getGuiQuad(origCam);
        left.attachScene(guiQuad);
        right.attachScene(guiQuad);
        camLeft = left.getCamera();
        camRight = right.getCamera();
        oHeight = origHeight;
        oWidth = origWidth;
        setPositioningMode(posMode);
    }
    
    private static Geometry guiQuad;
    private static ViewPort offView;
    private static Texture2D guiTexture;
    private static Geometry getGuiQuad(Camera sourceCam){
        if( guiQuad == null ) {
            VRApplication sourceApp = VRApplication.getMainVRApp();
            Camera offCamera = sourceCam.clone();
            offCamera.setParallelProjection(true);
            offCamera.setLocation(Vector3f.ZERO);
            offCamera.lookAt(Vector3f.UNIT_Z, Vector3f.UNIT_Y);

            offView = sourceApp.getRenderManager().createPreView("GUI View", offCamera);
            offView.setClearFlags(true, true, true);            
            offView.setBackgroundColor(ColorRGBA.BlackNoAlpha);

            // create offscreen framebuffer
            FrameBuffer offBuffer = new FrameBuffer(offCamera.getWidth(), offCamera.getHeight(), 1);

            //setup framebuffer's texture
            guiTexture = new Texture2D(offCamera.getWidth(), offCamera.getHeight(), Format.RGBA8);
            guiTexture.setMinFilter(Texture.MinFilter.Trilinear);
            guiTexture.setMagFilter(Texture.MagFilter.Bilinear);

            //setup framebuffer to use texture
            offBuffer.setDepthBuffer(Format.Depth);
            offBuffer.setColorTexture(guiTexture);

            //set viewport to render to offscreen framebuffer
            offView.setOutputFrameBuffer(offBuffer);

            // setup framebuffer's scene
            offView.attachScene(sourceApp.getGuiNode());

            guiQuad = new Geometry("guiQuad", new CenterQuad(1f, 1f));
            Material mat = new Material(sourceApp.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
            mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
            mat.getAdditionalRenderState().setDepthWrite(false);
            mat.setTexture("ColorMap", guiTexture);
            guiQuad.setQueueBucket(Bucket.Translucent);
            guiQuad.setMaterial(mat);
        }
        return guiQuad;
    }
}
