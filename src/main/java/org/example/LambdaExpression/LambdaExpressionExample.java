package org.example.LambdaExpression;

public class LambdaExpressionExample {
    public static void main(String[] args) {
        // you already know this
        // eg1
        Runnable runnable = () -> System.out.println("lamda expression in runnable");
        Thread t1 = new Thread(() -> System.out.println("Lambda expression in Thread instead of runnable object"));
    }
}
