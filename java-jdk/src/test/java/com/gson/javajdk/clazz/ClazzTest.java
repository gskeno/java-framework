package com.gson.javajdk.clazz;

import com.gson.javajdk.clazzloader.Bar;
import com.gson.javajdk.clazzloader.Foo;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.UnexpectedException;
import java.security.AccessControlException;
import java.security.Permission;
import java.util.LinkedList;
import java.util.List;

public class ClazzTest {
    @Test
    public void test() {
        Class<?> clazz = LinkedList.class;
        Class<? extends List> aClass = clazz.asSubclass(List.class);

        System.out.println(aClass);
    }

    @Test
    public void testCast() {
        List<String> a = new LinkedList<>();
        a.add("1");

        Object b = a;
        /**
         * 将对象转化为某特定类
         */
        List cast = List.class.cast(b);
        System.out.println(cast);

        cast = (List) a;
        System.out.println(cast);
    }

    @Test
    public void testGetDeclaredField() throws NoSuchFieldException, IllegalAccessException {
        Field value = String.class.getDeclaredField("value");
        System.out.println(value);
        String abc = new String("abc");
        try {
            value.get(String.class);
            throw new UnexpectedException("not run here");
        } catch (Exception e) {
            e.printStackTrace();
            assert e instanceof IllegalAccessException;
        }

        int[] ints = new int[5];
        // 数组是没有DeclaredField的
        Field[] declaredFields = ints.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }
    }

    @Test
    public void testGetDeclaredField1() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(WholeObject.class.getDeclaredField("a"));
        System.out.println(WholeObject.class.getDeclaredField("b"));
        System.out.println(WholeObject.class.getDeclaredField("c"));
        System.out.println(WholeObject.class.getDeclaredField("d"));
        System.out.println(WholeObject.class.getDeclaredField("e"));
        System.out.println(WholeObject.class.getDeclaredField("f"));

        Field f = WholeObject.class.getDeclaredField("f");
        System.out.println(f.get(WholeObject.class));
        System.out.println(f.get(new WholeObject()));
        System.out.println(f.get(new WholeObject()));
    }

    @Test
    public void testGetDeclaredField2() {
        String[] strs = new String[]{"abc", "def"};
        Field[] declaredFields = strs.getClass().getDeclaredFields();
        assert declaredFields.length == 0;
    }

    @Test
    public void testGetDeclaredField3() throws MalformedURLException, ClassNotFoundException, NoSuchFieldException {
        // Foo 由AppClassLoader加载
        Foo foo = new Foo();
        URL[] urls = {new File("target/classes").toURI().toURL()};
        // 父加载器是null
        URLClassLoader urlClassLoader = new URLClassLoader(urls, null);
        // 因为其无父类加载器，该Bar类则由自身类加载器URLClassLoader进行加载
        Class<Bar> barClass = (Class<Bar>) urlClassLoader.loadClass("com.gson.javajdk.clazzloader.Bar");
        try {
            System.setSecurityManager(new CustomizerSecurityManager());
            foo.invokeDeclaredField(barClass);
            // throw new RuntimeException("not run here");
        }
        // java.security.AccessControlException: access denied
        // ("java.lang.RuntimePermission" "accessDeclaredMembers")
        catch (AccessControlException e) {
            e.printStackTrace();
        }

        // Foo 和Bar 都是AppClassLoader加载的，所以不会有访问权限异常
        // foo.invokeDeclaredField(Bar.class);
    }

    @Test
    public void testGetDeclaredField4() throws NoSuchFieldException, ClassNotFoundException, MalformedURLException {
        // Foo 由AppClassLoader加载
        Foo foo = new Foo();
        URL[] urls = {new File("target/classes").toURI().toURL()};
        // 父加载器是null
        URLClassLoader urlClassLoader = new URLClassLoader(urls, null);
        // 因为其无父类加载器，该Bar类则由自身类加载器URLClassLoader进行加载
        Class<Bar> barClass = (Class<Bar>) urlClassLoader.loadClass("com.gson.javajdk.clazzloader.Bar");
        try {
            System.setSecurityManager(new CustomizerSecurityManager());
            foo.invokeDeclaredField(barClass);
        }
        // java.security.AccessControlException: access denied
        // ("java.lang.RuntimePermission" "accessDeclaredMembers")
        catch (AccessControlException e) {
            e.printStackTrace();
        }
    }



    static class CustomizerSecurityManager extends SecurityManager{
        @Override
        public void checkPermission(Permission perm) {
            // 检查通过
            String name = perm.getName();
            if (name.equals("accessDeclaredMembers")){
                return;
            }
            super.checkPermission(perm);
        }

        @Override
        public void checkPackageAccess(String pkg) {
            if (pkg.equals("com.gson.javajdk.clazzloader")){
                throw new RuntimeException("not allow access package com.gson.javajdk.clazzloader");
            }
            super.checkPackageAccess(pkg);
        }
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        // Foo 由AppClassLoader加载
        Foo foo = new Foo();
        URL[] urls = {new File("java-jdk/target/classes").toURI().toURL()};
        System.out.println(urls[0]);
        // 父加载器是null
        URLClassLoader urlClassLoader = new URLClassLoader(urls, null);
        // 因为其无父类加载器，该Bar类则由自身类加载器URLClassLoader进行加载
        Class<Bar> barClass = (Class<Bar>) urlClassLoader.loadClass("com.gson.javajdk.clazzloader.Bar");
        try {
            System.setSecurityManager(new CustomizerSecurityManager());
            foo.invokeDeclaredField(barClass);
        }
        // java.security.AccessControlException: access denied
        // ("java.lang.RuntimePermission" "accessDeclaredMembers")
        catch (AccessControlException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testClassForName() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> forNameObjectClass1 =  Class.forName(
                "com.gson.javajdk.clazz.ForNameObject",
                // 不进行初始化，则类变量不会被初始化，且静态代码块不会执行
                false,
                this.getClass().getClassLoader());
        System.out.println("-----------");
//        ForNameObject o = (ForNameObject)forNameObjectClass1.newInstance();
//        System.out.println(o.getName0());
        System.out.println("-----------");

        Class<?> forNameObjectClass2 =  Class.forName(
                "com.gson.javajdk.clazz.ForNameObject",
                // 进行初始化，则类变量会被初始化，且静态代码块会执行
                true,
                this.getClass().getClassLoader());
    }

    @Test
    public void testGetSuperClass(){
        // Void的父类是Object
        System.out.println(Void.class.getSuperclass());
        // int的父类是Number
        Object a = 5;
        Class<?> superclass = a.getClass().getSuperclass();
        System.out.println(superclass);

        // 数组的父类是Object
        int[] m = new int[]{1,2};
        System.out.println(m.getClass().getSuperclass());

        // 顶级接口的父类是null
        System.out.println(Iterable.class.getSuperclass());

        // List接口的父类是null, 它没有父类，虽然它继承Iterable接口
        System.out.println(List.class.getSuperclass());

        // 不可变类String的父类是Object
        System.out.println(String.class.getSuperclass());

        System.out.println(Array.class.getSuperclass());
    }

    @Test
    public void testGetMethodArgs() throws NoSuchMethodException {
        Method describe = Mountain.class.getMethod("describe", Stone.class,  Tree.class);
        Parameter[] parameters = describe.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            System.out.println(parameters[i].getType() + ":" + parameters[i].getName());
        }
    }
}
