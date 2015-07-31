package com.fourthskyinteractive.dx4j.d3dcompiler;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d11.shader.D3D11_SHADER_TYPE_DESC;

@IID("6E6FFA6A-9BAE-4613-A51E-91652D508C21")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11ShaderReflectionType extends IUnknown {

	public ID3D11ShaderReflectionType() {
		super();
	}
//	public ID3D11ShaderReflectionType(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}
	
	@Virtual(0)
	public native int GetDesc(Pointer<D3D11_SHADER_TYPE_DESC> pDesc);
	@Virtual(1)
	public native Pointer<ID3D11ShaderReflectionType> GetMemberTypeByIndex(int Index);
	@Virtual(2)
	public native Pointer<ID3D11ShaderReflectionType> GetMemberTypeByNamex(Pointer<Byte> Name);
	@Virtual(3)
	public native Pointer<Byte> GetMemberTypeName(int Index);
	@Virtual(4)
	public native int IsEqual(Pointer<ID3D11ShaderReflectionType> pType);
	@Virtual(5)
	public native Pointer<ID3D11ShaderReflectionType> GetSubType();
	@Virtual(6)
	public native Pointer<ID3D11ShaderReflectionType> GetBaseClass();
	@Virtual(7)
	public native int GetNumInterfaces();
	@Virtual(8)
	public native Pointer<ID3D11ShaderReflectionType> GetInterfaceByIndex(int uIndex);
	@Virtual(9)
	public native int IsOfType(Pointer<ID3D11ShaderReflectionType> pType);
	@Virtual(10)
	public native int ImplementsInterface(Pointer<ID3D11ShaderReflectionType> pType);
}
