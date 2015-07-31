package com.fourthskyinteractive.dx4j.d3d9.core;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;
import org.bridj.cpp.com.RECT;

import static org.bridj.Pointer.*;

import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DFORMAT;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DMULTISAMPLE_TYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DPOOL;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DPRIMITIVETYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DQUERYTYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DRENDERSTATETYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DSAMPLERSTATETYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DSTATEBLOCKTYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DTEXTUREFILTERTYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DTEXTURESTAGESTATETYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DTRANSFORMSTATETYPE;
import com.fourthskyinteractive.dx4j.d3d9.D3D9Exception;
import com.fourthskyinteractive.dx4j.d3d9.D3DMATRIX;
import com.fourthskyinteractive.dx4j.d3d9.D3DRECT;
import com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DBACKBUFFER_TYPE;
import com.fourthskyinteractive.dx4j.d3d9.query.IDirect3DQuery9;
import com.fourthskyinteractive.dx4j.d3d9.resources.IDirect3DBaseTexture9;
import com.fourthskyinteractive.dx4j.d3d9.resources.IDirect3DCubeTexture9;
import com.fourthskyinteractive.dx4j.d3d9.resources.IDirect3DIndexBuffer9;
import com.fourthskyinteractive.dx4j.d3d9.resources.IDirect3DSurface9;
import com.fourthskyinteractive.dx4j.d3d9.resources.IDirect3DTexture9;
import com.fourthskyinteractive.dx4j.d3d9.resources.IDirect3DVertexBuffer9;
import com.fourthskyinteractive.dx4j.d3d9.resources.IDirect3DVolumeTexture9;
import com.fourthskyinteractive.dx4j.d3d9.shader.IDirect3DPixelShader9;
import com.fourthskyinteractive.dx4j.d3d9.shader.IDirect3DVertexShader9;
import com.fourthskyinteractive.dx4j.d3d9.states.D3DVERTEXELEMENT9;
import com.fourthskyinteractive.dx4j.d3d9.states.IDirect3DStateBlock9;
import com.fourthskyinteractive.dx4j.d3d9.states.IDirect3DVertexDeclaration9;
import com.fourthskyinteractive.dx4j.windows.HANDLE;
import com.fourthskyinteractive.dx4j.windows.HWND;
import com.fourthskyinteractive.dx4j.windows.POINT;
import com.fourthskyinteractive.dx4j.windows.gdi.PALETTEENTRY;

@IID("d0223b96-bf7a-43fd-92bd-a43b0d82b9eb")
@Library("d3d9")
@Runtime(COMRuntime.class)
public class IDirect3DDevice9 extends IUnknown {
	
	@Virtual(0)
	public native final int TestCooperativeLevel();
	
	@Virtual(1)
	public native final long GetAvailableTestureMem();
	
	@Virtual(2)
	public native final int EvictManagedResources();
	
	@Virtual(3)
	public native final int GetDirect3D(Pointer<Pointer<IDirect3D9>> ppD3D9);
	
	@Virtual(4)
	public native final int GetDeviceCaps(Pointer<D3DCAPS9> pCaps);
	
	@Virtual(5)
	public native final int GetDisplayMode(int iSwapChain, Pointer<D3DDISPLAYMODE> pMode);
	
	@Virtual(6)
	public native final int GetCreationParameters(Pointer<D3DDEVICE_CREATION_PARAMETERS> pParameters);
	
	@Virtual(7)
	public native final int SetCursorProperties(int XHotSpot, int YHotSpot, Pointer<IDirect3DSurface9> pCursorBitmap);
	
	@Virtual(8)
	public native final int SetCursorPosition(int X, int Y, int Flags);
	
	@Virtual(9)
	public native final int ShowCursor(int bShow);
	
	@Virtual(10)
	public native final int CreateAdditionalSwapChain(Pointer<D3DPRESENT_PARAMETERS> pPresentationParameters, Pointer<Pointer<IDirect3DSwapChain9>> ppSwapChain);
	
	@Virtual(11)
	public native final int GetSwapChain(int iSwapChain, Pointer<Pointer<IDirect3DSwapChain9>> ppSwapChain);
	
	@Virtual(12)
	public native final int GetNumberOfSwapChains();
	
	@Virtual(13)
	public native final int Reset(Pointer<D3DPRESENT_PARAMETERS> pPresentationParameters);
	
