package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("SAPI") 
public class SPEVENTSOURCEINFO extends StructObject {
	public SPEVENTSOURCEINFO() {
		super();
	}
	@Field(0) 
	public long ullEventInterest() {
		return this.io.getLongField(this, 0);
	}
	@Field(0) 
	public SPEVENTSOURCEINFO ullEventInterest(long ullEventInterest) {
		this.io.setLongField(this, 0, ullEventInterest);
		return this;
	}
	@Field(1) 
	public long ullQueuedInterest() {
		return this.io.getLongField(this, 1);
	}
	@Field(1) 
	public SPEVENTSOURCEINFO ullQueuedInterest(long ullQueuedInterest) {
		this.io.setLongField(this, 1, ullQueuedInterest);
		return this;
	}
	@CLong 
	@Field(2) 
	public long ulCount() {
		return this.io.getCLongField(this, 2);
	}
	@CLong 
	@Field(2) 
	public SPEVENTSOURCEINFO ulCount(long ulCount) {
		this.io.setCLongField(this, 2, ulCount);
		return this;
	}
	public SPEVENTSOURCEINFO(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
