package com.fourthskyinteractive.dx4j.d3d9.shader;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3DDevice9;

@IID("EFC5557E-6265-4613-8A94-43857889EB36")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DVertexShader9 extends IUnknown {

	public IDirect3DVertexShader9() {
	}

	@Virtual(0)
	public native final int GetDevice(Pointer<Pointer<IDirect3DDevice9>> ppDevice);
	@Virtual(1)
	public native final int GetFunction(Pointer<?> pFuncName, Pointer<Integer> pSizeOfData);
}
