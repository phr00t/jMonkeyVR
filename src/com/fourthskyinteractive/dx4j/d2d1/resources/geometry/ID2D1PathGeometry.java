package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;


@IID("2cd906a5-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1PathGeometry extends ID2D1Geometry {

	public ID2D1PathGeometry() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1PathGeometry(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native int Open(Pointer<Pointer<ID2D1GeometrySink>> ppGeometrySink);
	@Virtual(1)
	public native int Stream(Pointer<ID2D1GeometrySink> geometrySink);
	@Virtual(2)
	public native int GetSegmentCount(Pointer<Integer> count);
	@Virtual(3)
	public native int GetFigureCount(Pointer<Integer> count);
}
