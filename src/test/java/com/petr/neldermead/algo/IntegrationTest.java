package com.petr.neldermead.algo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    @Test
    void fullOptimizationPipeline() {

        MathFunction f = p -> {
            double x = p.getXn(0);
            double y = p.getXn(1);
            return Math.pow(x-2,2) + Math.pow(y+1,2);
        };

        Point[] start = {
                f.createPoint(new double[]{5,5}),
                f.createPoint(new double[]{6,5}),
                f.createPoint(new double[]{5,6})
        };

        Simplex simplex = new Simplex(start);
        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        double value = f.calc(result);

        assertTrue(value < 1e-4);
        assertEquals(2, result.getXn(0), 1e-2);
        assertEquals(-1, result.getXn(1), 1e-2);
    }
}