	@Virtual(14)
	public native final int Present(Pointer<RECT> pSourceRect,Pointer<RECT> pDestRect, HWND hDestWindowOverride, Pointer</*RGNDATA*/?> pDirtyRegion);
	
	@Virtual(15)
	public native final int GetBackBuffer(int iSwapChain, int iBackBuffer, D3DBACKBUFFER_TYPE Type, Pointer<Pointer<IDirect3DSurface9>> ppBackBuffer);

	@Virtual(16)
	public native final int GetRasterStatus(int iSwapChain, Pointer<D3DRASTER_STATUS> pRasterStatus);
	
	@Virtual(17)
	public native final int SetDialogBoxMode(int bEnableDialogs);
	
	@Virtual(18)
	public native final void SetGammaRamp(int iSwapChain,int Flags,Pointer<D3DGAMMARAMP> pRamp);
	
	@Virtual(19)
	public native final void GetGammaRamp(int iSwapChain, Pointer<D3DGAMMARAMP> pRamp);
	
	@Virtual(20)
	public native final int CreateTexture(int Width, int Height, int Levels, int Usage, D3DFORMAT Format, D3DPOOL Pool, Pointer<Pointer<IDirect3DTexture9>> ppTexture, Pointer<HANDLE> pSharedHandle);
	
	@Virtual(21)
	public native final int CreateVolumeTexture(int Width, int Height, int Depth, int Levels, int Usage, D3DFORMAT Format, D3DPOOL Pool, Pointer<Pointer<IDirect3DVolumeTexture9>> ppVolumeTexture, Pointer<HANDLE> pSharedHandle);

	@Virtual(22)
	public native final int CreateCubeTexture(int EdgeLength, int Levels, int Usage, D3DFORMAT Format, D3DPOOL Pool, Pointer<Pointer<IDirect3DCubeTexture9>> ppCubeTexture, Pointer<HANDLE> pSharedHandle);
	
	@Deprecated @Virtual(23)
	public native final int CreateVertexBuffer(int length, int Usage, int FVF, D3DPOOL Pool, Pointer<Pointer<IDirect3DVertexBuffer9>> ppVertexBuffer, Pointer<HANDLE> pSharedHandle);
	
	@Virtual(24)
	public native final int CreateIndexBuffer(int Length, int Usage, D3DFORMAT Format, D3DPOOL Pool, Pointer<Pointer<IDirect3DIndexBuffer9>> ppIndexBuffer, Pointer<HANDLE> pSharedHandle);
	
	@Virtual(25)
	public native final int CreateRenderTarget(int Width, int Height, D3DFORMAT Format, D3DMULTISAMPLE_TYPE MultiSample, int MultisampleQuality, int Lockable, Pointer<Pointer<IDirect3DSurface9>> ppSurface, Pointer<HANDLE> pSharedHandle);
	
	@Virtual(26)
	public native final int CreateDepthStencilSurface(int Width, int Height, D3DFORMAT Format, D3DMULTISAMPLE_TYPE MultiSample, int MultisampleQuality, int bDiscard, Pointer<Pointer<IDirect3DSurface9>> ppSurface, Pointer<HANDLE> pSharedHandle);
	
	@Virtual(27) 
	public native final int UpdateSurface(Pointer<IDirect3DSurface9> pSourceSurface, Pointer<RECT> pSourceRect, Pointer<IDirect3DSurface9> pDestinationSurface, Pointer<POINT> pDestPoint);
	
	@Virtual(28) 
	public native final int UpdateTexture(Pointer<IDirect3DBaseTexture9> pSourceTexture, Pointer<IDirect3DBaseTexture9> pDestinationTexture);
	
	@Virtual(29) 
	public native final int GetRenderTargetData(Pointer<IDirect3DSurface9> pRenderTarget, Pointer<IDirect3DSurface9> pDestSurface);
	
	@Virtual(30) 
	public native final int GetFrontBufferData(int iSwapChain, Pointer<IDirect3DSurface9> pDestSurface);
	
	@Virtual(31)
	public native final int StretchRect(Pointer<IDirect3DSurface9> pSourceSurface, Pointer<RECT> pSourceRect, Pointer<IDirect3DSurface9> pDestSurface, Pointer<RECT> pDestRect, D3DTEXTUREFILTERTYPE Filter);
	
