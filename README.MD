模块太多，为了避免内存加载全部module，占用太多内存，可在父pom.xml文件中移除暂时不关心的子module节省内存占用空间。

## java-junit
- @RunWith(Parameterized.class) 可以构造任意组单元测试输入数据，以成员变量的方式，使同一个
测试案例可以根据不同的输入参数，执行多次。

## java-spring
- [入口](java-spring)
- spring基础使用


## java-generic
- [入口](java-generic)
- java 范型使用

## java-jackson
- [入口](java-jackson)
- jackson包基本使用
- Jackson的JsonTypeInfo注解实现多态类的解析  https://blog.csdn.net/bruce128/article/details/80298808

## java-lang
java.lang包中的关键类、关键方法使用

## java-reflections
类扫描神器，可以
- 获取某个类型的全部子类
- 只要类型、构造器、方法，字段上带有特定注解，便能获取带有这个注解的全部信息（类型、构造器、方法，字段）
- 获取所有能匹配某个正则表达式的资源
- 获取所有带有特定签名的方法，包括参数，参数注解，返回类型
- 获取所有方法的名字
- 获取代码里所有字段、方法名、构造器的使用
- https://blog.csdn.net/bruce128/article/details/80387036

## java-springaop
- [入口](java-springaop)

## java-ttl
- Alibaba提出的父子线程在池化背景下上下文有效传递的技术方案