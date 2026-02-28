// This is App.java, Assignment 3 - AbiolaA
package com.mycompany.app;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Fibonacci Application Demo ===");
        int n = 10;
        System.out.println("First " + n + " Fibonacci numbers:");

        int[] sequence = getFibonacciSequence(n);
        for (int num : sequence) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
        System.out.println("Testing Fibonacci checker:");
        int[] testNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 21, 55};
        for (int num : testNumbers) {
            System.out.println(num + " is Fibonacci? " + isFibonacci(num));
        }
    }

    // This is Method 1: The Original recursive Fibonacci
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    // This is Method 2: The Get sequence as array 
    public static int[] getFibonacciSequence(int n) {
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = fibonacci(i);
        }
        return sequence;
    }

    // This is Method 3: To Check if number is Fibonacci 
    public static boolean isFibonacci(int number) {
        if (number < 0) return false;
        if (number == 0 || number == 1) return true;

        int a = 0;
        int b = 1;
        while (b < number) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b == number;
    }

    // This is Method 4: Iterative Fibonacci 
    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;

        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}