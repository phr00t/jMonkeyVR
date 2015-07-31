package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("a24bc4d1-769e-43f7-8013-98ff566c18e2")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11CommandList extends ID3D11DeviceChild {
	public ID3D11CommandList() {
		super();
	}
//	public ID3D11CommandList(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native int GetContextFlags();
}
