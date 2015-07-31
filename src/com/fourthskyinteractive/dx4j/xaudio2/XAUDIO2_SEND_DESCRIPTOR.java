package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_SEND_DESCRIPTOR extends StructObject {
	public XAUDIO2_SEND_DESCRIPTOR() {
		super();
	}
	
	@Field(0)
	public int Flags() {
		return this.io.getIntField(this, 0);
	}
	@Field(0)
	public XAUDIO2_SEND_DESCRIPTOR Flags(int Flags) {
		this.io.setIntField(this, 0, Flags);
		return this;
	}
	/// This send's destination voice.
	@Field(1) 
	public Pointer<IXAudio2Voice > pOutputVoice() {
		return this.io.getPointerField(this, 1);
	}
	/// This send's destination voice.
	@Field(1) 
	public XAUDIO2_SEND_DESCRIPTOR pOutputVoice(Pointer<IXAudio2Voice > pOutputVoice) {
		this.io.setPointerField(this, 1, pOutputVoice);
		return this;
	}
	public XAUDIO2_SEND_DESCRIPTOR(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
