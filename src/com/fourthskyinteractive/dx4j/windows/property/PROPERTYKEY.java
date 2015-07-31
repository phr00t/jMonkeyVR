package com.fourthskyinteractive.dx4j.windows.property;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.cpp.com.GUID;

@Library("")
public class PROPERTYKEY extends StructObject {

	public PROPERTYKEY() {
	}

	public PROPERTYKEY(Pointer<? extends StructObject> peer) {
		super(peer);
	}
	
	public PROPERTYKEY(int fmtidData1, short fmtidData2, short fmtidData3, byte[] fmtidData4, int pid) {
		this.fmtid().Data1(0xa45c254e).Data2((short)0xdf1c).Data3((short)0x4efd);
		this.fmtid().Data4().setBytes(new byte[]{(byte) 0x80, 0x20, 0x67, (byte) 0xd1, 0x46, (byte) 0xa8, 0x50, (byte) 0xe0});
		this.pid(14);
	}

	@Field(0)
	public GUID fmtid() {
		return this.io.getNativeObjectField(this, 0);
	}
	
	@Field(0)
	public PROPERTYKEY fmtid(GUID fmtid) {
		this.io.setNativeObjectField(this, 0, fmtid);
		return this;
	}
	
	@Field(1)
	public int pid() {
		return this.io.getIntField(this, 1);
	}
	
	@Field(1)
	public PROPERTYKEY pid(int pid) {
		this.io.setIntField(this, 1, pid);
		return this;
	}
}
