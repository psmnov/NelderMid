package com.petr.neldermead.ui;

import com.petr.neldermead.controller.MainController;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MainView {

    private VBox root = new VBox();

    public MainView(){

        TextField functionInput = new TextField("(x1-2)^2 + (x2+1)^2");
        TextField dimensionInput = new TextField("2");

        Button runButton = new Button("Run");

        Canvas canvas = new Canvas(600, 600);
        GraphicsContext g = canvas.getGraphicsContext2D();

        MainController controller = new MainController(g);

        runButton.setOnAction(e -> {
            String expr = functionInput.getText();
            int dim = Integer.parseInt(dimensionInput.getText());

            controller.run(expr, dim);
        });

        root.getChildren().addAll(functionInput, dimensionInput, runButton, canvas);
    }

    public VBox getRoot(){
        return root;
    }
}