package ca.siva.ds.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * The main cons with this approach is, when usage reaches the maximum token limit,
 * the current thread which is near to get a next token, will end up waiting for an extra second. And this is resolved in the second approach.
 *
 */
public class RateLimiterApproach1 {
    public long maxTokenLimit;
    public long currentlyAvailableTokens;
    private long lastRequestTime = System.currentTimeMillis();

    public RateLimiterApproach1(int maxTokenLimit) {
        this.maxTokenLimit = maxTokenLimit;
    }


    public synchronized void getToken() throws InterruptedException {
        currentlyAvailableTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;
        if (currentlyAvailableTokens > maxTokenLimit)
            currentlyAvailableTokens = maxTokenLimit;

        if (currentlyAvailableTokens == 0) {
            Thread.sleep(1000); // this will generate a new token after a second.
        } else {
            currentlyAvailableTokens--;
        }
        lastRequestTime = System.currentTimeMillis();
        System.out.println(
                "Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));

    }

    public static void main(String[] args) throws InterruptedException {
        runTestMaxTokenIs1();
    }

    public static void runTestMaxTokenIs1() throws InterruptedException {

        Set<Thread> allThreads = new HashSet<Thread>();
        final RateLimiterApproach1 tokenBucketFilter = new RateLimiterApproach1(1);

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(() -> {
                try {
                    tokenBucketFilter.getToken();
                } catch (InterruptedException ie) {
                    System.out.println("We have a problem");
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
