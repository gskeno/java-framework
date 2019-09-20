package com.gson.generic.util;

/**
 * 枚举工具类
 */
public class EnumUtils {

    /**
     * 将字符串 转化为 枚举类
     * @param t
     * @param str
     * @param <T>
     * @return
     */
    public static  <T extends Enum<T>> T getEnumFormString(Class<T> t, String str){
        T emumT = Enum.valueOf(t, str);
        return emumT;
    }

    public static void main(String[] args) {
        Operation plus = getEnumFormString(Operation.class, "PLUS");
        System.out.println(plus);

        Planet planet = getEnumFormString(Planet.class, "mars");
        System.out.println(planet);
    }
}
