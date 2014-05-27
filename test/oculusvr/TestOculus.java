/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr;

import com.jme3.app.SimpleApplication;
import oculusvr.input.OculusRift;

/**
 *
 * @author Rickard
 */
public class TestOculus extends SimpleApplication {

    public static void main(String[] args) {
        OculusRift.initialize();
        TestOculus app = new TestOculus();
        app.start();

//        OculusRift.initialize();
//        OculusRiftReader orr = null;
//        try {
//            orr = new OculusRiftReader();
//
//        } catch (Exception ex) {
//            Logger.getLogger(TestOculus.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (orr != null) {
//            System.out.println(orr.isInitialized());
//            System.out.println(orr.getHMDInfo().getHResolution());
//            orr.update();
//        }
    }

    @Override
    public void simpleInitApp() {
        
        OculusRift.getHMDInfo();
        OculusRift.destroy();
//   OculusRift oc = new OculusRift();
//   oc.initOculus();
//        OculusRiftReader orr = null;
//        try {
//            orr = new OculusRiftReader();
//
//        } catch (Exception ex) {
//            Logger.getLogger(TestOculus.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (orr != null) {
//            System.out.println(orr.isInitialized());
//            System.out.println(orr.getHMDInfo().getHResolution());
//            orr.update();
//        }
        
    }
}
