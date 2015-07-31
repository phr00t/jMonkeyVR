package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("SAPI") 
public class SPPHRASE extends StructObject {
	public SPPHRASE() {
		super();
	}
	@CLong 
	@Field(0) 
	public long cbSize() {
		return this.io.getCLongField(this, 0);
	}
	@CLong 
	@Field(0) 
	public SPPHRASE cbSize(long cbSize) {
		this.io.setCLongField(this, 0, cbSize);
		return this;
	}
	@Field(1) 
	public short LangID() {
		return this.io.getShortField(this, 1);
	}
	@Field(1) 
	public SPPHRASE LangID(short LangID) {
		this.io.setShortField(this, 1, LangID);
		return this;
	}
	@Field(2) 
	public short wHomophoneGroupId() {
		return this.io.getShortField(this, 2);
	}
	@Field(2) 
	public SPPHRASE wHomophoneGroupId(short wHomophoneGroupId) {
		this.io.setShortField(this, 2, wHomophoneGroupId);
		return this;
	}
	@Field(3) 
	public long ullGrammarID() {
		return this.io.getLongField(this, 3);
	}
	@Field(3) 
	public SPPHRASE ullGrammarID(long ullGrammarID) {
		this.io.setLongField(this, 3, ullGrammarID);
		return this;
	}
	@Field(4) 
	public long ftStartTime() {
		return this.io.getLongField(this, 4);
	}
	@Field(4) 
	public SPPHRASE ftStartTime(long ftStartTime) {
		this.io.setLongField(this, 4, ftStartTime);
		return this;
	}
	@Field(5) 
	public long ullAudioStreamPosition() {
		return this.io.getLongField(this, 5);
	}
	@Field(5) 
	public SPPHRASE ullAudioStreamPosition(long ullAudioStreamPosition) {
		this.io.setLongField(this, 5, ullAudioStreamPosition);
		return this;
	}
	@CLong 
	@Field(6) 
	public long ulAudioSizeBytes() {
		return this.io.getCLongField(this, 6);
	}
	@CLong 
	@Field(6) 
	public SPPHRASE ulAudioSizeBytes(long ulAudioSizeBytes) {
		this.io.setCLongField(this, 6, ulAudioSizeBytes);
		return this;
	}
	@CLong 
	@Field(7) 
	public long ulRetainedSizeBytes() {
		return this.io.getCLongField(this, 7);
	}
	@CLong 
	@Field(7) 
	public SPPHRASE ulRetainedSizeBytes(long ulRetainedSizeBytes) {
		this.io.setCLongField(this, 7, ulRetainedSizeBytes);
		return this;
	}
	@CLong 
	@Field(8) 
	public long ulAudioSizeTime() {
		return this.io.getCLongField(this, 8);
	}
	@CLong 
	@Field(8) 
	public SPPHRASE ulAudioSizeTime(long ulAudioSizeTime) {
		this.io.setCLongField(this, 8, ulAudioSizeTime);
		return this;
	}
	/// C type : SPPHRASERULE
	@Field(9) 
	public SPPHRASERULE Rule() {
		return this.io.getNativeObjectField(this, 9);
	}
	/// C type : SPPHRASERULE
	@Field(9) 
	public SPPHRASE Rule(SPPHRASERULE Rule) {
		this.io.setNativeObjectField(this, 9, Rule);
		return this;
	}
	/// C type : const SPPHRASEPROPERTY*
	@Field(10) 
	public Pointer<SPPHRASEPROPERTY > pProperties() {
		return this.io.getPointerField(this, 10);
	}
	/// C type : const SPPHRASEPROPERTY*
	@Field(10) 
	public SPPHRASE pProperties(Pointer<SPPHRASEPROPERTY > pProperties) {
		this.io.setPointerField(this, 10, pProperties);
		return this;
	}
	/// C type : const SPPHRASEELEMENT*
	@Field(11) 
	public Pointer<SPPHRASEELEMENT > pElements() {
		return this.io.getPointerField(this, 11);
	}
	/// C type : const SPPHRASEELEMENT*
	@Field(11) 
	public SPPHRASE pElements(Pointer<SPPHRASEELEMENT > pElements) {
		this.io.setPointerField(this, 11, pElements);
		return this;
	}
	@CLong 
	@Field(12) 
	public long cReplacements() {
		return this.io.getCLongField(this, 12);
	}
	@CLong 
	@Field(12) 
	public SPPHRASE cReplacements(long cReplacements) {
		this.io.setCLongField(this, 12, cReplacements);
		return this;
	}
	/// C type : const SPPHRASEREPLACEMENT*
	@Field(13) 
	public Pointer<SPPHRASEREPLACEMENT > pReplacements() {
		return this.io.getPointerField(this, 13);
	}
	/// C type : const SPPHRASEREPLACEMENT*
	@Field(13) 
	public SPPHRASE pReplacements(Pointer<SPPHRASEREPLACEMENT > pReplacements) {
		this.io.setPointerField(this, 13, pReplacements);
		return this;
	}
	/// C type : GUID
	@Field(14) 
	public Pointer<Byte > SREngineID() {
		return this.io.getPointerField(this, 14);
	}
	/// C type : GUID
	@Field(14) 
	public SPPHRASE SREngineID(Pointer<Byte > SREngineID) {
		this.io.setPointerField(this, 14, SREngineID);
		return this;
	}
	@CLong 
	@Field(15) 
	public long ulSREnginePrivateDataSize() {
		return this.io.getCLongField(this, 15);
	}
	@CLong 
	@Field(15) 
	public SPPHRASE ulSREnginePrivateDataSize(long ulSREnginePrivateDataSize) {
		this.io.setCLongField(this, 15, ulSREnginePrivateDataSize);
		return this;
	}
	/// C type : const BYTE*
	@Field(16) 
	public Pointer<Byte > pSREnginePrivateData() {
		return this.io.getPointerField(this, 16);
	}
	/// C type : const BYTE*
	@Field(16) 
	public SPPHRASE pSREnginePrivateData(Pointer<Byte > pSREnginePrivateData) {
		this.io.setPointerField(this, 16, pSREnginePrivateData);
		return this;
	}
	/// C type : LPWSTR
	@Field(17) 
	public Pointer<Character > pSML() {
		return this.io.getPointerField(this, 17);
	}
	/// C type : LPWSTR
	@Field(17) 
	public SPPHRASE pSML(Pointer<Character > pSML) {
		this.io.setPointerField(this, 17, pSML);
		return this;
	}
	/// C type : SPSEMANTICERRORINFO*
	@Field(18) 
	public Pointer<SPSEMANTICERRORINFO > pSemanticErrorInfo() {
		return this.io.getPointerField(this, 18);
	}
	/// C type : SPSEMANTICERRORINFO*
	@Field(18) 
	public SPPHRASE pSemanticErrorInfo(Pointer<SPSEMANTICERRORINFO > pSemanticErrorInfo) {
		this.io.setPointerField(this, 18, pSemanticErrorInfo);
		return this;
	}
	public SPPHRASE(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}

