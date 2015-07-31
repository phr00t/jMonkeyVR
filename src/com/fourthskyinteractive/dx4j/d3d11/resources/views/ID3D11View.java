package com.fourthskyinteractive.dx4j.d3d11.resources.views;

import com.fourthskyinteractive.dx4j.util.Describable;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;
import com.fourthskyinteractive.dx4j.d3d11.resources.ID3D11Resource;

@IID("839d1216-bb2e-412b-b7f4-a9dbebe08ed1")
@Library("d3d11")
@Runtime(COMRuntime.class)
public abstract class ID3D11View extends ID3D11DeviceChild implements Describable {
	public ID3D11View() {
		super();
	}
//	public ID3D11View(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void GetResource(Pointer<Pointer<ID3D11Resource>> ppResource);
}
