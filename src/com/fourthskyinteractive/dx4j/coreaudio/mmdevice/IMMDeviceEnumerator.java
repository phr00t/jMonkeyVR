package com.fourthskyinteractive.dx4j.coreaudio.mmdevice;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.coreaudio.mmdevice.MMDevice.EDataFlow;
import com.fourthskyinteractive.dx4j.coreaudio.mmdevice.MMDevice.ERole;

@IID("A95664D2-9614-4F35-A746-DE8DB63617E6")
@Library("")
@Runtime(COMRuntime.class)
public class IMMDeviceEnumerator extends IUnknown {

	public IMMDeviceEnumerator() {
	}

	@Virtual(0)
	public final native int EnumAudioEndpoints(EDataFlow dataFlow, int dwStateMask, Pointer<Pointer<IMMDeviceCollection>> ppDevices);
	
	@Virtual(1)
	public final native int GetDefaultAudioEndpoint(EDataFlow dataFlow, ERole role, Pointer<Pointer<IMMDevice>> ppDevice);
	
	@Virtual(2)
	public final native int GetDevice(Pointer<Character> pwstrId, Pointer<Pointer<IMMDevice>> ppDevice);
	
	@Virtual(3)
	public final native int RegisterEndpointNotificationCallback(Pointer<IMMNotificationClient> pClient);
	
	@Virtual(4)
	public final native int UnregisterEndpointNotificationCallback(Pointer<IMMNotificationClient> pClient);
}
