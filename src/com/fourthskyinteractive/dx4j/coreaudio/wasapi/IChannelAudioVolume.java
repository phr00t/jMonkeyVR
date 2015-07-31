package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("1C158861-B533-4B30-B1CF-E853E51C59B8")
@Library("")
@Runtime(COMRuntime.class)
public class IChannelAudioVolume extends IUnknown {

	public IChannelAudioVolume() {
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
