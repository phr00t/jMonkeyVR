package com.fourthskyinteractive.dx4j.d3d11.query;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import static org.bridj.Pointer.allocate;

@IID("d6c00747-87b7-425e-b84d-44d108560afd")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11Query extends ID3D11Asynchronous {
	public ID3D11Query() {
		super();
	}
//	public ID3D11Query(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void GetDesc(Pointer<D3D11_QUERY_DESC> pDesc);

    @SuppressWarnings("unchecked")
	@Override
    public D3D11_QUERY_DESC GetDesc() {
        Pointer<D3D11_QUERY_DESC> pDesc = null;

        try {
            pDesc = allocate(D3D11_QUERY_DESC.class);
            this.GetDesc(pDesc);
            return pDesc.get();
        } finally {
            pDesc.release();
        }
    }
}
