package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("BE7A9CCE-5F9E-11D2-960F-00C04F8EE628")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpEventSource extends ISpNotifySource {

	@Virtual(0)
	public native int SetInterest(long ullEventInterest, long ullQueuedInterest);
        
	@Virtual(1)
	public native int GetEvents(int ulCount, Pointer<SPEVENT> pEventArray, Pointer<Long> pulFetched);
        
	@Virtual(2)
	public native int GetInfo(Pointer<SPEVENTSOURCEINFO> pInfo);

}
