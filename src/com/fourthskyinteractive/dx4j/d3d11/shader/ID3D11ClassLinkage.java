package com.fourthskyinteractive.dx4j.d3d11.shader;

import static org.bridj.Pointer.allocatePointer;
import static org.bridj.Pointer.pointerToCString;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.d3d11.D3D11Exception;
import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceChild;

@IID("ddf57cba-9543-46e4-a12b-f207a0fe7fed")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11ClassLinkage extends ID3D11DeviceChild {

	public ID3D11ClassLinkage() {
		super();
	}
//	public ID3D11ClassLinkage(Pointer pointer) {
//		super(pointer);
//	}
	@Deprecated @Virtual(0)
	public native int GetClassInstance(Pointer<?> pClassInstanceName, int InstanceIndex, Pointer<Pointer<ID3D11ClassInstance>> ppInstance);
	@Deprecated @Virtual(1)
	public native int CreateClassInstance(Pointer<?> pClassInstanceName, int ConstantBufferOffset, int ConstantVectorOffset, int TextureOffset, int SamplerOffset, Pointer<Pointer<ID3D11ClassInstance>> ppInstance);
	
	public final ID3D11ClassInstance GetClassInstance(String instanceName, int instanceIndex) throws D3D11Exception {
		Pointer<Pointer<ID3D11ClassInstance>> pp = allocatePointer(ID3D11ClassInstance.class);
		
		try {
			int result = this.GetClassInstance(pointerToCString(instanceName), instanceIndex, pp);
			if (result != 0) {
				throw new D3D11Exception("Error getting class instance", result);
			}
			
			return pp.get().getNativeObject(ID3D11ClassInstance.class);
		} finally {
			pp.release();
			pp = null;
		}
	}
	
	public final ID3D11ClassInstance CreateClassInstance(String instanceName) throws D3D11Exception {
		Pointer<Pointer<ID3D11ClassInstance>> pp = allocatePointer(ID3D11ClassInstance.class);
		
		try {
			int result = this.CreateClassInstance(pointerToCString(instanceName), 0, 0, 0, 0, pp);
			if (result != 0) {
				throw new D3D11Exception("Error getting class instance", result);
			}
			
			return pp.get().getNativeObject(ID3D11ClassInstance.class);
		} finally {
			pp.release();
			pp = null;
		}
	}
	
	public final ID3D11ClassInstance CreateClassInstance(String instanceName, int constantBufferOffset, int constantVectorOffset, int textureOffset, int samplerOffset) throws D3D11Exception {
		Pointer<Pointer<ID3D11ClassInstance>> pp = allocatePointer(ID3D11ClassInstance.class);
		
		try {
			int result = this.CreateClassInstance(pointerToCString(instanceName), constantBufferOffset, constantVectorOffset, textureOffset, samplerOffset, pp);
			if (result != 0) {
				throw new D3D11Exception("Error getting class instance", result);
			}
			
			return pp.get().getNativeObject(ID3D11ClassInstance.class);
		} finally {
			pp.release();
			pp = null;
		}
	}
}
