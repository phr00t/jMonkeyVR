package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.ann.CLong;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_VOICE_STATE extends StructObject {
	public XAUDIO2_VOICE_STATE() {
		super();
	}
	/// The pContext value provided in the XAUDIO2_BUFFER
	@Field(0) 
	public Pointer<? > pCurrentBufferContext() {
		return this.io.getPointerField(this, 0);
	}
	/// The pContext value provided in the XAUDIO2_BUFFER
	@Field(0) 
	public XAUDIO2_VOICE_STATE pCurrentBufferContext(Pointer<? > pCurrentBufferContext) {
		this.io.setPointerField(this, 0, pCurrentBufferContext);
		return this;
	}
	/**
	 * there are no buffers in the queue.<br>
	 * Number of buffers currently queued on the voice
	 */
	@Field(1) 
	public int BuffersQueued() {
		return this.io.getIntField(this, 1);
	}
	/**
	 * there are no buffers in the queue.<br>
	 * Number of buffers currently queued on the voice
	 */
	@Field(1) 
	public XAUDIO2_VOICE_STATE BuffersQueued(int BuffersQueued) {
		this.io.setIntField(this, 1, BuffersQueued);
		return this;
	}
	/**
	 * (including the one that is being processed).<br>
	 * Total number of samples produced by the voice since
	 */
	@CLong
	@Field(2) 
	public long SamplesPlayed() {
		return this.io.getCLongField(this, 2);
	}
	/**
	 * (including the one that is being processed).<br>
	 * Total number of samples produced by the voice since
	 */
	@Field(2) 
	public XAUDIO2_VOICE_STATE SamplesPlayed(@CLong long SamplesPlayed) {
		this.io.setLongField(this, 2, SamplesPlayed);
		return this;
	}
	public XAUDIO2_VOICE_STATE(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
