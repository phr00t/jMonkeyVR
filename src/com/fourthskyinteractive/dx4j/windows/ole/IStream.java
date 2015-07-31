package com.fourthskyinteractive.dx4j.windows.ole;

import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

@IID("0000000c-0000-0000-C000-000000000046")
@Library("ole32")
@Runtime(COMRuntime.class)
public class IStream extends ISequentialStream {

	
	/*
		virtual /* [local]  HRESULT STDMETHODCALLTYPE Seek( 
            /* [in]  LARGE_INTEGER dlibMove,
            /* [in]  DWORD dwOrigin,
            /* [annotation]  
            __out_opt  ULARGE_INTEGER *plibNewPosition) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SetSize( 
            /* [in]  ULARGE_INTEGER libNewSize) = 0;
        
        virtual /* [local]  HRESULT STDMETHODCALLTYPE CopyTo( 
            /* [unique][in]  IStream *pstm,
            /* [in]  ULARGE_INTEGER cb,
            /* [annotation]  
            __out_opt  ULARGE_INTEGER *pcbRead,
            /* [annotation]  
            __out_opt  ULARGE_INTEGER *pcbWritten) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Commit( 
            /* [in]  DWORD grfCommitFlags) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Revert( void) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE LockRegion( 
            /* [in]  ULARGE_INTEGER libOffset,
            /* [in]  ULARGE_INTEGER cb,
            /* [in]  DWORD dwLockType) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE UnlockRegion( 
            /* [in]  ULARGE_INTEGER libOffset,
            /* [in]  ULARGE_INTEGER cb,
            /* [in]  DWORD dwLockType) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Stat( 
            /* [out]  __RPC__out STATSTG *pstatstg,
            /* [in]  DWORD grfStatFlag) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Clone( 
            /* [out]  __RPC__deref_out_opt IStream **ppstm) = 0;

	*/
}
