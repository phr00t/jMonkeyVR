package com.fourthskyinteractive.dx4j.d3dx10;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3DXPLANE extends StructObject {

	public D3DXPLANE() {
		super();
		// TODO Auto-generated constructor stub
	}

	public D3DXPLANE(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public float a() {
		return this.io.getFloatField(this, 0);
	}
	
	@Field(0)
	public D3DXPLANE a(float a) {
		this.io.setFloatField(this, 0, a);
		return this;
	}
	
	@Field(1)
	public float b() {
		return this.io.getFloatField(this, 1);
	}
	
	@Field(1)
	public D3DXPLANE b(float b) {
		this.io.setFloatField(this, 1, b);
		return this;
	}
	
	@Field(2)
	public float c() {
		return this.io.getFloatField(this, 2);
	}
	
	@Field(2)
	public D3DXPLANE c(float c) {
		this.io.setFloatField(this, 2, c);
		return this;
	}
	
	@Field(3)
	public float d() {
		return this.io.getFloatField(this, 3);
	}
	
	@Field(3)
	public D3DXPLANE d(float d) {
		this.io.setFloatField(this, 3, d);
		return this;
	}
}
