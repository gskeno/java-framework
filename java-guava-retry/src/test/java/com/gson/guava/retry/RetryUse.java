package com.gson.guava.retry;

import com.github.rholder.retry.*;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class RetryUse {
    // 需要重试执行的操作
    private Boolean testCode() {
        System.out.println(LocalTime.now());

        // 强制抛出异常，触发重试
        if (true) {
            throw new RuntimeException("手动测试抛出异常");
        } else {
            return false;
        }
    }

    private int testDigit() {
        System.out.println(LocalTime.now());

        int next = new Random().nextInt(100);
        if (next > 50) {
            throw new RuntimeException("next > 50");
        }

        return next;
    }

    @Test
    public void testFirstRetryer() throws ExecutionException, RetryException, InterruptedException {
        // 创建一个重试器，重试器执行的方法，返回值为Boolean类型
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                // 出现异常时，会重试
                .retryIfException()
                // 失败后，隔2秒后重试
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(10, TimeUnit.SECONDS))
                // 重试3次后，仍未成功，就不再重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        // 使用重试器，执行具体逻辑
        Boolean res = retryer.call(() -> {
            return testCode();
        });

        Retryer<Integer> retryer1 = RetryerBuilder.<Integer>newBuilder()
                // 出现异常时，会重试
                .retryIfException()
                // 失败后，隔2秒后重试
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(10, TimeUnit.SECONDS))
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                // 重试3次后，仍未成功，就不再重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        // 使用重试器，执行具体逻辑
        Integer call = retryer1.call(() -> {
            return testDigit();
        });
    }

    @Test
    public void test() {

    }
}
