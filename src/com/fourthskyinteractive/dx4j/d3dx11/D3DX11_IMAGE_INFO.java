package com.fourthskyinteractive.dx4j.d3dx11;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_RESOURCE_DIMENSION;
import com.fourthskyinteractive.dx4j.d3dx11.D3DX11.D3DX11_IMAGE_FILE_FORMAT;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_FORMAT;

public class D3DX11_IMAGE_INFO extends StructObject {
	public D3DX11_IMAGE_INFO() {
		super();
	}
	public D3DX11_IMAGE_INFO(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	@Field(0) 
	public int Width() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3DX11_IMAGE_INFO Width(int Width) {
		this.io.setIntField(this, 0, Width);
		return this;
	}
	@Field(1) 
	public int Height() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public D3DX11_IMAGE_INFO Height(int Height) {
		this.io.setIntField(this, 1, Height);
		return this;
	}
	@Field(2) 
	public int Depth() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public D3DX11_IMAGE_INFO Depth(int Depth) {
		this.io.setIntField(this, 2, Depth);
		return this;
	}
	@Field(3) 
	public int ArraySize() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public D3DX11_IMAGE_INFO ArraySize(int ArraySize) {
		this.io.setIntField(this, 3, ArraySize);
		return this;
	}
	@Field(4) 
	public int MipLevels() {
		return this.io.getIntField(this, 4);
	}
	@Field(4) 
	public D3DX11_IMAGE_INFO MipLevels(int MipLevels) {
		this.io.setIntField(this, 4, MipLevels);
		return this;
	}
	@Field(5) 
	public int MiscFlags() {
		return this.io.getIntField(this, 5);
	}
	@Field(5) 
	public D3DX11_IMAGE_INFO MiscFlags(int MiscFlags) {
		this.io.setIntField(this, 5, MiscFlags);
		return this;
	}
	/// C type : DXGI_FORMAT
	@Field(6) 
	public ValuedEnum<DXGI_FORMAT > Format() {
		return this.io.getEnumField(this, 6);
	}
	/// C type : DXGI_FORMAT
	@Field(6) 
	public D3DX11_IMAGE_INFO Format(ValuedEnum<DXGI_FORMAT > Format) {
		this.io.setEnumField(this, 6, Format);
		return this;
	}
	/// C type : D3D11_RESOURCE_DIMENSION
	@Field(7) 
	public ValuedEnum<D3D11_RESOURCE_DIMENSION > ResourceDimension() {
		return this.io.getEnumField(this, 7);
	}
	/// C type : D3D11_RESOURCE_DIMENSION
	@Field(7) 
	public D3DX11_IMAGE_INFO ResourceDimension(ValuedEnum<D3D11_RESOURCE_DIMENSION> ResourceDimension) {
		this.io.setEnumField(this, 7, ResourceDimension);
		return this;
	}
	/// C type : D3DX11_IMAGE_FILE_FORMAT
	@Field(8) 
	public ValuedEnum<D3DX11_IMAGE_FILE_FORMAT > ImageFileFormat() {
		return this.io.getEnumField(this, 8);
	}
	/// C type : D3DX11_IMAGE_FILE_FORMAT
	@Field(8) 
	public D3DX11_IMAGE_INFO ImageFileFormat(ValuedEnum<D3DX11_IMAGE_FILE_FORMAT > ImageFileFormat) {
		this.io.setEnumField(this, 8, ImageFileFormat);
		return this;
	}
};