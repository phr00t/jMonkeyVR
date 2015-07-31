package com.fourthskyinteractive.dx4j.d2d1.resources;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_ANTIALIAS_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_TEXT_ANTIALIAS_MODE;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_MATRIX_3X2_F;

/**
 * Allows the drawing state to be atomically created. This also specifies the
 * drawing state that is saved into an IDrawingStateBlock object.
 */
@Library("d2d1") 
public class D2D1_DRAWING_STATE_DESCRIPTION extends StructObject {
	public D2D1_DRAWING_STATE_DESCRIPTION() {
		super();
	}
	public D2D1_DRAWING_STATE_DESCRIPTION(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	/// C type : D2D1_ANTIALIAS_MODE
	@Field(0) 
	public ValuedEnum<D2D1_ANTIALIAS_MODE > antialiasMode() {
		return this.io.getEnumField(this, 0);
	}
	/// C type : D2D1_ANTIALIAS_MODE
	@Field(0) 
	public D2D1_DRAWING_STATE_DESCRIPTION antialiasMode(ValuedEnum<D2D1_ANTIALIAS_MODE > antialiasMode) {
		this.io.setEnumField(this, 0, antialiasMode);
		return this;
	}
	/// C type : D2D1_TEXT_ANTIALIAS_MODE
	@Field(1) 
	public ValuedEnum<D2D1_TEXT_ANTIALIAS_MODE > textAntialiasMode() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : D2D1_TEXT_ANTIALIAS_MODE
	@Field(1) 
	public D2D1_DRAWING_STATE_DESCRIPTION textAntialiasMode(ValuedEnum<D2D1_TEXT_ANTIALIAS_MODE > textAntialiasMode) {
		this.io.setEnumField(this, 1, textAntialiasMode);
		return this;
	}
	/// C type : D2D1_TAG
	@Field(2) 
	public long tag1() {
		return this.io.getLongField(this, 2);
	}
	/// C type : D2D1_TAG
	@Field(2) 
	public D2D1_DRAWING_STATE_DESCRIPTION tag1(long tag1) {
		this.io.setLongField(this, 2, tag1);
		return this;
	}
	/// C type : D2D1_TAG
	@Field(3) 
	public long tag2() {
		return this.io.getLongField(this, 3);
	}
	/// C type : D2D1_TAG
	@Field(3) 
	public D2D1_DRAWING_STATE_DESCRIPTION tag2(long tag2) {
		this.io.setLongField(this, 3, tag2);
		return this;
	}
	/// C type : D2D1_MATRIX_3X2_F
	@Field(4) 
	public D2D1_MATRIX_3X2_F transform() {
		return this.io.getNativeObjectField(this, 4);
	}
	/// C type : D2D1_MATRIX_3X2_F
	@Field(4) 
	public D2D1_DRAWING_STATE_DESCRIPTION transform(D2D1_MATRIX_3X2_F transform) {
		this.io.setNativeObjectField(this, 4, transform);
		return this;
	}
}
