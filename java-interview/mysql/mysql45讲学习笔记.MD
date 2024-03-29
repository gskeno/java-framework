
[索引](#索引)

[日志](#日志)

&nbsp; &nbsp; [redolog](#redolog)

&nbsp; &nbsp; [binlog](#binlog)

&nbsp; &nbsp; [undolog](#undolog)

[事务](#事务)

&nbsp; &nbsp; [开启事务](#开启事务)


&nbsp; &nbsp; [四大特性](#四大特性)


&nbsp; &nbsp; [隔离级别](#隔离级别)


[幻读](#幻读)

&nbsp; &nbsp;  [幻读是什么](#幻读是什么)

&nbsp; &nbsp;  [幻读发生在什么场景](#幻读发生在什么场景)

&nbsp; &nbsp;  [幻读有什么问题](#幻读有什么问题)

&nbsp; &nbsp;  [如何解决幻读](#如何解决幻读)

&nbsp; &nbsp;  [间隙锁会带来什么负面影响](#间隙锁会带来什么负面影响)

[锁](#锁)

&nbsp; &nbsp;  [加锁规则](#加锁规则)



# 索引

## 索引分类
- 按照物理存储，可以分为 `聚簇索引`, `非聚簇索引(二级索引、辅助索引)`。
- [MySQL索引都有哪些分类](https://developer.huawei.com/consumer/cn/forum/topic/0204405591412170236)

## 聚簇索引与非聚簇索引
- 区别在于聚簇索引叶子节点是数据节点，而非聚簇索引叶子节点是索引文件，文件中包含着对应数据块的指针。
- [MySQL聚簇索引和非聚簇索引的理解](https://segmentfault.com/a/1190000041290817)

# 日志
## redolog
- **inno db引擎层** 特有的日志模块，保证服务宕机后，之前提交的数据依然不会丢失。
- 是物理日志，记录的是"某个数据页发生了什么更改**。

## binlog
- **server**层日志
- 是逻辑日志，记录是"某个行记录发生了什么变化**。
- 两种格式 statement，记录执行的sql; row 记录前后两条行记录。

> [日志系统：一条SQL更新语句是如何执行的](https://time.geekbang.org/column/article/68633)

![](mysql逻辑架构图.webp)

## undolog
- undo log 是逻辑日志，记录的是行数据被修改前的值。
- 提供了回滚机制和多版本并发控制(MVCC机制)。


# 事务

## 开启事务
两种方式


```sql
begin; -- 或者start transaction
update xxx;
commit; -- 或者rollback
```

```sql
set autocommit = 0; -- 关闭自动提交
update xxx;
commit; -- 或者rollback
```

## 四大特性
- A 原子性，一个事务的全部操作要么全成功，要么全不成功，是不可分割的最小操作单元。失败需要回滚，
涉及到逻辑日志 `undo log`。
- C 一致性。
- I 隔离性, MVCC 多版本并发控制机制。
- D 持久性， redo log， 二阶段提交。



## 隔离级别
mysql事务的隔离级别分为4种，串行化`Serializable`, 可重复读`Repeatable Read`，
读提交`Read Committed`， 读未提交`Read Uncommitted`。其中 **默认是可重复读**。

- `show variables like '%tx_isolation%';`, 查看隔离级别。


- `select @@tx_isolation;`, 查看**当前会话**隔离级别。


- `set session transaction isolation level read committed;`,
  设置当前会话隔离级别为读提交。


- `set global transaction isolation level read commited;`，设置全局会话隔离级别
  为读提交。


- `transaction-isolation = REPEATABLE-READ`, mysql.ini配置文件末尾加上该配置。

# 幻读
## 幻读是什么
>幻读指在一个事务中前后两次查询同一个范围数据的时候，
> 后一次查询看到了前一次查询**没有看到的行**。(**_因为期间有新记录插入_**)

## 幻读发生在什么场景
> 可重复读隔离级别下，发生在 _"当前读"_ 场景下，因为普通的查询是 _"快照读"_ , 
> 看不到别的事务插入的数据。


## 幻读有什么问题
> 破坏语意，比如`select * from t where d = 5 for update`, 表示对表中所有d=5的行锁住，
不允许别的事务进行写操作。

> 数据不一致，主要是binlog的时序结果与实际表中的数据不一致。

## 如何解决幻读
> 使用间隙锁 GAP LOCK, 锁住的是两个值(索引列行值)之间的间隙。比如
<br> begin;
<br>select * from t where d = 5 for update;
<br>commit;
<br> id是索引列，其他列没有索引，则会全表扫描，不仅会对所有的行记录加锁，
> 还会对id之间的间隙加 `间隙锁`，导致新insert语句无法执行成功，因为新id肯定在间隙锁内。


> 行锁和间隙锁放在一起组成next-key lock, _左开右闭_ 区间
## 间隙锁会带来什么负面影响
间隙锁的引入，可能会导致同样的语句锁住更大的范围，影响并发度。

## 可重复读隔离级别下会出现幻读吗?
使用不当(事务中快照读和当前图切换)，会出现幻读。一直使用当前读，不会出现幻读，
因为间隙锁的存在。

# 锁

## 加锁规则
- 加锁的基本单位是 `next-key lock`(左开右闭)。
- 查找过程中`访问到的对象`(索引)才加锁。
- `唯一索引上的等值查询`，在找到目标记录时，`next-key lock`可以退化为 `行锁`。
- `普通索引上的等值查询`，在找到最后一个不满足条件的记录时，`next-key lock`退化为 `间隙锁`。
- [mysql 45讲加锁规则](https://time.geekbang.org/column/article/75659)

# 命令

- 查看死锁日志 `show engine innodb status`;
