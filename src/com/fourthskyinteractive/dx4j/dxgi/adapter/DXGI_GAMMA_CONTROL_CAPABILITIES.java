package com.fourthskyinteractive.dx4j.dxgi.adapter;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;

public class DXGI_GAMMA_CONTROL_CAPABILITIES extends StructObject {
	public DXGI_GAMMA_CONTROL_CAPABILITIES() {
		super();
	}
	public DXGI_GAMMA_CONTROL_CAPABILITIES(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	@Field(0) 
	public int ScaleAndOffsetSupported() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public DXGI_GAMMA_CONTROL_CAPABILITIES ScaleAndOffsetSupported(int ScaleAndOffsetSupported) {
		this.io.setIntField(this, 0, ScaleAndOffsetSupported);
		return this;
	}
	@Field(1) 
	public float MaxConvertedValue() {
		return this.io.getFloatField(this, 1);
	}
	@Field(1) 
	public DXGI_GAMMA_CONTROL_CAPABILITIES MaxConvertedValue(float MaxConvertedValue) {
		this.io.setFloatField(this, 1, MaxConvertedValue);
		return this;
	}
	@Field(2) 
	public float MinConvertedValue() {
		return this.io.getFloatField(this, 2);
	}
	@Field(2) 
	public DXGI_GAMMA_CONTROL_CAPABILITIES MinConvertedValue(float MinConvertedValue) {
		this.io.setFloatField(this, 2, MinConvertedValue);
		return this;
	}
	@Field(3) 
	public int NumGammaControlPoints() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public DXGI_GAMMA_CONTROL_CAPABILITIES NumGammaControlPoints(int NumGammaControlPoints) {
		this.io.setIntField(this, 3, NumGammaControlPoints);
		return this;
	}
	/// C type : float[1025]
	@Array({1025}) 
	@Field(4) 
	public Pointer<Float > ControlPointPositions() {
		return this.io.getPointerField(this, 4);
	}
}

