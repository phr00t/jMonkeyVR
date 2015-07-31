package com.fourthskyinteractive.dx4j.d3d11.resources;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import static org.bridj.Pointer.allocate;

@IID("48570b85-d1ee-4fcd-a250-eb350722b037")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11Buffer extends ID3D11Resource {
	public ID3D11Buffer() {
		super();
	}
//	public ID3D11Buffer(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void GetDesc(Pointer<D3D11_BUFFER_DESC> pDesc);

    @SuppressWarnings("unchecked")
	@Override
    public D3D11_BUFFER_DESC GetDesc() {
        Pointer<D3D11_BUFFER_DESC> pDesc = null;

        try {
            pDesc = allocate(D3D11_BUFFER_DESC.class);
            this.GetDesc(pDesc);
            return pDesc.get();
        } finally {
            if (pDesc != null)
                pDesc.release();
        }
    }
}
