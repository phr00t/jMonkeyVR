package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("6d4865fe-0ab8-4d91-8f62-5dd6be34a3e0")
@Library("dwrite")
@Runtime(COMRuntime.class)
public class IDWriteFontFileStream extends IUnknown {

	public IDWriteFontFileStream() {
	}

	/**
	 * Reads a fragment from a file.<br>
	 * IMPORTANT: ReadFileFragment() implementations must check whether the requested file fragment
     * is within the file bounds. Otherwise, an error should be returned from ReadFileFragment.
	 * @param fragmentStart Receives the pointer to the start of the font file fragment
	 * @param fileOffset Offset of the fragment from the beginning of the font file
	 * @param fragmentSize Size of the fragment in bytes
	 * @param fragmentContext The client defined context to be passed to the ReleaseFileFragment
	 * @return
	 */
	@Virtual(0)
	public final native int ReadFileFragment(Pointer<Pointer<?>> fragmentStart, long fileOffset, long fragmentSize, Pointer<Pointer<?>> fragmentContext);
	
	@Virtual(1)
	public final native int ReleaseFileFragment(Pointer<?> fragmentContext);
	
	@Virtual(2)
	public final native int GetFileSize(Pointer<Long> fileSize);
	
	@Virtual(3)
	public final native int GetLastWriteTime(Pointer<Long> lastWriteTime); 
}
