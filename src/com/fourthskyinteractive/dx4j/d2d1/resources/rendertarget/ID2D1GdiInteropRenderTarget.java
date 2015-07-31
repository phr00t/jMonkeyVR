package com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.RECT;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_DC_INITIALIZE_MODE;

@IID("e0db51c3-6f77-4bae-b3d5-e47509b35838")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1GdiInteropRenderTarget extends ID2D1RenderTarget {

	public ID2D1GdiInteropRenderTarget() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1GdiInteropRenderTarget(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native int GetDC(ValuedEnum<D2D1_DC_INITIALIZE_MODE> mode, Pointer<?> hdc);
	@Virtual(1)
	public native int ReleaseDC(Pointer<RECT> update);
}
