package com.fourthskyinteractive.dx4j.d3d11.resources.views;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import static org.bridj.Pointer.allocate;

@IID("dfdba067-0b8d-4865-875b-d7b4516cc164")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11RenderTargetView extends ID3D11View {

	public ID3D11RenderTargetView() {
		super();
	}
//	public ID3D11RenderTargetView(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void GetDesc(Pointer<D3D11_RENDER_TARGET_VIEW_DESC> pDesc);

    @SuppressWarnings("unchecked")
	@Override
    public D3D11_RENDER_TARGET_VIEW_DESC GetDesc() {
        Pointer<D3D11_RENDER_TARGET_VIEW_DESC> pDesc = null;

        try {
            pDesc = allocate(D3D11_RENDER_TARGET_VIEW_DESC.class);
            this.GetDesc(pDesc);
            return pDesc.get();
        } finally {
            if (pDesc != null)
                pDesc.release();
        }
    }
}
