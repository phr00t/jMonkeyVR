package com.fourthskyinteractive.dx4j.coreaudio.mmdevice;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("0BD7A1BE-7A1A-44DB-8397-CC5392387B5E")
@Library("")
@Runtime(COMRuntime.class)
public class IMMDeviceCollection extends IUnknown {

	public IMMDeviceCollection() {
	}

	@Virtual(0)
	public final native int GetCount(Pointer<Integer> pcDevices);
	
	@Virtual(1)
	public final native int Item(int nDevice, Pointer<Pointer<IMMDevice>> ppDevice);
}
