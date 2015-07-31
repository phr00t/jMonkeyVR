package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.sapi.SAPI.SPINTERFERENCE;

@Library("SAPI") 
public class SPRECOCONTEXTSTATUS extends StructObject {
	public SPRECOCONTEXTSTATUS() {
		super();
	}
	/// C type : SPINTERFERENCE
	@Field(0) 
	public IntValuedEnum<SPINTERFERENCE > eInterference() {
		return this.io.getEnumField(this, 0);
	}
	/// C type : SPINTERFERENCE
	@Field(0) 
	public SPRECOCONTEXTSTATUS eInterference(IntValuedEnum<SPINTERFERENCE > eInterference) {
		this.io.setEnumField(this, 0, eInterference);
		return this;
	}
	/// C type : WCHAR[255]
	@Array({255}) 
	@Field(1) 
	public Pointer<Short > szRequestTypeOfUI() {
		return this.io.getPointerField(this, 1);
	}
	@Field(2) 
	public int dwReserved1() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public SPRECOCONTEXTSTATUS dwReserved1(int dwReserved1) {
		this.io.setIntField(this, 2, dwReserved1);
		return this;
	}
	@Field(3) 
	public int dwReserved2() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public SPRECOCONTEXTSTATUS dwReserved2(int dwReserved2) {
		this.io.setIntField(this, 3, dwReserved2);
		return this;
	}
	public SPRECOCONTEXTSTATUS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
