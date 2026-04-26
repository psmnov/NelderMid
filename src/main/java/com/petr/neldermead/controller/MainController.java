package com.petr.neldermead.controller;

import com.petr.neldermead.algo.*;
import com.petr.neldermead.parser.FunctionParser;
import com.petr.neldermead.visualization.*;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainController {

    private GraphicsContext g;
    private GraphicsContext gChart;
    private CoordinateTransformer transformer = new CoordinateTransformer();
    private MathFunction function;
    private Point[] lastSimplex;
    private int dimension;
    private List<Point[]> history = new ArrayList<>();
    private Point finalPoint;
    private List<Double> values = new ArrayList<>();
    private int iterationCounter = 0;

    public MainController(GraphicsContext g, GraphicsContext gChart){
        this.g = g;
        this.gChart = gChart;
    }

    public void run(String expr, int dimension){
        g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
        this.function = FunctionParser.parse(expr, dimension);
        this.dimension = dimension;
        this.history.clear();
        this.values.clear();
        this.iterationCounter = 0;
        this.finalPoint = null;
        Point[] simplexPoints = createSimplex(dimension);

        Simplex simplex = new Simplex(simplexPoints);
        NelderMead nm = new NelderMead(function, simplex);

        if(dimension == 2){
            FunctionPlotter.drawFunction(g, function, transformer);//рисует на той же панели что и показывает затем шаги симплекса
        }
        //имплементируем лямбда функцию
        nm.setListener(points -> {
            if(dimension == 2){
                SimplexDrawer.drawSimplex(g, points, transformer, false);
            }
        });

        nm.setListener(points -> {
            iterationCounter++;

            values.add(points[0].getValue()); // значение уже в Point

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
        double radius = DisplaySettings.SIMPLEX_RADIUS; //будем брать начальные точки случайно внутри радиуса, чтобы не всегда 5.5.5.5...5
        Random random = new Random(); //для тестов можно seed фиксированный поставить

        double[] base = new double[dim];
        for(int i = 0; i < dim; i++){
            base[i] = (random.nextDouble()*2-1)*radius;
        }
        points[0] = function.createPoint(base);

        for(int i = 1; i <= dim; i++){
            double[] p = base.clone();
            p[i-1] += DisplaySettings.SIMPLEX_STEP_MIN + random.nextDouble();
            points[i] = function.createPoint(p);
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
        if(dimension==2){
            g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
            FunctionPlotter.drawFunction(g, function, transformer);
            SimplexDrawer.drawAxes(g, transformer, g.getCanvas().getWidth(), g.getCanvas().getHeight());

            for (int i = 0; i < history.size(); i++) {
                SimplexDrawer.drawSimplex(g, history.get(i), transformer, i == history.size()-1);
            }
            if(this.finalPoint != null) FunctionValueShower.showValue(g, finalPoint, function);
        }

        gChart.clearRect(0, 0, gChart.getCanvas().getWidth(), gChart.getCanvas().getHeight());
        FunctionValueShower.drawChart(gChart, values);

    }
}