package com.exercises.java8;

import java.awt.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams {

    public List<Point> getUniquePointsInQuadrantOne(List<Point> points) {
        return pointFilter(points, point -> point.getX() > 0 && point.getY() > 0);
    }

    public List<Point> getUniquePointsInQuadrantTwo(List<Point> points) {
        return pointFilter(points, point -> point.getX() < 0 && point.getY() > 0);
    }

    public List<Point> getUniquePointsInQuadrantThree(List<Point> points) {
        return pointFilter(points, point -> point.getX() < 0 && point.getY() < 0);
    }

    public List<Point> getUniquePointsInQuadrantFour(List<Point> points) {
        return pointFilter(points, point -> point.getX() > 0 && point.getY() < 0);
    }

    public List<Double> getSortedPointsDistanceToOrigin(List<Point> points) {
        return points.stream()
          .map(point -> getDistanceToOrigin(point))
          .sorted()
          .collect(Collectors.toList());
    }

    public Double getSumOfAllDistances(List<Point> points) {
        return points.stream()
          .map(point -> getDistanceToOrigin(point))
          .reduce(0.0, (point1, point2) -> (point1) + (point2));
    }

    public List<Point> pointFilter(List<Point> points, Predicate<Point> predicate) {
        return points.stream()
          .filter(predicate)
          .distinct()
          .collect(Collectors.toList());
    }

    public Double getDistanceToOrigin(Point point) {
        return point.distance(0,0);
    }
}
