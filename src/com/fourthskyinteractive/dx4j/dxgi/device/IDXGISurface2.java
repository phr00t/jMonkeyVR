package com.fourthskyinteractive.dx4j.dxgi.device;

import com.fourthskyinteractive.dx4j.dxgi.DXGIException;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import static org.bridj.Pointer.allocateInt;
import static org.bridj.Pointer.allocatePointer;

@IID("aba496dd-b617-4cb8-a866-bc44d7eb1fa2")
@Library("DXGI")
@Runtime(COMRuntime.class)
public class IDXGISurface2 extends IDXGISurface1 {

	public IDXGISurface2() {
		// TODO Auto-generated constructor stub
	}

    @Deprecated
	@Virtual(0)
	public final native int GetResource(Pointer<Byte> riid, Pointer<Pointer<?>> ppParentResource, Pointer<Integer> pSubresourceIndex);

    public final <T extends IUnknown> T GetResource(Class<T> resourceClass) throws DXGIException {
        // Get GUID of class
        Pointer<Byte> guid = COMRuntime.getIID(resourceClass);

        // Get back buffer pointer
        Pointer<Pointer<?>> pp = allocatePointer();

        Pointer<Integer> pIndex = allocateInt();

        try {
            int result = this.GetResource(guid, pp, pIndex);
            if(result != 0) {
                throw new DXGIException(result);
            }

            return pp.get().getNativeObject(resourceClass);

        } finally {
            pp.release();
            pIndex.release();
        }
    }
}
