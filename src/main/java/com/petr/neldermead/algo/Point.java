package com.petr.neldermead.algo;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Point {
    private double[] X;
    private double functionValueInPoint;

    public Point(double[] X){
        this.X = Arrays.copyOf(X, X.length);
    }

    public double getFunctionValueInPoint() {
        return functionValueInPoint;
    }

    public double[] getX() {
        return Arrays.copyOf(X, X.length);
    }
    public double getXn(int i){
        return X[i];
    }
    public void calcFunctionValue(@NotNull MathFunction function){
        functionValueInPoint = function.calc(X);
    }

    @Override
    public String toString() {
        return Arrays.toString(X) + " f = "  + functionValueInPoint;
    }

}
