package com.fourthskyinteractive.dx4j.d3dcompiler;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d11.shader.D3D11_SHADER_VARIABLE_DESC;

@IID("51F23923-F3E5-4BD1-91CB-606177D8DB4C")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11ShaderReflectionVariable extends IUnknown {
	public ID3D11ShaderReflectionVariable() {
		super();
	}
//	public ID3D11ShaderReflectionVariable(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}

	@Virtual(0)
	public native int GetDesc(Pointer<D3D11_SHADER_VARIABLE_DESC> pDesc);
	@Virtual(1)
	public native Pointer<ID3D11ShaderReflectionType> GetType();
	@Virtual(2)
	public native Pointer<ID3D11ShaderReflectionConstantBuffer> GetBuffer();
	@Virtual(3)
	public native int GetInterfaceSlot(int uArrayIndex);
}
