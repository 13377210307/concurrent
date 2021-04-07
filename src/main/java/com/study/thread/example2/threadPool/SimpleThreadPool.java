package com.study.thread.example2.threadPool;

import java.util.LinkedList;

/**
 * @Author: w
 * @Date: 2021/4/6 22:25
 */
public class SimpleThreadPool {

    private final int size;

    private final static int DEFAULT_SIZE = 10;

    private static volatile int seg = 0;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    // 任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    private void init() {

    }

    // 创建任务
    private void createTask() {
        
    }

    // 线程状态
    private enum TaskState {
        FREE, RUNNING,BLOCKED,DEAD
    }

    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group,String name) {
            super(group,name);
        }

        // 获取状态方法
        public TaskState getTaskState() {
            return this.taskState;
        }

        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            // 设置成阻塞状态
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }

                    runnable = TASK_QUEUE.removeFirst();
                    if (runnable != null) {
                        // 设置成运行状态
                        taskState = TaskState.RUNNING;
                        runnable.run();
                        // 执行完变成空闲状态
                        taskState = TaskState.FREE;
                    }
                }
            }
        }



        // 线程结束方法
        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }
}
