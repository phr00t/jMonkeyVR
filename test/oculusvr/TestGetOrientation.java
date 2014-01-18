/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr;

import com.jme3.app.SimpleApplication;
import oculusvr.input.OculusRiftReader;

/**
 *
 * @author Rickard
 */
public class TestGetOrientation extends SimpleApplication {

    public static void main(String[] args) {
        OculusRiftReader.initialize();
        TestGetOrientation app = new TestGetOrientation();
        app.start();

    }

    @Override
    public void simpleInitApp() {
        
        OculusRiftReader.getHMDInfo();
        float[] orientation = OculusRiftReader.getOrientation();
        for(float f: orientation){
            System.out.println(f);
        }
        OculusRiftReader.destroy();
    }
}
