package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3D11_FEATURE_DATA_SHADER_MIN_PRECISION_SUPPORT extends StructObject {
	public D3D11_FEATURE_DATA_SHADER_MIN_PRECISION_SUPPORT() {
		super();
	}
	@Field(0) 
	public int PixelShaderMinPrecision() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3D11_FEATURE_DATA_SHADER_MIN_PRECISION_SUPPORT PixelShaderMinPrecision(int PixelShaderMinPrecision) {
		this.io.setIntField(this, 0, PixelShaderMinPrecision);
		return this;
	}
	@Field(1) 
	public int AllOtherShaderStagesMinPrecision() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public D3D11_FEATURE_DATA_SHADER_MIN_PRECISION_SUPPORT AllOtherShaderStagesMinPrecision(int AllOtherShaderStagesMinPrecision) {
		this.io.setIntField(this, 1, AllOtherShaderStagesMinPrecision);
		return this;
	}
	public D3D11_FEATURE_DATA_SHADER_MIN_PRECISION_SUPPORT(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
