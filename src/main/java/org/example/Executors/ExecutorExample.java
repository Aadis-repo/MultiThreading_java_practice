package org.example.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++){
            int finalI = i;
            // submit method returns a Future object
            // it is mostly used to check the returned value and check
            // if the execution completed successfully
            executorService.submit(
                    () -> {
                        long result = factorial(finalI);
                        System.out.println(result);
                    }
            );
        }
//        executorService.shutdownNow();
        executorService.shutdown();
//        executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
        while (!executorService.awaitTermination(50,TimeUnit.MILLISECONDS)){
            System.out.println("Waiting......");
        }
        System.out.println("work finished in "+(System.currentTimeMillis()-startTime));
    }

    public static int factorial(int n){
        try {
            Thread.sleep(1000);
        } catch (Exception e){

        }
        int result =1;
        for (int i=1;i<=n;i++){
            result *=i;
        }
        return result;
    }
}
