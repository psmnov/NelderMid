package com.petr.neldermead.visualization;
import com.petr.neldermead.algo.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimplexDrawer {

    public static void drawSimplex(GraphicsContext g, Point[] points){

        g.setStroke(Color.RED);
        g.setFill(Color.BLUE);

        for(int i = 0; i < points.length; i++){

            double x1 = transformX(points[i].getXn(0));
            double y1 = transformY(points[i].getXn(1));

            g.fillOval(x1, y1, 5, 5);

            double x2 = transformX(points[(i+1)%points.length].getXn(0));
            double y2 = transformY(points[(i+1)%points.length].getXn(1));

            g.strokeLine(x1, y1, x2, y2);
        }
    }

    private static double transformX(double x){
        return 300 + x * 20;
    }

    private static double transformY(double y){
        return 300 - y * 20;
    }
}