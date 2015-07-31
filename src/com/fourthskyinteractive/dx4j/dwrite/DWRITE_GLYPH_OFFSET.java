package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class DWRITE_GLYPH_OFFSET extends StructObject {

	public DWRITE_GLYPH_OFFSET() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DWRITE_GLYPH_OFFSET(Pointer<? extends StructObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	@Field(0)
	public float advanceOffset() {
		return this.io.getFloatField(this, 0);
	}
	@Field(0)
	public DWRITE_GLYPH_OFFSET advanceOffset(float advanceOffset) {
		this.io.setFloatField(this, 0, advanceOffset);
		return this;
	}
	
	@Field(1)
	public float ascenderOffset() {
		return this.io.getFloatField(this, 1);
	}
	/**
	 * Logical size of the font in DIPs, not points (equals 1/96 inch).
	 * @return
	 */
	@Field(1)
	public DWRITE_GLYPH_OFFSET ascenderOffset(float ascenderOffset) {
		this.io.setFloatField(this, 1, ascenderOffset);
		return this;
	}
}
