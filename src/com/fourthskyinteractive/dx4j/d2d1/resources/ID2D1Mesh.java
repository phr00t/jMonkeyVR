package com.fourthskyinteractive.dx4j.d2d1.resources;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1TessellationSink;

@IID("2cd906c2-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1Mesh extends ID2D1Resource {

	public ID2D1Mesh() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1Mesh(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native int Open(Pointer<Pointer<ID2D1TessellationSink>> ppTessellationSink);
}
