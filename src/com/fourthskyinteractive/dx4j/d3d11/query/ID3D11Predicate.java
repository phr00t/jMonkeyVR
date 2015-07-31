package com.fourthskyinteractive.dx4j.d3d11.query;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("9eb576dd-9f77-4d86-81aa-8bab5fe490e2")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11Predicate extends ID3D11Query {
	public ID3D11Predicate() {
		super();
	}
//	public ID3D11Predicate(Pointer pointer) {
//		super(pointer);
//	}
}
