/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.gui.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import oculusvr.util.OculusGuiNode;

/**
 *
 * @author reden
 * Tutorial at: http://www.softwarepioneering.com/2014/12/free-floating-vr-menu-in-jmonkeyengine.html
 */
public class MenuAppState extends AbstractAppState{

    private int width;
    private int height;
    private OculusGuiNode guiNode;
    private Node menuNode;
    
    private Ray sightRay;
    
    private SimpleApplication app;
    
    private CollisionResults collisionResults = new CollisionResults();
    private Vector2f screenCenter;
    
    private Geometry selectedGeometry;
    private float timer;
    
    private static float ACTIVATE_TIME  = 5f;
    private static ColorRGBA DEFAULT_COLOR = ColorRGBA.DarkGray;
    private static ColorRGBA SELECT_COLOR = ColorRGBA.Gray;
    private static ColorRGBA ACTIVATE_COLOR = ColorRGBA.White;

    public MenuAppState(int width, int height, OculusGuiNode guiNode) {
        this.width = width;
        this.height = height;
        this.guiNode = guiNode;
        screenCenter = new Vector2f(width * 0.5f, height * 0.5f);
    }
    
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        
        menuNode = new Node("Menu");
        sightRay = new Ray();
        
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", DEFAULT_COLOR);

        for(int x = -2; x < 2; x++){
            for(int y = -2; y < 2; y++){
                Geometry menuQuad = new Geometry("Test ", new Quad(width * 0.25f, height * 0.25f));
                menuQuad.setMaterial(mat.clone());
                menuQuad.setLocalTranslation(x * width * 0.25f, y * height * 0.25f, 0);
                menuNode.attachChild(menuQuad);
            }
        }
        guiNode.attachChild(menuNode);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(enabled && !this.isEnabled()){
            guiNode.attachChild(menuNode);
        } else if (!enabled && this.isEnabled()){
            guiNode.detachChild(menuNode);
        }
    }

    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        sightRay.setOrigin(app.getCamera().getWorldCoordinates(screenCenter, 0f));
        sightRay.setDirection(app.getCamera().getDirection());
        menuNode.collideWith(sightRay, collisionResults);
        if(collisionResults.getClosestCollision() != null){
            Geometry g = collisionResults.getClosestCollision().getGeometry();
            if(g != selectedGeometry){
                unselectItem();
                selectItem(g);
            }
        } else {
            unselectItem();
        }
        collisionResults.clear();
        
        if(selectedGeometry != null){
            timer += tpf;
            float interpolateValue = timer / ACTIVATE_TIME;
            ((ColorRGBA)selectedGeometry.getMaterial().getParam("Color").getValue()).interpolateLocal(SELECT_COLOR, ACTIVATE_COLOR, interpolateValue);
            if(timer > ACTIVATE_TIME){
                activateItem();
            }
        }
    }
    
    private void selectItem(Geometry g){
        selectedGeometry = g;
        selectedGeometry.getMaterial().setColor("Color", SELECT_COLOR.clone());
        timer = 0;
    }
    
    private void unselectItem(){
        if(selectedGeometry != null){
            selectedGeometry.getMaterial().setColor("Color", DEFAULT_COLOR.clone());
            selectedGeometry = null;
        }
    }
    
    private void activateItem(){
        // do stuff!
    }
}
