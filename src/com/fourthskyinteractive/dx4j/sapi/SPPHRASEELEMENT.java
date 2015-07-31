package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("SAPI") 
public class SPPHRASEELEMENT extends StructObject {
	public SPPHRASEELEMENT() {
		super();
	}
	@CLong 
	@Field(0) 
	public long ulAudioTimeOffset() {
		return this.io.getCLongField(this, 0);
	}
	@CLong 
	@Field(0) 
	public SPPHRASEELEMENT ulAudioTimeOffset(long ulAudioTimeOffset) {
		this.io.setCLongField(this, 0, ulAudioTimeOffset);
		return this;
	}
	@CLong 
	@Field(1) 
	public long ulAudioSizeTime() {
		return this.io.getCLongField(this, 1);
	}
	@CLong 
	@Field(1) 
	public SPPHRASEELEMENT ulAudioSizeTime(long ulAudioSizeTime) {
		this.io.setCLongField(this, 1, ulAudioSizeTime);
		return this;
	}
	@CLong 
	@Field(2) 
	public long ulAudioStreamOffset() {
		return this.io.getCLongField(this, 2);
	}
	@CLong 
	@Field(2) 
	public SPPHRASEELEMENT ulAudioStreamOffset(long ulAudioStreamOffset) {
		this.io.setCLongField(this, 2, ulAudioStreamOffset);
		return this;
	}
	@CLong 
	@Field(3) 
	public long ulAudioSizeBytes() {
		return this.io.getCLongField(this, 3);
	}
	@CLong 
	@Field(3) 
	public SPPHRASEELEMENT ulAudioSizeBytes(long ulAudioSizeBytes) {
		this.io.setCLongField(this, 3, ulAudioSizeBytes);
		return this;
	}
	@CLong 
	@Field(4) 
	public long ulRetainedStreamOffset() {
		return this.io.getCLongField(this, 4);
	}
	@CLong 
	@Field(4) 
	public SPPHRASEELEMENT ulRetainedStreamOffset(long ulRetainedStreamOffset) {
		this.io.setCLongField(this, 4, ulRetainedStreamOffset);
		return this;
	}
	@CLong 
	@Field(5) 
	public long ulRetainedSizeBytes() {
		return this.io.getCLongField(this, 5);
	}
	@CLong 
	@Field(5) 
	public SPPHRASEELEMENT ulRetainedSizeBytes(long ulRetainedSizeBytes) {
		this.io.setCLongField(this, 5, ulRetainedSizeBytes);
		return this;
	}
	/// C type : LPCWSTR
	@Field(6) 
	public Pointer<Character > pszDisplayText() {
		return this.io.getPointerField(this, 6);
	}
	/// C type : LPCWSTR
	@Field(6) 
	public SPPHRASEELEMENT pszDisplayText(Pointer<Character > pszDisplayText) {
		this.io.setPointerField(this, 6, pszDisplayText);
		return this;
	}
	/// C type : LPCWSTR
	@Field(7) 
	public Pointer<Character > pszLexicalForm() {
		return this.io.getPointerField(this, 7);
	}
	/// C type : LPCWSTR
	@Field(7) 
	public SPPHRASEELEMENT pszLexicalForm(Pointer<Character > pszLexicalForm) {
		this.io.setPointerField(this, 7, pszLexicalForm);
		return this;
	}
	/// C type : const SPPHONEID*
	@Field(8) 
	public Pointer<Short > pszPronunciation() {
		return this.io.getPointerField(this, 8);
	}
	/// C type : const SPPHONEID*
	@Field(8) 
	public SPPHRASEELEMENT pszPronunciation(Pointer<Short > pszPronunciation) {
		this.io.setPointerField(this, 8, pszPronunciation);
		return this;
	}
	@Field(9) 
	public byte bDisplayAttributes() {
		return this.io.getByteField(this, 9);
	}
	@Field(9) 
	public SPPHRASEELEMENT bDisplayAttributes(byte bDisplayAttributes) {
		this.io.setByteField(this, 9, bDisplayAttributes);
		return this;
	}
	@Field(10) 
	public byte RequiredConfidence() {
		return this.io.getByteField(this, 10);
	}
	@Field(10) 
	public SPPHRASEELEMENT RequiredConfidence(byte RequiredConfidence) {
		this.io.setByteField(this, 10, RequiredConfidence);
		return this;
	}
	@Field(11) 
	public byte ActualConfidence() {
		return this.io.getByteField(this, 11);
	}
	@Field(11) 
	public SPPHRASEELEMENT ActualConfidence(byte ActualConfidence) {
		this.io.setByteField(this, 11, ActualConfidence);
		return this;
	}
	@Field(12) 
	public byte Reserved() {
		return this.io.getByteField(this, 12);
	}
	@Field(12) 
	public SPPHRASEELEMENT Reserved(byte Reserved) {
		this.io.setByteField(this, 12, Reserved);
		return this;
	}
	@Field(13) 
	public float SREngineConfidence() {
		return this.io.getFloatField(this, 13);
	}
	@Field(13) 
	public SPPHRASEELEMENT SREngineConfidence(float SREngineConfidence) {
		this.io.setFloatField(this, 13, SREngineConfidence);
		return this;
	}
	public SPPHRASEELEMENT(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
