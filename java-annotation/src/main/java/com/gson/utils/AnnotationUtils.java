
package com.gson.utils;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 摘抄自springframework
 */
public abstract class AnnotationUtils {
    public static boolean isInJavaLangAnnotationPackage( String annotationType) {
        return (annotationType != null && annotationType.startsWith("java.lang.annotation"));
    }

    /**
     *  模仿自 recursivelyCollectMetaAnnotations.recursivelyCollectMetaAnnotations 方法
     * 递归获取注解 annotation 上的注解
     * @param visited
     * @param annotation
     * (Annotation) annotation===>(Class) annotation.annotationType()===>(Annotation) annotationType.getAnnotations
     */
    public static void recursivelyCollectMetaAnnotations(Set<Annotation> visited, Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        String annotationName = annotationType.getName();
        if (!AnnotationUtils.isInJavaLangAnnotationPackage(annotationName) && visited.add(annotation)) {
            try {
                for (Annotation metaMetaAnnotation : annotationType.getAnnotations()) {
                    recursivelyCollectMetaAnnotations(visited, metaMetaAnnotation);
                }
            }
            catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }
}

