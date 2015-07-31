package com.fourthskyinteractive.dx4j.d3d11.query;

import com.fourthskyinteractive.dx4j.util.Describable;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("4b35d0cd-1e15-4258-9c98-1b1333f6dd3b")
@Library("d3d11")
@Runtime(COMRuntime.class)
public abstract class ID3D11Asynchronous extends ID3D11DeviceChild implements Describable {

	public ID3D11Asynchronous() {
		super();
	}
//	public ID3D11Asynchronous(Pointer pointer) {
//		super(pointer);
//	}

    @Virtual(0)
    public native int GetDataSize();
}
