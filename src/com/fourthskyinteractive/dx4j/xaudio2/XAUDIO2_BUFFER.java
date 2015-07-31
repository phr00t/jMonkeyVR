package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_BUFFER extends StructObject {
	public XAUDIO2_BUFFER() {
		super();
	}
	/// Either 0 or XAUDIO2_END_OF_STREAM.
	@Field(0) 
	public int Flags() {
		return this.io.getIntField(this, 0);
	}
	/// Either 0 or XAUDIO2_END_OF_STREAM.
	@Field(0) 
	public XAUDIO2_BUFFER Flags(int Flags) {
		this.io.setIntField(this, 0, Flags);
		return this;
	}
	/// Size of the audio data buffer in bytes.
	@Field(1) 
	public int AudioBytes() {
		return this.io.getIntField(this, 1);
	}
	/// Size of the audio data buffer in bytes.
	@Field(1) 
	public XAUDIO2_BUFFER AudioBytes(int AudioBytes) {
		this.io.setIntField(this, 1, AudioBytes);
		return this;
	}
	/// Pointer to the audio data buffer.
	@Field(2) 
	public Pointer<Byte > pAudioData() {
		return this.io.getPointerField(this, 2);
	}
	/// Pointer to the audio data buffer.
	@Field(2) 
	public XAUDIO2_BUFFER pAudioData(Pointer<Byte > pAudioData) {
		this.io.setPointerField(this, 2, pAudioData);
		return this;
	}
	/// First sample in this buffer to be played.
	@Field(3) 
	public int PlayBegin() {
		return this.io.getIntField(this, 3);
	}
	/// First sample in this buffer to be played.
	@Field(3) 
	public XAUDIO2_BUFFER PlayBegin(int PlayBegin) {
		this.io.setIntField(this, 3, PlayBegin);
		return this;
	}
	/// Length of the region to be played in samples,
	@Field(4) 
	public int PlayLength() {
		return this.io.getIntField(this, 4);
	}
	/// Length of the region to be played in samples,
	@Field(4) 
	public XAUDIO2_BUFFER PlayLength(int PlayLength) {
		this.io.setIntField(this, 4, PlayLength);
		return this;
	}
	/**
	 * or 0 to play the whole buffer.<br>
	 * First sample of the region to be looped.
	 */
	@Field(5) 
	public int LoopBegin() {
		return this.io.getIntField(this, 5);
	}
	/**
	 * or 0 to play the whole buffer.<br>
	 * First sample of the region to be looped.
	 */
	@Field(5) 
	public XAUDIO2_BUFFER LoopBegin(int LoopBegin) {
		this.io.setIntField(this, 5, LoopBegin);
		return this;
	}
	/// Length of the desired loop region in samples,
	@Field(6) 
	public int LoopLength() {
		return this.io.getIntField(this, 6);
	}
	/// Length of the desired loop region in samples,
	@Field(6) 
	public XAUDIO2_BUFFER LoopLength(int LoopLength) {
		this.io.setIntField(this, 6, LoopLength);
		return this;
	}
	/**
	 * or 0 to loop the entire buffer.<br>
	 * Number of times to repeat the loop region,
	 */
	@Field(7) 
	public int LoopCount() {
		return this.io.getIntField(this, 7);
	}
	/**
	 * or 0 to loop the entire buffer.<br>
	 * Number of times to repeat the loop region,
	 */
	@Field(7) 
	public XAUDIO2_BUFFER LoopCount(int LoopCount) {
		this.io.setIntField(this, 7, LoopCount);
		return this;
	}
	/**
	 * or XAUDIO2_LOOP_INFINITE to loop forever.<br>
	 * Context value to be passed back in callbacks.
	 */
	@Field(8) 
	public Pointer<? > pContext() {
		return this.io.getPointerField(this, 8);
	}
	/**
	 * or XAUDIO2_LOOP_INFINITE to loop forever.<br>
	 * Context value to be passed back in callbacks.
	 */
	@Field(8) 
	public XAUDIO2_BUFFER pContext(Pointer<? > pContext) {
		this.io.setPointerField(this, 8, pContext);
		return this;
	}
	public XAUDIO2_BUFFER(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
