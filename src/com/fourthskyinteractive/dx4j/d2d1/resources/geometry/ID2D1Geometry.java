package com.fourthskyinteractive.dx4j.d2d1.resources.geometry;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_COMBINE_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_GEOMETRY_RELATION;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_GEOMETRY_SIMPLIFICATION_OPTION;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1Resource;
import com.fourthskyinteractive.dx4j.d2d1.resources.ID2D1StrokeStyle;

@IID("2cd906a1-12e2-11dc-9fed-001143a055f9")
@Library("D2D1")
@Runtime(COMRuntime.class)
public class ID2D1Geometry extends ID2D1Resource {

	public ID2D1Geometry() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1Geometry(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}
	
	/**
	 * Retrieve the bounds of the geometry, with an optional applied transform.
	 * 
	 * 
	 */
    @Virtual(0)
    public native int GetBounds(Pointer<D2D1_MATRIX_3X2_F> worldTransform, Pointer<D2D1_RECT_F> bounds);
    
    
    /**
     * Get the bounds of the corresponding geometry after it has been widened or have
     * an optional pen style applied.
     * 
     * 
     */
    @Virtual(1)
    public native int GetWidenedBounds(float strokeWidth, Pointer<ID2D1StrokeStyle> strokeStyle, Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<D2D1_RECT_F> bounds);
    
    
    //
    // Checks to see whether the corresponding penned and widened geometry contains the
    // given point.
    //
    @Virtual(2)
    public native int StrokeContainsPoint(D2D1_POINT_2F point, float strokeWidth, Pointer<ID2D1StrokeStyle> strokeStyle, Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<Boolean> contains);
    
    
    //
    // Test whether the given fill of this geometry would contain this point.
    //
    @Virtual(3)
    public native int FillContainsPoint(D2D1_POINT_2F point, Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<Boolean> contains);
    
    //
    // Compare how one geometry intersects or contains another geometry.
    //
    @Virtual(4)
    public native int CompareWithGeometry(Pointer<ID2D1Geometry> inputGeometry, Pointer<D2D1_MATRIX_3X2_F> inputGeometryTransform, float flatteningTolerance, Pointer<ValuedEnum<D2D1_GEOMETRY_RELATION>> relation);
    
    //
    // Converts a geometry to a simplified geometry that has arcs and quadratic beziers
    // removed.
    //
    @Virtual(5)
    public native int Simplify(ValuedEnum<D2D1_GEOMETRY_SIMPLIFICATION_OPTION> simplificationOption, Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<ID2D1SimplifiedGeometrySink> geometrySink);
    
    
    //
    // Tessellates a geometry into triangles.
    //
    @Virtual(6)
    public native int Tessellate(Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<ID2D1TessellationSink> tessellationSink);
    
    
    //
    // Performs a combine operation between the two geometries to produce a resulting
    // geometry.
    //
    @Virtual(7)
    public native int CombineWithGeometry(Pointer<ID2D1Geometry> inputGeometry, ValuedEnum<D2D1_COMBINE_MODE> combineMode, Pointer<D2D1_MATRIX_3X2_F> inputGeometryTransform, float flatteningTolerance, Pointer<ID2D1SimplifiedGeometrySink> geometrySink);
    
    
    //
    // Computes the outline of the geometry. The result is written back into a
    // simplified geometry sink.
    //
    @Virtual(8)
    public native int Outline(Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<ID2D1SimplifiedGeometrySink> geometrySink);
    
    
    //
    // Computes the area of the geometry.
    //
    @Virtual(9)
    public native int ComputeArea(Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<Float> area);
    
    
    //
    // Computes the length of the geometry.
    //
    @Virtual(10)
    public native int ComputeLength(Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<Float> length);
    
    
    //
    // Computes the point and tangent a given distance along the path.
    //
    @Virtual(11)
    public native int ComputePointAtLength(float length, Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<D2D1_POINT_2F> point, Pointer<D2D1_POINT_2F> unitTangentVector);
    
    
    //
    // Get the geometry and widen it as well as apply an optional pen style.
    //
    @Virtual(12)
    public native int Widen(float strokeWidth, Pointer<ID2D1StrokeStyle> strokeStyle, Pointer<D2D1_MATRIX_3X2_F> worldTransform, float flatteningTolerance, Pointer<ID2D1SimplifiedGeometrySink> geometrySink);
    
