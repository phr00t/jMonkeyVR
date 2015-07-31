package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XINPUT_CAPABILITIES extends StructObject {

	public XINPUT_CAPABILITIES() {
		// TODO Auto-generated constructor stub
	}

	public XINPUT_CAPABILITIES(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}
	
	@Field(0)
	public byte Type() {
		return this.io.getByteField(this, 0);
	}
	
	@Field(1)
	public byte SubType() {
		return this.io.getByteField(this, 1);
	}
	
	@Field(2)
	public short Flags() {
		return this.io.getShortField(this, 2);
	}
	
	@Field(3)
	public XINPUT_GAMEPAD Gamepad() {
		return this.io.getNativeObjectField(this, 3);
	}
	
	@Field(4)
	public XINPUT_VIBRATION Vibration() {
		return this.io.getNativeObjectField(this, 4);
	}
}
