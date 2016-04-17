/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.lwjgl.GlfwMouseInput;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Vector2f;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
import jmevr.app.VRApplication;
import jmevr.input.VRInput;
import jmevr.input.VRInput.VRINPUT_TYPE;

/**
 *
 * @author Phr00t
 */
public class VRMouseManager {
 
    private static Picture mouseImage;
    private static final Vector2f cursorPos = new Vector2f();
    private static float ySize, sensitivity = 4f, acceleration = 2.4f;
    
    protected static void init() {
        // load default mouseimage
        mouseImage = new Picture("mouse");
        setImage("jmevr/util/mouse.png");
        // hide default cursor by making it invisible        
        MouseInput mi = VRApplication.getMainVRApp().getContext().getMouseInput();
        if( mi instanceof GlfwMouseInput ) ((GlfwMouseInput)mi).hideActiveCursor();
        centerMouse();
    }
    
    public static void setSpeed(float sensitivity, float acceleration) {
        VRMouseManager.sensitivity = sensitivity;
        VRMouseManager.acceleration = acceleration;
    }
    
    public static float getSpeedSensitivity() {
        return sensitivity;
    }
    
    public static float getSpeedAcceleration() {
        return acceleration;
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
    
    public static void updateAnalogAsMouse(int inputIndex, AnalogListener mouseListener, String mouseXName, String mouseYName, float tpf) {
        // got a tracked controller to use as the "mouse"
        if( VRApplication.isInVR() == false || VRInput.isInputDeviceTracking(inputIndex) == false ) return;
        Vector2f tpDelta = VRInput.getAxisDeltaSinceLastCall(inputIndex, VRINPUT_TYPE.ViveTouchpadAxis);
        float Xamount = (float)Math.pow(Math.abs(tpDelta.x) * sensitivity, acceleration);
        float Yamount = (float)Math.pow(Math.abs(tpDelta.y) * sensitivity, acceleration);
        if( tpDelta.x < 0f ) Xamount = -Xamount;
        if( tpDelta.y < 0f ) Yamount = -Yamount;
        if( mouseListener != null ) {
            if( tpDelta.x != 0f && mouseXName != null ) mouseListener.onAnalog(mouseXName, Xamount * 0.2f, tpf);
            if( tpDelta.y != 0f && mouseYName != null ) mouseListener.onAnalog(mouseYName, Yamount * 0.2f, tpf);            
        }
        if( VRApplication.getMainVRApp().getInputManager().isCursorVisible() ) {
            float mvX = Xamount * 133f;
            float mvY = Yamount * 133f;
            cursorPos.x -= mvX;
            cursorPos.y -= mvY;
            Vector2f maxsize = VRGuiManager.getCanvasSize();
            if( cursorPos.x > maxsize.x ) cursorPos.x = maxsize.x;
            if( cursorPos.x < 0f ) cursorPos.x = 0f;
            if( cursorPos.y > maxsize.y ) cursorPos.y = maxsize.y;
            if( cursorPos.y < 0f ) cursorPos.y = 0f;
        }
    }
    
    public static Vector2f getCursorPosition() {
        if( VRApplication.isInVR() ) {
            return cursorPos;
        }
        return VRApplication.getMainVRApp().getInputManager().getCursorPosition();
    }
    
    public static void centerMouse() {
        // set mouse in center of the screen if newly added
        Vector2f size = VRGuiManager.getCanvasSize();
        if( VRApplication.isInVR() ) {
            cursorPos.x = size.x / 2f;
            cursorPos.y = size.y / 2f;
        }
        AppSettings as = VRApplication.getMainVRApp().getContext().getSettings();
        MouseInput mi = VRApplication.getMainVRApp().getContext().getMouseInput();
        if( mi instanceof GlfwMouseInput ) ((GlfwMouseInput)mi).setCursorPosition((int)(as.getWidth() / 2f), (int)(as.getHeight() / 2f));
    }
    
    protected static void update(float tpf) {
        // if we are showing the cursor, add our picture as it
        VRApplication vrapp = VRApplication.getMainVRApp();
        if( vrapp.getInputManager().isCursorVisible() ) {
            if( mouseImage.getParent() == null ) {
                VRApplication.getMainVRApp().getGuiNode().attachChild(mouseImage);                
                centerMouse();
            }
            // handle mouse movements, which may be in addition to (or exclusive from) tracked movement
            MouseInput mi = VRApplication.getMainVRApp().getContext().getMouseInput();
            if( mi instanceof GlfwMouseInput ) {
                Vector2f winratio = VRGuiManager.getCanvasToWindowRatio();
                cursorPos.x += ((GlfwMouseInput)mi).getLastDeltaX() * winratio.x;
                cursorPos.y += ((GlfwMouseInput)mi).getLastDeltaY() * winratio.y;
                ((GlfwMouseInput)mi).clearDeltas();
            }
            // ok, update the cursor graphic position
            Vector2f currentPos = getCursorPosition();
            mouseImage.setLocalTranslation(currentPos.x, currentPos.y - ySize, VRGuiManager.getGuiDistance() + 1f);
        } else if( mouseImage.getParent() != null ) {
            mouseImage.removeFromParent();
        }
    }
    
}
