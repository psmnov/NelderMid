package com.petr.neldermead.parser;

import com.petr.neldermead.algo.MathFunction;
import com.petr.neldermead.algo.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionParserTest {

    @Test
    void testParseSimpleParaboloid() {
        MathFunction f = FunctionParser.parse("(x1-2)^2 + (x2+1)^2", 2);
        Point p = f.createPoint(new double[]{2, -1});
        assertEquals(0, p.getValue(), 1e-9);
    }

    @Test
    void testParseNonZeroValue() {
        MathFunction f = FunctionParser.parse("x1^2 + x2^2", 2);
        Point p = f.createPoint(new double[]{3, 4});
        assertEquals(25, p.getValue(), 1e-9);
    }

    @Test
    void testParse3D() {
        MathFunction f = FunctionParser.parse("x1^2 + x2^2 + x3^2", 3);
        Point p = f.createPoint(new double[]{1, 2, 3});
        assertEquals(14, p.getValue(), 1e-9);
    }

    @Test
    void testParse1D() {
        MathFunction f = FunctionParser.parse("x1^2", 1);
        Point p = f.createPoint(new double[]{5});
        assertEquals(25, p.getValue(), 1e-9);
    }
}