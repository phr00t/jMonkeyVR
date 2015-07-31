package com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.RECT;


@IID("1c51bc64-de61-46fd-9899-63a5d8f03950")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1DCRenderTarget extends ID2D1RenderTarget {

	public ID2D1DCRenderTarget() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1DCRenderTarget(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native int BindDC(Pointer<?> hDC, Pointer<RECT> pSubRect);
}
