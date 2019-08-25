- 1. 使用范型将字符串转化为枚举类
```java
public static  <T extends Enum<T>> T getEnumFormString(Class<T> t, String str){
    T emumT = Enum.valueOf(t, str);
    return emumT;
}
```