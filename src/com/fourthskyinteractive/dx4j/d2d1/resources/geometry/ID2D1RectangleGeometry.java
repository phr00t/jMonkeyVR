package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;


@IID("2cd906a2-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1RectangleGeometry extends ID2D1Geometry {
	public ID2D1RectangleGeometry() {
		super();
	}
//	public ID2D1RectangleGeometry(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}
	
	@Virtual(0)
	public native void GetRect(Pointer<D2D1_RECT_F> rect);
}
