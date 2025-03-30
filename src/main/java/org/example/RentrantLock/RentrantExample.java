package org.example.RentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        try {
            System.out.println("outer method");
            innerMethod();
        } catch (Exception e){
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    // inner method will acquire the lock again as same thread is
    // acquiring the lock. And Each lock call must be paired with an
    // unlock call as a count is maintained when it is acquired by the same
    // thread. In this example, count will be one for outer method, then 2
    // when goes to inner method, then again 1 when inner unlock, then 0
    // when outer unlock
    public void innerMethod(){
        lock.lock();
        try {
            System.out.println("inner method");
        } catch (Exception e){
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        RentrantExample example = new RentrantExample();
        example.outerMethod();
    }
}
