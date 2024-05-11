package testing.A3;

import csc450lib.calc.FunctionMultiplication1D;
import csc450lib.calc.Optimizer1D_gss;
import csc450lib.calc.Optimizer1D_parabolic;
import csc450lib.calc.Optimizer1D_steepestDescent;
import csc450lib.calc.OptimizerRecord1D;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.calc.SinFunction1D;

import java.io.FileWriter;
import java.io.IOException;

public class a3_p2_Optimization_tests {
    public static void main(String[] args) {
        OptimizerRecord1D r = function10();
        writeMinMax(r, "output3_f10.csv");
    }

    // Steepest Descent

    public static OptimizerRecord1D function1() {
        PolynomialFunction1D f = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        Optimizer1D_steepestDescent ngd1 = new Optimizer1D_steepestDescent(f, 5);
        OptimizerRecord1D r = ngd1.minimize(-10, 10, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function2() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_steepestDescent ngd1 = new Optimizer1D_steepestDescent(f, 3);
        OptimizerRecord1D r = ngd1.minimize(-2, 2, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function3() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_steepestDescent ngd1 = new Optimizer1D_steepestDescent(f, -1.2f);
        OptimizerRecord1D r = ngd1.minimize(1, 6, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    // GSS

    public static OptimizerRecord1D function4() {
        PolynomialFunction1D f = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        Optimizer1D_gss gss = new Optimizer1D_gss(f);
        OptimizerRecord1D r = gss.minimize(-10, 10, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function5() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_gss gss = new Optimizer1D_gss(f);
        OptimizerRecord1D r = gss.minimize(-2, 2, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function6() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_gss gss = new Optimizer1D_gss(f);
        OptimizerRecord1D r = gss.minimize(1, 6, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function11() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_gss gss = new Optimizer1D_gss(f);
        OptimizerRecord1D r = gss.minimize(-3, 4, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function12() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_gss gss = new Optimizer1D_gss(f);
        OptimizerRecord1D r = gss.minimize(-1, 7, 0.001f, 1000);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    // Parabolic

    public static OptimizerRecord1D function7() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_parabolic p = new Optimizer1D_parabolic(f);
        OptimizerRecord1D r = p.minimize(-2, 2, 0.001f, 100);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function8() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_parabolic p = new Optimizer1D_parabolic(f);
        OptimizerRecord1D r = p.minimize(1, 6, 0.001f, 100);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function9() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_parabolic p = new Optimizer1D_parabolic(f);
        OptimizerRecord1D r = p.minimize(-3, 4, 0.001f, 100);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static OptimizerRecord1D function10() {
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        Optimizer1D_parabolic p = new Optimizer1D_parabolic(f);
        OptimizerRecord1D r = p.minimize(-1, 7, 0.001f, 100);
        System.out.println("OptX: " + r.optX + " | OptVal: " + r.optVal + " | NumIters: " + r.numIters
                + " | IsSuccess: " + r.isSuccess);
        return r;
    }

    public static void writeMinMax(OptimizerRecord1D r, String filename) {
        // Write x and y values to an output file
        try {
            FileWriter writer = new FileWriter(filename, false);
            writer.write(r.optX + "," + r.optVal);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}