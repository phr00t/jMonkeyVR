package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("87CE5498-68D6-44E5-9215-6DA47EF883D8")
@Library("")
@Runtime(COMRuntime.class)
public class ISimpleAudioVolume extends IUnknown {

	public ISimpleAudioVolume() {
	}

	@Virtual(0)
	public final native int SetMasterVolume(float fLevel, Pointer<Byte> EventContext);
	
	@Virtual(1)
	public final native int GetMasterVolume(Pointer<Float> pfLevel);
	
	@Virtual(2)
	public final native int SetMute(int bMute);
	
	@Virtual(3)
	public final native int GetMute(Pointer<Integer> pbMute); 
}
