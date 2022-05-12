/*****************************************************************************
 * Copyright (C) PicoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 * Original code by                                                          *
 *****************************************************************************/
package org.picocontainer.alternatives;
import org.junit.Test;
import org.picocontainer.injectors.ParameterNameBinding;
import org.picocontainer.paranamer.AdaptiveParanamer;
import org.picocontainer.paranamer.CachingParanamer;
import org.picocontainer.paranamer.DefaultParanamer;
import org.picocontainer.paranamer.Paranamer;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParanamerPicoContainerTestCase {

    @Test
    public void testCanInstantiateParanamer() {
        Paranamer paranamer = new DefaultParanamer();
        assertNotNull(paranamer);
    }


    public void methodToFind(String name) {
		assert name != null;
	}
    

    @Test
    public void
           testNameBindingShouldNotThrowWhenAreParameterNamesAreNotAvailable()
            throws Exception {
        Paranamer paranamer = new CachingParanamer(new AdaptiveParanamer());

        Method method = getClass().getMethod("methodToFind", String.class);
        ParameterNameBinding binding = new ParameterNameBinding(paranamer, method, 0);

        assertEquals("name", binding.getName());
    }


}
