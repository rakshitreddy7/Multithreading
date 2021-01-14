package com.java.multithreading.threads.multiplelocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedBlocks {

    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();
    private final Random random = new Random();

    private final Object object1 = new Object();
    private final Object object2 = new Object();

    /*
        1. Here, stageOne() & stageTwo() functionalities must be in sync all the time to prevent anomalies.
        2. One way to eradicate this anomalous behaviour is by adding the keyword "synchronized" to both the methods stageOne() & stageTwo().
        3. But by following the above method, only one thread (thread1 or thread2 in our case) can actively work on either of these methods, thereby reducing the performance.
        4. Hence we can make use of the synchronized blocks by surrounding the core logic of these methods within synchronized blocks. We make use of the locks for this purpose. (Here, both thread1 & thread2
           can work on the methods stageOne() & stageTwo() at the same time, of course not on the core logic. Hence efficient).
    */

    private void stageOne() {
        synchronized (object1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }
    }

    private void stageTwo() {
        synchronized (object2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    private void process() {
        for (int i=0; i<1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void worker() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(this::process);
        Thread thread2 = new Thread(this::process);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long end = System.currentTimeMillis();

        System.out.println("Starting...");
        System.out.println("Time taken: " + (end - start));
        System.out.println("list1 size: " +list1.size());
        System.out.println("list2 size: " +list2.size());
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlocks object = new SynchronizedBlocks();
        object.worker();
    }
}
