package com.gskeno.expression.engine;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAviator {
    public static void main(String[] args) throws InterruptedException {
        Expression expression = AviatorEvaluator.compile("vip.m==true", true);
        System.out.println("开始sleep");
        Thread.sleep(3000);
        List<String> variableFullNames = expression.getVariableFullNames();
        System.out.println(variableFullNames);
        List<String> variableNames = expression.getVariableNames();
        System.out.println(variableNames);
        Map<String,Object> context = new HashMap<>();
        context.put("vip", "true");
        Boolean vip = (Boolean)expression.execute(context);
        System.out.println(vip);
    }

    @Test
    public void test(){
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }

    @Test
    public void testCompileAndExecute(){

    }


}
