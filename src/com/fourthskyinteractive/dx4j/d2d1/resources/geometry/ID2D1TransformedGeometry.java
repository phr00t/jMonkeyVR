package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;


@IID("2cd906bb-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1TransformedGeometry extends ID2D1Geometry {

	public ID2D1TransformedGeometry() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1TransformedGeometry(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	
	@Virtual(0)
	public native void GetSourceGeometry(Pointer<Pointer<ID2D1Geometry>> ppSourceGeometry);
	@Virtual(1)
	public native void GetTransform(Pointer<D2D1_MATRIX_3X2_F> transform); 
}
