package com.fourthskyinteractive.dx4j.windows;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;

public class LUID extends StructObject {
	public LUID() {
		super();
	}
	@CLong 
	@Field(0) 
	public long LowPart() {
		return this.io.getCLongField(this, 0);
	}
	@CLong 
	@Field(0) 
	public LUID LowPart(long LowPart) {
		this.io.setCLongField(this, 0, LowPart);
		return this;
	}
	@CLong 
	@Field(1) 
	public long HighPart() {
		return this.io.getCLongField(this, 1);
	}
	@CLong 
	@Field(1) 
	public LUID HighPart(long HighPart) {
		this.io.setCLongField(this, 1, HighPart);
		return this;
	}
	public LUID(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