	@Virtual(32)
	public native final int ColorFill(Pointer<IDirect3DSurface9> pSurface, Pointer<RECT> pRect, int color);
	
	@Virtual(33)
	public native final int CreateOffscreenPlainSurface(int Width, int Height, D3DFORMAT Format, D3DPOOL Pool, Pointer<Pointer<IDirect3DSurface9>> ppSurface, Pointer<HANDLE> pSharedHandle);
    
	@Virtual(34)
	public native final int SetRenderTarget(int RenderTargetIndex, Pointer<IDirect3DSurface9> pRenderTarget);
	    
    @Virtual(35) 
    public native final int GetRenderTarget(int RenderTargetIndex,Pointer<Pointer<IDirect3DSurface9>> ppRenderTarget);
    
    @Virtual(36) 
    public native final int SetDepthStencilSurface(Pointer<IDirect3DSurface9> pNewZStencil);
    
    @Virtual(37) 
    public native final int GetDepthStencilSurface(Pointer<Pointer<IDirect3DSurface9>> ppZStencilSurface);   
	
	@Virtual(38)
	public native final int BeginScene();
	
	@Virtual(39)
	public native final int EndScene();
	
	@Virtual(40)
	public native final int Clear(int Count, Pointer<D3DRECT> pRects, int Flags, /*D3DCOLOR*/ int Color, float Z, int Stencil);
	
	@Virtual(41)
	public native final int SetTransform(D3DTRANSFORMSTATETYPE State,Pointer<D3DMATRIX> pMatrix);
	
	@Virtual(42) 
	public native final int GetTransform(D3DTRANSFORMSTATETYPE State, Pointer<D3DMATRIX> pMatrix);
    
	@Virtual(43) 
	public native final int MultiplyTransform(D3DTRANSFORMSTATETYPE State, Pointer<D3DMATRIX> pMatrix);
    
	@Virtual(44) 
	public native final int SetViewport(Pointer<D3DVIEWPORT9> pViewport);
    
	@Virtual(45) 
	public native final int GetViewport(Pointer<D3DVIEWPORT9> pViewport);
    
	@Virtual(46) 
	public native final int SetMaterial(Pointer<D3DMATERIAL9> pMaterial);
    
	@Virtual(47) 
	public native final int GetMaterial(Pointer<D3DMATERIAL9> pMaterial);
    
	@Virtual(48) 
	public native final int SetLight(int Index, Pointer<D3DLIGHT9> pLight);
    
	@Virtual(49) 
	public native final int GetLight(int Index,Pointer<D3DLIGHT9> pLight);
    
	@Virtual(50) 
	public native final int LightEnable(int Index, int Enable);
    
	@Virtual(51) 
	public native final int GetLightEnable(int Index, Pointer<Integer> pEnable);
    
	@Virtual(52) 
	public native final int SetClipPlane(int Index, Pointer<Float> pPlane);
   
	@Virtual(53) 
    public native final int GetClipPlane(int Index, Pointer<Float> pPlane);
    
    @Virtual(54) 
    public native final int SetRenderState(D3DRENDERSTATETYPE State,int Value);
    
    @Virtual(55) 
    public native final int GetRenderState(D3DRENDERSTATETYPE State, Pointer<Integer> pValue);
    
    @Virtual(56) 
    public native final int CreateStateBlock(D3DSTATEBLOCKTYPE Type, Pointer<Pointer<IDirect3DStateBlock9>> ppSB);
    
    @Virtual(57) 
    public native final int BeginStateBlock();
    
    @Virtual(58) 
    public native final int EndStateBlock(Pointer<Pointer<IDirect3DStateBlock9>> ppSB);
    
    @Virtual(59) 
    public native final int SetClipStatus(Pointer<D3DCLIPSTATUS9> pClipStatus);
    
    @Virtual(60) 
    public native final int GetClipStatus(Pointer<D3DCLIPSTATUS9> pClipStatus);
    
    @Virtual(61) 
    public native final int GetTexture(int Stage, Pointer<Pointer<IDirect3DBaseTexture9>> ppTexture);
    
    @Virtual(62) 
    public native final int SetTexture(int Stage,Pointer<IDirect3DBaseTexture9> pTexture);
    
    @Virtual(63) 
    public native final int GetTextureStageState(int Stage, D3DTEXTURESTAGESTATETYPE Type, Pointer<Integer> pValue);
    
