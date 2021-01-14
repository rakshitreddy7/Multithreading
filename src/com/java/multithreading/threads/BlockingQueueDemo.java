package com.java.multithreading.threads;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    /*
    * 1. BlockingQueue is a queue which is thread safe provided by java.
    * 2. It has 2 helpful methods put() & take() which patiently waits to perform an operation.
    * */
    private final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    private final Random random = new Random();

    private void producer() throws InterruptedException {
        while(true) {
            blockingQueue.put(random.nextInt(100));
        }
    }

    private void consumer() throws InterruptedException {
        while(true) {
            Thread.sleep(200);
            int val = blockingQueue.take();
            System.out.println("Value taken is: " + val + " Queue size is: " + blockingQueue.size());
        }
    }

    private void worker() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        //System.out.println("Executed by main..");
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo();
        blockingQueueDemo.worker();
    }
}
