package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.CLSID;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.util.WAVEFORMATEX;
import com.fourthskyinteractive.dx4j.xaudio2.XAudio2.XAUDIO2_PROCESSOR;

@CLSID("5a508685-a254-4fba-9b82-9a24b00306af")
@IID("8bcf1f58-9fe7-4583-8ac6-e2adc465c8bb")
@Library("XAudio2_7")
@Runtime(COMRuntime.class)
public class IXAudio2 extends IUnknown {

	public IXAudio2() {
		// TODO Auto-generated constructor stub
	}

	@Virtual(0)
	public final native int GetDeviceCount(Pointer<Integer> pCount);
	
	@Virtual(1)
	public final native int GetDeviceDetails(int index, Pointer<XAUDIO2_DEVICE_DETAILS> pDeviceDeatails);
	
	@Virtual(2)
	public final native int Initialize(int flags, XAUDIO2_PROCESSOR Processor);
	
	@Virtual(3)
	public final native int RegisterForCallbacks(Pointer<IXAudio2EngineCallback> pCallback);
	
	@Virtual(4)
	public final native int UnregisterForCallbacks(Pointer<IXAudio2EngineCallback> pCallback);
	
	@Virtual(5)
	public final native int CreateSourceVoice(Pointer<Pointer<IXAudio2SourceVoice>> ppSourceVoice, 
											  Pointer<WAVEFORMATEX> pSourceFormat, 
											  int Flags, 
											  float MaxFrequencyRatio, 
											  Pointer<IXAudio2EngineCallback> pCallback, 
											  Pointer<XAUDIO2_VOICE_SENDS> pSendList, 
											  Pointer<XAUDIO2_EFFECT_CHAIN> pEffectChain);
	
	@Virtual(6)
	public final native int CreateSubmixVoice(Pointer<Pointer<IXAudio2SubmixVoice>> ppSubmixVoice, 
											  int InputChannels, 
											  int InputSampleRate, 
											  int Flags, 
											  int ProcessingStage, 
											  Pointer<XAUDIO2_VOICE_SENDS> pSendList, 
											  Pointer<XAUDIO2_EFFECT_CHAIN> pEffectChain);
	
	@Virtual(7)
	public final native int CreateMasteringVoice(Pointer<Pointer<IXAudio2MasteringVoice>> ppMasteringVoice, 
												 int InputChannels, 
												 int InputSampleRate, 
												 int Flags, 
												 int DeviceIndex, 
												 Pointer<XAUDIO2_EFFECT_CHAIN> pEffectChain);
	
	@Virtual(8)
	public final native int StartEngine();
	
	@Virtual(9)
	public final native void StopEngine();
	
	@Virtual(10)
	public final native int CommitChanges(int OperationSet);
	
	@Virtual(11)
	public final native int GetPerformanceData(Pointer<XAUDIO2_PERFORMANCE_DATA> pPerfData);
	
	@Virtual(12)
	public final native int SetDebugConfiguration(Pointer<XAUDIO2_DEBUG_CONFIGURATION> pDebugConf, Pointer<?> pReserved); 
}
