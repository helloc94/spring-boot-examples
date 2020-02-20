package com.neo.facade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ProduceThread implements Runnable{
    private LimitQueue<Integer> queue;
    private static int count;

    ProduceThread(LimitQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            if(queue.size() < 10){
                synchronized (this){
                    count ++;
                    queue.offer(count);
                    System.out.print(Thread.currentThread().getName()+":" + count +" --> ");
                    queue.printQueue();
                }
            }else{
                System.out.println(Thread.currentThread().getName() + "：size为10 等待2s");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerThread implements Runnable{
    private LimitQueue<Integer> queue;

    ConsumerThread(LimitQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            if(queue.size() <= 0){
                System.out.println(Thread.currentThread().getName() + "：size为0 等待5s");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                synchronized (this){
                    System.out.print(Thread.currentThread().getName() + ":" + queue.poll() +" --> ");
                    queue.printQueue();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class HelloFacade {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        LimitQueue<Integer> queue = new LimitQueue<>(10);

        ProduceThread syncThread = new ProduceThread(queue);
        Thread thread1 = new Thread(syncThread, "syncThread1");
        Thread thread2 = new Thread(syncThread, "syncThread2");
        Thread thread3 = new Thread(syncThread, "syncThread3");
        thread1.start();
        thread2.start();
        thread3.start();

        ConsumerThread cusThread = new ConsumerThread(queue);
        Thread cus1 = new Thread(cusThread, "消费者-1");
        Thread cus2 = new Thread(cusThread, "消费者-2");
        cus1.start();
        cus2.start();

    }


}
