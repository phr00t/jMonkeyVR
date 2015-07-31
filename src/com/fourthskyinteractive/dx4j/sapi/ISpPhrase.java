package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("1A5C0354-B621-4b5a-8791-D306ED379E53")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpPhrase extends IUnknown {

	@Virtual(0)
	public native int GetPhrase(Pointer<Pointer<SPPHRASE>> ppCoMemPhrase);

	@Virtual(1)
	public native int GetSerializedPhrase(Pointer<Pointer<SPSERIALIZEDPHRASE>> ppCoMemPhrase);
	
	@Virtual(2)
	public native int GetText(int ulStart, int ulCount, int fUseTextReplacements, Pointer<Pointer<Character>> ppszCoMemText, Pointer<Byte> pbDisplayAttributes);
	
	@Virtual(3)
	public native int Discard(int dwValueTypes);

}
