package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("20B053BE-E235-43cd-9A2A-8D17A48B7842")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpRecoResult extends ISpPhrase {
	
	
	
	/*
		virtual HRESULT STDMETHODCALLTYPE GetResultTimes( 
            /* [out]  SPRECORESULTTIMES *pTimes) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetAlternates( 
            /* [in]  ULONG ulStartElement,
            /* [in]  ULONG cElements,
            /* [in]  ULONG ulRequestCount,
            /* [out][annotation]  
            __out_ecount_part(ulRequestCount,*pcPhrasesReturned)  ISpPhraseAlt **ppPhrases,
            /* [out]  ULONG *pcPhrasesReturned) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetAudio( 
            /* [in]  ULONG ulStartElement,
            /* [in]  ULONG cElements,
            /* [out]  ISpStreamFormat **ppStream) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SpeakAudio( 
            /* [in]  ULONG ulStartElement,
            /* [in]  ULONG cElements,
            /* [in]  DWORD dwFlags,
            /* [out]  ULONG *pulStreamNumber) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Serialize( 
            /* [out]  SPSERIALIZEDRESULT **ppCoMemSerializedResult) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE ScaleAudio( 
            /* [in]  const GUID *pAudioFormatId,
            /* [in]  const WAVEFORMATEX *pWaveFormatEx) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetRecoContext( 
            /* [out]  ISpRecoContext **ppRecoContext) = 0;
	*/
}
