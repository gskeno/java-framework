//package com.gson.javajdk.lang;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.AbstractCollection;
//import java.util.AbstractList;
//import java.util.ArrayList;
//
//public class ClassTest {
//
//    /**
//     * 测试 classA.isAssignableFrom(classB)的用法
//     * classA 是 classB 的父类或者接口
//     */
//    @Test
//    public void testAssignableFrom(){
//        // Number是Float的父类
//        Assert.assertTrue(Number.class.isAssignableFrom(Float.class));
//
//        // Comparable是Float的接口
//        Assert.assertTrue(Comparable.class.isAssignableFrom(Float.class));
//
//        // 同类也是true
//        Assert.assertTrue(Float.class.isAssignableFrom(Float.class));
//
//        // 其他情况返回false
//        Assert.assertFalse(Float.class.isAssignableFrom(Number.class));
//    }
//
//
//    // 1. getSupperClass 获取超类，而不是获取所实现接口
//    //   如果一个类没有显示的超类，则该方法返回Obejct.class
//
//    @Test
//    public void testSuperClass(){
//        Assert.assertEquals(ArrayList.class.getSuperclass(), AbstractList.class);
//
//        Assert.assertEquals(AbstractList.class.getSuperclass(), AbstractCollection.class);
//
//        Assert.assertEquals(AbstractCollection.class.getSuperclass(), Object.class);
//    }
//
//    @Test
//    public void testGenericSuperClass(){
//        Type genericSuperclass = ArrayList.class.getGenericSuperclass();
//        System.out.println(genericSuperclass.getTypeName() + "---" + genericSuperclass.getClass());
//
//        genericSuperclass = String.class.getGenericSuperclass();
//        System.out.println(genericSuperclass.getTypeName() + "---" + genericSuperclass.getClass());
//
//    }
//
//    // 返回表示某些接口的 Type，这些接口由此对象所表示的类或接口直接实现。
//    @Test
//    public void testGenericInterfaces(){
//        Type[] genericInterfaces = ArrayList.class.getGenericInterfaces();
//        for (Type type : genericInterfaces) {
//            System.out.println(type.getTypeName() + "---" + type.getClass());
//
//            //表明接口是一个范型接口，如 List<E>接口
//            if (type instanceof ParameterizedType){
//                // 获取到原型类型（即不带范型，如 interface java.util.List
//                System.out.println("---" + ((ParameterizedType)type).getRawType());
//                System.out.println("---" + ((Class<?>)((ParameterizedType)type).getRawType()).getName());
//            }
//        }
////java.util.List<E>---class sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
////java.util.RandomAccess---class java.lang.Class
////java.lang.Cloneable---class java.lang.Class
////java.io.Serializable---class java.lang.Class
//
//        // FinalArrayList并没有直接实现接口，所以这里为空
//        genericInterfaces = FinalArrayList.class.getGenericInterfaces();
//        for (Type type : genericInterfaces) {
//            System.out.println(type.getTypeName() + "---" + type.getClass());
//        }
//
//    }
//}
