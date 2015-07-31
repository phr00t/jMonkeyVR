package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.windows.HANDLE;

@IID("5EFF4AEF-8487-11D2-961C-00C04F8EE628")
@Library("sapi")
@Runtime(COMRuntime.class)
public class ISpNotifySource extends IUnknown {

	@Virtual(4)
	public native int SetNotifyWin32Event();
    
    @Virtual(5)
    public native int WaitForNotifyEvent(int dwMilliseconds);
    
    @Virtual(6)
    public native HANDLE GetNotifyEventHandle();
    
	/*
	virtual HRESULT STDMETHODCALLTYPE SetNotifySink( 
            /* [in]  __RPC__in_opt ISpNotifySink *pNotifySink) = 0;
        
        virtual /* [local]  HRESULT STDMETHODCALLTYPE SetNotifyWindowMessage( 
            /* [in]  HWND hWnd,
            /* [in] UINT Msg,
            /* [in]  WPARAM wParam,
            /* [in]  LPARAM lParam) = 0;
        
        virtual /* [local]  HRESULT STDMETHODCALLTYPE SetNotifyCallbackFunction( 
            /* [in]  SPNOTIFYCALLBACK *pfnCallback,
            /* [in]  WPARAM wParam,
            /* [in]  LPARAM lParam) = 0;
        
        virtual /* [local] HRESULT STDMETHODCALLTYPE SetNotifyCallbackInterface( 
            /* [in]  ISpNotifyCallback *pSpCallback,
            /* [in]  WPARAM wParam,
            /* [in] LPARAM lParam) = 0;
        
        */
}
