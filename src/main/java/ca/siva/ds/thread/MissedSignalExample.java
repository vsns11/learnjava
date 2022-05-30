package ca.siva.ds.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class DemonstrationMissedSignals {

    public static void main(String args[]) throws InterruptedException {
        MissedSignalExample.example();
    }
}

/**
 * This will provide a sequence, that signal was given without awaiting on the thread
 */
public class MissedSignalExample {

    public static void example() throws InterruptedException {

        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread signaller = new Thread(new Runnable() {

            public void run() {
                try {
                    Thread.currentThread().sleep(2000); // comment this to replicate the missing signal scenario
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                condition.signal();
                System.out.println("Sent signal");
                lock.unlock();
            }
        });

        Thread waiter = new Thread(new Runnable() {

            public void run() {

                lock.lock();

                try {
                    condition.await();
                    System.out.println("Received signal");
                } catch (InterruptedException ie) {
                    // handle interruption
                }

                lock.unlock();

            }
        });

        signaller.start();


        waiter.start();

//        signaller.join();
//        waiter.join();

        System.out.println("Program Exiting.");
    }
}