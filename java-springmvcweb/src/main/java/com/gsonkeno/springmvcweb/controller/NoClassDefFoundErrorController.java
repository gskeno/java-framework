package com.gsonkeno.springmvcweb.controller;

import junit.framework.TestCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoClassDefFoundErrorController {

    /**
     * 会报
     * java.lang.NoClassDefFoundError: junit/framework/TestCase
     *
     * 因为编译时有效，运行时没有相关包，找不到类的定义
     * @return
     */
    @RequestMapping(value = "/getTestCase")
    public String getTestCase(){
        return TestCase.class.getCanonicalName();
    }
}
