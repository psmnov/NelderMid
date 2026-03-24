package com.petr.neldermead;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.NelderMead;
import com.petr.neldermead.algo.Point;
import com.petr.neldermead.algo.Simplex;
import com.petr.neldermead.parser.FunctionParser;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class App 
{
    public static void main( String[] args )
    {

        MathFunction function = p-> Math.pow((p.getXn(0) - 2), 2) + Math.pow((p.getXn(1)+1), 2);
        //MathFunction function1 = x->Math.pow(x[0], 2) + 2*Math.pow(x[1], 2) + x[0]*x[1]*3;
        MathFunction function1 = p->Math.pow(p.getXn(0), 2) + 2*Math.pow(p.getXn(1), 2) + p.getXn(0)*p.getXn(1)*3;
        MathFunction function2 = p-> Math.pow(1-p.getXn(0), 2) + 100*Math.pow(p.getXn(1) - p.getXn(1)*p.getXn(0), 2);
        MathFunction function3 = p->Math.pow(p.getXn(0) - 2, 2) + Math.pow(p.getXn(1) + 1, 2) + Math.pow(p.getXn(2) - 3, 2);
        MathFunction f = FunctionParser.parse(
                "(x1-2)^2 + (x2+1)^2",
                2
        );

        Point p1 = new Point(new double[]{3, 3});
        Point p2 = new Point(new double[]{4, 3});
        Point p3 = new Point(new double[]{3, 4});

        //System.out.println(function.calc(new double[]{2,-1}));
        Simplex simplex = new Simplex(new Point[]{p1, p2, p3});

        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        System.out.println("минимум найден в точке:");
        System.out.println("x = " + result.getXn(0));
        System.out.println("y = " + result.getXn(1));
        System.out.println("f = " + f.calc(result));

    }


}
