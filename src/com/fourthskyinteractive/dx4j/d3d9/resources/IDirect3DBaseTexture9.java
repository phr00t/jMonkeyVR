package com.fourthskyinteractive.dx4j.d3d9.resources;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DTEXTUREFILTERTYPE;

@IID("580CA87E-1D3C-4d54-991D-B7D3E3C298CE")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DBaseTexture9 extends IDirect3DResource9 {

	public IDirect3DBaseTexture9() {
		// TODO Auto-generated constructor stub
	}

	@Virtual(0)
	public native final int SetLOD(int LODNew);
	
	@Virtual(1)
	public native final int GetLOD();
	
	@Virtual(2)
	public native final int GetLevelCount();
	
	@Virtual(3)
	public native final int SetAutoGenFilterType(D3DTEXTUREFILTERTYPE FilterType);
	
	@Virtual(4)
	public native final D3DTEXTUREFILTERTYPE GetAutoGenFilterType();
	
	@Virtual(5)
	public native final void GenerateMipSubLevels();
}
