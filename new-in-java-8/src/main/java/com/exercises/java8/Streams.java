package com.exercises.java8;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public List<Point> getUniquePointsInQuadrantOne(List<Point> points) {
        return points.stream()
                .filter(p -> p.x > 0 && p.y > 0)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantTwo(List<Point> points) {
        return points.stream()
                .filter(p -> p.x < 0 && p.y > 0)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantThree(List<Point> points) {
        return points.stream()
                .filter(p -> p.x < 0 && p.y < 0)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantFour(List<Point> points) {
        return points.stream()
                .filter(p -> p.x > 0 && p.y < 0)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Double> getSortedPointsDistanceToOrigin(List<Point> points) {
        return points.stream()
                .mapToDouble(p -> Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2)))
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }

    public Double getSumOfAllDistances(List<Point> points) {
        return points.stream()
                .mapToDouble(p -> Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2)))
                .reduce(0.0, (v1, v2) -> v1 + v2);
    }

}
