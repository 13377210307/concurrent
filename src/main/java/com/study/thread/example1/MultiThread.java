package com.study.thread.example1;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: w
 * @Date: 2021/3/25 17:08
 *
 * 演示两个线程交替执行
 */
@Slf4j(topic = "c.multiThread")
public class MultiThread {

    public static void main(String[] args) {
        multiMethod();
    }

    private static void multiMethod() {

        new Thread(() -> {
            while (true) {
                log.debug("running...");
            }
        },"线程1").start();

        new Thread(() -> {
            while (true) {
                log.debug("running...");
            }
        },"线程2").start();
    }
}
