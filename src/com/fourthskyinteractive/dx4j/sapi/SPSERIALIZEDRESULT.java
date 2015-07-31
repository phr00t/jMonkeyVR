package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("SAPI") 
public class SPSERIALIZEDRESULT extends StructObject {
	public SPSERIALIZEDRESULT() {
		super();
	}
	@CLong 
	@Field(0) 
	public long ulSerializedSize() {
		return this.io.getCLongField(this, 0);
	}
	@CLong 
	@Field(0) 
	public SPSERIALIZEDRESULT ulSerializedSize(long ulSerializedSize) {
		this.io.setCLongField(this, 0, ulSerializedSize);
		return this;
	}
	public SPSERIALIZEDRESULT(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}

