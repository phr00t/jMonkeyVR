package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_PERFORMANCE_DATA extends StructObject {
	public XAUDIO2_PERFORMANCE_DATA() {
		super();
	}
	/**
	 * CPU usage information<br>
	 * CPU cycles spent on audio processing since the
	 */
	@Field(0) 
	public long AudioCyclesSinceLastQuery() {
		return this.io.getLongField(this, 0);
	}
	/**
	 * CPU usage information<br>
	 * CPU cycles spent on audio processing since the
	 */
	@Field(0) 
	public XAUDIO2_PERFORMANCE_DATA AudioCyclesSinceLastQuery(long AudioCyclesSinceLastQuery) {
		this.io.setLongField(this, 0, AudioCyclesSinceLastQuery);
		return this;
	}
	/**
	 * last call to StartEngine or GetPerformanceData.<br>
	 * Total CPU cycles elapsed since the last call
	 */
	@Field(1) 
	public long TotalCyclesSinceLastQuery() {
		return this.io.getLongField(this, 1);
	}
	/**
	 * last call to StartEngine or GetPerformanceData.<br>
	 * Total CPU cycles elapsed since the last call
	 */
	@Field(1) 
	public XAUDIO2_PERFORMANCE_DATA TotalCyclesSinceLastQuery(long TotalCyclesSinceLastQuery) {
		this.io.setLongField(this, 1, TotalCyclesSinceLastQuery);
		return this;
	}
	/**
	 * (only counts the CPU XAudio2 is running on).<br>
	 * Fewest CPU cycles spent processing any one
	 */
	@Field(2) 
	public int MinimumCyclesPerQuantum() {
		return this.io.getIntField(this, 2);
	}
	/**
	 * (only counts the CPU XAudio2 is running on).<br>
	 * Fewest CPU cycles spent processing any one
	 */
	@Field(2) 
	public XAUDIO2_PERFORMANCE_DATA MinimumCyclesPerQuantum(int MinimumCyclesPerQuantum) {
		this.io.setIntField(this, 2, MinimumCyclesPerQuantum);
		return this;
	}
	/**
	 * audio quantum since the last call.<br>
	 * Most CPU cycles spent processing any one
	 */
	@Field(3) 
	public int MaximumCyclesPerQuantum() {
		return this.io.getIntField(this, 3);
	}
	/**
	 * audio quantum since the last call.<br>
	 * Most CPU cycles spent processing any one
	 */
	@Field(3) 
	public XAUDIO2_PERFORMANCE_DATA MaximumCyclesPerQuantum(int MaximumCyclesPerQuantum) {
		this.io.setIntField(this, 3, MaximumCyclesPerQuantum);
		return this;
	}
	/**
	 * Memory usage information<br>
	 * Total heap space currently in use.
	 */
	@Field(4) 
	public int MemoryUsageInBytes() {
		return this.io.getIntField(this, 4);
	}
	/**
	 * Memory usage information<br>
	 * Total heap space currently in use.
	 */
	@Field(4) 
	public XAUDIO2_PERFORMANCE_DATA MemoryUsageInBytes(int MemoryUsageInBytes) {
		this.io.setIntField(this, 4, MemoryUsageInBytes);
		return this;
	}
	/**
	 * Audio latency and glitching information<br>
	 * Minimum delay from when a sample is read from a
	 */
	@Field(5) 
	public int CurrentLatencyInSamples() {
		return this.io.getIntField(this, 5);
	}
	/**
	 * Audio latency and glitching information<br>
	 * Minimum delay from when a sample is read from a
	 */
	@Field(5) 
	public XAUDIO2_PERFORMANCE_DATA CurrentLatencyInSamples(int CurrentLatencyInSamples) {
		this.io.setIntField(this, 5, CurrentLatencyInSamples);
		return this;
	}
	/**
	 * source buffer to when it reaches the speakers.<br>
	 * Audio dropouts since the engine was started.
	 */
	@Field(6) 
	public int GlitchesSinceEngineStarted() {
		return this.io.getIntField(this, 6);
	}
	/**
	 * source buffer to when it reaches the speakers.<br>
	 * Audio dropouts since the engine was started.
	 */
	@Field(6) 
	public XAUDIO2_PERFORMANCE_DATA GlitchesSinceEngineStarted(int GlitchesSinceEngineStarted) {
		this.io.setIntField(this, 6, GlitchesSinceEngineStarted);
		return this;
	}
	/**
	 * Data about XAudio2's current workload<br>
	 * Source voices currently playing.
	 */
	@Field(7) 
	public int ActiveSourceVoiceCount() {
		return this.io.getIntField(this, 7);
	}
	/**
	 * Data about XAudio2's current workload<br>
	 * Source voices currently playing.
	 */
	@Field(7) 
	public XAUDIO2_PERFORMANCE_DATA ActiveSourceVoiceCount(int ActiveSourceVoiceCount) {
		this.io.setIntField(this, 7, ActiveSourceVoiceCount);
		return this;
	}
	/// Source voices currently existing.
	@Field(8) 
	public int TotalSourceVoiceCount() {
		return this.io.getIntField(this, 8);
	}
	/// Source voices currently existing.
	@Field(8) 
	public XAUDIO2_PERFORMANCE_DATA TotalSourceVoiceCount(int TotalSourceVoiceCount) {
		this.io.setIntField(this, 8, TotalSourceVoiceCount);
		return this;
	}
	/// Submix voices currently playing/existing.
	@Field(9) 
	public int ActiveSubmixVoiceCount() {
		return this.io.getIntField(this, 9);
	}
	/// Submix voices currently playing/existing.
	@Field(9) 
	public XAUDIO2_PERFORMANCE_DATA ActiveSubmixVoiceCount(int ActiveSubmixVoiceCount) {
		this.io.setIntField(this, 9, ActiveSubmixVoiceCount);
		return this;
	}
	/// Resample xAPOs currently active.
	@Field(10) 
	public int ActiveResamplerCount() {
		return this.io.getIntField(this, 10);
	}
	/// Resample xAPOs currently active.
	@Field(10) 
	public XAUDIO2_PERFORMANCE_DATA ActiveResamplerCount(int ActiveResamplerCount) {
		this.io.setIntField(this, 10, ActiveResamplerCount);
		return this;
	}
	/// MatrixMix xAPOs currently active.
	@Field(11) 
	public int ActiveMatrixMixCount() {
		return this.io.getIntField(this, 11);
	}
	/// MatrixMix xAPOs currently active.
	@Field(11) 
	public XAUDIO2_PERFORMANCE_DATA ActiveMatrixMixCount(int ActiveMatrixMixCount) {
		this.io.setIntField(this, 11, ActiveMatrixMixCount);
		return this;
	}
	/**
	 * Usage of the hardware XMA decoder (Xbox 360 only)<br>
	 * Number of source voices decoding XMA data.
	 */
	@Field(12) 
	public int ActiveXmaSourceVoices() {
		return this.io.getIntField(this, 12);
	}
	/**
	 * Usage of the hardware XMA decoder (Xbox 360 only)<br>
	 * Number of source voices decoding XMA data.
	 */
	@Field(12) 
	public XAUDIO2_PERFORMANCE_DATA ActiveXmaSourceVoices(int ActiveXmaSourceVoices) {
		this.io.setIntField(this, 12, ActiveXmaSourceVoices);
		return this;
	}
	/// A voice can use more than one XMA stream.
	@Field(13) 
	public int ActiveXmaStreams() {
		return this.io.getIntField(this, 13);
	}
	/// A voice can use more than one XMA stream.
	@Field(13) 
	public XAUDIO2_PERFORMANCE_DATA ActiveXmaStreams(int ActiveXmaStreams) {
		this.io.setIntField(this, 13, ActiveXmaStreams);
		return this;
	}
	public XAUDIO2_PERFORMANCE_DATA(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
