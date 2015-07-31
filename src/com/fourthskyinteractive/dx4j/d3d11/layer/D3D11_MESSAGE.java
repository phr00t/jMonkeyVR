package com.fourthskyinteractive.dx4j.d3d11.layer;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_MESSAGE_CATEGORY;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_MESSAGE_ID;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_MESSAGE_SEVERITY;

public class D3D11_MESSAGE extends StructObject {

	public D3D11_MESSAGE() {
		// TODO Auto-generated constructor stub
	}

	public D3D11_MESSAGE(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public int NumCategories() {
		return io.getIntField(this, 0);
	}

	@Field(0)
	public D3D11_MESSAGE NumCategories(int value) {
		io.setIntField(this, 0, value);
		return this;
	}
	
	@Field(1)
	public Pointer<D3D11_MESSAGE_CATEGORY> pCategoryList() {
		return io.getPointerField(this, 1);
	}
	
	@Field(1)
	public D3D11_MESSAGE pCategoryList(Pointer<D3D11_MESSAGE_CATEGORY> value) {
		io.setPointerField(this, 1, value);
		return this;
	}
	
	@Field(2)
	public int NumSeverities() {
		return io.getIntField(this, 2);
	}

	@Field(2)
	public D3D11_MESSAGE NumSeverities(int value) {
		io.setIntField(this, 2, value);
		return this;
	}
	
	@Field(3)
	public Pointer<D3D11_MESSAGE_SEVERITY> pSeverityList() {
		return io.getPointerField(this, 3);
	}
	
	@Field(3)
	public D3D11_MESSAGE pSeverityList(Pointer<D3D11_MESSAGE_SEVERITY> value) {
		io.setPointerField(this, 3, value);
		return this;
	}
	
	@Field(4)
	public int NumIDs() {
		return io.getIntField(this, 4);
	}

	@Field(4)
	public D3D11_MESSAGE NumIDs(int value) {
		io.setIntField(this, 4, value);
		return this;
	}
	
	@Field(5)
	public Pointer<D3D11_MESSAGE_ID> pIDList() {
		return io.getPointerField(this, 5);
	}
	
	@Field(5)
	public D3D11_MESSAGE pIDList(Pointer<D3D11_MESSAGE_ID> value) {
		io.setPointerField(this, 5, value);
		return this;
	}
}
