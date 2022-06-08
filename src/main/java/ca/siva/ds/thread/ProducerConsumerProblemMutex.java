package ca.siva.ds.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerProblemMutex {
    public static void main(String args[]) throws InterruptedException {
        final BlockingQueueWithMutex<Integer> q = new BlockingQueueWithMutex<Integer>(5);

        Thread producer1 = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 1;
                    while (true) {
                        q.enqueue(i);
                        System.out.println("Producer thread 1 enqueued " + i);
                        i++;
                    }
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread producer2 = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 5000;
                    while (true) {
                        q.enqueue(i);
                        System.out.println("Producer thread 2 enqueued " + i);
                        i++;
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread producer3 = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 100000;
                    while (true) {
                        q.enqueue(i);
                        System.out.println("Producer thread 3 enqueued " + i);
                        i++;
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread consumer1 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        System.out.println("Consumer thread 1 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread consumer2 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        System.out.println("Consumer thread 2 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread consumer3 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        System.out.println("Consumer thread 3 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        producer1.setDaemon(true);
        producer2.setDaemon(true);
        producer3.setDaemon(true);
        consumer1.setDaemon(true);
        consumer2.setDaemon(true);
        consumer3.setDaemon(true);

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();

        Thread.sleep(1000);
    }
}


class BlockingQueueWithMutex<T> {
    T[] array;
    Lock lock = new ReentrantLock();
    int size = 0;
    int capacity;
    int head = 0;
    int tail = 0;

    @SuppressWarnings("unchecked")
    public BlockingQueueWithMutex(int capacity) {
        // The casting results in a warning
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public T dequeue() throws InterruptedException {

        T item = null;

        lock.lock();
        while (size == 0) {
            lock.unlock();
            lock.lock();
        }

        if (head == capacity) {
            head = 0;
        }

        item = array[head];
        array[head] = null;
        head++;
        size--;

        lock.unlock();
        return item;
    }

    public void enqueue(T item) throws InterruptedException {

        lock.lock();
        while (size == capacity) {
            // Release the mutex to give other threads
            lock.unlock();
            // Reacquire the mutex before checking the
            // condition
            lock.lock();
        }

        if (tail == capacity) {
            tail = 0;
        }

        array[tail] = item;
        size++;
        tail++;
        lock.unlock();
    }
}
