/*****************************************************************************
 * Copyright (C) PicoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 * Original code by                                                          *
 *****************************************************************************/
package org.picocontainer.parameters;

import org.junit.Test;
import org.picocontainer.PicoCompositionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * test that constant parameter behaves well.
 * @author Konstantin Pribluda
 */
public class ConstantParameterTestCase {
    
    /**
    *  constant parameter with instance type shall verify for expected primitives
     * @throws Exception
     */
    @Test public void testThatInstaceTypeAcceptedForPrimitives() throws Exception {
        ConstantParameter param = new ConstantParameter(239);
        try{
            param.verify(null,null,Integer.TYPE, null, false, null);
        } catch(PicoCompositionException ex) {
            fail("failed verification for primitive / instance ");
        }
    }
    
    @Test
    public void testClassTypesAllowed() throws Exception {
        ConstantParameter param = new ConstantParameter(String.class);
        param.verify(null, null, Class.class, null, false, null);    	
    }
    
	
    public static class ConstantParameterTestClass {
    	public ConstantParameterTestClass(Class<String> type) {
    		assert type != null;
    	}
    }
        
    
    @Test
    public void testParameterizedTypesAllowed() throws Exception {
    	
    	Constructor<?>[] ctors = ConstantParameterTestClass.class.getConstructors();
    	Type[] t = ctors[0].getGenericParameterTypes();
        ConstantParameter param = new ConstantParameter(String.class);
        assertTrue(param.resolve(null, null, null, t[0], null, false, null).isResolved());

    }
    
}
