package org.example;

import org.example.secondhomeworkts1.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    public void testAddition(){
        double inputValue1 = 1.323;
        double inputValue2 = 1;
        double expectedResult = 2.323;

        double res = calc.addition(inputValue1, inputValue2);
        Assertions.assertEquals(res, expectedResult);
    }

    @Test
    public void testSubstract(){
        double inputValue1 = 3;
        double inputValue2 = 10;
        double expectedResult = -7;

        double res = calc.substract(inputValue1, inputValue2);
        Assertions.assertEquals(res, expectedResult);
    }

    @Test
    public void testDivision(){
        double inputValue1 = 10;
        double inputValue2 = 5;
        double expectedResult = 2;

        double res = calc.division(inputValue1, inputValue2);
        Assertions.assertEquals(res, expectedResult);
    }

    @Test
    public void testDivisionByZero(){
        double inputValue1 = 10;
        double inputValue2 = 0;

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            calc.division(inputValue1,inputValue2);
        });
    }

    @Test
    public void testMultiplication(){
        Calculator calc = new Calculator();
        double inputValue1 = 10;
        double inputValue2 = 5.34;
        double expectedResult = 53.4;

        double res = calc.multiply(inputValue1, inputValue2);
        Assertions.assertEquals(res, expectedResult);
    }
}
