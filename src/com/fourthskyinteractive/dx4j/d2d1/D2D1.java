package com.fourthskyinteractive.dx4j.d2d1;

import static org.bridj.Pointer.allocatePointer;
import static org.bridj.Pointer.pointerTo;

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

import com.fourthskyinteractive.dx4j.d2d1.core.D2D1_FACTORY_OPTIONS;
import com.fourthskyinteractive.dx4j.d2d1.core.ID2D1Factory;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_MATRIX_3X2_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_POINT_2F;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_FEATURE_LEVEL;

@Library("D2D1")
@Runtime(COMRuntime.class)
public class D2D1 {
	static {
		BridJ.register();
	}
	
	public static final int sc_redShift   = 16;
	public static final int sc_greenShift = 8; 
	public static final int sc_blueShift  = 0;    

	public static final int sc_redMask = 0xff << sc_redShift;
	public static final int sc_greenMask = 0xff << sc_greenShift;
	public static final int sc_blueMask = 0xff;
	
	
	public enum ColorF {
		AliceBlue(0xF0F8FF),
		AntiqueWhite(0xFAEBD7),
		Aqua(0x00FFFF),
		Aquamarine(0x7FFFD4),
		Azure(0xF0FFFF),
		Beige(0xF5F5DC),
		Bisque(0xFFE4C4),
		Black(0x000000),
		BlanchedAlmond(0xFFEBCD),
		Blue(0x0000FF),
		BlueViolet(0x8A2BE2),
		Brown(0xA52A2A),
		BurlyWood(0xDEB887),
		CadetBlue(0x5F9EA0),
		Chartreuse(0x7FFF00),
		Chocolate(0xD2691E),
		Coral(0xFF7F50),
		CornflowerBlue(0x6495ED),
		Cornsilk(0xFFF8DC),
		Crimson(0xDC143C),
		Cyan(0x00FFFF),
		DarkBlue(0x00008B),
		DarkCyan(0x008B8B),
		DarkGoldenrod(0xB8860B),
		DarkGray(0xA9A9A9),
		DarkGreen(0x006400),
		DarkKhaki(0xBDB76B),
		DarkMagenta(0x8B008B),
		DarkOliveGreen(0x556B2F),
		DarkOrange(0xFF8C00),
		DarkOrchid(0x9932CC),
		DarkRed(0x8B0000),
		DarkSalmon(0xE9967A),
		DarkSeaGreen(0x8FBC8F),
		DarkSlateBlue(0x483D8B),
		DarkSlateGray(0x2F4F4F),
		DarkTurquoise(0x00CED1),
		DarkViolet(0x9400D3),
		DeepPink(0xFF1493),
		DeepSkyBlue(0x00BFFF),
		DimGray(0x696969),
		DodgerBlue(0x1E90FF),
		Firebrick(0xB22222),
		FloralWhite(0xFFFAF0),
		ForestGreen(0x228B22),
		Fuchsia(0xFF00FF),
		Gainsboro(0xDCDCDC),
		GhostWhite(0xF8F8FF),
		Gold(0xFFD700),
		Goldenrod(0xDAA520),
		Gray(0x808080),
		Green(0x008000),
		GreenYellow(0xADFF2F),
		Honeydew(0xF0FFF0),
		HotPink(0xFF69B4),
		IndianRed(0xCD5C5C),
		Indigo(0x4B0082),
		Ivory(0xFFFFF0),
		Khaki(0xF0E68C),
		Lavender(0xE6E6FA),
		LavenderBlush(0xFFF0F5),
		LawnGreen(0x7CFC00),
		LemonChiffon(0xFFFACD),
		LightBlue(0xADD8E6),
		LightCoral(0xF08080),
		LightCyan(0xE0FFFF),
		LightGoldenrodYellow(0xFAFAD2),
		LightGreen(0x90EE90),
		LightGray(0xD3D3D3),
		LightPink(0xFFB6C1),
		LightSalmon(0xFFA07A),
		LightSeaGreen(0x20B2AA),
		LightSkyBlue(0x87CEFA),
		LightSlateGray(0x778899),
		LightSteelBlue(0xB0C4DE),
		LightYellow(0xFFFFE0),
		Lime(0x00FF00),
		LimeGreen(0x32CD32),
		Linen(0xFAF0E6),
		Magenta(0xFF00FF),
		Maroon(0x800000),
		MediumAquamarine(0x66CDAA),
		MediumBlue(0x0000CD),
		MediumOrchid(0xBA55D3),
		MediumPurple(0x9370DB),
		MediumSeaGreen(0x3CB371),
		MediumSlateBlue(0x7B68EE),
		MediumSpringGreen(0x00FA9A),
		MediumTurquoise(0x48D1CC),
		MediumVioletRed(0xC71585),
		MidnightBlue(0x191970),
		MintCream(0xF5FFFA),
		MistyRose(0xFFE4E1),
		Moccasin(0xFFE4B5),
		NavajoWhite(0xFFDEAD),
		Navy(0x000080),
		OldLace(0xFDF5E6),
		Olive(0x808000),
		OliveDrab(0x6B8E23),
		Orange(0xFFA500),
		OrangeRed(0xFF4500),
		Orchid(0xDA70D6),
		PaleGoldenrod(0xEEE8AA),
		PaleGreen(0x98FB98),
		PaleTurquoise(0xAFEEEE),
		PaleVioletRed(0xDB7093),
		PapayaWhip(0xFFEFD5),
		PeachPuff(0xFFDAB9),
		Peru(0xCD853F),
		Pink(0xFFC0CB),
		Plum(0xDDA0DD),
		PowderBlue(0xB0E0E6),
		Purple(0x800080),
		Red(0xFF0000),
		RosyBrown(0xBC8F8F),
		RoyalBlue(0x4169E1),
		SaddleBrown(0x8B4513),
		Salmon(0xFA8072),
		SandyBrown(0xF4A460),
		SeaGreen(0x2E8B57),
		SeaShell(0xFFF5EE),
		Sienna(0xA0522D),
		Silver(0xC0C0C0),
		SkyBlue(0x87CEEB),
		SlateBlue(0x6A5ACD),
		SlateGray(0x708090),
		Snow(0xFFFAFA),
		SpringGreen(0x00FF7F),
		SteelBlue(0x4682B4),
		Tan(0xD2B48C),
		Teal(0x008080),
		Thistle(0xD8BFD8),
		Tomato(0xFF6347),
		Turquoise(0x40E0D0),
		Violet(0xEE82EE),
		Wheat(0xF5DEB3),
		White(0xFFFFFF),
		WhiteSmoke(0xF5F5F5),
		Yellow(0xFFFF00),
		YellowGreen(0x9ACD32);
		
