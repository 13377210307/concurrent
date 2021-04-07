package com.study.thread.example3.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: w
 * @Date: 2021/4/7 22:24
 *
 * 线程池的基本使用
 */
public class SimpleThreadPool {

    public static void main(String[] args) {

        // 创建容量大小为5的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        // 创建18个任务给线程池中的线程执行，这18个任务会存储到线程池的阻塞队列中，线程池中这5个线程从阻塞队列中取任务执行。
        for (int i = 0; i < 20; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"在执行任务");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
