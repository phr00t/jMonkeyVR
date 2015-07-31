package com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_SIZE_U;
import com.fourthskyinteractive.dx4j.windows.HWND;

@IID("2cd90698-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1HwndRenderTarget extends ID2D1RenderTarget {

	public ID2D1HwndRenderTarget() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1HwndRenderTarget(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native /*D2D1_WINDOW_STATE*/ int CheckWindowState();
	@Virtual(1)
	public native int Resize(Pointer<D2D1_SIZE_U> pixelSize);
	@Virtual(2)
	public native HWND GetHwnd();
}