    @Virtual(64) 
    public native final int SetTextureStageState(int Stage, D3DTEXTURESTAGESTATETYPE Type,int Value);
    
    @Virtual(65) 
    public native final int GetSamplerState(int Sampler, D3DSAMPLERSTATETYPE Type, Pointer<Integer> pValue);
    
    @Virtual(66) 
    public native final int SetSamplerState(int Sampler, D3DSAMPLERSTATETYPE Type,int Value);
    
    @Virtual(67) 
    public native final int ValidateDevice(Pointer<Integer> pNumPasses);
    
    @Virtual(68) 
    public native final int SetPaletteEntries(int PaletteNumber, Pointer<PALETTEENTRY> pEntries);
    
    @Virtual(69) 
    public native final int GetPaletteEntries(int PaletteNumber, Pointer<PALETTEENTRY> pEntries);
    
    @Virtual(70) 
    public native final int SetCurrentTexturePalette(int PaletteNumber);
    
    @Virtual(71) 
    public native final int GetCurrentTexturePalette(Pointer<Integer> PaletteNumber);
    
    @Virtual(72) 
    public native final int SetScissorRect(Pointer<RECT> pRect);
    
    @Virtual(73) 
    public native final int GetScissorRect(Pointer<RECT> pRect);
    
    @Virtual(74) 
    public native final int SetSoftwareVertexProcessing(int bSoftware);
    
    @Virtual(75) 
    public native final int GetSoftwareVertexProcessing();
    
    @Virtual(76) 
    public native final int SetNPatchMode(float nSegments);
	
    @Virtual(77) 
	public native final float GetNPatchMode();
	
	@Virtual(78)
	public native final int DrawPrimitive(D3DPRIMITIVETYPE PrimitiveType, int StartVertex, int PrimitiveCount);
	
	@Virtual(79) 
	public native final int DrawIndexedPrimitive(D3DPRIMITIVETYPE primitiveType, int BaseVertexIndex, int MinVertexIndex, int NumVertices, int startIndex, int primCount);
    
	@Virtual(80) 
	public native final int DrawPrimitiveUP(D3DPRIMITIVETYPE PrimitiveType,int PrimitiveCount, Pointer<?> pVertexStreamZeroData,int VertexStreamZeroStride);
    
	@Virtual(81) 
	public native final int DrawIndexedPrimitiveUP(D3DPRIMITIVETYPE PrimitiveType,int MinVertexIndex,int NumVertices,int PrimitiveCount, Pointer<?> pIndexData,D3DFORMAT IndexDataFormat, Pointer<?> pVertexStreamZeroData,int VertexStreamZeroStride);
    
	@Virtual(82) 
	public native final int ProcessVertices(int SrcStartIndex,int DestIndex,int VertexCount, Pointer<IDirect3DVertexBuffer9> pDestBuffer, Pointer<IDirect3DVertexDeclaration9> pVertexDecl,int Flags);
    
	@Virtual(83) 
	public native final int CreateVertexDeclaration(Pointer<D3DVERTEXELEMENT9> pVertexElements, Pointer<Pointer<IDirect3DVertexDeclaration9>> ppDecl);
    
	@Virtual(84) 
	public native final int SetVertexDeclaration(Pointer<IDirect3DVertexDeclaration9> pDecl);
    
	@Virtual(85) 
	public native final int GetVertexDeclaration(Pointer<Pointer<IDirect3DVertexDeclaration9>> ppDecl);

	@Virtual(86)
	public native final int SetFVF(int FVF);
	
	@Virtual(87) 
	public native final int GetFVF(Pointer<Integer> pFVF);
    
	@Virtual(88) 
	public native final int CreateVertexShader(Pointer<Integer> pFunction, Pointer<Pointer<IDirect3DVertexShader9>> ppShader);
    
	@Virtual(89) 
	public native final int SetVertexShader(Pointer<IDirect3DVertexShader9> pShader);
    
	@Virtual(90) 
	public native final int GetVertexShader(Pointer<Pointer<IDirect3DVertexShader9>> ppShader);
    
	@Virtual(91) 
	public native final int SetVertexShaderConstantF(int StartRegister,Pointer<Float> pConstantData,int Vector4fCount);
    
