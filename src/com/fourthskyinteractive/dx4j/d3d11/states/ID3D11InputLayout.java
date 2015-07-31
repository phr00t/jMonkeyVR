package com.fourthskyinteractive.dx4j.d3d11.states;

import org.bridj.StructObject;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("e4819ddc-4cf0-4025-bd26-5de82a3e07b7")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11InputLayout extends ID3D11DeviceChild {

	public ID3D11InputLayout() {
		super();
	}
//	public ID3D11InputLayout(Pointer pointer) {
//		super(pointer);
//	}
}
