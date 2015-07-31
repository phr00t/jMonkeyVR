package com.fourthskyinteractive.dx4j.dxgi.device;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_MODE_ROTATION;
import com.fourthskyinteractive.dx4j.dxgi.DXGI_RGBA;
import com.fourthskyinteractive.dx4j.dxgi.adapter.IDXGIOutput;
import com.fourthskyinteractive.dx4j.windows.HWND;

import static org.bridj.Pointer.allocate;

@IID("790a45f7-0d42-4876-983a-0a55cfe6f4aa")
@Library("DXGI")
@Runtime(COMRuntime.class)
public class IDXGISwapChain1 extends IDXGISwapChain {

	public IDXGISwapChain1() {
	}

	@Virtual(0)
	public final native int Present1(int SyncInterval, int Flags, Pointer<DXGI_PRESENT_PARAMETERS> pParams);
	
	@Virtual(1)
	public final native int GetBackgroundColor(Pointer<DXGI_RGBA> pColor); 
	
	@Virtual(2)
	public final native int SetBackgroundColor(Pointer<DXGI_RGBA> pColor); 
	
	@Virtual(3)
	public final native int GetDesc1(Pointer<DXGI_SWAP_CHAIN_DESC1> pDesc); 
	
	@Virtual(4)
	public final native int GetFullscreenDesc(Pointer<DXGI_SWAP_CHAIN_FULLSCREEN_DESC> pDesc); 
	
	@Virtual(5)
	public final native int GetHwnd(Pointer<HWND> pHwnd);
	
	@Virtual(6)
	public final native int GetRestrictToOutput(Pointer<Pointer<IDXGIOutput>> ppRestrictToOutput);
	
	@Virtual(7)
	public final native int GetRotation(Pointer<DXGI_MODE_ROTATION> pRotation);
	
	@Virtual(8)
	public final native int SetRotation(DXGI_MODE_ROTATION pRotation);
	
	@Virtual(9)
	public final native int IsTemporaryMonoSupported();
	
	//@Virtual(10)
	//public final native int GetCoreWindow(Pointer<GUID> refiid, Pointer<Pointer<?>> ppUnk);

    public DXGI_SWAP_CHAIN_DESC1 GetDesc1() {
        Pointer<DXGI_SWAP_CHAIN_DESC1> pDesc = null;

        try {
            pDesc = allocate(DXGI_SWAP_CHAIN_DESC1.class);
            this.GetDesc1(pDesc);
            return pDesc.get();
        } finally {
            if (pDesc != null)
                pDesc.release();
        }
    }
}
