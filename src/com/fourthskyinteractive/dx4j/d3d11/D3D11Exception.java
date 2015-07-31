package com.fourthskyinteractive.dx4j.d3d11;

import com.fourthskyinteractive.dx4j.windows.WindowsException;

public class D3D11Exception extends WindowsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3315835539551010666L;
	
	public D3D11Exception(long code) {
		super(code);
	}
	
	public D3D11Exception(String message, long code) {
		super(message, code);
	}

	
}
