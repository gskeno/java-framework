package com.gson.javajdk.memory;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * https://www.cnblogs.com/jajian/p/13681781.html
 *
 * Java对象内存结构测试
 */
public class ClassStructTest {
    /**
     * object header 就需要对齐的
     *
     * OFFSET：偏移地址，单位字节；
     * SIZE：占用的内存大小，单位为字节；
     * TYPE DESCRIPTION：类型描述，其中object header为对象头；
     * VALUE：对应内存中当前存储的值，二进制32位；
     *
     * com.gson.javajdk.memory.D object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           77 16 01 f8 (01110111 00010110 00000001 11111000) (-134146441)
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     *
     * 可以看到，d对象实例共占据16byte，对象头（object header）占据12byte（96bit），
     * 其中 mark word占8byte（64bit），klass pointe 占4byte，另外剩余4byte是填充对齐的。
     *
     * 可以尝试关闭指针压缩 -XX:-UseCompressedOops
     */
    @Test
    public void testPlainObject(){
        D d = new D();
        System.out.println(ClassLayout.parseInstance(d).toPrintable());
    }

    public static void main(String[] args) {
        ClassStructTest classStructTest = new ClassStructTest();
        classStructTest.testArray();
        System.out.println("-----");
        classStructTest.testSimpleObject();
    }

    @Test
    public void testSimpleObject(){
        E e = new E();
        System.out.println(ClassLayout.parseInstance(e).toPrintable());

    }

    public void testArray(){
        byte[] a = new byte[]{1,2,3};
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
