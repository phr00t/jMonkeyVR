package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;


@IID("2cd9069f-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1GeometrySink extends ID2D1SimplifiedGeometrySink {

	public ID2D1GeometrySink() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1GeometrySink(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native void AddLine(D2D1_POINT_2F point);
	@Virtual(1)
	public native void AddBezier(Pointer<D2D1_BEZIER_SEGMENT> bezier);
	@Virtual(2)
	public native void AddQuadraticBezier(Pointer<D2D1_QUADRATIC_BEZIER_SEGMENT> bezier);
	@Virtual(3)
	public native void AddQuadraticBeziers(Pointer<D2D1_QUADRATIC_BEZIER_SEGMENT> beziers, int beziersCount);
	@Virtual(4)
	public native void AddArc(Pointer<D2D1_ARC_SEGMENT> arc);
}
