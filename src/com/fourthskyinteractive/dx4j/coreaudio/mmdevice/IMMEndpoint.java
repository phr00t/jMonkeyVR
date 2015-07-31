package com.fourthskyinteractive.dx4j.coreaudio.mmdevice;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.coreaudio.mmdevice.MMDevice.EDataFlow;

@IID("1BE09788-6894-4089-8586-9A2A6C265AC5")
@Library("")
@Runtime(COMRuntime.class)
public class IMMEndpoint extends IUnknown {

	public IMMEndpoint() {
		
	}
	
	@Virtual(0)
	public final native int GetDataFlow(Pointer<ValuedEnum<EDataFlow>> pDataFlow);
}
