package ca.siva.ds.thread;

public class SemaphoreImplementation {
    int maxPermit;
    int usedPermit;

    public static void main(String[] args) throws InterruptedException {
        final SemaphoreImplementation cs = new SemaphoreImplementation(1);

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cs.acquire();
                    System.out.println(Thread.currentThread().getName() + " Ping " + i);

                }
            } catch (InterruptedException ie) {

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    cs.release();
                    System.out.println(Thread.currentThread().getName() + " Pong " + i);
                } catch (InterruptedException ie) {

                }
            }
        });
        t2.setName("Thread #2");
        t1.setName("Thread #1");
        t2.start();
        t1.start();

        t1.join();
        t2.join();
    }

    public SemaphoreImplementation(int maxCount) {
        this.maxPermit = maxCount;
    }

    public synchronized void acquire() throws InterruptedException {
        while (usedPermit == maxPermit) {
            System.out.println(Thread.currentThread().getName() + " is in wait state at acquire");
            this.wait();
        }
        usedPermit++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermit == 0) {
            System.out.println(Thread.currentThread().getName() + " is in wait state at release");
            this.wait();
        }
        usedPermit--;
        notify();
    }

}
