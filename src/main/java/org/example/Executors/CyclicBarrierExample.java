package org.example.Executors;

import java.util.concurrent.*;

public class CyclicBarrierExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyclicBarrier barrier = new CyclicBarrier(3,()-> System.out.println("all task done"));
        Future future1 = executorService.submit(new Tasker(barrier));
        Future future2 = executorService.submit(new Tasker(barrier));
        Future future3 = executorService.submit(new Tasker(barrier));
        executorService.shutdown();
        System.out.println("Main is done");

    }
}

class Tasker implements Callable{

    public Tasker(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    CyclicBarrier barrier;

    @Override
    public Object call() throws Exception{
        System.out.println(Thread.currentThread().getName()+ " task is done, going to barrier");
        Thread.sleep(1000);
        System.out.println("waiting at barrier");
        barrier.await();
        System.out.println("wait complete");
      return 1;
    };
}