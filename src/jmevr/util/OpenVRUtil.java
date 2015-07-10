/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.math.Matrix3f;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import jopenvr.HmdMatrix34_t;
import jopenvr.HmdMatrix44_t;

/**
 *
 * @author reden
 */
public class OpenVRUtil {
    
    public static Matrix4f convertSteamVRMatrix3ToMatrix4f(HmdMatrix34_t hmdMatrix, Matrix4f mat){
        mat.set(hmdMatrix.m[0], hmdMatrix.m[4], hmdMatrix.m[8], 0f, 
                hmdMatrix.m[1], hmdMatrix.m[5], hmdMatrix.m[9], 0f, 
                hmdMatrix.m[2], hmdMatrix.m[6], hmdMatrix.m[10], 0f, 
                hmdMatrix.m[3], hmdMatrix.m[7], hmdMatrix.m[11], 1f);
        return mat;
    }
    
    public static Matrix4f convertSteamVRMatrix4ToMatrix4f(HmdMatrix44_t hmdMatrix, Matrix4f mat){
        mat.set(hmdMatrix.m[0], hmdMatrix.m[4], hmdMatrix.m[8], hmdMatrix.m[12], 
                hmdMatrix.m[1], hmdMatrix.m[5], hmdMatrix.m[9], hmdMatrix.m[13],
                hmdMatrix.m[2], hmdMatrix.m[6], hmdMatrix.m[10], hmdMatrix.m[14], 
                hmdMatrix.m[3], hmdMatrix.m[7], hmdMatrix.m[11], hmdMatrix.m[15]);
        return mat;
    }
    
    public static void convertMatrix4toQuat(Matrix4f in, Quaternion out) {
        // convert rotation matrix to quat
        out.fromRotationMatrix(in.m00, in.m01, in.m02, in.m10, in.m11, in.m12, in.m20, in.m21, in.m22);
        // flip the pitch
        out.set(-out.getX(), out.getY(), -out.getZ(), out.getW());
    }
}
