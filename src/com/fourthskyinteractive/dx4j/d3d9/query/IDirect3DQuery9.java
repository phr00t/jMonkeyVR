package com.fourthskyinteractive.dx4j.d3d9.query;

import org.bridj.Pointer;
import org.bridj.ann.Convention;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.ann.Convention.Style;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DQUERYTYPE;
import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3DDevice9;


@IID("d9771460-a695-4f26-bbd3-27b840b541cc")
@Library("d3d9")
@Runtime(COMRuntime.class)
@Convention(Style.StdCall)
public class IDirect3DQuery9 extends IUnknown {

	public IDirect3DQuery9() {
		// TODO Auto-generated constructor stub
	}
	
	@Virtual(0)
	public native final int GetDevice(Pointer<Pointer<IDirect3DDevice9>> ppDevice);
	
	@Virtual(1)
	public native final D3DQUERYTYPE GetType();
	
	@Virtual(2)
	public native final int GetDataSize();
	
	@Virtual(3)
	public native final int Issue(int dwIssueFlags);
	
	@Virtual(4)
	public native final int GetData(Pointer<?> pData, int dwSize, int dwGetDataFlags);
}
