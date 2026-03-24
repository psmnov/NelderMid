package com.petr.neldermead.parser;

import com.petr.neldermead.algo.MathFunction;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FunctionParser {
    public static MathFunction parse(String expr, int dimension){

        String[] vars = new String[dimension];

        for(int i = 0; i < dimension; i++){
            vars[i] = "x" + (i+1);
        }

        Expression e = new ExpressionBuilder(expr)
                .variables(vars)
                .build();

        return p -> {
            for(int i = 0; i < dimension; i++){
                e.setVariable("x"+(i+1), p.getXn(i));
            }
            return e.evaluate();
        };
    }
}
