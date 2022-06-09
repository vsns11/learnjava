package ca.siva.ds.thread;

import java.util.HashSet;
import java.util.Set;


public class RateLimiterApproach2Factory {


    public RateLimiterApproach2Factory() {

    }
    public static class RateLimiterApproach2 {
        public long maxTokenLimit;
        public long currentlyAvailableTokens;

        public RateLimiterApproach2(int maxTokenLimit) {
            this.maxTokenLimit = maxTokenLimit;
        }

        void initialize() {
            // Never start a thread in a constructor
            Thread dt = new Thread(() -> {
                try {
                    daemonThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            dt.setDaemon(true);
            dt.start();
        }

        private void daemonThread() throws InterruptedException {
            while (true) {
                synchronized (this) {
                    currentlyAvailableTokens++;
                    if (currentlyAvailableTokens > maxTokenLimit)
                        currentlyAvailableTokens = maxTokenLimit;
                    this.notify();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    // swallow exception
                }
            }

        }

        public void getToken() throws InterruptedException {
            synchronized (this) {
                while (currentlyAvailableTokens == 0) {
                    this.wait();
                }

                currentlyAvailableTokens--;
                System.out.println(
                        "Granting " + Thread.currentThread().getName() + " token at " + System.currentTimeMillis() / 1000);

            }
        }

    }

    public static RateLimiterApproach2 makeRateLimiterFilter(int capacity) {
        RateLimiterApproach2 rl = new RateLimiterApproach2(capacity);
        rl.initialize();
        return rl;
    }


    public static void main(String args[]) throws InterruptedException {
        Set<Thread> allThreads = new HashSet<Thread>();
        RateLimiterApproach2 tokenBucketFilter = RateLimiterApproach2Factory.makeRateLimiterFilter(1);

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {

                public void run() {
                    try {
                        tokenBucketFilter.getToken();
                    } catch (InterruptedException ie) {
                        System.out.println("We have a problem");
                    }
                }
            });
            thread.setName("Thread_" + (i + 1));
            allThreads.add(thread);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }

    }

}





