package org.example.Executors;

import java.util.concurrent.*;

public class ProblemExecutorService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CountDownLatch latch = new CountDownLatch(3);
        Future<String> future1 = executorService.submit(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                    System.out.println("Task1 done");
                    return "calling task 1";
                }
        );
        Future<String> future2 = executorService.submit(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                    System.out.println("Task2 done");
                    return "calling task 2";
                }
        );
        Future<String> future3 = executorService.submit(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                    System.out.println("Task3 done");
                    return "calling task 3";
                }
        );
        latch.await(1,TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println("Main is done");

    }
}

// Note: CountDown latch
// it removes the necessity to call the .get method to wait for our thread before main
// we use a latch object and specify how many tasks need to be completed.
// in those tasks we specify, latch.countDOwn(), which is basically a count decrement
// once count = 0; it will return to latch.await().
// this is useful when you have a lot of threads

// there is also: latch.await(timeout, unit)
// this method waits for specified time and then the main method runs
// but our thread execution is not stopped and will show the result later

// count down latch is not reusable, once it reaches 0, it cannot be reset to a new value

