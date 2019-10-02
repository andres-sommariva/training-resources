package com.exercises.java8;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaExpressions {

    public List<Integer> findOddNumbers(List<Integer> numbers) {
        return numbers.stream().filter(oddFilter()).collect(Collectors.toList());
    }

    public List<Integer> findPrimeNumbers(List<Integer> numbers) {
        return numbers.stream().filter(primeFilter()).collect(Collectors.toList());
    }

    public List<Integer> findPerfectNumbers(List<Integer> numbers) {
        return numbers.stream().filter(perfectFilter()).collect(Collectors.toList());
    }

    private Predicate<Integer> oddFilter() {
        return number -> (number % 2) != 0;
    }

    private Predicate<Integer> primeFilter() {
        return number -> {
            for(int i=2;i<number;i++) {
                if(number %i == 0)
                    return false;
            }
            return true;
        };
    }

    private Predicate<Integer> perfectFilter() {
        return number -> {
            int sum = 0;
            for (int i = 1; i < number; i++) {
                if (number % i == 0) {
                     sum += i;
                }
            }
            return sum == number;
        };
    }

}
