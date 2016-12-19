/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.input;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import jmevr.app.VRApplication;
import jmevr.util.VRViewManager;
import osvrclientkit.OsvrClientKitLibrary;
import osvrclientkit.OsvrClientKitLibrary.OSVR_ClientInterface;
import osvrclientreporttypes.OSVR_AnalogReport;
import osvrclientreporttypes.OSVR_ButtonReport;
import osvrclientreporttypes.OSVR_Pose3;
import osvrinterface.OsvrInterfaceLibrary;
import osvrtimevalue.OSVR_TimeValue;

/**
 *
 * @author Phr00t
 */
public class OSVRInput implements VRInputAPI {

    // position example: https://github.com/OSVR/OSVR-Core/blob/master/examples/clients/TrackerState.c
    // button example: https://github.com/OSVR/OSVR-Core/blob/master/examples/clients/ButtonCallback.c
    // analog example: https://github.com/OSVR/OSVR-Core/blob/master/examples/clients/AnalogCallback.c
    
    private static final int ANALOG_COUNT = 2, BUTTON_COUNT = 8, CHANNEL_COUNT = 3;
    
    OSVR_ClientInterface[][] buttons;
    OSVR_ClientInterface[][][] analogs;
    OSVR_ClientInterface[] hands;
    
    OSVR_Pose3[] handState;
    Callback buttonHandler, analogHandler;
    OSVR_TimeValue tv = new OSVR_TimeValue();
    boolean[] isHandTracked = new boolean[2];
    
    private float[][][] analogState;
    private float[][] buttonState;
    
    private final Quaternion tempq = new Quaternion();
    private final Vector3f tempv = new Vector3f();
    private final Vector2f temp2 = new Vector2f();
    private final boolean[][] buttonDown = new boolean[16][16];
    
    public static byte[] getButtonString(boolean left, byte index) {
        if( left ) {
            return new byte[] { '/', 'c', 'o', 'n', 't', 'r', 'o', 'l', 'l', 'e', 'r', '/', 'l', 'e', 'f', 't', '/', index, (byte)0 };
        }
        return new byte[] { '/', 'c', 'o', 'n', 't', 'r', 'o', 'l', 'l', 'e', 'r', '/', 'r', 'i', 'g', 'h', 't', '/', index, (byte)0 };
    }
    
    public static byte[] leftHand = { '/', 'm', 'e', '/', 'h', 'a', 'n', 'd', 's', '/', 'l', 'e', 'f', 't', (byte)0 };
    public static byte[] rightHand = { '/', 'm', 'e', '/', 'h', 'a', 'n', 'd', 's', '/', 'r', 'i', 'g', 'h', 't', (byte)0 };

    @Override
    public boolean isButtonDown(int controllerIndex, OpenVRInput.VRINPUT_TYPE checkButton) {
        return buttonState[controllerIndex][checkButton.getValue()] != 0f;
    }

    @Override
    public boolean wasButtonPressedSinceLastCall(int controllerIndex, OpenVRInput.VRINPUT_TYPE checkButton) {
        boolean buttonDownNow = isButtonDown(controllerIndex, checkButton);
        int checkButtonValue = checkButton.getValue();
        boolean retval = buttonDownNow == true && buttonDown[controllerIndex][checkButtonValue] == false;
        buttonDown[controllerIndex][checkButtonValue] = buttonDownNow;
        return retval;
    }

    @Override
    public void resetInputSinceLastCall() {
        for(int i=0;i<16;i++) {
            for(int j=0;j<16;j++) {
                buttonDown[i][j] = false;
            }
        }        
    }

    @Override
    public Vector2f getAxisDeltaSinceLastCall(int controllerIndex, OpenVRInput.VRINPUT_TYPE forAxis) {
        return Vector2f.ZERO;
    }

    @Override
    public Vector3f getVelocity(int controllerIndex) {
        return Vector3f.ZERO;
    }

    @Override
    public Vector3f getAngularVelocity(int controllerIndex) {
        return Vector3f.ZERO;
    }

    @Override
    public Vector2f getAxis(int controllerIndex, OpenVRInput.VRINPUT_TYPE forAxis) {
        temp2.x = analogState[controllerIndex][forAxis.getValue()][0];
        temp2.y = analogState[controllerIndex][forAxis.getValue()][1];
        return temp2;
    }
    
    private OSVR_ClientInterface getInterface(byte[] str) {
        PointerByReference pbr = new PointerByReference();
        OsvrClientKitLibrary.osvrClientGetInterface((OsvrClientKitLibrary.OSVR_ClientContext)VRApplication.getVRHardware().getVRSystem(), str, pbr);
        return new OSVR_ClientInterface(pbr.getValue());
    }

