package com.fourthskyinteractive.dx4j.d2d1.resources.brush;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1GradientStopCollection;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_POINT_2F;

@IID("2cd906ab-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1LinearGradientBrush extends ID2D1Brush {

	public ID2D1LinearGradientBrush() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1LinearGradientBrush(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native void SetStartPoint(D2D1_POINT_2F startPoint);
	@Virtual(1)
	public native void SetEndPoint(D2D1_POINT_2F endPoint);
	@Virtual(2)
	public native D2D1_POINT_2F GetStartPoint();
	@Virtual(3)
	public native D2D1_POINT_2F GetEndPoint();
	@Virtual(4)
	public native void GetGradientStopCollection(Pointer<Pointer<ID2D1GradientStopCollection>> ppGradientStopCollection);
}
