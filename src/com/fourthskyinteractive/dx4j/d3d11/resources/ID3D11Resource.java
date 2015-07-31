package com.fourthskyinteractive.dx4j.d3d11.resources;

import static org.bridj.Pointer.allocateInt;

import com.fourthskyinteractive.dx4j.util.Describable;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_RESOURCE_DIMENSION;
import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;
import com.fourthskyinteractive.dx4j.dxgi.DXGIException;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGIResource;
import com.fourthskyinteractive.dx4j.windows.HANDLE;

@IID("dc8e63f3-d12b-4952-b47b-5e45026a862d")
@Library("d3d11")
@Runtime(COMRuntime.class)
public abstract class ID3D11Resource extends ID3D11DeviceChild implements Describable {

	public ID3D11Resource() {
		super();
	}
//	public ID3D11Resource(Pointer pointer) {
//		super(pointer);
//	}
	@Deprecated @Virtual(0)
	public native void GetType(Pointer<Integer> pType);
	@Virtual(1)
	public native void SetEvictionPriority(int priority);
	@Virtual(2)
	public native int GetEvictionPriority();
	
	public final D3D11_RESOURCE_DIMENSION GetType() {
		Pointer<Integer> pDim = allocateInt();
		GetType(pDim);
		
		return (D3D11_RESOURCE_DIMENSION) D3D11_RESOURCE_DIMENSION.fromValue(pDim.get().longValue());
	}
	
	/**
	 * Utility method to obtain shared handle
	 * @return the shared handle
	 * @throws DXGIException 
	 */
	public final HANDLE GetSharedHandle() throws DXGIException {
		IDXGIResource dxgiResource = this.QueryInterface(IDXGIResource.class);
		try {
			return dxgiResource.GetSharedHandle();
		} finally {
			dxgiResource.Release();
			dxgiResource = null;
		}
	}
}
