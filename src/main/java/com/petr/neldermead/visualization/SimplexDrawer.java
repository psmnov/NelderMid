package com.petr.neldermead.visualization;
import com.petr.neldermead.algo.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimplexDrawer {

    public static class coordSimplex{

        public final double[] x1,y1,x2,y2;

        public coordSimplex(double[] x1, double[] y1, double[] x2, double[] y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static coordSimplex computeData(Point[] points, CoordinateTransformer t) {
        int n = points.length;
        double[] sx1 = new double[n];
        double[] sy1 = new double[n];
        double[] sx2 = new double[n];
        double[] sy2 = new double[n];

        for (int i = 0; i < n; i++) {
            sx1[i] = t.toScreenX(points[i].getXn(0));
            sy1[i] = t.toScreenY(points[i].getXn(1));
            sx2[i] = t.toScreenX(points[(i + 1) % n].getXn(0));
            sy2[i] = t.toScreenY(points[(i + 1) % n].getXn(1));
        }

        return new coordSimplex(sx1, sy1, sx2, sy2);
    }

    public static void drawSimplex(GraphicsContext g, Point[] points, CoordinateTransformer t, boolean isLast){
        coordSimplex data = computeData(points, t);

        g.setStroke(isLast ? DisplaySettings.SIMPLEX_LAST_COLOR : DisplaySettings.SIMPLEX_STROKE);
        g.setFill(isLast ? DisplaySettings.SIMPLEX_LAST_COLOR : DisplaySettings.SIMPLEX_FILL);

        for (int i = 0; i< points.length; i++){
            g.fillOval(data.x1[i], data.y1[i], DisplaySettings.POINT_SIZE, DisplaySettings.POINT_SIZE);
            g.strokeLine(data.x1[i], data.y1[i], data.x2[i], data.y2[i]);
        }
    }

    public static void drawAxes(GraphicsContext g, CoordinateTransformer t, double canvasWidth, double canvasHeight) {
        g.setStroke(Color.BLACK);
        g.setLineWidth(1.5);
        g.setFont(new javafx.scene.text.Font(10));
        g.setFill(Color.BLACK);

        double screenY0 = t.toScreenY(0);
        if (screenY0 >= 0 && screenY0 <= canvasHeight) {
            g.strokeLine(0, screenY0, canvasWidth, screenY0);
        }

        double screenX0 = t.toScreenX(0);
        if (screenX0 >= 0 && screenX0 <= canvasWidth) {
            g.strokeLine(screenX0, 0, screenX0, canvasHeight);
        }

        double scale = t.getScale();
        double step = 5;
        if (scale > 40) step = 1;
        if (scale > 80) step = 0.5;
        if (scale > 160) step = 0.25;

        for (double x = -100; x <= 100; x += step) {
            double sx = t.toScreenX(x);

            g.strokeLine(sx, screenY0 - 4, sx, screenY0 + 4); // черточка
            if (x != 0) {
                g.fillText(formatNumber(x), sx - 5, screenY0 + 15);
            }
        }

        for (double y = -100; y <= 100; y += step) {
            double sy = t.toScreenY(y);

            g.strokeLine(screenX0 - 4, sy, screenX0 + 4, sy); // черточка
            if (y != 0) {
                g.fillText(formatNumber(y), screenX0 + 6, sy + 4);
            }
        }
    }

    private static String formatNumber(double v) {
        return String.format("%.2f", v);
    }
}