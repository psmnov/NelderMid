package com.petr.neldermead.algo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NelderMeadTest {

    @Test
    void testSimpleParaboloid() {

        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            return x*x + y*y;
        };

        Point[] start = {
                f.createPoint(new double[]{5,5}),
                f.createPoint(new double[]{6,5}),
                f.createPoint(new double[]{5,6})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        assertEquals(0, result.getXn(0), 1e-2);
        assertEquals(0, result.getXn(1), 1e-2);
    }

    @Test
    void testShiftedMinimum() {

        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            return Math.pow(x-2,2) + Math.pow(y+1,2);
        };

        Point[] start = {
                f.createPoint(new double[]{0,0}),
                f.createPoint(new double[]{1,0}),
                f.createPoint(new double[]{0,1})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        assertEquals(2, result.getXn(0), 1e-2);
        assertEquals(-1, result.getXn(1), 1e-2);
    }

    @Test
    void testRosenbrock() {

        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            return Math.pow(1-x,2) + 100*Math.pow(y-x*x,2);
        };

        Point[] start = {
                f.createPoint(new double[]{-1,1}),
                f.createPoint(new double[]{0,1}),
                f.createPoint(new double[]{-1,2})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        assertEquals(1, result.getXn(0), 1e-1);
        assertEquals(1, result.getXn(1), 1e-1);
    }

    @Test
    void testFunctionDecreases() {
        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            return x*x + y*y;
        };

        Point[] start = {
                f.createPoint(new double[]{10,10}),
                f.createPoint(new double[]{11,10}),
                f.createPoint(new double[]{10,11})
        };

        Simplex simplex = new Simplex(start);

        double startValue = f.calc(start[0]);

        NelderMead nm = new NelderMead(f, simplex);
        Point result = nm.optimizationAlgo();

        double resultValue = f.calc(result);

        assertTrue(resultValue < startValue);
    }
    @Test
    void test3DParaboloid() {
        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            double z = p.getXn(2);

            return x*x + y*y + z*z;
        };

        Point[] start = {
                f.createPoint(new double[]{5,5,5}),
                f.createPoint(new double[]{6,5,5}),
                f.createPoint(new double[]{5,6,5}),
                f.createPoint(new double[]{5,5,6})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);
        Point result = nm.optimizationAlgo();

        assertEquals(0, result.getXn(0), 1e-2);
        assertEquals(0, result.getXn(1), 1e-2);
        assertEquals(0, result.getXn(2), 1e-2);
    }
    @Test
    void test3DShiftedMinimum() {
        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            double z = p.getXn(2);

            return Math.pow(x-2,2)
                    + Math.pow(y+1,2)
                    + Math.pow(z-3,2);
        };

        Point[] start = {
                f.createPoint(new double[]{0,0,0}),
                f.createPoint(new double[]{1,0,0}),
                f.createPoint(new double[]{0,1,0}),
                f.createPoint(new double[]{0,0,1})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        assertEquals(2, result.getXn(0), 1e-2);
        assertEquals(-1, result.getXn(1), 1e-2);
        assertEquals(3, result.getXn(2), 1e-2);
    }
    @Test
    void test4DParaboloid() {
        MathFunction f = p -> {
            double sum = 0;
            for(int i = 0; i < 4; i++){
                sum += p.getXn(i) * p.getXn(i);
            }
            return sum;
        };

        Point[] start = {
                f.createPoint(new double[]{5,5,5,5}),
                f.createPoint(new double[]{6,5,5,5}),
                f.createPoint(new double[]{5,6,5,5}),
                f.createPoint(new double[]{5,5,6,5}),
                f.createPoint(new double[]{5,5,5,6})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        for(int i = 0; i < 4; i++){
            assertEquals(0, result.getXn(i), 1e-2);
        }
    }
    @Test
    void test3DTrigonometricFunction() {
        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            double z = p.getXn(2);

            return Math.sin(x) + Math.cos(y) + z*z;
        };

        Point[] start = {
                f.createPoint(new double[]{0,0,0}),
                f.createPoint(new double[]{0,0,1}),
                f.createPoint(new double[]{-1,0.5,0}),
                f.createPoint(new double[]{-2,-1,-0.5})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();
        double value = f.calc(result);

        assertEquals(-Math.PI/2, result.getXn(0), 1e-1);
        assertEquals(-Math.PI, result.getXn(1), 1e-1);
        assertEquals(0, result.getXn(2), 1e-1);
        assertTrue(value < 0);
    }
    @Test
    void testLogFunction() {
        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);

            return Math.log(x*x + y*y + 1);
        };

        Point[] start = {
                f.createPoint(new double[]{3,3}),
                f.createPoint(new double[]{4,3}),
                f.createPoint(new double[]{3,4})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();


        assertEquals(0, result.getXn(0), 1e-2);
        assertEquals(0, result.getXn(1), 1e-2);
    }
}