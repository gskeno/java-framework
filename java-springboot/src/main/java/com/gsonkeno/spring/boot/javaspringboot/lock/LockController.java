package com.gsonkeno.spring.boot.javaspringboot.lock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ruchen
 * @date 2022/8/1
 */
@RestController
@RequestMapping("/lock/control")
public class LockController {
    private ReentrantLock lock = new ReentrantLock(true);

    private volatile boolean block = true;


    @RequestMapping(value = "get")
    @ResponseBody
    public String get() {
        lock.lock();
        try {
            while (block) {
                Long time = 2 * 1000L;
                Thread.sleep(time);
            }
            System.out.println("not block");
            return "get";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @RequestMapping(value = "open")
    @ResponseBody
    public String open() {
        block = false;
        return "block=" + block;
    }

    @RequestMapping(value = "close")
    @ResponseBody
    public String close() {
        block = true;
        return "block=" + block;
    }
}
