/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import jopenvr.HmdMatrix34_t;
import jopenvr.HmdMatrix44_t;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;

/**
 *
 * @author reden
 */
public class OpenVRUtil {
    
    public static Matrix4f convertSteamVRMatrix3ToMatrix4f(HmdMatrix34_t hmdMatrix, Matrix4f mat){
		/*matPose.m[0][0], matPose.m[1][0], matPose.m[2][0], 0.0,
		matPose.m[0][1], matPose.m[1][1], matPose.m[2][1], 0.0,
		matPose.m[0][2], matPose.m[1][2], matPose.m[2][2], 0.0,
		matPose.m[0][3], matPose.m[1][3], matPose.m[2][3], 1.0f        */        
        mat.set(hmdMatrix.m[0], hmdMatrix.m[4], hmdMatrix.m[8], hmdMatrix.m[3], 
                hmdMatrix.m[1], hmdMatrix.m[5], hmdMatrix.m[9], hmdMatrix.m[7], 
                hmdMatrix.m[2], hmdMatrix.m[6], hmdMatrix.m[10], hmdMatrix.m[11], 
                0f, 0f, 0f, 1f);
        return mat;
    }
    
    public static Matrix4f convertSteamVRMatrix4ToMatrix4f(HmdMatrix44_t hmdMatrix, Matrix4f mat){
        mat.set(hmdMatrix.m[0], hmdMatrix.m[1], hmdMatrix.m[2], hmdMatrix.m[3], 
                hmdMatrix.m[4], hmdMatrix.m[5], hmdMatrix.m[6], hmdMatrix.m[7],
                hmdMatrix.m[8], hmdMatrix.m[9], hmdMatrix.m[10], hmdMatrix.m[11], 
                hmdMatrix.m[12], hmdMatrix.m[13], hmdMatrix.m[14], hmdMatrix.m[15]);
        return mat;
    }
    
    public static void convertMatrix4toQuat(Matrix4f in, Quaternion out) {
        // convert rotation matrix to quat
        out.fromRotationMatrix(in.m00, in.m01, in.m02, in.m10, in.m11, in.m12, in.m20, in.m21, in.m22);
        // flip the pitch
        out.set(-out.getX(), out.getY(), -out.getZ(), out.getW());
    }

    public static long getNativeWindow() {
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
