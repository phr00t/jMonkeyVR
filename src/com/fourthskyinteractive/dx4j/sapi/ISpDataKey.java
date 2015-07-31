package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("14056581-E16C-11D2-BB90-00C04F8EE6C0")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpDataKey extends IUnknown {

	
	/*
		virtual HRESULT STDMETHODCALLTYPE SetData( 
            LPCWSTR pszValueName,
            ULONG cbData,
            const BYTE *pData) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetData( 
            LPCWSTR pszValueName,
            ULONG *pcbData,
            BYTE *pData) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetStringValue( 
            /* [in][annotation]  
            __in_opt  LPCWSTR pszValueName,
            LPCWSTR pszValue) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetStringValue( 
            /* [in][annotation]  
            __in_opt  LPCWSTR pszValueName,
            /* [out]  LPWSTR *ppszValue) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetDWORD( 
            LPCWSTR pszValueName,
            DWORD dwValue) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetDWORD( 
            LPCWSTR pszValueName,
            DWORD *pdwValue) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE OpenKey( 
            LPCWSTR pszSubKeyName,
            ISpDataKey **ppSubKey) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE CreateKey( 
            LPCWSTR pszSubKey,
            ISpDataKey **ppSubKey) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE DeleteKey( 
            LPCWSTR pszSubKey) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE DeleteValue( 
            /* [in]  LPCWSTR pszValueName) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE EnumKeys( 
            ULONG Index,
            /* [out]  LPWSTR *ppszSubKeyName) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE EnumValues( 
            ULONG Index,
            /* [out]  LPWSTR *ppszValueName) = 0;
	*/
}
