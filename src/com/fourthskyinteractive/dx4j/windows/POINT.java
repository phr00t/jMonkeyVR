package com.fourthskyinteractive.dx4j.windows;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;

public class POINT extends StructObject {
	public POINT() {
		super();
	}
	@CLong 
	@Field(0) 
	public long x() {
		return this.io.getCLongField(this, 0);
	}
	@CLong 
	@Field(0) 
	public POINT x(long x) {
		this.io.setCLongField(this, 0, x);
		return this;
	}
	@CLong 
	@Field(1) 
	public long y() {
		return this.io.getCLongField(this, 1);
	}
	@CLong 
	@Field(1) 
	public POINT y(long y) {
		this.io.setCLongField(this, 1, y);
		return this;
	}
	public POINT(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
