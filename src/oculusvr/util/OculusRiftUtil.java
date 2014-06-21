/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.util;

import com.jme3.math.Matrix4f;
import com.jme3.texture.FrameBuffer;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.FovPort;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.HmdDesc;
import com.oculusvr.capi.OvrLibrary;
import com.oculusvr.capi.OvrMatrix4f;
import com.oculusvr.capi.OvrVector2i;
import com.oculusvr.capi.RenderAPIConfig;
import com.oculusvr.capi.TextureHeader;

/**
 *
 * @author reden
 */
public class OculusRiftUtil {

    public static EyeRenderDesc[] configureRendering(Hmd hmd, HmdDesc hmdDesc) {
        EyeRenderDesc[] configureResult;
        
        FovPort fovPorts[] = (FovPort[]) new FovPort().toArray(2);
        fovPorts[0] = hmdDesc.DefaultEyeFov[0];
        fovPorts[1] = hmdDesc.DefaultEyeFov[1];
    
        RenderAPIConfig rc = new RenderAPIConfig();
        rc.Header.API = OvrLibrary.ovrRenderAPIType.ovrRenderAPI_OpenGL;
        rc.Header.RTSize = hmdDesc.Resolution;
        rc.Header.Multisample = 0;
        int distortionCaps = OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Chromatic
                             | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_TimeWarp
                             | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Vignette
                             | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_NoSwapBuffers;


        configureResult = hmd.configureRendering(rc, distortionCaps, fovPorts);

        System.out.println(configureResult[0]);
        if (null == configureResult) {
            throw new IllegalStateException("Unable to configure rendering");
        }
        return configureResult;
    }
    
    public static Matrix4f toMatrix4f(OvrMatrix4f m) {
        return new Matrix4f(m.M).transpose();
    }
}
