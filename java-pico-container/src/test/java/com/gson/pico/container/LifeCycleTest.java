package com.gson.pico.container;
import com.gson.pico.container.introduce.*;
import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

public class LifeCycleTest {
    @Test
    public void testLifeCycle1() {
        // 不Caching，则start时不会对StartA实例化，自然不会调用start方法
        MutablePicoContainer A  = new DefaultPicoContainer();
        A.addComponent(StartA.class);

        A.start();
    }

    @Test
    public void testLifeCycle2() {
        // 需要Caching
        MutablePicoContainer A  = new DefaultPicoContainer(new Caching());
        A.addComponent(StartA.class);

        A.start();
    }

    @Test
    public void testLifeCycle3() {
        // 需要Caching
        MutablePicoContainer A  = new DefaultPicoContainer(new Caching());
        A.addComponent(StartA.class);

        MutablePicoContainer B  = new DefaultPicoContainer(new Caching(), A);
        B.addComponent(StartB.class);

        MutablePicoContainer C = new DefaultPicoContainer(new Caching(), A);
        C.addComponent(StartC.class);

        A.addComponent("B", B);
        A.addComponent("C", C);

        MutablePicoContainer D = new DefaultPicoContainer(new Caching(), B);
        D.addComponent(StartD.class);
        B.addComponent("D",D);

        MutablePicoContainer E = new DefaultPicoContainer(new Caching(), C);
        E.addComponent(StartE.class);
        C.addComponent("E",E);

        MutablePicoContainer F = new DefaultPicoContainer(new Caching(), A);
        F.addComponent(StartF.class);
        A.addComponent("F",F);

        A.start();
        A.stop();
    }
}
