package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("XInput1_3")
public class XINPUT_VIBRATION extends StructObject {

	public XINPUT_VIBRATION() { }

	public XINPUT_VIBRATION(Pointer<? extends StructObject> peer) {
		super(peer);
	}

	@Field(0)
	public short wLeftMotorSpeed() {
		return this.io.getShortField(this, 0);
	}
	
	@Field(0)
	public XINPUT_VIBRATION wLeftMotorSpeed(short wLeftMotorSpeed) {
		this.io.setShortField(this, 0, wLeftMotorSpeed);
		return this;
	}
	
	@Field(1)
	public short wRightMotorSpeed() {
		return this.io.getShortField(this, 1);
	}
	
	@Field(1)
	public XINPUT_VIBRATION wRightMotorSpeed(short wRightMotorSpeed) {
		this.io.setShortField(this, 1, wRightMotorSpeed);
		return this;
	}
}
