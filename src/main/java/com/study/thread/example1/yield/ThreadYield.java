package com.study.thread.example1.yield;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: w
 * @Date: 2021/3/27 10:33
 * 线程暂停
 * 1：调用yield会让当前线程从running状态进入runnable就绪状态，然后调度执行其他线程
 *
 * 线程优先级
 * 1：线程优先级会提示调度器有限调度该线程，但他仅仅是个提示，调度器可以忽略它
 * 2：如果cpu比较忙，那么优先级高得线程会获得更多得时间片，但cpu比较闲时，优先级几乎没什么作用
 *
 */
@Slf4j(topic = "c.threadYield")
public class ThreadYield {

    public static void main(String[] args) {
        /*Thread thread = yieldThread();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("线程状态为：{}",thread.getState());*/
        threadPriority();
    }

    // 线程暂停
    private static Thread yieldThread() {
        Thread thread = new Thread(() -> {
            log.debug("running...");
        }, "线程1");
        thread.start();
        Thread.yield();
        return thread;
    }

    // 线程优先级
    private static void threadPriority() {
        Runnable task1 = () -> {
            for (;;) {
                System.out.println("线程1运行...");
            }
        };

        Runnable task2 = () -> {
            for (;;) {
                System.out.println("线程2运行...");
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();
    }
}
