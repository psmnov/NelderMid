package com.petr.neldermead.algo;

@FunctionalInterface
public interface MathFunction
{
    double calc(Point p);

    default Point createPoint(double[] coords) {
        Point tmp = new Point(coords, 0);
        return new Point(coords, calc(tmp));
    }
}
