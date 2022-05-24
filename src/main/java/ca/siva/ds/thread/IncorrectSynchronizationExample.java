package ca.siva.ds.thread;

/**
 * A classic example of IllegalMonitorState exception.
 * When the synchronized is applied on a boolean reference variable. During the thread is sleep,
 * 2nd thread will change its value, ends up changing the reference variable,
 * so wait is called on a new reference variable that is not synchronized, ends up throwing this exception.
 */
public class IncorrectSynchronizationExample {
    Boolean b = new Boolean(false);

    public void execute() {
        Thread t1 = new Thread(() -> {
            synchronized (b) {
                System.out.println("Entered the first thread synchronized block");
                try {
                    Thread.sleep(5000);
                    System.out.println("Slept for 5ms");
                    b.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Entered the second thread synchronized block");
            b = false;
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        IncorrectSynchronizationExample x = new IncorrectSynchronizationExample();
        x.execute();
    }
}
