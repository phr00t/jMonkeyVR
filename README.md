<b>Want to see how good this library works?</b> <a href="https://drive.google.com/file/d/0Bza9ecEdICHGMTJNRDNzcG9yWmM/view?usp=sharing">Free demo!</a> (might get outdated -- feel free to replace jMonkeyVR.jar with file above)

<b>What games use this!? Commercial games on Steam:
<a href="http://store.steampowered.com/app/363460">Spermination</a> * <a href="http://store.steampowered.com/app/329770/">4089</a></b>

I recommend using Phr00t's jMonkeyEngine build here: https://github.com/phr00t/jmonkeyengine (the main jME3 build may work, but OpenVR-specific changes will be made first on Phr00t's build) -- you can just use the JAR/libs under the dist/ and lib/ folders

SteamVR is required. You can download & install it free with the Steam client (under Tools). Extended mode required (but don't worry, this library makes it work like Direct mode).

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
         MyApp.preconfigureVRApp(useSteamVRCompositor, useJFrame, forceDebugEnableVR); // optional
         MyApp.setFrustrumNearFar(0.5f, 512f); // optional, set near/far rendering of cameras
         MyApp.start();
     }
 }
```

3. To add basic HMD sensing:

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

The VRGuiNode will operate as a normal node if a VR headset isn't initialized. For testing purposes without a VR headset, you can set forceDebugEnableVR to "true" in the preconfigureVRApp call in step #2 above.

<b>Adding Filters during runtime</b>

If you add filters during application intialization, they will be automatically moved to each eye <i>if</i> VR mode gets started. However, if after initialization, you add some filters, you will need to move them to the VR scene with this function:

     VRApplication.moveScreenProcessingToVR();
     
This handles moving them out of the VR scene & cloning them for each eye. It is safe to call this function even if you are not in VR mode (it will do nothing & return immediately in that case).
     
See the TestOpenVR.java example for more usage information.

<b>Don't be afraid to use the mouse!</b> Using inputManager.setCursorVisislbe(true) will work fine! This library will monitor mouse usage & replace the cursor with a 3D version at the GUI distance automatically. Make sure to use local translation comparisons against getCursorPosition() when determining if something is under the mouse cursor.
