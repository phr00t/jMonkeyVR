package com.fourthskyinteractive.dx4j.coreaudio.wasapi;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;

/**
 * More information in http://msdn.microsoft.com/en-us/library/windows/desktop/dd371455(v=vs.85).aspx
 * @author evandro.paulino
 *
 */
@Library("")
@Runtime(COMRuntime.class)
public class WASAPI {
	static {
		BridJ.register();
	}
	
	public static final int AUDCLNT_BUFFERFLAGS_DATA_DISCONTINUITY = 1;
	public static final int AUDCLNT_BUFFERFLAGS_SILENT = 2;
	public static final int AUDCLNT_BUFFERFLAGS_TIMESTAMP_ERROR = 4;
	
	public static final int AUDIOCLOCK_CHARACTERISTIC_FIXED_FREQ = 0x00000001;
	
	/**
	 Description: AudioClient stream flags
	 
	 Can be a combination of AUDCLNT_STREAMFLAGS and AUDCLNT_SYSFXFLAGS:
	 
	 	AUDCLNT_STREAMFLAGS (this group of flags uses the high word, w/exception of high-bit which is reserved, 0x7FFF0000):
	                                  
	     AUDCLNT_STREAMFLAGS_CROSSPROCESS - Audio policy control for this stream will be shared with 
	                                        with other process sessions that use the same audio session 
	                                        GUID.
	     AUDCLNT_STREAMFLAGS_LOOPBACK -     Initializes a renderer endpoint for a loopback audio application. 
	                                        In this mode, a capture stream will be opened on the specified 
	                                        renderer endpoint. Shared mode and a renderer endpoint is required.
	                                        Otherwise the IAudioClient::Initialize call will fail. If the 
	                                        initialize is successful, a capture stream will be available 
	                                        from the IAudioClient object.

	     AUDCLNT_STREAMFLAGS_EVENTCALLBACK - An exclusive mode client will supply an event handle that will be
	                                         signaled when an IRP completes (or a waveRT buffer completes) telling
	                                         it to fill the next buffer

	     AUDCLNT_STREAMFLAGS_NOPERSIST -    Session state will not be persisted

	 	 AUDCLNT_SYSFXFLAGS (these flags use low word 0x0000FFFF):

	     none defined currently
	*/
	public static final int AUDCLNT_STREAMFLAGS_CROSSPROCESS  = 0x00010000;
	public static final int AUDCLNT_STREAMFLAGS_LOOPBACK      = 0x00020000;
	public static final int AUDCLNT_STREAMFLAGS_EVENTCALLBACK = 0x00040000;
	public static final int AUDCLNT_STREAMFLAGS_NOPERSIST     = 0x00080000;

	public enum AUDCLNT_SHAREMODE implements IntValuedEnum<AUDCLNT_SHAREMODE> {
		AUDCLNT_SHAREMODE_SHARED(0),
		AUDCLNT_SHAREMODE_EXCLUSIVE(1);

		AUDCLNT_SHAREMODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return value;
		}
		public Iterator<AUDCLNT_SHAREMODE> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<AUDCLNT_SHAREMODE> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum DeviceShareMode implements IntValuedEnum<DeviceShareMode> {
		DeviceShared(0),
		DeviceExclusive (1);

		DeviceShareMode(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return value;
		}
		public Iterator<DeviceShareMode> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DeviceShareMode> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum AudioSessionState implements IntValuedEnum<AudioSessionState> {
		AudioSessionStateInactive(0),
		AudioSessionStateActive(1),
		AudioSessionStateExpired(2);

		AudioSessionState(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return value;
		}
		public Iterator<AudioSessionState> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<AudioSessionState> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	// TODO: Parse error codes
	/*
	 	#define FACILITY_AUDCLNT 0x889
		#define AUDCLNT_ERR(n) MAKE_HRESULT(SEVERITY_ERROR, FACILITY_AUDCLNT, n)
		#define AUDCLNT_SUCCESS(n) MAKE_SCODE(SEVERITY_SUCCESS, FACILITY_AUDCLNT, n)
		#define AUDCLNT_E_NOT_INITIALIZED            AUDCLNT_ERR(0x001)
		#define AUDCLNT_E_ALREADY_INITIALIZED        AUDCLNT_ERR(0x002)
		#define AUDCLNT_E_WRONG_ENDPOINT_TYPE        AUDCLNT_ERR(0x003)
		#define AUDCLNT_E_DEVICE_INVALIDATED         AUDCLNT_ERR(0x004)
		#define AUDCLNT_E_NOT_STOPPED                AUDCLNT_ERR(0x005)
		#define AUDCLNT_E_BUFFER_TOO_LARGE           AUDCLNT_ERR(0x006)
		#define AUDCLNT_E_OUT_OF_ORDER               AUDCLNT_ERR(0x007)
		#define AUDCLNT_E_UNSUPPORTED_FORMAT         AUDCLNT_ERR(0x008)
		#define AUDCLNT_E_INVALID_SIZE               AUDCLNT_ERR(0x009)
		#define AUDCLNT_E_DEVICE_IN_USE              AUDCLNT_ERR(0x00a)
		#define AUDCLNT_E_BUFFER_OPERATION_PENDING   AUDCLNT_ERR(0x00b)
		#define AUDCLNT_E_THREAD_NOT_REGISTERED      AUDCLNT_ERR(0x00c)
		#define AUDCLNT_E_EXCLUSIVE_MODE_NOT_ALLOWED AUDCLNT_ERR(0x00e)
		#define AUDCLNT_E_ENDPOINT_CREATE_FAILED     AUDCLNT_ERR(0x00f)
		#define AUDCLNT_E_SERVICE_NOT_RUNNING        AUDCLNT_ERR(0x010)
		#define AUDCLNT_E_EVENTHANDLE_NOT_EXPECTED     AUDCLNT_ERR(0x011)
		#define AUDCLNT_E_EXCLUSIVE_MODE_ONLY          AUDCLNT_ERR(0x012)
		#define AUDCLNT_E_BUFDURATION_PERIOD_NOT_EQUAL AUDCLNT_ERR(0x013)
		#define AUDCLNT_E_EVENTHANDLE_NOT_SET          AUDCLNT_ERR(0x014)
		#define AUDCLNT_E_INCORRECT_BUFFER_SIZE        AUDCLNT_ERR(0x015)
		#define AUDCLNT_E_BUFFER_SIZE_ERROR            AUDCLNT_ERR(0x016)
		#define AUDCLNT_E_CPUUSAGE_EXCEEDED            AUDCLNT_ERR(0x017)
		#define AUDCLNT_S_BUFFER_EMPTY              AUDCLNT_SUCCESS(0x001)
		#define AUDCLNT_S_THREAD_ALREADY_REGISTERED AUDCLNT_SUCCESS(0x002)
		#define AUDCLNT_S_POSITION_STALLED                 AUDCLNT_SUCCESS(0x003)
	 */
}
