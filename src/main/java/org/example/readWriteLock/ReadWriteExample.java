package org.example.readWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteExample {
    private int count = 0;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try {
            count++;
        } catch (Exception e){
            Thread.interrupted();
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount(){
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteExample example = new ReadWriteExample();
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    example.increment();
                    System.out.println(Thread.currentThread().getName()+ " incremented");
                }
            }
        };
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++)
                    System.out.println(Thread.currentThread().getName()+ " read count:"+example.getCount());
            }
        };

        Thread t1 = new Thread(writeTask, "Thread 1");
        Thread t2 = new Thread(readTask, "Thread 2");
        Thread t3 = new Thread(readTask, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
        try {
            t2.join();
            t3.join();
            t1.join();
        } catch (Exception e){
            Thread.interrupted();
        }
    }

}
