/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.test.oculusvr;

import com.jme3.animation.AnimEventListener;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.StereoCamAppState;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.control.StereoCameraControl;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

/**
 *
 * @author Rickard
 */
public class TestAppState extends SimpleApplication implements AnalogListener{

    private StereoCamAppState oas;
    private StereoCameraControl camControl;
    private CameraNode camNode;
    private Node origin = new Node("Origin");
    
    private Geometry box;
    private Vector3f camDirection = new Vector3f();
    
    public static void main(String[] args) {
        TestAppState app = new TestAppState();
        AppSettings settings = new AppSettings(false);
        app.setSettings(settings);
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        oas = new StereoCamAppState();
        stateManager.attach(oas);
        
        camControl = oas.getCameraControl();
        
        camNode = new CameraNode("CamNode", camControl);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        camNode.setLocalTranslation(new Vector3f(0, 1.75f, 0.25f));
        camNode.setEnabled(true);
        
        origin.attachChild(camNode);
        
        rootNode.attachChild(origin);
        box = new Geometry("Box", new Box(1,1,1));
        box.move(0, 1, 5);
        Material mat_default = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        box.setMaterial(mat_default);
        rootNode.attachChild(box);
        
        inputManager.addMapping("lookRight", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("lookLeft", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("lookUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("lookDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addListener(this, "lookRight", "lookLeft", "lookUp", "lookDown");
    }
    
    @Override
    public void simpleUpdate(float tpf) {
       
        camControl.setLookDirection(new Quaternion().fromAngles(camDirection.x, camDirection.y, camDirection.z));
    }
    
    public void onAnalog(String binding, float value, float tpf) {
        if (binding.equals("lookLeft")){
            camDirection.addLocal(0, -5f * tpf, 0);
        } else if (binding.equals("lookRight")){
            camDirection.addLocal(0, 5f * tpf, 0);
        } else if (binding.equals("lookUp")){
            camDirection.addLocal(5f * tpf, 0, 0);
        } else if (binding.equals("lookDown")){
            camDirection.addLocal(-5f * tpf, 0, 0);
        }
    }
    
}
