package com.study.thread.example1.sleep;

/**
 * @Author: w
 * @Date: 2021/3/27 11:12
 *
 * sleep得应用：防止CPU占用100%
 * 在没有利用cpu来计算时，不要让while(true)空转浪费cpu，这时可以使用yield或sleep来让出cpu的使用权给其他程序
 * 也可以用wait或条件变量达到类似的效果；不同的是后两种都需要加锁，并且需要相应的唤醒操作，一般适用于要进行同步的场景；sleep适用于无需锁同步的场景
 */
public class SleepApply {

    public static void main(String[] args) {
        applySleep();
    }

    // 使用sleep实现
    private static void applySleep() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
