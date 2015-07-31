package com.fourthskyinteractive.dx4j.d3dx11;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@IID("C93FECFA-6967-478a-ABBC-402D90621FCB")
@Library("d3dx11_43")
@Runtime(COMRuntime.class)
public class ID3DX11ThreadPump extends IUnknown {

	public ID3DX11ThreadPump() {
		// TODO Auto-generated constructor stub
	}
	
	@Virtual(0)
	public native final int AddWorkItem(Pointer<ID3DX11DataLoader> pDataLoader,Pointer<ID3DX11DataProcessor> pDataProcessor, Pointer<Integer> pResult, Pointer<Pointer<?>> ppDeviceObject);
	
	@Virtual(1)
	public native final int GetWorkItemCount();
	
	@Virtual(2)
	public native final int WaitForAllItems();
	
	@Virtual(3)
	public native final int ProcessDeviceWorkItems(int iWorkItemCount);
	
	@Virtual(4)
	public native final int PurgeAllItems();
	
	@Virtual(5)
	public native final int GetQueueStatus(int pIoQueue, Pointer<Integer> pProcessQueue, Pointer<Integer> pDeviceQueue);

}
