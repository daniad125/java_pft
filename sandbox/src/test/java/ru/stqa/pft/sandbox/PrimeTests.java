package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
public class PrimeTests {
    @Test
    public void testPrime1() {
        Assert.assertTrue(Primes.isPrime(7));
    }
    @Test
    public void testPrime2() {
        Assert.assertTrue(Primes.isPrime(2));
    }
    @Test
    public void testPrime3() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }
    @Test
    public void testNonPrime1(){
        Assert.assertFalse(Primes.isPrime(18));
    }
    @Test
    public void testNonPrime2() {
        Assert.assertFalse(Primes.isPrime(2965));
    }
    @Test
    public void testNonPrime3() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
    @Test
    public void primeWhile1() {
        Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
    }
    @Test (enabled = false)
    public void isPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }
    @Test
    public void testFast() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }
}
