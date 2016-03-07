package jmevr.input;

import com.jme3.math.Vector2f;
import java.nio.FloatBuffer;
import jopenvr.JOpenVRLibrary;
import jopenvr.VR_IVRChaperone_FnTable;

public class VRBounds {

    private static VR_IVRChaperone_FnTable vrChaperone;
    private static Vector2f playSize;
    
    public static boolean init() {
        if( vrChaperone == null ) {
            vrChaperone = new VR_IVRChaperone_FnTable(JOpenVRLibrary.VR_GetGenericInterface(JOpenVRLibrary.IVRChaperone_Version, OpenVR.hmdErrorStore));
            if( vrChaperone != null ) {
                vrChaperone.setAutoSynch(false);
                vrChaperone.read();
                FloatBuffer fbX = FloatBuffer.allocate(1);
                FloatBuffer fbZ = FloatBuffer.allocate(1);
                vrChaperone.GetPlayAreaSize.apply(fbX, fbZ);
                playSize = new Vector2f(fbX.get(0), fbZ.get(0));
                return true; // init success
            }
            return false; // failed to init
        }
        return true; // already initialized
    }
    
    public static Vector2f getPlaySize() {
        return playSize;
    }
    
}

