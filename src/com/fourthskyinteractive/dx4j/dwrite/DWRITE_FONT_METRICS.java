package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class DWRITE_FONT_METRICS extends StructObject {
	public DWRITE_FONT_METRICS() {
		super();
	}
	@Field(0) 
	public short designUnitsPerEm() {
		return this.io.getShortField(this, 0);
	}
	@Field(0) 
	public DWRITE_FONT_METRICS designUnitsPerEm(short designUnitsPerEm) {
		this.io.setShortField(this, 0, designUnitsPerEm);
		return this;
	}
	@Field(1) 
	public short ascent() {
		return this.io.getShortField(this, 1);
	}
	@Field(1) 
	public DWRITE_FONT_METRICS ascent(short ascent) {
		this.io.setShortField(this, 1, ascent);
		return this;
	}
	@Field(2) 
	public short descent() {
		return this.io.getShortField(this, 2);
	}
	@Field(2) 
	public DWRITE_FONT_METRICS descent(short descent) {
		this.io.setShortField(this, 2, descent);
		return this;
	}
	@Field(3) 
	public short lineGap() {
		return this.io.getShortField(this, 3);
	}
	@Field(3) 
	public DWRITE_FONT_METRICS lineGap(short lineGap) {
		this.io.setShortField(this, 3, lineGap);
		return this;
	}
	@Field(4) 
	public short capHeight() {
		return this.io.getShortField(this, 4);
	}
	@Field(4) 
	public DWRITE_FONT_METRICS capHeight(short capHeight) {
		this.io.setShortField(this, 4, capHeight);
		return this;
	}
	@Field(5) 
	public short xHeight() {
		return this.io.getShortField(this, 5);
	}
	@Field(5) 
	public DWRITE_FONT_METRICS xHeight(short xHeight) {
		this.io.setShortField(this, 5, xHeight);
		return this;
	}
	@Field(6) 
	public short underlinePosition() {
		return this.io.getShortField(this, 6);
	}
	@Field(6) 
	public DWRITE_FONT_METRICS underlinePosition(short underlinePosition) {
		this.io.setShortField(this, 6, underlinePosition);
		return this;
	}
	@Field(7) 
	public short underlineThickness() {
		return this.io.getShortField(this, 7);
	}
	@Field(7) 
	public DWRITE_FONT_METRICS underlineThickness(short underlineThickness) {
		this.io.setShortField(this, 7, underlineThickness);
		return this;
	}
	@Field(8) 
	public short strikethroughPosition() {
		return this.io.getShortField(this, 8);
	}
	@Field(8) 
	public DWRITE_FONT_METRICS strikethroughPosition(short strikethroughPosition) {
		this.io.setShortField(this, 8, strikethroughPosition);
		return this;
	}
	@Field(9) 
	public short strikethroughThickness() {
		return this.io.getShortField(this, 9);
	}
	@Field(9) 
	public DWRITE_FONT_METRICS strikethroughThickness(short strikethroughThickness) {
		this.io.setShortField(this, 9, strikethroughThickness);
		return this;
	}
	public DWRITE_FONT_METRICS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
