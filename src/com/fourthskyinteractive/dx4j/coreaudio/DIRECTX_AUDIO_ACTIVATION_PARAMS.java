package com.fourthskyinteractive.dx4j.coreaudio;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("")
public class DIRECTX_AUDIO_ACTIVATION_PARAMS extends StructObject {

	public DIRECTX_AUDIO_ACTIVATION_PARAMS() {
	}

	public DIRECTX_AUDIO_ACTIVATION_PARAMS(Pointer<? extends StructObject> peer) {
		super(peer);
	}

	@Field(0)
	public int cbDirectXAudioActivationParams() {
		return this.io.getIntField(this, 0);
	}
	
	@Field(1)
	public Pointer<Byte> guidAudioSession() {
		return this.io.getPointerField(this, 1);
	}
	
	@Field(2)
	public int dwAudioStreamFlags() {
		return this.io.getIntField(this, 2);
	}
}
