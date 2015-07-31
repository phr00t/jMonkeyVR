package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;


@IID("2cd906c1-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1TessellationSink extends IUnknown {

	public ID2D1TessellationSink() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1TessellationSink(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native void AddTriangles(Pointer<D2D1_TRIANGLE> triangles, int trianglesCount);
	@Virtual(1)
	public native int Close();
}
