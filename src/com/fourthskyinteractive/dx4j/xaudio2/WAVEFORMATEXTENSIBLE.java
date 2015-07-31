package com.fourthskyinteractive.dx4j.xaudio2;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Union;
import org.bridj.cpp.com.GUID;

import com.fourthskyinteractive.dx4j.util.WAVEFORMATEX;

public class WAVEFORMATEXTENSIBLE extends StructObject {
	public WAVEFORMATEXTENSIBLE() {
		super();
	}
	@Field(0) 
	public WAVEFORMATEX Format() {
		return this.io.getNativeObjectField(this, 0);
	}
	@Field(0) 
	public WAVEFORMATEXTENSIBLE Format(WAVEFORMATEX Format) {
		this.io.setNativeObjectField(this, 0, Format);
		return this;
	}
	@Field(1) 
	public WAVEFORMATEXTENSIBLE.Samples_union Samples() {
		return this.io.getNativeObjectField(this, 1);
	}
	@Field(1) 
	public WAVEFORMATEXTENSIBLE Samples(WAVEFORMATEXTENSIBLE.Samples_union Samples) {
		this.io.setNativeObjectField(this, 1, Samples);
		return this;
	}
	@Field(2) 
	public int dwChannelMask() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public WAVEFORMATEXTENSIBLE dwChannelMask(int dwChannelMask) {
		this.io.setIntField(this, 2, dwChannelMask);
		return this;
	}
	@Field(3) 
	public GUID SubFormat() {
		return this.io.getNativeObjectField(this, 3);
	}
	@Field(3) 
	public WAVEFORMATEXTENSIBLE SubFormat(GUID SubFormat) {
		this.io.setNativeObjectField(this, 3, SubFormat);
		return this;
	}
	@Union 
	public static class Samples_union extends StructObject {
		public Samples_union() {
			super();
		}
		@Field(0) 
		public short wValidBitsPerSample() {
			return this.io.getShortField(this, 0);
		}
		@Field(0) 
		public Samples_union wValidBitsPerSample(short wValidBitsPerSample) {
			this.io.setShortField(this, 0, wValidBitsPerSample);
			return this;
		}
		@Field(1) 
		public short wSamplesPerBlock() {
			return this.io.getShortField(this, 1);
		}
		@Field(1) 
		public Samples_union wSamplesPerBlock(short wSamplesPerBlock) {
			this.io.setShortField(this, 1, wSamplesPerBlock);
			return this;
		}
		@Field(2) 
		public short wReserved() {
			return this.io.getShortField(this, 2);
		}
		@Field(2) 
		public Samples_union wReserved(short wReserved) {
			this.io.setShortField(this, 2, wReserved);
			return this;
		}
		public Samples_union(Pointer<? extends StructObject> pointer) {
			super(pointer);
		}
	};
	public WAVEFORMATEXTENSIBLE(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
