package com.study.thread.example2.conCurrentExample;

import java.util.concurrent.TimeUnit;

/**
 * @Author: w
 * @Date: 2021/3/28 11:45
 *
 * 尝试并发执行两个耗时操作
 * 操作一：读写数据库
 * 操作二：读写文件
 */
public class TryConCurrent {

    public static void main(String[] args) {
        concurrentMethod();
    }

    /**
     * 方法同步：未创建多线程，两个方法有先后执行顺序
     */
    private static void synchronizedMethod() {
        readAndWriteDb();
        readWriteFile();
    }

    /**
     * 方法并发：创建多线程，两个方法分贝创建一个线程去执行
     */
    private static void concurrentMethod() {
        Runnable dbTask = TryConCurrent::readAndWriteDb;
        Runnable fileTask = TryConCurrent::readWriteFile;

        new Thread(dbTask, "db线程").start();
        new Thread(fileTask, "file线程").start();
    }


    // 读写数据库
    private static void readAndWriteDb() {
        printMessage("begin read db");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMessage("end write db");
    }

    // 读写文件
    private static void readWriteFile() {
        printMessage("begin read file");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMessage("end write file");
    }

    // 输出语句
    private static void printMessage(String message) {
        System.out.println(message);
    }


}
