package com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget;


import static org.bridj.Pointer.allocatePointer;
import static org.bridj.Pointer.pointerTo;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import com.fourthskyinteractive.dx4j.d2d1.D2D1Exception;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_ANTIALIAS_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_BITMAP_INTERPOLATION_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_DRAW_TEXT_OPTIONS;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_EXTEND_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_GAMMA;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_OPACITY_MASK_CONTENT;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_TEXT_ANTIALIAS_MODE;
import com.fourthskyinteractive.dx4j.d2d1.core.D2D1_PIXEL_FORMAT;
import com.fourthskyinteractive.dx4j.d2d1.resources.D2D1_GRADIENT_STOP;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1DrawingStateBlock;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1GradientStopCollection;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1Mesh;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1Resource;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1StrokeStyle;
import com.fourthskyinteractive.dx4j.d2d1.resources.bitmap.D2D1_BITMAP_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.bitmap.ID2D1Bitmap;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.D2D1_BITMAP_BRUSH_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.D2D1_BRUSH_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.D2D1_LINEAR_GRADIENT_BRUSH_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.D2D1_RADIAL_GRADIENT_BRUSH_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.ID2D1BitmapBrush;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.ID2D1Brush;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.ID2D1LinearGradientBrush;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.ID2D1RadialGradientBrush;
import com.fourthskyinteractive.dx4j.d2d1.resources.brush.ID2D1SolidColorBrush;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_COLOR_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_ELLIPSE;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_MATRIX_3X2_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_POINT_2F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_RECT_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_ROUNDED_RECT;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_SIZE_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_SIZE_U;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1Geometry;
import com.fourthskyinteractive.dx4j.d2d1.resources.layer.D2D1_LAYER_PARAMETERS;
import com.fourthskyinteractive.dx4j.d2d1.resources.layer.ID2D1Layer;
import com.fourthskyinteractive.dx4j.dwrite.DWRITE_GLYPH_RUN;
import com.fourthskyinteractive.dx4j.dwrite.DWrite.DWRITE_MEASURING_MODE;
import com.fourthskyinteractive.dx4j.dwrite.IDWriteRenderingParams;
import com.fourthskyinteractive.dx4j.dwrite.IDWriteTextFormat;
import com.fourthskyinteractive.dx4j.dwrite.IDWriteTextLayout;
import com.fourthskyinteractive.dx4j.wic.IWICBitmapSource;

