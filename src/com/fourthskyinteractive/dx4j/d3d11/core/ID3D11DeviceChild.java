package com.fourthskyinteractive.dx4j.d3d11.core;

import com.fourthskyinteractive.dx4j.util.Describable;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("1841e5c8-16b0-489b-bcc8-44cfb0d5deae")
@Library("d3d11") 
@Runtime(COMRuntime.class)
public class ID3D11DeviceChild extends IUnknown {
	public ID3D11DeviceChild() {
		super();
	}
//	public ID3D11DeviceChild(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void GetDevice(Pointer<Pointer<ID3D11Device>> pDevice);
	@Virtual(1)
	public native int GetPrivateData(Pointer<Byte> guid, Pointer<Integer> pDataSize, Pointer<?> pData);
	@Virtual(2)
	public native int SetPrivateData(Pointer<Byte> guid, Pointer<Integer> pDataSize, Pointer<?> pData);
	@Virtual(3)
	public native int SetPrivateDataInterface(Pointer<Byte> guid, Pointer<IUnknown> pData);
}
