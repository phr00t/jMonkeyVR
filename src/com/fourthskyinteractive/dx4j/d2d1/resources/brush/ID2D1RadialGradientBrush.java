package com.fourthskyinteractive.dx4j.d2d1.resources.brush;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1GradientStopCollection;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_POINT_2F;

@IID("2cd906ac-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1RadialGradientBrush extends ID2D1Brush {

	public ID2D1RadialGradientBrush() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1RadialGradientBrush(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native void SetCenter(D2D1_POINT_2F center);
	@Virtual(1)
	public native void SetGradientOriginOffset(D2D1_POINT_2F gradientOriginOffset);
	@Virtual(2)
	public native void SetRadiusX(float radiusX);
	@Virtual(3)
	public native void SetRadiusY(float radiusY);
	@Virtual(4)
	public native D2D1_POINT_2F GetCenter();
	@Virtual(5)
	public native D2D1_POINT_2F GetGradientOriginOffset();
	@Virtual(6)
	public native float GetRadiusX();
	@Virtual(7)
	public native float GetRadiusY();
	@Virtual(8)
	public native void GetGradientStopCollection(Pointer<Pointer<ID2D1GradientStopCollection>> ppGradientStopCollection);
}
