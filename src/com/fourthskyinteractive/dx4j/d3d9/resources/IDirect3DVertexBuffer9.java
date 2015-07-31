package com.fourthskyinteractive.dx4j.d3d9.resources;

import org.bridj.Pointer;
import org.bridj.ann.Convention;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.ann.Convention.Style;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;


@IID("B64BB1B5-FD70-4df6-BF91-19D0A12455E3")
@Library("d3d9")
@Runtime(COMRuntime.class)
@Convention(Style.StdCall)
public class IDirect3DVertexBuffer9 extends IDirect3DResource9 {

	public IDirect3DVertexBuffer9() {
		// TODO Auto-generated constructor stub
	}
	
	@Virtual(0)
	public native final int Lock(int OffsetToLock, int SizeToLock, Pointer<Pointer<?>> ppbData, int Flags);

	@Virtual(1)
	public native final int Unlock();
	
	@Virtual(2)
	public native final int GetDesc(Pointer<D3DVERTEXBUFFER_DESC> pDesc);
}
