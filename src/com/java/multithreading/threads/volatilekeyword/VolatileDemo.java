package com.java.multithreading.threads.volatilekeyword;

import java.util.Scanner;

class Runner extends Thread {

    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello World : " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        /*
            1. It is used to prevent threads from caching variables when they are not changed within that thread scope.
            2. If you want to change that variable value from another thread, make that variable "volatile" or we can use thread synchronization.
            3. Note: when a variable is declared volatile, the value of the variable is read and written directly from the memory.
               Each thread caches the values of variables from the memory. Thus the thread does not have to always refer the memory, and it can simply read the values from the cache. This is gives rise to the problem of visibility.
               This cache may not be consistent with what other variables see. With volatile, we skip the cache and read/write directly in the memory. Thus, changes are visible to all threads.
        */
        Runner runner1 = new Runner();
        runner1.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        runner1.shutdown();
    }
}