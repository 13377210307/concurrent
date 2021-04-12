package com.study.thread.example3.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: w
 * @Date: 2021/4/12 22:26
 * 生产者消费者模式
 */
public class ProduceAndConsumer {

    public static final Object LOCK = new Object();

    public static int value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                producer();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                consumer();
            }
        }).start();
    }

    // 生产方法
    private static void producer() {
       synchronized (LOCK) {
           while (value > 0) {
               // 值大于0，停止生产
               try {
                   LOCK.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           // 生产值
           value++;
           System.out.println("生产者生产...");
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           // 唤醒消费者
           LOCK.notify();
       }
    }

    // 消费方法
    private static void consumer()  {
        synchronized (LOCK) {
            while (value > 0) {
                // 消费值
                value --;
                System.out.println("消费者消费...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 唤醒生产者
                LOCK.notify();
            }
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
