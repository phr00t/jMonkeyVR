package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_FILL_MODE;

@IID("2cd906a6-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1GeometryGroup extends ID2D1Geometry {

	public ID2D1GeometryGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1GeometryGroup(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native ValuedEnum<D2D1_FILL_MODE> GetFillMode();
	@Virtual(1)
	public native int GetSourceGeometryCount();
	@Virtual(2)
	public native void GetSourceGeometries(Pointer<Pointer<ID2D1Geometry>> geometries, int geometriesCount);
}
