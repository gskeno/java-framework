- 1.测试用例代码中可以读取到main/resources和test/resources目录下的资源文件
```java
URL resource = AppTests.class.getResource("/school.json");
System.out.println(resource);

resource = AppTests.class.getResource("/testschool.json");
System.out.println(resource);

resource = AppTests.class.getClassLoader().getResource("school.json");
System.out.println(resource);

resource = AppTests.class.getClassLoader().getResource("testschool.json");
System.out.println(resource);
```