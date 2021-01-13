package com.java.multithreading.threads.threadsynchronization;

public class SynchronizedDemo {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    private void doWork() {
        Thread thread1 = new Thread(() -> {
            for (int i=0; i<10000; i++) {
                increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i=0; i<10000; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }

    public static void main(String[] args) {
         /*
            1. Every object in java has an intrensic lock or mutex.
            2. If you call a synchronized method you have to acquire a lock and only 1 thread can acquire a lock at a time.
            3. If another thread tries to acquire it, it has to wait until the 1st one releases (meaning, until method executes. In our case this is increment()).
            4. All this happens in the background.
            5. No need of using volatile here. (If you running something in sync block, sync will take care of it since its state is visible to all the threads).
        */

        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.doWork();
    }
}
