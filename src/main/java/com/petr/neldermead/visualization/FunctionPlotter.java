package com.petr.neldermead.visualization;

import com.petr.neldermead.algo.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FunctionPlotter {

    public static class PlotPoint{

        public final double screenX, screenY, colorValue;

        public PlotPoint(double screenX, double screenY, double colorValue){
            this.screenX = screenX;
            this.screenY = screenY;
            this.colorValue = colorValue;
        }
    }

    public static PlotPoint[] computePlotPoints(MathFunction f, CoordinateTransformer t) {
        int n = (int)((20.0 / 0.2) + 1);
        PlotPoint[] result = new PlotPoint[n * n];
        int idx = 0;

        for (double x = DisplaySettings.FUNCTION_RANGE_MIN; x <= DisplaySettings.FUNCTION_RANGE_MAX; x += DisplaySettings.FUNCTION_RANGE_STEP) {
            for (double y = DisplaySettings.FUNCTION_RANGE_MIN; y <= DisplaySettings.FUNCTION_RANGE_MAX; y += DisplaySettings.FUNCTION_RANGE_STEP) {
                double value = f.calc(f.createPoint(new double[]{x, y}));
                double colorVal = Math.min(1, value / 10);
                result[idx] = new PlotPoint(t.toScreenX(x), t.toScreenY(y), colorVal);
                idx++;
            }
        }

        PlotPoint[] trimmed = new PlotPoint[idx];
        for (int i = 0; i < idx; i++) trimmed[i] = result[i];
        return trimmed;
    }

    public static void drawFunction(GraphicsContext g, MathFunction f, CoordinateTransformer t){
        PlotPoint[] points = computePlotPoints(f, t);
        for (PlotPoint p : points) {
            g.setFill(Color.gray(p.colorValue));  // поле, не метод
            g.fillRect(p.screenX, p.screenY, DisplaySettings.FUNCTION_RECT_SIZE, DisplaySettings.FUNCTION_RECT_SIZE);  // поля, не методы
        }
    }
}