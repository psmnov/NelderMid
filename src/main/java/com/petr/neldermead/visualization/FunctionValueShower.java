package com.petr.neldermead.visualization;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FunctionValueShower {
    public static void showValue(GraphicsContext g, Point p, MathFunction f){

        double value = f.calc(p);

        g.setFill(Color.BLACK);
        g.setFont(new Font(16));

        String text = "Result: " + p + "\n  f = " + value;

        g.fillText(text, 50, 50); // низ canvas
    }
}
