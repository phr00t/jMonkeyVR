package com.fourthskyinteractive.dx4j.xinput;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("XInput1_3")
public class XINPUT_BATTERY_INFORMATION extends StructObject {

	public XINPUT_BATTERY_INFORMATION() {
		super();
	}

	public XINPUT_BATTERY_INFORMATION(Pointer<? extends StructObject> peer) {
		super(peer);
	}

	@Field(0)
	public byte BatteryType() {
		return this.io.getByteField(this, 0);
	}
	
	@Field(1)
	public byte BatteryLevel() {
		return this.io.getByteField(this, 1);
	}
}
