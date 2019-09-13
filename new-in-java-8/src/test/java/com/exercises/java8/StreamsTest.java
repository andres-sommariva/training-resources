package com.exercises.java8;

import static org.testng.Assert.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StreamsTest {

    private final static List<Point> TEST_VALUES = Arrays.asList(
            // origin
            new Point(0,0),
            // quadrant 1
            new Point(1,1),
            new Point(1,1),
            // quadrant 2
            new Point(-1,1),
            new Point(-2,2),
            new Point(-1,1),
            // quadrant 3
            new Point(-1,-1),
            new Point(-2,-2),
            new Point(-3,-3),
            new Point(-1,-1),
            // quadrant 4
            new Point(1,-1),
            new Point(2,-2),
            new Point(3,-3),
            new Point(4,-4),
            new Point(1,-1)
            );

    private Streams streams;

    @BeforeTest
    public void setUp() {
        streams = new Streams();
    }

    @Test
    public void testGetUniquePointsInQuadrantOne() {
        assertEquals(streams.getUniquePointsInQuadrantOne(TEST_VALUES).size(), 1);
    }

    @Test
    public void testGetUniquePointsInQuadrantTwo() {
        assertEquals(streams.getUniquePointsInQuadrantTwo(TEST_VALUES).size(), 2);
    }

    @Test
    public void testGetUniquePointsInQuadrantThree() {
        assertEquals(streams.getUniquePointsInQuadrantThree(TEST_VALUES).size(), 3);
    }

    @Test
    public void testGetUniquePointsInQuadrantFour() {
        assertEquals(streams.getUniquePointsInQuadrantFour(TEST_VALUES).size(), 4);
    }

    @Test
    public void testGetSortedPointsDistanceToOrigin() {
        Double sqrt2 = Double.valueOf(Math.sqrt(2));
        Double sqrt8 = Double.valueOf(Math.sqrt(8));
        Double sqrt18 = Double.valueOf(Math.sqrt(18));
        Double sqrt32 = Double.valueOf(Math.sqrt(32));

        List<Double> expectedDistances = Arrays.asList(
                Double.valueOf(0),
                sqrt2,sqrt2,sqrt2,sqrt2,sqrt2,sqrt2,sqrt2,sqrt2,
                sqrt8,sqrt8,sqrt8,
                sqrt18,sqrt18,
                sqrt32
        );

        assertEquals(streams.getSortedPointsDistanceToOrigin(TEST_VALUES), expectedDistances);
    }

    @Test
    public void testGetSumOfAllDistances() {
        Double sqrt2 = Double.valueOf(Math.sqrt(2));
        Double sqrt8 = Double.valueOf(Math.sqrt(8));
        Double sqrt18 = Double.valueOf(Math.sqrt(18));
        Double sqrt32 = Double.valueOf(Math.sqrt(32));

        Double expectedValue = sqrt2 * 8 + sqrt8 * 3 + sqrt18 * 2 + sqrt32;
        assertEquals(streams.getSumOfAllDistances(TEST_VALUES), expectedValue);
    }

    @Test
    public void testGetSumOfAllDistances_empty() {
        assertEquals(streams.getSumOfAllDistances(Collections.EMPTY_LIST), 0);
    }
}
