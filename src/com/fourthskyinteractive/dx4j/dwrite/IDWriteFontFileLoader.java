package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("727cad4e-d6af-4c9e-8a08-d695b11caa49")
@Library("dwrite")
@Runtime(COMRuntime.class)
public class IDWriteFontFileLoader extends IUnknown {

	public IDWriteFontFileLoader() {}

	@Virtual(0)
	public final native int CreateStreamFromKey(Pointer<?> fontFileReferenceKey, int fontFileReferenceKeySize, Pointer<Pointer<IDWriteFontFileStream>> fontFileStream);
}
