package com.fourthskyinteractive.dx4j.d2d1.resources.bitmap;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.core.D2D1_PIXEL_FORMAT;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1Resource;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_POINT_2U;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_RECT_U;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_SIZE_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_SIZE_U;
import com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget.ID2D1RenderTarget;

@IID("a2296057-ea42-4099-983b-539fb6505426")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1Bitmap extends ID2D1Resource {

	public ID2D1Bitmap() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1Bitmap(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native D2D1_SIZE_F GetSize();
	@Virtual(1)
	public native D2D1_SIZE_U GetPixelSize();
	@Virtual(2)
	public native D2D1_PIXEL_FORMAT GetPixelFormat();
	@Virtual(3)
	public native void GetDpi(Pointer<Float> dpiX, Pointer<Float> dpiY);
	@Virtual(4)
	public native int CopyFromBitmap(Pointer<D2D1_POINT_2U> destPoint, Pointer<ID2D1Bitmap> pBitmap, Pointer<D2D1_RECT_U> srcRect);
	@Virtual(5)
	public native int CopyFromRenderTarget(Pointer<D2D1_POINT_2U> destPoint, Pointer<ID2D1RenderTarget> pBitmap, Pointer<D2D1_RECT_U> srcRect);
	@Virtual(6)
	public native int CopyFromMemory(Pointer<D2D1_POINT_2U> destPoint, Pointer<Byte> srcData, long pitch);
}
