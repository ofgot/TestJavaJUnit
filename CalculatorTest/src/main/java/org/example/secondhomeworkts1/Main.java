package org.example.secondhomeworkts1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        System.out.println(cal.addition(1.323,1));
        System.out.println(cal.substract(3,10));
        System.out.println(cal.multiply(5.34,10));
        System.out.println(cal.division(10,2));
        System.out.println(cal.division(10,0));
    }

}
