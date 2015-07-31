package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.CLSID;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@CLSID("A910187F-0C7A-45AC-92CC-59EDAFB77B53")
@IID("2D3D3845-39AF-4850-BBF9-40B49780011D")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpObjectTokenCategory extends ISpDataKey {

	@Virtual(0)
	public native int SetId(Pointer<Character> pszCategoryId, int fCreateIfNotExist);
	
	@Virtual(1)
	public native int GetId(Pointer<Pointer<Character>> pszCoMemCategoryId);
	
	@Virtual(4)
	public native int SetDefaultTokenId(Pointer<Character> pszTokenId);
        
    @Virtual(5)
    public native int GetDefaultTokenId(Pointer<Pointer</*Character*/?>> ppszCoMemTokenId);
	
	/*
		virtual HRESULT STDMETHODCALLTYPE SetId( 
            /* [in]  LPCWSTR pszCategoryId,
            BOOL fCreateIfNotExist) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetId( 
            /* [out]  LPWSTR *ppszCoMemCategoryId) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetDataKey( 
            SPDATAKEYLOCATION spdkl,
            ISpDataKey **ppDataKey) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE EnumTokens( 
            /* [string][in][annotation]  
            __in_opt  LPCWSTR pzsReqAttribs,
            /* [string][in][annotation]  
            __in_opt  LPCWSTR pszOptAttribs,
            /* [out]  IEnumSpObjectTokens **ppEnum) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetDefaultTokenId( 
            /* [in]  LPCWSTR pszTokenId) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetDefaultTokenId( 
            /* [out]  LPWSTR *ppszCoMemTokenId) = 0;
	*/
}
