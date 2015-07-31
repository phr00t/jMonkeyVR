package com.fourthskyinteractive.dx4j.dxgi.adapter;

import com.fourthskyinteractive.dx4j.dxgi.DXGI;
import com.fourthskyinteractive.dx4j.dxgi.DXGI_MODE_DESC;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * Created with IntelliJ IDEA.
 * User: evandro.paulino
 * Date: 17/06/13
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Library("dxgi")
public class DXGI_OUTDUPL_DESC extends StructObject {
    public DXGI_OUTDUPL_DESC() {
        super();
    }
    public DXGI_OUTDUPL_DESC(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public DXGI_MODE_DESC ModeDesc() {
        return this.io.getNativeObjectField(this, 0);
    }

    @Field(1)
    public IntValuedEnum<DXGI.DXGI_MODE_ROTATION> Rotation() {
        return this.io.getEnumField(this, 1);
    }

    @Field(1)
    public DXGI_OUTDUPL_DESC Rotation(IntValuedEnum<DXGI.DXGI_MODE_ROTATION> rotation) {
        this.io.setEnumField(this, 1, rotation);
        return this;
    }

    @Field(2)
    public int DesktopImageInSystemMemory() {
        return this.io.getIntField(this, 2);
    }

    @Field(2)
    public DXGI_OUTDUPL_DESC DesktopImageInSystemMemory(int bool) {
        this.io.setIntField(this, 2, bool);
        return this;
    }
}
