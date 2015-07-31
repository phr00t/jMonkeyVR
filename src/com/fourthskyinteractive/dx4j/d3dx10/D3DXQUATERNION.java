package com.fourthskyinteractive.dx4j.d3dx10;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3DXQUATERNION extends StructObject {

	public D3DXQUATERNION() {
		super();
		// TODO Auto-generated constructor stub
	}

	public D3DXQUATERNION(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public float x() {
		return this.io.getFloatField(this, 0);
	}
	
	@Field(0)
	public D3DXQUATERNION x(float x) {
		this.io.setFloatField(this, 0, x);
		return this;
	}
	
	@Field(1)
	public float y() {
		return this.io.getFloatField(this, 1);
	}
	
	@Field(1)
	public D3DXQUATERNION y(float y) {
		this.io.setFloatField(this, 1, y);
		return this;
	}
	
	@Field(2)
	public float z() {
		return this.io.getFloatField(this, 2);
	}
	
	@Field(2)
	public D3DXQUATERNION z(float z) {
		this.io.setFloatField(this, 2, z);
		return this;
	}
	
	@Field(3)
	public float w() {
		return this.io.getFloatField(this, 3);
	}
	
	@Field(3)
	public D3DXQUATERNION w(float w) {
		this.io.setFloatField(this, 3, w);
		return this;
	}
}
