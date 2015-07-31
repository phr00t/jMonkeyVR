package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_VOICE_SENDS extends StructObject {
	public XAUDIO2_VOICE_SENDS() {
		super();
	}
	
	@Field(0)
	public int SendCount() {
		return this.io.getIntField(this, 0);
	}
	@Field(0)
	public XAUDIO2_VOICE_SENDS SendCount(int SendCount) {
		this.io.setIntField(this, 0, SendCount);
		return this;
	}
	/// Array of SendCount send descriptors.
	@Field(1) 
	public Pointer<XAUDIO2_SEND_DESCRIPTOR > pSends() {
		return this.io.getPointerField(this, 1);
	}
	/// Array of SendCount send descriptors.
	@Field(1) 
	public XAUDIO2_VOICE_SENDS pSends(Pointer<XAUDIO2_SEND_DESCRIPTOR > pSends) {
		this.io.setPointerField(this, 1, pSends);
		return this;
	}
	public XAUDIO2_VOICE_SENDS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
