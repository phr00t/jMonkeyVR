package com.fourthskyinteractive.dx4j.dxgi.device;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_MODE_SCALING;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_MODE_SCANLINE_ORDER;
import com.fourthskyinteractive.dx4j.dxgi.DXGI_RATIONAL;
import org.bridj.ann.Library;

@Library("dxgi")
public class DXGI_SWAP_CHAIN_FULLSCREEN_DESC extends StructObject {

	public DXGI_SWAP_CHAIN_FULLSCREEN_DESC() {
		// TODO Auto-generated constructor stub
	}

	public DXGI_SWAP_CHAIN_FULLSCREEN_DESC(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}
	
	@Field(0) 
	public DXGI_RATIONAL RefreshRate() {
		return this.io.getNativeObjectField(this, 0);
	}
	
	@Field(1) 
	public ValuedEnum<DXGI_MODE_SCANLINE_ORDER> ScanlineOrdering() {
		return this.io.getEnumField(this, 1);
	}
	@Field(1) 
	public DXGI_SWAP_CHAIN_FULLSCREEN_DESC ScanlineOrdering(ValuedEnum<DXGI_MODE_SCANLINE_ORDER> value) {
		this.io.setEnumField(this, 1, value);
		return this;
	}
	
	@Field(2) 
	public ValuedEnum<DXGI_MODE_SCALING> Scaling() {
		return this.io.getEnumField(this, 2);
	}
	@Field(2) 
	public DXGI_SWAP_CHAIN_FULLSCREEN_DESC Scaling(ValuedEnum<DXGI_MODE_SCALING> value) {
		this.io.setEnumField(this, 2, value);
		return this;
	}

}
