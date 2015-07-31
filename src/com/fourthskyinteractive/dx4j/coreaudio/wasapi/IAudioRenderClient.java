package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("C8ADBD64-E71E-48a0-A4DE-185C395CD317")
@Library("")
@Runtime(COMRuntime.class)
public class IAudioRenderClient extends IUnknown {

	public IAudioRenderClient() {
	}

	@Virtual(0)
	public final native int GetBuffer(Pointer<Pointer<?>> ppData, int NumFramesToRead, Pointer<Integer> fwFlags, Pointer<Long> pu64DevicePosition, Pointer<Long> QPCPosition);
	
	@Virtual(1)
	public final native int ReleaseBuffer(int NumFramesRead);
	
	@Virtual(2)
	public final native int GetNextPacketSize(Pointer<Integer> pNumFramesInNextPacket); 
}
