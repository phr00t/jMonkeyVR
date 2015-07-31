package com.fourthskyinteractive.dx4j.d3d11.states;

import static org.bridj.Pointer.allocate;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("1217d7a6-5039-418c-b042-9cbe256afd6e")
@Library("d3d11_1")
@Runtime(COMRuntime.class)
public class ID3D11RasterizerState1 extends ID3D11RasterizerState {

	private int hashCode;
	
	public ID3D11RasterizerState1() {
		super();
	}
//	public ID3D11RasterizerState(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public final native void GetDesc1(Pointer<D3D11_RASTERIZER_DESC1> pDesc);

    @SuppressWarnings("unchecked")
    public D3D11_RASTERIZER_DESC1 GetDesc1() {
        Pointer<D3D11_RASTERIZER_DESC1> pDesc = null;

        try {
            pDesc = allocate(D3D11_RASTERIZER_DESC1.class);
            this.GetDesc1(pDesc);
            return pDesc.get();
        } finally {
            if (pDesc != null)
                pDesc.release();
        }
    }
	
	public int hashCode() {
		if(hashCode == 0) {
			hashCode = GetDesc1().hashCode();
		}
		return hashCode;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(hashCode() != obj.hashCode()) 
			return false;
		
		return true;
	}	
}
