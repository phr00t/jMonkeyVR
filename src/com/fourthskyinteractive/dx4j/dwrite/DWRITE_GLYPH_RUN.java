package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * The DWRITE_GLYPH_RUN structure contains the information needed by renderers to draw glyph runs. All coordinates are in device independent pixels (DIPs).
 * 
 * @author evandro.paulino
 *
 */
@Library("dwrite")
public class DWRITE_GLYPH_RUN extends StructObject {
	public DWRITE_GLYPH_RUN() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DWRITE_GLYPH_RUN(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}
	
	@Field(0)
	public Pointer<IDWriteFontFace> fontFace() {
		return this.io.getPointerField(this, 0);
	}
	@Field(0)
	public DWRITE_GLYPH_RUN fontFace(Pointer<IDWriteFontFace> fontFace) {
		this.io.setPointerField(this, 0, fontFace);
		return this;
	}
	
	/**
	 * Logical size of the font in DIPs, not points (equals 1/96 inch).
	 * @return
	 */
	@Field(1)
	public float fontEmSize() {
		return this.io.getFloatField(this, 1);
	}
	/**
	 * Logical size of the font in DIPs, not points (equals 1/96 inch).
	 * @return
	 */
	@Field(1)
	public DWRITE_GLYPH_RUN fontEmSize(float fontEmSize) {
		this.io.setFloatField(this, 1, fontEmSize);
		return this;
	}
	
	@Field(2)
	public int glyphCount() {
		return this.io.getIntField(this, 2);
	}
	@Field(2)
	public DWRITE_GLYPH_RUN glyphCount(int glyphCount) {
		this.io.setIntField(this, 2, glyphCount);
		return this;
	}
	
	@Field(3)
	public Pointer<Short> glyphIndices() {
		return this.io.getPointerField(this, 3);
	}
	@Field(3)
	public DWRITE_GLYPH_RUN glyphIndices(Pointer<Short> glyphIndices) {
		this.io.setPointerField(this, 3, glyphIndices);
		return this;
	}
	
	@Field(4)
	public Pointer<Float> glyphAdvances() {
		return this.io.getPointerField(this, 4);
	}
	@Field(4)
	public DWRITE_GLYPH_RUN glyphAdvances(Pointer<Float> glyphAdvances) {
		this.io.setPointerField(this, 4, glyphAdvances);
		return this;
	}
	
	@Field(5)
	public Pointer<DWRITE_GLYPH_OFFSET> glyphOffsets() {
		return this.io.getPointerField(this, 5);
	}
	@Field(5)
	public DWRITE_GLYPH_RUN glyphOffsets(Pointer<DWRITE_GLYPH_OFFSET> glyphOffsets) {
		this.io.setPointerField(this, 5, glyphOffsets);
		return this;
	}
	
	/**
	 * If true, specifies that glyphs are rotated 90 degrees to the left and vertical metrics are used. Vertical writing is achieved by specifying isSideways = true and rotating the entire run 90 degrees to the right via a rotate transform.
	 * @return
	 */	
	@Field(6)
	public boolean isSideways() {
		return this.io.getBooleanField(this, 6);
	}
	/**
	 * If true, specifies that glyphs are rotated 90 degrees to the left and vertical metrics are used. Vertical writing is achieved by specifying isSideways = true and rotating the entire run 90 degrees to the right via a rotate transform.
	 * @return
	 */
	@Field(6)
	public DWRITE_GLYPH_RUN isSideways(boolean glyphCount) {
		this.io.setBooleanField(this, 6, glyphCount);
		return this;
	}
	
	/**
	 * The implicit resolved bidi level of the run. Odd levels indicate right-to-left languages like Hebrew and Arabic, while even levels indicate left-to-right languages like English and Japanese (when written horizontally). For right-to-left languages, the text origin is on the right, and text should be drawn to the left.
	 * 
	 * @return
	 */
	@Field(7)
	public int bidiLevel() {
		return this.io.getIntField(this, 7);
	}
	/**
	 * The implicit resolved bidi level of the run. Odd levels indicate right-to-left languages like Hebrew and Arabic, while even levels indicate left-to-right languages like English and Japanese (when written horizontally). For right-to-left languages, the text origin is on the right, and text should be drawn to the left.
	 * 
	 * @return
	 */
	@Field(7)
	public DWRITE_GLYPH_RUN bidiLevel(int bidiLevel) {
		this.io.setIntField(this, 7, bidiLevel);
		return this;
	}
	
}