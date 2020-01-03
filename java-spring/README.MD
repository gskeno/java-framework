- 1.spring测试用例使用方式
```java
/**
 * 引入spring-test包
 * 利用Runwith
 * 利用ContextConfiguration 支持(多个)配置文件和(多个)配置类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:olympic_beans.xml")
public class OlympicTests {

    @Autowired
    private BasketBall basketBall;

    @Test
    public void test1(){
        basketBall.description();
    }
}
```

- 2.bean property name ref的真正意义
```
<bean id="forest" class="com.gson.spring.bean.Forest">
        <property name="anTree" ref="tree"/>
</bean>

<bean id="tree" class="com.gson.spring.bean.Tree">
        <property name="diameter" value="1"/>
        <property name="height" value="5"/>
</bean>
    
forest这个bean的property的name="anTree"时，只是表示Forest这个类有一个
setAnTree的方法而已，并不能表示Forest有一个名叫anTree的成员变量

ref指向"tree"这个Bean，这个Bean一定需要是com.gson.spring.bean.Tree类型吗？不一定，
可以是生产Tree的工厂类，如
class TreeFactory implements FactoryBean<Tree>
```