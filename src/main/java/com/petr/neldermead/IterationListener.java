package com.petr.neldermead;

import com.petr.neldermead.algo.Point;

public interface IterationListener {
    void onIteration(Point[] points);
}
