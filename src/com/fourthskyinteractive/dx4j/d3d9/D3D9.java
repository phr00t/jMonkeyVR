package com.fourthskyinteractive.dx4j.d3d9;

import java.util.Collections;
import java.util.Iterator;

import com.fourthskyinteractive.dx4j.d3d9.core.D3DPRESENT_PARAMETERS;
import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Convention;
import org.bridj.ann.Convention.Style;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;

import com.fourthskyinteractive.dx4j.d3d9.core.IDirect3D9;

import static com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DFORMAT.D3DFMT_UNKNOWN;
import static com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DMULTISAMPLE_TYPE.D3DMULTISAMPLE_NONE;
import static com.fourthskyinteractive.dx4j.d3d9.D3D9.D3DSWAPEFFECT.D3DSWAPEFFECT_DISCARD;

@Library("d3d9")
@Runtime(COMRuntime.class)
public class D3D9 {
	static {
		BridJ.register();
	}
	
	public static final int D3D_SDK_VERSION = 31;
	
	/****************************************************************************
	 *
	 * Flags for IDirect3D9::CreateDevice's BehaviorFlags
	 *
	 ****************************************************************************/

	public static final int D3DCREATE_FPU_PRESERVE = 0x00000002;
	public static final int D3DCREATE_MULTITHREADED = 0x00000004;

	public static final int D3DCREATE_PUREDEVICE = 0x00000010;
	public static final int D3DCREATE_SOFTWARE_VERTEXPROCESSING = 0x00000020;
	public static final int D3DCREATE_HARDWARE_VERTEXPROCESSING = 0x00000040;
	public static final int D3DCREATE_MIXED_VERTEXPROCESSING = 0x00000080;

	public static final int D3DCREATE_DISABLE_DRIVER_MANAGEMENT = 0x00000100;
	public static final int D3DCREATE_ADAPTERGROUP_DEVICE = 0x00000200;
	
	/****************************************************************************
	 *
	 * Parameter for IDirect3D9::CreateDevice's iAdapter
	 *
	 ****************************************************************************/

	public static final int D3DADAPTER_DEFAULT = 0;

	/****************************************************************************
	 *
	 * Flags for IDirect3D9::EnumAdapters
	 *
	 ****************************************************************************/

	public static final int D3DENUM_WHQL_LEVEL = 0x00000002;

	/****************************************************************************
	 *
	 * Flags for IDirect3DDevice9::SetGammaRamp
	 *
	 ****************************************************************************/

	public static final int D3DSGR_NO_CALIBRATION = 0x00000000;
	public static final int D3DSGR_CALIBRATE = 0x00000001;

	/****************************************************************************
	 *
	 * Flags for IDirect3DDevice9::SetCursorPosition
	 *
	 ****************************************************************************/

	public static final int D3DCURSOR_IMMEDIATE_UPDATE = 0x00000001;

	/****************************************************************************
	 *
	 * Flags for IDirect3DSwapChain9::Present
	 *
	 ****************************************************************************/

