package com.fourthskyinteractive.dx4j.d3dcompiler;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Convention;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_INCLUDE_TYPE;

@Library("d3dcompiler_43")
@Convention(Convention.Style.StdCall)
public class ID3DInclude extends StructObject {

	@Field(0)
	public Pointer<OpenCB> Open() {
		return this.io.getPointerField(this, 0);
	}
	
	@Field(1)
	public Pointer<OpenCB> Close() {
		return this.io.getPointerField(this, 1);
	}
	
    public static abstract class OpenCB extends Callback<OpenCB> {
    	public abstract int apply(D3D_INCLUDE_TYPE IncludeType, Pointer<Byte> pFileName, Pointer<?> pParentData, Pointer<Pointer<?>> ppData, Pointer<Integer> pBytes);
    };
    
    public static abstract class CloseCB extends Callback<CloseCB> {
    	public abstract int apply(Pointer<?> pData);
    };
    
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
     */
}
