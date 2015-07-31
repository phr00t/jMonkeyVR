package com.fourthskyinteractive.dx4j.d3d9.states;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3DDevice9;

@IID("B07C4FE5-310D-4ba8-A23C-4F0F206F218B")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DStateBlock9 extends IUnknown {

	public IDirect3DStateBlock9() {
	}

	@Virtual(0)
	public native final int GetDevice(Pointer<Pointer<IDirect3DDevice9>> ppDevice);
    
	@Virtual(1) 
	public final native int Capture();
    
	@Virtual(2) 
	public final native int Apply();
}
