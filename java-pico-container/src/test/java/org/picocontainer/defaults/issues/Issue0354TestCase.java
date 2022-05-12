package org.picocontainer.defaults.issues;

import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.injectors.SetterInjection;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Issue0354TestCase {

 	@Test
    public void testGenericInjectionWithSetterInjection() {
        DefaultPicoContainer pico = new DefaultPicoContainer(new SetterInjection());
        pico.addComponent(new Cod());
        pico.addComponent(new Shark());
        pico.addComponent(GenericBowl.class);
        //pico.addComponent(ArrayList.class);
        //pico.addComponent(ArrayList.class, ArrayList.class, new CollectionComponentParameter(Fish.class, false));

        GenericBowl bowl = pico.getComponent(GenericBowl.class);
        assertEquals(2, bowl.fishes.size());
    }

	@Test
    public void testGenericInjectionWithConstructorInjection() {
        DefaultPicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(new Cod());
        pico.addComponent(new Shark());
        pico.addComponent(GenericBowl.class);

        GenericBowl bowl = pico.getComponent(GenericBowl.class);
        assertEquals(2, bowl.fishes.size());
    }

    public static interface Fish {
    }

    public static class Cod implements Fish {
    }

    public static class Shark implements Fish {
    }

    public static class GenericBowl {
        List<Fish> fishes;

        public void setFishes(List<Fish> fishes) {
            this.fishes = fishes;
        }

        public GenericBowl() {
        }

        public GenericBowl(List<Fish> fishes) {
            this.fishes = fishes;
        }
    }

}