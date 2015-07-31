package com.fourthskyinteractive.dx4j.windows.kernel32;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class LIST_ENTRY extends StructObject {
	public LIST_ENTRY() {
		super();
	}
	public LIST_ENTRY(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	/// C type : PLIST_ENTRY
	@Field(0) 
	public Pointer<LIST_ENTRY > Flink() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : PLIST_ENTRY
	@Field(0) 
	public LIST_ENTRY Flink(Pointer<LIST_ENTRY > Flink) {
		this.io.setPointerField(this, 0, Flink);
		return this;
	}
	/// C type : PLIST_ENTRY
	@Field(1) 
	public Pointer<LIST_ENTRY > Blink() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : PLIST_ENTRY
	@Field(1) 
	public LIST_ENTRY Blink(Pointer<LIST_ENTRY > Blink) {
		this.io.setPointerField(this, 1, Blink);
		return this;
	}
}
