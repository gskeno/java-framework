package com.gson.roaring.bitmap;

import org.roaringbitmap.Container;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.RunContainer;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ArrayContainer2Bitmap2Run {
    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
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
        ByteBuffer buffer = ByteBuffer.allocateDirect(bitmap.serializedSizeInBytes());
        // debug to find RoaringFormatSpec
        bitmap.serialize(buffer);
    }

    public static void test2(){
        RoaringBitmap bitmap = new RoaringBitmap();
        int segment = (int)Math.pow(2,16) - 1;
        // 高16位由1-7组成，故由7个key,7个Container
        for (int i = 1; i <= 7 * segment ; i++) {
            bitmap.add(i);
        }

        boolean b = bitmap.runOptimize();
        System.out.println(b);
        ByteBuffer buffer = ByteBuffer.allocateDirect(bitmap.serializedSizeInBytes());
        // debug to find RoaringFormatSpec
        bitmap.serialize(buffer);

        System.out.println(buffer.get(0));
        System.out.println(buffer.get(1));
        System.out.println(buffer.get(2));
        System.out.println(buffer.get(3));
        System.out.println(buffer.get(4));

    }
}
