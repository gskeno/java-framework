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