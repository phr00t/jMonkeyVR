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
        for (int eye = 0; eye < 2; ++eye) {
            {
                // JNA weirdness 1
                FovPort defaultEyeFov = hmdDesc.DefaultEyeFov[eye];
                fovPorts[eye] = defaultEyeFov;
                FovPort fovPort = new FovPort();
                fovPort.DownTan = defaultEyeFov.DownTan;
                fovPort.UpTan = defaultEyeFov.UpTan;
                fovPort.LeftTan = defaultEyeFov.LeftTan;
                fovPort.RightTan = defaultEyeFov.RightTan;
            }
        }


        RenderAPIConfig rc = new RenderAPIConfig();
        rc.Header.API = OvrLibrary.ovrRenderAPIType.ovrRenderAPI_OpenGL;
        rc.Header.RTSize = hmdDesc.Resolution;
        rc.Header.Multisample = 1;
        for (int i = 0; i < rc.PlatformData.length; ++i) {
            rc.PlatformData[i] = 0;
        }
        rc.write();
        int distortionCaps = 0
                | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Chromatic
                | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_TimeWarp
                | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Vignette
                | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_NoSwapBuffers;


        configureResult = hmd.configureRendering(
                rc, distortionCaps, fovPorts);

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
