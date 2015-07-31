package com.fourthskyinteractive.dx4j.d3dcompiler;

import com.fourthskyinteractive.dx4j.windows.WindowsException;

public class D3DCompilerException extends WindowsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8365845589527592021L;
	
	
	public D3DCompilerException(long code) {
		super(code);
	}
	
	public D3DCompilerException(String message, long code) {
		super(message, code);
	}
	
}
