package com.fourthskyinteractive.dx4j.dxgi.device;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.cpp.com.RECT;

import com.fourthskyinteractive.dx4j.windows.POINT;

@Library("dxgi")
public class DXGI_PRESENT_PARAMETERS extends StructObject {

	public DXGI_PRESENT_PARAMETERS() {
		// TODO Auto-generated constructor stub
	}

	public DXGI_PRESENT_PARAMETERS(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public int DirtyRectsCount() {
		return this.io.getIntField(this, 0);
	}
	@Field(0)
	public DXGI_PRESENT_PARAMETERS DirtyRectsCount(int DirtyRectsCount) {
		this.io.setIntField(this, 0, DirtyRectsCount);
		return this;
	}

	@Field(1)
	public Pointer<RECT> pDirtyRects() {
		return this.io.getPointerField(this, 1);
	}
	
	@Field(2)
	public Pointer<RECT> pScrollRect() {
		return this.io.getPointerField(this, 2);
	}
	
	@Field(3)
	public Pointer<POINT> pScrollOffset() {
		return this.io.getPointerField(this, 3);
	}
}
