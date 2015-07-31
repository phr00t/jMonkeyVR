package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("C05C768F-FAE8-4EC2-8E07-338321C12452")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpAudio extends ISpStreamFormat {

	/*
		virtual HRESULT STDMETHODCALLTYPE SetState( 
            /* [in]  SPAUDIOSTATE NewState,
            /* [in]  ULONGLONG ullReserved) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetFormat( 
            /* [in]  REFGUID rguidFmtId,
            /* [in]  const WAVEFORMATEX *pWaveFormatEx) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetStatus( 
            /* [out]  SPAUDIOSTATUS *pStatus) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetBufferInfo( 
            /* [in]  const SPAUDIOBUFFERINFO *pBuffInfo) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetBufferInfo( 
            /* [out]  SPAUDIOBUFFERINFO *pBuffInfo) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetDefaultFormat( 
            /* [out]  GUID *pFormatId,
            /* [out]  WAVEFORMATEX **ppCoMemWaveFormatEx) = 0;
        
        virtual HANDLE STDMETHODCALLTYPE EventHandle( void) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetVolumeLevel( 
            /* [out]  ULONG *pLevel) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetVolumeLevel( 
            /* [in]  ULONG Level) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetBufferNotifySize( 
            /* [out]  ULONG *pcbSize) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetBufferNotifySize( 
            /* [in]  ULONG cbSize) = 0;
	*/
}
