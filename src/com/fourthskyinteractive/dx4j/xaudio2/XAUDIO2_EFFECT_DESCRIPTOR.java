package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.cpp.com.IUnknown;

public class XAUDIO2_EFFECT_DESCRIPTOR extends StructObject {
	public XAUDIO2_EFFECT_DESCRIPTOR() {
		super();
	}
	/// Pointer to the effect object's IUnknown interface.
	@Field(0) 
	public Pointer<IUnknown > pEffect() {
		return this.io.getPointerField(this, 0);
	}
	/// Pointer to the effect object's IUnknown interface.
	@Field(0) 
	public XAUDIO2_EFFECT_DESCRIPTOR pEffect(Pointer<IUnknown > pEffect) {
		this.io.setPointerField(this, 0, pEffect);
		return this;
	}
	/// TRUE if the effect should begin in the enabled state.
	@Field(1) 
	public int InitialState() {
		return this.io.getIntField(this, 1);
	}
	/// TRUE if the effect should begin in the enabled state.
	@Field(1) 
	public XAUDIO2_EFFECT_DESCRIPTOR InitialState(int InitialState) {
		this.io.setIntField(this, 1, InitialState);
		return this;
	}
	/// How many output channels the effect should produce.
	@Field(2) 
	public int OutputChannels() {
		return this.io.getIntField(this, 2);
	}
	/// How many output channels the effect should produce.
	@Field(2) 
	public XAUDIO2_EFFECT_DESCRIPTOR OutputChannels(int OutputChannels) {
		this.io.setIntField(this, 2, OutputChannels);
		return this;
	}
	public XAUDIO2_EFFECT_DESCRIPTOR(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}

