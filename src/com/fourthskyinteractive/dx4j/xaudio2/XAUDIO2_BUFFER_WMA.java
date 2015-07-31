package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class XAUDIO2_BUFFER_WMA extends StructObject {
	public XAUDIO2_BUFFER_WMA() {
		super();
	}
	/// Decoded packet's cumulative size array.
	@Field(0) 
	public Pointer<Integer > pDecodedPacketCumulativeBytes() {
		return this.io.getPointerField(this, 0);
	}
	/// Decoded packet's cumulative size array.
	@Field(0) 
	public XAUDIO2_BUFFER_WMA pDecodedPacketCumulativeBytes(Pointer<Integer > pDecodedPacketCumulativeBytes) {
		this.io.setPointerField(this, 0, pDecodedPacketCumulativeBytes);
		return this;
	}
	/**
	 * order.  The array must have PacketCount elements.<br>
	 * Number of XWMA packets submitted. Must be >= 1 and
	 */
	@Field(1) 
	public int PacketCount() {
		return this.io.getIntField(this, 1);
	}
	/**
	 * order.  The array must have PacketCount elements.<br>
	 * Number of XWMA packets submitted. Must be >= 1 and
	 */
	@Field(1) 
	public XAUDIO2_BUFFER_WMA PacketCount(int PacketCount) {
		this.io.setIntField(this, 1, PacketCount);
		return this;
	}
	public XAUDIO2_BUFFER_WMA(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
