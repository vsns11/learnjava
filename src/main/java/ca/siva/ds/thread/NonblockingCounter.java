package ca.siva.ds.thread;

public class NonblockingCounter {
    private SimulatedCAS count = new SimulatedCAS(0);

    public long get() {
        return count.getValue();
    }

    public void increment() {

        long currentCount;

        // The loop exits when the return value from CAS is equal to our
        // expectedValue. Otherwise we keep looping until we are successful
        do {
            currentCount = count.getValue();
        } while (currentCount != count.compareAndSwap(currentCount, currentCount + 1));

    }
}