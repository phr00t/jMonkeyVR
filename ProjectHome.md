Oculus Rift support for jMonkeyEngine. Using JOVR (https://github.com/jherico/jocular)

See the "How To" in the wiki for instructions.

For support and discussion, please head here:
http://hub.jmonkeyengine.org/forum/topic/oculus-rift-support/

Dec 2014.
SDK version 0.4.4. Linux support now functional

Oct 2014.
SDK version 0.4.3. Direct HMD for Windows

Sep 2014.
Support for SDK 0.4.2, including positional tracking

May 2014.
phr00t from the jMonkeyEngine forums and of 2079 and 3089 fame has stepped up to improve the jME support and also create Linux64 lib's. He's already made a number of commits.

20th Jan 2014.
Implemented more native methods.
Replaced the euler angles with proper Quaternion. Feels smoother!
You no longer need to initialize the OculusRiftReader from a static context (thanks to rupy for making me look at the JRift implementation and realizing there's actually a bug in the SDK (or so it seems?))

18th Jan 2014.
Added methods for direct access to orientation and acceleration on the OVR object. Requested by "rupy" on the Oculus Developer forums.
Realized a new Quaternion was created every update in the CameraControl class. Changed it to setting the old one instead.

27th Dec 2013.
Long overdue update with OVR 0.2.5c support.
Major refactoring of the OculusRift object to static.
Support for many filter types.
One gotcha with this update is that you also need to initialize the Oculus in a new way. Please have a look in the wiki.
This is due to the problems that arise for some (and now me too in the latest libovr) where it can't be initialized properly. This is likely to change in the future, when i figure out what is wrong. Any help on the subject is appreciated.
