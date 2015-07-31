package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.CLSID;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.sapi.SAPI.SPRECOSTATE;

/*@CLSID("41B89B6B-9399-11D2-9623-00C04F8EE628") /* InprocRecognizer*/
@CLSID("3BEE4890-4FE9-4A37-8C1E-5E7E12791C1F") /* SharedRecognizer*/
@IID("C2B5F241-DAA0-4507-9E16-5A1EAA2B7A5C")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpRecognizer extends ISpProperties {

	@Virtual(2)
	public native int SetInput(Pointer<IUnknown> pUnkInput, int fAllowFormatChanges);
	
	@Virtual(5)
	public native int CreateRecoContext(Pointer<Pointer<ISpRecoContext>> ppNewCtxt);
	
	@Virtual(9)
	public native int GetRecoState(Pointer<Integer> pState);
        
	@Virtual(10)
	public native int SetRecoState(SPRECOSTATE NewState);
	
	/*
	virtual HRESULT STDMETHODCALLTYPE SetRecognizer( 
            /* [in]  ISpObjectToken *pRecognizer) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetRecognizer( 
            /* [out]  ISpObjectToken **ppRecognizer) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetInput( 
            /* [in][annotation]  
            __in_opt  IUnknown *pUnkInput,
            /* [in]  BOOL fAllowFormatChanges) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetInputObjectToken( 
            /* [out]  ISpObjectToken **ppToken) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetInputStream( 
            /* [out]  ISpStreamFormat **ppStream) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE CreateRecoContext( 
            /* [out]  ISpRecoContext **ppNewCtxt) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetRecoProfile( 
            /* [out]  ISpObjectToken **ppToken) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetRecoProfile( 
            /* [in]  ISpObjectToken *pToken) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE IsSharedInstance( void) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetRecoState( 
            /* [out]  SPRECOSTATE *pState) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetRecoState( 
            /* [in]  SPRECOSTATE NewState) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetStatus( 
            /* [out]  SPRECOGNIZERSTATUS *pStatus) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetFormat( 
            /* [in]  SPSTREAMFORMATTYPE WaveFormatType,
            /* [out]  GUID *pFormatId,
            /* [out]  WAVEFORMATEX **ppCoMemWFEX) = 0;
        
        virtual /* [local]  HRESULT STDMETHODCALLTYPE IsUISupported( 
            /* [in]  LPCWSTR pszTypeOfUI,
            /* [in]  void *pvExtraData,
            /* [in]  ULONG cbExtraData,
            /* [out]  BOOL *pfSupported) = 0;
        
        virtual /* [local]  HRESULT STDMETHODCALLTYPE DisplayUI( 
            /* [in]  HWND hwndParent,
            /* [in][annotation]  
            __in_opt  LPCWSTR pszTitle,
            /* [in]  LPCWSTR pszTypeOfUI,
            /* [in]  void *pvExtraData,
            /* [in]  ULONG cbExtraData) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE EmulateRecognition( 
            /* [in]  ISpPhrase *pPhrase) = 0;
             * 
             */
}
