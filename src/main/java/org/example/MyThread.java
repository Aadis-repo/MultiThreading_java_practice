package org.example;

public class MyThread extends Thread {

    MyThread(String name){
        super(name);
    }

    @Override
    public void run(){
        while (true){
            System.out.println(Thread.currentThread().getName()+ " is running");
        }
    }

    public static void main(String[] args) {
//        MyThread l = new MyThread("High");
//        MyThread m = new MyThread("Medium");
//        MyThread n = new MyThread("Low");
//        l.setPriority(Thread.MAX_PRIORITY);
//        m.setPriority(Thread.NORM_PRIORITY);
//        n.setPriority(Thread.MIN_PRIORITY);
//        l.start();
//        m.start();
//        n.start();
//    MyThread t1 = new MyThread("inter");
//    t1.start();
//    t1.interrupt();
//        MyThread t1 = new MyThread("Thread 1");
//        MyThread t2 = new MyThread("Thread 2");
//        t1.start();
//        t2.start();
        MyThread t1 = new MyThread("Thread 1");
        t1.setDaemon(true);
        MyThread t2 = new MyThread("Thread 2");
        t2.start();
        t1.start();
        System.out.println("Main done");
    }
}


// start run sleep join setPriority interrupt yield setDaemon

// Daemon Threads are those threads which run in the background
// but the main point of these threads is that main thread will
// not wait for these threads to finish execution