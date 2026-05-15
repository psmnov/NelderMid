package com.petr.neldermead;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.NelderMead;
import com.petr.neldermead.algo.Point;
import com.petr.neldermead.algo.Simplex;
import com.petr.neldermead.parser.FunctionParser;


public class App 
{
    public static void main( String[] args )
    {
        MathFunction f = FunctionParser.parse(
                "(x1-2)^2 + (x2+1)^2",
                2
        );

        Point p1 = f.createPoint(new double[]{3, 3});
        Point p2 = f.createPoint(new double[]{4, 3});
        Point p3 = f.createPoint(new double[]{3, 4});

        Simplex simplex = new Simplex(new Point[]{p1, p2, p3});

        NelderMead nm = new NelderMead(f, simplex);

        Point result = nm.optimizationAlgo();

        System.out.println("минимум найден в точке:");
        System.out.println("x = " + result.getXn(0));
        System.out.println("y = " + result.getXn(1));
        System.out.println("f = " + f.calc(result));

    }


}
