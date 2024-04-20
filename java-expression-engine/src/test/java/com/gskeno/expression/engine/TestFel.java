package com.gskeno.expression.engine;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.common.ObjectUtils;
import com.greenpineyu.fel.context.AbstractContext;
import com.greenpineyu.fel.context.ContextChain;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.context.MapContext;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;
import org.junit.Test;

import java.util.*;

/**
 * https://blog.51cto.com/u_16213595/9403266
 */
public class TestFel {

    @Test
    public void test(){
        FelEngine fel = new FelEngineImpl();
        Object result = fel.eval("5000*12+7500");
        System.out.println(result);


        FelContext ctx = fel.getContext();
        ctx.set("单价", 5000);
        ctx.set("数量", 12);
        ctx.set("运费", 7500);
        result = fel.eval("单价*数量+运费");
        System.out.println(result);
    }

    @Test
    public void test1(){
        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();

//数组
        int[] intArray = {1,2,3};
        ctx.set("intArray",intArray);
//获取intArray[0]
        String exp = "intArray[0]";
        System.out.println(exp+"->"+fel.eval(exp));

//List
        List<Integer> list = Arrays.asList(1,2,3);
        ctx.set("list",list);
//获取list.get(0)
        exp = "list[0]";
        System.out.println(exp+"->"+fel.eval(exp));

//集合
        Collection<String> coll = Arrays.asList("a","b","c");
        ctx.set("coll",coll);
//获取集合最前面的元素。执行结果为"a"
        exp = "coll[0]";
        System.out.println(exp+"->"+fel.eval(exp));

//迭代器
        Iterator<String> iterator = coll.iterator();
        ctx.set("iterator", iterator);
//获取迭代器最前面的元素。执行结果为"a"
        exp = "iterator[0]";
        System.out.println(exp+"->"+fel.eval(exp));

//Map
        Map<String,String> m = new HashMap<String, String>();
        m.put("name", "HashMap");
        ctx.set("map",m);
        exp = "map.name";
        System.out.println(exp+"->"+fel.eval(exp));

//多维数组
        int[][] intArrays= {{11,12},{21,22}};
        ctx.set("intArrays",intArrays);
        exp = "intArrays[0][0]";
        System.out.println(exp+"->"+fel.eval(exp));

//多维综合体，支持数组、集合的任意组合。
        List<int[]> listArray = new ArrayList<int[]>();
        listArray.add(new int[]{1,2,3});
        listArray.add(new int[]{4,5,6});
        ctx.set("listArray",listArray);
        exp = "listArray[0][0]";
        System.out.println(exp+"->"+fel.eval(exp));
    }

    @Test
    public void test2(){
        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        ctx.set("out", System.out);
        fel.eval("out.println('Hello Everybody'.substring(6))");
    }

    @Test
    public void test3(){
        //负责提供气象服务的上下文环境
        FelContext ctx = new AbstractContext() {
            public Object get(String name) {
                if("天气".equals(name)){
                    return "晴";
                }
                if("温度".equals(name)){
                    return 25;
                }
                return null;
            }
        };
        FelEngine fel = new FelEngineImpl(ctx);
        Object eval = fel.eval("'天气:'+天气+';温度:'+温度");
        System.out.println(eval);
    }

    @Test
    public void test4(){
        FelEngine fel = new FelEngineImpl();
        String costStr = "成本";
        String priceStr="价格";
        FelContext baseCtx = fel.getContext();
//父级上下文中设置成本和价格
        baseCtx.set(costStr, 50);
        baseCtx.set(priceStr,100);

        String exp = priceStr+"-"+costStr;
        Object baseCost = fel.eval(exp);
        System.out.println("期望利润：" + baseCost);

        FelContext ctx = new ContextChain(baseCtx, new MapContext());
//通货膨胀导致成本增加（子级上下文 中设置成本，会覆盖父级上下文中的成本）
        ctx.set(costStr,50+20 );
        Object allCost = fel.eval(exp, ctx);
        System.out.println("实际利润：" + allCost);
    }

    /**
     * 编译执行，比解释执行快
     */
    @Test
    public void test5(){
        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        ctx.set("单价", 5000);
        ctx.set("数量", 12);
        ctx.set("运费", 7500);
        Expression exp = fel.compile("单价*数量+运费",ctx);
        Object result = exp.eval(ctx);
        System.out.println(result);
    }

    /**
     * 编译执行与解释执行
     */
    @Test
    public void test6(){
        //定义hello函数
        Function fun = new CommonFunction() {

            public String getName() {
                return "hello";
            }

            /*
             * 调用hello("xxx")时执行的代码
             */
            @Override
            public Object call(Object[] arguments) {
                Object msg = null;
                if(arguments!= null && arguments.length>0){
                    msg = arguments[0];
                }
                return ObjectUtils.toString(msg);
            }

        };
        FelEngine e = new FelEngineImpl();
//添加函数到引擎中。
        e.addFun(fun);
        String exp = "hello('fel')";
//解释执行
        Object eval = e.eval(exp);
        System.out.println("hello "+eval);
//编译执行
        Expression compile = e.compile(exp, null);
        eval = compile.eval(null);
        System.out.println("hello "+eval);
    }

    @Test
    public void test7(){
        //调用Math.min(1,2)
        Object eval = FelEngine.instance.eval("$('Math').min(1,2)");
        System.out.println(eval);
        //调用new Foo().toString();
    }
}
