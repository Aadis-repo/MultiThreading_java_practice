package org.example.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    private final Lock lock = new ReentrantLock();

//    public synchronized void withdraw(int amt){
//        System.out.println(Thread.currentThread().getName()+" attempting to withdraw "+ amt);
//        if (balance >=amt){
//            System.out.println(Thread.currentThread().getName()+" proceeding with withdrawal");
//            try {
//                Thread.sleep(3000);
//            } catch (Exception e){
//                System.out.println("Exception occurred while processing transaction");
//            }
//            balance -= amt;
//            System.out.println("remaing balance: "+balance);
//        } else {
//            System.out.println("insufficient balance");
//        }
//    }

    public void withdraw(int amt){
        System.out.println(Thread.currentThread().getName()+" attempting to withdraw amt "+ amt);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if (balance >= amt){
                    System.out.println(Thread.currentThread().getName()+" processing withdrawal");
                    balance -= amt;
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e){
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                    System.out.println("remaining balance: "+balance);
                } else {
                    System.out.println("Insufficient balance");
                }
            } else {
                System.out.println("lock is not available, try again later");
            }
        } catch (Exception e){
            Thread.currentThread().interrupt();
        }
    }
}

// There is another lock called lock.interruptibly(), this lock is used
// when in main method some code is written that if you are waiting for more
// than 10 seconds then interrupt the current thread. But lock should allow us
// to interrupt , right? Therefore we use this lock which can be interrupted so that
// other threads can acquire this lock