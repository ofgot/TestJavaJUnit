package org.example.secondhomeworkts1;

public class Calculator {

    public double addition(double first, double second){
        return first + second;
    }
    public double substract(double first, double second){
        return first - second;
    }

    public double division(double first, double second){
        if (second != 0) {
            return first / second;
        } else {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
    }


    public  double multiply(double first, double second){
        return first * second;
    }


}



