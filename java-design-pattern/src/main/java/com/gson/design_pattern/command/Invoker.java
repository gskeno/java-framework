package com.gsonkeno.pattern.command;

/**
 * 负责人
 * Created by gaosong on 2018-01-16
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void action(){
        command.execute();
    }
}
