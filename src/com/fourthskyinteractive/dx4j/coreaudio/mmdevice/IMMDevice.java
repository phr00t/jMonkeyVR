package com.fourthskyinteractive.dx4j.coreaudio.mmdevice;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.windows.property.IPropertyStore;
import com.fourthskyinteractive.dx4j.windows.property.PROPVARIANT;

@IID("D666063F-1587-4E43-81F1-B948E807363F")
@Library("")
@Runtime(COMRuntime.class)
public class IMMDevice extends IUnknown {

	public IMMDevice() {
	}
	
	@Virtual(0)
	public final native int Activate(Pointer<Byte> iid, int dwClsCtx, Pointer<PROPVARIANT> pActivationParams, Pointer<Pointer<?>> ppInterface);

	@Virtual(1)
	public final native int OpenPropertyStore(int stgmAccess, Pointer<Pointer<IPropertyStore>> ppProperties);

	@Virtual(2)
	public final native int GetId(Pointer<Character> ppstrId);
	
	@Virtual(3)
	public final native int GetState(Pointer<Integer> pdwState);
}
