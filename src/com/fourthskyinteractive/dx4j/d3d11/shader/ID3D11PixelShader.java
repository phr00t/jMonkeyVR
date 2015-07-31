package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("ea82e40d-51dc-4f33-93d4-db7c9125ae8c")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11PixelShader extends ID3D11DeviceChild {

	public ID3D11PixelShader() {
		super();
	}
//	public ID3D11PixelShader(Pointer pointer) {
//		super(pointer);
//	}
}
