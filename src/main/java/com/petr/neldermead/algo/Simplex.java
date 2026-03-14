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
        for(Point p : points){
            System.out.println("Before sort: " + p);
        }
        Comparator<Point> byValue = Comparator.comparingDouble(Point::getFunctionValueInPoint);
        Arrays.sort(points, byValue);
        for(Point p : points){
            System.out.println("After sort: " + p);
        }
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
    //ошибка центр должен считаться по всем точкам, не по двум
    public Point getMiddlePoint(){
        int size = points[0].getX().length;
        int n = points.length - 1;
        double[] mid = new double[size];
        for(int i = 0; i<points.length - 1; i++){
            for(int j = 0; j<size; j++){
                mid[j] += (points[i].getXn(j))/n;
            }
        }
        return new Point(mid);
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