	@Virtual(92) 
	public native final int GetVertexShaderConstantF(int StartRegister, Pointer<Float> pConstantData,int Vector4fCount);
    
	@Virtual(93) 
	public native final int SetVertexShaderConstantI(int StartRegister,Pointer<Integer> pConstantData,int Vector4iCount);
    
	@Virtual(94) 
	public native final int GetVertexShaderConstantI(int StartRegister, Pointer<Integer> pConstantData,int Vector4iCount);
    
	@Virtual(95) 
	public native final int SetVertexShaderConstantB(int StartRegister, Pointer<Integer> pConstantData,int  BoolCount);
    
	@Virtual(96) 
	public native final int GetVertexShaderConstantB(int StartRegister, Pointer<Integer> pConstantData,int BoolCount);

	@Virtual(97)
	public native final int SetStreamSource(int StreamNumber, Pointer<IDirect3DVertexBuffer9> pStreamData, int OffsetInBytes, int Stride);
	
	@Virtual(98) 
	public native final int GetStreamSource(int StreamNumber, Pointer<Pointer<IDirect3DVertexBuffer9>> ppStreamData, Pointer<Integer> OffsetInBytes, Pointer<Integer> pStride);
    
	@Virtual(99) 
	public native final int SetStreamSourceFreq(int StreamNumber,int Divider);
    
	@Virtual(100) 
	public native final int GetStreamSourceFreq(int StreamNumber, Pointer<Integer> Divider);
    
	@Virtual(101) 
	public native final int SetIndices(Pointer<IDirect3DIndexBuffer9> pIndexData);
    
	@Virtual(102) 
	public native final int GetIndices(Pointer<Pointer<IDirect3DIndexBuffer9>> ppIndexData);
    
	@Virtual(103) 
	public native final int CreatePixelShader(Pointer<Integer> pFunction, Pointer<Pointer<IDirect3DPixelShader9>> ppShader);
    
	@Virtual(104) 
	public native final int SetPixelShader(Pointer<IDirect3DPixelShader9> pShader);
    
	@Virtual(105) 
	public native final int GetPixelShader(Pointer<Pointer<IDirect3DPixelShader9>> ppShader);
    
	@Virtual(106) 
	public native final int SetPixelShaderConstantF(int StartRegister, Pointer<Float> pConstantData,int Vector4fCount);
    
	@Virtual(107) 
	public native final int GetPixelShaderConstantF(int StartRegister, Pointer<Float> pConstantData,int Vector4fCount);
    
	@Virtual(108) 
	public native final int SetPixelShaderConstantI(int StartRegister, Pointer<Integer> pConstantData,int Vector4iCount);
    
	@Virtual(109) 
	public native final int GetPixelShaderConstantI(int StartRegister, Pointer<Integer> pConstantData,int Vector4iCount);
    
	@Virtual(110) 
	public native final int SetPixelShaderConstantB(int StartRegister, Pointer<Integer> pConstantData,int  BoolCount);
    
	@Virtual(111) 
	public native final int GetPixelShaderConstantB(int StartRegister, Pointer<Integer> pConstantData,int BoolCount);
    
	@Virtual(112) 
	public native final int DrawRectPatch(int Handle, Pointer<Float> pNumSegs, Pointer<D3DTRIPATCH_INFO> pRectPatchInfo);
    
	@Virtual(113) 
	public native final int DrawTriPatch(int Handle, Pointer<Float> pNumSegs, Pointer<D3DTRIPATCH_INFO> pTriPatchInfo);
    
	@Virtual(114) 
	public native final int DeletePatch(int Handle);
    
	@Virtual(115) 
    public native final int CreateQuery(D3DQUERYTYPE Type, Pointer<Pointer<IDirect3DQuery9>> ppQuery);
    
	public final IDirect3DVertexBuffer9 CreateVertexBuffer(int length, int Usage, int FVF, D3DPOOL Pool) throws D3D9Exception {
		Pointer<Pointer<IDirect3DVertexBuffer9>> pp = allocatePointer(IDirect3DVertexBuffer9.class);
		
		try {
			int result = this.CreateVertexBuffer(length, Usage, FVF, Pool, pp, null);
			if (result != 0) {
				throw new D3D9Exception(result);
			}
			
			return pp.get().getNativeObject(IDirect3DVertexBuffer9.class);
		} finally {
			pp.release();
			pp = null;
		}
	}
}
