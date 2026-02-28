// This is AppTest.java, Assignment 3 - AbiolaA
package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    // This is Test 1:The Basic Fibonacci calculations
    @Test
    public void testFibonacciBaseCases() {
        System.out.println("Test 1: Checking base cases...");
        assertEquals(0, App.fibonacci(0));
        assertEquals(1, App.fibonacci(1));
    }

    // This is Test 2: The Fibonacci sequence values
    @Test
    public void testFibonacciSequence() {
        System.out.println("Test 2: Checking sequence values...");
        assertEquals(1, App.fibonacci(2));
        assertEquals(2, App.fibonacci(3));
        assertEquals(3, App.fibonacci(4));
        assertEquals(5, App.fibonacci(5));
        assertEquals(8, App.fibonacci(6));
    }

    // This is Test 3: The Get Fibonacci sequence array
    @Test
    public void testGetFibonacciSequence() {
        System.out.println("Test 3: Testing getFibonacciSequence method...");
        int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
        int[] result = App.getFibonacciSequence(10);
        assertArrayEquals(expected, result);
    }

    // This is Test 4: To Check if numbers are Fibonacci (for true cases)
    @Test
    public void testIsFibonacciTrue() {
        System.out.println("Test 4: Testing isFibonacci (true cases)...");
        assertTrue(App.isFibonacci(0));
        assertTrue(App.isFibonacci(1));
        assertTrue(App.isFibonacci(2));
        assertTrue(App.isFibonacci(3));
        assertTrue(App.isFibonacci(5));
        assertTrue(App.isFibonacci(8));
        assertTrue(App.isFibonacci(13));
    }

    // This is Test 5: To Check if numbers are NOT Fibonacci (for false cases)
    @Test
    public void testIsFibonacciFalse() {
        System.out.println("Test 5: Testing isFibonacci (false cases)...");
        assertFalse(App.isFibonacci(4));
        assertFalse(App.isFibonacci(6));
        assertFalse(App.isFibonacci(7));
        assertFalse(App.isFibonacci(9));
        assertFalse(App.isFibonacci(10));
        assertFalse(App.isFibonacci(-1));
    }

    // This is Test 6: To Compare recursive vs iterative methods
    @Test
    public void testRecursiveVsIterative() {
        System.out.println("Test 6: Comparing recursive vs iterative...");
        for (int i = 0; i < 10; i++) {
            assertEquals(App.fibonacci(i), App.fibonacciIterative(i));
        }
    }

    // This is Test 7: The Edge cases and special scenarios
    @Test
    public void testEdgeCases() {
        System.out.println("Test 7: Testing edge cases...");
        // Test with n=0 sequence
        int[] empty = App.getFibonacciSequence(0);
        assertEquals(0, empty.length);

        // This will test with the n=1 sequence
        int[] single = App.getFibonacciSequence(1);
        assertEquals(1, single.length);
        assertEquals(0, single[0]);
    }
}