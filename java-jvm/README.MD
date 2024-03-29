
# GC

## 查看生产环境上使用的垃圾回收器
`ps -ef|grep java`， 从结果中搜索`GC`关键字，可以找到使用的垃圾回收器。结果中
可能有`-XX:+UseConcMarkSweepGC`，表示使用的是CMS垃圾回收器, 其是老年代下的
垃圾回收器。

使用的是`标记清除(Mark Sweep)`算法。垃圾回收分为4个阶段, 
- 初始标记(不可并发)
- 并发标记
- 重新标记(不可并发)
- 并发清除

在`-XX:+UseConcMarkSweepGC`背景下，年轻代 **默认使用** 的是`ParNew`垃圾回收器。

- 强制设置垃圾回收器
> -XX:+UseSerialGC  参数可以指定年轻代和老年代都使用串行收集器。 
<br><br>等价于新生代使用Serial GC，并且老年代使用Serial Old GC.

# 内存分配与回收策略

- 空间分配担保
> 新生代一次minor gc后,存活的对象可能很多，一个survivor区可能装不下。那么剩下的那些
存活的对象就需要进入老年代。**但是老年代也不一定能装的下**，因此会取历次晋升到老年代
的对象大小平均值 与 当前老年代剩余空间做对比，如果空间剩余，则允许担保，minor gc即可。
否则，需要进行一次 full gc。

- 对象优先在eden区分配
>当eden区内存不够时，会发生一次minor gc。

- 大对象直接进入老年代
>在Serial和ParNew两款收集器下,`-XX:PretenureSizeThreshold=3145728`参数可以
设置直接进入老年代对象的大小(单位字节)。

- 长期存活的对象进入老年代


# 调优实战

## premature promotion(过早提升)


# 代码CASE

- [安全点](src/test/java/com/gson/jvm/safepoint/SafePointTest.java)


