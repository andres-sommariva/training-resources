package com.exercises.java8;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Streams {

    public List<Point> getUniquePointsInQuadrantOne(List<Point> points) {
        return filter(points, point -> point.x > 0 && point.y > 0);
    }

    public List<Point> getUniquePointsInQuadrantTwo(List<Point> points) {
        return filter(points, point -> point.x < 0 && point.y > 0);
    }

    public List<Point> getUniquePointsInQuadrantThree(List<Point> points) {
        return filter(points, point -> point.x < 0 && point.y < 0);
    }

    public List<Point> getUniquePointsInQuadrantFour(List<Point> points) {
        return filter(points, point -> point.x > 0 && point.y < 0);
    }

    public List<Double> getSortedPointsDistanceToOrigin(List<Point> points) {
        return distances(points).sorted().boxed().collect(Collectors.toList());
    }

    public Double getSumOfAllDistances(List<Point> points) {
        return distances(points).sum();
    }

    private List<Point> filter(List<Point> points, Predicate<Point> predicate) {
        return Optional.ofNullable(points)
            .map(list -> list.stream().filter(predicate).distinct())
            .orElse(Stream.empty())
            .collect(Collectors.toList());
    }

    private DoubleStream distances(List<Point> points) {
        return Optional.ofNullable(points)
            .map(list -> list.stream().mapToDouble(point -> Math.sqrt(Math.pow(point.x, 2) + Math.pow(point.y, 2))))
            .orElse(DoubleStream.empty());
    }
}
