/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.jme3.math.Matrix4f;
import com.jme3.util.TempVars;
import openvr_api.HmdMatrix34_t;
import openvr_api.HmdMatrix44_t;

/**
 *
 * @author reden
 */
public class OpenVRUtil {
    
    public static Matrix4f convertSteamVRMatrix3ToMatrix4f(HmdMatrix34_t hmdMatrix, Matrix4f mat){
        //TODO: verify that this is a correct assumption;
        mat.set(hmdMatrix.m().get(0), hmdMatrix.m().get(1), hmdMatrix.m().get(2), 0f, 
                hmdMatrix.m().get(3), hmdMatrix.m().get(4), hmdMatrix.m().get(5), 0f, 
                hmdMatrix.m().get(6), hmdMatrix.m().get(7), hmdMatrix.m().get(8), 0f, 
                hmdMatrix.m().get(9), hmdMatrix.m().get(10), hmdMatrix.m().get(11), 1f);
        return mat;
    }
    
    public static Matrix4f convertSteamVRMatrix4ToMatrix4f(HmdMatrix44_t hmdMatrix, Matrix4f mat){
        //TODO: verify that this is a correct assumption;
        mat.set(hmdMatrix.m().get(0), hmdMatrix.m().get(1), hmdMatrix.m().get(2), hmdMatrix.m().get(3), 
                hmdMatrix.m().get(4), hmdMatrix.m().get(5), hmdMatrix.m().get(6), hmdMatrix.m().get(7),
                hmdMatrix.m().get(8), hmdMatrix.m().get(9), hmdMatrix.m().get(10), hmdMatrix.m().get(11), 
                hmdMatrix.m().get(12), hmdMatrix.m().get(13), hmdMatrix.m().get(14), hmdMatrix.m().get(15));
        return mat;
    }
}
