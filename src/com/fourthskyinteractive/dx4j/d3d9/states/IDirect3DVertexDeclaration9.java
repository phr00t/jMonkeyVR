package com.fourthskyinteractive.dx4j.d3d9.states;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3DDevice9;

@IID("DD13C59C-36FA-4098-A8FB-C7ED39DC8546")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DVertexDeclaration9 extends IUnknown {

	public IDirect3DVertexDeclaration9() {
	}

	@Virtual(0)
	public native final int GetDevice(Pointer<Pointer<IDirect3DDevice9>> ppDevice);
	
	@Virtual(1)
	public native final int GetDeclaration(Pointer<D3DVERTEXELEMENT9> pElements, Pointer<Integer> pNumElements);
}
