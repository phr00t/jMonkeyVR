package com.fourthskyinteractive.dx4j.d2d1.resources.layer;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_ANTIALIAS_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_LAYER_OPTIONS;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.ID2D1Brush;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_MATRIX_3X2_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_RECT_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1Geometry;

@Library("d2d1")
public class D2D1_LAYER_PARAMETERS extends StructObject {

	public D2D1_LAYER_PARAMETERS() {
		super();
		// TODO Auto-generated constructor stub
	}

	public D2D1_LAYER_PARAMETERS(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	/*
	 * The rectangular clip that will be applied to the layer. The clip is affected by
     * the world transform. Content outside of the content bounds will not render.
	 */
	@Field(0)
	public D2D1_RECT_F contentBounds() {
		return this.io.getNativeObjectField(this, 0);
	}
	/*
	 * The rectangular clip that will be applied to the layer. The clip is affected by
     * the world transform. Content outside of the content bounds will not render.
	 */
	@Field(0)
	public D2D1_LAYER_PARAMETERS contentBounds(D2D1_RECT_F rect) {
		this.io.setNativeObjectField(this, 0, rect);
		return this;
	}
	
	/**
	 * A general mask that can be optionally applied to the content. Content not inside
	 * the fill of the mask will not be rendered.
	 * 
	 * @return
	 */
	@Field(1)
	public Pointer<ID2D1Geometry> geometricMask() {
		return this.io.getPointerField(this, 1);
	}
	/**
	 * A general mask that can be optionally applied to the content. Content not inside
	 * the fill of the mask will not be rendered.
	 * 
	 * @return
	 */
	@Field(1)
	public D2D1_LAYER_PARAMETERS geometricMask(Pointer<ID2D1Geometry> geometricMask) {
		this.io.setPointerField(this, 1, geometricMask);
		return this;
	}
	
	/**
	 * Specifies whether the mask should be aliased or antialiased.
	 * 
	 * @return
	 */
	@Field(2)
	public ValuedEnum<D2D1_ANTIALIAS_MODE> maskAntialiasMode() {
		return this.io.getEnumField(this, 2);
	}
	/**
	 * Specifies whether the mask should be aliased or antialiased.
	 * 
	 * @return
	 */
	@Field(2)
	public D2D1_LAYER_PARAMETERS maskAntialiasMode(ValuedEnum<D2D1_ANTIALIAS_MODE> mode) {
		this.io.setEnumField(this, 2, mode);
		return this;
	}
	
	/**
	 * An additional transform that may be applied to the mask in addition to the
	 * current world transform.
     * 
	 * @return
	 */
	@Field(3)
	public D2D1_MATRIX_3X2_F maskTransform() {
		return this.io.getNativeObjectField(this, 3);
	}
	/**
	 * An additional transform that may be applied to the mask in addition to the
	 * current world transform.
     * 
	 * @return
	 */
	@Field(3)
	public D2D1_LAYER_PARAMETERS maskTransform(D2D1_MATRIX_3X2_F matrix) {
		this.io.setNativeObjectField(this, 3, matrix);
		return this;
	}
	
	/**
	 * The opacity with which all of the content in the layer will be blended back to
	 * the target when the layer is popped.
	 * 
	 * @return
	 */
	@Field(4)
	public float opacity() {
		return this.io.getFloatField(this, 4);
	}
	/**
	 * The opacity with which all of the content in the layer will be blended back to
	 * the target when the layer is popped.
	 * 
	 * @return
	 */
	@Field(4)
	public D2D1_LAYER_PARAMETERS opacity(float opacity) {
		this.io.setFloatField(this, 4, opacity);
		return this;
	}
	
	/**
	 * An additional brush that can be applied to the layer. Only the opacity channel
	 * is sampled from this brush and multiplied both with the layer content and the
	 * over-all layer opacity.
	 * 
	 * @return
	 */
	@Field(5)
	public Pointer<ID2D1Brush> opacityBrush() {
		return this.io.getPointerField(this, 5);
	}
	/**
	 * An additional brush that can be applied to the layer. Only the opacity channel
	 * is sampled from this brush and multiplied both with the layer content and the
	 * over-all layer opacity.
	 * 
	 * @return
	 */
	@Field(5)
	public D2D1_LAYER_PARAMETERS opacityBrush(Pointer<ID2D1Brush> opacityBrush) {
		this.io.setPointerField(this, 5, opacityBrush);
		return this;
	}
	
	/**
	 * Specifies if ClearType will be rendered into the layer.
	 * 
	 * @return
	 */
	@Field(6)
	public ValuedEnum<D2D1_LAYER_OPTIONS> layerOptions() {
		return this.io.getEnumField(this, 6);
	}
	/**
	 * Specifies if ClearType will be rendered into the layer.
	 * 
	 * @return
	 */
	@Field(6)
	public D2D1_LAYER_PARAMETERS layerOptions(ValuedEnum<D2D1_LAYER_OPTIONS> options) {
		this.io.setEnumField(this, 6, options);
		return this;
	}
}