@IID("2cd90694-12e2-11dc-9fed-001143a055f9")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1RenderTarget extends ID2D1Resource {

	public ID2D1RenderTarget() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1RenderTarget(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * Create a D2D bitmap by copying from memory, or create uninitialized.
	 * 
	 * @param size
	 * @param srcData
	 * @param pitch
	 * @param bitmapProperties
	 * @param bitmap
	 * @return
	 */
	@Virtual(0)
	public native int CreateBitmap(D2D1_SIZE_U size, Pointer<Byte> srcData, int pitch, Pointer<D2D1_BITMAP_PROPERTIES> bitmapProperties, Pointer<Pointer<ID2D1Bitmap>> bitmap);
	
	/**
	 * Create a D2D bitmap by copying a WIC bitmap.
	 * 
	 * @param wicBitmapSource
	 * @param bitmapProperties
	 * @param bitmap
	 * @return
	 */
	@Virtual(1)
	public native int CreateBitmapFromWicBitmap(Pointer<IWICBitmapSource> wicBitmapSource, Pointer<D2D1_BITMAP_PROPERTIES> bitmapProperties, Pointer<Pointer<ID2D1Bitmap>> bitmap);
	
	/**
	 * Create a D2D bitmap by sharing bits from another resource. The bitmap must be
     * compatible with the render target for the call to succeed.
     * For example, an IWICBitmap can be shared with a software target, or a DXGI
     * surface can be shared with a DXGI render target.
     * 
	 * @param pGUID
	 * @param data
	 * @param bitmapProperties
	 * @param bitmap
	 * @return
	 */
	@Virtual(2)
	public native int CreateSharedBitmap(Pointer<Byte> pGUID, Pointer<Byte> data, Pointer<D2D1_BITMAP_PROPERTIES> bitmapProperties, Pointer<Pointer<ID2D1Bitmap>> bitmap);
	
	/**
	 * Creates a bitmap brush. The bitmap is scaled, rotated, skewed or tiled to fill or pen a geometry.
	 * 
	 * @param bitmap
	 * @param bitmapBrushProperties
	 * @param brushProperties
	 * @param bitmapBrush
	 * @return
	 */
	@Virtual(3)
	public native int CreateBitmapBrush(Pointer<ID2D1Bitmap> bitmap, Pointer<D2D1_BITMAP_BRUSH_PROPERTIES> bitmapBrushProperties, Pointer<D2D1_BRUSH_PROPERTIES> brushProperties, Pointer<Pointer<ID2D1BitmapBrush>> bitmapBrush);
	
	@Virtual(4)
	public native int CreateSolidColorBrush(Pointer<D2D1_COLOR_F> color, Pointer<D2D1_BRUSH_PROPERTIES> brushProperties, Pointer<Pointer<ID2D1SolidColorBrush>> solidColorBrush);
	
	/**
	 * A gradient stop collection represents a set of stops in an ideal unit length.
     * This is the source resource for a linear gradient and radial gradient brush.
	 * 
	 * @param gradientStops
	 * @param gradientStopsCount
	 * @param colorInterpolationGamma Specifies which space the color interpolation occurs in.
	 * @param extendMode Specifies how the gradient will be extended outside of the unit length.
	 * @param ppGradientStopCollection
	 * @return
	 */
	@Virtual(5)
	public native int CreateGradientStopCollection(Pointer<D2D1_GRADIENT_STOP> gradientStops, int gradientStopsCount, ValuedEnum<D2D1_GAMMA> colorInterpolationGamma, ValuedEnum<D2D1_EXTEND_MODE> extendMode, Pointer<Pointer<ID2D1GradientStopCollection>> ppGradientStopCollection);
	
	@Virtual(6)
	public native int CreateLinearGradientBrush(Pointer<D2D1_LINEAR_GRADIENT_BRUSH_PROPERTIES> linearGradientBrushProperties, Pointer<D2D1_BRUSH_PROPERTIES> brushProperties, Pointer<ID2D1GradientStopCollection> gradientStopCollection, Pointer<Pointer<ID2D1LinearGradientBrush>> linearGradientBrush);
	
	@Virtual(7)
	public native int CreateRadialGradientBrush(Pointer<D2D1_RADIAL_GRADIENT_BRUSH_PROPERTIES> radialGradientBrushProperties, Pointer<D2D1_BRUSH_PROPERTIES> brushProperties, Pointer<ID2D1GradientStopCollection> gradientStopCollection, Pointer<Pointer<ID2D1RadialGradientBrush>> radialGradientBrush);
	
	/**
	 * Creates a bitmap render target whose bitmap can be used as a source for rendering in the API.
	 * 
	 * @param desiredSize The requested size of the target in DIPs. If the pixel size is not specified, the DPI is inherited from the parent target. However, the render target will never contain a fractional number of pixels.
	 * @param desiredPixelSize The requested size of the render target in pixels. If the DIP size is also specified, the DPI is calculated from these two values. If the desired size is not specified, the DPI is inherited from the parent render target. If neither value is specified, the compatible render target will be the same size and have the same DPI as the parent target.
	 * @param desiredFormat The desired pixel format. The format must be compatible with the parent render target type. If the format is not specified, it will be inherited from the parent render target. 
	 * @param options Allows the caller to retrieve a GDI compatible render target.
	 * @param bitmapRenderTarget The returned bitmap render target.
	 * @return
	 */
	@Virtual(8)
	public native int CreateCompatibleRenderTarget(Pointer<D2D1_SIZE_F> desiredSize, Pointer<D2D1_SIZE_U> desiredPixelSize, Pointer<D2D1_PIXEL_FORMAT> desiredFormat, ValuedEnum<D2D1_COMPATIBLE_RENDER_TARGET_OPTIONS> options, Pointer<Pointer<ID2D1BitmapRenderTarget>> bitmapRenderTarget);

	/**
	 * Creates a layer resource that can be used on any target and which will resize under the covers if necessary.
	 * 
	 * @param size The resolution independent minimum size hint for the layer resource. Specify this to prevent unwanted reallocation of the layer backing store. The size is in DIPs, but, it is unaffected by the current world transform. If the size is unspecified, the returned resource is a placeholder and the backing store will be allocated to be the minimum size that can hold the content when the layer is pushed.
	 * @param layer
	 * @return
	 */
	@Virtual(9)
	public native int CreateLayer(Pointer<D2D1_SIZE_F> size, Pointer<Pointer<ID2D1Layer>> layer);
	
	/**
	 * Create a D2D mesh.
	 * 
	 * @param mesh
	 * @return
	 */
	@Virtual(10)
	public native int CreateMesh(Pointer<Pointer<ID2D1Mesh>> mesh);
	
	@Virtual(11)
	public native void DrawLine(D2D1_POINT_2F point0, D2D1_POINT_2F point1, Pointer<? extends ID2D1Brush> brush, float strokeWidth, Pointer<ID2D1StrokeStyle> strokeStyle);
	
	@Virtual(12)
	public native void DrawRectangle(Pointer<D2D1_RECT_F> rect, Pointer<? extends ID2D1Brush> brush, float strokeWidth, Pointer<ID2D1StrokeStyle> strokeStyle);
	
	@Virtual(13)
	public native void FillRectangle(Pointer<D2D1_RECT_F> rect, Pointer<? extends ID2D1Brush> brush);
	
	@Virtual(14)
	public native void DrawRoundedRectangle(Pointer<D2D1_ROUNDED_RECT> roundedRect, Pointer<? extends ID2D1Brush> brush, float strokeWidth, Pointer<ID2D1StrokeStyle>strokeStyle);
	
	@Virtual(15)
	public native void FillRoundedRectangle(Pointer<D2D1_ROUNDED_RECT> roundedRect, Pointer<? extends ID2D1Brush> brush);
	
	@Virtual(16)
	public native void DrawEllipse(Pointer<D2D1_ELLIPSE> ellipse, Pointer<? extends ID2D1Brush> brush, float strokeWidth, Pointer<ID2D1StrokeStyle> strokeStyle);
	
	@Virtual(17)
	public native void FillEllipse(Pointer<D2D1_ELLIPSE> ellipse, Pointer<? extends ID2D1Brush> brush);
	
	@Virtual(18)
	public native void DrawGeometry(Pointer<ID2D1Geometry> geometry, Pointer<? extends ID2D1Brush> brush, float strokeWidth, Pointer<ID2D1StrokeStyle> strokeStyle);
	
	/**
	 * 
	 * @param geometry
	 * @param brush
	 * @param opacityBrush An optionally specified opacity brush. Only the alpha channel of the corresponding brush will be sampled and will be applied to the entire fill of the geometry. If this brush is specified, the fill brush must be a bitmap brush with an extend mode of D2D1_EXTEND_MODE_CLAMP.
	 */
	@Virtual(19)
	public native void FillGeometry(Pointer<ID2D1Geometry> geometry, Pointer<? extends ID2D1Brush> brush, Pointer<? extends ID2D1Brush> opacityBrush);

	/**
	 * Fill a mesh. Since meshes can only render aliased content, the render target antialiasing mode must be set to aliased.
	 * 
	 * @param mesh
	 * @param brush
	 */
	@Virtual(20)
	public native void FillMesh(Pointer<ID2D1Mesh> mesh, Pointer<? extends ID2D1Brush> brush);
	
	@Virtual(21)
	public native void FillOpacityMask(Pointer<ID2D1Bitmap> opacityMask, Pointer<? extends ID2D1Brush> brush, ValuedEnum<D2D1_OPACITY_MASK_CONTENT> content, Pointer<D2D1_RECT_F> destinationRectangle, Pointer<D2D1_RECT_F> sourceRectangle);
	
	@Virtual(22)
	public native void DrawBitmap(Pointer<ID2D1Bitmap> bitmap, Pointer<D2D1_RECT_F> destinationRectangle, float opacity, ValuedEnum<D2D1_BITMAP_INTERPOLATION_MODE> interpolationMode, Pointer<D2D1_RECT_F> sourceRectangle);
	
	@Virtual(23)
	public native void DrawText(Pointer<Byte> string, int stringLength, Pointer<IDWriteTextFormat> textFormat, Pointer<D2D1_RECT_F> layoutRect, Pointer<ID2D1Brush> defaultForegroundBrush, ValuedEnum<D2D1_DRAW_TEXT_OPTIONS> options, ValuedEnum<DWRITE_MEASURING_MODE> measuringMode);

	/**
	 * 
	 * @param origin
	 * @param textLayout
	 * @param defaultForegroundBrush
	 * @param options The specified text options. NOTE: By default the text is clipped to the layout bounds. This is derived from the origin and the layout bounds of the corresponding IDWriteTextLayout object.
	 */
	@Virtual(24)
	public native void DrawTextLayout(D2D1_POINT_2F origin, Pointer<IDWriteTextLayout> textLayout, Pointer<? extends ID2D1Brush> defaultForegroundBrush, ValuedEnum<D2D1_DRAW_TEXT_OPTIONS> options);
	
	@Virtual(25)
	public native void DrawGlyphRun(D2D1_POINT_2F baselineOrigin, Pointer<DWRITE_GLYPH_RUN> glyphRun, Pointer<? extends ID2D1Brush> foregroundBrush, DWRITE_MEASURING_MODE measuringMode);
	
	@Virtual(26)
	public native void SetTransform(Pointer<D2D1_MATRIX_3X2_F> transform);
	
	@Virtual(27)
	public native void GetTransform(Pointer<D2D1_MATRIX_3X2_F> transform);
	
	@Virtual(28)
	public native void SetAntialiasMode(ValuedEnum<D2D1_ANTIALIAS_MODE> antialiasMode);
	
	@Virtual(29)
	public native ValuedEnum<D2D1_ANTIALIAS_MODE> GetAntialiasMode();
	
	@Virtual(30)
	public native void SetTextAntialiasMode(ValuedEnum<D2D1_TEXT_ANTIALIAS_MODE> textAntialiasMode);
	
	@Virtual(31)
	public native ValuedEnum<D2D1_TEXT_ANTIALIAS_MODE> GetTextAntialiasMode();
	
	@Virtual(32)
	public native void SetTextRenderingParams(Pointer<IDWriteRenderingParams> textRenderingParams);
	
	/**
	 * Retrieve the text render parameters. NOTE: If NULL is specified to SetTextRenderingParameters, NULL will be returned.
     * 
	 * @param textRenderingParams
	 */
	@Virtual(33)
	public native void GetTextRenderingParams(Pointer<Pointer<IDWriteRenderingParams>> textRenderingParams);
	
	/**
	 * Set a tag to correspond to the succeeding primitives. If an error occurs rendering a primtive, the tags can be returned from the Flush or EndDraw call.
     * 
	 * @param tag1
	 * @param tag2
	 */
	@Virtual(34)
	public native void SetTags(long tag1, long tag2);
	
	/**
	 * Retrieves the currently set tags. This does not retrieve the tags corresponding to any primitive that is in error.
     * 
	 * @param tag1
	 * @param tag2
	 */
	@Virtual(35)
	public native void GetTags(Pointer<Long> tag1, Pointer<Long> tag2);
	
	/**
	 * Start a layer of drawing calls. The way in which the layer must be resolved is specified first as well as the logical resource that stores the layer parameters. The supplied layer resource might grow if the specified content cannot fit inside it. The layer will grow monitonically on each axis.
	 *
	 * @param layerParameters
	 * @param layer
	 */
	@Virtual(36)
	public native void PushLayer(Pointer<D2D1_LAYER_PARAMETERS> layerParameters, Pointer<ID2D1Layer> layer);
	
	/**
	 * Ends a layer that was defined with particular layer resources.
	 * 
	 */
	@Virtual(37)
	public native void PopLayer();
	
	@Virtual(38)
	public native int Flush(Pointer<Long> tag1, Pointer<Long> tag2);
	
	/**
	 * Gets the current drawing state and saves it into the supplied IDrawingStatckBlock.
     * 
	 * @param drawingStateBlock
	 */
	@Virtual(39)
	public native void SaveDrawingState(Pointer<ID2D1DrawingStateBlock> drawingStateBlock);
	
	/**
	 * Copies the state stored in the block interface.
	 * 
	 * @param drawingStateBlock
	 */
	@Virtual(40)
	public native void RestoreDrawingState(Pointer<ID2D1DrawingStateBlock> drawingStateBlock);
	
	/**
	 * Pushes a clip. The clip can be antialiased. The clip must be axis aligned. If the current world transform is not axis preserving, then the bounding box of the transformed clip rect will be used. The clip will remain in effect until a PopAxisAligned clip call is made.
     * 
	 * @param clipRect
	 * @param antialiasMode
	 */
	@Virtual(41)
	public native void PushAxisAlignedClip(Pointer<D2D1_RECT_F> clipRect, ValuedEnum<D2D1_ANTIALIAS_MODE> antialiasMode);
	
	@Virtual(42)
	public native void PopAxisAlignedClip();
	
	@Deprecated @Virtual(43)
	public native void Clear(Pointer<D2D1_COLOR_F> clearColor);
	
	@Virtual(44)
	public native void BeginDraw();
	
	/**
	 * Ends drawing on the render target, error results can be retrieved at this time, or when calling flush.
     * 
	 * @param tag1
	 * @param tag2
	 * @return
	 */
	@Virtual(45)
	public native int EndDraw(Pointer<Long> tag1, Pointer<Long> tag2);
	
	@Virtual(46)
	public native D2D1_PIXEL_FORMAT GetPixelFormat();

	/**
	 * Sets the DPI on the render target. This results in the render target being interpretted to a different scale. Neither DPI can be negative. If zero is specified for both, the system DPI is chosen. If one is zero and the other unspecified, the DPI is not changed.
     * 
	 * @param dpiX
	 * @param dpiY
	 */
	@Virtual(47)
	public native void SetDpi(float dpiX, float dpiY);

	/**
	 * Return the current DPI from the target.
	 * 
	 * @param pDpiX
	 * @param pDpiY
	 */
	@Virtual(48)
	public native void GetDpi(Pointer<Float> pDpiX, Pointer<Float> pDpiY);
	
	/**
	 * Returns the size of the render target in DIPs.
	 * 
	 * @return
	 */
	@Virtual(49)
	public native D2D1_SIZE_F GetSize();
	
	/**
	 * Returns the size of the render target in pixels.
	 * 
	 * @return
	 */
	@Virtual(50)
	public native D2D1_SIZE_U GetPixelSize();
	
	/**
	 * Returns the maximum bitmap and render target size that is guaranteed to be supported by the render target.
     * 
	 * @return
	 */
	@Virtual(51)
	public native long GetMaximumBitmapSize();
	
	/**
	 * Returns true if the given properties are supported by this render target. The DPI is ignored. NOTE: If the render target type is software, then neither D2D1_FEATURE_LEVEL_9 nor D2D1_FEATURE_LEVEL_10 will be considered to be supported.
     * 
	 * @param renderTargetProperties
	 * @return
	 */
	@Virtual(52)
	public native int IsSupported(Pointer<D2D1_RENDER_TARGET_PROPERTIES> renderTargetProperties);
	
	// "Javanized" methods
	
	public ID2D1SolidColorBrush CreateSolidColorBrush(D2D1_COLOR_F color, D2D1_BRUSH_PROPERTIES brushProperties) throws D2D1Exception {
		Pointer<Pointer<ID2D1SolidColorBrush>> pp = allocatePointer(ID2D1SolidColorBrush.class);
		
		try {
			int result = this.CreateSolidColorBrush(color != null ? pointerTo(color) : null, 
											  		brushProperties != null ? pointerTo(brushProperties) : null, 
											  		pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1SolidColorBrush.class);
		} finally {
			pp.release();
		}
	}
	
	public void SetTransform(D2D1_MATRIX_3X2_F matrix) {
		SetTransform(pointerTo(matrix));
	}
	
	public void Clear(D2D1_COLOR_F color) {
		Clear(pointerTo(color));
	}
	
	public void DrawLine(D2D1_POINT_2F point0, D2D1_POINT_2F point1, ID2D1Brush brush, float strokeWidth, ID2D1StrokeStyle strokeStyle) {
		DrawLine(point0, point1, 
				 brush != null ? pointerTo(brush) : null, 
				 strokeWidth, 
				 strokeStyle != null ? pointerTo(strokeStyle) : null);	
	}
}
