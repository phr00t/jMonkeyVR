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

package oculusvr.sdk;

import oculusvr.*;
import com.jme3.app.SimpleApplication;
import oculusvr.state.OVRAppState;
import com.jme3.material.Material;
import oculusvr.state.OVRAppState;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.SkyFactory;
import oculusvr.input.OculusRift;

public class TestSkyBox extends SimpleApplication {

    private Sphere sphereMesh = new Sphere(32, 32, 10, false, true);
    private Geometry sphere = new Geometry("Sky", sphereMesh);
    private static boolean useHttp = false;

    public static void main(String[] args) {
        OculusRift.initialize();
        TestSkyBox app = new TestSkyBox();
        app.start();
    }

    
    @Override
    public void simpleUpdate(float tpf){
        sphere.setLocalTranslation(cam.getLocation());
    }

    public void simpleInitApp() {
        this.flyCam.setMoveSpeed(10);

        // load sky
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        
        Geometry box = new Geometry("", new Box(1,1,1));
        Material m = new Material(getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        
        box.setMaterial(m);
        box.move(3, 0, 5);
        rootNode.attachChild(box);
        OVRAppState stereoCamAppState = new OVRAppState();
        stateManager.attach(stereoCamAppState);
        
        Spatial observer = new Node("Observer");
        observer.addControl(stereoCamAppState.getCameraControl());
        rootNode.attachChild(observer);
    }
}
