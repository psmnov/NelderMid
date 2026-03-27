package com.petr.neldermead.visualization;

public class CoordinateTransformer {
    private double scale = 20;
    private double offsetX = 300;
    private double offsetY = 300;

    public double toScreenX(double x){
        return offsetX + x * scale;
    }
    public double toScreenY(double y){
        return offsetY - y * scale;
    }

    public void zoom(double factor, double pivotX, double pivotY){
        scale *= factor;
        offsetX = pivotX - (pivotX - offsetX) * factor;
        offsetY = pivotY - (pivotY - offsetY) * factor;
    }

    public void move(double dx, double dy){
        offsetX += dx;
        offsetY += dy;
    }
    public double getScale(){
        return scale;
    }
}
