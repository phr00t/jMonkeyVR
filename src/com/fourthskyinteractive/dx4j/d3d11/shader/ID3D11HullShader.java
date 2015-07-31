package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("8e5c6061-628a-4c8e-8264-bbe45cb3d5dd")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11HullShader extends ID3D11DeviceChild {

	public ID3D11HullShader() {
		super();
	}
//	public ID3D11HullShader(Pointer pointer) {
//		super(pointer);
//	}
}
