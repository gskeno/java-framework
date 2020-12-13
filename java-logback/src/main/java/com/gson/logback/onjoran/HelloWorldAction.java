package com.gson.logback.onjoran;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ImplicitModelAction;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.xml.sax.Attributes;

public class HelloWorldAction  extends Action {
    /**
     * Called when the parser encounters an element matching a
     * {@link ElementSelector Pattern}.
     *
     * @param intercon
     * @param name
     * @param attributes
     */
    @Override
    public void begin(InterpretationContext intercon, String name, Attributes attributes) throws ActionException {
        System.out.println("Hello World");
    }

    @Override
    public void end(InterpretationContext intercon, String name) throws ActionException {
    }
}
