package com.fourthskyinteractive.dx4j.dxgi.adapter;

import com.fourthskyinteractive.dx4j.dxgi.DXGI;
import com.fourthskyinteractive.dx4j.dxgi.DXGI_MODE_DESC1;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGIResource;
import org.bridj.Pointer;
import org.bridj.ann.*;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import static com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_FORMAT;

/**
 * Created with IntelliJ IDEA.
 * User: evandro.paulino
 * Date: 17/06/13
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
@IID("00cddea8-939b-4b83-a340-a685226666cc")
@Library("dxgi")
@Runtime(COMRuntime.class)
public class IDXGIOutput1 extends IDXGIOutput {
    public IDXGIOutput1() {
        super();
    }

    @Virtual(0)
    public native int GetDisplayModeList1(DXGI_FORMAT EnumFormat, int Flags, Pointer<Integer> pNumModes, Pointer<DXGI_MODE_DESC1> pDesc);

    @Virtual(1)
    public native int FindClosestMatchingMode1(Pointer<DXGI_MODE_DESC1> pModeToMatch, Pointer<DXGI_MODE_DESC1> pClosestMatch, Pointer<IUnknown> pConcernedDevice);

    @Virtual(2)
    public native int GetDisplaySurfaceData1(Pointer<IDXGIResource> pDestination);

    @Virtual(3)
    public native int DuplicateOutput(Pointer<IUnknown> pDevice, Pointer<Pointer<IDXGIOutputDuplication>> ppOutputDuplication);
}
