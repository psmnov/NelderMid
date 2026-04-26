package com.petr.neldermead.visualization;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTransformerTest {

    @Test
    void testToScreenXDefault() {
        CoordinateTransformer t = new CoordinateTransformer();
        // при scale=20, offsetX=400: toScreenX(0) = 400
        assertEquals(400, t.toScreenX(0), 1e-6);
    }

    @Test
    void testToScreenYDefault() {
        CoordinateTransformer t = new CoordinateTransformer();
        // при scale=20, offsetY=400: toScreenY(0) = 400
        assertEquals(400, t.toScreenY(0), 1e-6);
    }

    @Test
    void testToScreenXPositive() {
        CoordinateTransformer t = new CoordinateTransformer();
        // toScreenX(1) = 400 + 1*20 = 420
        assertEquals(420, t.toScreenX(1), 1e-6);
    }

    @Test
    void testToScreenYPositive() {
        CoordinateTransformer t = new CoordinateTransformer();
        // toScreenY(1) = 400 - 1*20 = 380 (ось Y перевёрнута)
        assertEquals(380, t.toScreenY(1), 1e-6);
    }

    @Test
    void testMoveShiftsOffset() {
        CoordinateTransformer t = new CoordinateTransformer();
        double before = t.toScreenX(0);
        t.move(50, 0);
        assertEquals(before + 50, t.toScreenX(0), 1e-6);
    }

    @Test
    void testZoomChangesScale() {
        CoordinateTransformer t = new CoordinateTransformer();
        double before = t.getScale();
        t.zoom(2, 0, 0);
        assertEquals(before * 2, t.getScale(), 1e-6);
    }

    @Test
    void testZoomPivotStaysFixed() {
        CoordinateTransformer t = new CoordinateTransformer();
        // точка pivot должна остаться на месте после зума
        double pivotX = 300;
        double pivotY = 200;
        t.zoom(2, pivotX, pivotY);
        // после зума экранная точка pivot не меняется
        // pivot = offsetX + x*scale => x = (pivot - offsetX) / scale
        // проверяем что toScreenX для соответствующей мировой координаты = pivot
        double worldX = (pivotX - 400) / 20.0; // исходные offset=400, scale=20
        assertEquals(pivotX, t.toScreenX(worldX), 1e-6);
    }
}