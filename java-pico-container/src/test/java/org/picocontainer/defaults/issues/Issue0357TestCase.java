package org.picocontainer.defaults.issues;

import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.PicoCompositionException;
import org.picocontainer.injectors.Provider;
import org.picocontainer.injectors.ProviderAdapter;

import static org.junit.Assert.assertNotNull;

public class Issue0357TestCase {
    
    public static class SimpleFoo {
		public SimpleFoo() throws Exception {
			throw new Exception("deliberate");
		}
	}

	public static class SimpleProvider implements Provider {
		public SimpleFoo provide() throws Exception {
			return new SimpleFoo();
		}
	}

	@Test(expected = PicoCompositionException.class)
	// this method should throw an exception, but silently returns null (the issue)
	public void testShouldThrowWhenProvideMethodThrowsForCheckedException() {
		DefaultPicoContainer cont = new DefaultPicoContainer();
		cont.addAdapter(new ProviderAdapter(new SimpleProvider()));
		assertNotNull(cont.getComponent(SimpleFoo.class));
	}

	@Test(expected = PicoCompositionException.class)
	public void testShouldThrowWhenConstructorThrowsForCheckedException() {
		DefaultPicoContainer cont = new DefaultPicoContainer();
		cont.addComponent(SimpleFoo.class);
		assertNotNull(cont.getComponent(SimpleFoo.class));
	}

	public static class SimpleFooRuntime {
		public SimpleFooRuntime() {
			throw new RuntimeException("deliberate");
		}
	}

	public static class SimpleProviderRuntime implements Provider {
		public SimpleFooRuntime provide() {
			return new SimpleFooRuntime();
		}
	}

	@Test(expected = RuntimeException.class)
	public void testShouldThrowWhenProvideMethodThrowsForRuntimeException() {
		DefaultPicoContainer cont = new DefaultPicoContainer();
		cont.addAdapter(new ProviderAdapter(new SimpleProviderRuntime()));
		assertNotNull(cont.getComponent(SimpleFooRuntime.class));
	}

}