package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_VOICE_DETAILS extends StructObject {
	public XAUDIO2_VOICE_DETAILS() {
		super();
	}
	/// Flags the voice was created with.
	@Field(0) 
	public int CreationFlags() {
		return this.io.getIntField(this, 0);
	}
	/// Flags the voice was created with.
	@Field(0) 
	public XAUDIO2_VOICE_DETAILS CreationFlags(int CreationFlags) {
		this.io.setIntField(this, 0, CreationFlags);
		return this;
	}
	/// Channels in the voice's input audio.
	@Field(1) 
	public int InputChannels() {
		return this.io.getIntField(this, 1);
	}
	/// Channels in the voice's input audio.
	@Field(1) 
	public XAUDIO2_VOICE_DETAILS InputChannels(int InputChannels) {
		this.io.setIntField(this, 1, InputChannels);
		return this;
	}
	/// Sample rate of the voice's input audio.
	@Field(2) 
	public int InputSampleRate() {
		return this.io.getIntField(this, 2);
	}
	/// Sample rate of the voice's input audio.
	@Field(2) 
	public XAUDIO2_VOICE_DETAILS InputSampleRate(int InputSampleRate) {
		this.io.setIntField(this, 2, InputSampleRate);
		return this;
	}
	public XAUDIO2_VOICE_DETAILS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
