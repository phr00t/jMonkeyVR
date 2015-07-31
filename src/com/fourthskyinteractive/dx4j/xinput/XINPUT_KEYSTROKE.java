package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;


public class XINPUT_KEYSTROKE extends StructObject {
	public XINPUT_KEYSTROKE() {
		super();
	}
	public XINPUT_KEYSTROKE(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	
	@Field(0) 
	public short VirtualKey() {
		return this.io.getShortField(this, 0);
	}
	@Field(0) 
	public XINPUT_KEYSTROKE VirtualKey(short VirtualKey) {
		this.io.setShortField(this, 0, VirtualKey);
		return this;
	}
	@Field(1) 
	public short Unicode() {
		return this.io.getShortField(this, 1);
	}
	@Field(1) 
	public XINPUT_KEYSTROKE Unicode(short Unicode) {
		this.io.setShortField(this, 1, Unicode);
		return this;
	}
	@Field(2) 
	public short Flags() {
		return this.io.getShortField(this, 2);
	}
	@Field(2) 
	public XINPUT_KEYSTROKE Flags(short Flags) {
		this.io.setShortField(this, 2, Flags);
		return this;
	}
	@Field(3) 
	public byte UserIndex() {
		return this.io.getByteField(this, 3);
	}
	@Field(3) 
	public XINPUT_KEYSTROKE UserIndex(byte UserIndex) {
		this.io.setByteField(this, 3, UserIndex);
		return this;
	}
	@Field(4) 
	public byte HidCode() {
		return this.io.getByteField(this, 4);
	}
	@Field(4) 
	public XINPUT_KEYSTROKE HidCode(byte HidCode) {
		this.io.setByteField(this, 4, HidCode);
		return this;
	}
}
