package com.fourthskyinteractive.dx4j.coreaudio.mmdevice;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.windows.property.PROPVARIANT;

@IID("3B0D0EA4-D0A9-4B0E-935B-09516746FAC0")
@Library("")
@Runtime(COMRuntime.class)
public class IMMDeviceActivator extends IUnknown {

	public IMMDeviceActivator() {
	}

	@Virtual(0)
	public final native int Activate(Pointer<Byte> iid, Pointer<IMMDevice> pDevice, Pointer<PROPVARIANT> pActivationParams, Pointer<Pointer<?>> ppInterface);
}
