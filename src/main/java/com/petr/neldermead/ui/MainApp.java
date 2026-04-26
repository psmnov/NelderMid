package com.petr.neldermead.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        //содержит все элементы управления. Stage - окно.
        MainView view = new MainView();

        Scene scene = new Scene(view.getRoot(), 1520, 800);//получаем элементы из view
        //scene это текущее содержимое экрорана
        stage.setTitle("Nelder-Mead");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
