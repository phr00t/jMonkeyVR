package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("F294ACFC-3146-4483-A7BF-ADDCA7C260E2")
@Library("")
@Runtime(COMRuntime.class)
public class IAudioCaptureClient extends IUnknown {

	public IAudioCaptureClient() {
	}

	@Virtual(0)
	public final native int GetBuffer(int NumFramesRequested, Pointer<Pointer<?>> ppData);
	
	@Virtual(1)
	public final native int ReleaseBuffer(int NumFramesWritten, int dwFlags); 
}
