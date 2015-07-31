package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_FIGURE_BEGIN;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_FIGURE_END;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_FILL_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_PATH_SEGMENT;

@IID("")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1SimplifiedGeometrySink extends IUnknown {

	public ID2D1SimplifiedGeometrySink() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1SimplifiedGeometrySink(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native void SetFillMode(ValuedEnum<D2D1_FILL_MODE> fillMode);
	@Virtual(1)
	public native void SetSegmentFlags(ValuedEnum<D2D1_PATH_SEGMENT> vertexFlags);
	@Virtual(2)
	public native void BeginFigure(D2D1_POINT_2F startPoint, ValuedEnum<D2D1_FIGURE_BEGIN> figureBegin);
	@Virtual(3)
	public native void AddLines(Pointer<D2D1_POINT_2F> points, int pointsCount);
	@Virtual(4)
	public native void AddBeziers(Pointer<D2D1_BEZIER_SEGMENT> beziers, int beziersCount);
	@Virtual(5)
	public native void EndFigure(ValuedEnum<D2D1_FIGURE_END> figureEnd);
	@Virtual(6)
	public native int Close();
}

