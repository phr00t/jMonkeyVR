package com.fourthskyinteractive.dx4j.d3d9.core;

import static org.bridj.Pointer.allocatePointer;
import static org.bridj.Pointer.pointerTo;

import org.bridj.LastError;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DDEVTYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DFORMAT;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DMULTISAMPLE_TYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DRESOURCETYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9Exception;
import com.fourthskyinteractive.dx4j.windows.HMONITOR;
import com.fourthskyinteractive.dx4j.windows.HWND;

@IID("81BDCBCA-64D4-426d-AE8D-AD0147F4275C")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3D9 extends IUnknown {
	public IDirect3D9() {
		super();
	}

	@Virtual(0)
	public native final int RegisterSoftwareDevice(Pointer<?> pInitializeFunction);
    
	@Virtual(1)
	public native final int GetAdapterCount();
	
	@Virtual(2)
	public native final int GetAdapterIdentifier(int Adapter, int Flags, Pointer<D3DADAPTER_IDENTIFIER9> pIdentifier);
	
	@Virtual(3)
	public native final int GetAdapterModeCount(int Adapter, D3DFORMAT Format);
	
	@Virtual(4)
	public native final int EnumAdapterModes(int Adapter, D3DFORMAT Format, int Mode, Pointer<D3DDISPLAYMODE> pMode);
	
	@Virtual(5)
	public native final int GetAdapterDisplayMode(int Adapter, Pointer<D3DDISPLAYMODE> pMode);
	
	@Virtual(6)
	public native final int CheckDeviceType(int Adapter, D3DDEVTYPE DevType, D3DFORMAT DisplayFormat, D3DFORMAT BackBufferFormat, int bWindowed);
	
	@Virtual(7)
	public native final int CheckDeviceFormat(int Adapter, D3DDEVTYPE DevType, D3DFORMAT AdapterFormat, int Usage, D3DRESOURCETYPE RType, D3DFORMAT CheckFormat);
	
	@Virtual(8)
	public native  final int CheckDeviceMultiSampleType(int Adapter, D3DDEVTYPE DeviceType, D3DFORMAT SurfaceFormat, int Windowed, D3DMULTISAMPLE_TYPE MultiSampleType, Pointer<Integer> pQualityLevels);
	
	@Virtual(9)
	public native final int CheckDepthStencilMatch(int Adapter, D3DDEVTYPE DeviceType, D3DFORMAT AdapterFormat, D3DFORMAT RenderTargetFormat, D3DFORMAT DepthStencilFormat);
	
	@Virtual(10)
	public native final int CheckDeviceFormatConversion(int Adapter, D3DDEVTYPE DeviceType, D3DFORMAT SourceFormat, D3DFORMAT TargetFormat); 
	
	@Virtual(11)
	public native final int GetDeviceCaps(int Adapter, D3DDEVTYPE DeviceType, Pointer<D3DCAPS9> pCaps);
	
	@Virtual(12)
	public native final HMONITOR GetAdapterMonitor(int Adapter);
	
	@Deprecated @Virtual(13)
	public native final int CreateDevice(int Adapter, D3DDEVTYPE DeviceType, HWND hFocusWindow, int BehaviorFlags, Pointer<D3DPRESENT_PARAMETERS> pPresentationParameters, Pointer<Pointer<IDirect3DDevice9>> ppReturnedDeviceInterface) throws LastError;
	
	public final IDirect3DDevice9 CreateDevice(int Adapter, D3DDEVTYPE DeviceType, HWND hFocusWindow, int BehaviorFlags, D3DPRESENT_PARAMETERS pPresentationParameters) throws D3D9Exception {
		Pointer<Pointer<IDirect3DDevice9>> pp = allocatePointer(IDirect3DDevice9.class);
		
		try {
			int result = this.CreateDevice(Adapter, DeviceType, hFocusWindow, BehaviorFlags, pointerTo(pPresentationParameters), pp);
			if(result != 0) {
				throw new D3D9Exception(result);
			}
			
			return pp.get().getNativeObject(IDirect3DDevice9.class);
		} finally {
			pp.release();
			pp = null;
		}
	}
}
