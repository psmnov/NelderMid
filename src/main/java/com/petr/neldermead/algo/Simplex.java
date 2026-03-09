package com.petr.neldermead.algo;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

public class Simplex {
    private Point[] points;

    public Simplex(Point[] points){
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public Point getExactPoint(int i){
        return points[i];
    }
    public void sort(){
        Comparator<Point> byValue = Comparator.comparingDouble(Point::getFunctionValueInPoint);
        Arrays.sort(points, byValue);
    }
    public void calcAll(MathFunction function){
        for(Point p: points){
            p.calcFunctionValue(function);
        }
    }
    public Point getV3Worst(){
        return points[points.length - 1];
    }
    public Point getV1Best(){
        return points[0];
    }
    public Point getV2Worse(){
        return points[points.length - 2];
    }
    public Point getMiddlePoint(@NotNull Point point1, Point point2){
        double temp[] = new double[point1.getX().length];
        for(int i = 0; i<point1.getX().length; i++){
            temp[i] = (point1.getXn(i) + point2.getXn(i))/2;
        }
        return new Point(temp);
    }
    public Point plus(@NotNull Point point1, Point point2){
        double sum[] = new double[point1.getX().length];
        for(int i = 0; i<point1.getX().length; i++){
            sum[i] = (point1.getXn(i) + point2.getXn(i));
        }
        return new Point(sum);
    }
    public Point multiply(@org.jetbrains.annotations.NotNull Point point, double k){
        double temp[] = new double[point.getX().length];
        for(int i = 0; i<point.getX().length; i++){
            temp[i] = (point.getXn(i)*k);
        }
        return new Point(temp);
    }
    public void compression(){
        for(int i = 1; i<points.length; i++){
            points[i] = this.plus(points[0], this.multiply(this.plus(points[i], this.multiply(points[0], -1)), 0.5));
        }
    }

}
