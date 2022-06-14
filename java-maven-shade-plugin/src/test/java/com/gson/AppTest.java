package com.gson;


import com.gson.shade.Parameter;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testGetMethodNames() throws NoSuchMethodException {
        Parameter parameter = new Parameter();
        String[] names = parameter.getParameterNames(Float.class.getMethod("valueOf", String.class));
        System.out.println(Arrays.toString(names));
    }

}
