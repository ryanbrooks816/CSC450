package testing.A3;

import csc450lib.calc.FunctionMultiplication1D;
import csc450lib.calc.CosFunction1D;
import csc450lib.calc.Function1D;
import csc450lib.calc.FunctionAddition1D;
import csc450lib.calc.Norm1Function1D;
import csc450lib.calc.Norm2Function1D;
import csc450lib.calc.NormInfinityFunction1D;
import csc450lib.calc.Optimizer1D_gss;
import csc450lib.calc.OptimizerRecord1D;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.calc.SinFunction1D;

import java.io.IOException;
import java.io.PrintWriter;

public class a3_p3_PartialRegistration_tests {

    public static void main(String[] args) {
        function1();
        function2();
        function3();
    }

    // Provide a visualization of finding the closest point on f to m using the GSS
    // algorithm and norm 1.
    public static void function1() {
        // Define the point m
        float mX = 1;
        float mY = -3;
        // Define the range of x values to search
        float minX = -1;
        float maxX = 2;
        // Define the function f
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);
        // Call the function visualization with norm 1
        findClosestPointVisualization(mX, mY, minX, maxX, f, 2, "output3_p4_a");
    }

    // Uses the same function f but defines a point m for all 3 norms.
    public static void function2() {
        // Define the point m
        float mX = 1;
        float mY = -3;
        // Define the range of x values to search
        float minX = -1;
        float maxX = 2;
        // Define the function f
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);

        findClosestPoint(mX, mY, minX, maxX, f, 1, "output3_p4_b");
        findClosestPoint(mX, mY, minX, maxX, f, 2, "output3_p4_c");
        findClosestPoint(mX, mY, minX, maxX, f, 3, "output3_p4_d");
    }

    // This functions uses a different function f and defines multiple points m that
    // all use a different norm and range of X to search in.
    public static void function3() {
        // Define multiple points m
        float[][] m = { { 4, 12 }, { -12, -2 }, { 7, 2 } };
        // Define the range of x values to search
        float[][] xRange = { { 1, 5 }, { -15, 0 }, { 6, 8 } };
        // Define the function f = x - 8 * Sin(x) * Cos(2x)
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 1 });
        SinFunction1D f2 = new SinFunction1D(-8, 1, 0, 0);
        CosFunction1D f3 = new CosFunction1D(1, 2, 0, 0);
        FunctionMultiplication1D f4 = new FunctionMultiplication1D(f2, f3);
        FunctionAddition1D f = new FunctionAddition1D(f1, f4);

        findClosestPoint(m[0][0], m[0][1], xRange[0][0], xRange[0][1], f, 1, "output3_p4_e");
        findClosestPoint(m[1][0], m[1][1], xRange[1][0], xRange[1][1], f, 2, "output3_p4_f");
        findClosestPoint(m[2][0], m[2][1], xRange[2][0], xRange[2][1], f, 3, "output3_p4_g");
    }

    public static void findClosestPoint(float mX, float mY, float minX, float maxX, Function1D f, int norm,
            String filename) {
        // Define the tolerance and maximum number of iterations for the GSS method
        float tol = 0.0001f;
        int maxIters = 1000;

        // Define the function to be minimized based on the norm
        Function1D normFunction;
        switch (norm) {
            case 1:
                normFunction = new Norm1Function1D(f, mX, mY);
                break;
            case 2:
                normFunction = new Norm2Function1D(f, mX, mY);
                break;
            case 3:
                normFunction = new NormInfinityFunction1D(f, mX, mY);
                break;
            default:
                throw new IllegalArgumentException("Invalid norm: " + norm);
        }

        // Create a new Optimizer1D_gss object and use it to minimize the function
        Optimizer1D_gss optimizer = new Optimizer1D_gss(normFunction);
        OptimizerRecord1D record = optimizer.minimize(minX, maxX, tol, maxIters);

        // The closest point on f to m is (record.optX, f(record.optX))
        float closestX = record.optX;
        float closestY = f.func(closestX);

        // Print the result
        System.out.println("Closest point on f to m: (" + closestX + ", " + closestY + ")");

        // Write the coordinates of the closest point to a CSV file
        try (PrintWriter writer = new PrintWriter(filename + ".csv", "UTF-8")) {
            writer.println(closestX + "," + closestY);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }

    public static void findClosestPointVisualization(float mX, float mY, float minX, float maxX, Function1D f,
            int norm, String filename) {
        // Define the tolerance and maximum number of iterations for the GSS method
        float tol = 0.0001f;
        int maxIters = 1000;

        // Define the function to be minimized based on the norm
        Function1D normFunction;
        switch (norm) {
            case 1:
                normFunction = new Norm1Function1D(f, mX, mY);
                break;
            case 2:
                normFunction = new Norm2Function1D(f, mX, mY);
                break;
            case 3:
                normFunction = new NormInfinityFunction1D(f, mX, mY);
                break;
            default:
                throw new IllegalArgumentException("Invalid norm: " + norm);
        }

        // Create a new Optimizer1D_gss object and use it to minimize the function
        Optimizer1D_gss optimizer = new Optimizer1D_gss(normFunction);
        float[][] steps = optimizer.minimizeVisualization(minX, maxX, tol, maxIters);

        // Write the coordinates of each step to a CSV file
        try (PrintWriter writer = new PrintWriter(filename + ".csv", "UTF-8")) {
            for (float[] step : steps) {
                writer.println(step[0] + "," + step[1] + "," + step[2]);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }
}