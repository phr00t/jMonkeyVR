package com.fourthskyinteractive.dx4j.d3d9.core;

import org.bridj.ann.Convention;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Convention.Style;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("794950F2-ADFC-458a-905E-10A10B0B503B")
@Library("d3d9")
@Runtime(COMRuntime.class)
@Convention(Style.StdCall)
public class IDirect3DSwapChain9 extends IUnknown {

	public IDirect3DSwapChain9() {
		// TODO Auto-generated constructor stub
	}

}
