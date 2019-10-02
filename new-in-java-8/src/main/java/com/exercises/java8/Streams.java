package com.exercises.java8;

import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public List<Point> getUniquePointsInQuadrantOne(List<Point> points) {
        return points.stream()
            .filter(p -> p.x > 0)
            .filter(p -> p.y > 0)
						.distinct()
            .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantTwo(List<Point> points) {
        return points.stream()
          .filter(p -> p.x < 0)
          .filter(p -> p.y > 0)
					.distinct()
          .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantThree(List<Point> points) {
        return points.stream()
          .filter(p -> p.x < 0)
          .filter(p -> p.y < 0)
					.distinct()
          .collect(Collectors.toList());
    }

    public List<Point> getUniquePointsInQuadrantFour(List<Point> points) {
        return points.stream()
          .filter(p -> p.x > 0)
          .filter(p -> p.y < 0)
					.distinct()
          .collect(Collectors.toList());
    }

    public List<Double> getSortedPointsDistanceToOrigin(List<Point> points) {
        return points.stream()
					.map(p -> p.distance(0,0))
					.sorted()
					.collect(Collectors.toList());
    }

    public Double getSumOfAllDistances(List<Point> points) {
			return points.stream()
				.mapToDouble(p -> p.distance(0,0))
				.sum();
    }
}
