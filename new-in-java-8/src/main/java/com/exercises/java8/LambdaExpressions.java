package com.exercises.java8;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return i -> i % 2 != 0;
    }

    private Predicate<Integer> primeFilter() {
        return i -> i > 1 && IntStream.rangeClosed(2, i / 2).filter(n -> i % n == 0).count() == 0;
    }

    private Predicate<Integer> perfectFilter() {
        return i -> i > 0 && IntStream.rangeClosed(1, i / 2).filter(n -> i % n == 0).sum() == i;
    }
}
