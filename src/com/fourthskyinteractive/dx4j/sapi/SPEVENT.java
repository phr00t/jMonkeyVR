package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Bits;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.sapi.SAPI.SPEVENTENUM;
import com.fourthskyinteractive.dx4j.sapi.SAPI.SPEVENTLPARAMTYPE;

@Library("SAPI") 
public class SPEVENT extends StructObject {
	public SPEVENT() {
		super();
	}
	/// C type : SPEVENTENUM
	@Field(0) 
	@Bits(16) 
	public IntValuedEnum<SPEVENTENUM > eEventId() {
		return this.io.getEnumField(this, 0);
	}
	/// C type : SPEVENTENUM
	@Field(0) 
	@Bits(16) 
	public SPEVENT eEventId(IntValuedEnum<SPEVENTENUM > eEventId) {
		this.io.setEnumField(this, 0, eEventId);
		return this;
	}
	/// C type : SPEVENTLPARAMTYPE
	@Field(1) 
	@Bits(16) 
	public IntValuedEnum<SPEVENTLPARAMTYPE > elParamType() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : SPEVENTLPARAMTYPE
	@Field(1) 
	@Bits(16) 
	public SPEVENT elParamType(IntValuedEnum<SPEVENTLPARAMTYPE > elParamType) {
		this.io.setEnumField(this, 1, elParamType);
		return this;
	}
	@CLong 
	@Field(2) 
	public long ulStreamNum() {
		return this.io.getCLongField(this, 2);
	}
	@CLong 
	@Field(2) 
	public SPEVENT ulStreamNum(long ulStreamNum) {
		this.io.setCLongField(this, 2, ulStreamNum);
		return this;
	}
	@Field(3) 
	public long ullAudioStreamOffset() {
		return this.io.getLongField(this, 3);
	}
	@Field(3) 
	public SPEVENT ullAudioStreamOffset(long ullAudioStreamOffset) {
		this.io.setLongField(this, 3, ullAudioStreamOffset);
		return this;
	}
	/// C type : WPARAM
	@Field(4) 
	public int wParam() {
		return this.io.getIntField(this, 4);
	}
	/// C type : WPARAM
	@Field(4) 
	public SPEVENT wParam(int wParam) {
		this.io.setIntField(this, 4, wParam);
		return this;
	}
	/// C type : LPARAM
	@CLong 
	@Field(5) 
	public long lParam() {
		return this.io.getCLongField(this, 5);
	}
	/// C type : LPARAM
	@CLong 
	@Field(5) 
	public SPEVENT lParam(long lParam) {
		this.io.setCLongField(this, 5, lParam);
		return this;
	}
	public SPEVENT(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
