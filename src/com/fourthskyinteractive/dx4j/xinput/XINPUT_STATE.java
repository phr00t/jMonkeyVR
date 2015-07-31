package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XINPUT_STATE extends StructObject {

	public XINPUT_STATE() {
	}

	public XINPUT_STATE(Pointer<? extends StructObject> peer) {
		super(peer);
	}

	@Field(0)
	public int dwPacketNumber() {
		return this.io.getIntField(this, 0);
	}
	
	@Field(0)
	public XINPUT_STATE dwPacketNumber(int value) {
		this.io.setIntField(this, 0, value);
		return this;
	}
	
	@Field(1)
	public XINPUT_GAMEPAD Gamepad() {
		return this.io.getNativeObjectField(this, 1);
	}
	
	@Field(1)
	public XINPUT_STATE Gamepad(XINPUT_GAMEPAD value) {
		this.io.setNativeObjectField(this, 1, value);
		return this;
	}
}
