package com.fourthskyinteractive.dx4j.dxgi.device;

import com.fourthskyinteractive.dx4j.dxgi.DXGIException;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.windows.HANDLE;
import com.fourthskyinteractive.dx4j.windows.kernel32.SECURITY_ATTRIBUTES;

import static org.bridj.Pointer.*;

@IID("30961379-4609-4a41-998e-54fe567ee0c1")
@Library("DXGI")
@Runtime(COMRuntime.class)
public class IDXGIResource1 extends IDXGIResource {

	public IDXGIResource1() {
		// TODO Auto-generated constructor stub
	}

    @Deprecated
	@Virtual(0)
	public final native int CreateSharedHandle(Pointer<SECURITY_ATTRIBUTES> pAttributes, int dwAccess, Pointer<Byte> lpName, Pointer<HANDLE> pHandle);

    @Deprecated
	@Virtual(1)
	public final native int CreateSubresourceSurface(int index, Pointer<Pointer<IDXGISurface2>> ppSurface);

    public HANDLE CreateSharedHandle(SECURITY_ATTRIBUTES attributes, int access, String name) throws DXGIException {
        Pointer<HANDLE> pHandle = allocate(HANDLE.class);

        try {
            int result = CreateSharedHandle(pointerTo(attributes), access, pointerToCString(name), pHandle);
            if (result != 0) {
                throw new DXGIException(result);
            }

            return pHandle.get();
        } finally {
            pHandle.release();
            pHandle = null;
        }
    }

    public final IDXGISurface2 CreateSubresourceSurface(int index) throws DXGIException {
        Pointer<Pointer<IDXGISurface2>> pp = allocatePointer(IDXGISurface2.class);

        try {
            int result = CreateSubresourceSurface(index, pp);
            if (result != 0) {
                throw new DXGIException(result);
            }

            return pp.get().getNativeObject(IDXGISurface2.class);
        } finally {
            pp.release();
        }
    }
}