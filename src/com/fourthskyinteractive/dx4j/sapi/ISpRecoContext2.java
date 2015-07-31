package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.CLong;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.sapi.SAPI.SPADAPTATIONRELEVANCE;

@IID("BEAD311C-52FF-437f-9464-6B21054CA73D")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpRecoContext2 extends IUnknown {

	@Virtual(0)
	public native int SetGrammarOptions(int eGrammarOptions);
	
	@Virtual(1)
	public native int GetGrammarOptions(Pointer<Integer> eGrammarOptions);
	
	@Virtual(2)
	public native int SetAdaptationData2(/*LPCWSTR*/ Pointer<Character> pAdaptationData, @CLong long cch, /*LPCWSTR*/ Pointer<Character> pTopicName, int eAdaptationSettings, SPADAPTATIONRELEVANCE eRelevance);
	
}
