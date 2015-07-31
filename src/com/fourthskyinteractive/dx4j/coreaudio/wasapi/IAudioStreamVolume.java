package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("93014887-242D-4068-8A15-CF5E93B90FE3")
@Library("")
@Runtime(COMRuntime.class)
public class IAudioStreamVolume extends IUnknown {

	public IAudioStreamVolume() {
	}

	@Virtual(0)
	public final native int GetChannelCount(Pointer<Integer> pdwCount);
	
	@Virtual(1)
	public final native int SetChannelVolume(int dwIndex, float fLevel);
	
	@Virtual(2)
	public final native int GetChannelVolume(int dwIndex, Pointer<Float> pfLevel);
	
	@Virtual(3)
	public final native int SetAllVolumes(int dwCount, Pointer<Float> pfVolumes);
	
	@Virtual(4)
	public final native int GetAllVolumes(int dwCount, Pointer<Float> pfVolumes);
}
