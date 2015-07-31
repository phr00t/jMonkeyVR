package com.fourthskyinteractive.dx4j.d3dx11;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_BIND_FLAG;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_CPU_ACCESS_FLAG;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_USAGE;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_FORMAT;

public class D3DX11_IMAGE_LOAD_INFO extends StructObject {

	public D3DX11_IMAGE_LOAD_INFO() {
		super();
	}

	public D3DX11_IMAGE_LOAD_INFO(Pointer<? extends StructObject> peer) {
		super(peer);
	}
	
	@Field(0) 
	public int Width() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3DX11_IMAGE_LOAD_INFO Width(int Width) {
		this.io.setIntField(this, 0, Width);
		return this;
	}
	@Field(1) 
	public int Height() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public D3DX11_IMAGE_LOAD_INFO Height(int Height) {
		this.io.setIntField(this, 1, Height);
		return this;
	}
	@Field(2) 
	public int Depth() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public D3DX11_IMAGE_LOAD_INFO Depth(int Depth) {
		this.io.setIntField(this, 2, Depth);
		return this;
	}
	@Field(3) 
	public int FirstMipLevel() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public D3DX11_IMAGE_LOAD_INFO FirstMipLevel(int FirstMipLevel) {
		this.io.setIntField(this, 3, FirstMipLevel);
		return this;
	}
	@Field(4) 
	public int MipLevels() {
		return this.io.getIntField(this, 4);
	}
	@Field(4) 
	public D3DX11_IMAGE_LOAD_INFO MipLevels(int MipLevels) {
		this.io.setIntField(this, 4, MipLevels);
		return this;
	}
	@Field(5) 
	public ValuedEnum<D3D11_USAGE> Usage() {
		return this.io.getEnumField(this, 5);
	}
	@Field(5) 
	public D3DX11_IMAGE_LOAD_INFO Usage(ValuedEnum<D3D11_USAGE> Usage) {
		this.io.setEnumField(this, 5, Usage);
		return this;
	}
	@Field(6) 
	public ValuedEnum<D3D11_BIND_FLAG> BindFlags() {
		return this.io.getEnumField(this, 6);
	}
	@Field(6) 
	public D3DX11_IMAGE_LOAD_INFO BindFlags(ValuedEnum<D3D11_BIND_FLAG> BindFlags) {
		this.io.setEnumField(this, 6, BindFlags);
		return this;
	}
	@Field(7) 
	public ValuedEnum<D3D11_CPU_ACCESS_FLAG> CpuAccessFlags() {
		return this.io.getEnumField(this, 7);
	}
	@Field(7) 
	public D3DX11_IMAGE_LOAD_INFO CpuAccessFlags(ValuedEnum<D3D11_CPU_ACCESS_FLAG> CpuAccessFlags) {
		this.io.setEnumField(this, 7, CpuAccessFlags);
		return this;
	}
	@Field(8) 
	public int MiscFlags() {
		return this.io.getIntField(this, 8);
	}
	@Field(8) 
	public D3DX11_IMAGE_LOAD_INFO MiscFlags(int MiscFlags) {
		this.io.setIntField(this, 8, MiscFlags);
		return this;
	}
	@Field(9) 
	public ValuedEnum<DXGI_FORMAT> Format() {
		return this.io.getEnumField(this, 9);
	}
	@Field(9) 
	public D3DX11_IMAGE_LOAD_INFO Format(ValuedEnum<DXGI_FORMAT> Format) {
		this.io.setEnumField(this, 9, Format);
		return this;
	}
	@Field(10) 
	public int Filter() {
		return this.io.getIntField(this, 10);
	}
	@Field(10) 
	public D3DX11_IMAGE_LOAD_INFO Filter(int Filter) {
		this.io.setIntField(this, 10, Filter);
		return this;
	}
	@Field(11) 
	public int MipFilter() {
		return this.io.getIntField(this, 11);
	}
	@Field(11) 
	public D3DX11_IMAGE_LOAD_INFO MipFilter(int MipFilter) {
		this.io.setIntField(this, 11, MipFilter);
		return this;
	}
	@Field(12)
	public Pointer<D3DX11_IMAGE_INFO> pSrcInfo() {
		return this.io.getPointerField(this, 12);
	}
	@Field(12)
	public D3DX11_IMAGE_LOAD_INFO pSrcInfo(Pointer<D3DX11_IMAGE_INFO> pSrcInfo) {
		this.io.setPointerField(this, 12, pSrcInfo);
		return this;
	}
}
