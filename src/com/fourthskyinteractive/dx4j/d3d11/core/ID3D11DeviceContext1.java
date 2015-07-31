package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.RECT;

import com.fourthskyinteractive.dx4j.d3d11.resources.D3D11_BOX;
import com.fourthskyinteractive.dx4j.d3d11.resources.ID3D11Buffer;
import com.fourthskyinteractive.dx4j.d3d11.resources.ID3D11Resource;
import com.fourthskyinteractive.dx4j.d3d11.resources.views.ID3D11View;

import static org.bridj.Pointer.*;

@IID("bb2c6faa-b5fb-4082-8e6b-388b8cfa90e1")
@Library("D3D11_1")
@Runtime(COMRuntime.class)
public class ID3D11DeviceContext1 extends ID3D11DeviceContext {

	public ID3D11DeviceContext1() {
		// TODO Auto-generated constructor stub
	}
	
	@Virtual(0)
	public final native void CopySubresourceRegion1(Pointer<ID3D11Resource> pDstResource, int DstSubresource, 
													int DstX, int DstY, int DstZ, Pointer<ID3D11Resource> pSrcResource,
													int SrcSubresource, Pointer<D3D11_BOX> pSrcBox, int CopyFlags);

	@Virtual(1)
	public native void UpdateSubresource1(Pointer<? extends ID3D11Resource> pDstResource, int DstSubresource, Pointer<D3D11_BOX> pDstBox, 
										  Pointer<?> pSrcData, int SrcRowPitch, int SrcDepthPitch, int CopyFlags);
	
	@Virtual(2)
	public final native void DiscardView(Pointer<ID3D11View> pResourceView);
	
	@Virtual(3)
	public final native void DiscardResource(Pointer<ID3D11Resource> pResource);
	
	@Virtual(4)
	public final native void VSSetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(5)
	public final native void HSSetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(6)
	public final native void DSSetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(7)
	public final native void GSSetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(8)
	public final native void PSSetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(9)
	public final native void CSSetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(10)
	public final native void VSGetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(11)
	public final native void HSGetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(12)
	public final native void DSGetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(13)
	public final native void GSGetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(14)
	public final native void PSGetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(15)
	public final native void CSGetConstantBuffers1(int StartSlot, int NumBuffers, Pointer<Pointer<ID3D11Buffer>> ppConstantBuffers,
													Pointer<Integer> pFirstConstant, Pointer<Integer> pNumConstants);
	
	@Virtual(16)
	public final native void SwapDeviceContextState(Pointer<ID3DDeviceContextState> pState, Pointer<ID3DDeviceContextState> pPreviousState);
	
	@Virtual(17)
	public final native void ClearView(Pointer<ID3D11View> pView, Pointer<Float> pColor, Pointer<RECT> pRect);
	
	public final void DiscardView(ID3D11View view) {
		DiscardView(pointerTo(view));
	}
	
	public final void DiscardResource(ID3D11Resource resource) {
		DiscardResource(pointerTo(resource));
	}
			
}
