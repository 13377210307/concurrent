package com.study.thread.example1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: w
 * @Date: 2021/3/25 16:20
 *
 * 创建线程的方式
 *
 * 1：继承Thread类，重写run方法
 *
 * 2：实现runnable接口，重写run方法
 *
 * 3：配合futureTask
 */
@Slf4j(topic = "c.createThread")
public class CreateThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //createByThread();
        //createByRunnable();
        //createByRunnableLambda();
        createByFutureTask();
        log.debug("running...");
    }

    // 使用thread方式
    private static void createByThread() {
        // 采用匿名内部类方式创建线程
        Thread thread = new Thread() {
            // 重写run方法
            @Override
            public void run() {
                log.debug("running....");
            }
        };
        // 为线程设置名称
        thread.setName("thread线程1");
        thread.start();
    }

    // 使用runnable方式
    private static void createByRunnable() {
        // 采用匿名内部类
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                log.debug("running....");
            }
        };
        new Thread(runnable,"runnable线程1").start();
    }

    // 采用lambda方式简化
    private static void createByRunnableLambda() {
        //Runnable runnable = () -> { log.debug("running....");};
        new Thread(() -> { log.debug("running....");},"runnableLambda线程1").start();
    }

    // 配合futureTask
    private static void createByFutureTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running....");
                Thread.sleep(2000);
                return 100;
            }
        });

        new Thread(task,"futureTask线程1").start();
        log.debug("{}",task.get());
    }
}
