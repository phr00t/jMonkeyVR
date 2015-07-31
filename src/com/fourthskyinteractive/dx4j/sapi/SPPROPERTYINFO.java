package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.cpp.com.VARIANT;

@Library("SAPI") 
public class SPPROPERTYINFO extends StructObject {
	public SPPROPERTYINFO() {
		super();
	}
	/// C type : LPCWSTR
	@Field(0) 
	public Pointer<Character > pszName() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : LPCWSTR
	@Field(0) 
	public SPPROPERTYINFO pszName(Pointer<Character > pszName) {
		this.io.setPointerField(this, 0, pszName);
		return this;
	}
	@CLong 
	@Field(1) 
	public long ulId() {
		return this.io.getCLongField(this, 1);
	}
	@CLong 
	@Field(1) 
	public SPPROPERTYINFO ulId(long ulId) {
		this.io.setCLongField(this, 1, ulId);
		return this;
	}
	/// C type : LPCWSTR
	@Field(2) 
	public Pointer<Character > pszValue() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : LPCWSTR
	@Field(2) 
	public SPPROPERTYINFO pszValue(Pointer<Character > pszValue) {
		this.io.setPointerField(this, 2, pszValue);
		return this;
	}
	public SPPROPERTYINFO(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	
	@Field(3)
	public VARIANT vValue() {
		return this.io.getNativeObjectField(this, 3);
	}
}

