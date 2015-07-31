package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.xaudio2.XAudio2.XAUDIO2_FILTER_TYPE;

public class XAUDIO2_FILTER_PARAMETERS extends StructObject {
	public XAUDIO2_FILTER_PARAMETERS() {
		super();
	}
	/// Low-pass, band-pass or high-pass.
	@Field(0) 
	public IntValuedEnum<XAUDIO2_FILTER_TYPE > Type() {
		return this.io.getEnumField(this, 0);
	}
	/// Low-pass, band-pass or high-pass.
	@Field(0) 
	public XAUDIO2_FILTER_PARAMETERS Type(IntValuedEnum<XAUDIO2_FILTER_TYPE > Type) {
		this.io.setEnumField(this, 0, Type);
		return this;
	}
	/// Radian frequency (2 * sin(pi*CutoffFrequency/SampleRate));
	@Field(1) 
	public float Frequency() {
		return this.io.getFloatField(this, 1);
	}
	/// Radian frequency (2 * sin(pi*CutoffFrequency/SampleRate));
	@Field(1) 
	public XAUDIO2_FILTER_PARAMETERS Frequency(float Frequency) {
		this.io.setFloatField(this, 1, Frequency);
		return this;
	}
	/**
	 * (giving a maximum CutoffFrequency of SampleRate/6).<br>
	 * Reciprocal of the filter's quality factor Q;
	 */
	@Field(2) 
	public float OneOverQ() {
		return this.io.getFloatField(this, 2);
	}
	/**
	 * (giving a maximum CutoffFrequency of SampleRate/6).<br>
	 * Reciprocal of the filter's quality factor Q;
	 */
	@Field(2) 
	public XAUDIO2_FILTER_PARAMETERS OneOverQ(float OneOverQ) {
		this.io.setFloatField(this, 2, OneOverQ);
		return this;
	}
	public XAUDIO2_FILTER_PARAMETERS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
