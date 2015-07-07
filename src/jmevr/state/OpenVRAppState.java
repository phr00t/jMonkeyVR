/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.util.TempVars;
import jmevr.input.OpenVR;
import jmevr.input.VRHMD;
import jmevr.util.MeshUtil;
import jmevr.util.VRGuiNode;

/**
 *
 * @author reden
 */
public class OpenVRAppState extends AbstractAppState{

    private SimpleApplication app;
    Camera camLeft,camRight;
    //ViewPort viewPortLeft, viewPortRight;
    private Matrix4f transformMatrix;
    private Quaternion directionQuat;
    private static VRHMD VRhardware; 
    //private VRGuiNode guiNode;
    private Spatial observer;
    
    private final static String LEFT_VIEW_NAME = "Left View";
    private final static String RIGHT_VIEW_NAME = "Right View";
    
    public OpenVRAppState(){
        VRhardware = new OpenVR();
        VRhardware.initialize();
        transformMatrix = new Matrix4f();
        directionQuat = new Quaternion();
    }

    public OpenVRAppState(VRGuiNode guiNode){
        this();
        guiNode = new VRGuiNode();    
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        
        if( VRhardware.isInitialized()) {
            initializeHardware();
        }
        
        
        setupFiltersAndViews();
        observer.lookAt(Vector3f.UNIT_Z.negate(), Vector3f.UNIT_Y);
        transformMatrix.setRotationQuaternion(observer.getLocalRotation());
        
        setupVRScene();
    }
    
    private void initializeHardware(){
        
        if( VRhardware instanceof OpenVR ) {
            ((OpenVR)VRhardware).initOpenVRCompositor();
        }
    }
    
    /**
     * Replaces rootNode as the main cameras scene with the distortion mesh
     */
    private void setupVRScene(){
        ((SimpleApplication)app).getFlyByCamera().setEnabled(false);
        Node distortionScene = new Node();
        Material leftMat = new Material(app.getAssetManager(), "jmevr/shaders/OpenVR.j3md");
        leftMat.setTexture("Texture", app.getRenderManager().getPreView(LEFT_VIEW_NAME).getOutputFrameBuffer().getColorBuffer().getTexture());
        Geometry leftEye = new Geometry("box", MeshUtil.setupDistortionMesh(0));
        leftEye.setMaterial(leftMat);
        distortionScene.attachChild(leftEye);
        
        Material rightMat = new Material(app.getAssetManager(), "jmevr/shaders/OpenVR.j3md");
        rightMat.setTexture("Texture", app.getRenderManager().getPreView(RIGHT_VIEW_NAME).getOutputFrameBuffer().getColorBuffer().getTexture());
        Geometry rightEye = new Geometry("box", MeshUtil.setupDistortionMesh(1));
        rightEye.setMaterial(rightMat);
        distortionScene.attachChild(rightEye);
        
        distortionScene.updateGeometricState();
        
        ((SimpleApplication)app).getViewPort().detachScene(app.getRootNode());
        ((SimpleApplication)app).getViewPort().attachScene(distortionScene);
    }
    
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        VRhardware.updatePose();
        transformMatrix.setRotationQuaternion(observer.getLocalRotation());
        transformMatrix.setTranslation(observer.getLocalTranslation());
         if (VRhardware instanceof OpenVR){
             TempVars tempVars = TempVars.get();
                    // left eye
            tempVars.tempMat4.set(((OpenVR)VRhardware).getHMDMatrixPoseEye(0));
            tempVars.tempMat4.multLocal(transformMatrix);
            tempVars.tempMat4.toTranslationVector(tempVars.vect1);
            tempVars.tempMat4.toRotationQuat(tempVars.quat1);
            camLeft.setFrame(tempVars.vect1, tempVars.quat1);
            // right eye
            tempVars.tempMat4.set(((OpenVR)VRhardware).getHMDMatrixPoseEye(1));
            tempVars.tempMat4.multLocal(transformMatrix);
            tempVars.tempMat4.toTranslationVector(tempVars.vect1);
            tempVars.tempMat4.toRotationQuat(tempVars.quat1);
            camRight.setFrame(tempVars.vect1, tempVars.quat1);
            tempVars.release();
        }
    }
    
    private void setupFiltersAndViews() {
        camLeft = app.getCamera().clone();
        camLeft.setLocation(Vector3f.ZERO);
        camLeft.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        camRight = camLeft.clone();
        setupFrameBuffer(camLeft, LEFT_VIEW_NAME);
        setupFrameBuffer(camRight, RIGHT_VIEW_NAME);
    }
    
    private void setupFrameBuffer(Camera cam, String viewName){
        // create offscreen framebuffer
        FrameBuffer offBufferLeft = new FrameBuffer(512, 512, 1);

        //setup framebuffer's texture
        Texture2D offTex = new Texture2D(512, 512, Image.Format.RGBA8);
        offTex.setMinFilter(Texture.MinFilter.Trilinear);
        offTex.setMagFilter(Texture.MagFilter.Bilinear);

        //setup framebuffer to use texture
        offBufferLeft.setDepthBuffer(Image.Format.Depth);
        offBufferLeft.setColorTexture(offTex);
        
        
        ViewPort offViewLeft = app.getRenderManager().createPreView(viewName, cam);
        offViewLeft.setClearFlags(true, true, true);
        offViewLeft.setBackgroundColor(ColorRGBA.DarkGray);
        offViewLeft.attachScene(this.app.getRootNode());
        //set viewport to render to offscreen framebuffer
        offViewLeft.setOutputFrameBuffer(offBufferLeft);
    }
    
//    public VRGuiNode getGuiNode(){
        //return guiNode;
//    }
    
    public void setDirection(Quaternion direction){
        directionQuat.set(direction);
    }
    
    public Quaternion getDirection(){
        return directionQuat;
    }

    public Spatial getObserver() {
        return observer;
    }

    public void setObserver(Spatial observer) {
        this.observer = observer;
    }
}
