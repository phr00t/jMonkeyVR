package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("38325b96-effb-4022-ba02-2e795b70275c")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11GeometryShader extends ID3D11DeviceChild {

	public ID3D11GeometryShader() {
		super();
	}
//	public ID3D11GeometryShader(Pointer pointer) {
//		super(pointer);
//	}
}
