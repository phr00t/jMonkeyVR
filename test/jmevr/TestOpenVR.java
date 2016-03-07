/*

VR Instancing Progress:
- igByGeom gets huge, and updateInstances still happens on it,
  even if the associated geometry is no longer in the scene
  - track which InstanceGeometry to add or remove inside the Geometry?
  - have list of instances to render maintained somewhere else?

*/

package jmevr;

import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.MagFilter;
import com.jme3.texture.Texture.MinFilter;
import com.jme3.ui.Picture;
import com.jme3.util.SkyFactory;
import jmevr.app.VRApplication;
import jmevr.input.OpenVR;
import jmevr.input.VRBounds;
import jmevr.input.VRInput;
import jmevr.input.VRInput.VRINPUT_TYPE;
import jmevr.post.CartoonSSAO;
import jmevr.util.VRGuiManager;
import jmevr.util.VRGuiManager.POSITIONING_MODE;
import jopenvr.JOpenVRLibrary;
import jopenvr.VRControllerAxis_t;

/**
 *
 * @author reden
 */
public class TestOpenVR extends VRApplication {

    // set some VR settings & start the app
    public static void main(String[] args){
        TestOpenVR test = new TestOpenVR();
        //test.preconfigureVRApp(PRECONFIG_PARAMETER.USE_STEAMVR_COMPOSITOR, false); // disable the SteamVR compositor (kinda needed at the moment)
        test.preconfigureVRApp(PRECONFIG_PARAMETER.USE_CUSTOM_DISTORTION, false); // use full screen distortion, maximum FOV, possibly quicker (not compatible with instancing)
        test.preconfigureVRApp(PRECONFIG_PARAMETER.DISABLE_SWAPBUFFERS_COMPLETELY, false); // runs faster, but only VR Compositor visibility available
        test.preconfigureVRApp(PRECONFIG_PARAMETER.FORCE_VR_MODE, false); // render two eyes, regardless of SteamVR
        test.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_CURVED_SURFACE, true);
        test.preconfigureVRApp(PRECONFIG_PARAMETER.FLIP_EYES, false);
        test.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_OVERDRAW, true); // show gui even if it is behind things
        test.preconfigureVRApp(PRECONFIG_PARAMETER.INSTANCE_VR_RENDERING, true); // faster VR rendering, requires some vertex shader changes (see jmevr/shaders/Unshaded.j3md)
        test.preconfigureVRApp(PRECONFIG_PARAMETER.NO_GUI, false);
        test.preconfigureFrustrumNearFar(0.1f, 512f); // set frustum distances here before app starts
        //test.preconfigureResolutionMultiplier(0.666f); // you can downsample for performance reasons
        test.start();
    }
    
    // general objects for scene management
    Node boxes = new Node("boxes");
    Spatial observer;
    boolean moveForward, moveBackwards, rotateLeft, rotateRight;
    Material mat;
    Geometry leftHand, rightHand;
    
    @Override
    public void simpleInitApp() {        
        initTestScene();
    }
    
    private void initTestScene(){
        observer = new Node("observer");
        
        Spatial sky = SkyFactory.createSky(
                    assetManager, "Textures/Sky/Bright/spheremap.png", SkyFactory.EnvMapType.EquirectMap);
        rootNode.attachChild(sky);
        
        Geometry box = new Geometry("", new Box(5,5,5));
        mat = new Material(getAssetManager(), "jmevr/shaders/Unshaded.j3md");
        Texture noise = getAssetManager().loadTexture("Textures/noise.png");
        noise.setMagFilter(MagFilter.Nearest);
        noise.setMinFilter(MinFilter.Trilinear);
        noise.setAnisotropicFilter(16);
        mat.setTexture("ColorMap", noise);
                     
        // make the floor according to the size of our play area
        Geometry floor = new Geometry("floor", new Box(1f, 1f, 1f));
        Vector2f playArea = VRBounds.getPlaySize();
        if( playArea == null ) {
            // no play area, use default size & height
            floor.setLocalScale(2f, 0.5f, 2f);
            floor.move(0f, -1.5f, 0f);
        } else {
            // cube model is actually 2x as big, cut it down to proper playArea size with * 0.5
            floor.setLocalScale(playArea.x * 0.5f, 0.5f, playArea.y * 0.5f);
            floor.move(0f, -0.5f, 0f);
        }
        floor.setMaterial(mat);
        rootNode.attachChild(floor);
        
        // hand wands
        leftHand = (Geometry)getAssetManager().loadModel("Models/vive_controller.j3o");
        rightHand = leftHand.clone();
        Material handMat = new Material(getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        handMat.setTexture("ColorMap", getAssetManager().loadTexture("Textures/vive_controller.png"));
        leftHand.setMaterial(handMat);
        rightHand.setMaterial(handMat);
        rootNode.attachChild(rightHand);
        rootNode.attachChild(leftHand);
        
        // gui element
        Vector2f guiCanvasSize = VRGuiManager.getCanvasSize();
        Picture test = new Picture("testpic");
        test.setImage(assetManager, "Textures/crosshair.png", true);
        test.setWidth(192f);
        test.setHeight(128f);
        test.setPosition(guiCanvasSize.x * 0.5f - 192f * 0.5f, guiCanvasSize.y * 0.5f - 128f * 0.5f);
        guiNode.attachChild(test);
        
        // test any positioning mode here (defaults to AUTO_CAM_ALL)
        VRGuiManager.setPositioningMode(POSITIONING_MODE.AUTO_CAM_ALL_SKIP_PITCH);
        VRGuiManager.setGuiScale(0.4f);
        VRGuiManager.setPositioningElasticity(10f);
        
        box.setMaterial(mat);
        
        Geometry box2 = box.clone();
        box2.move(15, 0, 0);
        box2.setMaterial(mat);
        Geometry box3 = box.clone();
        box3.move(-15, 0, 0);
        box3.setMaterial(mat);        
        
        boxes.attachChild(box);
        boxes.attachChild(box2);
        boxes.attachChild(box3);
        rootNode.attachChild(boxes);
        
        observer.setLocalTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
        
        VRApplication.setObserver(observer);
        rootNode.attachChild(observer);
        
        addAllBoxes();

        initInputs();
        
        // use magic VR mouse cusor (same usage as non-VR mouse cursor)
        inputManager.setCursorVisible(true);
      
        // filter test (can be added here like this)
        // but we are going to save them for the F key during runtime
        /*
        CartoonSSAO cartfilt = new CartoonSSAO();
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(cartfilt);
        viewPort.addProcessor(fpp);        
        */
    }

     private void initInputs() {
        inputManager.addMapping("toggle", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("incShift", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("decShift", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("back", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("filter", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("dumpImages", new KeyTrigger(KeyInput.KEY_I));
        ActionListener acl = new ActionListener() {

            public void onAction(String name, boolean keyPressed, float tpf) {
                if(name.equals("incShift") && keyPressed){
                    VRGuiManager.adjustGuiDistance(-0.1f);
                }else if(name.equals("decShift") && keyPressed){
                    VRGuiManager.adjustGuiDistance(0.1f);
                }else if(name.equals("filter") && keyPressed){
                    // adding filters in realtime
                    CartoonSSAO cartfilt = new CartoonSSAO();
                    FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
                    fpp.addFilter(cartfilt);
                    viewPort.addProcessor(fpp);
                    // filters added to main viewport during runtime,
                    // move them into VR processing
                    // (won't do anything if not in VR mode)
                    VRApplication.moveScreenProcessingToVR();
                }
                if( name.equals("toggle") ) {
                    VRGuiManager.positionGui();
                }                
                if(name.equals("forward")){
                    if(keyPressed){
                        moveForward = true;
                    } else {
                        moveForward = false;
                    }
                } else if(name.equals("back")){
                    if(keyPressed){
                        moveBackwards = true;
                    } else {
                        moveBackwards = false;
                    }
                } else if( name.equals("dumpImages") ) {
                    OpenVR.getCompositor().CompositorDumpImages.apply();
                }else if(name.equals("left")){
                    if(keyPressed){
                        rotateLeft = true;
                    } else {
                        rotateLeft = false;
                    }
                } else if(name.equals("right")){
                    if(keyPressed){
                        rotateRight = true;
                    } else {
                        rotateRight = false;
                    }
                }
                
                
            }
        };
        inputManager.addListener(acl, "forward");
        inputManager.addListener(acl, "back");
        inputManager.addListener(acl, "left");
        inputManager.addListener(acl, "right");
        inputManager.addListener(acl, "toggle");
        inputManager.addListener(acl, "incShift");
        inputManager.addListener(acl, "decShift");
        inputManager.addListener(acl, "filter");
        inputManager.addListener(acl, "dumpImages");
    }
     
     private float distance = 100f;
     private float prod = 0f;
     private float placeRate = 0f;
     
     //FPS test
     private float tpfAdder = 0f;
     private int tpfCount = 0;
     
     @Override
     public void simpleUpdate(float tpf){
        
         //FPS test
         tpfAdder += tpf;
         tpfCount++;
         if( tpfCount == 60 ) {
             System.out.println("FPS: " + Float.toString(1f / (tpfAdder / tpfCount)));
             tpfCount = 0;
             tpfAdder = 0f;
         }
         
         prod+=tpf;
         distance = 100f * FastMath.sin(prod);
         boxes.setLocalTranslation(0, 0, 200f+ distance);
         
         if(moveForward){
             observer.move(VRApplication.getFinalObserverRotation().getRotationColumn(2).mult(tpf*8f));
         }
         if(moveBackwards){
             observer.move(VRApplication.getFinalObserverRotation().getRotationColumn(2).mult(-tpf*8f));
         }
         if(rotateLeft){
             observer.rotate(0, 0.75f*tpf, 0);
         }
         if(rotateRight){
             observer.rotate(0, -0.75f*tpf, 0);
         }
         
         handleWandInput(0, leftHand);
         handleWandInput(1, rightHand);
         if( placeRate > 0f ) placeRate -= tpf;
     }
     
     private void handleWandInput(int index, Geometry geo) {
         Quaternion q = VRInput.getFinalObserverRotation(index);
         Vector3f v = VRInput.getFinalObserverPosition(index);
         if( q != null && v != null ) {
             geo.setCullHint(CullHint.Dynamic); // make sure we see it
             geo.setLocalTranslation(v);
             geo.setLocalRotation(q);
             // place boxes when holding down trigger
             if( VRInput.getAxis(index, VRINPUT_TYPE.ViveTriggerAxis).x >= 1f &&
                 placeRate <= 0f ) {
                 placeRate = 0.5f;
                 addBox(v, q, 0.1f);
                 VRInput.triggerHapticPulse(index, 0.1f);
             }
             // print out all of the known information about the controllers here
             /*for(int i=0;i<VRInput.getRawControllerState(index).rAxis.length;i++) {
                 VRControllerAxis_t cs = VRInput.getRawControllerState(index).rAxis[i];
                 System.out.println("Controller#" + Integer.toString(index) + ", Axis#" + Integer.toString(i) + " X: " + Float.toString(cs.x) + ", Y: " + Float.toString(cs.y));
             }
             System.out.println("Button press: " + Long.toString(VRInput.getRawControllerState(index).ulButtonPressed) + ", touch: " + Long.toString(VRInput.getRawControllerState(index).ulButtonTouched));*/
         } else {
             geo.setCullHint(CullHint.Always); // hide it             
         }
     }
     
     private void addAllBoxes() {
        float distance = 8;
        for (int x = 0; x < 35; x++) {
            float cos = FastMath.cos(x * FastMath.PI / 16f) * distance;
            float sin = FastMath.sin(x * FastMath.PI / 16f) * distance;
            Vector3f loc = new Vector3f(cos, 0, sin);
            addBox(loc, null, 1f);
            loc = new Vector3f(0, cos, sin);
            addBox(loc, null, 1f);
        }

    }

    private static final Box smallBox = new Box(0.3f, 0.3f, .3f);
    private void addBox(Vector3f location, Quaternion rot, float scale) {
        Geometry leftQuad = new Geometry("Box", smallBox);
        if( rot != null ) {
            leftQuad.setLocalRotation(rot);
        } else {
            leftQuad.rotate(0.5f, 0f, 0f);
        }
        leftQuad.setLocalScale(scale);
        leftQuad.setMaterial(mat);
        leftQuad.setLocalTranslation(location);
        rootNode.attachChild(leftQuad);
    }
}
