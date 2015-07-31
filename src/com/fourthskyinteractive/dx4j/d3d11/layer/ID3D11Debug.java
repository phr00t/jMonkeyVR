package com.fourthskyinteractive.dx4j.d3d11.layer;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_RLDO_FLAGS;
import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceContext;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGISwapChain;

@IID("79cf2233-7536-4948-9d36-1e4692dc5760")
@Library("D3D11")
@Runtime(COMRuntime.class)
public class ID3D11Debug extends IUnknown {

	public ID3D11Debug() {
		// TODO Auto-generated constructor stub
	}

	
	@Virtual(0) 
	public final native int SetFeatureMask(int Mask);
        
	@Virtual(1) 
	public final native int GetFeatureMask();
    
    @Virtual(2)
    public final native int SetPresentPerRenderOpDelay(int Milliseconds);
    
    @Virtual(3) 
	public final native int GetPresentPerRenderOpDelay();
    
    @Virtual(4)
    public final native int SetSwapChain(Pointer<IDXGISwapChain> pSwapChain);
    
    @Virtual(5)
    public final native int GetSwapChain(Pointer<Pointer<IDXGISwapChain>> ppSwapChain);
    
    @Virtual(6)
    public final native int ValidateContext(Pointer<ID3D11DeviceContext> pContext);
    
    @Virtual(7)
    public final native int ReportLiveDeviceObjects(D3D11_RLDO_FLAGS Flags);
    
    @Virtual(8)
    public final native int ValidateContextForDispatch(Pointer<ID3D11DeviceContext> pContext);
}
