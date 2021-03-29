package com.study.thread.example1.sleep;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author: w
 * @Date: 2021/3/26 9:52
 * <p>
 * 线程休眠案例
 * 1：调用sleep会让当前线程从runnable进入timeWaiting状态
 * 2：其他线程可以使用interrupt方法打断正在休眠的线程，此时sleep方法会抛出interruptedException
 * 3：休眠结束后的线程未必会立刻得到执行
 * 4：建议用TimeUnit的sleep代替Thread的sleep来获得更好得可读性
 */
@Slf4j(topic = "c.sleepExample")
public class SleepExample {

    public static void main(String[] args) {

        // 线程休眠
        //Thread thread = threadSleep();

        // 线程打断
        //Thread thread = sleepInterrupt();

        Thread thread = sleepByTimeUnit();

        // 让主线程休眠500毫秒再打印，因为线程1可能还没分配到时间片
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打断线程1
        //thread.interrupt();

        log.debug("线程1的状态为：{}",thread.getState());
    }

    // 线程休眠
    private static Thread threadSleep() {
        // 创建线程
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1");
        thread.start();
        return thread;
    }

    // 休眠打断
    private static Thread sleepInterrupt() {
        // 创建线程
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.debug("我被唤醒了...");
                e.printStackTrace();
            }
        }, "线程1");
        thread.start();
        return thread;
    }

    // 采用timeUnit进行线程休眠
    private static Thread sleepByTimeUnit() {
        // 创建线程
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1");
        thread.start();
        return thread;
    }


}
