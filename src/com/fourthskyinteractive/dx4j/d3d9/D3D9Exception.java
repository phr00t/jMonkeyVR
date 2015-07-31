package com.fourthskyinteractive.dx4j.d3d9;

import com.fourthskyinteractive.dx4j.windows.WindowsException;

public class D3D9Exception extends WindowsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3315835539551010666L;

    public D3D9Exception(long code) {
        super(code);
    }

	public D3D9Exception(String message, long code) {
		super(message, code);
	}

}
