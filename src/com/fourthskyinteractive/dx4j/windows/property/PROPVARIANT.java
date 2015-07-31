package com.fourthskyinteractive.dx4j.windows.property;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Union;
import org.bridj.cpp.com.CLSID;
import org.bridj.cpp.com.CY;
import org.bridj.cpp.com.DECIMAL;
import org.bridj.cpp.com.IDispatch;
import org.bridj.cpp.com.IUnknown;

public class PROPVARIANT extends StructObject {
	public PROPVARIANT() {
		super();
	}
	/// C type : VARTYPE
	@Field(0) 
	public short vt() {
		return this.io.getShortField(this, 0);
	}
	/// C type : VARTYPE
	@Field(0) 
	public PROPVARIANT vt(short vt) {
		this.io.setShortField(this, 0, vt);
		return this;
	}
	@Field(1) 
	public short wReserved1() {
		return this.io.getShortField(this, 1);
	}
	@Field(1) 
	public PROPVARIANT wReserved1(short wReserved1) {
		this.io.setShortField(this, 1, wReserved1);
		return this;
	}
	@Field(2) 
	public short wReserved2() {
		return this.io.getShortField(this, 2);
	}
	@Field(2) 
	public PROPVARIANT wReserved2(short wReserved2) {
		this.io.setShortField(this, 2, wReserved2);
		return this;
	}
	@Field(3) 
	public short wReserved3() {
		return this.io.getShortField(this, 3);
	}
	@Field(3) 
	public PROPVARIANT wReserved3(short wReserved3) {
		this.io.setShortField(this, 3, wReserved3);
		return this;
	}
	/// C type : field1_union
	@Field(4) 
	public PROPVARIANT.field1_union field1() {
		return this.io.getNativeObjectField(this, 4);
	}
	/// C type : field1_union
	@Field(4) 
	public PROPVARIANT field1(PROPVARIANT.field1_union field1) {
		this.io.setNativeObjectField(this, 4, field1);
		return this;
	}
	/// <i>native declaration : line 41</i>
	/// <i>native declaration : line 41</i>
	@Union 
	public static class field1_union extends StructObject {
		public field1_union() {
			super();
		}
		/// C type : CHAR
		@Field(0) 
		public byte cVal() {
			return this.io.getByteField(this, 0);
		}
		/// C type : CHAR
		@Field(0) 
		public field1_union cVal(byte cVal) {
			this.io.setByteField(this, 0, cVal);
			return this;
		}
		/// C type : UCHAR
		@Field(1) 
		public byte bVal() {
			return this.io.getByteField(this, 1);
		}
		/// C type : UCHAR
		@Field(1) 
		public field1_union bVal(byte bVal) {
			this.io.setByteField(this, 1, bVal);
			return this;
		}
		@Field(2) 
		public short iVal() {
			return this.io.getShortField(this, 2);
		}
		@Field(2) 
		public field1_union iVal(short iVal) {
			this.io.setShortField(this, 2, iVal);
			return this;
		}
		@Field(3) 
		public short uiVal() {
			return this.io.getShortField(this, 3);
		}
		@Field(3) 
		public field1_union uiVal(short uiVal) {
			this.io.setShortField(this, 3, uiVal);
			return this;
		}
		@CLong 
		@Field(4) 
		public long lVal() {
			return this.io.getCLongField(this, 4);
		}
		@CLong 
		@Field(4) 
		public field1_union lVal(long lVal) {
			this.io.setCLongField(this, 4, lVal);
			return this;
		}
		@CLong 
		@Field(5) 
		public long ulVal() {
			return this.io.getCLongField(this, 5);
		}
		@CLong 
		@Field(5) 
		public field1_union ulVal(long ulVal) {
			this.io.setCLongField(this, 5, ulVal);
			return this;
		}
		@Field(6) 
		public int intVal() {
			return this.io.getIntField(this, 6);
		}
		@Field(6) 
		public field1_union intVal(int intVal) {
			this.io.setIntField(this, 6, intVal);
			return this;
		}
		@Field(7) 
		public int uintVal() {
			return this.io.getIntField(this, 7);
		}
		@Field(7) 
		public field1_union uintVal(int uintVal) {
			this.io.setIntField(this, 7, uintVal);
			return this;
		}
		@Field(10) 
		public float fltVal() {
			return this.io.getFloatField(this, 10);
		}
		@Field(10) 
		public field1_union fltVal(float fltVal) {
			this.io.setFloatField(this, 10, fltVal);
			return this;
		}
		@Field(11) 
		public double dblVal() {
			return this.io.getDoubleField(this, 11);
		}
		@Field(11) 
		public field1_union dblVal(double dblVal) {
			this.io.setDoubleField(this, 11, dblVal);
			return this;
		}
		/// C type : VARIANT_BOOL
		@Field(12) 
		public short boolVal() {
			return this.io.getShortField(this, 12);
		}
		/// C type : VARIANT_BOOL
		@Field(12) 
		public field1_union boolVal(short boolVal) {
			this.io.setShortField(this, 12, boolVal);
			return this;
		}
		/// C type : SCODE
		@CLong 
		@Field(13) 
		public long scode() {
			return this.io.getCLongField(this, 13);
		}
		/// C type : SCODE
		@CLong 
		@Field(13) 
		public field1_union scode(long scode) {
			this.io.setCLongField(this, 13, scode);
			return this;
		}
		/// C type : DATE
		@Field(15) 
		public double date() {
			return this.io.getDoubleField(this, 15);
		}
		/// C type : DATE
		@Field(15) 
		public field1_union date(double date) {
			this.io.setDoubleField(this, 15, date);
			return this;
		}
		/// C type : CLSID*
		@Field(17) 
		public Pointer<CLSID > puuid() {
			return this.io.getPointerField(this, 17);
		}
		/// C type : CLSID*
		@Field(17) 
		public field1_union puuid(Pointer<CLSID > puuid) {
			this.io.setPointerField(this, 17, puuid);
			return this;
		}
		/// C type : CLIPDATA*
//		@Field(18) 
//		public Pointer<CLIPDATA > pclipdata() {
//			return this.io.getPointerField(this, 18);
//		}
//		/// C type : CLIPDATA*
//		@Field(18) 
//		public field1_union pclipdata(Pointer<CLIPDATA > pclipdata) {
//			this.io.setPointerField(this, 18, pclipdata);
//			return this;
//		}
		/// C type : LPSTR
		@Field(22) 
		public Pointer<Byte > pszVal() {
			return this.io.getPointerField(this, 22);
		}
		/// C type : LPSTR
		@Field(22) 
		public field1_union pszVal(Pointer<Byte > pszVal) {
			this.io.setPointerField(this, 22, pszVal);
			return this;
		}
		/// C type : IUnknown*
		@Field(24) 
		public Pointer<IUnknown > punkVal() {
			return this.io.getPointerField(this, 24);
		}
		/// C type : IUnknown*
		@Field(24) 
		public field1_union punkVal(Pointer<IUnknown > punkVal) {
			this.io.setPointerField(this, 24, punkVal);
			return this;
		}
		/// C type : IDispatch*
		@Field(25) 
		public Pointer<IDispatch > pdispVal() {
			return this.io.getPointerField(this, 25);
		}
		/// C type : IDispatch*
		@Field(25) 
		public field1_union pdispVal(Pointer<IDispatch > pdispVal) {
			this.io.setPointerField(this, 25, pdispVal);
			return this;
		}
		/// C type : IStream*
//		@Field(26) 
//		public Pointer<IStream > pStream() {
//			return this.io.getPointerField(this, 26);
//		}
//		/// C type : IStream*
//		@Field(26) 
//		public field1_union pStream(Pointer<IStream > pStream) {
//			this.io.setPointerField(this, 26, pStream);
//			return this;
//		}
//		/// C type : IStorage*
//		@Field(27) 
//		public Pointer<IStorage > pStorage() {
//			return this.io.getPointerField(this, 27);
//		}
//		/// C type : IStorage*
//		@Field(27) 
//		public field1_union pStorage(Pointer<IStorage > pStorage) {
//			this.io.setPointerField(this, 27, pStorage);
//			return this;
//		}
		/// C type : CHAR*
		@Field(52) 
		public Pointer<Byte > pcVal() {
			return this.io.getPointerField(this, 52);
		}
		/// C type : CHAR*
		@Field(52) 
		public field1_union pcVal(Pointer<Byte > pcVal) {
			this.io.setPointerField(this, 52, pcVal);
			return this;
		}
		/// C type : UCHAR*
		@Field(53) 
		public Pointer<Byte > pbVal() {
			return this.io.getPointerField(this, 53);
		}
		/// C type : UCHAR*
		@Field(53) 
		public field1_union pbVal(Pointer<Byte > pbVal) {
			this.io.setPointerField(this, 53, pbVal);
			return this;
		}
		/// C type : short*
		@Field(54) 
		public Pointer<Short > piVal() {
			return this.io.getPointerField(this, 54);
		}
		/// C type : short*
		@Field(54) 
		public field1_union piVal(Pointer<Short > piVal) {
			this.io.setPointerField(this, 54, piVal);
			return this;
		}
		/// C type : USHORT*
		@Field(55) 
		public Pointer<Short > puiVal() {
			return this.io.getPointerField(this, 55);
		}
		/// C type : USHORT*
		@Field(55) 
		public field1_union puiVal(Pointer<Short > puiVal) {
			this.io.setPointerField(this, 55, puiVal);
			return this;
		}
		/// C type : long*
		@Field(56) 
		public Pointer<CLong > plVal() {
			return this.io.getPointerField(this, 56);
		}
		/// C type : long*
		@Field(56) 
		public field1_union plVal(Pointer<CLong > plVal) {
			this.io.setPointerField(this, 56, plVal);
			return this;
		}
		/// C type : ULONG*
		@Field(57) 
		public Pointer<CLong > pulVal() {
			return this.io.getPointerField(this, 57);
		}
		/// C type : ULONG*
		@Field(57) 
		public field1_union pulVal(Pointer<CLong > pulVal) {
			this.io.setPointerField(this, 57, pulVal);
			return this;
		}
		/// C type : INT*
		@Field(58) 
		public Pointer<Integer > pintVal() {
			return this.io.getPointerField(this, 58);
		}
		/// C type : INT*
		@Field(58) 
		public field1_union pintVal(Pointer<Integer > pintVal) {
			this.io.setPointerField(this, 58, pintVal);
			return this;
		}
		/// C type : UINT*
		@Field(59) 
		public Pointer<Integer > puintVal() {
			return this.io.getPointerField(this, 59);
		}
		/// C type : UINT*
		@Field(59) 
		public field1_union puintVal(Pointer<Integer > puintVal) {
			this.io.setPointerField(this, 59, puintVal);
			return this;
		}
		/// C type : FLOAT*
		@Field(60) 
		public Pointer<Float > pfltVal() {
			return this.io.getPointerField(this, 60);
		}
		/// C type : FLOAT*
		@Field(60) 
		public field1_union pfltVal(Pointer<Float > pfltVal) {
			this.io.setPointerField(this, 60, pfltVal);
			return this;
		}
		/// C type : DOUBLE*
		@Field(61) 
		public Pointer<Double > pdblVal() {
			return this.io.getPointerField(this, 61);
		}
		/// C type : DOUBLE*
		@Field(61) 
		public field1_union pdblVal(Pointer<Double > pdblVal) {
			this.io.setPointerField(this, 61, pdblVal);
			return this;
		}
		/// C type : VARIANT_BOOL*
		@Field(62) 
		public Pointer<Short > pboolVal() {
			return this.io.getPointerField(this, 62);
		}
		/// C type : VARIANT_BOOL*
		@Field(62) 
		public field1_union pboolVal(Pointer<Short > pboolVal) {
			this.io.setPointerField(this, 62, pboolVal);
			return this;
		}
		/// C type : DECIMAL*
		@Field(63) 
		public Pointer<DECIMAL > pdecVal() {
			return this.io.getPointerField(this, 63);
		}
		/// C type : DECIMAL*
		@Field(63) 
		public field1_union pdecVal(Pointer<DECIMAL > pdecVal) {
			this.io.setPointerField(this, 63, pdecVal);
			return this;
		}
		/// C type : SCODE*
		@Field(64) 
		public Pointer<CLong > pscode() {
			return this.io.getPointerField(this, 64);
		}
		/// C type : SCODE*
		@Field(64) 
		public field1_union pscode(Pointer<CLong > pscode) {
			this.io.setPointerField(this, 64, pscode);
			return this;
		}
		/// C type : CY*
		@Field(65) 
		public Pointer<CY > pcyVal() {
			return this.io.getPointerField(this, 65);
		}
		/// C type : CY*
		@Field(65) 
		public field1_union pcyVal(Pointer<CY > pcyVal) {
			this.io.setPointerField(this, 65, pcyVal);
			return this;
		}
		/// C type : DATE*
		@Field(66) 
		public Pointer<Double > pdate() {
			return this.io.getPointerField(this, 66);
		}
		/// C type : DATE*
		@Field(66) 
		public field1_union pdate(Pointer<Double > pdate) {
			this.io.setPointerField(this, 66, pdate);
			return this;
		}
//		/// C type : BSTR*
//		@Field(67) 
//		public Pointer<BSTR > pbstrVal() {
//			return this.io.getPointerField(this, 67);
//		}
//		/// C type : BSTR*
//		@Field(67) 
//		public field1_union pbstrVal(Pointer<BSTR > pbstrVal) {
//			this.io.setPointerField(this, 67, pbstrVal);
//			return this;
//		}
		/// C type : IUnknown**
		@Field(68) 
		public Pointer<Pointer<IUnknown > > ppunkVal() {
			return this.io.getPointerField(this, 68);
		}
		/// C type : IUnknown**
		@Field(68) 
		public field1_union ppunkVal(Pointer<Pointer<IUnknown > > ppunkVal) {
			this.io.setPointerField(this, 68, ppunkVal);
			return this;
		}
		/// C type : IDispatch**
		@Field(69) 
		public Pointer<Pointer<IDispatch > > ppdispVal() {
			return this.io.getPointerField(this, 69);
		}
		/// C type : IDispatch**
		@Field(69) 
		public field1_union ppdispVal(Pointer<Pointer<IDispatch > > ppdispVal) {
			this.io.setPointerField(this, 69, ppdispVal);
			return this;
		}
//		/// C type : LPSAFEARRAY*
//		@Field(70) 
//		public Pointer<LPSAFEARRAY > pparray() {
//			return this.io.getPointerField(this, 70);
//		}
//		/// C type : LPSAFEARRAY*
//		@Field(70) 
//		public field1_union pparray(Pointer<LPSAFEARRAY > pparray) {
//			this.io.setPointerField(this, 70, pparray);
//			return this;
//		}
		/// C type : PROPVARIANT*
		@Field(71) 
		public Pointer<PROPVARIANT > pvarVal() {
			return this.io.getPointerField(this, 71);
		}
		/// C type : PROPVARIANT*
		@Field(71) 
		public field1_union pvarVal(Pointer<PROPVARIANT > pvarVal) {
			this.io.setPointerField(this, 71, pvarVal);
			return this;
		}
		public field1_union(Pointer<? extends StructObject> pointer) {
			super(pointer);
		}
	};
	public PROPVARIANT(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
}

