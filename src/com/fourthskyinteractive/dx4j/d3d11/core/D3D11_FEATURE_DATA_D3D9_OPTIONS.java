package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3D11_FEATURE_DATA_D3D9_OPTIONS extends StructObject {
	public D3D11_FEATURE_DATA_D3D9_OPTIONS() {
		super();
	}
	@Field(0) 
	public int FullNonPow2TextureSupport() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3D11_FEATURE_DATA_D3D9_OPTIONS FullNonPow2TextureSupport(int FullNonPow2TextureSupport) {
		this.io.setIntField(this, 0, FullNonPow2TextureSupport);
		return this;
	}
	public D3D11_FEATURE_DATA_D3D9_OPTIONS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
