/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.math.FastMath;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;

/**
 *
 * @author reden
 */
public class VRMouseAppState extends AbstractAppState {

    private Picture cursor;
    private SimpleApplication app;

    private RawInputListener inputListener;
    
    @Override
    public void initialize(AppStateManager stateManager, final Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        inputListener = new RawInputListener() {

        private int width = app.getCamera().getWidth();
        private int height = app.getCamera().getHeight();
        private int halfHeight = height / 4;
        private int halhWIdth = width / 2;
        
        private float x = 0, y = 0;

        public void beginInput() {
        }
        public void endInput() {
        }
        public void onJoyAxisEvent(JoyAxisEvent evt) {
        }
        public void onJoyButtonEvent(JoyButtonEvent evt) {
        }
        public void onMouseMotionEvent(MouseMotionEvent evt) {
            x += evt.getDX() * 0.9f;
            y += evt.getDY() * 0.5f ;

            // Prevent mouse from leaving screen
            x = FastMath.clamp(x, 0, width);
            y = FastMath.clamp(y, 0, height);

            // adjust for hotspot
            cursor.setPosition(x, y + halfHeight - 64);
        }
        public void onMouseButtonEvent(MouseButtonEvent evt) {
        }
        public void onKeyEvent(KeyInputEvent evt) {
        }
        public void onTouchEvent(TouchEvent evt) {
        }
    };
        
        Texture tex = app.getAssetManager().loadTexture("Textures/crosshair.png");

        cursor = new Picture("cursor");
        cursor.setTexture(app.getAssetManager(), (Texture2D) tex, true);
        cursor.setWidth(64);
        cursor.setHeight(64);
        //cursor.setLocalScale(0.5f);
        this.app.getGuiNode().attachChild(cursor);

        app.getInputManager().addRawInputListener(inputListener);
    }
    
    
    
}
