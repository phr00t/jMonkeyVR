package com.fourthskyinteractive.dx4j.dxgi.device;

import com.fourthskyinteractive.dx4j.dxgi.DXGIException;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_OFFER_RESOURCE_PRIORITY;
import com.fourthskyinteractive.dx4j.windows.HANDLE;

import static org.bridj.Pointer.allocateInt;
import static org.bridj.Pointer.allocatePointers;
import static org.bridj.Pointer.pointerTo;

@IID("05008617-fbfd-4051-a790-144884b4f6a9")
@Library("DXGI")
@Runtime(COMRuntime.class)
public class IDXGIDevice2 extends IDXGIDevice1 {

	public IDXGIDevice2() {
		// TODO Auto-generated constructor stub
	}
	
	@Virtual(0)
	public final native int EnqueueSetEvent(HANDLE hEvent);

    @Deprecated
	@Virtual(1)
	public final native int OfferResources(int NumResources, Pointer<Pointer<IDXGIResource>> ppResources, DXGI_OFFER_RESOURCE_PRIORITY Priority);

    @Deprecated
	@Virtual(2)
	public final native int ReclaimResources(int NumResources, Pointer<Pointer<IDXGIResource>> ppResources, Pointer<Integer> pDiscarded);

    /**
     * Free resource data from video memory to system memory or disk.
     * @param resources
     * @param priority
     * @throws DXGIException
     */
    public void OfferResources(IDXGIResource[] resources, DXGI_OFFER_RESOURCE_PRIORITY priority) throws DXGIException {
        Pointer<Pointer<IDXGIResource>> ppResources = allocatePointers(IDXGIResource.class, resources.length);

        try {
            for (int i = 0; i < resources.length; i++) {
                ppResources.set(i, pointerTo(resources[i]));
            }

            int result = this.OfferResources(resources.length, ppResources, priority);
            if (result != 0) {
                throw new DXGIException(result);
            }
        } finally {
            ppResources.release();
            ppResources = null;
        }
    }

    public void OfferResource(IDXGIResource resource, DXGI_OFFER_RESOURCE_PRIORITY priority) throws DXGIException {
        this.OfferResources(new IDXGIResource[] {resource} , priority);
    }

    /**
     * Reclaim resources from storage. If method return false, the resources shall be recreated
     * @param resources
     * @return true if resources is recovered.
     * @throws DXGIException in case of error
     */
    public boolean ReclaimResources(IDXGIResource[] resources) throws DXGIException {
        Pointer<Pointer<IDXGIResource>> ppResources = allocatePointers(IDXGIResource.class, resources.length);
        Pointer<Integer> pDiscarded = allocateInt();

        try {
            for (int i = 0; i < resources.length; i++) {
                ppResources.set(i, pointerTo(resources[i]));
            }

            int result = this.ReclaimResources(resources.length, ppResources, pDiscarded);
            if (result != 0) {
                throw new DXGIException(result);
            }

            if (pDiscarded.get() == 0) {
                return true;
            }

        } finally {
            ppResources.release();
            ppResources = null;
        }

        return false;
    }

    public boolean ReclaimResource(IDXGIResource resource) throws DXGIException {
        return this.ReclaimResources(new IDXGIResource[] {resource});
    }
}
