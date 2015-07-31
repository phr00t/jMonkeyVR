package com.fourthskyinteractive.dx4j.dxgi.device;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * 
 * @author evandro.paulino
 * 
 *
 */
@Library("dxgi")
public class DXGI_SAMPLE_DESC extends StructObject {
	public DXGI_SAMPLE_DESC() {
		super();
	}
	public DXGI_SAMPLE_DESC(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	
	@Field(0) 
	public int Count() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public DXGI_SAMPLE_DESC Count(int Count) {
		this.io.setIntField(this, 0, Count);
		return this;
	}
	
	@Field(1) 
	public int Quality() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public DXGI_SAMPLE_DESC Quality(int Quality) {
		this.io.setIntField(this, 1, Quality);
		return this;
	}
}
