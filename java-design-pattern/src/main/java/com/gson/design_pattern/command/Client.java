package com.gsonkeno.pattern.command;

/**
 * Created by gaosong on 2018-01-16
 */
public class Client {

    public static void main(String[] args) {
        //新增需求命令
        AddRequirementCommand command = new AddRequirementCommand();

        //责任人
        Invoker invoker = new Invoker();
        //给责任人发命令
        invoker.setCommand(command);
        //责任人执行命令
        invoker.action();
    }
}
