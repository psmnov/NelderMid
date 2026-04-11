package com.petr.neldermead.visualization;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinateTransformerTest {
    @Test
    void testToScreenXDefault() {
        CoordinateTransformer ct = new CoordinateTransformer();
        assertEquals(400, ct.toScreenX(0));
        assertEquals(420, ct.toScreenX(1));
    }

    @Test
    void testToScreenYDefault() {
        CoordinateTransformer ct = new CoordinateTransformer();
        assertEquals(400, ct.toScreenY(0));
        assertEquals(380, ct.toScreenY(1));
    }
    @Test
    void testZoomZeroFactor() {
        CoordinateTransformer ct = new CoordinateTransformer();
        ct.zoom(0, 300, 300);
        assertEquals(0, ct.getScale()); //  масштаб обнуляется
        // дальше всё должно схлопнуться в pivot
        assertEquals(300, ct.toScreenX(100));
        assertEquals(300, ct.toScreenY(100));
    }
    @Test
    void testZoomNegativeFactor() {
        CoordinateTransformer ct = new CoordinateTransformer();
        ct.zoom(-1, 300, 300);
        assertEquals(-20, ct.getScale());
        //координаты зеркалятся
        assertEquals(180, ct.toScreenX(1));
        assertEquals(220, ct.toScreenY(1));
    }
    @Test
    void testZoomLargeFactor() {
        CoordinateTransformer ct = new CoordinateTransformer();
        ct.zoom(1e9, 300, 300);
        assertTrue(ct.getScale() > 1e9);
    }
    @Test
    void testZoomInfinity() {
        CoordinateTransformer ct = new CoordinateTransformer();
        ct.zoom(Double.POSITIVE_INFINITY, 300, 300);
        assertTrue(Double.isInfinite(ct.getScale()));
    }
}
