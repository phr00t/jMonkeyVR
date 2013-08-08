package com.jme3.test.oculusvr;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.app.state.StereoCamAppState;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
import com.jme3.math.Vector3f;
import java.util.logging.Level;
import java.util.logging.Logger;
import oculusvr.input.OculusRiftReader;

public class TestGui extends SimpleApplication {

    // set default for applets
    private static boolean useHttp = true;
    private static StereoCamAppState stereoCamAppState;
    private static OculusRiftReader orr;
    Spatial observer = new Node("");

    private String txtB =
    "ABCDEFGHIKLMNOPQRSTUVWXYZ1234567 890`~!@#$%^&*()-=_+[]\\;',./{}|:<>?";

    private BitmapText txt;
    private BitmapText txt2;
    
    boolean moveForward, moveBackwards, rotateLeft, rotateRight;
    Node scene;
    public static void main(String[] args) {
        
        TestGui app = new TestGui();
        app.start();
    }

    public void simpleInitApp() {
        this.flyCam.setMoveSpeed(10);
        Node mainScene=new Node();

        stereoCamAppState = new StereoCamAppState();
        stateManager.attach(stereoCamAppState);
        
        scene = new Node();
        
        observer.setLocalTranslation(new Vector3f(0.0f, 0.0f, -10.0f));//observer.setLocalTranslation(new Vector3f(0,0,5));
        
        observer.addControl(stereoCamAppState.getCameraControl());
        mainScene.attachChild(observer);
       
        rootNode.attachChild(mainScene);
        
        BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
        txt = new BitmapText(fnt, false);
        txt.setBox(new Rectangle(settings.getWidth()*0.25f, settings.getHeight()*0.25f, settings.getWidth() * 0.75f, settings.getHeight() * 0.75f));
        txt.setSize(fnt.getPreferredSize() * 2f);
        txt.setText(txtB);
        txt.setLocalTranslation(0, txt.getHeight()- 400, 0);
        guiNode.attachChild(txt);

    }

    @Override
     public void update(){
         super.update();
     }

    public static OculusRiftReader getOculusRiftReader(){
        return orr;
    }

    @Override
    public void destroy() {
        
        super.destroy();
        if(orr != null){
            orr.destroy();
        }
        
    }
    
    
}