	/*
	 typedef struct PROPVARIANT {
		  VARTYPE vt;
		  WORD    wReserved1;
		  WORD    wReserved2;
		  WORD    wReserved3;
		  union {
		    CHAR              cVal;
		    UCHAR             bVal;
		    SHORT             iVal;
		    USHORT            uiVal;
		    LONG              lVal;
		    ULONG             ulVal;
		    INT               intVal;
		    UINT              uintVal;
		    LARGE_INTEGER     hVal;
		    ULARGE_INTEGER    uhVal;
		    FLOAT             fltVal;
		    DOUBLE            dblVal;
		    VARIANT_BOOL      boolVal;
		    SCODE             scode;
		    CY                cyVal;
		    DATE              date;
		    FILETIME          filetime;
		    CLSID             *puuid;
		    CLIPDATA          *pclipdata;
		    BSTR              bstrVal;
		    BSTRBLOB          bstrblobVal;
		    BLOB              blob;
		    LPSTR             pszVal;
		    LPWSTR            pwszVal;
		    IUnknown          *punkVal;
		    IDispatch         *pdispVal;
		    IStream           *pStream;
		    IStorage          *pStorage;
		    LPVERSIONEDSTREAM pVersionedStream;
		    LPSAFEARRAY       parray;
		    CAC               cac;
		    CAUB              caub;
		    CAI               cai;
		    CAUI              caui;
		    CAL               cal;
		    CAUL              caul;
		    CAH               cah;
		    CAUH              cauh;
		    CAFLT             caflt;
		    CADBL             cadbl;
		    CABOOL            cabool;
		    CASCODE           cascode;
		    CACY              cacy;
		    CADATE            cadate;
		    CAFILETIME        cafiletime;
		    CACLSID           cauuid;
		    CACLIPDATA        caclipdata;
		    CABSTR            cabstr;
		    CABSTRBLOB        cabstrblob;
		    CALPSTR           calpstr;
		    CALPWSTR          calpwstr;
		    CAPROPVARIANT     capropvar;
		    CHAR              *pcVal;
		    UCHAR             *pbVal;
		    SHORT             *piVal;
		    USHORT            *puiVal;
		    LONG              *plVal;
		    ULONG             *pulVal;
		    INT               *pintVal;
		    UINT              *puintVal;
		    FLOAT             *pfltVal;
		    DOUBLE            *pdblVal;
		    VARIANT_BOOL      *pboolVal;
		    DECIMAL           *pdecVal;
		    SCODE             *pscode;
		    CY                *pcyVal;
		    DATE              *pdate;
		    BSTR              *pbstrVal;
		    IUnknown          **ppunkVal;
		    IDispatch         **ppdispVal;
		    LPSAFEARRAY       *pparray;
		    PROPVARIANT       *pvarVal;
		  };
		} PROPVARIANT;
	 */