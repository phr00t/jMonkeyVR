package com.fourthskyinteractive.dx4j.d3dcompiler;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_FEATURE_LEVEL;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_PRIMITIVE;
import com.fourthskyinteractive.dx4j.d3d11.shader.D3D11_SHADER_DESC;
import com.fourthskyinteractive.dx4j.d3d11.shader.D3D11_SHADER_INPUT_BIND_DESC;
import com.fourthskyinteractive.dx4j.d3d11.shader.D3D11_SIGNATURE_PARAMETER_DESC;

@IID("0a233719-3960-4578-9d7c-203b8b1d9cc1")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11ShaderReflection extends IUnknown {
	public ID3D11ShaderReflection() {
		super();
	}
//	public ID3D11ShaderReflection(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}

	@Virtual(3)
	public native int GetDesc(Pointer<D3D11_SHADER_DESC> pDesc);
	@Virtual(4)
	public native Pointer<ID3D11ShaderReflectionConstantBuffer> GetConstantBufferByIndex(int Index);
	@Virtual(5)
	public native Pointer<ID3D11ShaderReflectionConstantBuffer> GetConstantBufferByName(Pointer<Byte> Name);
	@Virtual(6)
	public native int GetResourceBindingDesc(int ResourceIndex, Pointer<D3D11_SHADER_INPUT_BIND_DESC> pDesc);
	@Virtual(7)
	public native int GetInputParameterDesc(int ParameterIndex, Pointer<D3D11_SIGNATURE_PARAMETER_DESC> pDesc);
	@Virtual(8)
	public native int GetOutputParameterDesc(int ParameterIndex, Pointer<D3D11_SIGNATURE_PARAMETER_DESC> pDesc);
	@Virtual(9)
	public native int GetPatchConstantParameterDesc(int ParameterIndex, Pointer<D3D11_SIGNATURE_PARAMETER_DESC> pDesc);
	@Virtual(10)
	public native Pointer<ID3D11ShaderReflectionVariable> GetVariableByName(Pointer<Byte> Name);
	@Virtual(11)
	public native int GetResourceBindingDescByName(Pointer<Byte> Name, Pointer<D3D11_SHADER_INPUT_BIND_DESC> pDesc);
	@Virtual(12)
	public native int GetMovInstructionCount();
	@Virtual(13)
	public native int GetMovcInstructionCount();
	@Virtual(14)
	public native int GetConversionInstructionCount();
	@Virtual(15)
	public native int GetBitwiseInstructionCount();
	@Virtual(16)
	public native D3D_PRIMITIVE GetGSInputPrimitive();
	@Virtual(17)
	public native int IsSampleFrequenceShader();
	@Virtual(18)
	public native int GetNumInterfaceSlots();
	@Virtual(19)
	public native int GetMinFeatureLevel(Pointer<D3D_FEATURE_LEVEL> pLevel);
	@Virtual(20)
	public native int GetThreadGroupSize(Pointer<Integer> pSizeX, Pointer<Integer> pSizeY, Pointer<Integer> pSizeZ);
}
