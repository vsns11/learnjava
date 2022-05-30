package ca.siva.ds.thread;

import java.util.concurrent.Semaphore;



class CorrectSemaphoreExampleDemonstration {

    public static void main(String args[]) throws InterruptedException {
        CorrectSemaphoreExample.example();
    }
}

public class CorrectSemaphoreExample {

    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(new Runnable() {

            public void run() {

                while (true) {

                    try {
                        semaphore.acquire();
                        try {
                            throw new RuntimeException("");
                        } catch (Exception e) {
                            // handle any program logic exception and exit the function
                            return;
                        } finally {
                            System.out.println("Bad thread releasing semahore.");
                            semaphore.release();
                        }

                    } catch (InterruptedException ie) {
                        // handle thread interruption
                    }
                }
            }
        });

        badThread.start();

        // Wait for the bad thread to go belly-up
        Thread.sleep(1000);

        final Thread goodThread = new Thread(new Runnable() {

            public void run() {
                System.out.println("Good thread patiently waiting to be signalled.");
                try {
                    semaphore.acquire();
                } catch (InterruptedException ie) {
                    // handle thread interruption
                }
            }
        });

        goodThread.start();
        badThread.join();
        goodThread.join();
        System.out.println("Exiting Program");
    }
}
