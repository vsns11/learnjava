package ca.siva.ds.thread;

import java.util.concurrent.Semaphore;


class SemaphoreMissedSignalExample {

    public static void main(String args[]) throws InterruptedException {
        FixedMissedSignalExample.example();
    }
}

class FixedMissedSignalExample {

    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread signaller = new Thread(new Runnable() {

            public void run() {
                try {
                    Thread.currentThread().sleep(1000);
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Sent signal");
                semaphore.release();
            }
        });

        Thread waiter = new Thread(new Runnable() {

            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("Received signal");
                    semaphore.release();
                } catch (InterruptedException ie) {
                    // handle interruption
                }
            }
        });

        signaller.start();

//        Thread.sleep(5000);
        waiter.start();
        signaller.join();
        waiter.join();

        System.out.println("Program Exiting.");
    }
}