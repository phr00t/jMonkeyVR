package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.CLong;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.sapi.SAPI.SPGRAMMARWORDTYPE;
import com.fourthskyinteractive.dx4j.sapi.SAPI.SPSTATEHANDLE;

@IID("8137828F-591A-4A42-BE58-49EA7EBAAC68")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpGrammarBuilder extends IUnknown {

	@Virtual(0)
	public native int ResetGrammar(short NewLanguage);

	@Virtual(1)
	public native int GetRule(/*LPCWSTR*/ Pointer<Character> pszRuleName, @CLong long dwRuleId, @CLong long dwAttributes, int fCreateIfNotExist, Pointer<SPSTATEHANDLE> phInitialState);
	
	@Virtual(2)
	public native int ClearRule(SPSTATEHANDLE hState); 
	
	@Virtual(3)
	public native int CreateNewState(SPSTATEHANDLE hState, Pointer<SPSTATEHANDLE> phState);
	
	@Virtual(4)
	public native int AddWordTransition(SPSTATEHANDLE hFromState, SPSTATEHANDLE hToState, /*LPCWSTR*/ Pointer<Character> psz, /*LPCWSTR*/ Pointer<Character> pszSeparators, SPGRAMMARWORDTYPE eWordType, float Weight, Pointer<SPPROPERTYINFO> pPropInfo);	
	
	@Virtual(5)
	public native int AddRuleTransition(SPSTATEHANDLE hFromState, SPSTATEHANDLE hToState, SPSTATEHANDLE hRule, float Weight, Pointer<SPPROPERTYINFO> pPropInfo);
	
    @Virtual(6)
    public native int AddResource(SPSTATEHANDLE hRuleState, Pointer<Character> pszResourceName, Pointer<Character> pszResourceValue);
        
    @Virtual(7)
    public native int Commit(int dwReserved);

}
