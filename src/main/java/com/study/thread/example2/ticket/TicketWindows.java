package com.study.thread.example2.ticket;

import java.util.concurrent.TimeUnit;

/**
 * @Author: w
 * @Date: 2021/3/28 18:20
 * <p>
 * 窗口卖票：实现多个窗口卖100张票
 */
public class TicketWindows {

    public static void main(String[] args) {
        Windows windows1 = new Windows("一号窗口");
        Windows windows2 = new Windows("二号窗口");
        Windows windows3 = new Windows("三号窗口");
        windows1.start();
        windows2.start();
        windows3.start();

        /*Windows1 windows1 = new Windows1();
        Thread thread1 = new Thread(windows1);
        Thread thread2 = new Thread(windows1);
        Thread thread3 = new Thread(windows1);
        thread1.setName("窗口一");
        thread2.setName("窗口二");
        thread3.setName("窗口三");
        thread1.start();
        thread2.start();
        thread3.start();*/
    }
}

// 采用继承Thread方式：不推荐，不灵活
class Windows extends Thread {

    private static Integer count = 100;

    private String name;

    public Windows(String name) {
        this.name = name;
    }

    private static Object object = new Object();

    @Override
    public void run() {
        while (count > 0) {
            synchronized (object) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "卖出了" + count + "号票");
                count--;
            }
        }
    }
}

// 采用实现runnable接口方式
class Windows1 implements Runnable {

    private Integer count = 100;

    @Override
    public void run() {
        while (count > 1) {
            synchronized (this) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出了" + count + "号票");
                count--;
            }
        }
    }
}

