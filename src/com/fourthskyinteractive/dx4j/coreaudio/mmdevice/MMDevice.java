package com.fourthskyinteractive.dx4j.coreaudio.mmdevice;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import com.fourthskyinteractive.dx4j.windows.property.PROPERTYKEY;

/**
 * More information in http://msdn.microsoft.com/en-us/library/windows/desktop/dd316556(v=vs.85).aspx
 * @author evandro.paulino
 *
 */
@Library("")
@Runtime(COMRuntime.class)
public class MMDevice {
	static {
		BridJ.register();
	}
	
	public static final int DEVICE_STATE_ACTIVE     = 0x00000001;
	public static final int DEVICE_STATE_DISABLED   = 0x00000002;
	public static final int DEVICE_STATE_NOTPRESENT = 0x00000004;
	public static final int DEVICE_STATE_UNPLUGGED  = 0x00000008;
	public static final int DEVICE_STATEMASK_ALL    = 0x0000000f;
	public static final int ENDPOINT_SYSFX_ENABLED  = 0x00000000;  // System Effects are enabled.
	public static final int ENDPOINT_SYSFX_DISABLED = 0x00000001;  // System Effects are disabled.
	
	public static final PROPERTYKEY PKEY_Device_FriendlyName = new PROPERTYKEY(0xa45c254e, (short)0xdf1c, (short)0x4efd, new byte[]{(byte) 0x80, 0x20, 0x67, (byte) 0xd1, 0x46, (byte) 0xa8, 0x50, (byte) 0xe0}, 14);

	// Property keys
	/*
		#ifdef DEFINE_PROPERTYKEY
		#undef DEFINE_PROPERTYKEY
		#endif
		#ifdef INITGUID
		#define DEFINE_PROPERTYKEY(name, l, w1, w2, b1, b2, b3, b4, b5, b6, b7, b8, pid) EXTERN_C const PROPERTYKEY name = { { l, w1, w2, { b1, b2,  b3,  b4,  b5,  b6,  b7,  b8 } }, pid }
		#else
		#define DEFINE_PROPERTYKEY(name, l, w1, w2, b1, b2, b3, b4, b5, b6, b7, b8, pid) EXTERN_C const PROPERTYKEY name
		#endif // INITGUID
		DEFINE_PROPERTYKEY(PKEY_AudioEndpoint_FormFactor, 0x1da5d803, 0xd492, 0x4edd, 0x8c, 0x23, 0xe0, 0xc0, 0xff, 0xee, 0x7f, 0x0e, 0); 
		DEFINE_PROPERTYKEY(PKEY_AudioEndpoint_ControlPanelPageProvider, 0x1da5d803, 0xd492, 0x4edd, 0x8c, 0x23, 0xe0, 0xc0, 0xff, 0xee, 0x7f, 0x0e, 1); 
		DEFINE_PROPERTYKEY(PKEY_AudioEndpoint_Association, 0x1da5d803, 0xd492, 0x4edd, 0x8c, 0x23, 0xe0, 0xc0, 0xff, 0xee, 0x7f, 0x0e, 2);
		DEFINE_PROPERTYKEY(PKEY_AudioEndpoint_PhysicalSpeakers, 0x1da5d803, 0xd492, 0x4edd, 0x8c, 0x23, 0xe0, 0xc0, 0xff, 0xee, 0x7f, 0x0e, 3);
		DEFINE_PROPERTYKEY(PKEY_AudioEndpoint_GUID, 0x1da5d803, 0xd492, 0x4edd, 0x8c, 0x23, 0xe0, 0xc0, 0xff, 0xee, 0x7f, 0x0e, 4);
		DEFINE_PROPERTYKEY(PKEY_AudioEndpoint_Disable_SysFx, 0x1da5d803, 0xd492, 0x4edd, 0x8c, 0x23, 0xe0, 0xc0, 0xff, 0xee, 0x7f, 0x0e, 5);
		DEFINE_PROPERTYKEY(PKEY_AudioEndpoint_FullRangeSpeakers, 0x1da5d803, 0xd492, 0x4edd, 0x8c, 0x23, 0xe0, 0xc0, 0xff, 0xee, 0x7f, 0x0e, 6);
		DEFINE_PROPERTYKEY(PKEY_AudioEngine_DeviceFormat, 0xf19f064d, 0x82c, 0x4e27, 0xbc, 0x73, 0x68, 0x82, 0xa1, 0xbb, 0x8e, 0x4c, 0);
		DEFINE_PROPERTYKEY(PKEY_Device_DeviceDesc, 0xa45c254e, 0xdf1c, 0x4efd, 0x80, 0x20, 0x67, 0xd1, 0x46, 0xa8, 0x50, 0xe0, 2);
		DEFINE_PROPERTYKEY(PKEY_Device_FriendlyName, 0xa45c254e, 0xdf1c, 0x4efd, 0x80, 0x20, 0x67, 0xd1, 0x46, 0xa8, 0x50, 0xe0, 14);
		DEFINE_PROPERTYKEY(PKEY_DeviceInterface_FriendlyName,  0x026e516e, 0xb814, 0x414b, 0x83, 0xcd, 0x85, 0x6d, 0x6f, 0xef, 0x48, 0x22, 2); // DEVPROP_TYPE_STRING 	 
	 */
	
	public enum EDataFlow implements IntValuedEnum<EDataFlow> {
		eRender(0),
		eCapture(1),
		eAll(2);

		EDataFlow(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return value;
		}
		public Iterator<EDataFlow> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<EDataFlow> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum ERole implements IntValuedEnum<ERole> {
		eConsole(0),
		eMultimedia(1),
		eCommunications(2);

		ERole(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return value;
		}
		public Iterator<ERole> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<ERole> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum EndpointFormFactor implements IntValuedEnum<EndpointFormFactor> {
		RemoteNetworkDevice(0),
		Speakers(1),
		LineLevel(2),
		Headphones(3),
		Microphone(4),
		Headset(5),
		Handset(6),
		UnknownDigitalPassthrough(7),
		SPDIF(8),
		HDMI(9),
		UnknownFormFactor(10);

		EndpointFormFactor(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return value;
		}
		public Iterator<EndpointFormFactor> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<EndpointFormFactor> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
}
