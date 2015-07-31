package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.util.WAVEFORMATEX;
import com.fourthskyinteractive.dx4j.windows.ole.IStream;

@IID("BED530BE-2606-4F4D-A1C0-54C5CDA5566F")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpStreamFormat extends IStream {

	@Virtual(0)
	public native int GetFormat(Pointer<Byte> pguidFormatId, Pointer<Pointer<WAVEFORMATEX>> ppCoMemWaveFormatEx);
}
