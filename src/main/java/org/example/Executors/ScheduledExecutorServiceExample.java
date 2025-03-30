package org.example.Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(
                () -> System.out.println("Task is executed"), 5,5, TimeUnit.SECONDS
        );

        scheduledExecutorService.schedule(
                () -> {
                    System.out.println("initiating shutdown");
                    scheduledExecutorService.shutdown();
                }, 20, TimeUnit.SECONDS
        );
    }
}

// Note: SchedulerMethods
// .schedule(runnable/callable, delay, timeunit)
// .scheduleAtFixedRate(runnable/callable, initial delay, period,timeunit)
// .scheduleAtFixedDelay(runnable/callable, initial delay, delay, timeunit);

// also, scheduledExecutor returns scheduledFuture which has its own methods

// Note: Executors.newCachedThreadPool()
// it is not a fixed size pool and creates threads according to requirement
// used for dynamic tasks where number of required threads are not known
// but carries the risk of creating unlimited threads and overloading cpu
// therefore should be used for quick or short dynamic tasks so that pool can
// handle requirement accordingly
