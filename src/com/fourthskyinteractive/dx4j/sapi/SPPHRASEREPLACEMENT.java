package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("SAPI") 
public class SPPHRASEREPLACEMENT extends StructObject {
	public SPPHRASEREPLACEMENT() {
		super();
	}
	@Field(0) 
	public byte bDisplayAttributes() {
		return this.io.getByteField(this, 0);
	}
	@Field(0) 
	public SPPHRASEREPLACEMENT bDisplayAttributes(byte bDisplayAttributes) {
		this.io.setByteField(this, 0, bDisplayAttributes);
		return this;
	}
	/// C type : LPCWSTR
	@Field(1) 
	public Pointer<Character > pszReplacementText() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : LPCWSTR
	@Field(1) 
	public SPPHRASEREPLACEMENT pszReplacementText(Pointer<Character > pszReplacementText) {
		this.io.setPointerField(this, 1, pszReplacementText);
		return this;
	}
	@CLong 
	@Field(2) 
	public long ulFirstElement() {
		return this.io.getCLongField(this, 2);
	}
	@CLong 
	@Field(2) 
	public SPPHRASEREPLACEMENT ulFirstElement(long ulFirstElement) {
		this.io.setCLongField(this, 2, ulFirstElement);
		return this;
	}
	@CLong 
	@Field(3) 
	public long ulCountOfElements() {
		return this.io.getCLongField(this, 3);
	}
	@CLong 
	@Field(3) 
	public SPPHRASEREPLACEMENT ulCountOfElements(long ulCountOfElements) {
		this.io.setCLongField(this, 3, ulCountOfElements);
		return this;
	}
	public SPPHRASEREPLACEMENT(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
