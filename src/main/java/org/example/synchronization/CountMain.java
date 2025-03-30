package org.example.synchronization;

public class CountMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());
    }
}
// RACE CONDITION
// if the threads work on a shared resource and their relative timings
// are causing an uncertain result then this condition is called as race
// condition

// using synchronized, we have achieved state of mutual exclusion
// it ensures that multiple threads simultaneously do not access the shared resource

// locking
// intrinsic: auto locks which are activated when we use synchronized keywords
// Extrinsic: user created locks using the lock class, basically you yourself will
// write the rules of how lock unlock will work



