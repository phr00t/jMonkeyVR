package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("SAPI") 
public class SPPHRASERULE extends StructObject {
	public SPPHRASERULE() {
		super();
	}
	/// C type : LPCWSTR
	@Field(0) 
	public Pointer<Character > pszName() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : LPCWSTR
	@Field(0) 
	public SPPHRASERULE pszName(Pointer<Character > pszName) {
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
	public SPPHRASERULE ulId(long ulId) {
		this.io.setCLongField(this, 1, ulId);
		return this;
	}
	@CLong 
	@Field(2) 
	public long ulFirstElement() {
		return this.io.getCLongField(this, 2);
	}
	@CLong 
	@Field(2) 
	public SPPHRASERULE ulFirstElement(long ulFirstElement) {
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
	public SPPHRASERULE ulCountOfElements(long ulCountOfElements) {
		this.io.setCLongField(this, 3, ulCountOfElements);
		return this;
	}
	/// C type : const SPPHRASERULE*
	@Field(4) 
	public Pointer<SPPHRASERULE > pNextSibling() {
		return this.io.getPointerField(this, 4);
	}
	/// C type : const SPPHRASERULE*
	@Field(4) 
	public SPPHRASERULE pNextSibling(Pointer<SPPHRASERULE > pNextSibling) {
		this.io.setPointerField(this, 4, pNextSibling);
		return this;
	}
	/// C type : const SPPHRASERULE*
	@Field(5) 
	public Pointer<SPPHRASERULE > pFirstChild() {
		return this.io.getPointerField(this, 5);
	}
	/// C type : const SPPHRASERULE*
	@Field(5) 
	public SPPHRASERULE pFirstChild(Pointer<SPPHRASERULE > pFirstChild) {
		this.io.setPointerField(this, 5, pFirstChild);
		return this;
	}
	@Field(6) 
	public float SREngineConfidence() {
		return this.io.getFloatField(this, 6);
	}
	@Field(6) 
	public SPPHRASERULE SREngineConfidence(float SREngineConfidence) {
		this.io.setFloatField(this, 6, SREngineConfidence);
		return this;
	}
	@Field(7) 
	public byte Confidence() {
		return this.io.getByteField(this, 7);
	}
	@Field(7) 
	public SPPHRASERULE Confidence(byte Confidence) {
		this.io.setByteField(this, 7, Confidence);
		return this;
	}
	public SPPHRASERULE(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