	public static final int D3DPRESENT_DONOTWAIT = 0x00000001;
	public static final int D3DPRESENT_LINEAR_CONTENT = 0x00000002;
	
	
	public static final int D3DPMISCCAPS_CULLCW = 32;
	public static final int D3DTEXOPCAPS_DOTPRODUCT3 = 8388608;
	public static final int D3DPTFILTERCAPS_MINFANISOTROPIC = 1024;
	public static final int D3DTEXOPCAPS_BLENDDIFFUSEALPHA = 2048;
	public static final int D3DVTXPCAPS_POSITIONALLIGHTS = 16;
	public static final int D3DLINECAPS_ALPHACMP = 8;
	public static final int D3DDEVCAPS2_PRESAMPLEDDMAPNPATCH = 32;
	public static final int D3DPMISCCAPS_MRTPOSTPIXELSHADERBLENDING = 524288;
	public static final int D3DSTENCILCAPS_TWOSIDED = 256;
	public static final int D3DPTEXTURECAPS_PROJECTED = 1024;
	public static final int D3DSTENCILCAPS_INVERT = 32;
	public static final int D3DDTCAPS_FLOAT16_4 = 512;
	public static final int D3DDTCAPS_FLOAT16_2 = 256;
	public static final int D3DPBLENDCAPS_BOTHSRCALPHA = 2048;
	public static final int D3DSTENCILCAPS_INCR = 64;
	public static final int D3DPMISCCAPS_CULLCCW = 64;
	public static final int D3DPRASTERCAPS_ZFOG = 2097152;
	public static final int D3DPRASTERCAPS_ZTEST = 16;
	public static final int D3DPTEXTURECAPS_TEXREPEATNOTSCALEDBYSIZE = 64;
	public static final int D3DTEXOPCAPS_MODULATE = 8;
	public static final int D3DPRASTERCAPS_FOGTABLE = 256;
	public static final int D3DPTEXTURECAPS_CUBEMAP = 2048;
	public static final int D3DPTFILTERCAPS_MINFLINEAR = 512;
	public static final int D3DPTFILTERCAPS_MAGFGAUSSIANQUAD = 268435456;
	public static final int D3DPTADDRESSCAPS_INDEPENDENTUV = 16;
	public static final int D3DCAPS3_ALPHA_FULLSCREEN_FLIP_OR_DISCARD = 32;
	public static final int D3DPMISCCAPS_LINEPATTERNREP = 4;
	public static final int D3DDTCAPS_UDEC3 = 64;
	public static final int D3DPS20_MIN_DYNAMICFLOWCONTROLDEPTH = 0;
	public static final int D3DTEXOPCAPS_PREMODULATE = 65536;
	public static final int D3DPRESENT_INTERVAL_ONE = 1;
	public static final int D3DTEXOPCAPS_BLENDTEXTUREALPHAPM = 16384;
	public static final int D3DPBLENDCAPS_INVDESTALPHA = 128;
	public static final int D3DVTXPCAPS_TEXGEN = 1;
	public static final int D3DCAPS2_RESERVED = 33554432;
	public static final int D3DCURSORCAPS_COLOR = 1;
	public static final int D3DCAPS2_FULLSCREENGAMMA = 131072;
	public static final int D3DPCMPCAPS_EQUAL = 4;
	public static final int D3DPTEXTURECAPS_NONPOW2CONDITIONAL = 256;
	public static final int D3DPTFILTERCAPS_MINFGAUSSIANQUAD = 4096;
	public static final int D3DDTCAPS_UBYTE4 = 1;
	public static final int D3DVS20_MAX_NUMTEMPS = 32;
	public static final int D3DLINECAPS_BLEND = 4;
	public static final int D3DPTEXTURECAPS_ALPHA = 4;
	public static final int D3DPMISCCAPS_FOGANDSPECULARALPHA = 65536;
	public static final int D3DFVFCAPS_DONOTSTRIPELEMENTS = 524288;
	public static final int D3DPBLENDCAPS_SRCALPHASAT = 1024;
	public static final int D3DDEVCAPS2_STREAMOFFSET = 1;
	public static final int D3DVS20_MAX_DYNAMICFLOWCONTROLDEPTH = 24;
	public static final int D3DPRASTERCAPS_FOGVERTEX = 128;
	public static final int D3DDTCAPS_SHORT4N = 8;
	public static final int D3DDEVCAPS2_ADAPTIVETESSNPATCH = 8;
	public static final int D3DPBLENDCAPS_DESTCOLOR = 256;
	public static final int D3DPRASTERCAPS_PAT = 8;
	public static final int D3DVTXPCAPS_NO_TEXGEN_NONLOCALVIEWER = 512;
	public static final int D3DPTEXTURECAPS_CUBEMAP_POW2 = 131072;
	public static final int D3DDEVCAPS_DRAWPRIMITIVES2 = 8192;
	public static final int D3DPBLENDCAPS_INVSRCCOLOR = 8;
	public static final int D3DPS20_MIN_STATICFLOWCONTROLDEPTH = 0;
	public static final int D3DDEVCAPS_NPATCHES = 16777216;
	public static final int D3DPRESENT_INTERVAL_FOUR = 8;
	public static final int D3DDEVCAPS_RTPATCHES = 4194304;
	public static final int D3DPTADDRESSCAPS_WRAP = 1;
	public static final int D3DFVFCAPS_TEXCOORDCOUNTMASK = 65535;
	public static final int D3DPS20CAPS_PREDICATION = (1 << 2);
	public static final int D3DCAPS2_DYNAMICTEXTURES = 536870912;
	public static final int D3DPRESENT_INTERVAL_TWO = 2;
	public static final int D3DDEVCAPS_CANRENDERAFTERFLIP = 2048;
	public static final int D3DTEXOPCAPS_SELECTARG1 = 2;
	public static final int D3DPTFILTERCAPS_MAGFANISOTROPIC = 67108864;
	public static final int D3DTEXOPCAPS_SELECTARG2 = 4;
	public static final int D3DPS20CAPS_NOTEXINSTRUCTIONLIMIT = (1 << 4);
	public static final int D3DPBLENDCAPS_SRCALPHA = 16;
	public static final int D3DPTADDRESSCAPS_MIRRORONCE = 32;
	public static final int D3DPTEXTURECAPS_VOLUMEMAP = 8192;
	public static final int D3DPTEXTURECAPS_NOPROJECTEDBUMPENV = 2097152;
	public static final int D3DDEVCAPS2_VERTEXELEMENTSCANSHARESTREAMOFFSET = 64;
	public static final int D3DCAPS2_CANCALIBRATEGAMMA = 1048576;
	public static final int D3DDEVCAPS_TEXTURESYSTEMMEMORY = 256;
	public static final int D3DDEVCAPS2_DMAPNPATCH = 2;
	public static final int D3DDEVCAPS_TLVERTEXSYSTEMMEMORY = 64;
	public static final int D3DDEVCAPS_QUINTICRTPATCHES = 2097152;
	public static final int D3DTEXOPCAPS_BUMPENVMAPLUMINANCE = 4194304;
	public static final int D3DDEVCAPS2_CAN_STRETCHRECT_FROM_TEXTURES = 16;
	public static final int D3DPCMPCAPS_ALWAYS = 128;
	public static final int D3DPRESENT_INTERVAL_THREE = 4;
	public static final int D3DPTEXTURECAPS_POW2 = 2;
	public static final int D3DPTADDRESSCAPS_CLAMP = 4;
	public static final int D3DVTXPCAPS_TWEENING = 64;
	public static final int D3DPRASTERCAPS_ANISOTROPY = 131072;
	public static final int D3DDEVCAPS_SEPARATETEXTUREMEMORIES = 16384;
	public static final int D3DLINECAPS_ANTIALIAS = 32;
	public static final int D3DDEVCAPS_TEXTURENONLOCALVIDMEM = 4096;
	public static final int D3DPRASTERCAPS_WFOG = 1048576;
	public static final int D3DCAPS3_LINEAR_TO_SRGB_PRESENTATION = 128;
	public static final int D3DSTENCILCAPS_KEEP = 1;
	public static final int D3DTEXOPCAPS_BLENDCURRENTALPHA = 32768;
	public static final int D3DPBLENDCAPS_DESTALPHA = 64;
	public static final int D3DPTADDRESSCAPS_BORDER = 8;
	public static final int D3DPMISCCAPS_CLIPPLANESCALEDPOINTS = 256;
	public static final int D3DPBLENDCAPS_ONE = 2;
	public static final int D3DPTFILTERCAPS_MAGFLINEAR = 33554432;
	public static final int D3DDEVCAPS_EXECUTEVIDEOMEMORY = 32;
	public static final int D3DLINECAPS_FOG = 16;
	public static final int D3DPTFILTERCAPS_MINFPOINT = 256;
	public static final int D3DVTXPCAPS_DIRECTIONALLIGHTS = 8;
	public static final int D3DLINECAPS_TEXTURE = 1;
	public static final int D3DPMISCCAPS_NULLREFERENCE = 4096;
	public static final int D3DPRASTERCAPS_COLORPERSPECTIVE = 4194304;
	public static final int D3DTEXOPCAPS_LERP = 33554432;
	public static final int D3DSTENCILCAPS_INCRSAT = 8;
	public static final int D3DTEXOPCAPS_MODULATEINVALPHA_ADDCOLOR = 524288;
	public static final int D3DDEVCAPS_HWRASTERIZATION = 524288;
	public static final int D3DVS20_MIN_STATICFLOWCONTROLDEPTH = 1;
	public static final int D3DPMISCCAPS_CULLNONE = 16;
	public static final int D3DVTXPCAPS_TEXGEN_SPHEREMAP = 256;
	public static final int D3DPCMPCAPS_LESS = 2;
	public static final int D3DPS20CAPS_NODEPENDENTREADLIMIT = (1 << 3);
	public static final int D3DPS20CAPS_ARBITRARYSWIZZLE = (1 << 0);
	public static final int D3DPTFILTERCAPS_MAGFPOINT = 16777216;
	public static final int D3DCAPS3_COPY_TO_VIDMEM = 256;
	public static final int D3DDEVCAPS_CANBLTSYSTONONLOCAL = 131072;
	public static final int D3DPTFILTERCAPS_MINFPYRAMIDALQUAD = 2048;
	public static final int D3DTEXOPCAPS_MULTIPLYADD = 16777216;
	public static final int D3DPTEXTURECAPS_VOLUMEMAP_POW2 = 262144;
	public static final int D3DPBLENDCAPS_INVSRCALPHA = 32;
	public static final int D3DPCMPCAPS_NOTEQUAL = 32;
	public static final int D3DPRASTERCAPS_ANTIALIASEDGES = 4096;
	public static final int D3DPRASTERCAPS_FOGRANGE = 65536;
	public static final int D3DPTADDRESSCAPS_MIRROR = 2;
	public static final int D3DPMISCCAPS_TSSARGTEMP = 1024;
	public static final int D3DPTFILTERCAPS_MIPFPOINT = 65536;
	public static final int D3DPCMPCAPS_LESSEQUAL = 8;
	public static final int D3DPRASTERCAPS_WBUFFER = 262144;
	public static final int D3DDEVCAPS_TLVERTEXVIDEOMEMORY = 128;
	public static final int D3DDEVCAPS_TEXTUREVIDEOMEMORY = 512;
	public static final int D3DVTXPCAPS_MATERIALSOURCE7 = 2;
	public static final int D3DTEXOPCAPS_BUMPENVMAP = 2097152;
	public static final int D3DDTCAPS_DEC3N = 128;
	public static final int D3DTEXOPCAPS_ADDSIGNED2X = 256;
	public static final int D3DPRASTERCAPS_MULTISAMPLE_TOGGLE = 134217728;
	public static final int D3DPMISCCAPS_MRTINDEPENDENTBITDEPTHS = 262144;
	public static final int D3DPSHADECAPS_FOGGOURAUD = 524288;
	public static final int D3DPS20_MAX_STATICFLOWCONTROLDEPTH = 4;
	public static final int D3DTEXOPCAPS_DISABLE = 1;
	public static final int D3DTEXOPCAPS_MODULATEALPHA_ADDCOLOR = 131072;
	public static final int D3DPBLENDCAPS_BOTHINVSRCALPHA = 4096;
	public static final int D3DPRASTERCAPS_MIPMAPLODBIAS = 8192;
	public static final int D3DDEVCAPS2_ADAPTIVETESSRTPATCH = 4;
	public static final int D3DTEXOPCAPS_ADDSMOOTH = 1024;
	public static final int D3DPMISCCAPS_FOGVERTEXCLAMPED = 1048576;
	public static final int D3DPTFILTERCAPS_MAGFPYRAMIDALQUAD = 134217728;
	public static final int D3DPTEXTURECAPS_SQUAREONLY = 32;
	public static final int D3DMIN30SHADERINSTRUCTIONS = 512;
	public static final int D3DPBLENDCAPS_SRCCOLOR = 4;
	public static final int D3DPTEXTURECAPS_MIPCUBEMAP = 65536;
	public static final int D3DPTEXTURECAPS_ALPHAPALETTE = 128;
	public static final int D3DSTENCILCAPS_ZERO = 2;
	public static final int D3DCAPS3_RESERVED = -2147483617;
	public static final int D3DPS20_MIN_NUMINSTRUCTIONSLOTS = 96;
	public static final int D3DPRASTERCAPS_DEPTHBIAS = 67108864;
	public static final int D3DSTENCILCAPS_REPLACE = 4;
	public static final int D3DPMISCCAPS_INDEPENDENTWRITEMASKS = 16384;
	public static final int D3DPRASTERCAPS_SLOPESCALEDEPTHBIAS = 33554432;
	public static final int D3DDTCAPS_USHORT4N = 32;
	public static final int D3DDEVCAPS_HWTRANSFORMANDLIGHT = 65536;
	public static final int D3DPSHADECAPS_ALPHAGOURAUDBLEND = 16384;
	public static final int D3DCAPS3_COPY_TO_SYSTEMMEM = 512;
	public static final int D3DPBLENDCAPS_INVDESTCOLOR = 512;
	public static final int D3DVS20_MAX_STATICFLOWCONTROLDEPTH = 4;
	public static final int D3DFVFCAPS_PSIZE = 1048576;
	public static final int D3DTEXOPCAPS_MODULATEINVCOLOR_ADDALPHA = 1048576;
	public static final int D3DPS20_MIN_NUMTEMPS = 12;
	public static final int D3DPS20_MAX_NUMINSTRUCTIONSLOTS = 512;
	public static final int D3DPMISCCAPS_PERSTAGECONSTANT = 32768;
	public static final int D3DPRESENT_INTERVAL_DEFAULT = 0;
	public static final int D3DDTCAPS_UBYTE4N = 2;
	public static final int D3DDEVCAPS_DRAWPRIMITIVES2EX = 32768;
	public static final int D3DPBLENDCAPS_BLENDFACTOR = 8192;
	public static final int D3DPBLENDCAPS_ZERO = 1;
	public static final int D3DTEXOPCAPS_ADDSIGNED = 128;
	public static final int D3DLINECAPS_ZTEST = 2;
	public static final int D3DPCMPCAPS_GREATER = 16;
	public static final int D3DPMISCCAPS_CLIPTLVERTS = 512;
	public static final int D3DPRASTERCAPS_ZBIAS = 16384;
	public static final int D3DCAPS2_CANRENDERWINDOWED = 524288;
	public static final int D3DPSHADECAPS_SPECULARGOURAUDRGB = 512;
	public static final int D3DTEXOPCAPS_MODULATE4X = 32;
	public static final int D3DPTEXTURECAPS_MIPMAP = 16384;
	public static final int D3DMAX30SHADERINSTRUCTIONS = 32768;
	public static final int D3DPCMPCAPS_GREATEREQUAL = 64;
	public static final int D3DCAPS2_CANAUTOGENMIPMAP = 1073741824;
	public static final int D3DDTCAPS_SHORT2N = 4;
	public static final int D3DDTCAPS_USHORT2N = 16;
	public static final int D3DPS20_MAX_DYNAMICFLOWCONTROLDEPTH = 24;
	public static final int D3DDEVCAPS_EXECUTESYSTEMMEMORY = 16;
	public static final int D3DDEVCAPS_PUREDEVICE = 1048576;
	public static final int D3DCAPS2_CANMANAGERESOURCE = 268435456;
	public static final int D3DPS20CAPS_GRADIENTINSTRUCTIONS = (1 << 1);
	public static final int D3DPSHADECAPS_COLORGOURAUDRGB = 8;
	public static final int D3DVS20CAPS_PREDICATION = (1 << 0);
	public static final int D3DVTXPCAPS_LOCALVIEWER = 32;
	public static final int D3DCAPS2_NO2DDURING3DSCENE = 2;
	public static final int D3DDEVCAPS_DRAWPRIMTLVERTEX = 1024;
	public static final int D3DCURSORCAPS_LOWRES = 2;
	public static final int D3DPTEXTURECAPS_MIPVOLUMEMAP = 32768;
	public static final int D3DPRESENT_INTERVAL_IMMEDIATE = -2147483648;
	public static final int D3DTEXOPCAPS_MODULATE2X = 16;
	public static final int D3DPTEXTURECAPS_PERSPECTIVE = 1;
	public static final int D3DPCMPCAPS_NEVER = 1;
	public static final int D3DPMISCCAPS_COLORWRITEENABLE = 128;
	public static final int D3DCAPS_READ_SCANLINE = 131072;
	public static final int D3DDEVCAPS_RTPATCHHANDLEZERO = 8388608;
	public static final int D3DTEXOPCAPS_SUBTRACT = 512;
	public static final int D3DPMISCCAPS_MASKZ = 2;
	public static final int D3DPRASTERCAPS_DITHER = 1;
	public static final int D3DPTFILTERCAPS_MIPFLINEAR = 131072;
	public static final int D3DTEXOPCAPS_MODULATECOLOR_ADDALPHA = 262144;
	public static final int D3DVS20_MIN_DYNAMICFLOWCONTROLDEPTH = 0;
	public static final int D3DSTENCILCAPS_DECRSAT = 16;
	public static final int D3DTEXOPCAPS_BLENDTEXTUREALPHA = 4096;
	public static final int D3DTEXOPCAPS_BLENDFACTORALPHA = 8192;
	public static final int D3DVS20_MIN_NUMTEMPS = 12;
	public static final int D3DPRASTERCAPS_SCISSORTEST = 16777216;
	public static final int D3DPRASTERCAPS_ZBUFFERLESSHSR = 32768;
	public static final int D3DTEXOPCAPS_ADD = 64;
	public static final int D3DPS20_MAX_NUMTEMPS = 32;
	public static final int D3DPMISCCAPS_SEPARATEALPHABLEND = 131072;
	public static final int D3DSTENCILCAPS_DECR = 128;
	public static final int D3DPMISCCAPS_BLENDOP = 2048;
	
