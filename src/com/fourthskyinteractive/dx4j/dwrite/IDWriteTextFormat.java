package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("")
@Library("dwrite")
@Runtime(COMRuntime.class)
public class IDWriteTextFormat extends IUnknown {

	public IDWriteTextFormat() {
		super();
	}

//	public IDWriteTextFormat(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}

}
