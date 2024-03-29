/*****************************************************************************
 * Copyright (C) PicoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 * Original code by                                                          *
 *****************************************************************************/
package org.picocontainer.tck;


import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


/**
 * @author Paul Hammant
 */
public abstract class AbstractImplementationHidingPicoContainerTest extends AbstractPicoContainerTest {


    @Test public void testInstanceIsNotAutomaticallyHidden() {
        MutablePicoContainer pc = createImplementationHidingPicoContainer();
        pc.as(this.getProperties()).addComponent(Map.class, new HashMap());
        Map map = pc.getComponent(Map.class);
        assertNotNull(map);
        assertTrue(map instanceof HashMap);
    }

    protected abstract MutablePicoContainer createImplementationHidingPicoContainer();


    @Test public void testImplementaionIsAutomaticallyHidden() {
        MutablePicoContainer pc = createImplementationHidingPicoContainer();
        pc.addComponent(Map.class, HashMap.class);
        Map map = pc.getComponent(Map.class);
        assertNotNull(map);
        assertFalse(map instanceof HashMap);
    }

    @Test public void testNonInterfaceImplementaionIsAutomaticallyHidden() {
        MutablePicoContainer pc = createImplementationHidingPicoContainer();
        pc.addComponent(HashMap.class, HashMap.class);
        Map map = pc.getComponent(HashMap.class);
        assertNotNull(map);
        assertTrue(map instanceof HashMap);
    }

    @Test public void testNonInterfaceImplementaionWithParametersIsAutomaticallyHidden() {
        MutablePicoContainer pc = createImplementationHidingPicoContainer();
        pc.addComponent(HashMap.class, HashMap.class);
        Map map = pc.getComponent(HashMap.class);
        assertNotNull(map);
        assertTrue(map instanceof HashMap);
    }


    @Test public void testImplementaionWithParametersIsAutomaticallyHidden() {
        MutablePicoContainer pc = createImplementationHidingPicoContainer();
        pc.addComponent(Map.class, HashMap.class);
        Map map = pc.getComponent(Map.class);
        assertNotNull(map);
        assertFalse(map instanceof HashMap);
    }

    @Test public void testSerializedContainerCanRetrieveImplementation() throws PicoException,
                                                                          IOException, ClassNotFoundException {
        try {
            super.testSerializedContainerCanRetrieveImplementation();
            fail("The ImplementationHidingPicoContainer should not be able to retrieve the component impl");
        } catch (ClassCastException cce) {
            // expected.
        }
    }

    @Test public void testExceptionThrowingFromHiddenComponent() {
        MutablePicoContainer pc = createImplementationHidingPicoContainer();
        pc.addComponent(ActionListener.class, Burp.class);
        try {
            ActionListener ac = pc.getComponent(ActionListener.class);
            ac.actionPerformed(null);
            fail("Oh no.");
        } catch (RuntimeException e) {
            assertEquals("woohoo", e.getMessage());
        }
    }

    public static class Burp implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            throw new RuntimeException("woohoo");
        }
    }

}
