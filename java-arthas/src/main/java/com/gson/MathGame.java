package com.gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/alibaba/arthas/blob/master/math-game/src/main/java/demo/MathGame.java
 */
public class MathGame {
    private static Random random = new Random();

    private int illegalArgumentCount = 0;

    public static void main(String[] args) throws InterruptedException {
        MathGame game = new MathGame();
        while (true) {
            game.run();
            TimeUnit.SECONDS.sleep(5);
        }
    }

    public void run() throws InterruptedException {
        try {
            int number = random.nextInt()/10000;
            List<Integer> primeFactors = primeFactors(number);
            print(number, primeFactors);

        } catch (Exception e) {
            System.out.println(String.format("illegalArgumentCount:%3d, ", illegalArgumentCount) + e.getMessage());
        }
    }

    public static void print(int number, List<Integer> primeFactors) {
        StringBuffer sb = new StringBuffer(number + "=");
        for (int factor : primeFactors) {
            sb.append(factor).append('*');
        }
        if (sb.charAt(sb.length() - 1) == '*') {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }

    /**
     * 这里需要注意，如果是在函数结束之后观察入参，其值是方法执行到最后时的结果,
     * 不管入参是基础类型还是封装类型
     * https://arthas.aliyun.com/doc/watch.html#%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E
     * @param number
     * @return
     */
    public List<Integer> primeFactors(int number) {
        if (number < 2) {
            illegalArgumentCount++;
            throw new IllegalArgumentException("number is: " + number + ", need >= 2");
        }

        List<Integer> result = new ArrayList<Integer>();
        int i = 2;
        while (i <= number) {
            if (number % i == 0) {
                result.add(i);
                number = number / i;
                i = 2;
            } else {
                i++;
            }
        }

        return result;
    }
}