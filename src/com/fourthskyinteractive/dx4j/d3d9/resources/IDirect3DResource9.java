package com.fourthskyinteractive.dx4j.d3d9.resources;

import org.bridj.Pointer;
import org.bridj.ann.Convention;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.ann.Convention.Style;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DRESOURCETYPE;
import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3DDevice9;

@IID("05EEC05D-8F7D-4362-B999-D1BAF357C704")
@Library("d3d9")
@Runtime(COMRuntime.class)
@Convention(Style.StdCall)
public class IDirect3DResource9 extends IUnknown {

	public IDirect3DResource9() {
		// TODO Auto-generated constructor stub
	}

	@Virtual(0)
	public native final int GetDevice(Pointer<Pointer<IDirect3DDevice9>> ppDevice);
	
	@Virtual(1)
	public native final int SetPrivateData(Pointer<Byte> refguid, Pointer<?> pData, int SizeOfData, int Flags);
	
	@Virtual(2)
	public native final int GetPrivateData(Pointer<Byte> refguid, Pointer<?> pData, Pointer<Integer> pSizeOfData);
	
	@Virtual(3)
	public native final int FreePrivateData(Pointer<Byte> refguid);
	
	@Virtual(4)
	public native final int SetPriority(int PriorityNew);
	
	@Virtual(5)
	public native final int GetPriority();
	
	@Virtual(6)
	public native final int PreLoad();
	
	@Virtual(7)
	public native final D3DRESOURCETYPE GetType();
}
