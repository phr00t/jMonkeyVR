package com.fourthskyinteractive.dx4j.d2d1.resources;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d2d1.core.ID2D1Factory;

@IID("2cd90691-12e2-11dc-9fed-001143a055f9")
@Library("D2D1")
@Runtime(COMRuntime.class)
public class ID2D1Resource extends IUnknown {
	public ID2D1Resource() {
		super();
	}
//	public ID2D1Resource(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}
	
	@Virtual(0)
	public native void GetFactory(Pointer<Pointer<ID2D1Factory>> ppFactory);
}
