package com.petr.neldermead;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.NelderMead;
import com.petr.neldermead.algo.Point;
import com.petr.neldermead.algo.Simplex;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        MathFunction function = x -> Math.pow((x[0] - 2), 2) + Math.pow((x[1]+1), 2);
        //MathFunction function1 = x->Math.pow(x[0], 2) + 2*Math.pow(x[1], 2) + x[0]*x[1]*3;
        MathFunction function1 = x->Math.pow(x[0], 2) + 2*Math.pow(x[1], 2) + x[0]*x[1]*3;
        MathFunction function2 = x-> Math.pow(1-x[0], 2) + 100*Math.pow(x[1] - x[0]*x[0], 2);
        MathFunction function3 = x->Math.pow(x[0] - 2, 2) + Math.pow(x[1] + 1, 2) + Math.pow(x[2] - 3, 2);

        Point p1 = new Point(new double[]{3, 3});
        Point p2 = new Point(new double[]{4, 3});
        Point p3 = new Point(new double[]{3, 4});

        //System.out.println(function.calc(new double[]{2,-1}));
        Simplex simplex = new Simplex(new Point[]{p1, p2, p3});

        NelderMead nm = new NelderMead(function2, simplex);

        Point result = nm.optimizationAlgo();

        System.out.println("минимум найден в точке:");
        System.out.println("x = " + result.getXn(0));
        System.out.println("y = " + result.getXn(1));
        System.out.println("f = " + result.getFunctionValueInPoint());
    }
}
