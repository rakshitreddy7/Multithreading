package com.java.multithreading.threads.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting-" + id + ", " + Thread.currentThread().getName());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stopping-" + id + " " + Thread.currentThread().getName());
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        /*
           1. Now, instead of using Thread.start(), we will use ExecutorService to make use of threads in a thread pool.
           2. As soon as one thread is done doing a task & is idle, it picks up an another task.
           3. Main advantage here is that we can recycle threads from the thread pool.
        */

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i=0; i<5; i++) {
            Processor processor = new Processor(i);
            executorService.submit(processor);
        }

        /*
           Here executorService.shutdown() will be executed only after all the tasks are submitted.
        */
        executorService.shutdown();

        System.out.println("Tasks are submitted...");

         /*
           Here we use awaitTermination() to wait for all the tasks to be completed.
        */

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All things are completed...");
    }
}
