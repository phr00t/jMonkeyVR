package com.fourthskyinteractive.dx4j.d3dx10;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3DXMATRIX extends StructObject {

	public D3DXMATRIX() {
		super();	
	}

	public D3DXMATRIX(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}
	
	@Field(0)
	public float _11() { return this.io.getFloatField(this, 0); }
	@Field(0)
	public D3DXMATRIX _11(float f) { this.io.setFloatField(this, 0, f); return this; }
	
	@Field(1)
	public float _12() { return this.io.getFloatField(this, 1); }
	@Field(1)
	public D3DXMATRIX _12(float f) { this.io.setFloatField(this, 1, f); return this; }
	
	@Field(2)
	public float _13() { return this.io.getFloatField(this, 2); }
	@Field(2)
	public D3DXMATRIX _13(float f) { this.io.setFloatField(this, 2, f); return this; }
	
	@Field(3)
	public float _14() { return this.io.getFloatField(this, 3); }
	@Field(3)
	public D3DXMATRIX _14(float f) { this.io.setFloatField(this, 3, f); return this; }
	
	@Field(4)
	public float _21() { return this.io.getFloatField(this, 4); }
	@Field(4)
	public D3DXMATRIX _21(float f) { this.io.setFloatField(this, 4, f); return this; }
	
	@Field(5)
	public float _22() { return this.io.getFloatField(this, 5); }
	@Field(5)
	public D3DXMATRIX _22(float f) { this.io.setFloatField(this, 5, f); return this; }
	
	@Field(6)
	public float _23() { return this.io.getFloatField(this, 6); }
	@Field(6)
	public D3DXMATRIX _23(float f) { this.io.setFloatField(this, 6, f); return this; }
	
	@Field(7)
	public float _24() { return this.io.getFloatField(this, 7); }
	@Field(7)
	public D3DXMATRIX _24(float f) { this.io.setFloatField(this, 7, f); return this; }
	
	@Field(8)
	public float _31() { return this.io.getFloatField(this, 8); }
	@Field(8)
	public D3DXMATRIX _31(float f) { this.io.setFloatField(this, 8, f); return this; }
	
	@Field(9)
	public float _32() { return this.io.getFloatField(this, 9); }
	@Field(9)
	public D3DXMATRIX _32(float f) { this.io.setFloatField(this, 9, f); return this; }
	
	@Field(10)
	public float _33() { return this.io.getFloatField(this, 10); }
	@Field(10)
	public D3DXMATRIX _33(float f) { this.io.setFloatField(this, 10, f); return this; }
	
	@Field(11)
	public float _34() { return this.io.getFloatField(this, 11); }
	@Field(11)
	public D3DXMATRIX _34(float f) { this.io.setFloatField(this, 11, f); return this; }
	
	@Field(12)
	public float _41() { return this.io.getFloatField(this, 12); }
	@Field(12)
	public D3DXMATRIX _41(float f) { this.io.setFloatField(this, 12, f); return this; }
	
	@Field(13)
	public float _42() { return this.io.getFloatField(this, 13); }
	@Field(13)
	public D3DXMATRIX _42(float f) { this.io.setFloatField(this, 13, f); return this; }
	
	@Field(14)
	public float _43() { return this.io.getFloatField(this, 14); }
	@Field(14)
	public D3DXMATRIX _43(float f) { this.io.setFloatField(this, 14, f); return this; }
	
	@Field(15)
	public float _44() { return this.io.getFloatField(this, 15); }
	@Field(15)
	public D3DXMATRIX _44(float f) { this.io.setFloatField(this, 15, f); return this; }
	
	/*
	@Field(0)
	public Pointer<Pointer<Float>> m() {
		return this.io.getPointerField(this, 0);
	}
	
	public ByteBuffer mb() {
		return this.io.getPointerField(this, 0).getByteBuffer();
	}
	*/
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(_11()).append(" ").append(_12()).append(" ").append(_13()).append(" ").append(_14()).append("\n")
		  .append(_21()).append(" ").append(_22()).append(" ").append(_23()).append(" ").append(_24()).append("\n")
		  .append(_31()).append(" ").append(_32()).append(" ").append(_33()).append(" ").append(_34()).append("\n")
		  .append(_41()).append(" ").append(_42()).append(" ").append(_43()).append(" ").append(_44()).append("\n");
		return sb.toString();
	}
}
