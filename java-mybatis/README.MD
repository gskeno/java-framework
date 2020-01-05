## 1.SqlSessionFactoryBean的使用方式
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-configuration.xml"/>
</bean>
```

```
SqlSessionFactoryBean 
    ⬇
SqlSessionFactory
    ⬇
SqlSession(SqlSessionTemplate)
```
数据库的增删改查操作都是在`SqlSession`接口提供的方法上完成的


