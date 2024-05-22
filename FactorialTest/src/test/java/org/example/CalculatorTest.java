package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Factorial {
    @Test
    public void factorial_inputOne_returnOne(){
        Factorial calc = new Factorial();
        long inputValue = 1;
        long expectedResult = 1;

        long result = calc.factorial(inputValue);

        Assertions.assertEquals(expectedResult, result);
    }
}
