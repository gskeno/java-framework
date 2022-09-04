
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

在`-XX:+UseConcMarkSweepGC`背景下，年轻代 默认使用的是`ParNew`垃圾回收器。




