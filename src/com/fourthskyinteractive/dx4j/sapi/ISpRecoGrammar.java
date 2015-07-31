package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.sapi.SAPI.SPRULESTATE;

@IID("2177DB29-7F45-47D0-8554-067E91C80502")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpRecoGrammar extends ISpGrammarBuilder {

	@Virtual(7)
	public native int SetRuleState(Pointer<Character> pszName, Pointer<?> pReserved, SPRULESTATE NewState);
        
	@Virtual(8)
	public native int SetRuleIdState(int ulRuleId, SPRULESTATE NewState);
	
	/*
	virtual HRESULT STDMETHODCALLTYPE GetGrammarId( 
            /* [out]  ULONGLONG *pullGrammarId) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetRecoContext( 
            /* [out]  ISpRecoContext **ppRecoCtxt) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE LoadCmdFromFile( 
            /* [string][in]  LPCWSTR pszFileName,
            /* [in]  SPLOADOPTIONS Options) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE LoadCmdFromObject( 
            /* [in]  REFCLSID rcid,
            /* [string][in]  LPCWSTR pszGrammarName,
            /* [in]  SPLOADOPTIONS Options) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE LoadCmdFromResource( 
            /* [in]  HMODULE hModule,
            /* [string][in]  LPCWSTR pszResourceName,
            /* [string][in]  LPCWSTR pszResourceType,
            /* [in]  WORD wLanguage,
            /* [in]  SPLOADOPTIONS Options) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE LoadCmdFromMemory( 
            /* [in]  const SPBINARYGRAMMAR *pGrammar,
            /* [in]  SPLOADOPTIONS Options) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE LoadCmdFromProprietaryGrammar( 
            /* [in]  REFGUID rguidParam,
            /* [string][in]  LPCWSTR pszStringParam,
            /* [in]  const void *pvDataPrarm,
            /* [in]  ULONG cbDataSize,
            /* [in]  SPLOADOPTIONS Options) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetRuleState( 
            /* [string][in][annotation]  
            __in_opt  LPCWSTR pszName,
            void *pReserved,
            /* [in]  SPRULESTATE NewState) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetRuleIdState( 
            /* [in]  ULONG ulRuleId,
            /* [in]  SPRULESTATE NewState) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE LoadDictation( 
            /* [string][in][annotation]  
            __in_opt  LPCWSTR pszTopicName,
            /* [in]  SPLOADOPTIONS Options) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE UnloadDictation( void) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetDictationState( 
            /* [in]  SPRULESTATE NewState) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetWordSequenceData( 
            /* [in][annotation]  
            __in_ecount_opt(cchText)  const WCHAR *pText,
            /* [in]  ULONG cchText,
            /* [in]  const SPTEXTSELECTIONINFO *pInfo) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetTextSelection( 
            /* [in]  const SPTEXTSELECTIONINFO *pInfo) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE IsPronounceable( 
            /* [string][in]  LPCWSTR pszWord,
            /* [out]  SPWORDPRONOUNCEABLE *pWordPronounceable) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetGrammarState( 
            /* [in]  SPGRAMMARSTATE eGrammarState) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SaveCmd( 
            /* [in]  IStream *pStream,
            /* [optional][out][annotation]  
            __deref_opt_out  LPWSTR *ppszCoMemErrorText) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetGrammarState( 
            /* [out]  SPGRAMMARSTATE *peGrammarState) = 0;

	*/
}
