>线程上下文类加载器（Thread Context ClassLoader）。
> 
> 这个类加载器可以通过java.lang.Thread类的
setContextClassLoaser（）方法进行设置，如果创建线程时还未设置，它将会从父线程中继承
一个，
> 
> 如果在应用程序的全局范围内都没有设置过的话，那这个类加载器默认就是应用程序类加载器

问1:服务提供者需要在resources/META-INF目录下建立一个以接口全限定名A为名称的文件，文件的内容是接口实现类的全限定名B，为什么要这样做？

答1:会根据A寻找文件，文件中的内容B是服务提供方的全类名，这样就能定位到具体的服务提供方实现。

问2:一个典型的例子就是JNDI服务，JNDI现在已经是Java的标准服务，它的代码由启动类加载器去加载（在JDK1.3时就放进去的rt.jar）,
但它需要调用由独立厂商实现并部署在应用程序的ClassPath下的JNDI接口提供者（SPI，Service Provider Interface）的代码，
但启动类加载器不可能“认识“这些代码啊。因为这些类不在rt.jar中，但是启动类加载器又需要加载。怎么办呢？

答2:为了解决这个问题，Java设计团队只好引入了一个不太优雅的设计：线程上下文类加载器（Thread Context ClassLoader）。
这个类加载器可以通过java.lang.Thread类的setContextClassLoader方法进行设置。如果创建线程时还未设置，
它将会从父线程中继承一个，如果在应用程序的全局范围内都没有设置过的话，那这个类加载器默认即是应用程序类加载器。 
有了线程上下文加载器，JNDI服务使用这个线程上下文加载器去加载所需要的SPI代码，
也就是父类加载器请求子类加载器去完成类加载的动作，
这种行为实际上就是打通了双亲委派模型的层次结构来逆向使用类加载器，实际上已经违背了双亲委派模型的一般性原则。
但这无可奈何，Java中所有涉及SPI的加载动作基本上都采用这种方式。例如JNDI，JDBC，JCE，JAXB，JBI等。
