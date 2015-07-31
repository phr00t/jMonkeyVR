package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.GUID;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.states.D3D11_BLEND_DESC1;
import com.fourthskyinteractive.dx4j.d3d11.states.D3D11_RASTERIZER_DESC1;
import com.fourthskyinteractive.dx4j.d3d11.states.ID3D11BlendState1;
import com.fourthskyinteractive.dx4j.d3d11.states.ID3D11RasterizerState1;
import com.fourthskyinteractive.dx4j.windows.HANDLE;

@IID("a04bfb29-08ef-43d6-a49c-a9bdbdcbe686")
@Library("d3d11_1")
@Runtime(COMRuntime.class)
public class ID3D11Device1 extends ID3D11Device {
	
	public ID3D11Device1() {
		// TODO Auto-generated constructor stub
	}

	@Virtual(0)
	public final native int GetImmediateContext1(Pointer<Pointer<ID3D11DeviceContext1>> ppImmediateContext);
	
	@Virtual(1)
	public final native int CreateDeferredContext1(int ContextFlags, Pointer<Pointer<ID3D11DeviceContext1>> ppDeferredContext);
	
	@Virtual(2)
	public final native int CreateBlendState1(Pointer<D3D11_BLEND_DESC1> pBlendDesc, Pointer<Pointer<ID3D11BlendState1>> ppBlendState);
	
	@Virtual(3)
	public final native int CreateRasterizerState1(Pointer<D3D11_RASTERIZER_DESC1> pBlendDesc, Pointer<Pointer<ID3D11RasterizerState1>> ppRasterizerState);
	
	@Virtual(4)
	public final native int OpenSharedResource1(HANDLE hResource, Pointer<GUID> returnedInterface, Pointer<Pointer<?>> ppResource);
	
	@Virtual(5)
	public final native int OpenSharedResourceByName(Pointer<Character> lpName, int dwDesiredAccess, Pointer<GUID> returnedInterface, Pointer<Pointer<?>> ppResource);
	
}
