package com.fourthskyinteractive.dx4j.dxgi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class DXGI_RGB extends StructObject {

	public DXGI_RGB() {
		// TODO Auto-generated constructor stub
	}

	public DXGI_RGB(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public float Red() {
		return this.io.getFloatField(this, 0);
	}
	@Field(0)
	public DXGI_RGB Red(float Red) {
		this.io.setFloatField(this, 0, Red);
		return this;
	}

	@Field(1)
	public float Green() {
		return this.io.getFloatField(this, 1);
	}
	@Field(1)
	public DXGI_RGB Green(float Green) {
		this.io.setFloatField(this, 1, Green);
		return this;
	}
	
	@Field(2)
	public float Blue() {
		return this.io.getFloatField(this, 2);
	}
	@Field(2)
	public DXGI_RGB Blue(float Blue) {
		this.io.setFloatField(this, 2, Blue);
		return this;
	}
}
