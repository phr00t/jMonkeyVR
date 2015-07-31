package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.util.WAVEFORMATEX;
import com.fourthskyinteractive.dx4j.coreaudio.wasapi.WASAPI.AUDCLNT_SHAREMODE;
import com.fourthskyinteractive.dx4j.windows.HANDLE;

@IID("1CB9AD4C-DBFA-4c32-B178-C2F568A703B2")
@Library("")
@Runtime(COMRuntime.class)
public class IAudioClient extends IUnknown {

	public IAudioClient() {
	}

	@Virtual(0)
	public final native int Initialize(AUDCLNT_SHAREMODE ShareMode, int StreamFlags, double hnsBufferDuration, double hnsPeriodicity, Pointer<WAVEFORMATEX> pFormat, Pointer<Byte> AudioSessionGuid);
	
	@Virtual(1)
	public final native int GetBufferSize(Pointer<Integer> pNumBufferFrames);
	
	@Virtual(2)
	public final native int GetStreamLatency(Pointer</*REFERENCE_TIME */Double> phnsLatency);
	
	@Virtual(3)
	public final native int GetCurrentPadding(Pointer<Integer> pNumPaddingFrames);
	
	@Virtual(4)
	public final native int IsFormatSupported(AUDCLNT_SHAREMODE ShareMode, Pointer<WAVEFORMATEX> pFormat, Pointer<Pointer<WAVEFORMATEX>> ppClosestMatch);
	
	@Virtual(5)
	public final native int GetMixFormat(Pointer<Pointer<WAVEFORMATEX>> ppDeviceFormat);
	
	@Virtual(6)
	public final native int GetDevicePeriod(Pointer</*REFERENCE_TIME */Double> phnsDefaultDevicePeriod, Pointer</*REFERENCE_TIME */Double> phnsMinimumDevicePeriod);
	
	@Virtual(7)
	public final native int Start();
	
	@Virtual(8)
	public final native int Stop();
	
	@Virtual(9)
	public final native int Reset();
	
	@Virtual(10)
	public final native int SetEventHandle(HANDLE eventHandle);
	
	@Virtual(11)
	public final native int GetService(Pointer<Byte> riid, Pointer<Pointer<?>> ppv);
}
