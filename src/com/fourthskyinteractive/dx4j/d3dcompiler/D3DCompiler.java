package com.fourthskyinteractive.dx4j.d3dcompiler;

import static org.bridj.Pointer.allocateArray;
import static org.bridj.Pointer.allocatePointer;
import static org.bridj.Pointer.pointerTo;
import static org.bridj.Pointer.pointerToCString;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.SizeT;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D10Blob;
import com.fourthskyinteractive.dx4j.d3d11.shader.D3D_SHADER_MACRO;

@Library("d3dcompiler_43") 
@Runtime(COMRuntime.class)
public class D3DCompiler {

	static {
		BridJ.register();
	}
	public enum D3DCOMPILER_STRIP_FLAGS implements IntValuedEnum<D3DCOMPILER_STRIP_FLAGS > {
		D3DCOMPILER_STRIP_REFLECTION_DATA(1),
		D3DCOMPILER_STRIP_DEBUG_INFO(2),
		D3DCOMPILER_STRIP_TEST_BLOBS(4),
		D3DCOMPILER_STRIP_FORCE_DWORD(0x7FFFFFFF);
		D3DCOMPILER_STRIP_FLAGS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3DCOMPILER_STRIP_FLAGS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3DCOMPILER_STRIP_FLAGS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D3D_BLOB_PART implements IntValuedEnum<D3D_BLOB_PART > {
		D3D_BLOB_INPUT_SIGNATURE_BLOB(0),
		D3D_BLOB_OUTPUT_SIGNATURE_BLOB(1),
		D3D_BLOB_INPUT_AND_OUTPUT_SIGNATURE_BLOB(2),
		D3D_BLOB_PATCH_CONSTANT_SIGNATURE_BLOB(3),
		D3D_BLOB_ALL_SIGNATURE_BLOB(4),
		D3D_BLOB_DEBUG_INFO(5),
		D3D_BLOB_LEGACY_SHADER(6),
		D3D_BLOB_XNA_PREPASS_SHADER(7),
		D3D_BLOB_XNA_SHADER(8),
		D3D_BLOB_TEST_ALTERNATE_SHADER(0x8000),
		D3D_BLOB_TEST_COMPILE_DETAILS(32769),
		D3D_BLOB_TEST_COMPILE_PERF(32770);
		D3D_BLOB_PART(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D3D_BLOB_PART > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D3D_BLOB_PART > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}

    public static final int D3D_DISASM_ENABLE_COLOR_CODE = (int)1;
    public static final int D3D_DISASM_ENABLE_DEFAULT_VALUE_PRINTS = (int)2;
    public static final int D3D_DISASM_ENABLE_INSTRUCTION_NUMBERING = (int)4;
    public static final int D3D_DISASM_ENABLE_INSTRUCTION_CYCLE = (int)8;
    public static final int D3D_DISASM_DISABLE_DEBUG_INFO = (int)16;

    public static final int D3DCOMPILE_OPTIMIZATION_LEVEL0 = (int)(1 << 14);
    public static final int D3DCOMPILE_OPTIMIZATION_LEVEL1 = (int)0;
    public static final int D3DCOMPILE_SKIP_VALIDATION = (int)(1 << 1);
    public static final int D3DCOMPILE_SKIP_OPTIMIZATION = (int)(1 << 2);
	public static final int D3DCOMPILE_FORCE_VS_SOFTWARE_NO_OPT = (int)(1 << 6);
	public static final int D3DCOMPILE_PARTIAL_PRECISION = (int)(1 << 5);
	public static final int D3DCOMPILE_IEEE_STRICTNESS = (int)(1 << 13);
	public static final int D3DCOMPILE_NO_PRESHADER = (int)(1 << 8);
	public static final int D3DCOMPILE_OPTIMIZATION_LEVEL3 = (int)(1 << 15);
	public static final int D3DCOMPILE_FORCE_PS_SOFTWARE_NO_OPT = (int)(1 << 7);
	public static final int D3DCOMPILE_RESERVED17 = (int)(1 << 17);
	public static final int D3DCOMPILE_DEBUG = (int)(1 << 0);
	public static final int D3DCOMPILE_RESERVED16 = (int)(1 << 16);
	public static final int D3DCOMPILE_ENABLE_STRICTNESS = (int)(1 << 11);
	public static final int D3DCOMPILE_ENABLE_BACKWARDS_COMPATIBILITY = (int)(1 << 12);
	public static final int D3DCOMPILE_AVOID_FLOW_CONTROL = (int)(1 << 9);
	public static final int D3DCOMPILE_PACK_MATRIX_COLUMN_MAJOR = (int)(1 << 4);
	public static final int D3DCOMPILE_OPTIMIZATION_LEVEL2 = (int)((1 << 14) | (1 << 15));
    public static final int D3DCOMPILE_WARNINGS_ARE_ERRORS = (int)(1 << 18);
	public static final int D3DCOMPILE_PACK_MATRIX_ROW_MAJOR = (int)(1 << 3);
	public static final int D3DCOMPILE_PREFER_FLOW_CONTROL = (int)(1 << 10);
	
	/**
	 * Compile source text into bytecode appropriate for the given target.
	 * @param pSrcData
	 * @param SrcDataSize
	 * @param pSourceName
	 * @param pDefines
	 * @param pInclude
	 * @param pEntrypoint
	 * @param pTarget
	 * @param Flags1
	 * @param Flags2
	 * @param ppCode
	 * @param ppErrorMsgs
	 * @return
	 */
	@Deprecated
	public static native int D3DCompile(Pointer<?> pSrcData,
										SizeT SrcDataSize,
										Pointer<Byte> pSourceName,
										Pointer<D3D_SHADER_MACRO> pDefines,
										Pointer<? extends ID3DInclude> pInclude,
										Pointer<Byte> pEntrypoint,
										Pointer<Byte> pTarget,
										int Flags1,
										int Flags2,
										Pointer<Pointer<ID3D10Blob>> ppCode,
										Pointer<Pointer<ID3D10Blob>> ppErrorMsgs);
	/**
	 * Shader code contains metadata that can be inspected via the reflection APIs.
	 * @param pSrcData
	 * @param SrcDataSize
	 * @param pInterface
	 * @param ppReflector
	 * @return
	 */
	@Deprecated
	public static native int D3DReflect(Pointer<?> pSrcData, long SrcDataSize, Pointer<Byte> pInterface, Pointer<Pointer<?>> ppReflector);
	
	/**
	 * Compresses a set of shaders into a more compact form.
	 * @param uNumShaders
	 * @param pShaderData
	 * @param uFlags
	 * @param ppCompressedData
	 * @return
	 */
	public static native int D3DCompressShaders(int uNumShaders, Pointer<D3D_SHADER_DATA> pShaderData, int uFlags, Pointer<Pointer<ID3D10Blob>> ppCompressedData);
	
	/**
	 * Create an ID3DBlob instance.
	 * @param size
	 * @param ppBlob
	 * @return
	 */
	public static native int D3DCreateBlob(long size, Pointer<Pointer<ID3D10Blob>> ppBlob);
	
	
	// "Javanized" methods
    public static final ID3D10Blob D3DCompile(String shaderCode,
                                              String entrypoint,
                                              String target,
                                              int Flags1,
                                              int Flags2) throws D3DCompilerException {
        return D3DCompile(shaderCode, null, null, null, entrypoint, target, Flags1, Flags2);
    }

	public static final ID3D10Blob D3DCompile(String shaderCode,
											 String sourceName,
											 D3D_SHADER_MACRO[] defines,
											 ID3DInclude includeHandler,
											 String entrypoint,
											 String target,
											 int Flags1,
											 int Flags2) throws D3DCompilerException {
		Pointer<Pointer<ID3D10Blob>> ppCode = allocatePointer(ID3D10Blob.class);
		Pointer<Pointer<ID3D10Blob>> ppErrors = allocatePointer(ID3D10Blob.class);
		Pointer<D3D_SHADER_MACRO> pMacros = null;
		
		try {
			if(defines != null) {
				pMacros = allocateArray(D3D_SHADER_MACRO.class, defines.length);
				for(int i = 0; i < defines.length; i++) {
					pMacros.get(i).Definition(defines[i].Definition())
								  .Name(defines[i].Name());
				}
			}
			
			int result = D3DCompile(shaderCode != null ? pointerToCString(shaderCode) : null, 
									shaderCode != null ? new SizeT(shaderCode.length()) : new SizeT(0L),
									sourceName != null ? pointerToCString(sourceName) : null, 
									pMacros, 
									includeHandler != null ? pointerTo(includeHandler) : null, 
									pointerToCString(entrypoint), 
									pointerToCString(target), 
									Flags1, 
									Flags2, 
									ppCode, 
									ppErrors);
			if(result != 0) {
				ID3D10Blob errors = ppErrors.get().getNativeObject(ID3D10Blob.class);
				if(errors != null) {
					//Pointer<Byte> pMessage = allocateArray(Byte.class, errors.GetBufferSize());
					//errors.GetBufferPointer().copyBytesTo(pMessage, errors.GetBufferSize());
					
					String message = errors.GetBufferPointer().getCString();
					errors.Release();
					throw new D3DCompilerException(message, result);
				}
			}
			
			return ppCode.get().getNativeObject(ID3D10Blob.class);
		
		} finally {
			ppCode.release();
			ppErrors.release();
			ppCode = null;
			ppErrors = null;
			
			if (pMacros != null) {
				pMacros.release();
				pMacros = null;
			}
		}
	}
	
	public ID3D11ShaderReflection D3DReflect(ID3D10Blob compiledShader) throws D3DCompilerException {
		Pointer<Pointer<?>> pp = allocatePointer();
		
		try {
			int result = D3DReflect(compiledShader.GetBufferPointer(), 
									compiledShader.GetBufferSize(), 
									COMRuntime.getIID(ID3D11ShaderReflection.class), 
									pp);
			if (result != 0) {
				throw new D3DCompilerException("Could not obtain shader metadata", result);
			}
			
			return pp.get().getNativeObject(ID3D11ShaderReflection.class);
		} finally {
			pp.release();
			pp = null;
		}
	}
	
	/*
	static class ID3DIncludeVtbl extends StructObject {
		ID3DIncludeVtbl() {
			super();
		}
		ID3DIncludeVtbl(Pointer<? extends StructObject> pointer) {
			super(pointer);
		}
		
		@Field(0)
		public Pointer<ID3DIncludeVtbl.OpenCB> Open() {
			return this.io.getPointerField(this, 0);
		}
		@Field(0)
		public ID3DIncludeVtbl Open(Pointer<OpenCB> Open) {
			this.io.setPointerField(this, 0, Open);
			return this;
		}
		@Field(1)
		public Pointer<ID3DIncludeVtbl.CloseCB> Close() {
			return this.io.getPointerField(this, 1);
		}
		@Field(1)
		public ID3DIncludeVtbl Close(Pointer<CloseCB> Close) {
			this.io.setPointerField(this, 1, Close);
			return this;
		}
		
		//Callbacks that must be implemented
	    @Convention(Convention.Style.StdCall)
	    public static abstract class OpenCB extends Callback<OpenCB> {
	            public abstract int apply(Pointer<ID3DInclude> This, D3D_INCLUDE_TYPE IncludeType, Pointer<Byte > pFileName, Pointer<? > pParentData, Pointer<Pointer<? > > ppData, Pointer<Integer > pBytes);
	    };
	    @Convention(Convention.Style.StdCall)
	    public static abstract class CloseCB extends Callback<CloseCB> {
	            public abstract int apply(Pointer<ID3DInclude> This, Pointer<?> pData);
	    };
	};

	public static abstract class ID3DInclude extends CPPObject {
		// Creating virtual table
		private final ID3DIncludeVtbl vTable = new ID3DIncludeVtbl();
		
		private final ID3DIncludeVtbl.OpenCB openCB = new ID3DIncludeVtbl.OpenCB() {
			@Override
			public int apply(Pointer<ID3DInclude> This,
					D3D_INCLUDE_TYPE IncludeType, Pointer<Byte> pFileName,
					Pointer<?> pParentData, Pointer<Pointer<?>> ppData,
					Pointer<Integer> pBytes) {
				return Open(IncludeType, pFileName, pParentData, ppData, pBytes);				
			}
		};
		private final ID3DIncludeVtbl.CloseCB closeCB = new ID3DIncludeVtbl.CloseCB() {
			@Override
			public int apply(Pointer<ID3DInclude> This, Pointer<?> pData) {
				return Close(pData);
			}
		};
		
		public ID3DInclude() {
			super();
			
			// Creating virtual table
			vTable.Open(pointerTo(openCB));
			vTable.Close(pointerTo(closeCB));
			this.lpVtbl(pointerTo(vTable));
		}
		public ID3DInclude(Pointer<? extends CPPObject> pointer) {
			super(pointer);
			
			// Creating virtual table
			vTable.Open(pointerTo(openCB));
			vTable.Close(pointerTo(closeCB));
			this.lpVtbl(pointerTo(vTable));
		}
		/// C type : ISampleGrabberCBVtbl*
        @SuppressWarnings("unused")
		@Field(0) 
        private Pointer<ID3DIncludeVtbl > lpVtbl() {
            return this.io.getPointerField(this, 0);
        }
        /// C type : ISampleGrabberCBVtbl*
        @Field(0) 
        private ID3DInclude lpVtbl(Pointer<ID3DIncludeVtbl > lpVtbl) {
            this.io.setPointerField(this, 0, lpVtbl);
            return this;
        }
        
        public abstract int Open(D3D_INCLUDE_TYPE IncludeType, Pointer<Byte > pFileName, Pointer<? > pParentData, Pointer<Pointer<? > > ppData, Pointer<Integer > pBytes);
        
        public abstract int Close(Pointer<?> pData);
	}
	*/
}
