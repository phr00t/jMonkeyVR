package oculusvr;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetEventListener;
import com.jme3.asset.AssetKey;
import com.jme3.asset.TextureKey;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.input.controls.ActionListener;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import oculusvr.state.OVRAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.MatParam;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Caps;
import com.jme3.renderer.Renderer;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.shader.VarType;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
import com.jme3.util.SkyFactory;
import java.util.Collection;
import oculusvr.app.OVRApplication;
import oculusvr.input.OculusRift;
import oculusvr.util.OculusGuiNode;
import oculusvr.util.OculusGuiNode.POSITIONING_MODE;
import oculusvr.util.OculusRiftUtil;

public class TestOVRApplication extends OVRApplication {

    
    Spatial observer = new Node("");
    Node boxes = new Node("");
    
    public static TestOVRApplication myApp;
    
    boolean moveForward, moveBackwards, rotateLeft, rotateRight;
    Node scene;
    public static void main(String[] args) {
        
        
        myApp = new TestOVRApplication();
        myApp.configureOVRApp(false, false, true);
        myApp.start();
        
    }

    // I have to do this crazy function to set anisotropic filtering because I don't know
    // how to set it in the wildhouse.zip main.scene directly :-P
    public void setFilter(Node n) {
        for(Spatial s : n.getChildren()) {
            if( s instanceof Geometry ) {
                Material mat = ((Geometry)s).getMaterial();
                if( mat != null ) {
                    for(MatParam mp : mat.getParams()) {
                        if( mp.getVarType() == VarType.Texture2D ) {
                            ((Texture)mp.getValue()).setAnisotropicFilter(16);
                        }
                    }
                }
            } else if( s instanceof Node ) {
                setFilter((Node)s);
            }
        }
        
    }

    @Override
    public void simpleInitApp() {
        super.simpleInitApp();
        this.flyCam.setMoveSpeed(10);
        Node mainScene=new Node();
        scene = new Node();
        assetManager.registerLocator("assets/Scenes/wildhouse.zip", ZipLocator.class);        
        scene.attachChild(assetManager.loadModel("main.scene"));
        rootNode.attachChild(scene);
        rootNode.attachChild(SkyFactory.createSky(
                    assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        
        Geometry box = new Geometry("", new Box(5,5,5));
        Material m = new Material(getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        
        // aniscopic filtering really helps!
        setFilter(scene);
        
        // gui element
        Picture test = new Picture("testpic");
        test.setImage(assetManager, "Textures/crosshair.png", true);
        test.setWidth(128f);
        test.setHeight(128f);
        test.setPosition(settings.getWidth() * 0.5f - 64f, settings.getHeight() * 0.5f - 64f);
        guiNode.attachChild(test);
        
        box.setMaterial(m);
        
        Geometry box2 = box.clone();
        box2.move(15, 0, 0);
        box2.setMaterial(m);
        Geometry box3 = box.clone();
        box3.move(-15, 0, 0);
        box3.setMaterial(m);
        
        
        boxes.attachChild(box);
        boxes.attachChild(box2);
        boxes.attachChild(box3);
        scene.attachChild(boxes);
        
        observer.setLocalTranslation(new Vector3f(0.0f, 0.0f, -10.0f));//observer.setLocalTranslation(new Vector3f(0,0,5));
        
        //observer.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        observer.addControl(ovrAppState.getCameraControl());
        mainScene.attachChild(observer);
        
        DirectionalLight sun = new DirectionalLight();
        Vector3f lightDir=new Vector3f(-0.37352666f, -0.50444174f, -0.7784704f);
        sun.setDirection(lightDir);
        sun.setColor(ColorRGBA.White.clone().multLocal(2));
        scene.addLight(sun);

        mainScene.attachChild(scene);
        rootNode.attachChild(mainScene);

        initInputs();
    }

     private void initInputs() {
        inputManager.addMapping("toggle", new KeyTrigger(KeyInput.KEY_SPACE));
         inputManager.addMapping("incShift", new KeyTrigger(KeyInput.KEY_Q));
         inputManager.addMapping("decShift", new KeyTrigger(KeyInput.KEY_E));
         inputManager.addMapping("forward", new KeyTrigger(KeyInput.KEY_W));
         inputManager.addMapping("back", new KeyTrigger(KeyInput.KEY_S));
         inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_A));
         inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        ActionListener acl = new ActionListener() {

            public void onAction(String name, boolean keyPressed, float tpf) {
                if(name.equals("incShift") && keyPressed){
                    OculusRift.getAppState().getGuiNode().adjustGuiDistance(-0.1f);
                }else if(name.equals("decShift") && keyPressed){
                    OculusRift.getAppState().getGuiNode().adjustGuiDistance(0.1f);
                }
                if( name.equals("toggle") ) {
                    OculusRift.getAppState().getGuiNode().positionGui();
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
    }
     
     private float distance = 100f;
     private float prod = 0f;
     
     @Override
     public void simpleUpdate(float tpf){
         prod+=tpf;
         distance = 100f * FastMath.sin(prod);
         boxes.setLocalTranslation(0, 0, 200f+ distance);
         
         if(moveForward){
             observer.move(ovrAppState.getCameraControl().getLookDirection().getRotationColumn(2).mult(10f*tpf));
         }
         if(moveBackwards){
             observer.move(ovrAppState.getCameraControl().getLookDirection().getRotationColumn(2).mult(-tpf*10f));
         }
         if(rotateLeft){
             observer.rotate(0, 0.5f*tpf, 0);
         }
         if(rotateRight){
             observer.rotate(0, -0.5f*tpf, 0);
         }
     }

}

