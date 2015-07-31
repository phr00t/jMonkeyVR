package com.fourthskyinteractive.dx4j.d3d11.query;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D11_COUNTER;

public class D3D11_COUNTER_INFO extends StructObject {
	public D3D11_COUNTER_INFO() {
		super();
	}
	public D3D11_COUNTER_INFO(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	/// C type : D3D11_COUNTER
	@Field(0) 
	public ValuedEnum<D3D11_COUNTER > Counter() {
		return this.io.getEnumField(this, 0);
	}
	/// C type : D3D11_COUNTER
	@Field(0) 
	public D3D11_COUNTER_INFO Counter(ValuedEnum<D3D11_COUNTER > Counter) {
		this.io.setEnumField(this, 0, Counter);
		return this;
	}
	@Field(1)
	public int NumSimultaneousCounters() {
		return this.io.getIntField(this, 1);
	}
	/// C type : D3D11_COUNTER
	@Field(1) 
	public D3D11_COUNTER_INFO NumSimultaneousCounters(int NumSimultaneousCounters) {
		this.io.setIntField(this, 1, NumSimultaneousCounters);
		return this;
	}
	@Field(2)
	public byte NumDetectableParallelUnits() {
		return this.io.getByteField(this, 2);
	}
	@Field(2)
	public D3D11_COUNTER_INFO NumDetectableParallelUnits(byte NumDetectableParallelUnits) {
		this.io.setByteField(this, 2, NumDetectableParallelUnits);
		return this;
	}
}
