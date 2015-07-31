package com.fourthskyinteractive.dx4j.d3d11.core;

import static com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_QUERY;
import static org.bridj.BridJ.sizeOf;
import static org.bridj.Pointer.*;
import static org.bridj.Pointer.allocate;

import com.fourthskyinteractive.dx4j.d3d11.D3D11;
import com.fourthskyinteractive.dx4j.d3d11.query.D3D11_QUERY_DESC;
import com.fourthskyinteractive.dx4j.d3d11.query.ID3D11Query;
import com.fourthskyinteractive.dx4j.windows.WindowsException;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Array;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.RECT;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_ASYNC_GETDATA_FLAG;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_CLEAR_FLAG;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_DEVICE_CONTEXT_TYPE;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_MAP;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_PRIMITIVE_TOPOLOGY;
import com.fourthskyinteractive.dx4j.d3d11.D3D11Exception;
import com.fourthskyinteractive.dx4j.d3d11.query.ID3D11Asynchronous;
import com.fourthskyinteractive.dx4j.d3d11.query.ID3D11Predicate;
import com.fourthskyinteractive.dx4j.d3d11.resources.D3D11_BOX;
import com.fourthskyinteractive.dx4j.d3d11.resources.D3D11_MAPPED_SUBRESOURCE;
import com.fourthskyinteractive.dx4j.d3d11.resources.ID3D11Buffer;
import com.fourthskyinteractive.dx4j.d3d11.resources.ID3D11Resource;
import com.fourthskyinteractive.dx4j.d3d11.resources.views.ID3D11DepthStencilView;
import com.fourthskyinteractive.dx4j.d3d11.resources.views.ID3D11RenderTargetView;
import com.fourthskyinteractive.dx4j.d3d11.resources.views.ID3D11ShaderResourceView;
import com.fourthskyinteractive.dx4j.d3d11.resources.views.ID3D11UnorderedAccessView;
import com.fourthskyinteractive.dx4j.d3d11.shader.ID3D11ClassInstance;
import com.fourthskyinteractive.dx4j.d3d11.shader.ID3D11ComputeShader;
import com.fourthskyinteractive.dx4j.d3d11.shader.ID3D11DomainShader;
import com.fourthskyinteractive.dx4j.d3d11.shader.ID3D11GeometryShader;
import com.fourthskyinteractive.dx4j.d3d11.shader.ID3D11HullShader;
import com.fourthskyinteractive.dx4j.d3d11.shader.ID3D11PixelShader;
import com.fourthskyinteractive.dx4j.d3d11.shader.ID3D11VertexShader;
import com.fourthskyinteractive.dx4j.d3d11.states.ID3D11BlendState;
import com.fourthskyinteractive.dx4j.d3d11.states.ID3D11DepthStencilState;
import com.fourthskyinteractive.dx4j.d3d11.states.ID3D11InputLayout;
import com.fourthskyinteractive.dx4j.d3d11.states.ID3D11RasterizerState;
import com.fourthskyinteractive.dx4j.d3d11.states.ID3D11SamplerState;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_FORMAT;

