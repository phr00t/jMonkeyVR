package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.CLSID;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@CLSID("EF411752-3736-4CB4-9C8C-8EF4CCB58EFE")
@IID("14056589-E16C-11D2-BB90-00C04F8EE6C0")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpObjectToken extends ISpDataKey {

	@Virtual(0)
	public native int SetId(Pointer<Character> pszCategoryId, Pointer<Character> pszTokenId, int fCreateIfNotExist);
        
	@Virtual(1)
	public native int GetId(Pointer<Pointer<Character>> ppszCoMemTokenId);
	
	@Virtual(3)
	public native int CreateInstance(Pointer<IUnknown> pUnkOuter, int dwClsContext, /*REFIID*/ Pointer<Byte> riid, Pointer<Pointer<?>> ppvObject);
	
	/*
		virtual HRESULT STDMETHODCALLTYPE SetId( 
            /* [annotation]  
            __in_opt  LPCWSTR pszCategoryId,
            LPCWSTR pszTokenId,
            BOOL fCreateIfNotExist) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetId( 
            /* [annotation][out]  
            __deref_out  LPWSTR *ppszCoMemTokenId) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetCategory( 
            ISpObjectTokenCategory **ppTokenCategory) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE CreateInstance( 
            /* [in]  IUnknown *pUnkOuter,
            /* [in]  DWORD dwClsContext,
            /* [in]  REFIID riid,
            /* [iid_is][out]  void **ppvObject) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetStorageFileName( 
            /* [in]  REFCLSID clsidCaller,
            /* [annotation][in]  
            __in  LPCWSTR pszValueName,
            /* [string][in][annotation]  
            __in_opt  LPCWSTR pszFileNameSpecifier,
            /* [in]  ULONG nFolder,
            /* [out][annotation]  
            __deref_out  LPWSTR *ppszFilePath) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE RemoveStorageFileName( 
            /* [in]  REFCLSID clsidCaller,
            /* [in][annotation]  
            __in  LPCWSTR pszKeyName,
            /* [in]  BOOL fDeleteFile) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Remove( 
            /* [annotation]  
            __in_opt  const CLSID *pclsidCaller) = 0;
        
        virtual /* [local]  HRESULT STDMETHODCALLTYPE IsUISupported( 
            /* [in]  LPCWSTR pszTypeOfUI,
            /* [in]  void *pvExtraData,
            /* [in]  ULONG cbExtraData,
            /* [in]  IUnknown *punkObject,
            /* [out]  BOOL *pfSupported) = 0;
        
        virtual /* [local]  HRESULT STDMETHODCALLTYPE DisplayUI( 
            /* [in]  HWND hwndParent,
            /* [in]  LPCWSTR pszTitle,
            /* [in]  LPCWSTR pszTypeOfUI,
            /* [in]  void *pvExtraData,
            /* [in]  ULONG cbExtraData,
            /* [in]  IUnknown *punkObject) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE MatchesAttributes( 
            /* [in]  LPCWSTR pszAttributes,
            /* [out]  BOOL *pfMatches) = 0;
	*/
}
