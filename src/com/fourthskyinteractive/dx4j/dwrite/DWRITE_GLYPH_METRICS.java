package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class DWRITE_GLYPH_METRICS extends StructObject {
	public DWRITE_GLYPH_METRICS() {
		super();
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(0) 
	public int leftSideBearing() {
		return this.io.getIntField(this, 0);
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(0) 
	public DWRITE_GLYPH_METRICS leftSideBearing(int leftSideBearing) {
		this.io.setIntField(this, 0, leftSideBearing);
		return this;
	}
	/**
	 * </summary><br>
	 * C type : UINT32
	 */
	@Field(1) 
	public int advanceWidth() {
		return this.io.getIntField(this, 1);
	}
	/**
	 * </summary><br>
	 * C type : UINT32
	 */
	@Field(1) 
	public DWRITE_GLYPH_METRICS advanceWidth(int advanceWidth) {
		this.io.setIntField(this, 1, advanceWidth);
		return this;
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(2) 
	public int rightSideBearing() {
		return this.io.getIntField(this, 2);
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(2) 
	public DWRITE_GLYPH_METRICS rightSideBearing(int rightSideBearing) {
		this.io.setIntField(this, 2, rightSideBearing);
		return this;
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(3) 
	public int topSideBearing() {
		return this.io.getIntField(this, 3);
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(3) 
	public DWRITE_GLYPH_METRICS topSideBearing(int topSideBearing) {
		this.io.setIntField(this, 3, topSideBearing);
		return this;
	}
	/**
	 * </summary><br>
	 * C type : UINT32
	 */
	@Field(4) 
	public int advanceHeight() {
		return this.io.getIntField(this, 4);
	}
	/**
	 * </summary><br>
	 * C type : UINT32
	 */
	@Field(4) 
	public DWRITE_GLYPH_METRICS advanceHeight(int advanceHeight) {
		this.io.setIntField(this, 4, advanceHeight);
		return this;
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(5) 
	public int bottomSideBearing() {
		return this.io.getIntField(this, 5);
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(5) 
	public DWRITE_GLYPH_METRICS bottomSideBearing(int bottomSideBearing) {
		this.io.setIntField(this, 5, bottomSideBearing);
		return this;
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(6) 
	public int verticalOriginY() {
		return this.io.getIntField(this, 6);
	}
	/**
	 * </summary><br>
	 * C type : INT32
	 */
	@Field(6) 
	public DWRITE_GLYPH_METRICS verticalOriginY(int verticalOriginY) {
		this.io.setIntField(this, 6, verticalOriginY);
		return this;
	}
	public DWRITE_GLYPH_METRICS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
