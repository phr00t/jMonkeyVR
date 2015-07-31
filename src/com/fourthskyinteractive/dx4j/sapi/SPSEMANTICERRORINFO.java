package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("sapi") 
public class SPSEMANTICERRORINFO extends StructObject {
	public SPSEMANTICERRORINFO() {
		super();
	}
	@CLong 
	@Field(0) 
	public long ulLineNumber() {
		return this.io.getCLongField(this, 0);
	}
	@CLong 
	@Field(0) 
	public SPSEMANTICERRORINFO ulLineNumber(long ulLineNumber) {
		this.io.setCLongField(this, 0, ulLineNumber);
		return this;
	}
	/// C type : LPWSTR
	@Field(1) 
	public Pointer<Character > pszScriptLine() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : LPWSTR
	@Field(1) 
	public SPSEMANTICERRORINFO pszScriptLine(Pointer<Character > pszScriptLine) {
		this.io.setPointerField(this, 1, pszScriptLine);
		return this;
	}
	/// C type : LPWSTR
	@Field(2) 
	public Pointer<Character > pszSource() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : LPWSTR
	@Field(2) 
	public SPSEMANTICERRORINFO pszSource(Pointer<Character > pszSource) {
		this.io.setPointerField(this, 2, pszSource);
		return this;
	}
	/// C type : LPWSTR
	@Field(3) 
	public Pointer<Character > pszDescription() {
		return this.io.getPointerField(this, 3);
	}
	/// C type : LPWSTR
	@Field(3) 
	public SPSEMANTICERRORINFO pszDescription(Pointer<Character > pszDescription) {
		this.io.setPointerField(this, 3, pszDescription);
		return this;
	}
	/// C type : HRESULT
	@CLong 
	@Field(4) 
	public long hrResultCode() {
		return this.io.getCLongField(this, 4);
	}
	/// C type : HRESULT
	@CLong 
	@Field(4) 
	public SPSEMANTICERRORINFO hrResultCode(long hrResultCode) {
		this.io.setCLongField(this, 4, hrResultCode);
		return this;
	}
	public SPSEMANTICERRORINFO(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}

