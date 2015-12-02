/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.input.MouseInput;
import com.jme3.input.lwjgl.GlfwMouseInput;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Vector2f;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
import jmevr.app.VRApplication;

/**
 *
 * @author Phr00t
 */
public class VRMouseManager {
 
    private static Picture mouseImage;
    private static final Vector2f temp2f = new Vector2f();
    private static float ySize;
    
    protected static void init() {
        // load default mouseimage
        mouseImage = new Picture("mouse");
        setImage("jmevr/util/mouse.png");
        // hide default cursor by making it invisible        
        MouseInput mi = VRApplication.getMainVRApp().getContext().getMouseInput();
        if( mi instanceof GlfwMouseInput ) ((GlfwMouseInput)mi).hideActiveCursor();
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
    
    public static Vector2f getCursorPosition() {
        temp2f.set(VRApplication.getMainVRApp().getInputManager().getCursorPosition());
        if( VRApplication.isInVR() ) {
            AppSettings as = VRApplication.getMainVRApp().getContext().getSettings();
            Vector2f canvasSize = VRGuiManager.getCanvasSize();
            temp2f.x *= canvasSize.x / as.getWidth();
            temp2f.y *= canvasSize.y / as.getHeight();
        }
        return temp2f;
    }
    
    protected static void update(float tpf) {
        // if we are showing the cursor, add our picture as it
        VRApplication vrapp = VRApplication.getMainVRApp();
        if( vrapp.getInputManager().isCursorVisible() ) {
            if( mouseImage.getParent() == null ) {
                VRApplication.getMainVRApp().getGuiNode().attachChild(mouseImage);
                // set mouse in center of the screen if newly added
                AppSettings as = VRApplication.getMainVRApp().getContext().getSettings();
                MouseInput mi = VRApplication.getMainVRApp().getContext().getMouseInput();
                if( mi instanceof GlfwMouseInput ) ((GlfwMouseInput)mi).setCursorPosition(as.getWidth() / 2, as.getHeight() / 2);
            }
            Vector2f mousePos = getCursorPosition();
            mouseImage.setLocalTranslation(mousePos.x, mousePos.y - ySize, VRGuiManager.getGuiDistance() + 1f);
        } else if( mouseImage.getParent() != null ) {
            mouseImage.removeFromParent();
        }
    }
    
}
