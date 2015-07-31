package com.fourthskyinteractive.dx4j.d3dx11;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11Device;
import com.fourthskyinteractive.dx4j.d3d11.resources.ID3D11Resource;
import com.fourthskyinteractive.dx4j.d3d11.resources.views.ID3D11ShaderResourceView;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_FORMAT;

@Library("d3dx11_43")
@Runtime(COMRuntime.class)
public class D3DX11 {
	
	static {
		BridJ.register();
	}
	
	public static final int D3DX11_DEFAULT            = -1;
	public static final int D3DX11_FROM_FILE          = -3;
	public static final ValuedEnum<DXGI_FORMAT> DXGI_FORMAT_FROM_FILE = DXGI_FORMAT.fromValue(-3);

	public enum D3DX11_FILTER_FLAG implements IntValuedEnum<D3DX11_FILTER_FLAG> {
		D3DX11_FILTER_NONE            (1 << 0),
		D3DX11_FILTER_POINT           (2 << 0),
		D3DX11_FILTER_LINEAR          (3 << 0),
		D3DX11_FILTER_TRIANGLE        (4 << 0),
		D3DX11_FILTER_BOX             (5 << 0),
		D3DX11_FILTER_MIRROR_U        (1 << 16),
		D3DX11_FILTER_MIRROR_V        (2 << 16),
		D3DX11_FILTER_MIRROR_W        (4 << 16),
		D3DX11_FILTER_MIRROR          (7 << 16),
		D3DX11_FILTER_DITHER          (1 << 19),
		D3DX11_FILTER_DITHER_DIFFUSION(2 << 19),
		D3DX11_FILTER_SRGB_IN         (1 << 21),
		D3DX11_FILTER_SRGB_OUT        (2 << 21),
		D3DX11_FILTER_SRGB            (3 << 21);

		public final long value;
		D3DX11_FILTER_FLAG(long value) {
			this.value = value;
		}
		public Iterator<D3DX11_FILTER_FLAG > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DX11_FILTER_FLAG > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
		@Override
		public long value() {
			return value;
		}
	}
	
	public enum D3DX11_NORMALMAP_FLAG implements IntValuedEnum<D3DX11_NORMALMAP_FLAG> {
	    D3DX11_NORMALMAP_MIRROR_U(1 << 16),
	    D3DX11_NORMALMAP_MIRROR_V(2 << 16),
	    D3DX11_NORMALMAP_MIRROR(3 << 16),
	    D3DX11_NORMALMAP_INVERTSIGN(8 << 16),
	    D3DX11_NORMALMAP_COMPUTE_OCCLUSION(16 << 16);
	    
	    public final long value;
	    D3DX11_NORMALMAP_FLAG(long value) {
	    	this.value = value;
	    }

		@Override
		public long value() {
			return value;
		}
	
		public Iterator<D3DX11_NORMALMAP_FLAG> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DX11_NORMALMAP_FLAG> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum D3DX11_CHANNEL_FLAG implements IntValuedEnum<D3DX11_CHANNEL_FLAG> {
	    D3DX11_CHANNEL_RED(1 << 0),
	    D3DX11_CHANNEL_BLUE(1 << 1),
	    D3DX11_CHANNEL_GREEN(1 << 2),
	    D3DX11_CHANNEL_ALPHA(1 << 3),
	    D3DX11_CHANNEL_LUMINANCE(1 << 4);
	    
	    public final long value;
	    D3DX11_CHANNEL_FLAG(long value) {
	    	this.value = value;
	    }

		@Override
		public long value() {
			return value;
		}
	
		public Iterator<D3DX11_CHANNEL_FLAG> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DX11_CHANNEL_FLAG> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum D3DX11_IMAGE_FILE_FORMAT implements IntValuedEnum<D3DX11_IMAGE_FILE_FORMAT>	{
	    D3DX11_IFF_BMP(0),
	    D3DX11_IFF_JPG(1),
	    D3DX11_IFF_PNG(3),
	    D3DX11_IFF_DDS(4),
	    D3DX11_IFF_TIFF(10),
	    D3DX11_IFF_GIF(11),
	    D3DX11_IFF_WMP(12),
	    D3DX11_IFF_FORCE_DWORD(0x7fffffff);
	    
	    public final long value;
	    D3DX11_IMAGE_FILE_FORMAT(long value) {
	    	this.value = value;
	    }

		@Override
		public long value() {
			return value;
		}
	
		public Iterator<D3DX11_IMAGE_FILE_FORMAT> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DX11_IMAGE_FILE_FORMAT> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum D3DX11_SAVE_TEXTURE_FLAG implements IntValuedEnum<D3DX11_SAVE_TEXTURE_FLAG> {
	    D3DX11_STF_USEINPUTBLOB(0x0001);
	    		
	    public final long value;
	    D3DX11_SAVE_TEXTURE_FLAG(long value) {
	    	this.value = value;
	    }

		@Override
		public long value() {
			return value;
		}
	
		public Iterator<D3DX11_SAVE_TEXTURE_FLAG> iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DX11_SAVE_TEXTURE_FLAG> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	
	public static native int D3DX11CreateThreadPump(int cIoThreads, int cProcThreads, Pointer<Pointer<ID3DX11ThreadPump>> ppThreadPump);
	
	public static native int D3DX11CreateShaderResourceViewFromFileA(Pointer<ID3D11Device> pDevice, Pointer<Byte> pSrcFile, Pointer<D3DX11_IMAGE_LOAD_INFO> pLoadInfo, Pointer<ID3DX11ThreadPump> pPump, Pointer<Pointer<ID3D11ShaderResourceView>> ppSRV, Pointer<Integer> pHResult);
	
	public static native int D3DX11CreateTextureFromFileA(Pointer<ID3D11Device> pDevice, Pointer<Byte> pSrcFile, Pointer<D3DX11_IMAGE_LOAD_INFO> pLoadInfo, Pointer<ID3DX11ThreadPump> pPump, Pointer<Pointer<ID3D11Resource>> ppTexture, Pointer<Integer> pHResult);
}
