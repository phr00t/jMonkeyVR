package com.fourthskyinteractive.dx4j.windows.ole;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("0c733a30-2a1c-11ce-ade5-00aa0044773d")
@Library("ole32")
@Runtime(COMRuntime.class)
public class ISequentialStream extends IUnknown {

    @Virtual(0)
    public native int Read(Pointer<?> pv, int cb, Pointer<Integer> pcbRead);
        
	@Virtual(1)
	public native int Write(Pointer<?> pv, int cb, Pointer<Integer> pcbWritten);
}
