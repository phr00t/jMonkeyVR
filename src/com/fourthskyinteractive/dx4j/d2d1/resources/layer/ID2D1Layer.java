package com.fourthskyinteractive.dx4j.d2d1.resources.layer;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1Resource;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_SIZE_F;

@IID("2cd9069b-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1Layer extends ID2D1Resource {

	public ID2D1Layer() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1Layer(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	@Virtual(0)
	public native D2D1_SIZE_F GetSize();
}
