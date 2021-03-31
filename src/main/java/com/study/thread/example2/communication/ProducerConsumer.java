package com.study.thread.example2.communication;

/**
 * @Author: w
 * @Date: 2021/3/31 21:56
 *
 * 生产者消费者模型
 */
public class ProducerConsumer {

    private static Integer count = 0;

    private static final Object LOCK = new Object();

    // 生产者
    private static void produce() {
        synchronized (LOCK) {
            while (count > 0) {
                // 说明已经生产了，线程暂停
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 说明无产品，需要生产
            LOCK.notify();
            count ++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产者生产...");
        }
    }

    // 消费者
    private static void consume() {
        synchronized (LOCK) {
            while (count > 0) {
                // 有产品，需要消费
                LOCK.notify();
                count --;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费...");
            }
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 生产者生产
        new Thread(() -> {
            while (true) {
                produce();
            }
        },"生产者").start();

        new Thread(() -> {
            while (true) {
                consume();
            }
        },"消费者").start();
    }
}
