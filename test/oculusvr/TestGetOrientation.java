/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import oculusvr.input.OculusRift;

/**
 *
 * @author Rickard
 */
public class TestGetOrientation extends SimpleApplication {

    public static void main(String[] args) {
        OculusRift.initialize();
        TestGetOrientation app = new TestGetOrientation();
        app.start();

    }

    @Override
    public void simpleInitApp() {
        
        OculusRift.getHMDInfo();
        Quaternion quat = OculusRift.getOrientation();
        System.out.println(quat.toString());
        OculusRift.destroy();
    }
}
