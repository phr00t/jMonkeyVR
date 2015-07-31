package com.fourthskyinteractive.dx4j.windows.gdi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class PALETTEENTRY extends StructObject {
	public PALETTEENTRY() {
		super();
	}
	@Field(0) 
	public byte peRed() {
		return this.io.getByteField(this, 0);
	}
	@Field(0) 
	public PALETTEENTRY peRed(byte peRed) {
		this.io.setByteField(this, 0, peRed);
		return this;
	}
	@Field(1) 
	public byte peGreen() {
		return this.io.getByteField(this, 1);
	}
	@Field(1) 
	public PALETTEENTRY peGreen(byte peGreen) {
		this.io.setByteField(this, 1, peGreen);
		return this;
	}
	@Field(2) 
	public byte peBlue() {
		return this.io.getByteField(this, 2);
	}
	@Field(2) 
	public PALETTEENTRY peBlue(byte peBlue) {
		this.io.setByteField(this, 2, peBlue);
		return this;
	}
	
	/**
	 * Indicates how the palette entry is to be used. 
	 * This member may be set to 0 or one of the following values:
	 * PC_EXPLICIT Specifies that the low-order word of the logical palette entry designates a hardware palette index. This flag allows the application to show the contents of the display device palette.
	 * PC_NOCOLLAPSE Specifies that the color be placed in an unused entry in the system palette instead of being matched to an existing color in the system palette. If there are no unused entries in the system palette, the color is matched normally. Once this color is in the system palette, colors in other logical palettes can be matched to this color.
	 * PC_RESERVED Specifies that the logical palette entry be used for palette animation. This flag prevents other windows from matching colors to the palette entry since the color frequently changes. If an unused system-palette entry is available, the color is placed in that entry. Otherwise, the color is not available for animation.
	 * 
	 * @param peFlags
	 * @return
	 */
	@Field(3) 
	public byte peFlags() {
		return this.io.getByteField(this, 3);
	}
	
	/**
	 * Indicates how the palette entry is to be used. 
	 * This member may be set to 0 or one of the following values:
	 * PC_EXPLICIT Specifies that the low-order word of the logical palette entry designates a hardware palette index. This flag allows the application to show the contents of the display device palette.
	 * PC_NOCOLLAPSE Specifies that the color be placed in an unused entry in the system palette instead of being matched to an existing color in the system palette. If there are no unused entries in the system palette, the color is matched normally. Once this color is in the system palette, colors in other logical palettes can be matched to this color.
	 * PC_RESERVED Specifies that the logical palette entry be used for palette animation. This flag prevents other windows from matching colors to the palette entry since the color frequently changes. If an unused system-palette entry is available, the color is placed in that entry. Otherwise, the color is not available for animation.
	 * 
	 * @param peFlags
	 * @return
	 */
	@Field(3) 
	public PALETTEENTRY peFlags(byte peFlags) {
		this.io.setByteField(this, 3, peFlags);
		return this;
	}
	public PALETTEENTRY(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
