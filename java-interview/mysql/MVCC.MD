# MVCC是什么？
- 英文名称 Multi-Version Concurrency Control 多版本并发控制。
- 解释说明: 一个事务在**真正**开启前，会去向系统申请一个事务id(**trx_id**),这个事务id是全局递增的。而真正开启事务，创建一致性视图
  (**Read View**)是在执行事务语句内的第一条select快照图语句时。<br><br>
  
  当然你可以通过`start transaction with consistent snapshot`开启事务
  并同时创建一致性视图，这时事务id和一致性视图是一起出来的。如下图所示
  <img src="两阶段提交.png" width = "200" height = "300" alt="" align=center />

 
  