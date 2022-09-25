package com.gson.design_pattern.command;

/**
 * 抽象命名
 * Created by gaosong on 2018-01-16
 */
public abstract class Command {
    protected RequirementGroup rg = new RequirementGroup();
    protected PageGroup pg = new PageGroup();
    protected CodeGroup cg = new CodeGroup();

    public abstract void execute();
}