    @Override
    public boolean init() {
        
        buttonHandler = new Callback() {
            public void invoke(OSVR_ClientInterface userdata, Pointer timeval, OSVR_ButtonReport report) {
                for(int i=0;i<2;i++) {
                    for(int j=0;j<BUTTON_COUNT;j++) {
                        if( userdata == buttons[i][j] ) {
                            buttonState[i][j] = report.state;
                            return;
                        }
                    }
                }
            }                
        };  
        analogHandler = new Callback() {
            public void invoke(OSVR_ClientInterface userdata, Pointer timeval, OSVR_AnalogReport report) {
                for(int i=0;i<2;i++) {
                    for(int j=0;j<ANALOG_COUNT;j++) {
                        for(int k=0;k<CHANNEL_COUNT;k++) {
                        if( userdata == buttons[i][j] ) {
                            analogState[i][j][k] = (float)report.state;
                            return;
                            }
                        }
                    }
                }
            }                
        };  
        
        buttons = new OSVR_ClientInterface[2][BUTTON_COUNT];
        analogs = new OSVR_ClientInterface[2][ANALOG_COUNT][CHANNEL_COUNT];
        buttonState = new float[2][BUTTON_COUNT];
        analogState = new float[2][ANALOG_COUNT][CHANNEL_COUNT];
        hands = new OSVR_ClientInterface[2];
        hands[0] = getInterface(leftHand);
        hands[1] = getInterface(rightHand);
        handState = new OSVR_Pose3[2];
        handState[0] = new OSVR_Pose3(); handState[1] = new OSVR_Pose3();
        for(int h=0;h<2;h++) {
            for(int i=0;i<BUTTON_COUNT;i++) {
                buttons[h][i] = getInterface(getButtonString(h==0, (byte)Integer.toString(i).toCharArray()[0]));
                OsvrClientKitLibrary.osvrRegisterButtonCallback(buttons[h][i], buttonHandler, buttons[h][i].getPointer()); 
            }
        }
        analogs[0][0][0] = getInterface(new byte[] { '/', 'c', 'o', 'n', 't', 'r', 'o', 'l', 'l', 'e', 'r', '/', 'l', 'e', 'f', 't', '/', 't', 'r', 'i', 'g', 'g', 'e', 'r', (byte)0 } );
        analogs[1][0][0] = getInterface(new byte[] { '/', 'c', 'o', 'n', 't', 'r', 'o', 'l', 'l', 'e', 'r', '/', 'r', 'i', 'g', 'h', 't', '/', 't', 'r', 'i', 'g', 'g', 'e', 'r', (byte)0 } );
        OsvrClientKitLibrary.osvrRegisterAnalogCallback(analogs[0][0][0], analogHandler, analogs[0][0][0].getPointer());
        OsvrClientKitLibrary.osvrRegisterAnalogCallback(analogs[1][0][0], analogHandler, analogs[1][0][0].getPointer());
        
        return true;
    }

    @Override
    public int getTrackedControllerCount() {
        return (isHandTracked[0]?1:0) + (isHandTracked[1]?1:0);
    }

    @Override
    public void _updateConnectedControllers() {
        
    }

    @Override
    public void _updateControllerStates() {
        for(int i=0;i<hands.length;i++) {
            isHandTracked[i] = OsvrInterfaceLibrary.osvrGetPoseState(hands[i], tv, handState[i]) == 0;
        }
    }

    @Override
    public Object getRawControllerState(int index) {
        return handState[index];
    }

    //@Override
    //public Matrix4f getPoseForInputDevice(int index) {
    //    return handState[i].
    //}

    @Override
    public boolean doWeHaveInputFocus() {
        return true;
    }

    @Override
    public boolean isInputDeviceTracking(int index) {
        return isHandTracked[index];
    }

    @Override
    public Quaternion getOrientation(int index) {
        tempq.set((float)-handState[index].rotation.data[1],
                  (float)handState[index].rotation.data[2],
                  (float)-handState[index].rotation.data[3],
                  (float)handState[index].rotation.data[0]);
        return tempq;
    }

    @Override
    public Vector3f getPosition(int index) {
        tempv.x = (float)-handState[index].translation.data[0];
        tempv.y = (float)handState[index].translation.data[1];
        tempv.z = (float)-handState[index].translation.data[2];
        return tempv;
    }

    /*
        where is the controller pointing, after all rotations are combined?
        depends on observer rotation, if any
    */
    public Quaternion getFinalObserverRotation(int index) {
        VRViewManager vrvm = VRApplication.getVRViewManager();
        if( vrvm == null || isInputDeviceTracking(index) == false ) return null;
        Object obs = VRApplication.getObserver();
        if( obs instanceof Camera ) {
            tempq.set(((Camera)obs).getRotation());
        } else {
            tempq.set(((Spatial)obs).getWorldRotation());
        }
        return tempq.multLocal(getOrientation(index));
    }
    
    /*
        where is the controller, after all positional tracking is complete?
        includes observer position, if any
    */    
    public Vector3f getFinalObserverPosition(int index) {
        VRViewManager vrvm = VRApplication.getVRViewManager();
        if( vrvm == null || isInputDeviceTracking(index) == false ) return null;
        Object obs = VRApplication.getObserver();
        Vector3f pos = getPosition(index);
        if( obs instanceof Camera ) {
            ((Camera)obs).getRotation().mult(pos, pos);
            return pos.addLocal(((Camera)obs).getLocation());
        } else {
            ((Spatial)obs).getWorldRotation().mult(pos, pos);
            return pos.addLocal(((Spatial)obs).getWorldTranslation());
        }
    } 

    @Override
    public void triggerHapticPulse(int controllerIndex, float seconds) {
        
    }
    
}
