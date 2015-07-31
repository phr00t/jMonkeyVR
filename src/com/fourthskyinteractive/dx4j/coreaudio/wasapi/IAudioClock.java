package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("CD63314F-3FBA-4a1b-812C-EF96358728E7")
@Library("")
@Runtime(COMRuntime.class)
public class IAudioClock extends IUnknown {

	public IAudioClock() {
	}

	@Virtual(0)
	public final native int GetFrequency(Pointer<Long> pu64Frequency);
	
	@Virtual(1)
	public final native int GetPosition(Pointer<Long> pu64Position, Pointer<Long> pu64QPCPosition);
	
	@Virtual(2)
	public final native int GetCharacteristics(Pointer<Integer> pdwCharacteristics);
}
