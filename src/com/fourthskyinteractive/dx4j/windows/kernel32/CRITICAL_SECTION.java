package com.fourthskyinteractive.dx4j.windows.kernel32;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;

public class CRITICAL_SECTION extends StructObject {
	public CRITICAL_SECTION() {
		super();
	}
	public CRITICAL_SECTION(Pointer<StructObject> pointer) {
		super(pointer);
	}
	/// C type : PRTL_CRITICAL_SECTION_DEBUG
	@Field(0) 
	public Pointer<RTL_CRITICAL_SECTION_DEBUG > DebugInfo() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : PRTL_CRITICAL_SECTION_DEBUG
	@Field(0) 
	public CRITICAL_SECTION DebugInfo(Pointer<RTL_CRITICAL_SECTION_DEBUG > DebugInfo) {
		this.io.setPointerField(this, 0, DebugInfo);
		return this;
	}
	@CLong 
	@Field(1) 
	public long LockCount() {
		return this.io.getCLongField(this, 1);
	}
	@CLong 
	@Field(1) 
	public CRITICAL_SECTION LockCount(long LockCount) {
		this.io.setCLongField(this, 1, LockCount);
		return this;
	}
	@CLong 
	@Field(2) 
	public long RecursionCount() {
		return this.io.getCLongField(this, 2);
	}
	@CLong 
	@Field(2) 
	public CRITICAL_SECTION RecursionCount(long RecursionCount) {
		this.io.setCLongField(this, 2, RecursionCount);
		return this;
	}
	/**
	 * from the thread's ClientId->UniqueThread<br>
	 * C type : HANDLE
	 */
	@Field(3) 
	public Pointer<? > OwningThread() {
		return this.io.getPointerField(this, 3);
	}
	/**
	 * from the thread's ClientId->UniqueThread<br>
	 * C type : HANDLE
	 */
	@Field(3) 
	public CRITICAL_SECTION OwningThread(Pointer<? > OwningThread) {
		this.io.setPointerField(this, 3, OwningThread);
		return this;
	}
	/// C type : HANDLE
	@Field(4) 
	public Pointer<? > LockSemaphore() {
		return this.io.getPointerField(this, 4);
	}
	/// C type : HANDLE
	@Field(4) 
	public CRITICAL_SECTION LockSemaphore(Pointer<? > LockSemaphore) {
		this.io.setPointerField(this, 4, LockSemaphore);
		return this;
	}
	@Field(5) 
	public int SpinCount() {
		return this.io.getIntField(this, 5);
	}
	@Field(5) 
	public CRITICAL_SECTION SpinCount(int SpinCount) {
		this.io.setIntField(this, 5, SpinCount);
		return this;
	}
}
