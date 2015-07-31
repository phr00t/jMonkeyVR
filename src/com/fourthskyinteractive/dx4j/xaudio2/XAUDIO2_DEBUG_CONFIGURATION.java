package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_DEBUG_CONFIGURATION extends StructObject {
	public XAUDIO2_DEBUG_CONFIGURATION() {
		super();
	}
	/// Bitmap of enabled debug message types.
	@Field(0) 
	public int TraceMask() {
		return this.io.getIntField(this, 0);
	}
	/// Bitmap of enabled debug message types.
	@Field(0) 
	public XAUDIO2_DEBUG_CONFIGURATION TraceMask(int TraceMask) {
		this.io.setIntField(this, 0, TraceMask);
		return this;
	}
	/// Message types that will break into the debugger.
	@Field(1) 
	public int BreakMask() {
		return this.io.getIntField(this, 1);
	}
	/// Message types that will break into the debugger.
	@Field(1) 
	public XAUDIO2_DEBUG_CONFIGURATION BreakMask(int BreakMask) {
		this.io.setIntField(this, 1, BreakMask);
		return this;
	}
	/// Whether to log the thread ID with each message.
	@Field(2) 
	public int LogThreadID() {
		return this.io.getIntField(this, 2);
	}
	/// Whether to log the thread ID with each message.
	@Field(2) 
	public XAUDIO2_DEBUG_CONFIGURATION LogThreadID(int LogThreadID) {
		this.io.setIntField(this, 2, LogThreadID);
		return this;
	}
	/// Whether to log the source file and line number.
	@Field(3) 
	public int LogFileline() {
		return this.io.getIntField(this, 3);
	}
	/// Whether to log the source file and line number.
	@Field(3) 
	public XAUDIO2_DEBUG_CONFIGURATION LogFileline(int LogFileline) {
		this.io.setIntField(this, 3, LogFileline);
		return this;
	}
	/// Whether to log the function name.
	@Field(4) 
	public int LogFunctionName() {
		return this.io.getIntField(this, 4);
	}
	/// Whether to log the function name.
	@Field(4) 
	public XAUDIO2_DEBUG_CONFIGURATION LogFunctionName(int LogFunctionName) {
		this.io.setIntField(this, 4, LogFunctionName);
		return this;
	}
	/// Whether to log message timestamps.
	@Field(5) 
	public int LogTiming() {
		return this.io.getIntField(this, 5);
	}
	/// Whether to log message timestamps.
	@Field(5) 
	public XAUDIO2_DEBUG_CONFIGURATION LogTiming(int LogTiming) {
		this.io.setIntField(this, 5, LogTiming);
		return this;
	}
	public XAUDIO2_DEBUG_CONFIGURATION(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
