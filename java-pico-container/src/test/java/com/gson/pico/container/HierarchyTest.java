package com.gson.pico.container;

import com.gson.pico.container.introduce.Apple;
import com.gson.pico.container.introduce.Juicer;
import com.gson.pico.container.introduce.Peeler;
import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

/**
 * 继承Test
 */
public class HierarchyTest {
    @Test
    public void test(){
        // 容器继承，子容器的组件初始化时，如果依赖父容器中的组件，是可成立的，反之不可
        MutablePicoContainer x = new DefaultPicoContainer();
        MutablePicoContainer y = new DefaultPicoContainer( x );
        MutablePicoContainer z = new DefaultPicoContainer( y );
        // Assemble components
        x.addComponent(Apple.class);
        y.addComponent(Juicer.class);
        z.addComponent(Peeler.class);
        // Instantiate components
        Peeler peeler = z.getComponent(Peeler.class);
        System.out.println(peeler);
        // WON'T WORK! peeler will be null
        // x是最顶层容器，其内只有Apple组件，没有Peeler组件
        peeler = x.getComponent(Peeler.class);
        System.out.println(peeler);
        // WON'T WORK! This will throw an exception
        // 因为Juicer的构造函数依赖一个Peeler和一个Peelable
        // y容器可以看到x父容器中的Apple作为Peelable,
        // 但是看不到z子容器中的Peeler，所以抛出异常
        Juicer juicer =  y.getComponent(Juicer.class);
        System.out.println(juicer);
    }
}
