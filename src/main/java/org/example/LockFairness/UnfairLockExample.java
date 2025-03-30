package org.example.LockFairness;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnfairLockExample {

    private final Lock lock = new ReentrantLock(true);

    public void accessResource(){
        System.out.println(Thread.currentThread().getName()+" trying to access resource");
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" got the lock");
            Thread.sleep(3000);
        } catch (Exception e){
            Thread.interrupted();
        } finally {
            System.out.println(Thread.currentThread().getName()+" released the lock");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        UnfairLockExample example = new UnfairLockExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");
        t1.start();
        t2.start();
        t3.start();
    }
}
