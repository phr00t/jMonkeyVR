package com.fourthskyinteractive.dx4j.dxgi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class DXGI_RGBA extends StructObject {
	public DXGI_RGBA() {
		super();
	}
	@Field(0) 
	public float r() {
		return this.io.getFloatField(this, 0);
	}
	@Field(0) 
	public DXGI_RGBA r(float r) {
		this.io.setFloatField(this, 0, r);
		return this;
	}
	@Field(1) 
	public float g() {
		return this.io.getFloatField(this, 1);
	}
	@Field(1) 
	public DXGI_RGBA g(float g) {
		this.io.setFloatField(this, 1, g);
		return this;
	}
	@Field(2) 
	public float b() {
		return this.io.getFloatField(this, 2);
	}
	@Field(2) 
	public DXGI_RGBA b(float b) {
		this.io.setFloatField(this, 2, b);
		return this;
	}
	@Field(3) 
	public float a() {
		return this.io.getFloatField(this, 3);
	}
	@Field(3) 
	public DXGI_RGBA a(float a) {
		this.io.setFloatField(this, 3, a);
		return this;
	}
	public DXGI_RGBA(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
