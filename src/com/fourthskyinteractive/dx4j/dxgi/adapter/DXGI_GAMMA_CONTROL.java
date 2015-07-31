package com.fourthskyinteractive.dx4j.dxgi.adapter;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.dxgi.DXGI_RGB;

public class DXGI_GAMMA_CONTROL extends StructObject {

	public DXGI_GAMMA_CONTROL() {
		// TODO Auto-generated constructor stub
	}

	public DXGI_GAMMA_CONTROL(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public DXGI_RGB Scale() {
		return this.io.getNativeObjectField(this, 0);
	}
	
	@Field(1)
	public DXGI_RGB Offset() {
		return this.io.getNativeObjectField(this, 1);
	}
	
	@Array({1025})
	@Field(2)
	public Pointer<DXGI_RGB> GammaCurve() {
		return this.io.getPointerField(this, 2);
	}
}
