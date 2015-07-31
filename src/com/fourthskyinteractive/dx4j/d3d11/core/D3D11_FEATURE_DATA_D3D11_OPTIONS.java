package com.fourthskyinteractive.dx4j.d3d11.core;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;

public class D3D11_FEATURE_DATA_D3D11_OPTIONS extends StructObject {
	public D3D11_FEATURE_DATA_D3D11_OPTIONS() {
		super();
	}
	@Field(0) 
	public int OutputMergerLogicOp() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS OutputMergerLogicOp(int OutputMergerLogicOp) {
		this.io.setIntField(this, 0, OutputMergerLogicOp);
		return this;
	}
	@Field(1) 
	public int UAVOnlyRenderingForcedSampleCount() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS UAVOnlyRenderingForcedSampleCount(int UAVOnlyRenderingForcedSampleCount) {
		this.io.setIntField(this, 1, UAVOnlyRenderingForcedSampleCount);
		return this;
	}
	@Field(2) 
	public int DiscardAPIsSeenByDriver() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS DiscardAPIsSeenByDriver(int DiscardAPIsSeenByDriver) {
		this.io.setIntField(this, 2, DiscardAPIsSeenByDriver);
		return this;
	}
	@Field(3) 
	public int FlagsForUpdateAndCopySeenByDriver() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS FlagsForUpdateAndCopySeenByDriver(int FlagsForUpdateAndCopySeenByDriver) {
		this.io.setIntField(this, 3, FlagsForUpdateAndCopySeenByDriver);
		return this;
	}
	@Field(4) 
	public int ClearView() {
		return this.io.getIntField(this, 4);
	}
	@Field(4) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS ClearView(int ClearView) {
		this.io.setIntField(this, 4, ClearView);
		return this;
	}
	@Field(5) 
	public int CopyWithOverlap() {
		return this.io.getIntField(this, 5);
	}
	@Field(5) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS CopyWithOverlap(int CopyWithOverlap) {
		this.io.setIntField(this, 5, CopyWithOverlap);
		return this;
	}
	@Field(6) 
	public int ConstantBufferPartialUpdate() {
		return this.io.getIntField(this, 6);
	}
	@Field(6) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS ConstantBufferPartialUpdate(int ConstantBufferPartialUpdate) {
		this.io.setIntField(this, 6, ConstantBufferPartialUpdate);
		return this;
	}
	@Field(7) 
	public int ConstantBufferOffsetting() {
		return this.io.getIntField(this, 7);
	}
	@Field(7) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS ConstantBufferOffsetting(int ConstantBufferOffsetting) {
		this.io.setIntField(this, 7, ConstantBufferOffsetting);
		return this;
	}
	@Field(8) 
	public int MapNoOverwriteOnDynamicConstantBuffer() {
		return this.io.getIntField(this, 8);
	}
	@Field(8) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS MapNoOverwriteOnDynamicConstantBuffer(int MapNoOverwriteOnDynamicConstantBuffer) {
		this.io.setIntField(this, 8, MapNoOverwriteOnDynamicConstantBuffer);
		return this;
	}
	@Field(9) 
	public int MapNoOverwriteOnDynamicBufferSRV() {
		return this.io.getIntField(this, 9);
	}
	@Field(9) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS MapNoOverwriteOnDynamicBufferSRV(int MapNoOverwriteOnDynamicBufferSRV) {
		this.io.setIntField(this, 9, MapNoOverwriteOnDynamicBufferSRV);
		return this;
	}
	@Field(10) 
	public int MultisampleRTVWithForcedSampleCountOne() {
		return this.io.getIntField(this, 10);
	}
	@Field(10) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS MultisampleRTVWithForcedSampleCountOne(int MultisampleRTVWithForcedSampleCountOne) {
		this.io.setIntField(this, 10, MultisampleRTVWithForcedSampleCountOne);
		return this;
	}
	@Field(11) 
	public int SAD4ShaderInstructions() {
		return this.io.getIntField(this, 11);
	}
	@Field(11) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS SAD4ShaderInstructions(int SAD4ShaderInstructions) {
		this.io.setIntField(this, 11, SAD4ShaderInstructions);
		return this;
	}
	@Field(12) 
	public int ExtendedDoublesShaderInstructions() {
		return this.io.getIntField(this, 12);
	}
	@Field(12) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS ExtendedDoublesShaderInstructions(int ExtendedDoublesShaderInstructions) {
		this.io.setIntField(this, 12, ExtendedDoublesShaderInstructions);
		return this;
	}
	@Field(13) 
	public int ExtendedResourceSharing() {
		return this.io.getIntField(this, 13);
	}
	@Field(13) 
	public D3D11_FEATURE_DATA_D3D11_OPTIONS ExtendedResourceSharing(int ExtendedResourceSharing) {
		this.io.setIntField(this, 13, ExtendedResourceSharing);
		return this;
	}
	public D3D11_FEATURE_DATA_D3D11_OPTIONS(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
