package ca.siva.ds.thread;


/**
 * Producer consumer problem:
 * Problem statement:
 * Producer must be blocked, when the size reaches the maximum capacity,
 * same as well for the consumer when there's no element, it should be blocked. once a new element is added to the queue, it should notify the blocking consumer.
 */
public class ProducerConsumerProblemMonitor {
    public static void main(String args[]) throws Exception {
        final BlockingQueue<Integer> q = new BlockingQueue<Integer>(5);

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    q.enqueue(new Integer(i));
                    System.out.println("enqueued " + i);
                }
            } catch (InterruptedException ie) {

            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 25; i++) {
                    System.out.println("Thread 2 dequeued: " + q.dequeue());
                }
            } catch (InterruptedException ie) {

            }
        });

        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < 25; i++) {
                    System.out.println("Thread 3 dequeued: " + q.dequeue());
                }
            } catch (InterruptedException ie) {

            }
        });

        t1.start();
        Thread.sleep(4000);
        t2.start();
        t2.join();

        t3.start();

        t1.join();
        t3.join();
    }
}

// The blocking queue class
class BlockingQueue<T> {

    T[] array;
    Object lock = new Object();
    int size = 0;
    int capacity;
    int head = 0;
    int tail = 0;

    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        // The casting results in a warning
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {

        synchronized (lock) {

            while (size == capacity) {
                lock.wait();
            }

            if (tail == capacity) {
                tail = 0;
            }

            array[tail] = item;
            size++;
            tail++;
            // notifyAll involves any of the waiting thread(including consumer) will get a chance, else it may cause deadlocks.
            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {

        T item = null;
        synchronized (lock) {

            while (size == 0) {
                lock.wait();
            }

            if (head == capacity) {
                head = 0;
            }

            item = array[head];
            array[head] = null;
            head++;
            size--;

            lock.notifyAll();
        }

        return item;
    }
}