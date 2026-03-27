package com.petr.neldermead.ui;

import com.petr.neldermead.controller.MainController;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MainView {

    private VBox root = new VBox();//упорядочивает вложенные узлы вертикально, можно задать отступ между элементами

    public MainView(){

        TextField functionInput = new TextField("(x1-2)^2 + (x2+1)^2");
        TextField dimensionInput = new TextField("2");

        Button runButton = new Button("Run");
        Button clearButton = new Button("Clear");
        //холст для рисования
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext g = canvas.getGraphicsContext2D();//предоставляет методы для рисования  на холсте

        MainController controller = new MainController(g);

        runButton.setOnAction(e -> {
            String expr = functionInput.getText();
            int dim = Integer.parseInt(dimensionInput.getText());

            controller.run(expr, dim);
        });
        clearButton.setOnAction(e -> {
            g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
        canvas.setOnScroll(e -> {

            double factor = (e.getDeltaY() > 0) ? 1.1 : 0.9;

            controller.zoom(factor, e.getX(), e.getY());
        });
        final double[] last = new double[2];

        canvas.setOnMousePressed(e -> {
            last[0] = e.getX();
            last[1] = e.getY();
        });

        canvas.setOnMouseDragged(e -> {
            double dx = e.getX() - last[0];
            double dy = e.getY() - last[1];

            controller.move(dx, dy);

            last[0] = e.getX();
            last[1] = e.getY();
        });
        //кидаем в root все что насоздавали
        root.getChildren().addAll(functionInput, dimensionInput, runButton, clearButton, canvas);
    }

    public VBox getRoot(){
        return root;
    }
}