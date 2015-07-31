package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3D11_FEATURE_DATA_ARCHITECTURE_INFO extends StructObject {
	public D3D11_FEATURE_DATA_ARCHITECTURE_INFO() {
		super();
	}
	@Field(0) 
	public int TileBasedDeferredRenderer() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3D11_FEATURE_DATA_ARCHITECTURE_INFO TileBasedDeferredRenderer(int TileBasedDeferredRenderer) {
		this.io.setIntField(this, 0, TileBasedDeferredRenderer);
		return this;
	}
	public D3D11_FEATURE_DATA_ARCHITECTURE_INFO(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
