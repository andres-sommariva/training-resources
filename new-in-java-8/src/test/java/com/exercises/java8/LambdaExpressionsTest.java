package com.exercises.java8;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LambdaExpressionsTest {

    private final static List<Integer> TEST_VALUES = Arrays.asList(1,2,3,4,5,6,7,8,9,10,28,29,30);
    private LambdaExpressions lambdaExpressions;

    @BeforeTest
    public void setUp() {
        lambdaExpressions = new LambdaExpressions();
    }

    @Test
    public void test_getOddNumbers() {
        assertEquals(lambdaExpressions.findOddNumbers(TEST_VALUES), Arrays.asList(1,3,5,7,9,29));
    }

    @Test
    public void test_getPrimeNumbers() {
        assertEquals(lambdaExpressions.findPrimeNumbers(TEST_VALUES), Arrays.asList(2,3,5,7,29));
    }

    @Test
    public void test_getPerfectNumbers() {
        assertEquals(lambdaExpressions.findPerfectNumbers(TEST_VALUES), Arrays.asList(6,28));
    }

}
