package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.Pointer;
import org.bridj.SizeT;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("a6cd7faa-b0b7-4a2f-9436-8662a65797cb")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11ClassInstance extends ID3D11DeviceChild {
	public ID3D11ClassInstance() {
		super();
	}
//	public ID3D11ClassInstance(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void GetClassLinkage(Pointer<Pointer<ID3D11ClassLinkage>> ppLinkage);
	@Virtual(1)
	public native void GetDesc(Pointer<D3D11_CLASS_INSTANCE_DESC> pDesc);
	@Virtual(2)
	public native void GetInstanceName(Pointer<?> pInstanceName, Pointer<SizeT> pBufferLength);
	@Virtual(3)
	public native void GetTypeName(Pointer<?> pInstanceName, Pointer<SizeT> pBufferLength);
}
