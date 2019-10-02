package com.exercises.java8;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        return number -> Optional.ofNullable(number).map(value -> value % 2 != 0 ).orElse(false);
    }

    private Predicate<Integer> primeFilter() {
        return number -> getDivisors(number).count() == 1L;
    }

    private Predicate<Integer> perfectFilter() {
        return number -> getDivisors(number).sum() == number;
    }

    private IntStream getDivisors(Integer integer) {
        return  Optional.ofNullable(integer)
            .map(value -> IntStream.rangeClosed(1, integer / 2).filter(intValue -> integer % intValue == 0))
            .orElse(IntStream.empty());
    }
}
