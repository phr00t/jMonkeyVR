/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvr.app;

import com.jme3.app.SimpleApplication;
import oculusvr.input.OculusRift;
import oculusvr.state.OVRAppState;
import oculusvr.util.OculusGuiNode;
import oculusvr.util.OculusRiftUtil;

/**
 *
 * @author reden
 */
public class OVRApplication extends SimpleApplication{

    protected OVRAppState ovrAppState;

    public OVRApplication() {
        OculusRift.initialize();
        
        // this will make it work even if an HMD isn't present
        OculusRift.forceInitializeSuccess();
    }
    
    @Override
    public void simpleInitApp() {
        // run this function before OVRAppState gets initialized to force
        // maximum FOV rendering
//        OculusRiftUtil.useMaxEyeFov(true);
        
        ovrAppState = new OVRAppState((OculusGuiNode)guiNode, true);
        ovrAppState.getGuiNode().setPositioningMode(OculusGuiNode.POSITIONING_MODE.AUTO);
                
        stateManager.attach(ovrAppState);
    }
    
}
