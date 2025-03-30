package org.example.Executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FutureExecutorExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<Integer> future = executorService.submit(() -> 42);
//        Future<?> hello = executorService.submit(() -> System.out.println("Hello"));
//        Future<String> fut = executorService.submit(()->System.out.println("hello),"success");
//        System.out.println(hello.get());
//        if (hello.isDone()){
//            System.out.println(" hello future is done");
//        }
        // Note: But isDone Method does not wait for actual execution to complete
        // only future.get() method waits till the execution is completed
//        executorService.shutdown();
        Callable<String> callable = () -> "hello";
        Future<String> future = executorService.submit(callable);
        // Note: if you want to return something then use callable with executor, but
        // if you do not want to return anything then use runnable

        executorService.shutdown();

        // Note: DIff between callable and runnable
        // no return - runnable, return something - callable
        // runnable does not throw exception by itself, we have to wrap a try catch
        // but callable throws exception by itself, see the call and run method side by side

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
//        Future<Integer> future1 = executorService1.submit(() -> 1 + 2);
//        System.out.println(future1.get());
//        executorService1.shutdown();
//        System.out.println(executorService1.isTerminated());
//        System.out.println(executorService1.isShutdown());

        Callable<Integer> callable1 = ()-> {
            System.out.println("Task 1");
            return 1;
        };
        Callable<Integer> callable2 = ()-> {
            System.out.println("Task 2");
            return 2;
        };
        Callable<Integer> callable3 = ()-> {
            System.out.println("Task 3");
            return 3;
        };

        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);
        List<Future<Integer>> futures = executorService1.invokeAll(list);
// Note: invokeAll is blocking in nature, main thread will wait for invokeAll to complete and only
        // then it will go ahead with the main thread
        for (Future<Integer> f: futures){
            System.out.println(f.get());
        }
        executorService1.shutdown();
        // Note: executors methods
        // submit(runnable)
        // submit(callable)
        // submit(runnable, result)
        // shutdown()
        // shutdownNow()
        // awaitTermination(Time, timeunit)
        // isShutDown()
        // isTerminated()
        // invokeAll()
        // invokeAll(collection, timeout) - it throws CancellationException if any task is cancelled or timed out
        // invokeAny() - from the list of callables, it will start all tasks but will return only 1 result

        // Note: Future methods
        // .get() - to get the result
        // .get(timeout) - get or wait for the result for the specified time
        // .isDone() - does not wait, just checks if it is done,cancelled or exception or not
        // .isCancelled() - returns if the task is cancelled
        // .cancel(true) - to cancel the task, if provided false it means if the task started then dont cancel otherwise cancel

    }
}
