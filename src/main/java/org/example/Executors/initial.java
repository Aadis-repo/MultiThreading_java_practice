package org.example.Executors;

public class initial {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
//        for (int i=0;i<10;i++){
//            int result = factorial(i);
//            System.out.println(result);
//        }
        Thread[] threads = new Thread[9];
        for (int i=1;i<10;i++){
            int finalI = i;
            threads[i-1] = new Thread(
                    () -> {
                        long result = factorial(finalI);
                        System.out.println(result);
                    }
            );
            threads[i-1].start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e){

            }
        }
        System.out.println("time taken to calculate factorial "+ (System.currentTimeMillis() - start));
    }

    public static int factorial (int n){
        try {
            Thread.sleep(1000);
        } catch (Exception e){

        }
        if(n == 0)
            return 1;
        return n*factorial(n-1);
    }
}
