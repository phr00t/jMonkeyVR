package com.fourthskyinteractive.dx4j.windows.kernel32;

import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;

import com.fourthskyinteractive.dx4j.windows.HANDLE;

/**
 * http://msdn.microsoft.com/en-us/library/windows/desktop/aa383751(v=vs.85).aspx
 * @author evandro.paulino
 *
 */
@Library("kernel32")
@Runtime(CRuntime.class)
public class Kernel32 {
	static {
		BridJ.register();
	}
	
	public static final int WAIT_OBJECT_0 =	0x00000000;
	public static final int WAIT_TIMEOUT = 0x00000102; 
	public static final int WAIT_ABANDONED = 0x00000080; 
	
	/*
	 * Events, semaphores and mutexes are kernel-mode objects
	 */
	public static final native HANDLE CreateEvent(Pointer<SECURITY_ATTRIBUTES> lpAttr, 
												  int mManualReset, 
												  int bInitialState, 
												  Pointer<Byte> lpName);
	public static final native int SetEvent(HANDLE hEvent);
	public static final native int ResetEvent(HANDLE hEvent);
	public static final native int PulseEvent(HANDLE hEvent);
	
	public static final native HANDLE CreateMutex(Pointer<SECURITY_ATTRIBUTES> lpAttr, 
			  									int bInitialOwner, 
			  									Pointer<Byte> lpName);
	public static final native int ReleaseMutex(HANDLE hMutex);
	
	public static final native HANDLE CreateSemaphore(Pointer<SECURITY_ATTRIBUTES> lpAttr, 
												  		int lInitialCount, 
												  		int lMaximumCount, 
												  		Pointer<Byte> lpName);
	public static final native int ReleaseSemaphore(HANDLE hSemaphore, int lReleaseCount, Pointer<Integer> lpPreviousCount);
	
	public static final native int WaitForSingleObject(HANDLE hHandle, int dwMilliseconds);
	public static final native int WaitForMultipleObjects(int nCount, Pointer<HANDLE> lpHandles, int bWaitAll, int dwMilliseconds);
	
	public static final native int CloseHandle(HANDLE handle);
	
	/*
	 * Critical sections are user-mode objects
	 */
	public static final native int InitializeCriticalSection(Pointer<CRITICAL_SECTION> cs);
	public static final native int EnterCriticalSection(Pointer<CRITICAL_SECTION> cs);
	public static final native int TryEnterCriticalSection(Pointer<CRITICAL_SECTION> cs);
	public static final native int LeaveCriticalSection(Pointer<CRITICAL_SECTION> cs);
	public static final native int DeleteCriticalSection(Pointer<CRITICAL_SECTION> cs);
	
	/**
	 * Return number of counts per seconds
	 * @param pCounter number of counts per seconds
	 * @return true if successful
	 */
	public static final native int QueryPerformanceFrequency(Pointer<LARGE_INTEGER> pCounter);
	
	/**
	 * Retrieves the current value of the high-resolution performance counter.
	 * @param pCounter current performance counter, in counts
	 * @return true if successful
	 */
	public static final native int QueryPerformanceCounter(Pointer<LARGE_INTEGER> pCounter);
}