	public enum D3DDECLUSAGE implements IntValuedEnum<D3DDECLUSAGE > {
		D3DDECLUSAGE_POSITION(0),
		D3DDECLUSAGE_BLENDWEIGHT(1),
		D3DDECLUSAGE_BLENDINDICES(2),
		D3DDECLUSAGE_NORMAL(3),
		D3DDECLUSAGE_PSIZE(4),
		D3DDECLUSAGE_TEXCOORD(5),
		D3DDECLUSAGE_TANGENT(6),
		D3DDECLUSAGE_BINORMAL(7),
		D3DDECLUSAGE_TESSFACTOR(8),
		D3DDECLUSAGE_POSITIONT(9),
		D3DDECLUSAGE_COLOR(10),
		D3DDECLUSAGE_FOG(11),
		D3DDECLUSAGE_DEPTH(12),
		D3DDECLUSAGE_SAMPLE(13);
		D3DDECLUSAGE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DDECLUSAGE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DDECLUSAGE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DDECLMETHOD implements IntValuedEnum<D3DDECLMETHOD > {
		D3DDECLMETHOD_DEFAULT(0),
		D3DDECLMETHOD_PARTIALU(1),
		D3DDECLMETHOD_PARTIALV(2),
		D3DDECLMETHOD_CROSSUV(3),
		D3DDECLMETHOD_UV(4),
		D3DDECLMETHOD_LOOKUP(5),
		D3DDECLMETHOD_LOOKUPPRESAMPLED(6);
		D3DDECLMETHOD(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DDECLMETHOD > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DDECLMETHOD > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DDECLTYPE implements IntValuedEnum<D3DDECLTYPE > {
		D3DDECLTYPE_FLOAT1(0),
		D3DDECLTYPE_FLOAT2(1),
		D3DDECLTYPE_FLOAT3(2),
		D3DDECLTYPE_FLOAT4(3),
		D3DDECLTYPE_D3DCOLOR(4),
		D3DDECLTYPE_UBYTE4(5),
		D3DDECLTYPE_SHORT2(6),
		D3DDECLTYPE_SHORT4(7),
		D3DDECLTYPE_UBYTE4N(8),
		D3DDECLTYPE_SHORT2N(9),
		D3DDECLTYPE_SHORT4N(10),
		D3DDECLTYPE_USHORT2N(11),
		D3DDECLTYPE_USHORT4N(12),
		D3DDECLTYPE_UDEC3(13),
		D3DDECLTYPE_DEC3N(14),
		D3DDECLTYPE_FLOAT16_2(15),
		D3DDECLTYPE_FLOAT16_4(16),
		D3DDECLTYPE_UNUSED(17);
		D3DDECLTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DDECLTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DDECLTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DQUERYTYPE implements IntValuedEnum<D3DQUERYTYPE > {
		D3DQUERYTYPE_VCACHE(4),
		D3DQUERYTYPE_RESOURCEMANAGER(5),
		D3DQUERYTYPE_VERTEXSTATS(6),
		D3DQUERYTYPE_EVENT(8),
		D3DQUERYTYPE_OCCLUSION(9),
		D3DQUERYTYPE_TIMESTAMP(10),
		D3DQUERYTYPE_TIMESTAMPDISJOINT(11),
		D3DQUERYTYPE_TIMESTAMPFREQ(12),
		D3DQUERYTYPE_PIPELINETIMINGS(13),
		D3DQUERYTYPE_INTERFACETIMINGS(14),
		D3DQUERYTYPE_VERTEXTIMINGS(15),
		D3DQUERYTYPE_PIXELTIMINGS(16),
		D3DQUERYTYPE_BANDWIDTHTIMINGS(17),
		D3DQUERYTYPE_CACHEUTILIZATION(18);
		D3DQUERYTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DQUERYTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DQUERYTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADER_INSTRUCTION_OPCODE_TYPE implements IntValuedEnum<D3DSHADER_INSTRUCTION_OPCODE_TYPE > {
		D3DSIO_NOP(0),
		D3DSIO_MOV(1),
		D3DSIO_ADD(2),
		D3DSIO_SUB(3),
		D3DSIO_MAD(4),
		D3DSIO_MUL(5),
		D3DSIO_RCP(6),
		D3DSIO_RSQ(7),
		D3DSIO_DP3(8),
		D3DSIO_DP4(9),
		D3DSIO_MIN(10),
		D3DSIO_MAX(11),
		D3DSIO_SLT(12),
		D3DSIO_SGE(13),
		D3DSIO_EXP(14),
		D3DSIO_LOG(15),
		D3DSIO_LIT(16),
		D3DSIO_DST(17),
		D3DSIO_LRP(18),
		D3DSIO_FRC(19),
		D3DSIO_M4x4(20),
		D3DSIO_M4x3(21),
		D3DSIO_M3x4(22),
		D3DSIO_M3x3(23),
		D3DSIO_M3x2(24),
		D3DSIO_CALL(25),
		D3DSIO_CALLNZ(26),
		D3DSIO_LOOP(27),
		D3DSIO_RET(28),
		D3DSIO_ENDLOOP(29),
		D3DSIO_LABEL(30),
		D3DSIO_DCL(31),
		D3DSIO_POW(32),
		D3DSIO_CRS(33),
		D3DSIO_SGN(34),
		D3DSIO_ABS(35),
		D3DSIO_NRM(36),
		D3DSIO_SINCOS(37),
		D3DSIO_REP(38),
		D3DSIO_ENDREP(39),
		D3DSIO_IF(40),
		D3DSIO_IFC(41),
		D3DSIO_ELSE(42),
		D3DSIO_ENDIF(43),
		D3DSIO_BREAK(44),
		D3DSIO_BREAKC(45),
		D3DSIO_MOVA(46),
		D3DSIO_DEFB(47),
		D3DSIO_DEFI(48),
		D3DSIO_TEXCOORD(64),
		D3DSIO_TEXKILL(65),
		D3DSIO_TEX(66),
		D3DSIO_TEXBEM(67),
		D3DSIO_TEXBEML(68),
		D3DSIO_TEXREG2AR(69),
		D3DSIO_TEXREG2GB(70),
		D3DSIO_TEXM3x2PAD(71),
		D3DSIO_TEXM3x2TEX(72),
		D3DSIO_TEXM3x3PAD(73),
		D3DSIO_TEXM3x3TEX(74),
		D3DSIO_TEXM3x3DIFF(75),
		D3DSIO_TEXM3x3SPEC(76),
		D3DSIO_TEXM3x3VSPEC(77),
		D3DSIO_EXPP(78),
		D3DSIO_LOGP(79),
		D3DSIO_CND(80),
		D3DSIO_DEF(81),
		D3DSIO_TEXREG2RGB(82),
		D3DSIO_TEXDP3TEX(83),
		D3DSIO_TEXM3x2DEPTH(84),
		D3DSIO_TEXDP3(85),
		D3DSIO_TEXM3x3(86),
		D3DSIO_TEXDEPTH(87),
		D3DSIO_CMP(88),
		D3DSIO_BEM(89),
		D3DSIO_DP2ADD(90),
		D3DSIO_DSX(91),
		D3DSIO_DSY(92),
		D3DSIO_TEXLDD(93),
		D3DSIO_SETP(94),
		D3DSIO_TEXLDL(95),
		D3DSIO_BREAKP(96),
		D3DSIO_PHASE(0xFFFD),
		D3DSIO_COMMENT(0xFFFE),
		D3DSIO_END(0xFFFF),
		D3DSIO_FORCE_DWORD(0x7FFFFFFF);
		D3DSHADER_INSTRUCTION_OPCODE_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADER_INSTRUCTION_OPCODE_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADER_INSTRUCTION_OPCODE_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSAMPLER_TEXTURE_TYPE implements IntValuedEnum<D3DSAMPLER_TEXTURE_TYPE > {
		D3DSTT_UNKNOWN(0 << 27),
		D3DSTT_1D(1 << 27),
		D3DSTT_2D(2 << 27),
		D3DSTT_CUBE(3 << 27),
		D3DSTT_VOLUME(4 << 27),
		D3DSTT_FORCE_DWORD(0x7FFFFFFF);
		D3DSAMPLER_TEXTURE_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSAMPLER_TEXTURE_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSAMPLER_TEXTURE_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADER_PARAM_DSTMOD_TYPE implements IntValuedEnum<D3DSHADER_PARAM_DSTMOD_TYPE > {
		D3DSPDM_NONE(0 << 20),
		D3DSPDM_SATURATE(1 << 20),
		D3DSPDM_PARTIALPRECISION(2 << 20),
		D3DSPDM_MSAMPCENTROID(4 << 20),
		D3DSPDM_FORCE_DWORD(0x7FFFFFFF);
		D3DSHADER_PARAM_DSTMOD_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADER_PARAM_DSTMOD_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADER_PARAM_DSTMOD_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADER_PARAM_REGISTER_TYPE implements IntValuedEnum<D3DSHADER_PARAM_REGISTER_TYPE > {
		D3DSPR_TEMP(0),
		D3DSPR_INPUT(1),
		D3DSPR_CONST(2),
		D3DSPR_ADDR(3),
		D3DSPR_TEXTURE(3),
		D3DSPR_RASTOUT(4),
		D3DSPR_ATTROUT(5),
		D3DSPR_TEXCRDOUT(6),
		D3DSPR_OUTPUT(6),
		D3DSPR_CONSTINT(7),
		D3DSPR_COLOROUT(8),
		D3DSPR_DEPTHOUT(9),
		D3DSPR_SAMPLER(10),
		D3DSPR_CONST2(11),
		D3DSPR_CONST3(12),
		D3DSPR_CONST4(13),
		D3DSPR_CONSTBOOL(14),
		D3DSPR_LOOP(15),
		D3DSPR_TEMPFLOAT16(16),
		D3DSPR_MISCTYPE(17),
		D3DSPR_LABEL(18),
		D3DSPR_PREDICATE(19),
		D3DSPR_FORCE_DWORD(0x7FFFFFFF);
		D3DSHADER_PARAM_REGISTER_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADER_PARAM_REGISTER_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADER_PARAM_REGISTER_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADER_MISCTYPE_OFFSETS implements IntValuedEnum<D3DSHADER_MISCTYPE_OFFSETS > {
		D3DSMO_POSITION(0),
		D3DSMO_FACE(1);
		D3DSHADER_MISCTYPE_OFFSETS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADER_MISCTYPE_OFFSETS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADER_MISCTYPE_OFFSETS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DVS_RASTOUT_OFFSETS implements IntValuedEnum<D3DVS_RASTOUT_OFFSETS > {
		D3DSRO_POSITION(0),
		D3DSRO_FOG(1),
		D3DSRO_POINT_SIZE(2),
		D3DSRO_FORCE_DWORD(0x7FFFFFFF);
		D3DVS_RASTOUT_OFFSETS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DVS_RASTOUT_OFFSETS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DVS_RASTOUT_OFFSETS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DVS_ADDRESSMODE_TYPE implements IntValuedEnum<D3DVS_ADDRESSMODE_TYPE > {
		D3DVS_ADDRMODE_ABSOLUTE(0 << 13),
		D3DVS_ADDRMODE_RELATIVE(1 << 13),
		D3DVS_ADDRMODE_FORCE_DWORD(0x7FFFFFFF);
		D3DVS_ADDRESSMODE_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DVS_ADDRESSMODE_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DVS_ADDRESSMODE_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADER_ADDRESSMODE_TYPE implements IntValuedEnum<D3DSHADER_ADDRESSMODE_TYPE > {
		D3DSHADER_ADDRMODE_ABSOLUTE(0 << 13),
		D3DSHADER_ADDRMODE_RELATIVE(1 << 13),
		D3DSHADER_ADDRMODE_FORCE_DWORD(0x7FFFFFFF);
		D3DSHADER_ADDRESSMODE_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADER_ADDRESSMODE_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADER_ADDRESSMODE_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADER_PARAM_SRCMOD_TYPE implements IntValuedEnum<D3DSHADER_PARAM_SRCMOD_TYPE > {
		D3DSPSM_NONE(0 << 24),
		D3DSPSM_NEG(1 << 24),
		D3DSPSM_BIAS(2 << 24),
		D3DSPSM_BIASNEG(3 << 24),
		D3DSPSM_SIGN(4 << 24),
		D3DSPSM_SIGNNEG(5 << 24),
		D3DSPSM_COMP(6 << 24),
		D3DSPSM_X2(7 << 24),
		D3DSPSM_X2NEG(8 << 24),
		D3DSPSM_DZ(9 << 24),
		D3DSPSM_DW(10 << 24),
		D3DSPSM_ABS(11 << 24),
		D3DSPSM_ABSNEG(12 << 24),
		D3DSPSM_NOT(13 << 24),
		D3DSPSM_FORCE_DWORD(0x7FFFFFFF);
		D3DSHADER_PARAM_SRCMOD_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADER_PARAM_SRCMOD_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADER_PARAM_SRCMOD_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DBACKBUFFER_TYPE implements IntValuedEnum<D3DBACKBUFFER_TYPE > {
		D3DBACKBUFFER_TYPE_MONO(0),
		D3DBACKBUFFER_TYPE_LEFT(1),
		D3DBACKBUFFER_TYPE_RIGHT(2),
		D3DBACKBUFFER_TYPE_FORCE_DWORD(0x7FFFFFFF);
		D3DBACKBUFFER_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DBACKBUFFER_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DBACKBUFFER_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DBASISTYPE implements IntValuedEnum<D3DBASISTYPE > {
		D3DBASIS_BEZIER(0),
		D3DBASIS_BSPLINE(1),
		D3DBASIS_INTERPOLATE(2),
		D3DBASIS_FORCE_DWORD(0x7FFFFFFF);
		D3DBASISTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DBASISTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DBASISTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DBLEND implements IntValuedEnum<D3DBLEND > {
		D3DBLEND_ZERO(1),
		D3DBLEND_ONE(2),
		D3DBLEND_SRCCOLOR(3),
		D3DBLEND_INVSRCCOLOR(4),
		D3DBLEND_SRCALPHA(5),
		D3DBLEND_INVSRCALPHA(6),
		D3DBLEND_DESTALPHA(7),
		D3DBLEND_INVDESTALPHA(8),
		D3DBLEND_DESTCOLOR(9),
		D3DBLEND_INVDESTCOLOR(10),
		D3DBLEND_SRCALPHASAT(11),
		D3DBLEND_BOTHSRCALPHA(12),
		D3DBLEND_BOTHINVSRCALPHA(13),
		D3DBLEND_BLENDFACTOR(14),
		D3DBLEND_INVBLENDFACTOR(15),
		D3DBLEND_FORCE_DWORD(0x7FFFFFFF);
		D3DBLEND(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DBLEND > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DBLEND > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DBLENDOP implements IntValuedEnum<D3DBLENDOP > {
		D3DBLENDOP_ADD(1),
		D3DBLENDOP_SUBTRACT(2),
		D3DBLENDOP_REVSUBTRACT(3),
		D3DBLENDOP_MIN(4),
		D3DBLENDOP_MAX(5),
		D3DBLENDOP_FORCE_DWORD(0x7FFFFFFF);
		D3DBLENDOP(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DBLENDOP > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DBLENDOP > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DCMPFUNC implements IntValuedEnum<D3DCMPFUNC > {
		D3DCMP_NEVER(1),
		D3DCMP_LESS(2),
		D3DCMP_EQUAL(3),
		D3DCMP_LESSEQUAL(4),
		D3DCMP_GREATER(5),
		D3DCMP_NOTEQUAL(6),
		D3DCMP_GREATEREQUAL(7),
		D3DCMP_ALWAYS(8),
		D3DCMP_FORCE_DWORD(0x7FFFFFFF);
		D3DCMPFUNC(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DCMPFUNC > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DCMPFUNC > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DCUBEMAP_FACES implements IntValuedEnum<D3DCUBEMAP_FACES > {
		D3DCUBEMAP_FACE_POSITIVE_X(0),
		D3DCUBEMAP_FACE_NEGATIVE_X(1),
		D3DCUBEMAP_FACE_POSITIVE_Y(2),
		D3DCUBEMAP_FACE_NEGATIVE_Y(3),
		D3DCUBEMAP_FACE_POSITIVE_Z(4),
		D3DCUBEMAP_FACE_NEGATIVE_Z(5),
		D3DCUBEMAP_FACE_FORCE_DWORD(0xFFFFFFFF);
		D3DCUBEMAP_FACES(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DCUBEMAP_FACES > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DCUBEMAP_FACES > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DCULL implements IntValuedEnum<D3DCULL > {
		D3DCULL_NONE(1),
		D3DCULL_CW(2),
		D3DCULL_CCW(3),
		D3DCULL_FORCE_DWORD(0x7FFFFFFF);
		D3DCULL(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DCULL > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DCULL > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DDEBUGMONITORTOKENS implements IntValuedEnum<D3DDEBUGMONITORTOKENS > {
		D3DDMT_ENABLE(0),
		D3DDMT_DISABLE(1),
		D3DDMT_FORCE_DWORD(0x7FFFFFFF);
		D3DDEBUGMONITORTOKENS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DDEBUGMONITORTOKENS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DDEBUGMONITORTOKENS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DDEGREETYPE implements IntValuedEnum<D3DDEGREETYPE > {
		D3DDEGREE_LINEAR(1),
		D3DDEGREE_QUADRATIC(2),
		D3DDEGREE_CUBIC(3),
		D3DDEGREE_QUINTIC(5),
		D3DDEGREE_FORCE_DWORD(0x7FFFFFFF);
		D3DDEGREETYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DDEGREETYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DDEGREETYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DDEVTYPE implements IntValuedEnum<D3DDEVTYPE > {
		D3DDEVTYPE_HAL(1),
		D3DDEVTYPE_REF(2),
		D3DDEVTYPE_SW(3),
		D3DDEVTYPE_NULLREF(4),
		D3DDEVTYPE_FORCE_DWORD(0xFFFFFFFF);
		D3DDEVTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DDEVTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DDEVTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DFILLMODE implements IntValuedEnum<D3DFILLMODE > {
		D3DFILL_POINT(1),
		D3DFILL_WIREFRAME(2),
		D3DFILL_SOLID(3),
		D3DFILL_FORCE_DWORD(0x7FFFFFFF);
		D3DFILLMODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DFILLMODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DFILLMODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DFOGMODE implements IntValuedEnum<D3DFOGMODE > {
		D3DFOG_NONE(0),
		D3DFOG_EXP(1),
		D3DFOG_EXP2(2),
		D3DFOG_LINEAR(3),
		D3DFOG_FORCE_DWORD(0x7FFFFFFF);
		D3DFOGMODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DFOGMODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DFOGMODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DFORMAT implements IntValuedEnum<D3DFORMAT > {
		D3DFMT_UNKNOWN(0),
		D3DFMT_R8G8B8(20),
		D3DFMT_A8R8G8B8(21),
		D3DFMT_X8R8G8B8(22),
		D3DFMT_R5G6B5(23),
		D3DFMT_X1R5G5B5(24),
		D3DFMT_A1R5G5B5(25),
		D3DFMT_A4R4G4B4(26),
		D3DFMT_R3G3B2(27),
		D3DFMT_A8(28),
		D3DFMT_A8R3G3B2(29),
		D3DFMT_X4R4G4B4(30),
		D3DFMT_A2B10G10R10(31),
		D3DFMT_A8B8G8R8(32),
		D3DFMT_X8B8G8R8(33),
		D3DFMT_G16R16(34),
		D3DFMT_A2R10G10B10(35),
		D3DFMT_A16B16G16R16(36),
		D3DFMT_A8P8(40),
		D3DFMT_P8(41),
		D3DFMT_L8(50),
		D3DFMT_A8L8(51),
		D3DFMT_A4L4(52),
		D3DFMT_V8U8(60),
		D3DFMT_L6V5U5(61),
		D3DFMT_X8L8V8U8(62),
		D3DFMT_Q8W8V8U8(63),
		D3DFMT_V16U16(64),
		D3DFMT_A2W10V10U10(67),
		/*
		D3DFMT_UYVY(((DWORD)(BYTE)('U') | ((DWORD)(BYTE)('Y') << 8) | ((DWORD)(BYTE)('V') << 16) | ((DWORD)(BYTE)('Y') << 24))),
		D3DFMT_YUY2(((DWORD)(BYTE)('Y') | ((DWORD)(BYTE)('U') << 8) | ((DWORD)(BYTE)('Y') << 16) | ((DWORD)(BYTE)('2') << 24))),
		D3DFMT_DXT1(((DWORD)(BYTE)('D') | ((DWORD)(BYTE)('X') << 8) | ((DWORD)(BYTE)('T') << 16) | ((DWORD)(BYTE)('1') << 24))),
		D3DFMT_DXT2(((DWORD)(BYTE)('D') | ((DWORD)(BYTE)('X') << 8) | ((DWORD)(BYTE)('T') << 16) | ((DWORD)(BYTE)('2') << 24))),
		D3DFMT_DXT3(((DWORD)(BYTE)('D') | ((DWORD)(BYTE)('X') << 8) | ((DWORD)(BYTE)('T') << 16) | ((DWORD)(BYTE)('3') << 24))),
		D3DFMT_DXT4(((DWORD)(BYTE)('D') | ((DWORD)(BYTE)('X') << 8) | ((DWORD)(BYTE)('T') << 16) | ((DWORD)(BYTE)('4') << 24))),
		D3DFMT_DXT5(((DWORD)(BYTE)('D') | ((DWORD)(BYTE)('X') << 8) | ((DWORD)(BYTE)('T') << 16) | ((DWORD)(BYTE)('5') << 24))),
		D3DFMT_MULTI2_ARGB8(((DWORD)(BYTE)('M') | ((DWORD)(BYTE)('E') << 8) | ((DWORD)(BYTE)('T') << 16) | ((DWORD)(BYTE)('1') << 24))),
		D3DFMT_G8R8_G8B8(((DWORD)(BYTE)('G') | ((DWORD)(BYTE)('R') << 8) | ((DWORD)(BYTE)('G') << 16) | ((DWORD)(BYTE)('B') << 24))),
		D3DFMT_R8G8_B8G8(((DWORD)(BYTE)('R') | ((DWORD)(BYTE)('G') << 8) | ((DWORD)(BYTE)('B') << 16) | ((DWORD)(BYTE)('G') << 24))),
		*/
		D3DFMT_D16_LOCKABLE(70),
		D3DFMT_D32(71),
		D3DFMT_D15S1(73),
		D3DFMT_D24S8(75),
		D3DFMT_D24X8(77),
		D3DFMT_D24X4S4(79),
		D3DFMT_D16(80),
		D3DFMT_L16(81),
		D3DFMT_D32F_LOCKABLE(82),
		D3DFMT_D24FS8(83),
		D3DFMT_VERTEXDATA(100),
		D3DFMT_INDEX16(101),
		D3DFMT_INDEX32(102),
		D3DFMT_Q16W16V16U16(110),
		D3DFMT_R16F(111),
		D3DFMT_G16R16F(112),
		D3DFMT_A16B16G16R16F(113),
		D3DFMT_R32F(114),
		D3DFMT_G32R32F(115),
		D3DFMT_A32B32G32R32F(116),
		D3DFMT_CxV8U8(117),
		D3DFMT_FORCE_DWORD(0xFFFFFFFF);
		D3DFORMAT(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DFORMAT > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DFORMAT > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DLIGHTTYPE implements IntValuedEnum<D3DLIGHTTYPE > {
		D3DLIGHT_POINT(1),
		D3DLIGHT_SPOT(2),
		D3DLIGHT_DIRECTIONAL(3),
		D3DLIGHT_FORCE_DWORD(0x7FFFFFFF);
		D3DLIGHTTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DLIGHTTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DLIGHTTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DMATERIALCOLORSOURCE implements IntValuedEnum<D3DMATERIALCOLORSOURCE > {
		D3DMCS_MATERIAL(0),
		D3DMCS_COLOR1(1),
		D3DMCS_COLOR2(2),
		D3DMCS_FORCE_DWORD(0x7FFFFFFF);
		D3DMATERIALCOLORSOURCE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DMATERIALCOLORSOURCE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DMATERIALCOLORSOURCE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DMULTISAMPLE_TYPE implements IntValuedEnum<D3DMULTISAMPLE_TYPE > {
		D3DMULTISAMPLE_NONE(0),
		D3DMULTISAMPLE_NONMASKABLE(1),
		D3DMULTISAMPLE_2_SAMPLES(2),
		D3DMULTISAMPLE_3_SAMPLES(3),
		D3DMULTISAMPLE_4_SAMPLES(4),
		D3DMULTISAMPLE_5_SAMPLES(5),
		D3DMULTISAMPLE_6_SAMPLES(6),
		D3DMULTISAMPLE_7_SAMPLES(7),
		D3DMULTISAMPLE_8_SAMPLES(8),
		D3DMULTISAMPLE_9_SAMPLES(9),
		D3DMULTISAMPLE_10_SAMPLES(10),
		D3DMULTISAMPLE_11_SAMPLES(11),
		D3DMULTISAMPLE_12_SAMPLES(12),
		D3DMULTISAMPLE_13_SAMPLES(13),
		D3DMULTISAMPLE_14_SAMPLES(14),
		D3DMULTISAMPLE_15_SAMPLES(15),
		D3DMULTISAMPLE_16_SAMPLES(16),
		D3DMULTISAMPLE_FORCE_DWORD(0x7FFFFFFF);
		D3DMULTISAMPLE_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DMULTISAMPLE_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DMULTISAMPLE_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DPATCHEDGESTYLE implements IntValuedEnum<D3DPATCHEDGESTYLE > {
		D3DPATCHEDGE_DISCRETE(0),
		D3DPATCHEDGE_CONTINUOUS(1),
		D3DPATCHEDGE_FORCE_DWORD(0x7FFFFFFF);
		D3DPATCHEDGESTYLE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DPATCHEDGESTYLE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DPATCHEDGESTYLE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DPOOL implements IntValuedEnum<D3DPOOL > {
		D3DPOOL_DEFAULT(0),
		D3DPOOL_MANAGED(1),
		D3DPOOL_SYSTEMMEM(2),
		D3DPOOL_SCRATCH(3),
		D3DPOOL_FORCE_DWORD(0x7FFFFFFF);
		D3DPOOL(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DPOOL > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DPOOL > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DPRIMITIVETYPE implements IntValuedEnum<D3DPRIMITIVETYPE > {
		D3DPT_POINTLIST(1),
		D3DPT_LINELIST(2),
		D3DPT_LINESTRIP(3),
		D3DPT_TRIANGLELIST(4),
		D3DPT_TRIANGLESTRIP(5),
		D3DPT_TRIANGLEFAN(6),
		D3DPT_FORCE_DWORD(0x7FFFFFFF);
		D3DPRIMITIVETYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DPRIMITIVETYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DPRIMITIVETYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DRENDERSTATETYPE implements IntValuedEnum<D3DRENDERSTATETYPE > {
		D3DRS_ZENABLE(7),
		D3DRS_FILLMODE(8),
		D3DRS_SHADEMODE(9),
		D3DRS_ZWRITEENABLE(14),
		D3DRS_ALPHATESTENABLE(15),
		D3DRS_LASTPIXEL(16),
		D3DRS_SRCBLEND(19),
		D3DRS_DESTBLEND(20),
		D3DRS_CULLMODE(22),
		D3DRS_ZFUNC(23),
		D3DRS_ALPHAREF(24),
		D3DRS_ALPHAFUNC(25),
		D3DRS_DITHERENABLE(26),
		D3DRS_ALPHABLENDENABLE(27),
		D3DRS_FOGENABLE(28),
		D3DRS_SPECULARENABLE(29),
		D3DRS_FOGCOLOR(34),
		D3DRS_FOGTABLEMODE(35),
		D3DRS_FOGSTART(36),
		D3DRS_FOGEND(37),
		D3DRS_FOGDENSITY(38),
		D3DRS_RANGEFOGENABLE(48),
		D3DRS_STENCILENABLE(52),
		D3DRS_STENCILFAIL(53),
		D3DRS_STENCILZFAIL(54),
		D3DRS_STENCILPASS(55),
		D3DRS_STENCILFUNC(56),
		D3DRS_STENCILREF(57),
		D3DRS_STENCILMASK(58),
		D3DRS_STENCILWRITEMASK(59),
		D3DRS_TEXTUREFACTOR(60),
		D3DRS_WRAP0(128),
		D3DRS_WRAP1(129),
		D3DRS_WRAP2(130),
		D3DRS_WRAP3(131),
		D3DRS_WRAP4(132),
		D3DRS_WRAP5(133),
		D3DRS_WRAP6(134),
		D3DRS_WRAP7(135),
		D3DRS_CLIPPING(136),
		D3DRS_LIGHTING(137),
		D3DRS_AMBIENT(139),
		D3DRS_FOGVERTEXMODE(140),
		D3DRS_COLORVERTEX(141),
		D3DRS_LOCALVIEWER(142),
		D3DRS_NORMALIZENORMALS(143),
		D3DRS_DIFFUSEMATERIALSOURCE(145),
		D3DRS_SPECULARMATERIALSOURCE(146),
		D3DRS_AMBIENTMATERIALSOURCE(147),
		D3DRS_EMISSIVEMATERIALSOURCE(148),
		D3DRS_VERTEXBLEND(151),
		D3DRS_CLIPPLANEENABLE(152),
		D3DRS_POINTSIZE(154),
		D3DRS_POINTSIZE_MIN(155),
		D3DRS_POINTSPRITEENABLE(156),
		D3DRS_POINTSCALEENABLE(157),
		D3DRS_POINTSCALE_A(158),
		D3DRS_POINTSCALE_B(159),
		D3DRS_POINTSCALE_C(160),
		D3DRS_MULTISAMPLEANTIALIAS(161),
		D3DRS_MULTISAMPLEMASK(162),
		D3DRS_PATCHEDGESTYLE(163),
		D3DRS_DEBUGMONITORTOKEN(165),
		D3DRS_POINTSIZE_MAX(166),
		D3DRS_INDEXEDVERTEXBLENDENABLE(167),
		D3DRS_COLORWRITEENABLE(168),
		D3DRS_TWEENFACTOR(170),
		D3DRS_BLENDOP(171),
		D3DRS_POSITIONDEGREE(172),
		D3DRS_NORMALDEGREE(173),
		D3DRS_SCISSORTESTENABLE(174),
		D3DRS_SLOPESCALEDEPTHBIAS(175),
		D3DRS_ANTIALIASEDLINEENABLE(176),
		D3DRS_MINTESSELLATIONLEVEL(178),
		D3DRS_MAXTESSELLATIONLEVEL(179),
		D3DRS_ADAPTIVETESS_X(180),
		D3DRS_ADAPTIVETESS_Y(181),
		D3DRS_ADAPTIVETESS_Z(182),
		D3DRS_ADAPTIVETESS_W(183),
		D3DRS_ENABLEADAPTIVETESSELLATION(184),
		D3DRS_TWOSIDEDSTENCILMODE(185),
		D3DRS_CCW_STENCILFAIL(186),
		D3DRS_CCW_STENCILZFAIL(187),
		D3DRS_CCW_STENCILPASS(188),
		D3DRS_CCW_STENCILFUNC(189),
		D3DRS_COLORWRITEENABLE1(190),
		D3DRS_COLORWRITEENABLE2(191),
		D3DRS_COLORWRITEENABLE3(192),
		D3DRS_BLENDFACTOR(193),
		D3DRS_SRGBWRITEENABLE(194),
		D3DRS_DEPTHBIAS(195),
		D3DRS_WRAP8(198),
		D3DRS_WRAP9(199),
		D3DRS_WRAP10(200),
		D3DRS_WRAP11(201),
		D3DRS_WRAP12(202),
		D3DRS_WRAP13(203),
		D3DRS_WRAP14(204),
		D3DRS_WRAP15(205),
		D3DRS_SEPARATEALPHABLENDENABLE(206),
		D3DRS_SRCBLENDALPHA(207),
		D3DRS_DESTBLENDALPHA(208),
		D3DRS_BLENDOPALPHA(209),
		D3DRS_FORCE_DWORD(0x7FFFFFFF);
		D3DRENDERSTATETYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DRENDERSTATETYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DRENDERSTATETYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DRESOURCETYPE implements IntValuedEnum<D3DRESOURCETYPE > {
		D3DRTYPE_SURFACE(1),
		D3DRTYPE_VOLUME(2),
		D3DRTYPE_TEXTURE(3),
		D3DRTYPE_VOLUMETEXTURE(4),
		D3DRTYPE_CUBETEXTURE(5),
		D3DRTYPE_VERTEXBUFFER(6),
		D3DRTYPE_INDEXBUFFER(7),
		D3DRTYPE_FORCE_DWORD(0x7FFFFFFF);
		D3DRESOURCETYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DRESOURCETYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DRESOURCETYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADEMODE implements IntValuedEnum<D3DSHADEMODE > {
		D3DSHADE_FLAT(1),
		D3DSHADE_GOURAUD(2),
		D3DSHADE_PHONG(3),
		D3DSHADE_FORCE_DWORD(0x7FFFFFFF);
		D3DSHADEMODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADEMODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADEMODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSTATEBLOCKTYPE implements IntValuedEnum<D3DSTATEBLOCKTYPE > {
		D3DSBT_ALL(1),
		D3DSBT_PIXELSTATE(2),
		D3DSBT_VERTEXSTATE(3),
		D3DSBT_FORCE_DWORD(0xFFFFFFFF);
		D3DSTATEBLOCKTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSTATEBLOCKTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSTATEBLOCKTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSTENCILOP implements IntValuedEnum<D3DSTENCILOP > {
		D3DSTENCILOP_KEEP(1),
		D3DSTENCILOP_ZERO(2),
		D3DSTENCILOP_REPLACE(3),
		D3DSTENCILOP_INCRSAT(4),
		D3DSTENCILOP_DECRSAT(5),
		D3DSTENCILOP_INVERT(6),
		D3DSTENCILOP_INCR(7),
		D3DSTENCILOP_DECR(8),
		D3DSTENCILOP_FORCE_DWORD(0x7FFFFFFF);
		D3DSTENCILOP(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSTENCILOP > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSTENCILOP > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSWAPEFFECT implements IntValuedEnum<D3DSWAPEFFECT > {
		D3DSWAPEFFECT_DISCARD(1),
		D3DSWAPEFFECT_FLIP(2),
		D3DSWAPEFFECT_COPY(3),
		D3DSWAPEFFECT_FORCE_DWORD(0xFFFFFFFF);
		D3DSWAPEFFECT(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSWAPEFFECT > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSWAPEFFECT > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DTEXTUREADDRESS implements IntValuedEnum<D3DTEXTUREADDRESS > {
		D3DTADDRESS_WRAP(1),
		D3DTADDRESS_MIRROR(2),
		D3DTADDRESS_CLAMP(3),
		D3DTADDRESS_BORDER(4),
		D3DTADDRESS_MIRRORONCE(5),
		D3DTADDRESS_FORCE_DWORD(0x7FFFFFFF);
		D3DTEXTUREADDRESS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DTEXTUREADDRESS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DTEXTUREADDRESS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DTEXTUREFILTERTYPE implements IntValuedEnum<D3DTEXTUREFILTERTYPE > {
		D3DTEXF_NONE(0),
		D3DTEXF_POINT(1),
		D3DTEXF_LINEAR(2),
		D3DTEXF_ANISOTROPIC(3),
		D3DTEXF_FLATCUBIC(4),
		D3DTEXF_GAUSSIANCUBIC(5),
		D3DTEXF_PYRAMIDALQUAD(6),
		D3DTEXF_GAUSSIANQUAD(7),
		D3DTEXF_FORCE_DWORD(0x7FFFFFFF);
		D3DTEXTUREFILTERTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DTEXTUREFILTERTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DTEXTUREFILTERTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DTEXTUREOP implements IntValuedEnum<D3DTEXTUREOP > {
		D3DTOP_DISABLE(1),
		D3DTOP_SELECTARG1(2),
		D3DTOP_SELECTARG2(3),
		D3DTOP_MODULATE(4),
		D3DTOP_MODULATE2X(5),
		D3DTOP_MODULATE4X(6),
		D3DTOP_ADD(7),
		D3DTOP_ADDSIGNED(8),
		D3DTOP_ADDSIGNED2X(9),
		D3DTOP_SUBTRACT(10),
		D3DTOP_ADDSMOOTH(11),
		D3DTOP_BLENDDIFFUSEALPHA(12),
		D3DTOP_BLENDTEXTUREALPHA(13),
		D3DTOP_BLENDFACTORALPHA(14),
		D3DTOP_BLENDTEXTUREALPHAPM(15),
		D3DTOP_BLENDCURRENTALPHA(16),
		D3DTOP_PREMODULATE(17),
		D3DTOP_MODULATEALPHA_ADDCOLOR(18),
		D3DTOP_MODULATECOLOR_ADDALPHA(19),
		D3DTOP_MODULATEINVALPHA_ADDCOLOR(20),
		D3DTOP_MODULATEINVCOLOR_ADDALPHA(21),
		D3DTOP_BUMPENVMAP(22),
		D3DTOP_BUMPENVMAPLUMINANCE(23),
		D3DTOP_DOTPRODUCT3(24),
		D3DTOP_MULTIPLYADD(25),
		D3DTOP_LERP(26),
		D3DTOP_FORCE_DWORD(0x7FFFFFFF);
		D3DTEXTUREOP(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DTEXTUREOP > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DTEXTUREOP > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DTEXTURESTAGESTATETYPE implements IntValuedEnum<D3DTEXTURESTAGESTATETYPE > {
		D3DTSS_COLOROP(1),
		D3DTSS_COLORARG1(2),
		D3DTSS_COLORARG2(3),
		D3DTSS_ALPHAOP(4),
		D3DTSS_ALPHAARG1(5),
		D3DTSS_ALPHAARG2(6),
		D3DTSS_BUMPENVMAT00(7),
		D3DTSS_BUMPENVMAT01(8),
		D3DTSS_BUMPENVMAT10(9),
		D3DTSS_BUMPENVMAT11(10),
		D3DTSS_TEXCOORDINDEX(11),
		D3DTSS_BUMPENVLSCALE(22),
		D3DTSS_BUMPENVLOFFSET(23),
		D3DTSS_TEXTURETRANSFORMFLAGS(24),
		D3DTSS_COLORARG0(26),
		D3DTSS_ALPHAARG0(27),
		D3DTSS_RESULTARG(28),
		D3DTSS_CONSTANT(32),
		D3DTSS_FORCE_DWORD(0x7FFFFFFF);
		D3DTEXTURESTAGESTATETYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DTEXTURESTAGESTATETYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DTEXTURESTAGESTATETYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DTEXTURETRANSFORMFLAGS implements IntValuedEnum<D3DTEXTURETRANSFORMFLAGS > {
		D3DTTFF_DISABLE(0),
		D3DTTFF_COUNT1(1),
		D3DTTFF_COUNT2(2),
		D3DTTFF_COUNT3(3),
		D3DTTFF_COUNT4(4),
		D3DTTFF_PROJECTED(256),
		D3DTTFF_FORCE_DWORD(0x7FFFFFFF);
		D3DTEXTURETRANSFORMFLAGS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DTEXTURETRANSFORMFLAGS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DTEXTURETRANSFORMFLAGS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DTRANSFORMSTATETYPE implements IntValuedEnum<D3DTRANSFORMSTATETYPE > {
		D3DTS_VIEW(2),
		D3DTS_PROJECTION(3),
		D3DTS_TEXTURE0(16),
		D3DTS_TEXTURE1(17),
		D3DTS_TEXTURE2(18),
		D3DTS_TEXTURE3(19),
		D3DTS_TEXTURE4(20),
		D3DTS_TEXTURE5(21),
		D3DTS_TEXTURE6(22),
		D3DTS_TEXTURE7(23),
		D3DTS_FORCE_DWORD(0x7FFFFFFF);
		D3DTRANSFORMSTATETYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DTRANSFORMSTATETYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DTRANSFORMSTATETYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DVERTEXBLENDFLAGS implements IntValuedEnum<D3DVERTEXBLENDFLAGS > {
		D3DVBF_DISABLE(0),
		D3DVBF_1WEIGHTS(1),
		D3DVBF_2WEIGHTS(2),
		D3DVBF_3WEIGHTS(3),
		D3DVBF_TWEENING(255),
		D3DVBF_0WEIGHTS(256);
		D3DVERTEXBLENDFLAGS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DVERTEXBLENDFLAGS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DVERTEXBLENDFLAGS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DZBUFFERTYPE implements IntValuedEnum<D3DZBUFFERTYPE > {
		D3DZB_FALSE(0),
		D3DZB_TRUE(1),
		D3DZB_USEW(2),
		D3DZB_FORCE_DWORD(0x7FFFFFFF);
		D3DZBUFFERTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DZBUFFERTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DZBUFFERTYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSAMPLERSTATETYPE implements IntValuedEnum<D3DSAMPLERSTATETYPE > {
		D3DSAMP_ADDRESSU(1),
		D3DSAMP_ADDRESSV(2),
		D3DSAMP_ADDRESSW(3),
		D3DSAMP_BORDERCOLOR(4),
		D3DSAMP_MAGFILTER(5),
		D3DSAMP_MINFILTER(6),
		D3DSAMP_MIPFILTER(7),
		D3DSAMP_MIPMAPLODBIAS(8),
		D3DSAMP_MAXMIPLEVEL(9),
		D3DSAMP_MAXANISOTROPY(10),
		D3DSAMP_SRGBTEXTURE(11),
		D3DSAMP_ELEMENTINDEX(12),
		D3DSAMP_DMAPOFFSET(13),
		D3DSAMP_FORCE_DWORD(0x7FFFFFFF);
		D3DSAMPLERSTATETYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSAMPLERSTATETYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSAMPLERSTATETYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSCANLINEORDERING implements IntValuedEnum<D3DSCANLINEORDERING > {
		D3DSCANLINEORDERING_UNKNOWN(0),
		D3DSCANLINEORDERING_PROGRESSIVE(1),
		D3DSCANLINEORDERING_INTERLACED(2);
		D3DSCANLINEORDERING(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSCANLINEORDERING > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSCANLINEORDERING > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DDISPLAYROTATION implements IntValuedEnum<D3DDISPLAYROTATION > {
		D3DDISPLAYROTATION_IDENTITY(1),
		D3DDISPLAYROTATION_90(2),
		D3DDISPLAYROTATION_180(3),
		D3DDISPLAYROTATION_270(4);
		D3DDISPLAYROTATION(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DDISPLAYROTATION > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DDISPLAYROTATION > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DCOMPOSERECTSOP implements IntValuedEnum<D3DCOMPOSERECTSOP > {
		D3DCOMPOSERECTS_COPY(1),
		D3DCOMPOSERECTS_OR(2),
		D3DCOMPOSERECTS_AND(3),
		D3DCOMPOSERECTS_NEG(4),
		D3DCOMPOSERECTS_FORCE_DWORD(0x7FFFFFFF);
		D3DCOMPOSERECTSOP(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DCOMPOSERECTSOP > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DCOMPOSERECTSOP > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public enum D3DSHADER_COMPARISON implements IntValuedEnum<D3DSHADER_COMPARISON > {
		D3DSPC_RESERVED0(0),
		D3DSPC_GT(1),
		D3DSPC_EQ(2),
		D3DSPC_GE(3),
		D3DSPC_LT(4),
		D3DSPC_NE(5),
		D3DSPC_LE(6),
		D3DSPC_RESERVED1(7);
		D3DSHADER_COMPARISON(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DSHADER_COMPARISON > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DSHADER_COMPARISON > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	public static final int D3DLOCK_NOOVERWRITE = 4096;
	public static final int D3DSP_WRITEMASK_0 = 65536;
	public static final int D3DFVF_PSIZE = 32;
	public static final int D3DSHADER_INSTRUCTION_PREDICATED = (1 << 28);
	public static final int D3DWRAP_V = 2;
	public static final int D3DWRAP_W = 4;
	public static final int D3DFVF_LASTBETA_D3DCOLOR = 32768;
	public static final int D3DWRAP_U = 1;
	public static final int D3DSP_WRITEMASK_3 = 524288;
	public static final int D3DUSAGE_AUTOGENMIPMAP = 1024;
	public static final int D3DSP_WRITEMASK_1 = 131072;
	public static final int D3DSP_WRITEMASK_2 = 262144;
	public static final int D3DSP_DCL_USAGEINDEX_MASK = 983040;
	public static final int D3DFVF_XYZW = 16386;
	public static final int D3DCLEAR_ZBUFFER = 2;
	public static final int D3DTSS_TCI_CAMERASPACENORMAL = 65536;
	public static final int D3DTA_SELECTMASK = 15;
	public static final int D3DUSAGE_QUERY_POSTPIXELSHADER_BLENDING = 524288;
	public static final int D3DTA_DIFFUSE = 0;
	public static final int D3DPRESENT_RATE_DEFAULT = 0;
	public static final int D3DUSAGE_QUERY_VERTEXTEXTURE = 1048576;
	public static final int D3DCS_FRONT = 16;
	public static final int D3DCOLORWRITEENABLE_ALPHA = (1 << 3);
	public static final int D3DSI_INSTLENGTH_SHIFT = 24;
	public static final int D3DLOCK_DISCARD = 8192;
	public static final int D3DFVF_NORMAL = 16;
	public static final int D3DUSAGE_POINTS = 64;
	public static final int D3DPRESENTFLAG_DEVICECLIP = 4;
	public static final int D3DUSAGE_NPATCHES = 256;
	public static final int D3DMAXUSERCLIPPLANES = 32;
	public static final int D3DCS_ALL = 4095;
	public static final int D3DGETDATA_FLUSH = (1 << 0);
	public static final int D3DPRESENTFLAG_DISCARD_DEPTHSTENCIL = 2;
	public static final int D3DVSD_NOP = 0;
	public static final int D3DFVF_SPECULAR = 128;
	public static final int D3DUSAGE_RENDERTARGET = 1;
	public static final int D3DTSS_TCI_CAMERASPACEPOSITION = 131072;
	public static final int D3DUSAGE_DYNAMIC = 512;
	public static final int D3DUSAGE_SOFTWAREPROCESSING = 16;
	public static final int D3DCS_PLANE2 = 256;
	public static final int D3DCS_PLANE3 = 512;
	public static final int D3DCS_PLANE4 = 1024;
	public static final int D3DSTREAMSOURCE_INSTANCEDATA = (2 << 30);
	public static final int D3DCS_PLANE5 = 2048;
	public static final int D3DSP_SRCMOD_SHIFT = 24;
	public static final int MAXD3DDECLLENGTH = 64;
	public static final int D3DUSAGE_DMAP = 16384;
	public static final int D3DLOCK_NO_DIRTY_UPDATE = 32768;
	public static final int D3DTSS_TCI_PASSTHRU = 0;
	public static final int D3DUSAGE_QUERY_WRAPANDMIP = 2097152;
	public static final int D3DCS_PLANE1 = 128;
	public static final int D3DCS_PLANE0 = 64;
	public static final int D3DFVF_XYZB2 = 8;
	public static final int D3DFVF_XYZB1 = 6;
	public static final int D3DSP_TEXTURETYPE_MASK = 2013265920;
	public static final int D3DFVF_XYZB5 = 14;
	public static final int D3DLOCK_DONOTWAIT = 16384;
	public static final int D3DFVF_XYZB4 = 12;
	public static final int D3DFVF_XYZB3 = 10;
	public static final int D3DRENDERSTATE_WRAPBIAS = 128;
	public static final int D3DISSUE_END = (1 << 0);
	public static final int D3DSP_REGNUM_MASK = 2047;
	public static final int D3DPS_END = 65535;
	public static final int D3DCLEAR_STENCIL = 4;
	public static final int D3DCS_BOTTOM = 8;
	public static final int D3DFVF_RESERVED2 = 24576;
	public static final int D3DFVF_RESERVED0 = 1;
	public static final int D3DLOCK_NOSYSLOCK = 2048;
	public static final int D3DUSAGE_WRITEONLY = 8;
	public static final int D3DVSD_END = -1;
	public static final int MAX_DEVICE_IDENTIFIER_STRING = 512;
	public static final int D3DFVF_DIFFUSE = 64;
	public static final int D3DUSAGE_DEPTHSTENCIL = 2;
	public static final int D3DSHADER_ADDRESSMODE_SHIFT = 13;
	public static final int D3DSP_DCL_USAGEINDEX_SHIFT = 16;
	public static final int D3DCS_RIGHT = 2;
	public static final int D3DSI_INSTLENGTH_MASK = 251658240;
	public static final int D3DSI_COISSUE = 1073741824;
	public static final int D3DTSS_TCI_SPHEREMAP = 262144;
	public static final int D3DFVF_XYZ = 2;
	public static final int D3DDP_MAXTEXCOORD = 8;
	public static final int D3DSP_DSTSHIFT_SHIFT = 24;
	public static final int D3DMAXDECLUSAGEINDEX = 15;
	public static final int D3DCOLORWRITEENABLE_RED = (1 << 0);
	public static final int D3DPRESENTFLAG_LOCKABLE_BACKBUFFER = 1;
	public static final int D3DSI_COMMENTSIZE_SHIFT = 16;
	public static final int D3DPRESENT_BACK_BUFFERS_MAX = 3;
	public static final int D3DPRESENT_BACK_BUFFER_MAX = 3;
	public static final int D3DSP_REGTYPE_SHIFT2 = 8;
	public static final int D3DSP_SWIZZLE_SHIFT = 16;
	public static final int D3DFVF_LASTBETA_UBYTE4 = 4096;
	public static final int D3DDMAPSAMPLER = 256;
	public static final int D3DSP_WRITEMASK_ALL = 983040;
	public static final int D3DSTREAMSOURCE_INDEXEDDATA = (1 << 30);
	public static final int D3DTA_CURRENT = 1;
	public static final int D3DFVF_TEXCOUNT_MASK = 3840;
	public static final int D3DFVF_TEX0 = 0;
	public static final int D3D_MAX_SIMULTANEOUS_RENDERTARGETS = 4;
	public static final int D3DFVF_TEXCOUNT_SHIFT = 8;
	public static final int D3DFVF_TEX6 = 1536;
	public static final int D3DFVF_TEX5 = 1280;
	public static final int D3DFVF_TEX8 = 2048;
	public static final int D3DFVF_TEX7 = 1792;
	public static final int D3DFVF_TEX2 = 512;
	public static final int D3DISSUE_BEGIN = (1 << 1);
	public static final int D3DFVF_TEX1 = 256;
	public static final int D3DFVF_TEX4 = 1024;
	public static final int D3DFVF_TEX3 = 768;
	public static final int D3DTA_TEMP = 5;
	public static final int D3DUSAGE_DONOTCLIP = 32;
	public static final int D3DSI_OPCODE_MASK = 65535;
	public static final int D3DUSAGE_QUERY_SRGBWRITE = 262144;
	public static final int D3DCOLORWRITEENABLE_BLUE = (1 << 2);
	public static final int D3DFVF_POSITION_MASK = 16398;
	public static final int D3DTA_TEXTURE = 2;
	public static final int D3DSP_REGTYPE_SHIFT = 28;
	public static final int D3DTA_CONSTANT = 6;
	public static final int D3DSI_TEXLD_BIAS = 131072;
	public static final int D3DCLEAR_TARGET = 1;
	public static final int D3DPV_DONOTCOPYDATA = (1 << 0);
	public static final int D3DCS_LEFT = 1;
	public static final int D3DVS_END = 65535;
	public static final int D3DMAXDECLLENGTH = 18;
	public static final int D3DSP_REGTYPE_MASK2 = 6144;
	public static final int D3DLOCK_READONLY = 16;
	public static final int D3DCS_TOP = 4;
	public static final int D3DTSS_TCI_CAMERASPACEREFLECTIONVECTOR = 196608;
	public static final int D3DSP_DCL_USAGE_MASK = 15;
	public static final int D3DSP_TEXTURETYPE_SHIFT = 27;
	public static final int D3DSI_TEXLD_PROJECT = 65536;
	public static final int D3DCLIPPLANE4 = (1 << 4);
	public static final int D3DCLIPPLANE3 = (1 << 3);
	public static final int D3DCLIPPLANE5 = (1 << 5);
	public static final int D3DCLIPPLANE0 = (1 << 0);
	public static final int D3DCLIPPLANE2 = (1 << 2);
	public static final int D3DCLIPPLANE1 = (1 << 1);
	public static final int D3DFVF_XYZRHW = 4;
	public static final int D3DUSAGE_RTPATCHES = 128;
	public static final int D3DTA_SPECULAR = 4;
	/**
	 * define<br>
	 * Conversion Error : -1.5500992E-6F, -2.170139E-5F, 0.0026041667F, 2.6041668E-4F<br>
	 * SKIPPED:<br>
	 * -1.5500992E-6F, -2.170139E-5F, 0.0026041667F, 2.6041668E-4F
	 */
	/**
	 * define<br>
	 * Conversion Error : -0.020833334F, -0.125F, 1.0F, 0.5F<br>
	 * SKIPPED:<br>
	 * -0.020833334F, -0.125F, 1.0F, 0.5F
	 */
	public static final int D3DPRESENTFLAG_UNPRUNEDMODE = 64;
	public static final int D3DTA_ALPHAREPLICATE = 32;
	public static final int MAXD3DDECLUSAGEINDEX = 15;
	public static final int D3DPRESENTFLAG_NOAUTOROTATE = 32;
	public static final int D3DCS_BACK = 32;
	public static final int D3DFVF_TEXTUREFORMAT4 = 2;
	public static final int D3DUSAGE_QUERY_LEGACYBUMPMAP = 32768;
	public static final int D3DSP_DSTMOD_SHIFT = 20;
	public static final int D3DTA_COMPLEMENT = 16;
	public static final int D3DTA_TFACTOR = 3;
	public static final int D3DWRAPCOORD_0 = 1;
	public static final int D3DSP_DCL_USAGE_SHIFT = 0;
	public static final int D3DPRESENTFLAG_VIDEO = 16;
	public static final int D3DVS_SWIZZLE_SHIFT = 16;
	public static final int D3DWRAPCOORD_3 = 8;
	public static final int D3DWRAPCOORD_2 = 4;
	public static final int D3DWRAPCOORD_1 = 2;
	public static final int D3DUSAGE_QUERY_FILTER = 131072;
	public static final int D3DFVF_TEXTUREFORMAT1 = 3;
	public static final int D3DUSAGE_QUERY_SRGBREAD = 65536;
	public static final int D3DFVF_TEXTUREFORMAT3 = 1;
	public static final int D3DFVF_TEXTUREFORMAT2 = 0;
	public static final int D3DVS_ADDRESSMODE_SHIFT = 13;
	public static final int D3DCOLORWRITEENABLE_GREEN = (1 << 1);
	public static final int D3DVS_W_X = (0 << (D3DVS_SWIZZLE_SHIFT + 6));
	public static final int D3DVS_W_W = (3 << (D3DVS_SWIZZLE_SHIFT + 6));
	public static final int D3DVS_W_Z = (2 << (D3DVS_SWIZZLE_SHIFT + 6));
	public static final int D3DSHADER_ADDRESSMODE_MASK = (1 << D3DSHADER_ADDRESSMODE_SHIFT);
	public static final int D3DVS_W_Y = (1 << (D3DVS_SWIZZLE_SHIFT + 6));
	public static final int D3DSI_COMMENTSIZE_MASK = (32767 << D3DSI_COMMENTSIZE_SHIFT);
	public static final int D3DVS_ADDRESSMODE_MASK = (1 << D3DVS_ADDRESSMODE_SHIFT);
	public static final int D3DSP_REGTYPE_MASK = (7 << D3DSP_REGTYPE_SHIFT);
	public static final int D3DSP_DSTSHIFT_MASK = (15 << D3DSP_DSTSHIFT_SHIFT);
	public static final int D3DSP_SWIZZLE_MASK = (255 << D3DSP_SWIZZLE_SHIFT);
	public static final int D3DSP_NOSWIZZLE = ((0 << (D3DSP_SWIZZLE_SHIFT + 0)) | (1 << (D3DSP_SWIZZLE_SHIFT + 2)) | (2 << (D3DSP_SWIZZLE_SHIFT + 4)) | (3 << (D3DSP_SWIZZLE_SHIFT + 6)));
	public static final int D3DSP_SRCMOD_MASK = (15 << D3DSP_SRCMOD_SHIFT);
	public static final int D3DVS_Z_Y = (1 << (D3DVS_SWIZZLE_SHIFT + 4));
	public static final int D3DVS_Z_X = (0 << (D3DVS_SWIZZLE_SHIFT + 4));
	public static final int D3DVS_Z_W = (3 << (D3DVS_SWIZZLE_SHIFT + 4));
	public static final int D3DSP_DSTMOD_MASK = (15 << D3DSP_DSTMOD_SHIFT);
	public static final int D3DVS_SWIZZLE_MASK = (255 << D3DVS_SWIZZLE_SHIFT);
	public static final int D3DVS_Z_Z = (2 << (D3DVS_SWIZZLE_SHIFT + 4));
	public static final int D3DVERTEXTEXTURESAMPLER3 = (D3DDMAPSAMPLER + 4);
	public static final int D3DVERTEXTEXTURESAMPLER2 = (D3DDMAPSAMPLER + 3);
	public static final int D3DVERTEXTEXTURESAMPLER1 = (D3DDMAPSAMPLER + 2);
	public static final int D3DVERTEXTEXTURESAMPLER0 = (D3DDMAPSAMPLER + 1);
	public static final int D3DVS_X_Z = (2 << D3DVS_SWIZZLE_SHIFT);
	public static final int D3DVS_X_X = (0 << D3DVS_SWIZZLE_SHIFT);
	public static final int D3DVS_X_Y = (1 << D3DVS_SWIZZLE_SHIFT);
	public static final int D3DVS_Y_Z = (2 << (D3DVS_SWIZZLE_SHIFT + 2));
	public static final int D3DVS_Y_Y = (1 << (D3DVS_SWIZZLE_SHIFT + 2));
	public static final int D3DVS_X_W = (3 << D3DVS_SWIZZLE_SHIFT);
	public static final int D3DVS_Y_X = (0 << (D3DVS_SWIZZLE_SHIFT + 2));
	public static final int D3DVS_Y_W = (3 << (D3DVS_SWIZZLE_SHIFT + 2));
	
	@Convention(Style.StdCall)
	public static final native Pointer<IDirect3D9> Direct3DCreate9(int SDKVersion);

    public static final D3DPRESENT_PARAMETERS CreatePresentationParameters(int width, int height, boolean windowed) {
        D3DPRESENT_PARAMETERS d3dpp = new D3DPRESENT_PARAMETERS();
        d3dpp.Windowed(windowed ? 1 : 0);
        d3dpp.SwapEffect(D3DSWAPEFFECT_DISCARD);
        d3dpp.BackBufferCount(1);
        d3dpp.BackBufferFormat(D3DFMT_UNKNOWN);
        d3dpp.BackBufferWidth(width);
        d3dpp.BackBufferHeight(height);
        d3dpp.EnableAutoDepthStencil(0);
        d3dpp.MultiSampleType(D3DMULTISAMPLE_NONE);
        d3dpp.MultiSampleQuality(0);
        d3dpp.FullScreen_RefreshRateInHz(D3DPRESENT_RATE_DEFAULT);
        d3dpp.PresentationInterval(D3DPRESENT_INTERVAL_DEFAULT);
        d3dpp.Flags(0);

        return d3dpp;
    }
}
