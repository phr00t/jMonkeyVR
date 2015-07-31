package com.fourthskyinteractive.dx4j.dxgi.adapter;

import com.fourthskyinteractive.dx4j.dxgi.IDXGIObject;
import com.fourthskyinteractive.dx4j.dxgi.device.DXGI_MAPPED_RECT;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGIResource;
import com.fourthskyinteractive.dx4j.util.Describable;
import org.bridj.Pointer;
import org.bridj.ann.*;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.RECT;

import static org.bridj.Pointer.allocate;

/**
 * Created with IntelliJ IDEA.
 * User: evandro.paulino
 * Date: 17/06/13
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
@IID("191cfac3-a341-470d-b26e-a864f428319c")
@Library("dxgi")
@Runtime(COMRuntime.class)
public class IDXGIOutputDuplication extends IDXGIObject implements Describable {
    public IDXGIOutputDuplication() {
        super();
    }

    @Virtual(0)
    public native void GetDesc(Pointer<DXGI_OUTDUPL_DESC> pDesc);

    @Virtual(1)
    public native int AcquireNextFrame(int TimeoutInMilliseconds, Pointer<DXGI_OUTDUPL_FRAME_INFO> pFrameInfo, Pointer<Pointer<IDXGIResource>> ppDesktopResource);

    @Virtual(2)
    public native int GetFrameDirtyRects(int DirtyRectsBufferSize, Pointer<RECT> pDirtyRectsBuffer, Pointer<Integer> pDirtyRectsBufferSizeRequired);

    @Virtual(3)
    public native int GetFrameMoveRects(int MoveRectsBufferSize, Pointer<DXGI_OUTDUPL_MOVE_RECT> pMoveRectBuffer, Pointer<Integer> pMoveRectsBufferSizeRequired);

    @Virtual(4)
    public native int GetFramePointerShape(int PointerShapeBufferSize, Pointer<?> pPointerShapeBuffer, Pointer<Integer> pPointerShapeBufferSizeRequired, Pointer<DXGI_OUTDUPL_POINTER_SHAPE_INFO> pPointerShapeInfo);

    @Virtual(5)
    public native int MapDesktopSurface(Pointer<DXGI_MAPPED_RECT> pLockedRect);

    @Virtual(6)
    public native int UnMapDesktopSurface();

    @Virtual(7)
    public native int ReleaseFrame();

    @SuppressWarnings("unchecked")
	public DXGI_OUTDUPL_DESC GetDesc() {
        Pointer<DXGI_OUTDUPL_DESC> pDesc = null;

        try {
            pDesc = allocate(DXGI_OUTDUPL_DESC.class);
            this.GetDesc(pDesc);
            return pDesc.get();
        } finally {
            if (pDesc != null)
                pDesc.release();
        }
    }

}
