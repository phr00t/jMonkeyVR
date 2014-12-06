/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package oculusvr.gui;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import oculusvr.gui.state.MenuAppState;
import oculusvr.app.OVRApplication;
import oculusvr.util.OculusGuiNode;

/**
 *
 * @author reden
 * Tutorial at: http://www.softwarepioneering.com/2014/12/free-floating-vr-menu-in-jmonkeyengine.html
 */
public class ManualGUIExample extends OVRApplication {

    public static void main(String[] args){
        ManualGUIExample app = new ManualGUIExample();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        super.simpleInitApp();
        this.setDisplayFps(false);
        this.setDisplayStatView(false);
        ovrAppState.getGuiNode().setPositioningMode(OculusGuiNode.POSITIONING_MODE.MANUAL);
        ovrAppState.getGuiNode().setGuiDistance(0.5f);
        ovrAppState.getGuiNode().setGuiScale(0.5f);
        Node observer = new Node("Observer");
        observer.setLocalTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
        observer.addControl(ovrAppState.getCameraControl());
        rootNode.attachChild(observer);

        MenuAppState menuAppState = new MenuAppState(settings.getWidth(), settings.getHeight(), (OculusGuiNode) guiNode);
        stateManager.attach(menuAppState);
    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
        guiNode.setLocalTranslation(cam.getLocation().add(0, 0, 0.5f));
        guiNode.lookAt(cam.getLocation(), Vector3f.UNIT_Y);
    }

}
