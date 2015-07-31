package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;

@Runtime(CPPRuntime.class)
public class IXAudio2VoiceCallback extends CPPObject {

	// Called just before this voice's processing pass begins.
    @Virtual(0) 
    public final native void OnVoiceProcessingPassStart(int BytesRequired);

    // Called just after this voice's processing pass ends.
    @Virtual(1) 
    public final native void OnVoiceProcessingPassEnd();

    // Called when this voice has just finished playing a buffer stream
    // (as marked with the XAUDIO2_END_OF_STREAM flag on the last buffer).
    @Virtual(2) 
    public final native void OnStreamEnd();

    // Called when this voice is about to start processing a new buffer.
    @Virtual(3) 
    public final native void OnBufferStart(Pointer<?> pBufferContext);

    // Called when this voice has just finished processing a buffer.
    // The buffer can now be reused or destroyed.
    @Virtual(4) 
    public final native void OnBufferEnd(Pointer<?> pBufferContext);

    // Called when this voice has just reached the end position of a loop.
    @Virtual(5) 
    public final native void OnLoopEnd(Pointer<?> pBufferContext);

    // Called in the event of a critical error during voice processing,
    // such as a failing xAPO or an error from the hardware XMA decoder.
    // The voice may have to be destroyed and re-created to recover from
    // the error.  The callback arguments report which buffer was being
    // processed when the error occurred, and its HRESULT code.
    @Virtual(6) 
    public final native void OnVoiceError(Pointer<?> pBufferContext, int Error);
}
