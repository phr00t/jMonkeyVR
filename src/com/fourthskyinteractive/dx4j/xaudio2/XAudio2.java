package com.fourthskyinteractive.dx4j.xaudio2;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;

import static org.bridj.cpp.com.COMRuntime.*;

@Library("XAudio2_7")
@Runtime(COMRuntime.class)
public class XAudio2 {
	static {
		BridJ.register();
	}
	
	public enum XAUDIO2_DEVICE_ROLE implements IntValuedEnum<XAUDIO2_DEVICE_ROLE> {
	    NotDefaultDevice			(0x0),
	    DefaultConsoleDevice       	(0x1),
	    DefaultMultimediaDevice    	(0x2),
	    DefaultCommunicationsDevice	(0x4),
	    DefaultGameDevice          	(0x8),
	    GlobalDefaultDevice        	(0xf),
	    InvalidDeviceRole(~GlobalDefaultDevice.value());
	    XAUDIO2_DEVICE_ROLE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<XAUDIO2_DEVICE_ROLE> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<XAUDIO2_DEVICE_ROLE> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}	    
	}
	
	public enum XAUDIO2_PROCESSOR implements IntValuedEnum<XAUDIO2_PROCESSOR > {
		Processor1(1),
		Processor2(2),
		Processor3(4),
		Processor4(8),
		Processor5(16),
		Processor6(32),
		Processor7(64),
		Processor8(128),
		Processor9(256),
		Processor10(512),
		Processor11(1024),
		Processor12(2048),
		Processor13(4096),
		Processor14(8192),
		Processor15(16384),
		Processor16(32768),
		Processor17(65536),
		Processor18(131072),
		Processor19(262144),
		Processor20(524288),
		Processor21(1048576),
		Processor22(2097152),
		Processor23(4194304),
		Processor24(8388608),
		Processor25(16777216),
		Processor26(33554432),
		Processor27(67108864),
		Processor28(134217728),
		Processor29(268435456),
		Processor30(536870912),
		Processor31(1073741824),
		Processor32(-2147483648),
		XAUDIO2_ANY_PROCESSOR(-1),
		XAUDIO2_DEFAULT_PROCESSOR(XAUDIO2_ANY_PROCESSOR.value());
		XAUDIO2_PROCESSOR(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<XAUDIO2_PROCESSOR > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<XAUDIO2_PROCESSOR > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	
	public enum XAUDIO2_FILTER_TYPE implements IntValuedEnum<XAUDIO2_FILTER_TYPE > {
		LowPassFilter(0),
		BandPassFilter(1),
		HighPassFilter(2),
		NotchFilter(3);
		XAUDIO2_FILTER_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<XAUDIO2_FILTER_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<XAUDIO2_FILTER_TYPE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public static final int XAUDIO2_LOG_DETAIL = (int)8;
	public static final float XAUDIO2_DEFAULT_FILTER_FREQUENCY = (float)1.0f;
	public static final int XAUDIO2_MAX_BUFFER_BYTES = (int)-2147483648;
	public static final int XAUDIO2_LOG_TIMING = (int)64;
	public static final int XAUDIO2_COMMIT_ALL = (int)0;
	public static final float XAUDIO2_MAX_FREQ_RATIO = (float)1024.0f;
	public static final int XAUDIO2_NO_LOOP_REGION = (int)0;
	public static final int XAUDIO2_COMMIT_NOW = (int)0;
	public static final float XAUDIO2_MAX_VOLUME_LEVEL = (float)16777216.0f;
	public static final int XAUDIO2_DEBUG_ENGINE = (int)1;
	public static final int XAUDIO2_VOICE_MUSIC = (int)16;
	public static final int XAUDIO2_VOICE_NOPITCH = (int)2;
	public static final int XAUDIO2_VOICE_NOSRC = (int)4;
	public static final int XAUDIO2_DEFAULT_CHANNELS = (int)0;
	public static final int XAUDIO2_MAX_SAMPLE_RATE = (int)200000;
	public static final int XAUDIO2_E_INVALID_CALL = (int)-2003435519;
	public static final int XAUDIO2_LOG_INFO = (int)4;
	public static final int XAUDIO2_MIN_SAMPLE_RATE = (int)1000;
	public static final int XAUDIO2_VOICE_USEFILTER = (int)8;
	public static final int XAUDIO2_DEFAULT_SAMPLERATE = (int)0;
	public static final int XAUDIO2_LOG_LOCKS = (int)128;
	public static final int XAUDIO2_LOG_MEMORY = (int)256;
	public static final int XAUDIO2_LOG_FUNC_CALLS = (int)32;
	public static final int XAUDIO2_MAX_QUEUED_BUFFERS = (int)64;
	public static final int XAUDIO2_QUANTUM_DENOMINATOR = (int)100;
	public static final int XAUDIO2_E_DEVICE_INVALIDATED = (int)-2003435516;
	public static final int XAUDIO2_E_XAPO_CREATION_FAILED = (int)-2003435517;
	public static final int XAUDIO2_MAX_LOOP_COUNT = (int)254;
	public static final int XAUDIO2_MAX_BUFFERS_SYSTEM = (int)2;
	public static final int XAUDIO2_QUANTUM_NUMERATOR = (int)1;
	public static final int XAUDIO2_SEND_USEFILTER = (int)128;
	public static final int XAUDIO2_MAX_AUDIO_CHANNELS = (int)64;
	public static final int XAUDIO2_E_XMA_DECODER_ERROR = (int)-2003435518;
	public static final float XAUDIO2_QUANTUM_MS = (float)(1000.0f * 1 / 100);
	public static final float XAUDIO2_MAX_FILTER_FREQUENCY = (float)1.0f;
	public static final int XAUDIO2_LOG_STREAMING = (int)4096;
	public static final int FACILITY_XAUDIO2 = (int)2198;
	public static final float XAUDIO2_MAX_FILTER_ONEOVERQ = (float)1.5f;
	public static final int XAUDIO2_LOG_ERRORS = (int)1;
	public static final float XAUDIO2_DEFAULT_FREQ_RATIO = (float)2.0f;
	public static final int XAUDIO2_MAX_RATIO_TIMES_RATE_XMA_MULTICHANNEL = (int)300000;
	public static final int XAUDIO2_LOG_WARNINGS = (int)2;
	public static final float XAUDIO2_DEFAULT_FILTER_ONEOVERQ = (float)1.0f;
	public static final int XAUDIO2_END_OF_STREAM = (int)64;
	public static final int XAUDIO2_LOOP_INFINITE = (int)255;
	public static final float XAUDIO2_MIN_FREQ_RATIO = (float)(1 / 1024.0f);
	public static final int XAUDIO2_MAX_RATIO_TIMES_RATE_XMA_MONO = (int)600000;
	public static final int XAUDIO2_MAX_INSTANCES = (int)8;
	public static final int XAUDIO2_LOG_API_CALLS = (int)16;
	public static final int XAUDIO2_PLAY_TAILS = (int)32;
	
	// Audio formats (little endian, because of this the bytes are inverted
	public static final int fourCCRIFF = 'F' << 24 | 'F' << 16 | 'I' << 8 | 'R';
	public static final int fourCCDATA = 'a' << 24 | 't' << 16 | 'a' << 8 | 'd';
	public static final int fourCCFMT = ' ' << 24 | 't' << 16 | 'm' << 8 | 'f';
	public static final int fourCCWAVE = 'E' << 24 | 'V' << 16 | 'A' << 8 | 'W';
	public static final int fourCCXWMA = 'A' << 24 | 'M' << 16 | 'W' << 8 | 'X';
	public static final int fourCCDPDS = 's' << 24 | 'd' << 16 | 'p' << 8 | 'd';
	
	public static final String CLSID_XAudio2 = "5a508685-a254-4fba-9b82-9a24b00306af";
	public static final String CLSID_XAudio2_Debug = "db05ea35-0329-4d4b-a53a-6dead03d3852";
	
	// Calculate the argument to SetVolume from a decibel value
	float XAudio2DecibelsToAmplitudeRatio(float Decibels)
	{
	    return (float) Math.pow(10.0, Decibels / 20.0);
	}

	// Recover a volume in decibels from an amplitude factor
	float XAudio2AmplitudeRatioToDecibels(float Volume)
	{
	    if (Volume == 0)
	    {
	        return -3.402823466e+38f; // Smallest float value (-FLT_MAX)
	    }
	    return (float)(20.0 * Math.log10(Volume));
	}

	// Calculate the argument to SetFrequencyRatio from a semitone value
	float XAudio2SemitonesToFrequencyRatio(float Semitones)
	{
	    // FrequencyRatio = 2 ^ Octaves
	    //                = 2 ^ (Semitones / 12)
	    return (float) Math.pow(2.0, Semitones / 12.0);
	}

	// Recover a pitch in semitones from a frequency ratio
	float XAudio2FrequencyRatioToSemitones(float FrequencyRatio)
	{
	    // Semitones = 12 * log2(FrequencyRatio)
	    //           = 12 * log2(10) * log10(FrequencyRatio)
	    return (float)(39.86313713864835 * Math.log10(FrequencyRatio));
	}

	// Convert from filter cutoff frequencies expressed in Hertz to the radian
	// frequency values used in XAUDIO2_FILTER_PARAMETERS.Frequency.  Note that
	// the highest CutoffFrequency supported is SampleRate/6.  Higher values of
	// CutoffFrequency will return XAUDIO2_MAX_FILTER_FREQUENCY.
	float XAudio2CutoffFrequencyToRadians(float CutoffFrequency, int SampleRate)
	{
	    if ((int)(CutoffFrequency * 6.0f) >= SampleRate)
	    {
	        return XAUDIO2_MAX_FILTER_FREQUENCY;
	    }
	    return (float)(2.0 * Math.sin((float)Math.PI * CutoffFrequency / SampleRate));
	}

	// Convert from radian frequencies back to absolute frequencies in Hertz
	float XAudio2RadiansToCutoffFrequency(float Radians, float SampleRate)
	{
	    return (float)(SampleRate * Math.asin(Radians / 2.0) / (float)Math.PI);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static final int XAudio2Create(Pointer<Pointer<IXAudio2>> ppXAudio2, int Flags, XAUDIO2_PROCESSOR Processor) {
		Pointer<Pointer<?>> pp = (Pointer<Pointer<?>>) ppXAudio2.asUntyped();
		IXAudio2 xAudio2 = null;
		int result = S_OK;
		
		result = CoCreateInstance(getCLSID(IXAudio2.class), 
				 				  null, 
				 				 CLSCTX_ALL, 
								  getIID(IXAudio2.class), 
								  pp);
		if (result == S_OK) {
			xAudio2 = ppXAudio2.get().getNativeObject(IXAudio2.class);
			
			result = xAudio2.Initialize(Flags, Processor);
			if (result != S_OK) {
				xAudio2.Release();
			}
		}
		
		/*
		try {
			xAudio2 = COMRuntime.newInstance(IXAudio2.class);
			if (xAudio2 != null) {
				result = xAudio2.Initialize(Flags, Processor);
				if (result != S_OK) {
					xAudio2.Release();
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = E_FAIL;
		}
		*/
		
		return result;
	}
	
	public static final int XAudio2Create(Pointer<Pointer<IXAudio2>> ppXAudio2, int Flags) {
		return XAudio2Create(ppXAudio2, Flags, XAUDIO2_PROCESSOR.XAUDIO2_DEFAULT_PROCESSOR);
	}
	
	public static final int XAudio2Create(Pointer<Pointer<IXAudio2>> ppXAudio2) {
		return XAudio2Create(ppXAudio2, 0, XAUDIO2_PROCESSOR.XAUDIO2_DEFAULT_PROCESSOR);
	}
}
