package com.petr.neldermead.algo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @Test
    void testGetValue() {
        MathFunction f = p -> p.getXn(0) * p.getXn(0);
        Point p = f.createPoint(new double[]{3});
        assertEquals(9, p.getValue(), 1e-9);
    }

    @Test
    void testGetDimension() {
        MathFunction f = p -> 0;
        Point p = f.createPoint(new double[]{1, 2, 3});
        assertEquals(3, p.getDimension());
    }

    @Test
    void testGetXn() {
        MathFunction f = p -> 0;
        Point p = f.createPoint(new double[]{5, 7});
        assertEquals(5, p.getXn(0), 1e-9);
        assertEquals(7, p.getXn(1), 1e-9);
    }

    @Test
    void testGetXReturnsCopy() {
        MathFunction f = p -> 0;
        Point p = f.createPoint(new double[]{1, 2});
        double[] x = p.getX();
        x[0] = 999;
        // изменение копии не должно менять оригинал
        assertEquals(1, p.getXn(0), 1e-9);
    }

    @Test
    void testToString() {
        MathFunction f = p -> 0;
        Point p = f.createPoint(new double[]{1, 2});
        String s = p.toString();
        assertTrue(s.contains("1"));
        assertTrue(s.contains("2"));
    }
}