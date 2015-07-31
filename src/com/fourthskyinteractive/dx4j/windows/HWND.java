package com.fourthskyinteractive.dx4j.windows;

import org.bridj.Pointer;

public class HWND extends HANDLE {

	public HWND(long address) {
		super(address);
	}

	public HWND(Pointer<?> ptr) {
		super(ptr);
	}
}
