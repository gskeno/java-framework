package com.gson.paranamer;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class BaseTest {

    @Test
    public void test()  {
        Method[] methods = Restaurant.class.getDeclaredMethods();

        Paranamer cachingParanamer = new CachingParanamer();

        Paranamer bytecodeReadingParanamer = new BytecodeReadingParanamer();
        for (int i = 0; i < methods.length; i++) {
            // throws ParameterNamesNotFoundException if not found
            String[] parameterNames = cachingParanamer.lookupParameterNames(methods[i], false);
            System.out.println(Arrays.toString(parameterNames));
        }
    }
}