		public final int value;
		ColorF(int col) {
			this.value = col;
		}
	}
	
	public enum D2D1_ALPHA_MODE implements IntValuedEnum<D2D1_ALPHA_MODE > {
		D2D1_ALPHA_MODE_UNKNOWN(0),
		D2D1_ALPHA_MODE_PREMULTIPLIED(1),
		D2D1_ALPHA_MODE_STRAIGHT(2),
		D2D1_ALPHA_MODE_IGNORE(3),
		D2D1_ALPHA_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_ALPHA_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_ALPHA_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_ALPHA_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}

    public enum D2D1_GAMMA implements IntValuedEnum<D2D1_GAMMA > {
		D2D1_GAMMA_2_2(0),
		D2D1_GAMMA_1_0(1),
		D2D1_GAMMA_FORCE_DWORD(0x7FFFFFFF);
		D2D1_GAMMA(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_GAMMA > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_GAMMA > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_OPACITY_MASK_CONTENT implements IntValuedEnum<D2D1_OPACITY_MASK_CONTENT > {
		D2D1_OPACITY_MASK_CONTENT_GRAPHICS(0),
		D2D1_OPACITY_MASK_CONTENT_TEXT_NATURAL(1),
		D2D1_OPACITY_MASK_CONTENT_TEXT_GDI_COMPATIBLE(2),
		D2D1_OPACITY_MASK_CONTENT_FORCE_DWORD(0x7FFFFFFF);
		D2D1_OPACITY_MASK_CONTENT(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_OPACITY_MASK_CONTENT > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_OPACITY_MASK_CONTENT > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_EXTEND_MODE implements IntValuedEnum<D2D1_EXTEND_MODE > {
		D2D1_EXTEND_MODE_CLAMP(0),
		D2D1_EXTEND_MODE_WRAP(1),
		D2D1_EXTEND_MODE_MIRROR(2),
		D2D1_EXTEND_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_EXTEND_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_EXTEND_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_EXTEND_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_ANTIALIAS_MODE implements IntValuedEnum<D2D1_ANTIALIAS_MODE > {
		D2D1_ANTIALIAS_MODE_PER_PRIMITIVE(0),
		D2D1_ANTIALIAS_MODE_ALIASED(1),
		D2D1_ANTIALIAS_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_ANTIALIAS_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_ANTIALIAS_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_ANTIALIAS_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_TEXT_ANTIALIAS_MODE implements IntValuedEnum<D2D1_TEXT_ANTIALIAS_MODE > {
		D2D1_TEXT_ANTIALIAS_MODE_DEFAULT(0),
		D2D1_TEXT_ANTIALIAS_MODE_CLEARTYPE(1),
		D2D1_TEXT_ANTIALIAS_MODE_GRAYSCALE(2),
		D2D1_TEXT_ANTIALIAS_MODE_ALIASED(3),
		D2D1_TEXT_ANTIALIAS_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_TEXT_ANTIALIAS_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_TEXT_ANTIALIAS_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_TEXT_ANTIALIAS_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_BITMAP_INTERPOLATION_MODE implements IntValuedEnum<D2D1_BITMAP_INTERPOLATION_MODE > {
		D2D1_BITMAP_INTERPOLATION_MODE_NEAREST_NEIGHBOR(0),
		D2D1_BITMAP_INTERPOLATION_MODE_LINEAR(1),
		D2D1_BITMAP_INTERPOLATION_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_BITMAP_INTERPOLATION_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_BITMAP_INTERPOLATION_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_BITMAP_INTERPOLATION_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_DRAW_TEXT_OPTIONS implements IntValuedEnum<D2D1_DRAW_TEXT_OPTIONS > {
		D2D1_DRAW_TEXT_OPTIONS_NO_SNAP(0x1),
		D2D1_DRAW_TEXT_OPTIONS_CLIP(0x2),
		D2D1_DRAW_TEXT_OPTIONS_NONE(0x0),
		D2D1_DRAW_TEXT_OPTIONS_FORCE_DWORD(0x7FFFFFFF);
		D2D1_DRAW_TEXT_OPTIONS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_DRAW_TEXT_OPTIONS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_DRAW_TEXT_OPTIONS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_ARC_SIZE implements IntValuedEnum<D2D1_ARC_SIZE > {
		D2D1_ARC_SIZE_SMALL(0),
		D2D1_ARC_SIZE_LARGE(1),
		D2D1_ARC_SIZE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_ARC_SIZE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_ARC_SIZE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_ARC_SIZE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_CAP_STYLE implements IntValuedEnum<D2D1_CAP_STYLE > {
		D2D1_CAP_STYLE_FLAT(0),
		D2D1_CAP_STYLE_SQUARE(1),
		D2D1_CAP_STYLE_ROUND(2),
		D2D1_CAP_STYLE_TRIANGLE(3),
		D2D1_CAP_STYLE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_CAP_STYLE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_CAP_STYLE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_CAP_STYLE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_DASH_STYLE implements IntValuedEnum<D2D1_DASH_STYLE > {
		D2D1_DASH_STYLE_SOLID(0),
		D2D1_DASH_STYLE_DASH(1),
		D2D1_DASH_STYLE_DOT(2),
		D2D1_DASH_STYLE_DASH_DOT(3),
		D2D1_DASH_STYLE_DASH_DOT_DOT(4),
		D2D1_DASH_STYLE_CUSTOM(5),
		D2D1_DASH_STYLE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_DASH_STYLE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_DASH_STYLE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_DASH_STYLE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_LINE_JOIN implements IntValuedEnum<D2D1_LINE_JOIN > {
		D2D1_LINE_JOIN_MITER(0),
		D2D1_LINE_JOIN_BEVEL(1),
		D2D1_LINE_JOIN_ROUND(2),
		D2D1_LINE_JOIN_MITER_OR_BEVEL(3),
		D2D1_LINE_JOIN_FORCE_DWORD(0x7FFFFFFF);
		D2D1_LINE_JOIN(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_LINE_JOIN > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_LINE_JOIN > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_COMBINE_MODE implements IntValuedEnum<D2D1_COMBINE_MODE > {
		D2D1_COMBINE_MODE_UNION(0),
		D2D1_COMBINE_MODE_INTERSECT(1),
		D2D1_COMBINE_MODE_XOR(2),
		D2D1_COMBINE_MODE_EXCLUDE(3),
		D2D1_COMBINE_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_COMBINE_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_COMBINE_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_COMBINE_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_GEOMETRY_RELATION implements IntValuedEnum<D2D1_GEOMETRY_RELATION > {
		D2D1_GEOMETRY_RELATION_UNKNOWN(0),
		D2D1_GEOMETRY_RELATION_DISJOINT(1),
		D2D1_GEOMETRY_RELATION_IS_CONTAINED(2),
		D2D1_GEOMETRY_RELATION_CONTAINS(3),
		D2D1_GEOMETRY_RELATION_OVERLAP(4),
		D2D1_GEOMETRY_RELATION_FORCE_DWORD(0x7FFFFFFF);
		D2D1_GEOMETRY_RELATION(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_GEOMETRY_RELATION > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_GEOMETRY_RELATION > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_GEOMETRY_SIMPLIFICATION_OPTION implements IntValuedEnum<D2D1_GEOMETRY_SIMPLIFICATION_OPTION > {
		D2D1_GEOMETRY_SIMPLIFICATION_OPTION_CUBICS_AND_LINES(0),
		D2D1_GEOMETRY_SIMPLIFICATION_OPTION_LINES(1),
		D2D1_GEOMETRY_SIMPLIFICATION_OPTION_FORCE_DWORD(0x7FFFFFFF);
		D2D1_GEOMETRY_SIMPLIFICATION_OPTION(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_GEOMETRY_SIMPLIFICATION_OPTION > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_GEOMETRY_SIMPLIFICATION_OPTION > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_FIGURE_BEGIN implements IntValuedEnum<D2D1_FIGURE_BEGIN > {
		D2D1_FIGURE_BEGIN_FILLED(0),
		D2D1_FIGURE_BEGIN_HOLLOW(1),
		D2D1_FIGURE_BEGIN_FORCE_DWORD(0x7FFFFFFF);
		D2D1_FIGURE_BEGIN(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_FIGURE_BEGIN > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_FIGURE_BEGIN > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_FIGURE_END implements IntValuedEnum<D2D1_FIGURE_END > {
		D2D1_FIGURE_END_OPEN(0),
		D2D1_FIGURE_END_CLOSED(1),
		D2D1_FIGURE_END_FORCE_DWORD(0x7FFFFFFF);
		D2D1_FIGURE_END(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_FIGURE_END > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_FIGURE_END > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_PATH_SEGMENT implements IntValuedEnum<D2D1_PATH_SEGMENT > {
		D2D1_PATH_SEGMENT_NONE(0x0),
		D2D1_PATH_SEGMENT_FORCE_UNSTROKED(0x1),
		D2D1_PATH_SEGMENT_FORCE_ROUND_LINE_JOIN(0x2),
		D2D1_PATH_SEGMENT_FORCE_DWORD(0x7FFFFFFF);
		D2D1_PATH_SEGMENT(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_PATH_SEGMENT > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_PATH_SEGMENT > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_SWEEP_DIRECTION implements IntValuedEnum<D2D1_SWEEP_DIRECTION > {
		D2D1_SWEEP_DIRECTION_COUNTER_CLOCKWISE(0),
		D2D1_SWEEP_DIRECTION_CLOCKWISE(1),
		D2D1_SWEEP_DIRECTION_FORCE_DWORD(0x7FFFFFFF);
		D2D1_SWEEP_DIRECTION(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_SWEEP_DIRECTION > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_SWEEP_DIRECTION > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_FILL_MODE implements IntValuedEnum<D2D1_FILL_MODE > {
		D2D1_FILL_MODE_ALTERNATE(0),
		D2D1_FILL_MODE_WINDING(1),
		D2D1_FILL_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_FILL_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_FILL_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_FILL_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_LAYER_OPTIONS implements IntValuedEnum<D2D1_LAYER_OPTIONS > {
		D2D1_LAYER_OPTIONS_NONE(0x0),
		D2D1_LAYER_OPTIONS_INITIALIZE_FOR_CLEARTYPE(0x1),
		D2D1_LAYER_OPTIONS_FORCE_DWORD(0x7FFFFFFF);
		D2D1_LAYER_OPTIONS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_LAYER_OPTIONS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_LAYER_OPTIONS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_WINDOW_STATE implements IntValuedEnum<D2D1_WINDOW_STATE > {
		D2D1_WINDOW_STATE_NONE(0x0),
		D2D1_WINDOW_STATE_OCCLUDED(0x1),
		D2D1_WINDOW_STATE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_WINDOW_STATE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_WINDOW_STATE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_WINDOW_STATE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_RENDER_TARGET_TYPE implements IntValuedEnum<D2D1_RENDER_TARGET_TYPE > {
		D2D1_RENDER_TARGET_TYPE_DEFAULT(0),
		D2D1_RENDER_TARGET_TYPE_SOFTWARE(1),
		D2D1_RENDER_TARGET_TYPE_HARDWARE(2),
		D2D1_RENDER_TARGET_TYPE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_RENDER_TARGET_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_RENDER_TARGET_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_RENDER_TARGET_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_FEATURE_LEVEL implements IntValuedEnum<D2D1_FEATURE_LEVEL > {
		D2D1_FEATURE_LEVEL_DEFAULT(0),
		D2D1_FEATURE_LEVEL_9(D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_9_1.value),
		D2D1_FEATURE_LEVEL_10(D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_10_0.value),
		D2D1_FEATURE_LEVEL_FORCE_DWORD(0x7FFFFFFF);
		D2D1_FEATURE_LEVEL(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_FEATURE_LEVEL > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_FEATURE_LEVEL > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_RENDER_TARGET_USAGE implements IntValuedEnum<D2D1_RENDER_TARGET_USAGE > {
		D2D1_RENDER_TARGET_USAGE_NONE(0x0),
		/**
		 * Rendering will occur locally, if a terminal-services session is established, the
         * bitmap updates will be sent to the terminal services client.
		 */
		D2D1_RENDER_TARGET_USAGE_FORCE_BITMAP_REMOTING(0x1),
		/**
		 * The render target will allow a call to GetDC on the IGdiInteropRenderTarget
         * interface. Rendering will also occur locally.
		 */
		D2D1_RENDER_TARGET_USAGE_GDI_COMPATIBLE(0x2),
		D2D1_RENDER_TARGET_USAGE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_RENDER_TARGET_USAGE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_RENDER_TARGET_USAGE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_RENDER_TARGET_USAGE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_PRESENT_OPTIONS implements IntValuedEnum<D2D1_PRESENT_OPTIONS > {
		D2D1_PRESENT_OPTIONS_NONE(0x0),
		/**
		 * Keep the target contents intact through present.
		 */
		D2D1_PRESENT_OPTIONS_RETAIN_CONTENTS(0x1),
		/**
		 * Do not wait for display refresh to commit changes to display.
		 */
		D2D1_PRESENT_OPTIONS_IMMEDIATELY(0x2),
		D2D1_PRESENT_OPTIONS_FORCE_DWORD(0x7FFFFFFF);
		D2D1_PRESENT_OPTIONS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_PRESENT_OPTIONS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_PRESENT_OPTIONS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS implements IntValuedEnum<D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS > {
		D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS_NONE(0x0),
		/**
		 * The compatible render target will allow a call to GetDC on the
         * IGdiInteropRenderTarget interface. This can be specified even if the parent
         * render target is not GDI compatible.
		 */
		D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS_GDI_COMPATIBLE(0x1),
		D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS_FORCE_DWORD(0x7FFFFFFF);
		D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_DC_INITIALIZE_MODE implements IntValuedEnum<D2D1_DC_INITIALIZE_MODE > {
		D2D1_DC_INITIALIZE_MODE_COPY(0),
		D2D1_DC_INITIALIZE_MODE_CLEAR(1),
		D2D1_DC_INITIALIZE_MODE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_DC_INITIALIZE_MODE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_DC_INITIALIZE_MODE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_DC_INITIALIZE_MODE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_DEBUG_LEVEL implements IntValuedEnum<D2D1_DEBUG_LEVEL > {
		D2D1_DEBUG_LEVEL_NONE(0),
		D2D1_DEBUG_LEVEL_ERROR(1),
		D2D1_DEBUG_LEVEL_WARNING(2),
		D2D1_DEBUG_LEVEL_INFORMATION(3),
		D2D1_DEBUG_LEVEL_FORCE_DWORD(0x7FFFFFFF);
		D2D1_DEBUG_LEVEL(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_DEBUG_LEVEL > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_DEBUG_LEVEL > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}
	public enum D2D1_FACTORY_TYPE implements IntValuedEnum<D2D1_FACTORY_TYPE > {
		/**
		 * The resulting factory and derived resources may only be invoked serially.
         * Reference counts on resources are interlocked, however, resource and render
         * target state is not protected from multi-threaded access.
		 */
		D2D1_FACTORY_TYPE_SINGLE_THREADED(0),
		/**
		 * The resulting factory may be invoked from multiple threads. Returned resources
         * use interlocked reference counting and their state is protected.
		 */
		D2D1_FACTORY_TYPE_MULTI_THREADED(1),
		D2D1_FACTORY_TYPE_FORCE_DWORD(0x7FFFFFFF);
		D2D1_FACTORY_TYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<D2D1_FACTORY_TYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static ValuedEnum<D2D1_FACTORY_TYPE > fromValue(long value) {
			return FlagSet.fromValue(value, values());
		}
	}

	
	public static native int D2D1CreateFactory(ValuedEnum<D2D1_FACTORY_TYPE> factoryType, Pointer<Byte> riid, Pointer<D2D1_FACTORY_OPTIONS> pFactoryOptions, Pointer<Pointer<ID2D1Factory>> ppIFactory);
	
	public static native int D2D1MakeRotateMatrix(float angle, D2D1_POINT_2F center, Pointer<D2D1_MATRIX_3X2_F> matrix);
	
	public static native int D2D1MakeSkewMatrix(float angleX, float angleY, D2D1_POINT_2F center, Pointer<D2D1_MATRIX_3X2_F> matrix);
	
	public static native int D2D1IsMatrixInvertible(Pointer<D2D1_MATRIX_3X2_F> matrix);
	
	public static native int D2D1InvertMatrix(Pointer<D2D1_MATRIX_3X2_F> matrix);
	
	
	// "Javanized" methods
	public static ID2D1Factory D2D1CreateFactory(ValuedEnum<D2D1_FACTORY_TYPE> factoryType, D2D1_FACTORY_OPTIONS pFactoryOptions) throws D2D1Exception {
		Pointer<Pointer<ID2D1Factory>> ppfactory = allocatePointer(ID2D1Factory.class);
		
		try {
			int result = D2D1CreateFactory(factoryType, COMRuntime.getIID(ID2D1Factory.class), pointerTo(pFactoryOptions), ppfactory);
			if(result != 0) {
				throw new D2D1Exception(result);
			}		
		
			return ppfactory.get().getNativeObject(ID2D1Factory.class);
		} finally {
			ppfactory.release();
		}
	}
	
}
