package com.gson.roaring.bitmap;

import org.roaringbitmap.Container;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.RunContainer;

public class ArrayContainer2Bitmap2Run {
    public static void main(String[] args) {
        RoaringBitmap bitmap = new RoaringBitmap();
        for (int i = 1; i <=4096 ; i++) {
            bitmap.add(i);
        }
        // 1到4096的高16位都为0，元素存储在keys[0]所指向的values[0]所代表的container
        // ArrayContainer可以存在4096个short元素，大小为4096 * 2= 8KB，线性增长
        Container container = bitmap.getContainerPointer().getContainer();
        System.out.println(container.getClass().getSimpleName());
        assert container.getClass().getSimpleName().equals("ArrayContainer");

        // 添加一个新元素
        // 超过4096个short元素后，变为bitmap
        bitmap.add(4097);
        container = bitmap.getContainerPointer().getContainer();
        System.out.println(container.getClass().getSimpleName());
        assert container.getClass().getSimpleName().equals("BitmapContainer");

        // 步长压缩优化
        boolean b = bitmap.runOptimize();
        System.out.println(b);
        assert b;
        container = bitmap.getContainerPointer().getContainer();
        System.out.println(container.getClass().getSimpleName());
        // 压缩后的元素为1,4096 表示1后面有4096个元素，分别是2,3,4,...,4097
        assert container.getClass().getSimpleName().equals(RunContainer.class.getSimpleName());
    }
}
