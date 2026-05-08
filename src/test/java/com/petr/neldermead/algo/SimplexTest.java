package com.petr.neldermead.algo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimplexTest {

    private static final MathFunction DUMMY = p -> 0;

    @Test
    void testDimension() {

        MathFunction f = p -> p.getXn(0) * p.getXn(0) + p.getXn(1) * p.getXn(1);

        Point[] points = {
                f.createPoint(new double[]{1,2}),
                f.createPoint(new double[]{3,4}),
                f.createPoint(new double[]{5,6})
        };

        Simplex simplex = new Simplex(points);

        assertEquals(2, simplex.dimension());
    }

    @Test
    void testSort() {
        MathFunction f = p -> p.getXn(0) * p.getXn(0);

        Point[] points = {
                f.createPoint(new double[]{3}),
                f.createPoint(new double[]{1}),
                f.createPoint(new double[]{2})
        };

        Simplex simplex = new Simplex(points);
        simplex.sort(f);

        assertEquals(1, simplex.getV1Best().getXn(0));
        assertEquals(2, simplex.getV2Worse().getXn(0));
        assertEquals(3, simplex.getV3Worst().getXn(0));
    }

    @Test
    void testMiddlePoint() {
        MathFunction f = p -> p.getXn(0) + p.getXn(1);

        Point[] points = {
                f.createPoint(new double[]{0,0}),
                f.createPoint(new double[]{2,0}),
                f.createPoint(new double[]{0,2})
        };

        Simplex simplex = new Simplex(points);

        // сначала сортировка не обязательна, но обычно есть
        Point middle = simplex.getMiddlePoint(f);

        assertEquals(1, middle.getXn(0), 1e-6);
        assertEquals(0, middle.getXn(1), 1e-6);
    }

    @Test
    void testPlus() {
        Simplex simplex = new Simplex(new Point[]{
                DUMMY.createPoint(new double[]{0,0}),
                DUMMY.createPoint(new double[]{1,1})
        });

        Point p1 = DUMMY.createPoint(new double[]{1,2});
        Point p2 = DUMMY.createPoint(new double[]{3,4});

        Point result = simplex.plus(p1, p2, DUMMY);

        assertEquals(4, result.getXn(0));
        assertEquals(6, result.getXn(1));
    }

    @Test
    void testMultiply() {
        Simplex simplex = new Simplex(new Point[]{
                DUMMY.createPoint(new double[]{0,0}),
                DUMMY.createPoint(new double[]{1,1})
        });

        Point p = DUMMY.createPoint(new double[]{2,3});

        Point result = simplex.multiply(p, 2, DUMMY);

        assertEquals(4, result.getXn(0));
        assertEquals(6, result.getXn(1));
    }

    @Test
    void testReflection() {
        Simplex simplex = new Simplex(new Point[]{
                DUMMY.createPoint(new double[]{0,0}),
                DUMMY.createPoint(new double[]{1,0}),
                DUMMY.createPoint(new double[]{0,1})
        });

        Point middle = DUMMY.createPoint(new double[]{1,1});
        Point worst = DUMMY.createPoint(new double[]{0,0});

        Point xr = simplex.getXr(middle, worst, DUMMY);

        assertEquals(2, xr.getXn(0));
        assertEquals(2, xr.getXn(1));
    }
    //Переименовать xe xr xs
    @Test
    void testExpansion() {
        Simplex simplex = new Simplex(new Point[]{
                DUMMY.createPoint(new double[]{0,0}),
                DUMMY.createPoint(new double[]{1,0}),
                DUMMY.createPoint(new double[]{0,1})
        });

        Point middle = DUMMY.createPoint(new double[]{1,1});
        Point xr = DUMMY.createPoint(new double[]{2,2});

        Point xe = simplex.getXe(middle, xr,DUMMY);

        assertEquals(3, xe.getXn(0));
        assertEquals(3, xe.getXn(1));
    }

    @Test
    void testContraction() {
        Simplex simplex = new Simplex(new Point[]{
                DUMMY.createPoint(new double[]{0,0}),
                DUMMY.createPoint(new double[]{1,0}),
                DUMMY.createPoint(new double[]{0,1})
        });

        Point middle = DUMMY.createPoint(new double[]{1,1});
        Point worst = DUMMY.createPoint(new double[]{3,3});

        Point xs = simplex.getXs(middle, worst, DUMMY);

        assertEquals(2, xs.getXn(0));
        assertEquals(2, xs.getXn(1));
    }

    @Test
    void testCompression() {
        Point[] points = {
                DUMMY.createPoint(new double[]{0,0}),
                DUMMY.createPoint(new double[]{2,0}),
                DUMMY.createPoint(new double[]{0,2})
        };

        Simplex simplex = new Simplex(points);

        simplex.compression(DUMMY);

        // после сжатия точки должны приблизиться к первой
        assertTrue(simplex.getExactPoint(1).getXn(0) < 2);
        assertTrue(simplex.getExactPoint(2).getXn(1) < 2);
    }

    // newTests

    @Test
    void testSortValuesCorrect() {
        MathFunction f = p -> p.getXn(0);
        Point[] points = {
                f.createPoint(new double[]{10}),
                f.createPoint(new double[]{5}),
                f.createPoint(new double[]{1})
        };
        Simplex simplex = new Simplex(points);
        simplex.sort(f);

        assertTrue(simplex.getV1Best().getValue() <= simplex.getV2Worse().getValue());
        assertTrue(simplex.getV2Worse().getValue() <= simplex.getV3Worst().getValue());
    }

    @Test
    void testPointValueStoredCorrectly() {
        MathFunction f = p -> p.getXn(0) * p.getXn(0);
        Point p = f.createPoint(new double[]{3});
        assertEquals(9, p.getValue(), 1e-9);
    }

    @Test
    void testMiddlePointExcludesWorst() {
        MathFunction f = p -> p.getXn(0);
        // points[0]=0, points[1]=1, points[2]=100 — worst не входит в среднее
        Point[] points = {
                f.createPoint(new double[]{0}),
                f.createPoint(new double[]{1}),
                f.createPoint(new double[]{100})
        };
        Simplex simplex = new Simplex(points);
        Point middle = simplex.getMiddlePoint(f);

        // среднее только первых двух = 0.5
        assertEquals(0.5, middle.getXn(0), 1e-6);
    }

    @Test
    void testCopyPointsIsIndependent() {
        MathFunction f = p -> p.getXn(0);
        Point[] points = {
                f.createPoint(new double[]{1}),
                f.createPoint(new double[]{2})
        };
        Simplex simplex = new Simplex(points);
        Point[] copy = simplex.copyPoints();

        // копия не должна быть тем же массивом
        assertNotSame(points, copy);
        assertEquals(points[0].getXn(0), copy[0].getXn(0));
    }

}