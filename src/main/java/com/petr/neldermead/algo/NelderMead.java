package com.petr.neldermead.algo;

public class NelderMead {
    private MathFunction function;


    private double alpha = 1;
    private double betta = 0.5;
    private double gamma = 2;

    private final int maxIterations = 1000000;
    private final double epsilon = 0.00000001;

    Simplex simplex;
    public NelderMead(MathFunction function, Simplex simplex){
        this.function = function;
        this.simplex = simplex;
    }
    public Point optimizationAlgo(){
        simplex.calcAll(function);

        //посчитать значение функции в каждой точке и отсортировать. best, good, worst
        simplex.sort();
        int i = 0;
        while(i<maxIterations) {

            Point best = simplex.getV1Best();
            Point good = simplex.getV2Worse();
            Point worst = simplex.getV3Worst();

            if(Math.abs(best.getFunctionValueInPoint() - worst.getFunctionValueInPoint()) < epsilon) break;

            Point middle = simplex.getMiddlePoint(good, best);
            Point xR = simplex.plus(
                    middle,
                    simplex.multiply(
                            simplex.plus(middle, simplex.multiply(worst, -1)),
                            alpha
                    )
            );

            xR.calcFunctionValue(function);

            if (xR.getFunctionValueInPoint() < best.getFunctionValueInPoint()) {

                Point xE = simplex.plus(
                        middle,
                        simplex.multiply(
                                simplex.plus(xR, simplex.multiply(middle, -1)),
                                gamma
                        )
                );
                xE.calcFunctionValue(function);

                if (xE.getFunctionValueInPoint() < best.getFunctionValueInPoint()) simplex.getPoints()[simplex.getPoints().length - 1] = xE;
                else simplex.getPoints()[simplex.getPoints().length - 1] = xR;
            }
            else if (xR.getFunctionValueInPoint() < good.getFunctionValueInPoint() && xR.getFunctionValueInPoint() > best.getFunctionValueInPoint()) {
                simplex.getPoints()[simplex.getPoints().length - 1] = xR;
            }

            else  {
                Point xS = simplex.plus(simplex.multiply(middle, (1 - betta)), simplex.multiply(worst, betta));
                xS.calcFunctionValue(function);

                if (xS.getFunctionValueInPoint() < worst.getFunctionValueInPoint()) simplex.getPoints()[simplex.getPoints().length - 1] = xS;
                else {
                    simplex.compression();
                    simplex.calcAll(function);
                }
            }
            i++;
        }
        simplex.sort();
        return simplex.getV1Best();
    }

}
