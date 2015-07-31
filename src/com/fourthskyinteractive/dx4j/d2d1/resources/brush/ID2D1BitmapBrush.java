package com.fourthskyinteractive.dx4j.d2d1.resources.brush;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_BITMAP_INTERPOLATION_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_EXTEND_MODE;
import com.fourthskyinteractive.dx4j.d2d1.resources.bitmap.ID2D1Bitmap;

@IID("2cd906aa-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1BitmapBrush extends ID2D1Brush {

	public ID2D1BitmapBrush() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1BitmapBrush(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native void SetExtendModeX(ValuedEnum<D2D1_EXTEND_MODE> extendModeX);
	@Virtual(1)
	public native void SetExtendModeY(ValuedEnum<D2D1_EXTEND_MODE> extendModeY);
	@Virtual(2)
	public native void SetInterpolationMode(ValuedEnum<D2D1_BITMAP_INTERPOLATION_MODE> interpolationMode);
	@Virtual(3)
	public native void SetBitmap(Pointer<ID2D1Bitmap> bitmap);
	@Virtual(4)
	public native ValuedEnum<D2D1_EXTEND_MODE> GetExtendModeX();
	@Virtual(5)
	public native ValuedEnum<D2D1_EXTEND_MODE> GetExtendModeY();
	@Virtual(6)
	public native ValuedEnum<D2D1_BITMAP_INTERPOLATION_MODE> GetInterpolationMode();
	@Virtual(7)
	public native void GetBitmap(Pointer<Pointer<ID2D1Bitmap>> ppBitmap);
}
