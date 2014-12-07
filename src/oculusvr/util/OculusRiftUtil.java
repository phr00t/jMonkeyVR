/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.util;

import com.jme3.math.Matrix4f;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.FovPort;
import com.oculusvr.capi.Hmd;
import com.oculusvr.capi.OvrLibrary;
import com.oculusvr.capi.OvrLibrary.ovrHmdCaps;
import com.oculusvr.capi.OvrMatrix4f;
import com.oculusvr.capi.OvrSizei;
import com.oculusvr.capi.RenderAPIConfig;
import com.sun.jna.Pointer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import oculusvr.input.OculusRift;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;

/**
 *
 * @author reden
 */
public class OculusRiftUtil {

    private static boolean maxFOV = false, dis_vig = false;
    private static boolean customFOV = false;
    private static FovPort[] customFovPorts;

    public static void useMaxEyeFov(boolean enable) {
        maxFOV = enable;
    }

    public static void disableVignette(boolean disable) {
        dis_vig = disable;
    }

    public static EyeRenderDesc[] configureRendering(Hmd hmd, int width, int height, int samples) {
        EyeRenderDesc[] configureResult;

        FovPort fovPorts[] = (FovPort[]) new FovPort().toArray(2);

        if (maxFOV) {
            fovPorts[0] = hmd.MaxEyeFov[0];
            fovPorts[1] = hmd.MaxEyeFov[1];
        } else if (customFOV) {
            fovPorts[0] = hmd.DefaultEyeFov[0];
            fovPorts[1] = hmd.DefaultEyeFov[1];
            applyFovPort(customFovPorts[0], fovPorts[0]);
            applyFovPort(customFovPorts[1], fovPorts[1]);
        } else {
            fovPorts[0] = hmd.DefaultEyeFov[0];
            fovPorts[1] = hmd.DefaultEyeFov[1];
        }

        RenderAPIConfig rc = new RenderAPIConfig();
        rc.Header.API = OvrLibrary.ovrRenderAPIType.ovrRenderAPI_OpenGL;
        rc.Header.BackBufferSize = new OvrSizei(width, height);
        rc.Header.Multisample = samples;

        int distortionCaps;

        if (dis_vig) {
            distortionCaps = OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Chromatic
                    | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_TimeWarp
                    | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Overdrive;
        } else {
            distortionCaps = OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Chromatic
                    | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_TimeWarp
                    | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Vignette
                    | OvrLibrary.ovrDistortionCaps.ovrDistortionCap_Overdrive;
        }

        configureResult = hmd.configureRendering(rc, distortionCaps, fovPorts);

        if (LWJGLUtil.PLATFORM_LINUX == LWJGLUtil.getPlatform()) {
            // Native window support currently only available on windows
            System.out.println("Direct HMD mode not supported on linux");
        } else if (LWJGLUtil.PLATFORM_WINDOWS == LWJGLUtil.getPlatform()) {
            long nativeWindow = getNativeWindow();
            if (0 == (hmd.getEnabledCaps() & ovrHmdCaps.ovrHmdCap_ExtendDesktop)) {
                OvrLibrary.INSTANCE.ovrHmd_AttachToWindow(hmd, Pointer.createConstant(nativeWindow), null, null);
            }
        }
        System.out.println(configureResult[0]);
        System.out.println(fovPorts[0]);
        if (null == configureResult) {
            throw new IllegalStateException("Unable to configure rendering");
        }
        return configureResult;
    }

    public static Matrix4f toMatrix4f(OvrMatrix4f m) {
        return new Matrix4f(m.M).transpose();
    }

    public static void setCustomFovPorts(FovPort[] fovPorts) {
        customFOV = true;
        customFovPorts = fovPorts;
    }

    private static void applyFovPort(FovPort source, FovPort target) {
        target.LeftTan = source.LeftTan;
        target.RightTan = source.RightTan;
        target.UpTan = source.UpTan;
        target.DownTan = source.DownTan;

    }

    private static long getNativeWindow() {
        long window = -1;
        try {
            Object displayImpl = null;
            Method[] displayMethods = Display.class.getDeclaredMethods();
            for (Method m : displayMethods) {
                if (m.getName().equals("getImplementation")) {
                    m.setAccessible(true);
                    displayImpl = m.invoke(null, (Object[]) null);
                    break;
                }
            }
            String fieldName = null;
            switch (LWJGLUtil.getPlatform()) {
                case LWJGLUtil.PLATFORM_LINUX:
                    fieldName = "current_window";
                    break;
                case LWJGLUtil.PLATFORM_WINDOWS:
                    fieldName = "hwnd";
                    break;
            }
            if (null != fieldName) {
                Field[] windowsDisplayFields = displayImpl.getClass().getDeclaredFields();
                for (Field f : windowsDisplayFields) {
                    if (f.getName().equals(fieldName)) {
                        f.setAccessible(true);
                        window = (Long) f.get(displayImpl);
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return window;
    }
}