@IID("c0bfa96c-e089-44fb-8eaf-26f8796190da")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11DeviceContext extends ID3D11DeviceChild {
	public ID3D11DeviceContext() {
		super();
	}
//	public ID3D11DeviceContext(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void VSSetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppBuffers);
	@Virtual(1)
	public native void PSSetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppViews);
	@Deprecated @Virtual(2)
	public native void PSSetShader(Pointer<ID3D11PixelShader> pPixelShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, int NumClassInstances);
	@Virtual(3)
	public native void PSSetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Deprecated @Virtual(4)
	public native void VSSetShader(Pointer<ID3D11VertexShader> pVertexShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, int NumClassInstances);
	@Virtual(5)
	public native void DrawIndexed(int IndexCount, int StartIndexLocation, int BaseVertexLocation);
	@Virtual(6)
	public native void Draw(int VertexCount, int StartVertexLocation);
	@Deprecated @Virtual(7)
	public native int Map(Pointer<? extends ID3D11Resource> pResource, int Subresource, ValuedEnum<D3D11_MAP> MapType, int MapFlags, Pointer<D3D11_MAPPED_SUBRESOURCE> pMappedResource);
	@Deprecated @Virtual(8)
	public native void Unmap(Pointer<? extends ID3D11Resource> pResource, int Subresource);
	@Virtual(9)
	public native void PSSetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Deprecated @Virtual(10)
	public native void IASetInputLayout(Pointer<ID3D11InputLayout> pLayout);
	@Deprecated @Virtual(11)
	public native void IASetVertexBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppVertexBuffers, Pointer<Integer> pStrides, Pointer<Integer> pOffsets);
	@Deprecated @Virtual(12)
	public native void IASetIndexBuffer(Pointer<ID3D11Buffer> pIndexBuffer, DXGI_FORMAT Format, int Offset);
	@Virtual(13)
	public native void DrawIndexedInstanced(int IndexCountPerInstance, int InstanceCount, int StartIndexLocation, int BaseVertexLocation, int StartInstanceLocation);
	@Virtual(14)
	public native void DrawInstanced(int VertexCountPerInstance, int InstanceCount, int StartVertexLocation, int StartInstanceLocation);
	@Virtual(15)
	public native void GSSetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Deprecated @Virtual(16)
	public native void GSSetShader(Pointer<ID3D11GeometryShader> pShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, int NumClassInstances);
	@Virtual(17)
	public native void IASetPrimitiveTopology(D3D_PRIMITIVE_TOPOLOGY Topology);
	@Virtual(18)
	public native void VSSetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(19)
	public native void VSSetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Deprecated @Virtual(20)
	public native void Begin(Pointer<? extends ID3D11Asynchronous> pAsync);
	@Deprecated @Virtual(21)
	public native void End(Pointer<? extends ID3D11Asynchronous> pAsync);
	@Deprecated	@Virtual(22)
	public native int GetData(Pointer<? extends ID3D11Asynchronous> pAsync, Pointer<?> pData, int DataSize, ValuedEnum<D3D11_ASYNC_GETDATA_FLAG> GetDataFlags);
	@Virtual(23)
	public native void SetPredication(Pointer<ID3D11Predicate> pPredicate, int PredicateValue);
	@Virtual(24)
	public native int GSSetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(25)
	public native void GSSetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(26)
	public native void OMSetRenderTargets(int NumViews, Pointer<Pointer<ID3D11RenderTargetView>> ppRenderTargetViews, Pointer<ID3D11DepthStencilView> pDepthStencilView);
	@Virtual(27)
	public native void OMSetRenderTargetsAndUnorderedAccessViews(int NumRTVs, Pointer<Pointer<ID3D11RenderTargetView>> ppRenderTargetViews, Pointer<ID3D11DepthStencilView> pDepthStencilView, int UAVStartSlot, int NumUAVs, Pointer<Pointer<ID3D11UnorderedAccessView>> ppUnorderedAccessViews, int pUAVInitialCounts);
	@Virtual(28)
	public native void OMSetBlendState( Pointer<ID3D11BlendState> pBlendState, @Array(4) Pointer<Float> BlendFactor, int SampleMask);
	@Virtual(29)
	public native void OMSetDepthStencilState(Pointer<ID3D11DepthStencilState> pDepthStencilState, int StencilRef);
	@Virtual(30)
	public native void SOSetTargets(int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppSOTargets, Pointer<Integer> pOffsets);
	@Virtual(31)
	public native void DrawAuto();
	@Virtual(32)
	public native void DrawIndexedInstancedIndirect(Pointer<ID3D11Buffer> pBufferForArgs, int AlignedByteOffsetForArgs);
	@Virtual(33)
	public native void DrawInstancedIndirect(Pointer<ID3D11Buffer> pBufferForArgs, int AlignedByteOffsetForArgs);
	@Virtual(34)
	public native void Dispatch(int ThreadGroupCountX, int ThreadGroupCountY, int ThreadGroupCountZ);
	@Virtual(35)
	public native void DispatchIndirect(Pointer<ID3D11Buffer> pBufferForArgs, int AlignedByteOffsetForArgs);
	@Virtual(36)
	public native void RSSetState(Pointer<ID3D11RasterizerState> pRasterizerState);
	@Virtual(37)
	public native void RSSetViewports(int NumViewports, Pointer<D3D11_VIEWPORT> pViewports);
	@Virtual(38)
	public native void RSSetScissorRects(int NumRects, Pointer<RECT> pRects);
	@Virtual(39)
	public native void CopySubresourceRegion(Pointer<? extends ID3D11Resource> pDstResource, int DstSubresource, int DstX, int DstY, int DstZ, Pointer<ID3D11Resource> pSrcResource, int SrcSubresource, Pointer<D3D11_BOX> pSrcBox);
	@Virtual(40)
	public native void CopyResource(Pointer<? extends ID3D11Resource> pDstResource, Pointer<? extends ID3D11Resource> pSrcResource);
	@Virtual(41)
	public native void UpdateSubresource(Pointer<? extends ID3D11Resource> pDstResource, int DstSubresource, Pointer<D3D11_BOX> pDstBox, Pointer<?> pSrcData, int SrcRowPitch, int SrcDepthPitch);
	@Virtual(42)
	public native void CopyStructureCount(Pointer<ID3D11Buffer> pDstBuffer, int DstAlignedByteOffset, Pointer<ID3D11UnorderedAccessView> pSrcView);
	@Deprecated @Virtual(43)
	public native void ClearRenderTargetView(Pointer<ID3D11RenderTargetView> pRenderTargetView, @Array(4) Pointer<Float> ColorRGBA);
	@Virtual(44)
	public native void ClearUnorderedAccessViewUint(Pointer<ID3D11UnorderedAccessView> pUnorderedAccessView, @Array(4) Pointer<Integer> Values);
	@Virtual(45)
	public native void ClearUnorderedAccessViewFloat(Pointer<ID3D11UnorderedAccessView> pUnorderedAccessView,  @Array(4) Pointer<Float> Values);
	@Virtual(46)
	public native void ClearDepthStencilView(Pointer<ID3D11DepthStencilView> pDepthStencilView, ValuedEnum<D3D11_CLEAR_FLAG> ClearFlags, float Depth, byte Stencil);
	@Virtual(47)
	public native void GenerateMips(Pointer<ID3D11ShaderResourceView> pShaderResourceView);
	@Virtual(48)
	public native void SetResourceMinLOD(Pointer<? extends ID3D11Resource> pResource, float MinLOD);
	@Virtual(49)
	public native float GetResourceMinLOD(Pointer<? extends ID3D11Resource> pResource);
	@Virtual(50)
	public native void ResolveSubresource(Pointer<? extends ID3D11Resource> pDstResource, int DstSubresource, Pointer<? extends ID3D11Resource> pSrcResource, int SrcSubresource, DXGI_FORMAT Format);
	@Virtual(51)
	public native void ExecuteCommandList(Pointer<ID3D11CommandList> pCommandList, int RestoreContextState);
	@Virtual(52)
	public native void HSSetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Deprecated @Virtual(53)
	public native void HSSetShader(Pointer<ID3D11HullShader> pHullShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, int NumClassInstances);
	@Virtual(54)
	public native void HSSetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(55)
	public native void HSSetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(56)
	public native void DSSetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Deprecated @Virtual(57)
	public native void DSSetShader(Pointer<ID3D11DomainShader> pDomainShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, int NumClassInstances);
	@Virtual(58)
	public native void DSSetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(59)
	public native void DSSetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(60)
	public native void CSSetShaderResources( int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(61)
	public native void CSSetUnorderedAccessViews(int StartSlot, int NumUAVs, Pointer<Pointer<ID3D11UnorderedAccessView>> ppUnorderedAccessViews,  Pointer<Integer> pUAVInitialCounts);
	@Deprecated @Virtual(62)
	public native void CSSetShader(Pointer<ID3D11ComputeShader> pComputeShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, int NumClassInstances);
	@Virtual(63)
	public native void CSSetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(64)
	public native void CSSetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(65)
	public native void VSGetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(66)
	public native void PSGetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(67)
	public native void PSGetShader(Pointer<Pointer<ID3D11PixelShader>> ppPixelShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances,  Pointer<Integer> pNumClassInstances);
	@Virtual(68)
	public native void PSGetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(69)
	public native void VSGetShader(Pointer<Pointer<ID3D11VertexShader>> ppVertexShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances,  Pointer<Integer> pNumClassInstances);
	@Virtual(70)
	public native void PSGetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(71)
	public native void IAGetInputLayout(Pointer<Pointer<ID3D11InputLayout>> ppInputLayout);
	@Virtual(72)
	public native void IAGetVertexBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppVertexBuffers, Pointer<Integer> pStrides, Pointer<Integer> pOffsets);
	@Virtual(73)
	public native void IAGetIndexBuffer(Pointer<Pointer<ID3D11Buffer>> pIndexBuffer, Pointer<DXGI_FORMAT> pFormat, Pointer<Integer> Offset);
	@Virtual(74)
	public native void GSGetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(75)
	public native void GSGetShader(Pointer<Pointer<ID3D11GeometryShader>> ppGeometryShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, Pointer<Integer> pNumClassInstances);
	@Virtual(76)
	public native void IAGetPrimitiveTopology(Pointer<D3D_PRIMITIVE_TOPOLOGY> pTopology);
	@Virtual(77)
	public native void VSGetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(78)
	public native void VSGetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(79)
	public native void GetPredication(Pointer<Pointer<ID3D11Predicate>> ppPredicate, Pointer<Integer> pPredicateValue);
	@Virtual(80)
	public native void GSGetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(81)
	public native void GSGetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(82)
	public native void OMGetRenderTargets(int NumViews, Pointer<Pointer<ID3D11RenderTargetView>> ppRenderTargetViews, Pointer<Pointer<ID3D11DepthStencilView>> ppDepthStencilView);
	@Virtual(83)
	public native void OMGetRenderTargetsAndUnorderedAccessViews(int NumRTVs, Pointer<Pointer<ID3D11RenderTargetView>> ppRenderTargetViews, Pointer<Pointer<ID3D11DepthStencilView>> ppDepthStencilView, int UAVStartSlot, int NumUAVs, Pointer<Pointer<ID3D11UnorderedAccessView>> ppUnorderedAccessViews);
	@Virtual(84)
	public native void OMGetBlendState(Pointer<Pointer<ID3D11BlendState>> ppBlendState, @Array(4) Pointer<Float> BlendFactor, Pointer<Integer> pSampleMask);
	@Virtual(85)
	public native void OMGetDepthStencilState(Pointer<Pointer<ID3D11DepthStencilState>> ppDepthStencilState, Pointer<Integer> pStencilRef);
	@Virtual(86)
	public native void SOGetTargets(int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppSOTargets);
	@Virtual(87)
	public native void RSGetState(Pointer<Pointer<ID3D11RasterizerState>> ppState);
	@Virtual(88)
	public native void RSGetViewports(Pointer<Integer> pNumViewports, Pointer<D3D11_VIEWPORT> pViewports);
	@Virtual(89)
	public native void RSGetScissorRects(Pointer<Integer> pNumRects, Pointer<RECT> pRects);
	@Virtual(90)
	public native void HSGetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(91)
	public native void HSGetShader(Pointer<Pointer<ID3D11HullShader>> ppHullShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, Pointer<Integer> pNumClassInstances);
	@Virtual(92)
	public native void HSGetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(93)
	public native void HSGetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(94)
	public native void DSGetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(95)
	public native void DSGetShader(Pointer<Pointer<ID3D11HullShader>> ppHullShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, Pointer<Integer> pNumClassInstances);
	@Virtual(96)
	public native void DSGetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(97)
	public native void DSGetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(98)
	public native void CSGetShaderResources(int StartSlot, int NumViews, Pointer<Pointer<ID3D11ShaderResourceView>> ppShaderResourceViews);
	@Virtual(99)
	public native void CSGetUnorderedAccessViews(int StartSlot, int NumUAVs, Pointer<Pointer<ID3D11UnorderedAccessView>> ppShaderResourceViews);
	@Virtual(100)
	public native void CSGetShader(Pointer<Pointer<ID3D11HullShader>> ppHullShader, Pointer<Pointer<ID3D11ClassInstance>> ppClassInstances, Pointer<Integer> pNumClassInstances);
	@Virtual(101)
	public native void CSGetSamplers(int StartSlot, int NumSamplers, Pointer<Pointer<ID3D11SamplerState>> ppSamplers);
	@Virtual(102)
	public native void CSGetConstantBuffers(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers);
	@Virtual(103)
	public native void ClearState();
	@Virtual(104)
	public native void Flush();
	@Virtual(105)
	public native D3D11_DEVICE_CONTEXT_TYPE GetType();
	@Virtual(106)
	public native int GetContextFlags();
	@Deprecated @Virtual(107)
	public native int FinishCommandList(int RestoreDeferredContextState, Pointer<Pointer<ID3D11CommandList>> ppCommandList);

    public final boolean isDeferred() {
        return GetType().equals(D3D11_DEVICE_CONTEXT_TYPE.D3D11_DEVICE_CONTEXT_DEFERRED);
    }

	// "Javanized methods
	public final void OMSetRenderTargets(ID3D11RenderTargetView rTView, ID3D11DepthStencilView dSView) {
		OMSetRenderTargets(1, 
							rTView != null ? pointerToPointer(pointerTo(rTView)) : null,
							dSView != null ? pointerTo(dSView) : null);
	}
	
	public final void OMSetRenderTargets(ID3D11RenderTargetView[] rTViews, ID3D11DepthStencilView dSView) {
		Pointer<Pointer<ID3D11RenderTargetView>> pp = allocatePointers(ID3D11RenderTargetView.class, rTViews.length);
		
		try {
			for(int i = 0; i < rTViews.length; i++) {
				pp.set(i, pointerTo(rTViews[i]));
			}
			
			OMSetRenderTargets(rTViews.length, pp, 
								dSView != null ? pointerTo(dSView) : null);
		} finally {
			pp.release();
			pp = null;
		}
	}
	
	public final void VSSetShader(ID3D11VertexShader shader, ID3D11ClassInstance[] instances) {
		Pointer<Pointer<ID3D11ClassInstance>> pp = null;
		
		try {
			if (instances != null && instances.length > 0) {
				
				pp = allocatePointers(ID3D11ClassInstance.class, instances.length);
				for(int i = 0; i < instances.length; i++) {
					pp.set(i, pointerTo(instances[i]));
				}				
				
				VSSetShader(pointerTo(shader), pp, instances.length);
				
			} else {
				VSSetShader(pointerTo(shader), null, 0);
			}
			
		} finally {
			if (pp != null) {
				pp.release();
				pp = null;
			}
		}
	}
	
	public final void PSSetShader(ID3D11PixelShader shader, ID3D11ClassInstance[] instances) {
		Pointer<Pointer<ID3D11ClassInstance>> pp = null;
		
		try {
			if (instances != null && instances.length > 0) {
				
				pp = allocatePointers(ID3D11ClassInstance.class, instances.length);
				for(int i = 0; i < instances.length; i++) {
					pp.set(i, pointerTo(instances[i]));
				}				
				
				PSSetShader(pointerTo(shader), pp, instances.length);
				
			} else {
				PSSetShader(pointerTo(shader), null, 0);
			}
			
		} finally {
			if (pp != null) {
				pp.release();
				pp = null;
			}
		}
	}
	
	public final void GSSetShader(ID3D11GeometryShader shader, ID3D11ClassInstance[] instances) {
		Pointer<Pointer<ID3D11ClassInstance>> pp = null;
		
		try {
			if (instances != null && instances.length > 0) {
				
				pp = allocatePointers(ID3D11ClassInstance.class, instances.length);
				for(int i = 0; i < instances.length; i++) {
					pp.set(i, pointerTo(instances[i]));
				}				
				
				GSSetShader(pointerTo(shader), pp, instances.length);
				
			} else {
				GSSetShader(pointerTo(shader), null, 0);
			}
			
		} finally {
			if (pp != null) {
				pp.release();
				pp = null;
			}
		}
	}
	
	public final void HSSetShader(ID3D11HullShader shader, ID3D11ClassInstance[] instances) {
		Pointer<Pointer<ID3D11ClassInstance>> pp = null;
		
		try {
			if (instances != null && instances.length > 0) {
				
				pp = allocatePointers(ID3D11ClassInstance.class, instances.length);
				for(int i = 0; i < instances.length; i++) {
					pp.set(i, pointerTo(instances[i]));
				}				
				
				HSSetShader(pointerTo(shader), pp, instances.length);
				
			} else {
				HSSetShader(pointerTo(shader), null, 0);
			}
			
		} finally {
			if (pp != null) {
				pp.release();
				pp = null;
			}
		}
	}
	
	public final void DSSetShader(ID3D11DomainShader shader, ID3D11ClassInstance[] instances) {
		Pointer<Pointer<ID3D11ClassInstance>> pp = null;
		
		try {
			if (instances != null && instances.length > 0) {
				
				pp = allocatePointers(ID3D11ClassInstance.class, instances.length);
				for(int i = 0; i < instances.length; i++) {
					pp.set(i, pointerTo(instances[i]));
				}				
				
				DSSetShader(pointerTo(shader), pp, instances.length);
				
			} else {
				DSSetShader(pointerTo(shader), null, 0);
			}
			
		} finally {
			if (pp != null) {
				pp.release();
				pp = null;
			}
		}
	}
	
	public final void CSSetShader(ID3D11ComputeShader shader, ID3D11ClassInstance[] instances) {
		Pointer<Pointer<ID3D11ClassInstance>> pp = null;
		
		try {
			if (instances != null && instances.length > 0) {
				
				pp = allocatePointers(ID3D11ClassInstance.class, instances.length);
				for(int i = 0; i < instances.length; i++) {
					pp.set(i, pointerTo(instances[i]));
				}				
				
				CSSetShader(pointerTo(shader), pp, instances.length);
				
			} else {
				CSSetShader(pointerTo(shader), null, 0);
			}
			
		} finally {
			if (pp != null) {
				pp.release();
				pp = null;
			}
		}
	}

	public final void IASetInputLayout(ID3D11InputLayout layout) {
		IASetInputLayout(pointerTo(layout));
	}
	
	public final void IASetVertexBuffers(int startSlot, int numBuffers, ID3D11Buffer vertexBuffer, int stride, int offset) {
		IASetVertexBuffers(startSlot, numBuffers, pointerToPointer(pointerTo(vertexBuffer)), pointerToInt(stride), pointerToInt(offset));
	}
	
	public final void IASetVertexBuffers(int StartSlot, int NumBuffers, ID3D11Buffer[] vertexBuffers, int[] strides, int[] offsets) {
		Pointer<Pointer<ID3D11Buffer>> pp = allocatePointers(ID3D11Buffer.class, vertexBuffers.length);
		
		try {
			for(int i = 0; i < vertexBuffers.length; i++) {
				pp.set(i, pointerTo(vertexBuffers[i]));
			}
			
			IASetVertexBuffers(StartSlot, NumBuffers, pp, pointerToInts(strides), pointerToInts(offsets));			
			
		} finally {
			pp.release();
			pp = null;
		}
	}
	
	public final void ClearRenderTargetView(ID3D11RenderTargetView rtView, float[] ColorRGBA) {
		ClearRenderTargetView(pointerTo(rtView), pointerToFloats(ColorRGBA));
	}
	
	public final D3D11_MAPPED_SUBRESOURCE Map(ID3D11Resource resource, int subResource, ValuedEnum<D3D11_MAP> MapType, int MapFlags) throws D3D11Exception {
		D3D11_MAPPED_SUBRESOURCE mappedData = new D3D11_MAPPED_SUBRESOURCE();

		int result = Map(pointerTo(resource), subResource, MapType, MapFlags, pointerTo(mappedData));
		if(result != 0) {
			throw new D3D11Exception(result);
		}
		
		return mappedData;
	}
	
	public final void Unmap(ID3D11Resource resource, int subResource) {
		Unmap(pointerTo(resource), subResource);
	}
	
	public final void Begin(ID3D11Asynchronous async) {
		Begin(pointerTo(async));
	}
	
	public final void End(ID3D11Asynchronous async) {
		End(pointerTo(async));
	}

    @SuppressWarnings("unchecked")
	public final <T> T GetData(ID3D11Asynchronous pAsync, /*Class<T> klazz,*/ ValuedEnum<D3D11_ASYNC_GETDATA_FLAG> GetDataFlags) throws D3D11Exception {
        // Allocate a pointer to return object
        Pointer<?> pData = null;

        try {
            D3D11_QUERY_DESC queryDesc = pAsync.GetDesc();
            Class<?> queryResultClass = queryDesc.ResultClass();

            pData = allocate(queryResultClass);
            int DataSize = (int)sizeOf(queryResultClass);

            int result = GetData(pointerTo(pAsync), pData, DataSize, GetDataFlags);
            if(result != 0) {
                throw new D3D11Exception(result);
            }

            return (T) pData.as(queryResultClass).get();

        } finally {
            if (pData != null) {
                pData.release();
            }
        }
    }

	public final void RSSetViewports(D3D11_VIEWPORT... viewports) {
		if (viewports.length == 1) {
			this.RSSetViewports(1, pointerTo(viewports[0]));
			
		} else {
			Pointer<D3D11_VIEWPORT> pVP = pointerToArray(viewports);
			this.RSSetViewports(viewports.length, pVP);
			
		}
	}
	
	public final ID3D11CommandList FinishCommandList(int RestoreDeferredContextState) throws D3D11Exception {
		Pointer<Pointer<ID3D11CommandList>> pp = allocatePointer(ID3D11CommandList.class);
		try {
			int result = this.FinishCommandList(RestoreDeferredContextState, pp);
			if(result != 0) {
				throw new D3D11Exception(result);
			}
			
			return pp.get().getNativeObject(ID3D11CommandList.class);
		} finally {
			pp.release();
			pp = null;
		}		
	}

}
