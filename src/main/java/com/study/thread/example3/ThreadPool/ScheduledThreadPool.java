package com.study.thread.example3.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: w
 * @Date: 2021/4/12 22:03
 * 定时任务线程池
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        createScheduledThreadPool2();
    }

    private static void createScheduledThreadPool1() {

        // 创建一个有调度功能的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // 延时2秒执行任务
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行任务");
            }
        },2, TimeUnit.SECONDS);
    }

    private static void createScheduledThreadPool2() {
        // 创建一个有调度功能的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // 延时2秒而且每隔2秒进行执行
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行任务");
            }
        },2, 2, TimeUnit.SECONDS);
    }
}
