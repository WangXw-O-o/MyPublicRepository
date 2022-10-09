package demoTest.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    private ReentrantLock reentrantLock;
    private Condition condition;


    public void testLock() {
        reentrantLock.lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void testCondition() throws InterruptedException {
        condition.await();
    }
}
