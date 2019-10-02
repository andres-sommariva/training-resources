package com.exercises.java8;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public List<Point> getUniquePointsInQuadrantOne(List<Point> points) {
        return points.stream().filter(point -> point.x > 0 && point.y > 0).distinct().collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantTwo(List<Point> points) {
        return points.stream().filter(point -> point.x < 0 && point.y > 0).distinct().collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantThree(List<Point> points) {
        return points.stream().filter(point -> point.x < 0 && point.y < 0).distinct().collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantFour(List<Point> points) {
        return points.stream().filter(point -> point.x > 0 && point.y < 0).distinct().collect(Collectors.toList());
    }

    public List<Double> getSortedPointsDistanceToOrigin(List<Point> points) {
        return points.stream().map(point -> Math.sqrt(Math.pow(Math.abs(point.x), 2) + Math.pow(Math.abs(point.y), 2))).sorted().collect(Collectors.toList());
    }

    public Double getSumOfAllDistances(List<Point> points) {
        return points.stream().map(point -> Math.sqrt(Math.pow(Math.abs(point.x), 2) + Math.pow(Math.abs(point.y), 2))).mapToDouble(Double::valueOf).sum();
    }
}
