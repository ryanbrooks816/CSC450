package testing;

import csc450lib.calc.PolynomialFunction1D;
import csc450lib.calc.CosFunction1D;
import csc450lib.calc.SinFunction1D;
import csc450lib.calc.RootFunction1D;

public class test2a {

    // Create a few polynomials and test the derivatives and their derivate
    // approximations. Print the results.

    public static void main(String[] args) {
        // Polynomial 1: y = 2x^3 + 4x^2 + 2x
        float[] a = { 0, 2, 4, 2 };
        PolynomialFunction1D p1 = new PolynomialFunction1D(a);
        System.out.println(p1.getExpressionMMA());
        System.out.println("d1 Expression:");
        System.out.println(p1.dfunc(0));
        System.out.println(p1.dfunc(1));
        System.out.println(p1.dfunc(5));
        System.out.println(p1.dfunc(-10));
        System.out.println(p1.dfunc(8));
        System.out.println("d1 Approximation:");
        System.out.println(p1.dfuncApproximation(0));
        System.out.println(p1.dfuncApproximation(1));
        System.out.println(p1.dfuncApproximation(5));
        System.out.println(p1.dfuncApproximation(-10));
        System.out.println(p1.dfuncApproximation(8));
        System.out.println("d2 Expression:");
        System.out.println(p1.d2func(0));
        System.out.println(p1.d2func(1));
        System.out.println(p1.d2func(5));
        System.out.println(p1.d2func(-10));
        System.out.println(p1.d2func(8));
        System.out.println("d2 Approximation:");
        System.out.println(p1.d2funcApproximation(0));
        System.out.println(p1.d2funcApproximation(1));
        System.out.println(p1.d2funcApproximation(5));
        System.out.println(p1.d2funcApproximation(-10));
        System.out.println(p1.d2funcApproximation(8));
        System.out.println();

        // Polynomial 2: y = -x^5 + x - 8
        float[] b = { -8, 1, 0, 0, 0, -1 };
        PolynomialFunction1D p2 = new PolynomialFunction1D(b);
        System.out.println(p2.getExpressionMMA());
        System.out.println("d1 Expression:");
        System.out.println(p2.dfunc(0));
        System.out.println(p2.dfunc(12));
        System.out.println(p2.dfunc(-2));
        System.out.println(p2.dfunc(-1.5f));
        System.out.println(p2.dfunc(9));
        System.out.println("d1 Approximation:");
        System.out.println(p2.dfuncApproximation(0));
        System.out.println(p2.dfuncApproximation(12));
        System.out.println(p2.dfuncApproximation(-2));
        System.out.println(p2.dfuncApproximation(-1.5f));
        System.out.println(p2.dfuncApproximation(9));
        System.out.println("d2 Expression:");
        System.out.println(p2.d2func(0));
        System.out.println(p2.d2func(12));
        System.out.println(p2.d2func(-2));
        System.out.println(p2.d2func(-1.5f));
        System.out.println(p2.d2func(9));
        System.out.println("d2 Approximation:");
        System.out.println(p2.d2funcApproximation(0));
        System.out.println(p2.d2funcApproximation(12));
        System.out.println(p2.d2funcApproximation(-2));
        System.out.println(p2.d2funcApproximation(-1.5f));
        System.out.println(p2.d2funcApproximation(9));
        System.out.println();

        // Square Root Function: y = sqrt(x)
        RootFunction1D sqrtFunc = new RootFunction1D();
        System.out.println(sqrtFunc.getExpressionMMA());
        System.out.println("d1 Expression:");
        System.out.println(sqrtFunc.dfunc(1));
        System.out.println(sqrtFunc.dfunc(2));
        System.out.println(sqrtFunc.dfunc(9));
        System.out.println(sqrtFunc.dfunc(0.25f));
        System.out.println("d1 Approximation:");
        System.out.println(sqrtFunc.dfuncApproximation(1));
        System.out.println(sqrtFunc.dfuncApproximation(2));
        System.out.println(sqrtFunc.dfuncApproximation(9));
        System.out.println(sqrtFunc.dfuncApproximation(0.25f));
        System.out.println("d2 Expression:");
        System.out.println(sqrtFunc.d2func(1));
        System.out.println(sqrtFunc.d2func(2));
        System.out.println(sqrtFunc.d2func(9));
        System.out.println(sqrtFunc.d2func(0.25f));
        System.out.println("d2 Approximation:");
        System.out.println(sqrtFunc.d2funcApproximation(1));
        System.out.println(sqrtFunc.d2funcApproximation(2));
        System.out.println(sqrtFunc.d2funcApproximation(9));
        System.out.println(sqrtFunc.d2funcApproximation(0.25f));
        System.out.println();

        // Cube Root Function: y = cbrt(x)
        RootFunction1D cbrtFunc = new RootFunction1D(3);
        System.out.println(cbrtFunc.getExpressionMMA());
        System.out.println("d1 Expression:");
        System.out.println(cbrtFunc.dfunc(1));
        System.out.println(cbrtFunc.dfunc(4));
        System.out.println(cbrtFunc.dfunc(16));
        System.out.println(cbrtFunc.dfunc(0.5f));
        System.out.println("d1 Approximation:");
        System.out.println(cbrtFunc.dfuncApproximation(1));
        System.out.println(cbrtFunc.dfuncApproximation(4));
        System.out.println(cbrtFunc.dfuncApproximation(16));
        System.out.println(cbrtFunc.dfuncApproximation(0.5f));
        System.out.println("d2 Expression:");
        System.out.println(cbrtFunc.d2func(1));
        System.out.println(cbrtFunc.d2func(4));
        System.out.println(cbrtFunc.d2func(16));
        System.out.println(cbrtFunc.d2func(0.5f));
        System.out.println("d2 Approximation:");
        System.out.println(cbrtFunc.d2funcApproximation(1));
        System.out.println(cbrtFunc.d2funcApproximation(4));
        System.out.println(cbrtFunc.d2funcApproximation(16));
        System.out.println(cbrtFunc.d2funcApproximation(0.5f));
        System.out.println();

        // Sine Function: y = sin(x)
        SinFunction1D sinFunc = new SinFunction1D();
        System.out.println(sinFunc.getExpressionMMA());
        System.out.println("d1 Expression:");
        System.out.println(sinFunc.dfunc(0));
        System.out.println(sinFunc.dfunc((float) (Math.PI / 2)));
        System.out.println(sinFunc.dfunc((float) Math.PI));
        System.out.println(sinFunc.dfunc((float) (3 * Math.PI / 2)));
        System.out.println(sinFunc.dfunc((float) (2 * Math.PI)));
        System.out.println("d1 Approximation:");
        System.out.println(sinFunc.dfuncApproximation(0));
        System.out.println(sinFunc.dfuncApproximation((float) (Math.PI / 2)));
        System.out.println(sinFunc.dfuncApproximation((float) Math.PI));
        System.out.println(sinFunc.dfuncApproximation((float) (3 * Math.PI / 2)));
        System.out.println(sinFunc.dfuncApproximation((float) (2 * Math.PI)));
        System.out.println("d2 Expression:");
        System.out.println(sinFunc.d2func(0));
        System.out.println(sinFunc.d2func((float) (Math.PI / 2)));
        System.out.println(sinFunc.d2func((float) Math.PI));
        System.out.println(sinFunc.d2func((float) (3 * Math.PI / 2)));
        System.out.println(sinFunc.d2func((float) (2 * Math.PI)));
        System.out.println("d2 Approximation:");
        System.out.println(sinFunc.d2funcApproximation(0));
        System.out.println(sinFunc.d2funcApproximation((float) (Math.PI / 2)));
        System.out.println(sinFunc.d2funcApproximation((float) Math.PI));
        System.out.println(sinFunc.d2funcApproximation((float) (3 * Math.PI / 2)));
        System.out.println(sinFunc.d2funcApproximation((float) (2 * Math.PI)));
        System.out.println();

        // Cosine Function: y = cos(x)
        CosFunction1D cosFunc = new CosFunction1D();
        System.out.println(cosFunc.getExpressionMMA());
        System.out.println("d1 Expression:");
        System.out.println(cosFunc.dfunc(0));
        System.out.println(cosFunc.dfunc((float) (Math.PI / 2)));
        System.out.println(cosFunc.dfunc((float) Math.PI));
        System.out.println(cosFunc.dfunc((float) (3 * Math.PI / 2)));
        System.out.println(cosFunc.dfunc((float) (2 * Math.PI)));
        System.out.println("d1 Approximation:");
        System.out.println(cosFunc.dfuncApproximation(0));
        System.out.println(cosFunc.dfuncApproximation((float) (Math.PI / 2)));
        System.out.println(cosFunc.dfuncApproximation((float) Math.PI));
        System.out.println(cosFunc.dfuncApproximation((float) (3 * Math.PI / 2)));
        System.out.println(cosFunc.dfuncApproximation((float) (2 * Math.PI)));
        System.out.println("d2 Expression:");
        System.out.println(cosFunc.d2func(0));
        System.out.println(cosFunc.d2func((float) (Math.PI / 2)));
        System.out.println(cosFunc.d2func((float) Math.PI));
        System.out.println(cosFunc.d2func((float) (3 * Math.PI / 2)));
        System.out.println(cosFunc.d2func((float) (2 * Math.PI)));
        System.out.println("d2 Approximation:");
        System.out.println(cosFunc.d2funcApproximation(0));
        System.out.println(cosFunc.d2funcApproximation((float) (Math.PI / 2)));
        System.out.println(cosFunc.d2funcApproximation((float) Math.PI));
        System.out.println(cosFunc.d2funcApproximation((float) (3 * Math.PI / 2)));
        System.out.println(cosFunc.d2funcApproximation((float) (2 * Math.PI)));
    }
}
