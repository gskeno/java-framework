- 1 一种map转化为另一种map
```java
Map<Integer, Integer> intMap = new HashMap<>();
intMap.put(1, 1);
intMap.put(2, 2);
intMap.put(3, 3);

Map<String, String> collect = intMap.entrySet().stream().map(entry ->
        new HashMap.SimpleEntry<>(
                entry.getKey() + 1 + "",
                entry.getValue() + 1 + "")).
        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

```

- 2 findFirst().orElse()
```java
// 获取大于5的第一个数，拿到了就返回；如果没有，返回0
Integer integer = list.stream().filter(o -> o>5).findFirst().orElse(0);
```

- 3 Optional.ofNullable().orElse()
```java
// 如果list为空，返回一个空集合
List<String> list1 = Optional.ofNullable(list).orElse(new ArrayList<>());
```