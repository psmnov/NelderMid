package com.petr.neldermead.algo;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

public class Simplex {
    private final Point[] points;

    private double alpha = 1;
    private double betta = 0.5;
    private double  gamma = 2;

    public Simplex(Point[] points){
        this.points = points;
    }
    //так делать нельзя
    /*public Point[] getPoints() {
        return points;
    }*/
    public void replaceWorst(Point p, MathFunction function){

        points[points.length - 1] = p;
    }

    public Point getExactPoint(int i){
        return points[i];
    }
    public void sort(MathFunction f){

        Comparator<Point> byValue = Comparator.comparingDouble(f::calc);
        Arrays.sort(points, byValue);


    }
    public int dimension(){
        return points[0].getX().length;
    }
    //симплекс неменяемый объект
    public Point getV3Worst(){
        return points[points.length - 1];
    }
    public Point getV1Best(){
        return points[0];
    }
    public Point getV2Worse(){
        return points[points.length - 2];
    }

    public Point getMiddlePoint(){
        int size = this.dimension();
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
        double[] sum = new double[this.dimension()];
        for(int i = 0; i<this.dimension(); i++){
            sum[i] = (point1.getXn(i) + point2.getXn(i));
        }
        return new Point(sum);
    }
    public Point multiply(@org.jetbrains.annotations.NotNull Point point, double k){
        double[] temp = new double[this.dimension()];
        for(int i = 0; i<this.dimension(); i++){
            temp[i] = (point.getXn(i)*k);
        }
        return new Point(temp);
    }
//перенести отражение сюда же и растяжение тоже
    public void compression(){
        for(int i = 1; i<points.length; i++){
            points[i] = this.plus(points[0], this.multiply(this.plus(points[i], this.multiply(points[0], -1)), 0.5));
        }
    }



    public Point getXr(Point middle, Point worst){
        return this.plus(middle, this.multiply(this.plus(middle, this.multiply(worst, -1)), alpha));
    }
    public Point getXe(Point middle, Point xR){
        return this.plus(middle, this.multiply(this.plus(xR, this.multiply(middle, -1)), gamma));
    }
    public Point getXs(Point middle, Point worst){
        return this.plus(this.multiply(middle, (1 - betta)), this.multiply(worst, betta));
    }
    public Point[] copyPoints(){
        Point[] copy = new Point[points.length];

        for(int i = 0; i < points.length; i++){
            copy[i] = new Point(points[i].getX());
        }

        return copy;
    }

}
