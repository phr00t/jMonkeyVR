package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_EFFECT_CHAIN extends StructObject {
	public XAUDIO2_EFFECT_CHAIN() {
		super();
	}
	/// Number of effects in this voice's effect chain.
	@Field(0) 
	public int EffectCount() {
		return this.io.getIntField(this, 0);
	}
	/// Number of effects in this voice's effect chain.
	@Field(0) 
	public XAUDIO2_EFFECT_CHAIN EffectCount(int EffectCount) {
		this.io.setIntField(this, 0, EffectCount);
		return this;
	}
	/// Array of effect descriptors.
	@Field(1) 
	public Pointer<XAUDIO2_EFFECT_DESCRIPTOR > pEffectDescriptors() {
		return this.io.getPointerField(this, 1);
	}
	/// Array of effect descriptors.
	@Field(1) 
	public XAUDIO2_EFFECT_CHAIN pEffectDescriptors(Pointer<XAUDIO2_EFFECT_DESCRIPTOR > pEffectDescriptors) {
		this.io.setPointerField(this, 1, pEffectDescriptors);
		return this;
	}
	public XAUDIO2_EFFECT_CHAIN(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
