package com.fourthskyinteractive.dx4j.d2d1.core;

import static org.bridj.Pointer.allocatePointer;
import static org.bridj.Pointer.pointerTo;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_FILL_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1Exception;
import com.fourthskyinteractive.dx4j.d2d1.resources.D2D1_DRAWING_STATE_DESCRIPTION;
import com.fourthskyinteractive.dx4j.d2d1.resources.D2D1_STROKE_STYLE_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1DrawingStateBlock;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1StrokeStyle;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_ELLIPSE;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_MATRIX_3X2_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_RECT_F;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.D2D1_ROUNDED_RECT;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1EllipseGeometry;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1Geometry;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1GeometryGroup;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1PathGeometry;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1RectangleGeometry;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1RoundedRectangleGeometry;
import com.fourthskyinteractive.dx4j.d2d1.resources.geometry.ID2D1TransformedGeometry;
import com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget.D2D1_HWND_RENDER_TARGET_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget.D2D1_RENDER_TARGET_PROPERTIES;
import com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget.ID2D1DCRenderTarget;
import com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget.ID2D1HwndRenderTarget;
import com.fourthskyinteractive.dx4j.d2d1.resources.rendertarget.ID2D1RenderTarget;
import com.fourthskyinteractive.dx4j.dwrite.IDWriteRenderingParams;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGISurface;
import com.fourthskyinteractive.dx4j.wic.IWICBitmap;

@IID("06152247-6f50-465a-9245-118bfd3b6007")
@Library("D2D1")
@Runtime(COMRuntime.class)
public class ID2D1Factory extends IUnknown {
	public ID2D1Factory() {
		super();
	}
//	public ID2D1Factory(Pointer<? extends IUnknown> peer) {
//		super(peer);
//	}
	
	/**
	 * Cause the factory to refresh any system metrics that it might have been snapped
     * on factory creation.
     * 
	 * @return
	 */
	@Virtual(0)
	public native int ReloadSystemMetrics();
	
	/**
	 * Retrieves the current desktop DPI. To refresh this, call ReloadSystemMetrics.
	 * 
	 * @param dpiX
	 * @param dpiY
	 */
	@Virtual(1)
	public native void GetDesktopDpi(Pointer<Float> dpiX, Pointer<Float> dpiY);
	
	@Virtual(2)
	public native int CreateRectangleGeometry(Pointer<D2D1_RECT_F> rect, Pointer<Pointer<ID2D1RectangleGeometry>> rectangleGeometry);
	
	@Virtual(3)
	public native int CreateRoundedRectangleGeometry(Pointer<D2D1_ROUNDED_RECT> roundedRectangle, Pointer<Pointer<ID2D1RoundedRectangleGeometry>> roundedRectangleGeometry);
	
	@Virtual(4)
	public native int CreateEllipseGeometry(Pointer<D2D1_ELLIPSE> ellipse, Pointer<Pointer<ID2D1EllipseGeometry>> ellipseGeometry);
	
	@Virtual(5)
	public native int CreateGeometryGroup(ValuedEnum<D2D1_FILL_MODE> fillMode, Pointer<Pointer<ID2D1Geometry>> geometries, int geometriesCount, Pointer<Pointer<ID2D1GeometryGroup>> geometryGroup);
	
	@Virtual(6)
	public native int CreateTransformedGeometry(Pointer<ID2D1Geometry> sourceGeometry, Pointer<D2D1_MATRIX_3X2_F> transform, Pointer<Pointer<ID2D1TransformedGeometry>> transformedGeometry);
	
	@Virtual(7)
	public native int CreatePathGeometry(Pointer<Pointer<ID2D1PathGeometry>> pathGeometry);
	
	@Virtual(8)
	public native int CreateStrokeStyle(Pointer<D2D1_STROKE_STYLE_PROPERTIES> strokeStyleProperties, Pointer<Float> dashes, int dashesCount, Pointer<Pointer<ID2D1StrokeStyle>> strokeStyle);
	
	/**
	 * Creates a new drawing state block, this can be used in subsequent SaveDrawingState and RestoreDrawingState operations on the render target.
	 * 
	 * @param drawingStateDescription
	 * @param textRenderingParams
	 * @param drawingStateBlock
	 * @return
	 */
	@Virtual(9)
	public native int CreateDrawingStateBlock(Pointer<D2D1_DRAWING_STATE_DESCRIPTION> drawingStateDescription, Pointer<IDWriteRenderingParams> textRenderingParams, Pointer<Pointer<ID2D1DrawingStateBlock>> drawingStateBlock);
	
	/**
	 * Creates a render target which is a source of bitmaps.
	 * 
	 * @param target
	 * @param renderTargetProperties
	 * @param renderTarget
	 * @return
	 */
	@Virtual(10)
	public native int CreateWicBitmapRenderTarget(Pointer<IWICBitmap> target, Pointer<D2D1_RENDER_TARGET_PROPERTIES> renderTargetProperties, Pointer<Pointer<ID2D1RenderTarget>> renderTarget);
	
	/**
	 * Creates a render target that appears on the display.
	 * 
	 * @param renderTargetProperties
	 * @param hwndRenderTargetProperties
	 * @param hwndRenderTarget
	 * @return
	 */
	@Virtual(11)
	public native int CreateHwndRenderTarget(Pointer<D2D1_RENDER_TARGET_PROPERTIES> renderTargetProperties, Pointer<D2D1_HWND_RENDER_TARGET_PROPERTIES> hwndRenderTargetProperties, Pointer<Pointer<ID2D1HwndRenderTarget>> hwndRenderTarget);
	
