# 基础工具

- [MoreObjects](https://guava.dev/releases/snapshot/api/docs/com/google/common/base/MoreObjects.html)
- [ComparisonChain](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ComparisonChain.html)


# 集合
- [ImmutableCollection](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableCollection.html)
- [ImmutableList](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableList.html)
- [ImmutableSet](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableSet.html)
- [ImmutableSortedSet](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableSortedSet.html)
- [ImmutableMap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableMap.html)
- [ImmutableSortedMap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableSortedMap.html)
- [ImmutableMultiset](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableMultiset.html)
- [ImmutableSortedMultiset](https://guava.dev/releases/12.0/api/docs/com/google/common/collect/ImmutableSortedMultiset.html)
- [ImmutableMultimap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableMultimap.html)
- [ImmutableListMultimap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableListMultimap.html)
- [ImmutableSetMultimap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableSetMultimap.html)
- [ImmutableBiMap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableBiMap.html)
- [ImmutableClassToInstanceMap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableClassToInstanceMap.html)
- [ImmutableTable](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ImmutableTable.html)

 --------------

- [HashMultiset](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/HashMultiset.html)
- [HashMultimap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/HashMultimap.html)
- [HashBasedTable](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/HashBasedTable.html)
- [ClassToInstanceMap](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/ClassToInstanceMap.html)

 --------------
- [Iterables](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/Iterables.html)
- [PeekingIterator](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/PeekingIterator.html)
- [AbstractIterator](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/AbstractIterator.html)
- [AbstractSequentialIterator](https://guava.dev/releases/snapshot/api/docs/com/google/common/collect/AbstractSequentialIterator.html)

# 反射
- [TypeToken,Invokable,Reflection.newProxy,ClassPath](https://github.com/google/guava/wiki/ReflectionExplained)

# 缓存
- [cache.getIfPresent](src/test/java/com/gson/guava/cache/CacheTest.java)
  <br>缓存中没有关联值，不会加载，返回null(不管执行几次)。
- [cache.get](src/test/java/com/gson/guava/cache/CacheTest.java)
  <br>get方法会声明异常，第一次get如果cache中没有值，会加载，第二次直接返回缓存值。

