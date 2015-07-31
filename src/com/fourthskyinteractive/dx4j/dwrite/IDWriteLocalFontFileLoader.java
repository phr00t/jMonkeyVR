package com.fourthskyinteractive.dx4j.dwrite;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.windows.FILETIME;

@IID("b2d9f3ec-c9fe-4a11-a2ec-d86208f7c0a2")
@Library("dwrite")
@Runtime(COMRuntime.class)
public class IDWriteLocalFontFileLoader extends IDWriteFontFileLoader {

	public IDWriteLocalFontFileLoader() {
	}

	@Virtual(0)
	public final native int GetFilePathLengthFromKey(Pointer<?> fontFileReferenceKey, int fontFileReferenceKeySize, Pointer<Integer> filePathLength);
	
	@Virtual(1)
	public final native int GetFilePathFromKey(Pointer<?> fontFileReferenceKey, int fontFileReferenceKeySize, Pointer<Character> filePath, int filePathSize);
	
	@Virtual(2)
	public final native int GetLastWriteTimeFromKey(Pointer<?> fontFileReferenceKey, int fontFileReferenceKeySize, Pointer<FILETIME> lastWriteTime);
}
