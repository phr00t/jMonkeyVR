package com.fourthskyinteractive.dx4j.dxgi;

import com.fourthskyinteractive.dx4j.windows.WindowsException;

public class DXGIException extends WindowsException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1137607257985787559L;
	
	public DXGIException(long code) {
		super(code);
	}
	
	public DXGIException(String message, long code) {
		super(message, code);
	}
	
}
