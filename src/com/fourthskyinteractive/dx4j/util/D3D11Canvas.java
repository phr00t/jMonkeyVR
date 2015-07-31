package com.fourthskyinteractive.dx4j.util;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;

import org.bridj.Pointer;
import org.bridj.jawt.JAWT;
import org.bridj.jawt.JAWTUtils;
import org.bridj.jawt.JawtLibrary.JNIEnv;

/**
 * 
 * @author evandro.paulino
 *
 */
public abstract class D3D11Canvas extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5930855221829981534L;
	
	private JNIEnv env;
	private JAWT jawt;
	private Pointer<?> handle;
	
	private Runnable runnable;
	
	/**
	 * 
	 */
	public D3D11Canvas() {
		env = JAWTUtils.getJNIEnv();
		jawt = JAWTUtils.getJAWT(env);
		handle = Pointer.pointerToAddress(JAWTUtils.getNativePeerHandle(this));
	}
	
	/**
	 * 
	 */
	public final void paint(Graphics g) {
		JAWTUtils.withLockedSurface(env, jawt, this, new JAWTUtils.LockedComponentRunnable() {
			@Override
			public void run(Component component, long peer) {
				if(runnable != null) {
					runnable.run();
				}
			}
		});
	}
	
	/**
	 * 
	 * @return
	 */
	public Pointer<?> getHandle() {
		return handle;
	}

	public void setRunnable(Runnable r) {
		this.runnable = r;
	}
}
