package com.fourthskyinteractive.dx4j.d3dx10;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3DXVECTOR2 extends StructObject {

	public D3DXVECTOR2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public D3DXVECTOR2(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	
	@Field(0)
	public float x() {
		return this.io.getFloatField(this, 0);
	}
	
	@Field(0)
	public D3DXVECTOR2 x(float x) {
		this.io.setFloatField(this, 0, x);
		return this;
	}
	
	@Field(1)
	public float y() {
		return this.io.getFloatField(this, 1);
	}
	
	@Field(1)
	public D3DXVECTOR2 y(float y) {
		this.io.setFloatField(this, 1, y);
		return this;
	}
}
