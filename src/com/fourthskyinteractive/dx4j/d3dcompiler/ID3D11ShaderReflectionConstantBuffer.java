package com.fourthskyinteractive.dx4j.d3dcompiler;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d11.shader.D3D11_SHADER_BUFFER_DESC;

@IID("EB62D63D-93DD-4318-8AE8-C6F83AD371B8")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11ShaderReflectionConstantBuffer extends IUnknown {
	public ID3D11ShaderReflectionConstantBuffer() {
		super();
	}
//	public ID3D11ShaderReflectionConstantBuffer(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}
	
	@Virtual(0)
	public native int GetDesc(Pointer<D3D11_SHADER_BUFFER_DESC> pDesc);
	@Virtual(1)
	public native Pointer<ID3D11ShaderReflectionVariable> GetVariableByIndex(int Index);
	@Virtual(2)
	public native Pointer<ID3D11ShaderReflectionVariable> GetVariableByName(Pointer<Byte> Name);
}
