package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("3b301d64-d678-4289-8897-22f8928b72f3")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11VertexShader extends ID3D11DeviceChild {

	public ID3D11VertexShader() {
		super();
	}
//	public ID3D11VertexShader(Pointer pointer) {
//		super(pointer);
//	}
}
