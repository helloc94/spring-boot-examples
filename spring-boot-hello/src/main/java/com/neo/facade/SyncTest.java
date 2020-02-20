package com.neo.facade;

import java.util.concurrent.atomic.AtomicInteger;

public class SyncTest implements Runnable {
    //共享资源变量
    AtomicInteger count = new AtomicInteger(0);
    private static LimitQueue<String> queue = new LimitQueue<>(10);

    @Override
    public synchronized void run() {
        while (true) {
            if (queue.size() >= 10) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {

//                    count.getAndIncrement();
                String finalJ = "" + count;
                System.out.print(Thread.currentThread().getName() + "：生产 -- " + finalJ + " --> ");
                queue.offer(finalJ);
                queue.printQueue();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest1 = new SyncTest();
        SyncTest syncTest2 = new SyncTest();
        Thread thread1 = new Thread(syncTest1, "thread1");
        Thread thread2 = new Thread(syncTest2, "thread2");
        thread1.start();
        thread2.start();
    }
}