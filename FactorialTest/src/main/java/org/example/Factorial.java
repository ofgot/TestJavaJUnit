package org.example;

public class Factorial {
    public long factorial(long n){
        if (n>1){
            return n*factorial(n-1);
        } else {
            return n;
        }
    }
}
