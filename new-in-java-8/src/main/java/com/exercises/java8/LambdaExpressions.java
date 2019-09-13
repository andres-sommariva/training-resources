package com.exercises.java8;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        // TODO: Implement this method
        throw new NotImplementedException();
    }

    private Predicate<Integer> primeFilter() {
        // TODO: Implement this method
        throw new NotImplementedException();
    }

    private Predicate<Integer> perfectFilter() {
        // TODO: Implement this method
        throw new NotImplementedException();
    }

}
