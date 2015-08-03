/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.input.MouseInput;
import com.jme3.input.lwjgl.LwjglMouseInput;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Vector2f;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
import jmevr.app.VRApplication;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Phr00t
 */
public class VRMouseManager {
 
    private static Picture mouseImage;
    private static float ySize;
    
    protected static void init() {
        // load default mouseimage
        mouseImage = new Picture("mouse");
        setImage("jmevr/util/mouse.png");
        // hide default cursor by making it invisible
        try {
            Cursor emptyCursor = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
            Mouse.setNativeCursor(emptyCursor);        
            MouseInput mi = VRApplication.getMainVRApp().getContext().getMouseInput();
            if( mi instanceof LwjglMouseInput ) ((LwjglMouseInput)mi).setNativeCursor(emptyCursor);
        } catch(Exception e) { }
    }
    
    public static void setImage(String texture) {
        if( VRApplication.isInVR() == false ) return;
        Texture tex = VRApplication.getMainVRApp().getAssetManager().loadTexture(texture);
        mouseImage.setTexture(VRApplication.getMainVRApp().getAssetManager(), (Texture2D)tex, true);
        ySize = tex.getImage().getHeight();
        mouseImage.setHeight(ySize);
        mouseImage.setWidth(tex.getImage().getWidth());
        mouseImage.getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        mouseImage.getMaterial().getAdditionalRenderState().setDepthWrite(false);
    }
    
    protected static void update(float tpf) {
        // if we are showing the cursor, add our picture as it
        VRApplication vrapp = VRApplication.getMainVRApp();
        if( vrapp.getInputManager().isCursorVisible() ) {
            if( mouseImage.getParent() == null ) {
                VRApplication.getVRGuiNode().attachChild(mouseImage);
            }
            Vector2f mousePos = vrapp.getInputManager().getCursorPosition();
            mouseImage.setLocalTranslation(mousePos.x, mousePos.y - ySize, VRApplication.getVRGuiNode().getGuiDistance() + 1f);
        } else if( mouseImage.getParent() != null ) {
            mouseImage.removeFromParent();
        }
    }
    
}
