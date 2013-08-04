package com.jme3.test.oculusvr;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.HttpZipLocator;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.io.File;
import com.jme3.post.BarrelDistortionFilter;
import com.jme3.post.BarrelDistortionFilterFake;
import com.jme3.system.AppSettings;
import oculusvr.input.HMDInfo;

public class TestBarrelDistortion extends SimpleApplication {

    private FilterPostProcessor fpp, fpp2;
    private boolean enabled=true;
    private BarrelDistortionFilterFake filterLeft, filterRight;
    Camera cam2;

    // set default for applets
    private static boolean useHttp = true;

    public static void main(String[] args) {
        File file = new File("wildhouse.zip");
        if (file.exists()) {
            useHttp = false;
        }
        TestBarrelDistortion app = new TestBarrelDistortion();
        AppSettings settings = new AppSettings(false);
        settings.setFrameRate(-1);
                
        app.setSettings(settings);
        app.start();
    }

    public void simpleInitApp() {
        this.flyCam.setMoveSpeed(10);
        Node mainScene=new Node();
        cam.setLocation(new Vector3f(-27.0f, 1.0f, 75.0f));
        cam.setRotation(new Quaternion(0.03f, 0.9f, 0f, 0.4f));
        cam.setViewPort(0.0f, 0.5f, 0.0f, 1.0f);
        
        cam2 = cam.clone();
        cam2.setLocation(new Vector3f(-26.5f, 1.0f, 75.0f));
        cam2.setRotation(new Quaternion(0.03f, 0.9f, 0.25f, 0.4f));
        cam2.setViewPort(0.5f, 1f, 0.0f, 1f);
        ViewPort viewPort2 = renderManager.createMainView("PiP", cam2);
        viewPort2.setClearFlags(true, true, true);
        viewPort2.attachScene(rootNode);
        // load sky
        //mainScene.attachChild(SkyFactory.createSky(assetManager, "Common/Textures/Sky/Bright/BrightSky.dds", false));

        // create the geometry and attach it
        // load the level from zip or http zip
        if (useHttp) {
            assetManager.registerLocator("http://jmonkeyengine.googlecode.com/files/wildhouse.zip", HttpZipLocator.class);
        } else {
            assetManager.registerLocator("wildhouse.zip", ZipLocator.class);
        }
        Spatial scene = assetManager.loadModel("main.scene");

        DirectionalLight sun = new DirectionalLight();
        Vector3f lightDir=new Vector3f(-0.37352666f, -0.50444174f, -0.7784704f);
        sun.setDirection(lightDir);
        sun.setColor(ColorRGBA.White.clone().multLocal(2));
        scene.addLight(sun);


        mainScene.attachChild(scene);
        rootNode.attachChild(mainScene);

        HMDInfo deviceInfo = new HMDInfo();
        deviceInfo.setHResolution(settings.getWidth());
        deviceInfo.setVResolution(settings.getHeight());
        
        fpp=new FilterPostProcessor(assetManager);
        //fpp.setNumSamples(4);
        filterLeft=new BarrelDistortionFilterFake(true);
        
        fpp.addFilter(filterLeft);
        viewPort.addProcessor(fpp);

        fpp2 =new FilterPostProcessor(assetManager);
        filterRight =new BarrelDistortionFilterFake(false);
        fpp2.addFilter(filterRight);
        viewPort2.addProcessor(fpp2);
        
        initInputs();
    }

     private void initInputs() {
        inputManager.addMapping("toggle", new KeyTrigger(KeyInput.KEY_SPACE));


        ActionListener acl = new ActionListener() {

            public void onAction(String name, boolean keyPressed, float tpf) {
                if (name.equals("toggle") && keyPressed) {
                    if(enabled){
                        enabled=false;
                        viewPort.removeProcessor(fpp);
                    }else{
                        enabled=true;
                        viewPort.addProcessor(fpp);
                    }
                }

            }
        };

        inputManager.addListener(acl, "toggle");
    }
     
    @Override
     public void update(){
         super.update();
         cam2.setRotation(cam.getRotation());
         //cam2.setLocation(cam.getLocation().add(0.5f, 0, 0));
     }
}

