package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.com.COMRuntime;

@Library("XAudio2_7")
@Runtime(COMRuntime.class)
public class IXAudio2SourceVoice extends IXAudio2Voice {

	public IXAudio2SourceVoice() {
		super();
	}
	
	protected IXAudio2SourceVoice(Pointer<? extends CPPObject> peer) {
        super(peer);
    }
	
	@Virtual(0)
	public final native int Start(int Flags, int OperationSet);
	
	@Virtual(1)
	public final native int Stop(int Flags, int OperationSet);
	
	@Virtual(2)
	public final native int SubmitSourceBuffer(Pointer<XAUDIO2_BUFFER> pBuffer, Pointer<XAUDIO2_BUFFER_WMA> pBufferWMA);
	
	@Virtual(3)
	public final native int FlushSourceBuffer();
	
	@Virtual(4)
	public final native int Discontinuity();
	
	@Virtual(5)
	public final native int ExitLoop(int OperationSet);
	
	@Virtual(6)
	public final native void GetState(Pointer<XAUDIO2_VOICE_STATE> pVoiceState);
	
	@Virtual(7)
	public final native int SetFrequencyRatio(float Ratio, int OperationSet);
	
	@Virtual(8)
	public final native int GetFrequencyRatio(Pointer<Float> Ratio);
	
	@Virtual(9)
	public final native int SetSourceSampleRate(int NewSourceSampleRate);
}
