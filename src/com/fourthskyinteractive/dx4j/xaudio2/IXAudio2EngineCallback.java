package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;

/**
 * Contains methods to notify the client when certain events happen
 * in the XAudio2 engine.  This interface should be implemented by
 * the client.  XAudio2 will call these methods via the interface
 * pointer provided by the client when it calls XAudio2Create or
 * IXAudio2::Initialize.
 * 
 * @author Evandro
 *
 */
@Runtime(CPPRuntime.class)
public abstract class IXAudio2EngineCallback extends CPPObject {

	public IXAudio2EngineCallback() {
		// TODO Auto-generated constructor stub
	}

	public IXAudio2EngineCallback(Pointer<? extends CPPObject> peer) {
		super(peer);
		// TODO Auto-generated constructor stub
	}

	public abstract void OnProcessingPassStart();
	
	public abstract void OnProcessingPassEnd();
	
	public abstract void OnCriticalError();

}
