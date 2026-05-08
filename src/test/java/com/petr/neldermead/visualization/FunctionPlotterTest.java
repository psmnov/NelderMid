package com.petr.neldermead.visualization;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionPlotterTest {

    @Test
    void testComputePlotPointsNotEmpty() {
        MathFunction f = p -> p.getXn(0) * p.getXn(0) + p.getXn(1) * p.getXn(1);
        CoordinateTransformer t = new CoordinateTransformer();

        FunctionPlotter.PlotPoint[] points = FunctionPlotter.computePlotPoints(f, t);

        assertTrue(points.length > 0);
    }

    @Test
    void testComputePlotPointsColorInRange() {
        MathFunction f = p -> p.getXn(0) * p.getXn(0) + p.getXn(1) * p.getXn(1);
        CoordinateTransformer t = new CoordinateTransformer();

        FunctionPlotter.PlotPoint[] points = FunctionPlotter.computePlotPoints(f, t);

        for (FunctionPlotter.PlotPoint p : points) {
            assertTrue(p.colorValue >= 0, "colorValue должен быть >= 0");
            assertTrue(p.colorValue <= 1, "colorValue должен быть <= 1");
        }
    }

    @Test
    void testComputePlotPointsMinimumHasDarkestColor() {
        // функция минимальна в (0,0) — там colorValue должен быть минимальным
        MathFunction f = p -> p.getXn(0) * p.getXn(0) + p.getXn(1) * p.getXn(1);
        CoordinateTransformer t = new CoordinateTransformer();

        FunctionPlotter.PlotPoint[] points = FunctionPlotter.computePlotPoints(f, t);

        double minColor = Double.MAX_VALUE;
        for (FunctionPlotter.PlotPoint p : points) {
            if (p.colorValue < minColor) minColor = p.colorValue;
        }

        assertEquals(0, minColor, 1e-6);
    }
}