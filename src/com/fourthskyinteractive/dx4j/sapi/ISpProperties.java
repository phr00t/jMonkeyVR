package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("5B4FB971-B115-4DE1-AD97-E482E3BF6EE4")
@Library("sapi") 
@Runtime(COMRuntime.class)
public class ISpProperties extends IUnknown {

	@Virtual(0)
	public native int SetPropertyNum(/*LPCWSTR*/ Pointer<Character> pName, long lValue);
	
	@Virtual(1)
	public native int GetPropertyNum(/*LPCWSTR*/ Pointer<Character> pName, Pointer<Long> plValue);
     
	@Virtual(2)
	public native int SetPropertyString(/*LPCWSTR*/ Pointer<Character> pName, long lValue);
	
	@Virtual(3)
	public native int GetPropertyString(/*LPCWSTR*/ Pointer<Character> pName, Pointer<Pointer<Character>> plValue);
	
}
