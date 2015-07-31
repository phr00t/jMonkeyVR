package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;

@Library("XAudio2_7")
@Runtime(COMRuntime.class)
public class IXAudio2MasteringVoice extends IXAudio2Voice {
	public IXAudio2MasteringVoice() {
		super();
	}
}
