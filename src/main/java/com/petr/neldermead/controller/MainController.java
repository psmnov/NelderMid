package com.petr.neldermead.controller;

import com.petr.neldermead.algo.*;
import com.petr.neldermead.parser.FunctionParser;
import com.petr.neldermead.visualization.*;

import javafx.scene.canvas.GraphicsContext;

public class MainController {

    private GraphicsContext g;

    public MainController(GraphicsContext g){
        this.g = g;
    }

    public void run(String expr, int dimension){

        MathFunction function = FunctionParser.parse(expr, dimension);

        Point[] simplexPoints = createSimplex(dimension);

        Simplex simplex = new Simplex(simplexPoints);
        NelderMead nm = new NelderMead(function, simplex);

        if(dimension == 2){
            FunctionPlotter.drawFunction(g, function);
        }

        nm.setListener(points -> {
            if(dimension == 2){
                SimplexDrawer.drawSimplex(g, points);
            }
        });

        nm.optimizationAlgo();
    }

    private Point[] createSimplex(int dim){
        Point[] points = new Point[dim + 1];

        double[] base = new double[dim];
        for(int i = 0; i < dim; i++) base[i] = 5;

        points[0] = new Point(base);

        for(int i = 1; i <= dim; i++){
            double[] p = base.clone();
            p[i-1] += 1;
            points[i] = new Point(p);
        }

        return points;
    }
}