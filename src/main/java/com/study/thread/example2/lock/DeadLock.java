package com.study.thread.example2.lock;

/**
 * @Author: w
 * @Date: 2021/3/30 22:57
 *
 * 死锁：两个线程竞争某一个资源不释放导致死锁
 */
public class DeadLock {

    private static final Object LOCK1 = new Object();

    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
         new Thread(() -> {
            synchronized (LOCK1) {
                try {
                    System.out.println("获取锁1...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK2) {
                    System.out.println("获取锁2...");
                }
            }
        }).start();


        new Thread(() -> {
            synchronized (LOCK2) {
                try {
                    System.out.println("获取锁2...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK1) {
                    System.out.println("获取锁1...");
                }
            }
        }).start();
    }



}

