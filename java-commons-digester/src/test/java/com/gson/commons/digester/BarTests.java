package com.gson.commons.digester;

import org.apache.commons.digester3.Digester;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

public class BarTests {
    @Test
    public void test(){
        try
        {
            //1、创建Digester对象实例
            Digester digester = new Digester();

            //2、配置属性值
            digester.setValidating(false);

            //3、push对象到对象栈
            //digester.push(new Foo());

            //4、设置匹配模式、规则
            digester.addObjectCreate("foo", "com.gson.commons.digester.Foo");
            digester.addSetProperties("foo");
            digester.addObjectCreate("foo/bar", "com.gson.commons.digester.Bar");
            digester.addSetProperties("foo/bar");
            digester.addSetNext("foo/bar", "addBar", "com.gson.commons.digester.Bar");

            //5、开始解析
            Foo foo = digester.parse(BarTests.class.getClassLoader().getResourceAsStream("foo.xml"));

            //6、打印解析结果
            System.out.println(foo.getName());
            for (Bar bar : foo.getBarList())
            {
                System.out.println(bar.getId() + "," + bar.getTitle());
            }

        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
    }
}
