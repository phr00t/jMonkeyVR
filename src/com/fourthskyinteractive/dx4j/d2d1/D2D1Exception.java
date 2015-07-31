package com.fourthskyinteractive.dx4j.d2d1;

import com.fourthskyinteractive.dx4j.windows.WindowsException;

public class D2D1Exception extends WindowsException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9149841022121288219L;

    public D2D1Exception(int code) {
        super(code);
    }

	public D2D1Exception(String message, int code) {
		super(message, code);
	}
}
