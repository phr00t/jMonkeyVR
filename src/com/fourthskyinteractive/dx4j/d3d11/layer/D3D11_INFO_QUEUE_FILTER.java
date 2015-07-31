package com.fourthskyinteractive.dx4j.d3d11.layer;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("d3d11")
public class D3D11_INFO_QUEUE_FILTER extends StructObject {

	public D3D11_INFO_QUEUE_FILTER() {
		// TODO Auto-generated constructor stub
	}

	public D3D11_INFO_QUEUE_FILTER(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}
	
	@Field(0)
	public D3D11_INFO_QUEUE_FILTER_DESC AllowList() {
		return this.io.getNativeObjectField(this, 0);
	}
	
	@Field(1)
	public D3D11_INFO_QUEUE_FILTER_DESC DenyList() {
		return this.io.getNativeObjectField(this, 1);
	}

}
