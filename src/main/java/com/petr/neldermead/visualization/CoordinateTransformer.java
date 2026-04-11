package com.petr.neldermead.visualization;

public class CoordinateTransformer {
    private double scale = 20;
    private double offsetX = 400; //офсеты это условный 0 0, где находится курсор, куда мы зумим
    private double offsetY = 400;
    //масштабируем, потом двигаем в центр точку из под функции
    public double toScreenX(double x){
        return offsetX + x * scale;
    }
    public double toScreenY(double y){
        return offsetY - y * scale;
    }
    //пивот - точка которая остается на месте во вермя зума
    public void zoom(double factor, double pivotX, double pivotY){
        scale *= factor;
        //(pivotX - offsetX) это расстояние от offset до pivot, масштабируется соответствующим образом.
        //итого офсет двигается так, чтобы пивот не менялся

        offsetX = pivotX - (pivotX - offsetX) * factor;
        offsetY = pivotY - (pivotY - offsetY) * factor;
    }
    //сдвигает всю систему координат
    public void move(double dx, double dy){
        offsetX += dx;
        offsetY += dy;
    }
    public double getScale(){
        return scale;
    }
}
