package com.petr.neldermead.algo;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Point {
    private final double[] X;

    public Point(double[] X){
        this.X = Arrays.copyOf(X, X.length);
    }

    public double[] getX() {
        return Arrays.copyOf(X, X.length);
    }

    public double getXn(int i){
        return X[i];
    }


    @Override
    public String toString() {
        return Arrays.toString(X);
    }

}
