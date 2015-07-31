package com.fourthskyinteractive.dx4j.d3d11.states;

import com.fourthskyinteractive.dx4j.util.Describable;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

import static org.bridj.Pointer.allocate;

@IID("75b68faa-347d-4159-8f45-a0640f01cd9a")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11BlendState extends ID3D11DeviceChild implements Describable {

	private int hashCode;
	
	public ID3D11BlendState() {
		super();
	}
//	public ID3D11BlendState(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0)
	public final native void GetDesc(Pointer<D3D11_BLEND_DESC> pDesc);

    @SuppressWarnings("unchecked")
	@Override
    public D3D11_BLEND_DESC GetDesc() {
        Pointer<D3D11_BLEND_DESC> pDesc = null;

        try {
            pDesc = allocate(D3D11_BLEND_DESC.class);
            this.GetDesc(pDesc);
            return pDesc.get();
        } finally {
            if (pDesc != null)
                pDesc.release();
        }
    }
	
	public int hashCode() {
		if(hashCode == 0) {
			hashCode = GetDesc().hashCode();
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
