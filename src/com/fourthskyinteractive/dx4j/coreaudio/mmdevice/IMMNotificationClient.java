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
import com.fourthskyinteractive.dx4j.windows.property.PROPERTYKEY;

@IID("7991EEC9-7E89-4D85-8390-6C703CEC60C0")
@Library("")
@Runtime(COMRuntime.class)
public class IMMNotificationClient extends IUnknown {

	public IMMNotificationClient() {
	}

	@Virtual(0)
	public final native int OnDeviceStateChanged(Pointer<Character> pwstrDeviceId, int dwNewState);
	
	@Virtual(1)
	public final native int OnDeviceAdded(Pointer<Character> pwstrDeviceId);
	
	@Virtual(2)
	public final native int OnDeviceRemoved(Pointer<Character> pwstrDeviceId);
	
	@Virtual(3)
	public final native int OnDefaultDeviceChanged(EDataFlow flow, ERole role, Pointer<Character> pwstrDefaultDeviceId);
	
	@Virtual(4)
	public final native int OnPropertyValueChanged(Pointer<Character> pwstrDeviceId, PROPERTYKEY key);
}
