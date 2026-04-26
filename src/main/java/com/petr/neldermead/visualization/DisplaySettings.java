package com.petr.neldermead.visualization;

import javafx.scene.paint.Color;

public class DisplaySettings {

    // Цвета симплекса
    public static final Color SIMPLEX_STROKE = Color.RED;
    public static final Color SIMPLEX_FILL = Color.BLUE;
    public static final Color SIMPLEX_LAST_COLOR = Color.GREEN;

    // Размер точки симплекса
    public static final double POINT_SIZE = 5;

    // Размер прямоугольника функции
    public static final double FUNCTION_RECT_SIZE = 2;

    // Диапазон отрисовки функции
    public static final double FUNCTION_RANGE_MIN = -10;
    public static final double FUNCTION_RANGE_MAX = 10;
    public static final double FUNCTION_RANGE_STEP = 0.2;

    // График значений
    public static final double CHART_START_X = 50;
    public static final double CHART_START_Y = 500;
    public static final double CHART_BAR_WIDTH = 40;
    public static final double CHART_MAX_HEIGHT = 200;

    // Начальный симплекс
    public static final double SIMPLEX_RADIUS = 3.0;
    public static final double SIMPLEX_STEP_MIN = 1.0;
}