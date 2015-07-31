package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("8BA5FB08-5195-40e2-AC58-0D989C3A0102")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D10Blob extends IUnknown {
	public ID3D10Blob() {
		super();
	}
//	public ID3D10Blob(Pointer pointer) {
//		super(pointer);
//	}
	
	@Virtual(0) 
	public native Pointer<?> GetBufferPointer();
	@Virtual(1) 
	public native long GetBufferSize();
}
