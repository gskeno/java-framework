package com.gson.fastjson;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void testParseStr2JsonObject(){
        String str = "{\"cardNo\":134213}";
        Member member = JSONObject.parseObject(str, Member.class);
        // member不为null，只是属性值都为默认值
        Assert.assertTrue(member != null);
        System.out.println(member);
    }
}
