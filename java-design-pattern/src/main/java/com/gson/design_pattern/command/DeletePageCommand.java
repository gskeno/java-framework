package com.gsonkeno.pattern.command;

/**
 * Created by gaosong on 2018-01-16
 */
public class DeletePageCommand extends Command {
    @Override
    public void execute() {
        pg.find();
        rg.delete();
        rg.plan();
    }
}
