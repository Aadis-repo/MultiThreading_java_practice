package org.example.Executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Cf {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(
                ()-> {
                    try {
                        Thread.sleep(5000);
                        System.out.println("Process done, printing the result");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "ok";
                }
        );
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(
                ()-> {
                    try {
                        Thread.sleep(5000);
                        System.out.println("Process done, printing the result");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "ok";
                }
        );
        CompletableFuture future = CompletableFuture.allOf(task1,task2);
//        String s =  completableFuture.getNow("Noooooooo");
//        System.out.println(s);
//        future.get();
        future.join();
        System.out.println("Main is done");
    }
}
