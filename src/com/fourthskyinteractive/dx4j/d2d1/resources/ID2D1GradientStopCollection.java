package com.fourthskyinteractive.dx4j.d2d1.resources;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_EXTEND_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_GAMMA;

@IID("2cd906a7-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1GradientStopCollection extends ID2D1Resource {

	public ID2D1GradientStopCollection() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1GradientStopCollection(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native long GetGradientStopCount();
	@Virtual(1)
	public native void GetGradientStops(Pointer<D2D1_GRADIENT_STOP> pGradientStops, long gradientStopsCount);
	@Virtual(2)
	public native ValuedEnum<D2D1_GAMMA> GetColorInterpolationGamma();
	@Virtual(3)
	public native ValuedEnum<D2D1_EXTEND_MODE> GetExtendMode();
}
