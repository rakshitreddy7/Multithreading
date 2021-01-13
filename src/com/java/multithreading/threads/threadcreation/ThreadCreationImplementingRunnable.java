package com.java.multithreading.threads.threadcreation;

class RunnerUsingRunnable implements Runnable {

    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("Hello World : " + i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadCreationImplementingRunnable {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnerUsingRunnable());
        thread1.start();

        Thread thread2 = new Thread(new RunnerUsingRunnable());
        thread2.start();
    }
}
