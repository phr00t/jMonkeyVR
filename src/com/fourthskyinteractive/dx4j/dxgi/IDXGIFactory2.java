package com.fourthskyinteractive.dx4j.dxgi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.dxgi.adapter.IDXGIOutput;
import com.fourthskyinteractive.dx4j.dxgi.device.DXGI_SWAP_CHAIN_DESC1;
import com.fourthskyinteractive.dx4j.dxgi.device.DXGI_SWAP_CHAIN_FULLSCREEN_DESC;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGISwapChain1;
import com.fourthskyinteractive.dx4j.windows.HANDLE;
import com.fourthskyinteractive.dx4j.windows.HWND;
import com.fourthskyinteractive.dx4j.windows.LUID;

@IID("")
@Library("DXGI")
@Runtime(COMRuntime.class)
public class IDXGIFactory2 extends IDXGIFactory1 {

	public IDXGIFactory2() {
	}
	
	@Virtual(0)
	public final native int CreateSwapChainForHwnd(Pointer<IUnknown> pDevice, HWND hWnd, Pointer<DXGI_SWAP_CHAIN_DESC1> pDesc, Pointer<DXGI_SWAP_CHAIN_FULLSCREEN_DESC> pFullscreenDesc, Pointer<IDXGIOutput> pRestrictToOutput, Pointer<Pointer<IDXGISwapChain1>> ppSwapChain);
	
	@Virtual(1)
	public final native int CreateSwapChainForCoreWindow(Pointer<IUnknown> pDevice, Pointer<IUnknown> pWindow, Pointer<DXGI_SWAP_CHAIN_DESC1> pDesc, Pointer<IDXGIOutput> pRestrictToOutput, Pointer<Pointer<IDXGISwapChain1>> ppSwapChain);
	
	@Virtual(2)
	public final native int IsWindowedStereoEnabled();
	
	@Virtual(3)
	public final native int GetSharedRersourceAdapterLuid(HANDLE hResource, Pointer<LUID> pLuid);
	
	@Virtual(4)
	public final native int RegisterOcclusionStatusEvent(HANDLE hEvent, Pointer<Integer> pdwCookie);
	
	@Virtual(5)
	public final native int RegisterOcclusionStatusWindow(HWND WindowHandle, int wMsg, Pointer<Integer> pdwCookie);
	
	@Virtual(6)
	public final native int UnregisterOcclusionStatus(int dwCookie);
	
	@Virtual(7)
	public final native int RegisterStereoStatusEvent(HANDLE hEvent, Pointer<Integer> pdwCookie);
	
	@Virtual(8)
	public final native int RegisterStereoStatusWindow(HWND WindowHandle, int wMsg, Pointer<Integer> pdwCookie);
	
	@Virtual(9)
	public final native int UnregisterStereoStatus(int dwCookie);
}
