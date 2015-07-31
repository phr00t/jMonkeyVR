package com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.resources.bitmap.ID2D1Bitmap;

@IID("2cd90695-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1BitmapRenderTarget extends ID2D1RenderTarget {

	public ID2D1BitmapRenderTarget() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1BitmapRenderTarget(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native int GetBitmap(Pointer<Pointer<ID2D1Bitmap>> bitmap);
}
