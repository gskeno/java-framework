/*****************************************************************************
 * Copyright (C) PicoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 *****************************************************************************/
package org.picocontainer.containers;

import junit.framework.AssertionFailedError;
import org.junit.Test;
import org.picocontainer.Characteristics;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.behaviors.ImplementationHiding;
import org.picocontainer.injectors.ConstructorInjection;
import org.picocontainer.tck.AbstractImplementationHidingPicoContainerTest;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Aslak Helles&oslash;y
 */
public class ImplementationHidingWithDefaultPicoContainerTestCase extends AbstractImplementationHidingPicoContainerTest {

    protected MutablePicoContainer createImplementationHidingPicoContainer() {
        return createPicoContainer(null);
    }

    protected Properties[] getProperties() {
        return new Properties[] {Characteristics.NO_CACHE, Characteristics.NO_HIDE_IMPL};
    }

    // TODO (PH) should IH do caching at all and CtorInjection instead of AdaptingInjection ?

    protected void addDefaultComponentFactories(List expectedList) {
        expectedList.add(Caching.class);
        expectedList.add(ImplementationHiding.class);
        expectedList.add(ConstructorInjection.class);
    }

    protected MutablePicoContainer createPicoContainer(PicoContainer parent) {
        return new DefaultPicoContainer(new Caching().wrap(new ImplementationHiding().wrap(new ConstructorInjection())), parent);
    }

    @Test
    public void testAggregatedVerificationException() {
        super.testAggregatedVerificationException();    
    }

    @Test public void testSameInstanceCanBeUsedAsDifferentTypeWhenCaching() {
        // we're choosing a CAF for DPC, thus Caching (a default) not enabled.
        try {
            super.testSameInstanceCanBeUsedAsDifferentTypeWhenCaching();
        } catch (AssertionFailedError e) {
            assertTrue(e.getMessage().indexOf("expected same:<org.picocontainer.testmodel.WashableTouchable@") > -1);
            assertTrue(e.getMessage().indexOf("was not:<org.picocontainer.testmodel.WashableTouchable@") > -1);
        }

    }

    @Test public void testAcceptImplementsBreadthFirstStrategy() {
        super.testAcceptImplementsBreadthFirstStrategy();
    }

}
