package com.gson.paranamer;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.DefaultParanamer;
import com.thoughtworks.paranamer.Paranamer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseTest {

    @Test
    public void test() {
        Method[] methods = Restaurant.class.getDeclaredMethods();

        Paranamer defaultParanamer = new DefaultParanamer();
        for (int i = 0; i < methods.length; i++) {
            // throws ParameterNamesNotFoundException if not found
            String[] parameterNames = defaultParanamer.lookupParameterNames(methods[i], false);
            System.out.println(Arrays.toString(parameterNames));
        }
    }

    @Test
    public void testDefaultParanamer() throws NoSuchMethodException {
        Paranamer paranamer = new DefaultParanamer();
        String[] getStaffs = paranamer.lookupParameterNames(Restaurant.class.getMethod("getStaffs", Staff.class));
        System.out.println(Arrays.toString(getStaffs));
    }

    @Test
    public void testBytecodeReadingParanamer() throws NoSuchMethodException {
        BytecodeReadingParanamer paranamer = new BytecodeReadingParanamer();

        Method method = Restaurant.class.getMethod("getStaffs", Staff.class);
        String[] params = paranamer.lookupParameterNames(method, false);
        System.out.println(Arrays.toString(params));

        Method getNameMethod = Restaurant.class.getMethod("getName", String.class);
        String[] getNameMethodParams = paranamer.lookupParameterNames(getNameMethod, false);
        System.out.println(Arrays.toString(getNameMethodParams));

        Method valueOfMethod = String.class.getMethod("valueOf", int.class);
        String[] valueOfMethodParams = paranamer.lookupParameterNames(valueOfMethod, false);
        System.out.println(Arrays.toString(valueOfMethodParams));

        Method assertTrueMethod = Assert.class.getMethod("assertTrue", boolean.class);
        String[] assertTrueMethodParams = paranamer.lookupParameterNames(assertTrueMethod);
        System.out.println(Arrays.toString(assertTrueMethodParams));

        Method sleepMethod = Thread.class.getMethod("sleep", long.class);
        String[] sleepMethodParams = paranamer.lookupParameterNames(sleepMethod, false);
        System.out.println(Arrays.toString(sleepMethodParams));
    }

    @Test
    public void testGetMethodParamInJava8() throws NoSuchMethodException {
        Method method = LocalDate.class
                .getMethod("of", int.class, int.class, int.class);

        System.out.printf("public static %s date(%s) {\n",
                method.getReturnType().getSimpleName(),
                Arrays.stream(method.getParameters())
                        .map(p -> String.format("%s %s",
                                p.getType().getSimpleName(), p.getName()))
                        .collect(Collectors.joining(", ")));
        System.out.printf("    return %s.%s(%s);\n",
                method.getDeclaringClass().getSimpleName(),
                method.getName(),
                Arrays.stream(method.getParameters())
                        .map(Parameter::getName)
                        .collect(Collectors.joining(", ")));
        System.out.println("}");
    }

    @Test
    public void testGetJDKMethodParamByParaName() throws NoSuchMethodException {
        Method method = LocalDate.class
                .getMethod("of", int.class, int.class, int.class);

        Paranamer paranamer = new CachingParanamer(new BytecodeReadingParanamer());

        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameterNames = paranamer.lookupParameterNames(method);

        System.out.printf("public static %s date(%s) {\n",
                method.getReturnType().getSimpleName(),
                IntStream.range(0, parameterTypes.length)
                        .mapToObj(i -> String.format("%s %s",
                                parameterTypes[i], parameterNames[i]))
                        .collect(Collectors.joining(", ")));
        System.out.printf("    return %s.%s(%s);\n",
                method.getDeclaringClass().getSimpleName(),
                method.getName(),
                String.join(", ", parameterNames));
        System.out.println("}");
    }


}
