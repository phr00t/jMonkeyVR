package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("4f5b196e-c2bd-495e-bd01-1fded38e4969")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11ComputeShader extends ID3D11DeviceChild {

	public ID3D11ComputeShader() {
		super();
	}
//	public ID3D11ComputeShader(Pointer pointer) {
//		super(pointer);
//	}
}
