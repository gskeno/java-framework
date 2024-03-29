/*****************************************************************************
 * Copyright (c) PicoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 * Idea by Rachel Davies, Original code by Aslak Hellesoy and Paul Hammant   *
 *****************************************************************************/

package org.picocontainer.defaults;


import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.PicoException;
import org.picocontainer.tck.AbstractPicoContainerTest;

import java.io.*;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;


/**
 * @author Thomas Heller
 * @author Paul Hammant
 */
public class DefaultPicoContainerTreeSerializationTestCase extends AbstractPicoContainerTest {

    protected MutablePicoContainer createPicoContainer(PicoContainer parent) {
        return new DefaultPicoContainer(parent);
    }

    protected Properties[] getProperties() {
        return new Properties[0];
    }
    
    @Test public void testContainerIsDeserializableWithParent() throws PicoException,
                                                                 IOException, ClassNotFoundException {

        PicoContainer parent = createPicoContainer(null);
        MutablePicoContainer child = createPicoContainer(parent);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(child);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        child = (MutablePicoContainer) ois.readObject();
        assertNotNull(child.getParent());
    }
}
