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
        return x -> x % 2 == 1;
    }

    private Predicate<Integer> primeFilter() {
        return x -> {
            if (x == 1) return false;
            for(int i = 2; i <= x/2; ++i) {
                if(x % i == 0) {
                    return false;
                }
            }
            return true;
        };
    }

    private Predicate<Integer> perfectFilter() {
        return x -> {
            int sum = 0;
            for (int i = 1; i < x; i++) {
                if (x % i == 0) {
                    sum = sum + i;
                }
            }
            return sum == x;
        };
    }

}
