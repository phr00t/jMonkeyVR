I recommend using Phr00t's jMonkeyEngine build here: https://github.com/phr00t/jmonkeyengine (the main jME3 build may work, but OpenVR-specific changes will be made first on Phr00t's build) -- you can just use the JAR/libs under the dist/ and lib/ folders

SteamVR is required. You can download & install it free with the Steam client (under Tools).

If testing with a Rift, I recommend disabling the Steam VR Compositor in your application (using the preconfigureVRApp function below), since support is flaky. The JFrame "easy extended" backup mode will do a good job as a replacement.

See <a href="https://github.com/phr00t/jmonkeyengine-virtual-reality/blob/master/test/jmevr/TestOpenVR.java">TestOpenVR.java</a> to see a full example of how to set up an application to use VR hardware & jME3.

<b>Adding OpenVR support to your application:</b>

1. Add the latest jna-x.x.x & JMonkeyVR.jar to the project.

2. Instead of extending SimpleApplication/BaseApplication for your Main class, extend VRApplication. This sets up a few things including the special VR GUI node. If you want to configure a few things, use the preconfigureVRApp function as follows (useSteamVRCompositor defaults to true, useJFrame [a backup rendering method] is set to true, forceDebugEnableVR defaults to false).

```
public class Main extends VRApplication {
     
     public static Main MyApp;
 
    public static void main(String[] args) {
         MyApp = new Main();
         MyApp.preconfigureVRApp(useSteamVRCompositor, useJFrame, forceDebugEnableVR);
         MyApp.start();
     }
 }
```

3. In your simpleInitApp() function, make sure you call super.simpleInitApp()!

```
     @Override
     public void simpleInitApp() {
         super.simpleInitApp();
         .....
     }
```
 
4. To add basic HMD sensing:

```
 Spatial observer = new Node("Observer");
 VRApplication.setObserver(observer);
 rootNode.attachChild(observer);
```

<i>PRO-TIP: Use anisotropic filtering! Textures will be very blurry otherwise!</i>

<b>Using the GUI</b>

The GUI system has two options: automatic & manual positioning. Automatic positioning will always keep the GUI elements floating infront of the view, while manual will let you center it manually, where it will stay as the player moves their head. The default is manual positioning.

You can change options like so:

```
 VRApplication.getVRGuiNode().setPositioningMode(POSITIONING_MODE.AUTO);
 VRApplication.getVRGuiNode().setGuiDistance(0.8f);
```

To center the GUI's position manually, where it will stay until another manual position update is called (as long as the positioning mode is set to MANUAL):

```
 VRApplication.getVRGuiNode().positionGui();
```

The VRGuiNode will try and keep things in the "Translucent" render bucket. If something ends up in the "Gui" bucket, you will need to call fixBrokenElements() to convert them to "Translucent".

It should be safe to have all of the children of the root VRGuiNode also be VRGuiNode.

The VRGuiNode will operate as a normal node if a VR headset isn't initialized. For testing purposes without a VR headset, you can set forceVR to "true" in the preconfigureVRApp(disableVignette, maxFov, flipEyes, forceVR) call in step #2 above.
