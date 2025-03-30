package org.example.ThreadCommunication;


class SharedResource {
    int data;
    boolean hasValue;

    public synchronized void produce(int value){
        if(hasValue){
            try {
                wait();
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasValue = true;
        System.out.println("produced data: "+value);
        notify();
    }

    public synchronized int consume(){
        if (!hasValue){
            try {
                wait();
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("consuming data: "+data);
        hasValue = false;
        notify();
        return data;
    }
}

class Producer implements Runnable{

    public Producer(SharedResource resource){
        this.resource = resource;
    }
    private SharedResource resource;
    @Override
    public void run(){
        for (int i=0;i<5;i++){
            resource.produce(i);
        }
    }
}

class Consumer implements Runnable{

    private SharedResource resource;

    public Consumer(SharedResource resource){
        this.resource = resource;
    }
    @Override
    public void run(){
        for (int i=0;i<5;i++){
            int value = resource.consume();
        }
    }
}
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread p = new Thread(new Producer(resource));
        Thread c = new Thread(new Consumer(resource));
        p.start();
        c.start();
    }
}
