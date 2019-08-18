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