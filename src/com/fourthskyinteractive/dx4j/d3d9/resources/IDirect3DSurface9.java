package com.fourthskyinteractive.dx4j.d3d9.resources;

import org.bridj.ann.Convention;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Convention.Style;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("0CFBAF3A-9FF6-429a-99B3-A2796AF8B89B")
@Library("d3d9")
@Runtime(COMRuntime.class)
@Convention(Style.StdCall)
public class IDirect3DSurface9 extends IUnknown {

	public IDirect3DSurface9() {
		// TODO Auto-generated constructor stub
	}

}
