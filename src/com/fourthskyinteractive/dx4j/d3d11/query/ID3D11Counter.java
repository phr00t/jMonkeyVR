package com.fourthskyinteractive.dx4j.d3d11.query;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("6e8c49fb-a371-4770-b440-29086022b741")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11Counter extends ID3D11Asynchronous {
	public ID3D11Counter() {
		super();
	}
//	public ID3D11Counter(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public native void GetDesc(Pointer<D3D11_COUNTER_DESC> pDesc);

    @SuppressWarnings({ "unchecked", "null" })
	@Override
    public D3D11_COUNTER_DESC GetDesc() {
        Pointer<D3D11_COUNTER_DESC> pDesc = null;

        try {
            this.GetDesc(pDesc);
            return pDesc.get();
        } finally {
            pDesc.release();
        }
    }
}
