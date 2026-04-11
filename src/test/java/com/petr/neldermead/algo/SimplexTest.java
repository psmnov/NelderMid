package com.petr.neldermead.algo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimplexTest {

    @Test
    void testDimension() {
        Point[] points = {
                new Point(new double[]{1,2}),
                new Point(new double[]{3,4}),
                new Point(new double[]{5,6})
        };

        Simplex simplex = new Simplex(points);

        assertEquals(2, simplex.dimension());
    }

    @Test
    void testSort() {
        MathFunction f = p -> p.getXn(0) * p.getXn(0);

        Point[] points = {
                new Point(new double[]{3}),
                new Point(new double[]{1}),
                new Point(new double[]{2})
        };

        Simplex simplex = new Simplex(points);
        simplex.sort(f);

        assertEquals(1, simplex.getV1Best().getXn(0));
        assertEquals(2, simplex.getV2Worse().getXn(0));
        assertEquals(3, simplex.getV3Worst().getXn(0));
    }

    @Test
    void testMiddlePoint() {
        Point[] points = {
                new Point(new double[]{0,0}),
                new Point(new double[]{2,0}),
                new Point(new double[]{0,2})
        };

        Simplex simplex = new Simplex(points);

        // сначала сортировка не обязательна, но обычно есть
        Point middle = simplex.getMiddlePoint();

        assertEquals(1, middle.getXn(0), 1e-6);
        assertEquals(0, middle.getXn(1), 1e-6);
    }

    @Test
    void testPlus() {
        Simplex simplex = new Simplex(new Point[]{
                new Point(new double[]{0,0}),
                new Point(new double[]{1,1})
        });

        Point p1 = new Point(new double[]{1,2});
        Point p2 = new Point(new double[]{3,4});

        Point result = simplex.plus(p1, p2);

        assertEquals(4, result.getXn(0));
        assertEquals(6, result.getXn(1));
    }

    @Test
    void testMultiply() {
        Simplex simplex = new Simplex(new Point[]{
                new Point(new double[]{0,0}),
                new Point(new double[]{1,1})
        });

        Point p = new Point(new double[]{2,3});

        Point result = simplex.multiply(p, 2);

        assertEquals(4, result.getXn(0));
        assertEquals(6, result.getXn(1));
    }

    @Test
    void testReflection() {
        Simplex simplex = new Simplex(new Point[]{
                new Point(new double[]{0,0}),
                new Point(new double[]{1,0}),
                new Point(new double[]{0,1})
        });

        Point middle = new Point(new double[]{1,1});
        Point worst = new Point(new double[]{0,0});

        Point xr = simplex.getXr(middle, worst);

        assertEquals(2, xr.getXn(0));
        assertEquals(2, xr.getXn(1));
    }
    //Переименовать xe xr xs
    @Test
    void testExpansion() {
        Simplex simplex = new Simplex(new Point[]{
                new Point(new double[]{0,0}),
                new Point(new double[]{1,0}),
                new Point(new double[]{0,1})
        });

        Point middle = new Point(new double[]{1,1});
        Point xr = new Point(new double[]{2,2});

        Point xe = simplex.getXe(middle, xr);

        assertEquals(3, xe.getXn(0));
        assertEquals(3, xe.getXn(1));
    }

    @Test
    void testContraction() {
        Simplex simplex = new Simplex(new Point[]{
                new Point(new double[]{0,0}),
                new Point(new double[]{1,0}),
                new Point(new double[]{0,1})
        });

        Point middle = new Point(new double[]{1,1});
        Point worst = new Point(new double[]{3,3});

        Point xs = simplex.getXs(middle, worst);

        assertEquals(2, xs.getXn(0));
        assertEquals(2, xs.getXn(1));
    }

    @Test
    void testCompression() {
        Point[] points = {
                new Point(new double[]{0,0}),
                new Point(new double[]{2,0}),
                new Point(new double[]{0,2})
        };

        Simplex simplex = new Simplex(points);

        simplex.compression();

        // после сжатия точки должны приблизиться к первой
        assertTrue(simplex.getExactPoint(1).getXn(0) < 2);
        assertTrue(simplex.getExactPoint(2).getXn(1) < 2);
    }
}