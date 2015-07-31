package com.fourthskyinteractive.dx4j.d3d9.shader;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3DDevice9;

@IID("6D3BDBDC-5B02-4415-B852-CE5E8BCCB289")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DPixelShader9 extends IUnknown {

	public IDirect3DPixelShader9() {
	}

	@Virtual(0)
	public native final int GetDevice(Pointer<Pointer<IDirect3DDevice9>> ppDevice);
	@Virtual(1)
	public native final int GetFunction(Pointer<?> pFuncName, Pointer<Integer> pSizeOfData);
}
