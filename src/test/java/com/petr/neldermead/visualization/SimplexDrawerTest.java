package com.petr.neldermead.visualization;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimplexDrawerTest {

    @Test
    void testComputeDataCoordinatesCorrect() {
        CoordinateTransformer t = new CoordinateTransformer();
        MathFunction f = p -> 0;

        Point[] points = {
                f.createPoint(new double[]{0, 0}),
                f.createPoint(new double[]{1, 0}),
                f.createPoint(new double[]{0, 1})
        };

        SimplexDrawer.coordSimplex data = SimplexDrawer.computeData(points, t);

        // x1[0] должен быть экранной координатой точки (0,0)
        assertEquals(t.toScreenX(0), data.x1[0], 1e-6);
        assertEquals(t.toScreenY(0), data.y1[0], 1e-6);

        // x2[0] должен вести к следующей точке (1,0)
        assertEquals(t.toScreenX(1), data.x2[0], 1e-6);
        assertEquals(t.toScreenY(0), data.y2[0], 1e-6);
    }

    @Test
    void testComputeDataLastPointConnectsToFirst() {
        CoordinateTransformer t = new CoordinateTransformer();
        MathFunction f = p -> 0;

        Point[] points = {
                f.createPoint(new double[]{0, 0}),
                f.createPoint(new double[]{1, 0}),
                f.createPoint(new double[]{0, 1})
        };

        SimplexDrawer.coordSimplex data = SimplexDrawer.computeData(points, t);

        int last = points.length - 1;
        // последняя точка должна соединяться с первой
        assertEquals(data.x1[0], data.x2[last], 1e-6);
        assertEquals(data.y1[0], data.y2[last], 1e-6);
    }

    @Test
    void testComputeDataSizeMatchesPoints() {
        CoordinateTransformer t = new CoordinateTransformer();
        MathFunction f = p -> 0;

        Point[] points = {
                f.createPoint(new double[]{0, 0}),
                f.createPoint(new double[]{2, 0}),
                f.createPoint(new double[]{0, 2}),
                f.createPoint(new double[]{1, 1})
        };

        SimplexDrawer.coordSimplex data = SimplexDrawer.computeData(points, t);

        assertEquals(points.length, data.x1.length);
        assertEquals(points.length, data.y1.length);
        assertEquals(points.length, data.x2.length);
        assertEquals(points.length, data.y2.length);
    }
}