package com.petr.neldermead.controller;

import com.petr.neldermead.algo.*;
import com.petr.neldermead.parser.FunctionParser;
import com.petr.neldermead.visualization.*;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private GraphicsContext g;
    private CoordinateTransformer transformer = new CoordinateTransformer();
    private MathFunction function;
    private Point[] lastSimplex;
    private int dimension;
    private List<Point[]> history = new ArrayList<>();
    private Point finalPoint;

    public MainController(GraphicsContext g){
        this.g = g;
    }

    public void run(String expr, int dimension){
        g.clearRect(0, 0, 600, 600);
        this.function = FunctionParser.parse(expr, dimension);
        this.dimension = dimension;
        Point[] simplexPoints = createSimplex(dimension);

        Simplex simplex = new Simplex(simplexPoints);
        NelderMead nm = new NelderMead(function, simplex);

        if(dimension == 2){
            FunctionPlotter.drawFunction(g, function, transformer);//рисует на той же панели что и показывает затем шаги симплекса
        }
        //имплементируем лямбда функцию
        nm.setListener(points -> {
            if(dimension == 2){
                SimplexDrawer.drawSimplex(g, points, transformer);
            }
        });
        nm.setListener(points -> {
            history.add(points);
            redraw();
        });
        nm.setAlgorithmEndedListener(point -> {
            this.finalPoint = point;
            FunctionValueShower.showValue(g, point, function);

        });

        nm.optimizationAlgo();
    }

    private Point[] createSimplex(int dim){
        Point[] points = new Point[dim + 1];

        double[] base = new double[dim];
        //в тупую первую точку 5;5;5...5;
        for(int i = 0; i < dim; i++) base[i] = 5;

        points[0] = new Point(base);

        for(int i = 1; i <= dim; i++){
            double[] p = base.clone();
            p[i-1] += 1;
            points[i] = new Point(p);
        }//получим витоге points = {(6, 5 5 ..), (5, 6 5..), (5, 5, 6..)}

        return points;
    }
    public void zoom(double factor, double x, double y){
        transformer.zoom(factor, x, y);
        redraw();
    }
    public void move(double dx, double dy){
        transformer.move(dx, dy);
        redraw();
    }
    private void redraw(){

        g.clearRect(0, 0, 600, 600);

        for(Point[] simplex : history){
            SimplexDrawer.drawSimplex(g, simplex, transformer);
        }
        FunctionPlotter.drawFunction(g, function, transformer);
        if(this.finalPoint != null) FunctionValueShower.showValue(g, finalPoint, function);

    }
}