	/**
	 * Creates a render target that draws to a DXGI Surface. The device that owns the surface is used for rendering.
     * 
	 * @param dxgiSurface
	 * @param renderTargetProperties
	 * @param renderTarget
	 * @return
	 */
	@Virtual(12)
	public native int CreateDxgiSurfaceRenderTarget(Pointer<IDXGISurface> dxgiSurface, Pointer<D2D1_RENDER_TARGET_PROPERTIES> renderTargetProperties, Pointer<Pointer<ID2D1RenderTarget>> renderTarget);
	
	/**
	 * Creates a render target that draws to a GDI device context.
	 * 
	 * @param renderTargetProperties
	 * @param dcRenderTarget
	 * @return
	 */
	@Virtual(13)
	public native int CreateDCRenderTarget(Pointer<D2D1_RENDER_TARGET_PROPERTIES> renderTargetProperties, Pointer<Pointer<ID2D1DCRenderTarget>> dcRenderTarget);
	
	// "Javanized" methods
	
	public ID2D1RectangleGeometry CreateRectangleGeometry(D2D1_RECT_F rect) throws D2D1Exception {
		Pointer<Pointer<ID2D1RectangleGeometry>> pp = allocatePointer(ID2D1RectangleGeometry.class);
		
		try {
			int result = CreateRectangleGeometry(pointerTo(rect), pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1RectangleGeometry.class);
		} finally {
			pp.release();
		}
	}
	
	public ID2D1RoundedRectangleGeometry CreateRoundedRectangleGeometry(D2D1_ROUNDED_RECT rect) throws D2D1Exception {
		Pointer<Pointer<ID2D1RoundedRectangleGeometry>> pp = allocatePointer(ID2D1RoundedRectangleGeometry.class);
		
		try {
			int result = CreateRoundedRectangleGeometry(pointerTo(rect), pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1RoundedRectangleGeometry.class);
		} finally {
			pp.release();
		}
	}
	
	public ID2D1EllipseGeometry CreateEllipseGeometry(D2D1_ELLIPSE rect) throws D2D1Exception {
		Pointer<Pointer<ID2D1EllipseGeometry>> pp = allocatePointer(ID2D1EllipseGeometry.class);
		
		try {
			int result = CreateEllipseGeometry(pointerTo(rect), pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1EllipseGeometry.class);
		} finally {
			pp.release();
		}
	}
	
	public ID2D1TransformedGeometry CreateTransformedGeometry(ID2D1Geometry geometry, D2D1_MATRIX_3X2_F transform) throws D2D1Exception {
		Pointer<Pointer<ID2D1TransformedGeometry>> pp = allocatePointer(ID2D1TransformedGeometry.class);
		
		try {
			int result = CreateTransformedGeometry(pointerTo(geometry), pointerTo(transform), pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1TransformedGeometry.class);
		} finally {
			pp.release();
		}
	}
	
	public ID2D1StrokeStyle CreateStrokeStyle(D2D1_STROKE_STYLE_PROPERTIES props, Pointer<Float> dashes, int dashesCount) throws D2D1Exception {
		Pointer<Pointer<ID2D1StrokeStyle>> pp = allocatePointer(ID2D1StrokeStyle.class);
		
		try {
			int result = CreateStrokeStyle(pointerTo(props), dashes, dashesCount, pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1StrokeStyle.class);
		} finally {
			pp.release();
		}
	}
	
	public ID2D1DrawingStateBlock CreateDrawingStateBlock(D2D1_DRAWING_STATE_DESCRIPTION desc) throws D2D1Exception {
		Pointer<Pointer<ID2D1DrawingStateBlock>> pp = allocatePointer(ID2D1DrawingStateBlock.class);
		
		try {
			int result = CreateDrawingStateBlock(pointerTo(desc), null, pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1DrawingStateBlock.class);
		} finally {
			pp.release();
		}
	}
	
	public ID2D1HwndRenderTarget CreateHwndRenderTarget(D2D1_RENDER_TARGET_PROPERTIES renderTargetProperties, D2D1_HWND_RENDER_TARGET_PROPERTIES hwndRenderTargetProperties) throws D2D1Exception {
		Pointer<Pointer<ID2D1HwndRenderTarget>> pp = allocatePointer(ID2D1HwndRenderTarget.class);
		
		try {
			int result = CreateHwndRenderTarget(renderTargetProperties != null ? pointerTo(renderTargetProperties) : null, 
												hwndRenderTargetProperties != null ? pointerTo(hwndRenderTargetProperties) : null, 
												pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1HwndRenderTarget.class);
		} finally {
			pp.release();
		}
	}
	
	public ID2D1RenderTarget CreateDxgiSurfaceRenderTarget(IDXGISurface dxgiSurface, D2D1_RENDER_TARGET_PROPERTIES renderTargetProperties) throws D2D1Exception {
		Pointer<Pointer<ID2D1RenderTarget>> pp = allocatePointer(ID2D1RenderTarget.class);
		
		try {
			int result = CreateDxgiSurfaceRenderTarget(pointerTo(dxgiSurface), pointerTo(renderTargetProperties), pp);
			if(result != 0) {
				throw new D2D1Exception(result);
			}
			
			return pp.get().getNativeObject(ID2D1RenderTarget.class);
		} finally {
			pp.release();
		}
	}
	
}
