package testing;

import csc450lib.calc.DomainOfDefinition;
import csc450lib.calc.FunctionComposition1D;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.calc.CosFunction1D;
import csc450lib.calc.SinFunction1D;

public class test1 {

    public static void main(String[] args) {
        // Polynomial 1: y = 2x^3 + 4x^2 + 2x
        float[] a = { 0, 2, 4, 2 };
        PolynomialFunction1D p1 = new PolynomialFunction1D(a);
        System.out.println(p1.getExpressionMMA());
        System.out.println(p1.func(0)); // Expected: 0.0
        System.out.println(p1.func(1)); // Expected: 8.0
        System.out.println(p1.func(2)); // Expected: 36.0
        System.out.println(p1.func(-1)); // Expected: 0.0
        System.out.println(p1.func(-2)); // Expected: -4.0

        // Polynomial 2: y = x^5 + x + 8
        float[] b = { 8, 1, 0, 0, 0, 1 };
        PolynomialFunction1D p2 = new PolynomialFunction1D(b);
        System.out.println(p2.getExpressionMMA());
        System.out.println(p2.func(0));
        System.out.println(p2.func(1));
        System.out.println(p2.func(2));
        System.out.println(p2.func(-1));
        System.out.println(p2.func(-2));

        // Polynomial 3: y = 3x^2 + 2x
        float[] c = { 0, 2, 3 };
        PolynomialFunction1D p3 = new PolynomialFunction1D(c);
        System.out.println(p3.getExpressionMMA());
        System.out.println(p3.func(0));
        System.out.println(p3.func(1));
        System.out.println(p3.func(2));
        System.out.println(p3.func(-1));
        System.out.println(p3.func(-2));

        // Sin 1: y = sin(x)
        SinFunction1D s1 = new SinFunction1D(1, 1, 0, 0);
        System.out.println(s1.getExpressionMMA());
        System.out.println(s1.func(0));
        System.out.println(s1.func((float) Math.PI));
        System.out.println(s1.func((float) Math.PI / 2));

        // Sin 2: y = 3 * sin(1/3x) + 2
        SinFunction1D s2 = new SinFunction1D(3, (float) 1 / 3, 0, 2);
        System.out.println(s2.getExpressionMMA());
        System.out.println(s2.func(0));
        System.out.println(s2.func((float) Math.PI));
        System.out.println(s2.func((float) Math.PI / 2));

        // Cos 1: y = cos(x)
        CosFunction1D c1 = new CosFunction1D(1, 1, 0, 0);
        System.out.println(c1.getExpressionMMA());
        System.out.println(c1.func(0));
        System.out.println(c1.func((float) Math.PI));
        System.out.println(c1.func((float) Math.PI / 2));

        // Cos 2: y = 4 * cos(1/5x) + 5
        CosFunction1D c2 = new CosFunction1D(4, (float) 1 / 5, 0, 5);
        System.out.println(c2.getExpressionMMA());
        System.out.println(c2.func(0));
        System.out.println(c2.func((float) Math.PI));
        System.out.println(c2.func((float) Math.PI / 2));

        // Testing composition of functions
        float[] e = { 0, 0, 1 };
        PolynomialFunction1D p4 = new PolynomialFunction1D(e);
        SinFunction1D s3 = new SinFunction1D(1, 1, 0, 0);
        FunctionComposition1D fog = new FunctionComposition1D(p4, s3);
        System.out.println(fog.getExpressionMMA());
        System.out.println(fog.func((float) Math.PI / 2));
        System.out.println(fog.func((float) Math.PI));
        System.out.println(fog.func((float) Math.PI * 2));

        // Domain testing
        float[] d = { 0, 0, 0.5f };
        DomainOfDefinition domain = new DomainOfDefinition(-10, 10);
        PolynomialFunction1D p5 = new PolynomialFunction1D(d, domain);
        System.out.println(p5.getExpressionMMA());
        System.out.println(p5.func(4)); // Expected: 8.0
        System.out.println(p5.func(11)); // Throws an exception, function is not defined at evaluation point

        SinFunction1D s4 = new SinFunction1D(1, 1, 0, 0, domain);
        System.out.println(s4.getExpressionMMA());
        System.out.println(s4.func((float) Math.PI)); // Expected: 0.0
        System.out.println(s4.func((float) Math.PI * 2)); // Throws an exception, function is not defined at evaluation
                                                          // point

        CosFunction1D c3 = new CosFunction1D(1, 1, 0, 0, domain);
        System.out.println(c3.getExpressionMMA());
        System.out.println(c3.func((float) Math.PI)); // Expected: -1.0
        System.out.println(c3.func((float) Math.PI * 2)); // Throws an exception, function is not defined at evaluation
                                                          // point
    }
}