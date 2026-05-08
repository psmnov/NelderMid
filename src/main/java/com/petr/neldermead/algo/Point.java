package com.petr.neldermead.algo;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Point {
    private final double[] X;
    private final double value;

    public Point(double[] X, double value){ //создать без значения не получится, стандартно значение 0 задаём
        this.X = Arrays.copyOf(X, X.length);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public double[] getX() {
        return Arrays.copyOf(X, X.length);
    }

    public double getXn(int i) {
        return X[i];
    }

    public int getDimension() {
        return X.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(X) + " f= " + value;
    }

}
