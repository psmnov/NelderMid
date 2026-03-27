package com.petr.neldermead.visualization;
import com.petr.neldermead.algo.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimplexDrawer {

    public static void drawSimplex(GraphicsContext g, Point[] points, CoordinateTransformer t){

        g.setStroke(Color.RED);
        g.setFill(Color.BLUE);

        for(int i = 0; i < points.length; i++){

            double x1 = t.toScreenX(points[i].getXn(0));
            double y1 = t.toScreenY(points[i].getXn(1));

            g.fillOval(x1, y1, 5, 5);

            double x2 = t.toScreenX(points[(i+1)%points.length].getXn(0));
            double y2 = t.toScreenY(points[(i+1)%points.length].getXn(1));

            g.strokeLine(x1, y1, x2, y2);
        }
    }
}