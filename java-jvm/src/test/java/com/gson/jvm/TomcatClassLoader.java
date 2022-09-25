package com.gsonkeno.jdk.training.jvm;

import java.io.*;

/**
 * Created by gaosong on 2017-12-29
 * 自定义类加载器 继承自系统类加载器AppClassLoader,也继承了双亲委托机制
 */
public class TomcatClassLoader extends  ClassLoader{

    private final String classLoaderName;

    public TomcatClassLoader( String classLoaderName) {
        //super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    public String toString() {
        return classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is  = null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            is = new FileInputStream(new File(Test.class.getName()));
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

    public static void main(String[] args) {
        TomcatClassLoader loader = new TomcatClassLoader(//TomcatClassLoader.class.getClassLoader(),
                "tomcatLoader");
        Class clazz;
        try {
            clazz = loader.loadClass(Test.class.getName());
            clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
