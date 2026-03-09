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

        MathFunction function = x ->
                Math.pow(x[0] - 2, 2) +
                        Math.pow(x[1] + 1, 2);

        Point p1 = new Point(new double[]{3, 3});
        Point p2 = new Point(new double[]{4, 3});
        Point p3 = new Point(new double[]{3, 4});

        Simplex simplex = new Simplex(new Point[]{p1, p2, p3});

        NelderMead nm = new NelderMead(function, simplex);

        Point result = nm.optimizationAlgo();

        System.out.println("Минимум найден в точке:");
        System.out.println("x = " + result.getXn(0));
        System.out.println("y = " + result.getXn(1));
        System.out.println("f(x,y) = " + result.getFunctionValueInPoint());
    }
}
