package com.java.multithreading.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

    private final int id;
    private final CountDownLatch latch;

    public Processor(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Process " + id + " started by " + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Process " + id + " completed by " + Thread.currentThread().getName());
        latch.countDown();
    }
}

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        /*
          1. CountDownLatch lets one or more threads wait until the latch reaches a count of 0.
          2. There are few classes in java that are thread safe. CountDownLatch is on of them.
          3. We can access this from multiple threads without worrying about thread synchronization.
          4. Note that when using threads from thread pool, they get assigned to another job as soon as they are done without any delay.
             And then only followed by the main thread (assuming count down is complete by this point)
        */

        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i=0; i<5; i++) {
            Processor processor = new Processor(i, latch);
            executorService.submit(processor);
        }

        /*
           Here, await() gets called once the latch countdown is completed.
        */
        latch.await();

        executorService.shutdown();

        System.out.println("Latch count down is completed.. and executed by " + Thread.currentThread().getName());
    }
}
