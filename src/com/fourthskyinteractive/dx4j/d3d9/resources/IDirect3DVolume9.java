package com.fourthskyinteractive.dx4j.d3d9.resources;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.D3DBOX;
import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3DDevice9;

@IID("24F416E6-1F67-4aa7-B88E-D33F6F3128A1")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DVolume9 extends IUnknown {

	public IDirect3DVolume9() {
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
	public native final int GetContainer(Pointer<Byte> riid, Pointer<Pointer<?>> ppContainer);
	
	@Virtual(5)
	public native final int GetDesc(Pointer<D3DVOLUME_DESC> pDesc);
	
	@Virtual(6)
	public native final int LockBox(Pointer<D3DLOCKED_BOX> pLockedBox, Pointer<D3DBOX> pRect, int Flags);
	
	@Virtual(7)
	public native final int UnlockBox();
}
