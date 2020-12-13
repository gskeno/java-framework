package com.gson.logback.onjoran;

import ch.qos.logback.core.joran.GenericConfigurator;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ImplicitModelAction;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.RuleStore;
import ch.qos.logback.core.joran.spi.SaxEventInterpreter;

import java.util.List;
import java.util.Map;

public class SimpleConfigurator extends GenericConfigurator {

    final Map<ElementSelector, Action> ruleMap;
    final List<ImplicitModelAction> iaList;

    public SimpleConfigurator(Map<ElementSelector, Action> ruleMap) {
        this(ruleMap, null);
    }

    public SimpleConfigurator(Map<ElementSelector, Action> ruleMap, List<ImplicitModelAction> iaList) {
        this.ruleMap = ruleMap;
        this.iaList = iaList;
    }

    @Override
    protected void addInstanceRules(RuleStore rs) {
        for (ElementSelector elementSelector : ruleMap.keySet()) {
            Action action = ruleMap.get(elementSelector);
            rs.addRule(elementSelector, action);
        }
    }

    @Override
    protected void addImplicitRules(SaxEventInterpreter interpreter) {
        if (iaList == null) {
            return;
        }
        for (ImplicitModelAction ia : iaList) {
            interpreter.addImplicitAction(ia);
        }
    }
}
