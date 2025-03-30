package org.example.Lock;

public class BankMain {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                account.withdraw(50);
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}
