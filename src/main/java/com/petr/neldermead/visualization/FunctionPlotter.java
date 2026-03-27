package com.petr.neldermead.visualization;

import com.petr.neldermead.algo.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FunctionPlotter {

    public static void drawFunction(GraphicsContext g, MathFunction f, CoordinateTransformer t){
        //маленью область охватываем от -10 до 10, масштабируем до -100 - 500
        for(double x = -10; x <= 10; x += 0.2){
            for(double y = -10; y <= 10; y += 0.2){

                Point p = new Point(new double[]{x,y});
                double value = f.calc(p);
                double color = Math.min(1, value / 10);
                //чем меньше значение функции тем темнее цвет точек
                g.setFill(Color.gray(color));
                double screenX = t.toScreenX(x);
                double screenY = t.toScreenY(y);

                g.fillRect(screenX, screenY, 2, 2);
            }
        }
    }
}