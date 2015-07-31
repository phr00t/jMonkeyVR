package com.fourthskyinteractive.dx4j.windows.property;

import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

@Library("")
@Runtime(COMRuntime.class)
@IID("886D8EEB-8CF2-4446-8D02-CDBA1DBDCF99")
public class IPropertyStore extends IUnknown {
	public IPropertyStore() {
		// TODO Auto-generated constructor stub
	}

	@Virtual(0)
	public final native int GetCount(Pointer<Integer> cProps);
        
	@Virtual(1)
	public final native int GetAt(int iProp, Pointer<PROPERTYKEY> pkey);
        
	@Virtual(2)
	public final native int GetValue(Pointer<PROPERTYKEY> key, Pointer<PROPVARIANT> pv);
        
	@Virtual(3)
	public final native int SetValue(Pointer<PROPERTYKEY> key, Pointer<PROPVARIANT> propvar);        
	
	@Virtual(4)
	public final native int Commit();
}
