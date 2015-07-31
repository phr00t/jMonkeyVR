package com.fourthskyinteractive.dx4j.d3d9.resources;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import com.fourthskyinteractive.dx4j.d3d9.D3DBOX;

@IID("85C31227-3DE5-4f00-9B3A-F11AC38C18B5")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DVolumeTexture9 extends IDirect3DBaseTexture9 {

	public IDirect3DVolumeTexture9() {
	}

	@Virtual(0)
	public native final int GetLevelDesc(int Level, Pointer<D3DVOLUME_DESC> pDesc);
	
	@Virtual(1)
	public native final int GetVolumeLevel(int Leve, Pointer<Pointer<IDirect3DVolume9>> ppVolumeLevel); 
	
	@Virtual(2)
	public native final int LockBox(int Level, Pointer<D3DLOCKED_BOX> pLockedBox, Pointer<D3DBOX> pRect, int Flags);
	
	@Virtual(3)
	public native final int UnlockBox(int Level);
	
	@Virtual(4)
	public native final int AddDirtyBox(Pointer<D3DBOX> pDirtyRect);
}
