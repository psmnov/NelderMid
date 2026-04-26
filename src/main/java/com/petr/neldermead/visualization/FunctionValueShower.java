package com.petr.neldermead.visualization;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class FunctionValueShower {
    public static void showValue(GraphicsContext g, Point p, MathFunction f){

        double value = f.calc(p);

        g.setFill(Color.BLACK);
        g.setFont(new Font(16));

        String text = "Result: " + p + "\n  f = " + value;

        g.fillText(text, 50, 50);
    }
    public static void drawChart(GraphicsContext g, List<Double> values){

        double startX = DisplaySettings.CHART_START_X;
        double startY = DisplaySettings.CHART_START_Y;
        double width = DisplaySettings.CHART_BAR_WIDTH;

        double max = values.stream().mapToDouble(v -> v).max().orElse(1);

        for(int i = 0; i < values.size(); i++){

            double value = values.get(i);

            double height = Math.log(value + 1) / Math.log(max + 1) * DisplaySettings.CHART_MAX_HEIGHT;

            double x = startX + i * (width + 2);
            double y = startY - height;

            g.fillText("f(x)", 5, 20);
            g.setFill(Color.BLUE);
            g.fillRect(x, y, width, height);

        }
    }
}
