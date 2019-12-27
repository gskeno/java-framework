package com.gson.springaop;

import com.gson.springaop.services.ReadService;
import com.gson.springaop.services.WriteService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springaop.xml");

        WriteService writeService = context.getBean(WriteService.class);
        writeService.write("写书");

        ReadService readService = context.getBean(ReadService.class);
        readService.read("读书");

    }
}
