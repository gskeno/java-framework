

# jdk使用介绍
- [jdk动态代理](src/main/java/com/gson/javajdk/proxyjdk/Main.java)

## 正则表达式回溯导致CPU偏高
- https://www.cnblogs.com/lbxob-tom/p/9334361.html
- https://www.cnblogs.com/study-everyday/p/7426862.html
- https://mp.weixin.qq.com/s?__biz=MzA4MjIyNTY0MQ==&mid=2647738965&idx=1&sn=61706e46fc7cf175ebc17fe5472f9f95&scene=21#wechat_redirect

# Class
- [获取当前方法名](src/test/java/com/gson/javajdk/获取方法名.java)
  - new Object(){ }.getClass().getEnclosingMethod()
  - StackWalker.getInstance().walk(frames -> frames .findFirst().map(StackWalker.StackFrame::getMethodName));
  - new Throwable().getStackTrace()[0].getMethodName()
  - Thread.currentThread().getStackTrace()[1].getMethodName()
  - logback日志中的类名就是第三种方式获取的
  - 性能 1>2>3=4