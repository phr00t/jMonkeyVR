package com.fourthskyinteractive.dx4j.d2d1.resources.brush;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1Resource;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_MATRIX_3X2_F;

@IID("2cd906a8-12e2-11dc-9fed-001143a055f9")
@Library("D2D1")
@Runtime(COMRuntime.class)
public class ID2D1Brush extends ID2D1Resource {

	public ID2D1Brush() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1Brush(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native void SetOpacity(float opacity);
	@Virtual(1)
	public native void SetTransform(Pointer<D2D1_MATRIX_3X2_F> pTransform);
	@Virtual(2)
	public native float GetOpacity();
	@Virtual(3)
	public native void GetTransform(Pointer<D2D1_MATRIX_3X2_F> pTransform);
}
