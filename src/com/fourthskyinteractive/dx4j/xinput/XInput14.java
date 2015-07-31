package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Optional;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;

@Library("XInput1_4")
@Runtime(COMRuntime.class)
public class XInput14 {
	static {
		BridJ.register();
	}

	@Optional
	public static final native int XInputGetAudioDeviceIds(int dwUserIndex, Pointer<Byte> pRenderDeviceId, Pointer<Integer> pRenderCount, Pointer<Byte> pCaptureDeviceId, Pointer<Integer> pCaptureCount);
}
