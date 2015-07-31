package com.fourthskyinteractive.dx4j.dwrite;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IUnknown;

@Library("dwrite")
@Runtime(COMRuntime.class)
public class DWrite {
	static {
		BridJ.register();
	}
	
	public enum DWRITE_FONT_FILE_TYPE implements IntValuedEnum<DWRITE_FONT_FILE_TYPE> {
	    /// <summary>
	    /// Font type is not recognized by the DirectWrite font system.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_UNKNOWN(0),

	    /// <summary>
	    /// OpenType font with CFF outlines.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_CFF(1),

	    /// <summary>
	    /// OpenType font with TrueType outlines.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_TRUETYPE(2),

	    /// <summary>
	    /// OpenType font that contains a TrueType collection.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_TRUETYPE_COLLECTION(3),

	    /// <summary>
	    /// Type 1 PFM font.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_TYPE1_PFM(4),

	    /// <summary>
	    /// Type 1 PFB font.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_TYPE1_PFB(5),

	    /// <summary>
	    /// Vector .FON font.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_VECTOR(6),

	    /// <summary>
	    /// Bitmap .FON font.
	    /// </summary>
	    DWRITE_FONT_FILE_TYPE_BITMAP(7);
	    
	    public final long value;
	    DWRITE_FONT_FILE_TYPE(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_FONT_FILE_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_FONT_FILE_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum DWRITE_FONT_FACE_TYPE implements IntValuedEnum<DWRITE_FONT_FACE_TYPE> {
	    /// <summary>
	    /// OpenType font face with CFF outlines.
	    /// </summary>
	    DWRITE_FONT_FACE_TYPE_CFF(0),

	    /// <summary>
	    /// OpenType font face with TrueType outlines.
	    /// </summary>
	    DWRITE_FONT_FACE_TYPE_TRUETYPE(1),

	    /// <summary>
	    /// OpenType font face that is a part of a TrueType collection.
	    /// </summary>
	    DWRITE_FONT_FACE_TYPE_TRUETYPE_COLLECTION(2),

	    /// <summary>
	    /// A Type 1 font face.
	    /// </summary>
	    DWRITE_FONT_FACE_TYPE_TYPE1(3),

	    /// <summary>
	    /// A vector .FON format font face.
	    /// </summary>
	    DWRITE_FONT_FACE_TYPE_VECTOR(4),

	    /// <summary>
	    /// A bitmap .FON format font face.
	    /// </summary>
	    DWRITE_FONT_FACE_TYPE_BITMAP(5),

	    /// <summary>
	    /// Font face type is not recognized by the DirectWrite font system.
	    /// </summary>
	    DWRITE_FONT_FACE_TYPE_UNKNOWN(6);
	    
	    public final long value;
	    DWRITE_FONT_FACE_TYPE(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_FONT_FACE_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_FONT_FACE_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};

	/// <summary>
	/// Specifies algorithmic style simulations to be applied to the font face.
	/// Bold and oblique simulations can be combined via bitwise OR operation.
	/// </summary>
	public enum DWRITE_FONT_SIMULATIONS implements IntValuedEnum<DWRITE_FONT_SIMULATIONS> {
	    /// <summary>
	    /// No simulations are performed.
	    /// </summary>
	    DWRITE_FONT_SIMULATIONS_NONE(0),

	    /// <summary>
	    /// Algorithmic emboldening is performed.
	    /// </summary>
	    DWRITE_FONT_SIMULATIONS_BOLD(1),

	    /// <summary>
	    /// Algorithmic italicization is performed.
	    /// </summary>
	    DWRITE_FONT_SIMULATIONS_OBLIQUE(2);
	    
		public final long value;
	    DWRITE_FONT_SIMULATIONS(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_FONT_SIMULATIONS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_FONT_SIMULATIONS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public enum DWRITE_FONT_WEIGHT implements IntValuedEnum<DWRITE_FONT_WEIGHT> {
	    /// <summary>
	    /// Predefined font weight : Thin (100).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_THIN(100),

	    /// <summary>
	    /// Predefined font weight : Extra-light (200).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_EXTRA_LIGHT(200),

	    /// <summary>
	    /// Predefined font weight : Ultra-light (200).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_ULTRA_LIGHT(200),

	    /// <summary>
	    /// Predefined font weight : Light (300).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_LIGHT(300),

	    /// <summary>
	    /// Predefined font weight : Normal (400).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_NORMAL(400),

	    /// <summary>
	    /// Predefined font weight : Regular (400).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_REGULAR(400),

	    /// <summary>
	    /// Predefined font weight : Medium (500).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_MEDIUM(500),

	    /// <summary>
	    /// Predefined font weight : Demi-bold (600).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_DEMI_BOLD(600),

	    /// <summary>
	    /// Predefined font weight : Semi-bold (600).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_SEMI_BOLD(600),

	    /// <summary>
	    /// Predefined font weight : Bold (700).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_BOLD(700),

	    /// <summary>
	    /// Predefined font weight : Extra-bold (800).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_EXTRA_BOLD(800),

	    /// <summary>
	    /// Predefined font weight : Ultra-bold (800).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_ULTRA_BOLD(800),

	    /// <summary>
	    /// Predefined font weight : Black (900).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_BLACK(900),

	    /// <summary>
	    /// Predefined font weight : Heavy (900).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_HEAVY(900),

	    /// <summary>
	    /// Predefined font weight : Extra-black (950).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_EXTRA_BLACK(950),

	    /// <summary>
	    /// Predefined font weight : Ultra-black (950).
	    /// </summary>
	    DWRITE_FONT_WEIGHT_ULTRA_BLACK(950);
	    
	    public final long value;
	    DWRITE_FONT_WEIGHT(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_FONT_WEIGHT > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_FONT_WEIGHT > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	
	public enum DWRITE_FONT_STRETCH implements IntValuedEnum<DWRITE_FONT_STRETCH> {
	    /// <summary>
	    /// Predefined font stretch : Not known (0).
	    /// </summary>
	    DWRITE_FONT_STRETCH_UNDEFINED(0),

	    /// <summary>
	    /// Predefined font stretch : Ultra-condensed (1).
	    /// </summary>
	    DWRITE_FONT_STRETCH_ULTRA_CONDENSED(1),

	    /// <summary>
	    /// Predefined font stretch : Extra-condensed (2).
	    /// </summary>
	    DWRITE_FONT_STRETCH_EXTRA_CONDENSED(2),

	    /// <summary>
	    /// Predefined font stretch : Condensed (3).
	    /// </summary>
	    DWRITE_FONT_STRETCH_CONDENSED(3),

	    /// <summary>
	    /// Predefined font stretch : Semi-condensed (4).
	    /// </summary>
	    DWRITE_FONT_STRETCH_SEMI_CONDENSED(4),

	    /// <summary>
	    /// Predefined font stretch : Normal (5).
	    /// </summary>
	    DWRITE_FONT_STRETCH_NORMAL(5),

	    /// <summary>
	    /// Predefined font stretch : Medium (5).
	    /// </summary>
	    DWRITE_FONT_STRETCH_MEDIUM(5),

	    /// <summary>
	    /// Predefined font stretch : Semi-expanded (6).
	    /// </summary>
	    DWRITE_FONT_STRETCH_SEMI_EXPANDED(6),

	    /// <summary>
	    /// Predefined font stretch : Expanded (7).
	    /// </summary>
	    DWRITE_FONT_STRETCH_EXPANDED(7),

	    /// <summary>
	    /// Predefined font stretch : Extra-expanded (8).
	    /// </summary>
	    DWRITE_FONT_STRETCH_EXTRA_EXPANDED(8),

	    /// <summary>
	    /// Predefined font stretch : Ultra-expanded (9).
	    /// </summary>
	    DWRITE_FONT_STRETCH_ULTRA_EXPANDED(9);
	    
	    public final long value;
	    DWRITE_FONT_STRETCH(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_FONT_STRETCH > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_FONT_STRETCH > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	
	public enum DWRITE_FONT_STYLE implements IntValuedEnum<DWRITE_FONT_STYLE> {
	    /// <summary>
	    /// Font slope style : Normal.
	    /// </summary>
	    DWRITE_FONT_STYLE_NORMAL(0),

	    /// <summary>
	    /// Font slope style : Oblique.
	    /// </summary>
	    DWRITE_FONT_STYLE_OBLIQUE(1),

	    /// <summary>
	    /// Font slope style : Italic.
	    /// </summary>
	    DWRITE_FONT_STYLE_ITALIC(2);
	    
	    public final long value;
	    DWRITE_FONT_STYLE(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_FONT_STYLE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_FONT_STYLE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}

	};
	
	public enum DWRITE_INFORMATIONAL_STRING_ID implements IntValuedEnum<DWRITE_INFORMATIONAL_STRING_ID > {
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_NONE(0),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_COPYRIGHT_NOTICE(1),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_VERSION_STRINGS(2),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_TRADEMARK(3),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_MANUFACTURER(4),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_DESIGNER(5),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_DESIGNER_URL(6),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_DESCRIPTION(7),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_FONT_VENDOR_URL(8),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_LICENSE_DESCRIPTION(9),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_LICENSE_INFO_URL(10),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_WIN32_FAMILY_NAMES(11),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_WIN32_SUBFAMILY_NAMES(12),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_PREFERRED_FAMILY_NAMES(13),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_PREFERRED_SUBFAMILY_NAMES(14),
		/// </summary>
		DWRITE_INFORMATIONAL_STRING_SAMPLE_TEXT(15);
		DWRITE_INFORMATIONAL_STRING_ID(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<DWRITE_INFORMATIONAL_STRING_ID > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<DWRITE_INFORMATIONAL_STRING_ID > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	
	public enum DWRITE_MEASURING_MODE implements IntValuedEnum<DWRITE_MEASURING_MODE> {
		DWRITE_MEASURING_MODE_NATURAL(0),
		DWRITE_MEASURING_MODE_GDI_CLASSIC(1),
		DWRITE_MEASURING_MODE_GDI_NATURAL(2);
		
		public final long value;
		DWRITE_MEASURING_MODE(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_MEASURING_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_MEASURING_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	};
	
	public enum DWRITE_FACTORY_TYPE implements IntValuedEnum<DWRITE_FACTORY_TYPE> {
		DWRITE_FACTORY_TYPE_SHARED(0),
		DWRITE_FACTORY_TYPE_ISOLATED(1);
		
		public final long value;
		DWRITE_FACTORY_TYPE(long value) {
			this.value = value;
		}
		public long value() {
			return value;
		}
		public Iterator<DWRITE_FACTORY_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<DWRITE_FACTORY_TYPE> fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	
	public static native final int DWriteCreateFactory(DWRITE_FACTORY_TYPE factoryType, Pointer<Byte> refGuid, Pointer<Pointer<? extends IUnknown>> ppFactory);
}