    /*
     
      //
    // Retrieve the bounds of the geometry, with an optional applied transform.
    //
    HRESULT
    GetBounds(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __out D2D1_RECT_F *bounds 
        ) CONST 
    {
        return GetBounds(&worldTransform, bounds);
    }
    
    
    //
    // Get the bounds of the corresponding geometry after it has been widened or have
    // an optional pen style applied.
    //
    HRESULT
    GetWidenedBounds(
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __out D2D1_RECT_F *bounds 
        ) CONST 
    {
        return GetWidenedBounds(strokeWidth, strokeStyle, &worldTransform, flatteningTolerance, bounds);
    }
    
    
    //
    // Get the bounds of the corresponding geometry after it has been widened or have
    // an optional pen style applied.
    //
    HRESULT
    GetWidenedBounds(
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __out D2D1_RECT_F *bounds 
        ) CONST 
    {
        return GetWidenedBounds(strokeWidth, strokeStyle, worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, bounds);
    }
    
    
    //
    // Get the bounds of the corresponding geometry after it has been widened or have
    // an optional pen style applied.
    //
    HRESULT
    GetWidenedBounds(
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __out D2D1_RECT_F *bounds 
        ) CONST 
    {
        return GetWidenedBounds(strokeWidth, strokeStyle, &worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, bounds);
    }
    
    HRESULT
    StrokeContainsPoint(
        D2D1_POINT_2F point,
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __out BOOL *contains 
        ) CONST 
    {
        return StrokeContainsPoint(point, strokeWidth, strokeStyle, &worldTransform, flatteningTolerance, contains);
    }
    
    
    //
    // Checks to see whether the corresponding penned and widened geometry contains the
    // given point.
    //
    HRESULT
    StrokeContainsPoint(
        D2D1_POINT_2F point,
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __out BOOL *contains 
        ) CONST 
    {
        return StrokeContainsPoint(point, strokeWidth, strokeStyle, worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, contains);
    }
    
    HRESULT
    StrokeContainsPoint(
        D2D1_POINT_2F point,
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __out BOOL *contains 
        ) CONST 
    {
        return StrokeContainsPoint(point, strokeWidth, strokeStyle, &worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, contains);
    }
    
    HRESULT
    FillContainsPoint(
        D2D1_POINT_2F point,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __out BOOL *contains 
        ) CONST 
    {
        return FillContainsPoint(point, &worldTransform, flatteningTolerance, contains);
    }
    
    
    //
    // Test whether the given fill of this geometry would contain this point.
    //
    HRESULT
    FillContainsPoint(
        D2D1_POINT_2F point,
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __out BOOL *contains 
        ) CONST 
    {
        return FillContainsPoint(point, worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, contains);
    }
    
    HRESULT
    FillContainsPoint(
        D2D1_POINT_2F point,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __out BOOL *contains 
        ) CONST 
    {
        return FillContainsPoint(point, &worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, contains);
    }
    
    
    //
    // Compare how one geometry intersects or contains another geometry.
    //
    HRESULT
    CompareWithGeometry(
        __in ID2D1Geometry *inputGeometry,
        CONST D2D1_MATRIX_3X2_F &inputGeometryTransform,
        FLOAT flatteningTolerance,
        __out D2D1_GEOMETRY_RELATION *relation 
        ) CONST 
    {
        return CompareWithGeometry(inputGeometry, &inputGeometryTransform, flatteningTolerance, relation);
    }
    
    
    //
    // Compare how one geometry intersects or contains another geometry.
    //
    HRESULT
    CompareWithGeometry(
        __in ID2D1Geometry *inputGeometry,
        __in_opt CONST D2D1_MATRIX_3X2_F *inputGeometryTransform,
        __out D2D1_GEOMETRY_RELATION *relation 
        ) CONST 
    {
        return CompareWithGeometry(inputGeometry, inputGeometryTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, relation);
    }
    
    
    //
    // Compare how one geometry intersects or contains another geometry.
    //
    HRESULT
    CompareWithGeometry(
        __in ID2D1Geometry *inputGeometry,
        CONST D2D1_MATRIX_3X2_F &inputGeometryTransform,
        __out D2D1_GEOMETRY_RELATION *relation 
        ) CONST 
    {
        return CompareWithGeometry(inputGeometry, &inputGeometryTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, relation);
    }
    
    
    //
    // Converts a geometry to a simplified geometry that has arcs and quadratic beziers
    // removed.
    //
    HRESULT
    Simplify(
        D2D1_GEOMETRY_SIMPLIFICATION_OPTION simplificationOption,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Simplify(simplificationOption, &worldTransform, flatteningTolerance, geometrySink);
    }
    
    
    //
    // Converts a geometry to a simplified geometry that has arcs and quadratic beziers
    // removed.
    //
    HRESULT
    Simplify(
        D2D1_GEOMETRY_SIMPLIFICATION_OPTION simplificationOption,
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Simplify(simplificationOption, worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
    
    
    //
    // Converts a geometry to a simplified geometry that has arcs and quadratic beziers
    // removed.
    //
    HRESULT
    Simplify(
        D2D1_GEOMETRY_SIMPLIFICATION_OPTION simplificationOption,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Simplify(simplificationOption, &worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
    
    
    //
    // Tessellates a geometry into triangles.
    //
    HRESULT
    Tessellate(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __in ID2D1TessellationSink *tessellationSink 
        ) CONST 
    {
        return Tessellate(&worldTransform, flatteningTolerance, tessellationSink);
    }
    
    
    //
    // Tessellates a geometry into triangles.
    //
    HRESULT
    Tessellate(
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __in ID2D1TessellationSink *tessellationSink 
        ) CONST 
    {
        return Tessellate(worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, tessellationSink);
    }
    
    
    //
    // Tessellates a geometry into triangles.
    //
    HRESULT
    Tessellate(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __in ID2D1TessellationSink *tessellationSink 
        ) CONST 
    {
        return Tessellate(&worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, tessellationSink);
    }
    
    
    //
    // Performs a combine operation between the two geometries to produce a resulting
    // geometry.
    //
    HRESULT
    CombineWithGeometry(
        __in ID2D1Geometry *inputGeometry,
        D2D1_COMBINE_MODE combineMode,
        CONST D2D1_MATRIX_3X2_F &inputGeometryTransform,
        FLOAT flatteningTolerance,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return CombineWithGeometry(inputGeometry, combineMode, &inputGeometryTransform, flatteningTolerance, geometrySink);
    }
    
    
    //
    // Performs a combine operation between the two geometries to produce a resulting
    // geometry.
    //
    HRESULT
    CombineWithGeometry(
        __in ID2D1Geometry *inputGeometry,
        D2D1_COMBINE_MODE combineMode,
        __in_opt CONST D2D1_MATRIX_3X2_F *inputGeometryTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return CombineWithGeometry(inputGeometry, combineMode, inputGeometryTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
    
    
    //
    // Performs a combine operation between the two geometries to produce a resulting
    // geometry.
    //
    HRESULT
    CombineWithGeometry(
        __in ID2D1Geometry *inputGeometry,
        D2D1_COMBINE_MODE combineMode,
        CONST D2D1_MATRIX_3X2_F &inputGeometryTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return CombineWithGeometry(inputGeometry, combineMode, &inputGeometryTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
    
    
    //
    // Computes the outline of the geometry. The result is written back into a
    // simplified geometry sink.
    //
    HRESULT
    Outline(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Outline(&worldTransform, flatteningTolerance, geometrySink);
    }
    
    
    //
    // Computes the outline of the geometry. The result is written back into a
    // simplified geometry sink.
    //
    HRESULT
    Outline(
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Outline(worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
    
    
    //
    // Computes the outline of the geometry. The result is written back into a
    // simplified geometry sink.
    //
    HRESULT
    Outline(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Outline(&worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
    
    
    //
    // Computes the area of the geometry.
    //
    HRESULT
    ComputeArea(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __out FLOAT *area 
        ) CONST 
    {
        return ComputeArea(&worldTransform, flatteningTolerance, area);
    }
    
    
    //
    // Computes the area of the geometry.
    //
    HRESULT
    ComputeArea(
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __out FLOAT *area 
        ) CONST 
    {
        return ComputeArea(worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, area);
    }
    
    
    //
    // Computes the area of the geometry.
    //
    HRESULT
    ComputeArea(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __out FLOAT *area 
        ) CONST 
    {
        return ComputeArea(&worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, area);
    }
    
    
    //
    // Computes the length of the geometry.
    //
    HRESULT
    ComputeLength(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __out FLOAT *length 
        ) CONST 
    {
        return ComputeLength(&worldTransform, flatteningTolerance, length);
    }
    
    
    //
    // Computes the length of the geometry.
    //
    HRESULT
    ComputeLength(
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __out FLOAT *length 
        ) CONST 
    {
        return ComputeLength(worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, length);
    }
    
    
    //
    // Computes the length of the geometry.
    //
    HRESULT
    ComputeLength(
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __out FLOAT *length 
        ) CONST 
    {
        return ComputeLength(&worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, length);
    }
    
    
    //
    // Computes the point and tangent a given distance along the path.
    //
    HRESULT
    ComputePointAtLength(
        FLOAT length,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __out_opt D2D1_POINT_2F *point,
        __out_opt D2D1_POINT_2F *unitTangentVector 
        ) CONST 
    {
        return ComputePointAtLength(length, &worldTransform, flatteningTolerance, point, unitTangentVector);
    }
    
    
    //
    // Computes the point and tangent a given distance along the path.
    //
    HRESULT
    ComputePointAtLength(
        FLOAT length,
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __out_opt D2D1_POINT_2F *point,
        __out_opt D2D1_POINT_2F *unitTangentVector 
        ) CONST 
    {
        return ComputePointAtLength(length, worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, point, unitTangentVector);
    }
    
    
    //
    // Computes the point and tangent a given distance along the path.
    //
    HRESULT
    ComputePointAtLength(
        FLOAT length,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __out_opt D2D1_POINT_2F *point,
        __out_opt D2D1_POINT_2F *unitTangentVector 
        ) CONST 
    {
        return ComputePointAtLength(length, &worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, point, unitTangentVector);
    }
    
    
    //
    // Get the geometry and widen it as well as apply an optional pen style.
    //
    HRESULT
    Widen(
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        FLOAT flatteningTolerance,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Widen(strokeWidth, strokeStyle, &worldTransform, flatteningTolerance, geometrySink);
    }
    
    
    //
    // Get the geometry and widen it as well as apply an optional pen style.
    //
    HRESULT
    Widen(
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        __in_opt CONST D2D1_MATRIX_3X2_F *worldTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Widen(strokeWidth, strokeStyle, worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
    
    
    //
    // Get the geometry and widen it as well as apply an optional pen style.
    //
    HRESULT
    Widen(
        FLOAT strokeWidth,
        __in_opt ID2D1StrokeStyle *strokeStyle,
        CONST D2D1_MATRIX_3X2_F &worldTransform,
        __in ID2D1SimplifiedGeometrySink *geometrySink 
        ) CONST 
    {
        return Widen(strokeWidth, strokeStyle, &worldTransform, D2D1_DEFAULT_FLATTENING_TOLERANCE, geometrySink);
    }
      
     */
}
