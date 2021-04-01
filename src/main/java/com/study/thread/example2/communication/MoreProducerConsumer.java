package com.study.thread.example2.communication;

import java.util.stream.Stream;

/**
 * @Author: w
 * @Date: 2021/4/1 22:16
 * <p>
 * 多生产者消费者情况
 */
public class MoreProducerConsumer {

    private static final Object LOCK = new Object();

    private static int count = 0;

    public static void main(String[] args) {
        Stream.of("p1","p2").forEach( n-> {
            new Thread(() -> {
                while (true) {
                    produce();
                }
            }).start();
        });

        Stream.of("c1","c2").forEach( n-> {
            new Thread(() -> {
                while (true) {
                    consume();
                }
            }).start();
        });
    }

    private static void produce() {
        synchronized (LOCK) {
            while (count > 0) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOCK.notifyAll();
            count ++;
            System.out.println("生产者生产...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consume() {
        synchronized (LOCK) {
            while (count > 0) {
                LOCK.notifyAll();
                count --;
                System.out.println("消费者消费...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
