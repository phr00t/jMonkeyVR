package com.fourthskyinteractive.dx4j.sapi;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Union;

@Library("sapi") 
public class SPPHRASEPROPERTY extends StructObject {
	public SPPHRASEPROPERTY() {
		super();
	}
	/// C type : LPCWSTR
	@Field(0) 
	public Pointer<Character > pszName() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : LPCWSTR
	@Field(0) 
	public SPPHRASEPROPERTY pszName(Pointer<Character > pszName) {
		this.io.setPointerField(this, 0, pszName);
		return this;
	}
	/// C type : field1_union
	@Field(1) 
	public SPPHRASEPROPERTY.union union() {
		return this.io.getNativeObjectField(this, 1);
	}
	/// C type : field1_union
	@Field(1) 
	public SPPHRASEPROPERTY union(SPPHRASEPROPERTY.union field1) {
		this.io.setNativeObjectField(this, 1, field1);
		return this;
	}
	/// C type : LPCWSTR
	@Field(2) 
	public Pointer<Character > pszValue() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : LPCWSTR
	@Field(2) 
	public SPPHRASEPROPERTY pszValue(Pointer<Character > pszValue) {
		this.io.setPointerField(this, 2, pszValue);
		return this;
	}
	@CLong 
	@Field(4) 
	public long ulFirstElement() {
		return this.io.getCLongField(this, 4);
	}
	@CLong 
	@Field(4) 
	public SPPHRASEPROPERTY ulFirstElement(long ulFirstElement) {
		this.io.setCLongField(this, 4, ulFirstElement);
		return this;
	}
	@CLong 
	@Field(5) 
	public long ulCountOfElements() {
		return this.io.getCLongField(this, 5);
	}
	@CLong 
	@Field(5) 
	public SPPHRASEPROPERTY ulCountOfElements(long ulCountOfElements) {
		this.io.setCLongField(this, 5, ulCountOfElements);
		return this;
	}
	/// C type : const SPPHRASEPROPERTY*
	@Field(6) 
	public Pointer<SPPHRASEPROPERTY > pNextSibling() {
		return this.io.getPointerField(this, 6);
	}
	/// C type : const SPPHRASEPROPERTY*
	@Field(6) 
	public SPPHRASEPROPERTY pNextSibling(Pointer<SPPHRASEPROPERTY > pNextSibling) {
		this.io.setPointerField(this, 6, pNextSibling);
		return this;
	}
	/// C type : const SPPHRASEPROPERTY*
	@Field(7) 
	public Pointer<SPPHRASEPROPERTY > pFirstChild() {
		return this.io.getPointerField(this, 7);
	}
	/// C type : const SPPHRASEPROPERTY*
	@Field(7) 
	public SPPHRASEPROPERTY pFirstChild(Pointer<SPPHRASEPROPERTY > pFirstChild) {
		this.io.setPointerField(this, 7, pFirstChild);
		return this;
	}
	@Field(8) 
	public float SREngineConfidence() {
		return this.io.getFloatField(this, 8);
	}
	@Field(8) 
	public SPPHRASEPROPERTY SREngineConfidence(float SREngineConfidence) {
		this.io.setFloatField(this, 8, SREngineConfidence);
		return this;
	}
	@Field(9) 
	public byte Confidence() {
		return this.io.getByteField(this, 9);
	}
	@Field(9) 
	public SPPHRASEPROPERTY Confidence(byte Confidence) {
		this.io.setByteField(this, 9, Confidence);
		return this;
	}
	/// <i>native declaration : line 212</i>
	/// <i>native declaration : line 212</i>
	@Union 
	public static class union extends StructObject {
		public union() {
			super();
		}
		@CLong 
		@Field(0) 
		public long ulId() {
			return this.io.getCLongField(this, 0);
		}
		@CLong 
		@Field(0) 
		public union ulId(long ulId) {
			this.io.setCLongField(this, 0, ulId);
			return this;
		}
		/// C type : field1_struct
		@Field(1) 
		public SPPHRASEPROPERTY.union.field1_struct field1() {
			return this.io.getNativeObjectField(this, 1);
		}
		/// C type : field1_struct
		@Field(1) 
		public union field1(SPPHRASEPROPERTY.union.field1_struct field1) {
			this.io.setNativeObjectField(this, 1, field1);
			return this;
		}
		/// <i>native declaration : line 215</i>
		/// <i>native declaration : line 215</i>
		public static class field1_struct extends StructObject {
			public field1_struct() {
				super();
			}
			@Field(2) 
			public short usArrayIndex() {
				return this.io.getShortField(this, 2);
			}
			@Field(2) 
			public field1_struct usArrayIndex(short usArrayIndex) {
				this.io.setShortField(this, 2, usArrayIndex);
				return this;
			}
			public field1_struct(Pointer<? extends StructObject> pointer) {
				super(pointer);
			}
		};
		public union(Pointer<? extends StructObject> pointer) {
			super(pointer);
		}
	};
	public SPPHRASEPROPERTY(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}
