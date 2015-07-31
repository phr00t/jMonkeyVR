package com.fourthskyinteractive.dx4j.d2d1.resources;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.dwrite.IDWriteRenderingParams;

@IID("28506e39-ebf6-46a1-bb47-fd85565ab957")
@Library("d2d1")
@Runtime(COMRuntime.class)
public class ID2D1DrawingStateBlock extends ID2D1Resource {

	public ID2D1DrawingStateBlock() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ID2D1DrawingStateBlock(Pointer<? extends IUnknown> peer) {
//		super(peer);
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * Retrieves the state currently contained within this state block resource.
	 * 
	 * @param stateDescription
	 */
	@Virtual(0)
	public native void GetDescription(Pointer<D2D1_DRAWING_STATE_DESCRIPTION> stateDescription);
	
	/**
	 * Sets the state description of this state block resource.
	 * 
	 * @param stateDescription
	 */
	@Virtual(1)
	public native void SetDescription(Pointer<D2D1_DRAWING_STATE_DESCRIPTION> stateDescription);
	
	/**
	 * Sets the text rendering parameters of this state block resource.
	 * 
	 * @param textRenderingParams
	 */
	@Virtual(2)
	public native void SetTextRenderingParams(Pointer<IDWriteRenderingParams> textRenderingParams);
	
	/**
	 * Retrieves the text rendering parameters contained within this state block
     * resource. If a NULL text rendering parameter was specified, NULL will be
     * returned.
     * 
	 * @param textRenderingParams
	 */
	@Virtual(3)
	public native void GetTextRenderingParams(Pointer<Pointer<IDWriteRenderingParams>> textRenderingParams);
}
