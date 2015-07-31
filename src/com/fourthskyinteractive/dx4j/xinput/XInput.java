package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.GUID;

@Runtime(COMRuntime.class)
@Library("XInput1_3")
public class XInput {
	static {
		BridJ.register();
	}
	
	// Button constants 
	public static final int XINPUT_GAMEPAD_DPAD_UP         = 0x00000001;
	public static final int XINPUT_GAMEPAD_DPAD_DOWN       = 0x00000002;
	public static final int XINPUT_GAMEPAD_DPAD_LEFT       = 0x00000004;
	public static final int XINPUT_GAMEPAD_DPAD_RIGHT      = 0x00000008;
	public static final int XINPUT_GAMEPAD_START           = 0x00000010;
	public static final int XINPUT_GAMEPAD_BACK            = 0x00000020;
	public static final int XINPUT_GAMEPAD_LEFT_THUMB      = 0x00000040;
	public static final int XINPUT_GAMEPAD_RIGHT_THUMB     = 0x00000080;
	public static final int XINPUT_GAMEPAD_LEFT_SHOULDER   = 0x0100;
	public static final int XINPUT_GAMEPAD_RIGHT_SHOULDER  = 0x0200;
	public static final int XINPUT_GAMEPAD_A               = 0x1000;
	public static final int XINPUT_GAMEPAD_B               = 0x2000;
	public static final int XINPUT_GAMEPAD_X               = 0x4000;
	public static final int XINPUT_GAMEPAD_Y               = 0x8000;
	
	// Battery information
	public static final byte BATTERY_TYPE_DISCONNECTED = 0;
	public static final byte BATTERY_TYPE_WIRED = 1;
	public static final byte BATTERY_TYPE_ALKALINE = 2;
	public static final byte BATTERY_TYPE_NIMH = 3;
	public static final byte BATTERY_TYPE_UNKNOWN = 4;
	
	public static final byte BATTERY_LEVEL_EMPTY = 0;
	public static final byte BATTERY_LEVEL_LOW = 1;
	public static final byte BATTERY_LEVEL_MEDIUM = 2;
	public static final byte BATTERY_LEVEL_FULL = 3;
	
	public static final byte BATTERY_DEVTYPE_GAMEPAD = (byte)0x0;
	public static final byte BATTERY_DEVTYPE_HEADSET = (byte)0x1;
	
	// Flags for XInputGetCapabilities
	public static final int ANY_FLAG_GAMEPAD = 0x0;
	public static final int XINPUT_FLAG_GAMEPAD = 0x1;
	
	
	public static final native void XInputEnable(int enable);
	
	public static final native short XInputSetState(short dwUserIndex, Pointer<XINPUT_VIBRATION> pVibration);
	
	public static final native short XInputGetState(short dwUserIndex, Pointer<XINPUT_STATE> pState);
	
	public static final native int XInputGetKeystroke(int dwUserIndex, int dwReserved, Pointer<XINPUT_KEYSTROKE> pKeystroke);
	
	public static final native int XInputGetBatteryInformation(int dwUserIndex, byte devType, Pointer<XINPUT_BATTERY_INFORMATION> pBatteryInfo);
	
	public static final native int XInputGetCapabilities(int dwUserIndex, int dwFlags, Pointer<XINPUT_CAPABILITIES> pCapabilities);
	
	/**
	 * To integrate with DirectSound, see: http://msdn.microsoft.com/en-us/library/windows/desktop/ee417001(v=vs.85).aspx
	 * @param dwUserIndex
	 * @param pDSoundRenderGuid
	 * @param pDSoundCaptureGuid
	 * @return
	 */
	public static final native int XInputGetDSoundAudioDeviceGuids(int dwUserIndex, Pointer<GUID> pDSoundRenderGuid, Pointer<GUID> pDSoundCaptureGuid);
}
