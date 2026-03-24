package com.petr.neldermead.visualization;

import com.petr.neldermead.algo.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FunctionPlotter {

    public static void drawFunction(GraphicsContext g, MathFunction f){

        for(double x = -10; x <= 10; x += 0.2){
            for(double y = -10; y <= 10; y += 0.2){

                Point p = new Point(new double[]{x,y});
                double value = f.calc(p);

                double color = Math.min(1, value / 10);

                g.setFill(Color.gray(color));

                g.fillRect(
                        transformX(x),
                        transformY(y),
                        2, 2
                );
            }
        }
    }

    private static double transformX(double x){
        return 300 + x * 20;
    }

    private static double transformY(double y){
        return 300 - y * 20;
    }
}