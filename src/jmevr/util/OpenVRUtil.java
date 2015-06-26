/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.math.Matrix4f;
import jopenvr.HmdMatrix34_t;
import jopenvr.HmdMatrix44_t;

/**
 *
 * @author reden
 */
public class OpenVRUtil {
    
    public static Matrix4f convertSteamVRMatrix3ToMatrix4f(HmdMatrix34_t hmdMatrix, Matrix4f mat){
        //TODO: verify that this is a correct assumption;
        mat.set(hmdMatrix.m[0], hmdMatrix.m[1], hmdMatrix.m[2], 0f, 
                hmdMatrix.m[3], hmdMatrix.m[4], hmdMatrix.m[5], 0f, 
                hmdMatrix.m[6], hmdMatrix.m[7], hmdMatrix.m[8], 0f, 
                hmdMatrix.m[9], hmdMatrix.m[10], hmdMatrix.m[11], 1f);
        return mat;
    }
    
    public static Matrix4f convertSteamVRMatrix4ToMatrix4f(HmdMatrix44_t hmdMatrix, Matrix4f mat){
        //TODO: verify that this is a correct assumption;
        mat.set(hmdMatrix.m[0], hmdMatrix.m[1], hmdMatrix.m[2], hmdMatrix.m[3], 
                hmdMatrix.m[4], hmdMatrix.m[5], hmdMatrix.m[6], hmdMatrix.m[7],
                hmdMatrix.m[8], hmdMatrix.m[9], hmdMatrix.m[10], hmdMatrix.m[11], 
                hmdMatrix.m[12], hmdMatrix.m[13], hmdMatrix.m[14], hmdMatrix.m[15]);
        return mat;
    }
}
