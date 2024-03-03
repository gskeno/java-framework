package com.gson.javajdk.generic;

import java.util.List;

public class CollectionUtils {
    public static <T extends Comparable<T>> void sort(List<T> list) {
        list.sort(null);
    }
}
