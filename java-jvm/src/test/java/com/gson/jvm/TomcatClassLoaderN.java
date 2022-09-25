package com.gsonkeno.jdk.training.jvm;


import java.io.*;

/**
 * Created by gaosong on 2017-12-29
 * 自定义类加载器 继承自系统类加载器AppClassLoader,打破了双亲委托机制
 */
public class TomcatClassLoaderN extends  ClassLoader{

    private final String classLoaderName;

    public TomcatClassLoaderN(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        ClassLoader systemClassLoader = getSystemClassLoader();
        try {
            systemClassLoader.loadClass(name);
        }catch (Exception e){

        }

        if (clazz != null){
            return clazz;
        }
        clazz = findClass(name);
        return clazz;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is  = null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            is = new FileInputStream(new File
                    ("D:\\test\\com\\huachao\\cl\\Test.class"));
            int c = 0;
            while (-1 != (c=is.read())){
                baos.write(c);
            }
            data = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return this.defineClass(name,data,0,data.length);
    }

    @Override
    public String toString() {
        return classLoaderName;
    }

    public static void main(String[] args) {
        TomcatClassLoaderN loader = new TomcatClassLoaderN(TomcatClassLoaderN.class.getClassLoader(),
                "tomcatLoaderN");
        Class clazz;
        try {
            clazz = loader.loadClass("com.huachao.cl.Test");
            clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
