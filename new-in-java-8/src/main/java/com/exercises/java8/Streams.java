package com.exercises.java8;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public List<Point> getUniquePointsInQuadrantOne(List<Point> points) {
        return points.stream()
          .filter(point -> point.getX() > 0 && point.getY() > 0)
          .distinct()
          .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantTwo(List<Point> points) {
        return points.stream()
          .filter(point -> point.getX() < 0 && point.getY() > 0)
          .distinct()
          .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantThree(List<Point> points) {
        return points.stream()
          .filter(point -> point.getX() < 0 && point.getY() < 0)
          .distinct()
          .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantFour(List<Point> points) {
        return points.stream()
          .filter(point -> point.getX() > 0 && point.getY() < 0)
          .distinct()
          .collect(Collectors.toList());
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

    public Double getDistanceToOrigin(Point point) {
        return point.distance(0,0);
    }
}
