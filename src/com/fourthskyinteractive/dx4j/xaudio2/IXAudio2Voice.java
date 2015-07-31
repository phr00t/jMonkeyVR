package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.com.COMRuntime;

@Library("XAudio2_7")
@Runtime(COMRuntime.class)
public class IXAudio2Voice extends CPPObject {

	public IXAudio2Voice() {
		super();
	}
	
	protected IXAudio2Voice(Pointer<? extends CPPObject> peer) {
        super(peer);
    }
	
	@Virtual(0)
	public final native int GetVoiceDetails(Pointer<XAUDIO2_VOICE_DETAILS> pVoiceDetails);
	
	@Virtual(1)
	public final native int SetOutputVoices(Pointer<XAUDIO2_VOICE_SENDS> pSendList);
	
	@Virtual(2)
	public final native int SetEffectChain(Pointer<XAUDIO2_EFFECT_CHAIN> pEffectChain);
	
	@Virtual(3)
	public final native int EnableEffect(int EffectIndex, int OperationSet);
	
	@Virtual(4)
	public final native int DisableEffect(int EffectIndex, int OperationSet);
	
	@Virtual(5)
	public final native int GetEffectState(int EffectIndex, Pointer<Integer> pEnabled);
	
	@Virtual(6)
	public final native int SetEffectParameters(int EffectIndex, Pointer<?> pParameters, int ParametersByteSize, int OperationSet);
	
	@Virtual(7)
	public final native int GetEffectParameters(int EffectIndex, Pointer<?> pParameters, int ParametersByteSize);
	
	@Virtual(8)
	public final native int SetFilterParameters(Pointer<XAUDIO2_FILTER_PARAMETERS> pParameters, int OperationSet);
	
	@Virtual(9)
	public final native int GetFilterParameters(Pointer<XAUDIO2_FILTER_PARAMETERS> pParameters);
	
	@Virtual(10)
	public final native int SetOutputFilterParameters(Pointer<IXAudio2Voice> pDestinationVoice, Pointer<XAUDIO2_FILTER_PARAMETERS> pParameters, int OperationSet);
	
	@Virtual(11)
	public final native int GetOutputFilterParameters(Pointer<IXAudio2Voice> pDestinationVoice, Pointer<XAUDIO2_FILTER_PARAMETERS> pParameters);
	
	@Virtual(12)
	public final native int SetVolume(float Volume, int OperationSet);
	
	@Virtual(13)
	public final native int GetVolume(Pointer<Float> pVolume);
	
	@Virtual(14)
	public final native int SetChannelVolumes(int Channels, Pointer<Float> pVolumes, int OperationSet);
	
	@Virtual(15)
	public final native void GetChannelVolumes(int Channels, Pointer<Float> pVolumes);  
	
	@Virtual(16)
	public final native int SetOutputMatrix(Pointer<IXAudio2Voice> pDestinationVoice, int SourceChannels, int DestinationChannels, Pointer<Float> pLevelMatrix, int OperationSet);
	
	@Virtual(17)
	public final native int GetOutputMatrix(Pointer<IXAudio2Voice> pDestinationVoice, int SourceChannels, int DestinationChannels, Pointer<Float> pLevelMatrix);
	
	@Virtual(18)
	public final native void DestroyVoice(); 
}
