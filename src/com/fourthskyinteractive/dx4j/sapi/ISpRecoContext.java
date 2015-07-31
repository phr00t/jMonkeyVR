package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

import com.fourthskyinteractive.dx4j.util.WAVEFORMATEX;
import com.fourthskyinteractive.dx4j.sapi.SAPI.SPAUDIOOPTIONS;
import com.fourthskyinteractive.dx4j.sapi.SAPI.SPBOOKMARKOPTIONS;
import com.fourthskyinteractive.dx4j.sapi.SAPI.SPCONTEXTSTATE;

@IID("F740A62F-7C15-489E-8234-940A33D9272D")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpRecoContext extends ISpEventSource {

	@Virtual(0)
	public native int GetRecognizer(Pointer<Pointer<ISpRecognizer>> ppRecognizer);
	
	@Virtual(1)
	public native int CreateGrammar(double ullGrammarId, Pointer<Pointer<ISpRecoGrammar>> ppGrammar);
	
	@Virtual(2)
	public native int GetStatus(Pointer<SPRECOCONTEXTSTATUS> pStatus);
	
	@Virtual(3)
	public native int GetMaxAlternates(Pointer<Integer> pcAlternates);
	
	@Virtual(4)
	public native int SetMaxAlternates(int pcAlternates);
	
	@Virtual(5)
	public native int SetAudioOptions(SPAUDIOOPTIONS Options, Pointer<Byte> pAudioFormatId, Pointer<WAVEFORMATEX> pWaveFormatEx);
	
	@Virtual(6)
	public native int GetAudioOptions(Pointer<SPAUDIOOPTIONS> pOptions, Pointer<Byte> pAudioFormatId, Pointer<Pointer<WAVEFORMATEX>> pWaveFormatEx);
	
	@Virtual(7)
	public native int DeserializeResult(Pointer<SPSERIALIZEDRESULT> pSerializedResult, Pointer<Pointer<ISpRecoResult>> ppResult);
	
	@Virtual(8)
	public native int Bookmark(SPBOOKMARKOPTIONS Options, double ullStreamPosition, int lparamEvent);

	@Virtual(9)
	public native int SetAdaptationData(Pointer<Character> pAdaptationData, int cch);

	@Virtual(10)
	public native int Pause(int dwReserved);
	
	@Virtual(11)
	public native int Resume(int dwReserved);
	
	/*
	    virtual HRESULT STDMETHODCALLTYPE SetVoice( 
	        /* [in]  ISpVoice *pVoice,
	        /* [in]  BOOL fAllowFormatChanges) = 0;
	    
	    virtual HRESULT STDMETHODCALLTYPE GetVoice( 
	        /* [out]  ISpVoice **ppVoice) = 0;
    
	 */
	
	@Virtual(14)
	public native int SetVoicePurgeEvent(double ullEventInterest);
    
	@Virtual(15)
	public native int GetVoicePurgeEvent(Pointer<Double> ullEventInterest);
	
	@Virtual(16)
	public native int SetContextState(SPCONTEXTSTATE eContextState);
    
	@Virtual(17)
	public native int SetContextState(Pointer<Integer> eContextState);

}
