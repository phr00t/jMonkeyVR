package com.fourthskyinteractive.dx4j.util;

import org.bridj.StructObject;

/**
 * Created with IntelliJ IDEA.
 * User: evandro.paulino
 * Date: 21/06/13
 * Time: 12:45
 * To change this template use File | Settings | File Templates.
 */
public interface Describable {

    public <T extends StructObject> T GetDesc();
}
