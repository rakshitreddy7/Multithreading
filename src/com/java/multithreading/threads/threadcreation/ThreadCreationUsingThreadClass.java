package com.java.multithreading.threads.threadcreation;

class RunnerUsingThread extends Thread {
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

public class ThreadCreationUsingThreadClass {
    public static void main(String[] args) {
        /*
            1. We need to make use of start() & not run() so that it uses a new thread to work with or else it uses main thread.
            2. Try using run() for both runner1 & runner2 instances to learn the difference.
        */
        RunnerUsingThread runnerUsingThread1 = new RunnerUsingThread();
        runnerUsingThread1.start();

        RunnerUsingThread runnerUsingThread2 = new RunnerUsingThread();
        runnerUsingThread2.start();
    }
}
