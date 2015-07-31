package com.fourthskyinteractive.dx4j.d3d11.layer;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("1ef337e3-58e7-4f83-a692-db221f5ed47e")
@Library("D3D11")
@Runtime(COMRuntime.class)
public class ID3D11SwitchToRef extends IUnknown {

	public ID3D11SwitchToRef() {
		// TODO Auto-generated constructor stub
	}

	@Virtual(0)
	public final native int SetUseRef(int UseRef);
        
    @Virtual(1)
    public final native int GetUseRef();
}
