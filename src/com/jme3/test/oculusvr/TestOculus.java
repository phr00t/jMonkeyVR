/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.test.oculusvr;

import com.jme3.app.SimpleApplication;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import oculusvr.input.OculusRiftReader;

/**
 *
 * @author Rickard
 */
public class TestOculus extends SimpleApplication {

    public static void main(String[] args) {
        TestOculus app = new TestOculus();
        app.start();
        
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
        OculusRiftReader orr = null;
        try {
            orr = new OculusRiftReader();

        } catch (Exception ex) {
            Logger.getLogger(TestOculus.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (orr != null) {
            System.out.println(orr.isInitialized());
            System.out.println(orr.getHMDInfo().getHResolution());
            orr.update();
        }
        
    }
}
