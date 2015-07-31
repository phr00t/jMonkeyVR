package com.fourthskyinteractive.dx4j.d3d11.layer;

import org.bridj.CLong;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_MESSAGE_CATEGORY;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_MESSAGE_ID;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_MESSAGE_SEVERITY;

@IID("6543dbb6-1b48-42f5-ab82-e97ec74326f6")
@Library("d3d11")
@Runtime(COMRuntime.class)
public class ID3D11InfoQueue extends IUnknown {
	public ID3D11InfoQueue() {
		super();
	}

	@Virtual(0)
	public native final int SetMessageCountLimit(long MessageCountLimit);
    
	@Virtual(1)
    public native final void ClearStoredMessages();
    
	@Virtual(2)
    public native final int GetMessage(long MessageIndex, Pointer<D3D11_MESSAGE> pMessage, Pointer<CLong> pMessageByteLength);
    
	@Virtual(3)
    public native final long GetNumMessagesAllowedByStorageFilter();
    
	@Virtual(4)
    public native final long GetNumMessagesDeniedByStorageFilter();
    
	@Virtual(5)
    public native final long GetNumStoredMessages();
    
	@Virtual(6)
    public native final long GetNumStoredMessagesAllowedByRetrievalFilter();
    
	@Virtual(7)
    public native final long GetNumMessagesDiscardedByMessageCountLimit();
    
	@Virtual(8)
    public native final long GetMessageCountLimit();
    
	@Virtual(9)
    public native final int AddStorageFilterEntries(Pointer<D3D11_INFO_QUEUE_FILTER> pFilter);
    
	@Virtual(10)
    public native final int GetStorageFilter(Pointer<D3D11_INFO_QUEUE_FILTER> pFilter, Pointer<CLong> pFilterByteLength);
    
	@Virtual(11)
    public native final void ClearStorageFilter();
    
	@Virtual(12)
    public native final int PushEmptyStorageFilter();
    
	@Virtual(13)
    public native final int PushCopyOfStorageFilter();
    
	@Virtual(14)
    public native final int PushStorageFilter(Pointer<D3D11_INFO_QUEUE_FILTER> pFilter);
    
	@Virtual(15)
    public native final void PopStorageFilter();
    
	@Virtual(16)
    public native final int GetStorageFilterStackSize();
    
	@Virtual(17)
    public native final int AddRetrievalFilterEntries(Pointer<D3D11_INFO_QUEUE_FILTER> pFilter);
    
	@Virtual(18)
    public native final int GetRetrievalFilter(Pointer<D3D11_INFO_QUEUE_FILTER> pFilter, Pointer<CLong> pFilterByteLength);
    
	@Virtual(19)
    public native final void ClearRetrievalFilter();
    
	@Virtual(20)
    public native final int PushEmptyRetrievalFilter();
    
	@Virtual(21)
    public native final int PushCopyOfRetrievalFilter();
    
	@Virtual(22)
    public native final int PushRetrievalFilter(Pointer<D3D11_INFO_QUEUE_FILTER> pFilter);
    
	@Virtual(23)
    public native final void PopRetrievalFilter();
    
	@Virtual(24)
    public native final void GetRetrievalFilterStackSize();
    
	@Virtual(25)
    public native final int AddMessage(D3D11_MESSAGE_CATEGORY Category, D3D11_MESSAGE_SEVERITY Severity, D3D11_MESSAGE_ID ID, Pointer<Byte> pDescription);
    
	@Virtual(26)
    public native final int AddApplicationMessage(D3D11_MESSAGE_SEVERITY Severity, Pointer<Byte> pDescription);
    
	@Virtual(27)
    public native final int SetBreakOnCategory(D3D11_MESSAGE_CATEGORY Category, int bEnable);
    
	@Virtual(28)
    public native final int SetBreakOnSeverity(D3D11_MESSAGE_SEVERITY Severity, int bEnable);
    
	@Virtual(29)
    public native final int SetBreakOnID(D3D11_MESSAGE_ID ID, int bEnable);
    
	@Virtual(30)
    public native final int GetBreakOnCategory(D3D11_MESSAGE_CATEGORY Category);
    
	@Virtual(31)
    public native final int GetBreakOnSeverity(D3D11_MESSAGE_SEVERITY Severity);
    
	@Virtual(32)
    public native final int GetBreakOnID(D3D11_MESSAGE_ID ID);
    
	@Virtual(33)
    public native final void SetMuteDebugOutput(int bMute);
    
	@Virtual(34)
    public native final int GetMuteDebugOutput();
}
