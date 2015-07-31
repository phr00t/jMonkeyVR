package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("XInput1_3")
public class XINPUT_GAMEPAD extends StructObject {

	public XINPUT_GAMEPAD() {
	}

	public XINPUT_GAMEPAD(Pointer<? extends StructObject> peer) {
		super(peer);
	}

	@Field(0) 
	public short wButtons() {
		return this.io.getShortField(this, 0);
	}
	
	@Field(1)
	public char bLeftTrigger() {
		return this.io.getCharField(this, 1);
	}
	
	@Field(2)
	public char bRightTrigger() {
		return this.io.getCharField(this, 2);
	}
	
	@Field(3)
	public short sThumbLX() {
		return this.io.getShortField(this, 3);
	}
	
	@Field(4)
	public short sThumbLY() {
		return this.io.getShortField(this, 4);
	}
	
	@Field(5)
	public short sThumbRX() {
		return this.io.getShortField(this, 5);
	}
	
	@Field(6)
	public short sThumbRY() {
		return this.io.getShortField(this, 6);
	}

}
