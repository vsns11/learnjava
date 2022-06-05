package ca.siva.ds.thread;

public class SimulatedCAS {

    // Let's assume for simplicity our value is a long
    private long value = 0;

    // constructor to initialize the value
    public SimulatedCAS(long initValue) {
        value = initValue;
    }

    synchronized long getValue() {
        return value;
    }

    // The synchronized keyword causes all the steps in this method to execute
    // atomically, which is akin to simulating the compare and swap processor
    // instruction. The behavior of the function is as follows:
    //
    // 1. Return the expectedValue if the CAS instruction completes successfully, i.e.
    //    the newValue is written.
    // 2. Return the current value if the CAS instruction doesn't complete successfully
    //
    // The method is setup such that when expectedValue equals the return value
    // the caller can assume success.
    synchronized long compareAndSwap(long expectedValue, long newValue) {

        if (value == expectedValue) {
            value = newValue;
            return expectedValue;
        }

        // return whatever is the current value
        return value;
    }

    // This method uses the compareAndSwap() method to indicate if the CAS
    // instruction completed successfully or not.
    synchronized boolean compareAndSet(long expectedValue, long newValue) {
        return compareAndSwap(expectedValue, newValue) == expectedValue;
    }
}