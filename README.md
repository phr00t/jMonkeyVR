I recommend using Phr00t's jMonkeyEngine build here: https://github.com/phr00t/jmonkeyengine (the main jME3 build may work, but Rift-specific changes will be made first on Phr00t's build) -- you can just use the JAR/libs under the dist/ and lib/ folders

See TestVRApplication.java to see a full example of how to set up an application to use VR hardware & jME3.

Adding Oculus Rift support to your application:

1. Add the latest jna-x.x.x, jovr-0.x.x.x, guava-x.x.jar & JMonkeyVR.jar to the project.

2. Instead of extending SimpleApplication for your Main class, extend VRApplication. This sets up a few things including the special VR GUI node. If you want to configure a few things, use the configureVRApp function as follows (all those options default to "false").

 public class Main extends VRApplication {
     
     public static Main MyApp;
 
    public static void main(String[] args) {
         MyApp = new Main();
         MyApp.preconfigureVRApp(disableVignette, maxFov, flipEyes, forceDebugEnableVR);
         StartGame();
     }
 }

3. In your simpleInitApp() function, make sure you call super.simpleInitApp()!

     @Override
     public void simpleInitApp() {
         super.simpleInitApp();
         .....
     }
 
4. To add basic HMD sensing:

 Spatial observer = new Node("Observer");
 observer.addControl(VRApplication.getVRAppState().getCameraControl());
 rootNode.attachChild(observer);
 
PRO-TIP: Use anisotropic filtering! Textures will be very blurry otherwise!

Using the GUI
The GUI system has two options: automatic & manual positioning. Automatic positioning will always keep the GUI elements floating infront of the view, while manual will let you center it manually, where it will stay as the player moves their head. The default is manual positioning.

You can change options like so:

 VRApplication.getVRAppState().getGuiNode().setPositioningMode(POSITIONING_MODE.AUTO);
 VRApplication.getVRAppState().getGuiNode().setGuiDistance(0.8f);
 
To center the GUI's position manually, where it will stay until another manual position update is called (as long as the positioning mode is set to MANUAL):

 VRApplication.getVRAppState().getGuiNode().positionGui();
 
The VRGuiNode will try and keep things in the "Translucent" render bucket. If something ends up in the "Gui" bucket, you will need to call fixBrokenElements() to convert them to "Translucent".

It should be safe to have all of the children of the root VRGuiNode also be VRGuiNode.

The VRGuiNode will operate as a normal node if a VR headset isn't initialized. For testing purposes without a VR headset, you can set forceVR to "true" in the preconfigureVRApp(disableVignette, maxFov, flipEyes, forceVR) call in step #2 above.
