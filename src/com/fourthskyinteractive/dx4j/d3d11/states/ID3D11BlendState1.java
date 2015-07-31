package com.fourthskyinteractive.dx4j.d3d11.states;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import static org.bridj.Pointer.allocate;

@IID("")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11BlendState1 extends ID3D11BlendState {

	public ID3D11BlendState1() {
		// TODO Auto-generated constructor stub
	}

	@Virtual(0)
	public final native void GetDesc1(Pointer<D3D11_BLEND_DESC1> pDesc);

    public D3D11_BLEND_DESC1 GetDesc1() {
        Pointer<D3D11_BLEND_DESC1> pDesc = null;

        try {
            pDesc = allocate(D3D11_BLEND_DESC1.class);
            this.GetDesc1(pDesc);
            return pDesc.get();
        } finally {
            if (pDesc != null)
                pDesc.release();
        }
    }
}
