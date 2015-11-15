package jmevr.input;

import com.jme3.math.Vector2f;
import com.sun.jna.Pointer;
import java.nio.FloatBuffer;
import jopenvr.JOpenVRLibrary;

public class VRBounds {

    private static Pointer vrChaperone;
    private static Vector2f playSize;
    
    public static boolean init() {
        vrChaperone = JOpenVRLibrary.VR_GetGenericInterface(JOpenVRLibrary.IVRChaperone_Version, OpenVR.hmdErrorStore);
        if( vrChaperone != null ) {
            FloatBuffer fbX = FloatBuffer.allocate(1);
            FloatBuffer fbZ = FloatBuffer.allocate(1);
            JOpenVRLibrary.VR_IVRChaperone_GetPlayAreaSize(vrChaperone, fbX, fbZ);
            playSize = new Vector2f(fbX.get(0), fbZ.get(0));
            return true;
        }
        return false;
    }
    
    public static Vector2f getPlaySize() {
        return playSize;
    }
    
}

