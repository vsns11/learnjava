package ca.siva.ds.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//3 and no women and men at the sametime
public class UniSexBathroomProblem {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    int currCount = 0, currFemale = 0, currMale = 0, limit = 3;

    void maleUseBathroom(String name) throws InterruptedException {
        lock.lock();
        while (currFemale != 0 || currCount == limit) {
            this.wait();
        }
        currMale++;
        currCount++;
        notify();
        lock.unlock();
    }

    void femaleUseBathroom(String name) throws InterruptedException {
        lock.lock();
        while (currMale != 0 || currCount == limit) {
            this.wait();
        }
        currFemale++;
        currCount++;
        notify();
        lock.unlock();

    }
}
