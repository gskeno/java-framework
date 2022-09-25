package com.gson.annotation;

import com.gson.bean.BlackPeople;

import com.gson.utils.AnnotationUtils;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;


public class AppTest {

    @Test
    public void test(){
        //获取类上的注解
        Annotation[] annotations = BlackPeople.class.getAnnotations();

        for (Annotation annotation: annotations) {
            System.out.println(annotation);
            //如果属于Configuration注解的实例，获取注解上的值
            if (annotation instanceof Configuration){
                System.out.println(((Configuration)annotation).value());
            }
        }
    }

    @Test
    public void test1(){
        //所有注解类型均继承自 Annotation接口
        Class<? extends Annotation> annotationClass = SpringBootConfiguration.class;

        //当注解类型不是java.lang.annotation包下的注解类型时
        if (!AnnotationUtils.isInJavaLangAnnotationPackage(annotationClass.getName())){

            //获取注解类SpringBootConfiguration上的注解
            Annotation[] annotations = annotationClass.getAnnotations();
            Set<Annotation> annotationSet = new HashSet<>();

            for (Annotation annotation: annotations) {
                System.out.println(annotation);
                AnnotationUtils.recursivelyCollectMetaAnnotations(annotationSet,annotation);
            }

            //annotationSet 包含SpringBootConfiguration注解上的递归注解
            System.out.println("\r\n===SpringBootConfiguration注解上递归获取到的注解(非java.lang.annotation包下)有==");
            System.out.println(annotationSet);


        }
    }

}
