package com.gson.fastjson;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

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

    @Test
    public void test(){
        String row = "{\"cardNum\":\"602804779\",\"extra\":\"{\"storeId\":\"00100\",\"shopId\":\"210820279\",\"operatorName\":\"张琪\",\"operatorId\":\"20642\",\"posId\":\"1118\"}\",\"merchantCode\":\"SANJGW\",\"score\":\"13.0\",\"storeId\":0,\"tranId\":\"1422500259151739169\",\"userId\":0}";
        int extraIndex = row.indexOf("\"extra\"");
        int merchantCodeIndex = row.indexOf("\"merchantCode");

        String s1 = row.substring(0, extraIndex);
        String s2 = row.substring(merchantCodeIndex);

        String extraInfo = row.substring(extraIndex + 9, merchantCodeIndex - 2);
        String newJsonInfo = s1 + s2;

        System.out.println(newJsonInfo);
        System.out.println(extraInfo);
    }
}
