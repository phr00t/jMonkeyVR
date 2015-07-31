package com.fourthskyinteractive.dx4j.windows.kernel32;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

/**
 * 
 * @author evandro.paulino
 *
 */
public class LARGE_INTEGER extends StructObject {
	public LARGE_INTEGER() {
		super();
	}
	public LARGE_INTEGER(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	
	@Field(0)
	public int LowPart() {
		return this.io.getIntField(this, 0);
	}
	
	@Field(0)
	public LARGE_INTEGER LowPart(int LowPart) {
		this.io.setIntField(this, 0, LowPart);
		return this;
	}
	
	@Field(1)
	public int HighPart() {
		return this.io.getIntField(this, 1);
	}
	
	@Field(1)
	public LARGE_INTEGER HighPart(int HighPart) {
		this.io.setIntField(this, 1, HighPart);
		return this;
	}
	
	public long QuadPart() {
		long result = this.HighPart() << 32 | this.LowPart();
		return result;
	}
	
	public LARGE_INTEGER QuadPart(long value) {
		this.HighPart((int) (value >> 32)).LowPart((int)value);
		return this;
	}
}
