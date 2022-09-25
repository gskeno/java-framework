package com.gsonkeno.pattern.command;

/**
 * Created by gaosong on 2018-01-16
 */
public class AddRequirementCommand extends Command {

    @Override
    public void execute() {
        rg.find();
        rg.add();
        rg.plan();
    }
}
