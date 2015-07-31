package com.fourthskyinteractive.dx4j.windows;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class FILETIME extends StructObject {
	public FILETIME() {
		super();
	}
	@Field(0) 
	public int dwLowDateTime() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public FILETIME dwLowDateTime(int dwLowDateTime) {
		this.io.setIntField(this, 0, dwLowDateTime);
		return this;
	}
	@Field(1) 
	public int dwHighDateTime() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public FILETIME dwHighDateTime(int dwHighDateTime) {
		this.io.setIntField(this, 1, dwHighDateTime);
		return this;
	}
	public FILETIME(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}

