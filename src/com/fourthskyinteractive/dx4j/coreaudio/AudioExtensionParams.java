package com.fourthskyinteractive.dx4j.coreaudio;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.coreaudio.mmdevice.IMMDevice;

@Library("")
public class AudioExtensionParams extends StructObject {

	public AudioExtensionParams() {
		// TODO Auto-generated constructor stub
	}

	public AudioExtensionParams(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public int AddPageParam() {
		return this.io.getIntField(this, 0);
	}
	
	@Field(0)
	public AudioExtensionParams AddPageParam(int param) {
		this.io.setIntField(this, 0, param);
		return this;
	}
	
	@Field(1)
	public Pointer<IMMDevice> pEndpoint() {
		return this.io.getPointerField(this, 1);
	}
	@Field(1)
	public AudioExtensionParams pEndpoint(Pointer<IMMDevice> value) {
		this.io.setPointerField(this, 1, value);
		return this;
	}
	
	@Field(2)
	public Pointer<IMMDevice> pPnpInterface() {
		return this.io.getPointerField(this, 2);
	}
	@Field(2)
	public AudioExtensionParams pPnpInterface(Pointer<IMMDevice> value) {
		this.io.setPointerField(this, 2, value);
		return this;
	}
	
	@Field(3)
	public Pointer<IMMDevice> pPnpDevnode() {
		return this.io.getPointerField(this, 3);
	}
	@Field(3)
	public AudioExtensionParams pPnpDevnode(Pointer<IMMDevice> value) {
		this.io.setPointerField(this, 3, value);
		return this;
	}
}
