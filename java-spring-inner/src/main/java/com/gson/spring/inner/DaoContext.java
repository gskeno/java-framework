package com.gson.spring.inner;

import org.springframework.beans.factory.annotation.Autowired;

public class DaoContext {
    /**
     * @Autowired 自动查找 Bean 的顺序
     *
     * 根据 @Qualifier 指定的 Bean 名字查找
     * 如果没有指定 @Qualifier，则根据 @Primary 注解查找
     * 如果没有指定 @Primary，则把属性名当作 Bean 名字查找
     * 如果还没找到，则根据类型查找，如果有多个相同类型的类，则无法找到，报错
     */
    @Autowired
    //这里属性名必须是basicDao，否则有多个类型是BasicDao的bean，启动异常
    private BasicDao basicDao;

    public BasicDao getBasicDao() {
        return basicDao;
    }


    @Override
    public String toString() {
        return "DaoContext{" +
                "basicDao=" + basicDao +
                '}';
    }
}
