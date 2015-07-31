package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.xaudio2.XAudio2.XAUDIO2_DEVICE_ROLE;

public class XAUDIO2_DEVICE_DETAILS extends StructObject {
	public XAUDIO2_DEVICE_DETAILS() {
		super();
	}
	/// String identifier for the audio device.
	@Array({256}) 
	@Field(0) 
	public Pointer<Short > DeviceID() {
		return this.io.getPointerField(this, 0);
	}
	/// Friendly name suitable for display to a human.
	@Array({256}) 
	@Field(1) 
	public Pointer<Short > DisplayName() {
		return this.io.getPointerField(this, 1);
	}
	/// Roles that the device should be used for.
	@Field(2) 
	public IntValuedEnum<XAUDIO2_DEVICE_ROLE > Role() {
		return this.io.getEnumField(this, 2);
	}
	/// Roles that the device should be used for.
	@Field(2) 
	public XAUDIO2_DEVICE_DETAILS Role(IntValuedEnum<XAUDIO2_DEVICE_ROLE > Role) {
		this.io.setEnumField(this, 2, Role);
		return this;
	}
	/// The device's native PCM audio output format.
	@Field(3) 
	public WAVEFORMATEXTENSIBLE OutputFormat() {
		return this.io.getNativeObjectField(this, 3);
	}
	/// The device's native PCM audio output format.
	@Field(3) 
	public XAUDIO2_DEVICE_DETAILS OutputFormat(WAVEFORMATEXTENSIBLE OutputFormat) {
		this.io.setNativeObjectField(this, 3, OutputFormat);
		return this;
	}
	public XAUDIO2_DEVICE_DETAILS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
