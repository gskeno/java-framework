/*****************************************************************************
 * Copyright (C) PicoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 * Original code by                                                          *
 *****************************************************************************/
package org.picocontainer.defaults.issues;

import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.picocontainer.*;
import org.picocontainer.injectors.AbstractInjector;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;
import static org.picocontainer.tck.MockFactory.mockeryWithCountingNamingScheme;

@RunWith(JMock.class)
public class Issue0265TestCase {

	private Mockery mockery = mockeryWithCountingNamingScheme();

    @Test public void testCanReallyChangeMonitor() throws SecurityException, NoSuchMethodException {
        final Method start = Startable.class.getMethod("start");
        final Method stop = Startable.class.getMethod("stop");
        final ComponentMonitor monitor1 = mockery.mock(ComponentMonitor.class, "Monitor1");
        final ComponentMonitor monitor2 = mockery.mock(ComponentMonitor.class, "Monitor2");
        DefaultPicoContainer pico = new DefaultPicoContainer(monitor1);
        mockery.checking(new Expectations(){{
            one(monitor1).newBehavior(with(any(Behavior.class)));
            will(returnParameterAction(0));
            one(monitor1).newInjector(with(any(AbstractInjector.class)));
            will(returnParameterAction(0));
            one(monitor1).instantiating(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(any(Constructor.class)));
            will(returnValue(DefaultPicoContainerTestCase.MyStartable.class.getConstructor()));
            one(monitor1).instantiated(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(any(Constructor.class)), 
            		with(any(Object.class)), with(any(Object[].class)), with(any(Long.class)));
            one(monitor1).invoking(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(start)), 
            		with(any(Object.class)), with(any(Object[].class)));
            one(monitor1).invoked(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(start)), 
            		with(any(Object.class)), with(any(Long.class)), with(any(Object[].class)), //with(same(null))
                    null);
            one(monitor1).invoking(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(stop)), 
            		with(any(Object.class)), with(any(Object[].class)));
            one(monitor1).invoked(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(stop)), 
            		with(any(Object.class)), with(any(Long.class)), with(any(Object[].class)), //with(same(null))
                    null);
        }});
        pico.as(Characteristics.CACHE).addComponent(DefaultPicoContainerTestCase.MyStartable.class);
        pico.start();
        pico.stop();
        Startable startable = pico.getComponent(DefaultPicoContainerTestCase.MyStartable.class);
        assertNotNull(startable);
        pico.changeMonitor(monitor2);
        mockery.checking(new Expectations(){{
            one(monitor2).invoking(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(start)),
            		with(any(Object.class)), with(any(Object[].class)));
            one(monitor2).invoked(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(start)), 
            		with(any(Object.class)), with(any(Long.class)), with(any(Object[].class)), //with(same(null))
                    null);
            one(monitor2).invoking(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(stop)), 
            		with(any(Object.class)), with(any(Object[].class)));
            one(monitor2).invoked(with(any(PicoContainer.class)), with(any(ComponentAdapter.class)), with(equal(stop)), 
            		with(any(Object.class)), with(any(Long.class)), with(any(Object[].class)), //with(same(null))
                    null);
        }});
        pico.start();
        pico.stop();
    }

    public static Action returnParameterAction(int param) {
        return new ReturnParameterAction(param);
    }

    public static class ReturnParameterAction implements Action {
        private final int parameter;

        public ReturnParameterAction(int parameter) {
            this.parameter = parameter;
        }

        public void describeTo(Description description) {
            description.appendText("returns param[")
                    .appendValue(parameter)
                    .appendText("]");
        }

        public Object invoke(Invocation invocation) {
            return invocation.getParameter(parameter);
        }
    }


}
