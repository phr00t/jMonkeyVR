package com.fourthskyinteractive.dx4j.windows.kernel32;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;

public class RTL_CRITICAL_SECTION_DEBUG extends StructObject {
	public RTL_CRITICAL_SECTION_DEBUG() {
		super();
	}
	public RTL_CRITICAL_SECTION_DEBUG(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	@Field(0) 
	public short Type() {
		return this.io.getShortField(this, 0);
	}
	@Field(0) 
	public RTL_CRITICAL_SECTION_DEBUG Type(short Type) {
		this.io.setShortField(this, 0, Type);
		return this;
	}
	@Field(1) 
	public short CreatorBackTraceIndex() {
		return this.io.getShortField(this, 1);
	}
	@Field(1) 
	public RTL_CRITICAL_SECTION_DEBUG CreatorBackTraceIndex(short CreatorBackTraceIndex) {
		this.io.setShortField(this, 1, CreatorBackTraceIndex);
		return this;
	}
	/// C type : PRTL_CRITICAL_SECTION
	@Field(2) 
	public Pointer<CRITICAL_SECTION > CriticalSection() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : PRTL_CRITICAL_SECTION
	@Field(2) 
	public RTL_CRITICAL_SECTION_DEBUG CriticalSection(Pointer<CRITICAL_SECTION > CriticalSection) {
		this.io.setPointerField(this, 2, CriticalSection);
		return this;
	}
	/// C type : LIST_ENTRY
	@Field(3) 
	public LIST_ENTRY ProcessLocksList() {
		return this.io.getNativeObjectField(this, 3);
	}
	@CLong 
	@Field(4) 
	public long EntryCount() {
		return this.io.getCLongField(this, 4);
	}
	@CLong 
	@Field(4) 
	public RTL_CRITICAL_SECTION_DEBUG EntryCount(long EntryCount) {
		this.io.setCLongField(this, 4, EntryCount);
		return this;
	}
	@CLong 
	@Field(5) 
	public long ContentionCount() {
		return this.io.getCLongField(this, 5);
	}
	@CLong 
	@Field(5) 
	public RTL_CRITICAL_SECTION_DEBUG ContentionCount(long ContentionCount) {
		this.io.setCLongField(this, 5, ContentionCount);
		return this;
	}
	@CLong 
	@Field(6) 
	public long Flags() {
		return this.io.getCLongField(this, 6);
	}
	@CLong 
	@Field(6) 
	public RTL_CRITICAL_SECTION_DEBUG Flags(long Flags) {
		this.io.setCLongField(this, 6, Flags);
		return this;
	}
	@Field(7) 
	public short CreatorBackTraceIndexHigh() {
		return this.io.getShortField(this, 7);
	}
	@Field(7) 
	public RTL_CRITICAL_SECTION_DEBUG CreatorBackTraceIndexHigh(short CreatorBackTraceIndexHigh) {
		this.io.setShortField(this, 7, CreatorBackTraceIndexHigh);
		return this;
	}
	@Field(8) 
	public short SpareUSHORT() {
		return this.io.getShortField(this, 8);
	}
	@Field(8) 
	public RTL_CRITICAL_SECTION_DEBUG SpareUSHORT(short SpareUSHORT) {
		this.io.setShortField(this, 8, SpareUSHORT);
		return this;
	}
}

