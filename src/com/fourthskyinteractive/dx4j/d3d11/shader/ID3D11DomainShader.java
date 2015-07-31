package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("f582c508-0f36-490c-9977-31eece268cfa")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11DomainShader extends ID3D11DeviceChild {

	public ID3D11DomainShader() {
		super();
	}
//	public ID3D11DomainShader(Pointer pointer) {
//		super(pointer);
//	}
}
