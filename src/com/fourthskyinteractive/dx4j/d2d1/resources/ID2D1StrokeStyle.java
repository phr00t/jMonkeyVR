package com.fourthskyinteractive.dx4j.d2d1.resources;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_CAP_STYLE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_DASH_STYLE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_LINE_JOIN;

@IID("2cd9069d-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1StrokeStyle extends ID2D1Resource {

	public ID2D1StrokeStyle() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1StrokeStyle(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native ValuedEnum<D2D1_CAP_STYLE> GetStartCap();
	@Virtual(1)
	public native D2D1_CAP_STYLE GetEndCap();
	@Virtual(2)
    public native D2D1_CAP_STYLE GetDashCap();
	@Virtual(3)
    public native float GetMiterLimit() ;
	@Virtual(4)
    public native D2D1_LINE_JOIN GetLineJoin() ;
	@Virtual(5)
    public native float GetDashOffset() ;
	@Virtual(6)
    public native D2D1_DASH_STYLE GetDashStyle() ;
	@Virtual(7)
    public native long GetDashesCount();
	@Virtual(8)
	public native void GetDashes(Pointer<Float> dashes, long dashesCount);
}
