package com.petr.neldermead.algo;

import com.petr.neldermead.AlgorithmEndedListener;
import com.petr.neldermead.IterationListener;

public class NelderMead {
    private MathFunction function;
    private IterationListener listener;
    private AlgorithmEndedListener algorithmEndedListener;


    private final int maxIterations = 100000;
    private final double epsilon = 0.000001;

    Simplex simplex;
    public NelderMead(MathFunction function, Simplex simplex){
        this.function = function;
        this.simplex = simplex;
    }
    public Point optimizationAlgo(){
        //simplex.calcAll(function);

        //посчитать значение функции в каждой точке и отсортировать. best, good, worst

        int i = 0;
        while(i<maxIterations) {
            simplex.sort(function);

            Point best = simplex.getV1Best();
            Point good = simplex.getV2Worse();
            Point worst = simplex.getV3Worst();

            double fBest = function.calc(best);
            double fGood = function.calc(good);
            double fWorst = function.calc(worst);

            if(Math.abs(fBest - fWorst) < epsilon) break;

            Point middle = simplex.getMiddlePoint();
            //отражение
            Point xR = simplex.getXr(middle, worst);

            double fXR = function.calc(xR);

            if (fXR < fBest) {
                //растяжение
                Point xE =simplex.getXe(middle, xR);
                double fXE = function.calc(xE);


                if (fXE < fBest) simplex.replaceWorst(xE, function);
                else simplex.replaceWorst(xR, function);
            }
            else if (fXR < fGood && fXR > fBest) {
                simplex.replaceWorst(xR, function);
            }

            else  {
                //сжатие
                Point xS = simplex.getXs(middle, worst);
                double fXS = function.calc(xS);

                if (fXS < fWorst) simplex.replaceWorst(xS, function);
                else {
                    simplex.compression();
                }
            }
            i++;
            if(listener != null){
                listener.onIteration(simplex.copyPoints());
            }
        }

        simplex.sort(function);
        if(algorithmEndedListener != null){
            algorithmEndedListener.onAlgoEnd(simplex.getV1Best());
        }
        return simplex.getV1Best();
    }
    public void setListener(IterationListener listener){
        this.listener = listener;
    }
    public void setAlgorithmEndedListener(AlgorithmEndedListener algorithmEndedListener){
        this.algorithmEndedListener = algorithmEndedListener;
    }

}
