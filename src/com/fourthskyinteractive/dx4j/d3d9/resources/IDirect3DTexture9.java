package com.fourthskyinteractive.dx4j.d3d9.resources;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.RECT;


@IID("85C31227-3DE5-4f00-9B3A-F11AC38C18B5")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DTexture9 extends IDirect3DBaseTexture9 {

	public IDirect3DTexture9() {
	}

	@Virtual(0)
	public native final int GetLevelDesc(int Level, Pointer<D3DSURFACE_DESC> pDesc);
	
	@Virtual(1)
	public native final int LockRect(int Level, Pointer<D3DLOCKED_RECT> pLockedRect, Pointer<RECT> pRect, int Flags);
	
	@Virtual(2)
	public native final int UnlockRect(int Level);
	
	@Virtual(3)
	public native final int AddDirtyRect(Pointer<RECT> pDirtyRect);
}
