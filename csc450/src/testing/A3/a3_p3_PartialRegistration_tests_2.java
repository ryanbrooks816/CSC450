package testing.A3;

import csc450lib.calc.FunctionMultiplication1D;
import csc450lib.calc.Function1D;
import csc450lib.calc.FunctionAddition1D;
import csc450lib.calc.Norm1Function1D;
import csc450lib.calc.Norm2Function1D;
import csc450lib.calc.NormInfinityFunction1D;
import csc450lib.calc.Optimizer1D_gss;
import csc450lib.calc.Optimizer1D_steepestDescent;
import csc450lib.calc.OptimizerRecord1D;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.calc.SinFunction1D;

import java.io.IOException;
import java.io.PrintWriter;

public class a3_p3_PartialRegistration_tests_2 {

    public static void main(String[] args) {
        findMinimumDistances(1, "output3_p4_2_1_");
        approach1(1, "output3_p4_2_2a_");
    }

    public static void findMinimumDistances(int norm, String filename) {
        // Define the five points m
        float[][] m = { { -5, 10 }, { -2, 5 }, { 0, 10 }, { 2, 12 }, { 3, -18 } };

        // Define the range of x values to search
        float[][] xRange = { { -5, -3 }, { -4, -2 }, { -2, -2 }, { 1, 3 }, { 2, 6 } };

        // Define the function f = x^2 * Sin(x)
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);

        float[] distances = new float[m.length];

        // For each point mi in m, find the closest point on f to mi
        for (int i = 0; i < m.length; i++) {
            distances[i] = findClosestPoint(m[i][0], m[i][1], xRange[i][0], xRange[i][1], f, norm,
                    filename + (i + 1));
        }
    }

    // This approach uses brute force to find the best value of a
    public static void approach1(int norm, String filename) {
        // Define the five points m
        float[][] m = { { -5, 10 }, { -2, 5 }, { 0, 10 }, { 2, 12 }, { 3, -18 } };

        // Define the range of x values to search
        float[][] xRange = { { -5, -3 }, { -4, -2 }, { -2, -2 }, { 1, 3 }, { 2, 6 } };

        // Define the range of a values to search
        float[] aRange = { -10, 10 };

        // Initialize the best score and the best value of a
        float bestScore = Float.MAX_VALUE;
        float bestA = 0;

        // For each value of a in the range [aRange[0], aRange[1]]
        for (float a = aRange[0]; a <= aRange[1]; a += 0.1) {
            // Define the function f = x^2 * Sin(x)
            PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
            SinFunction1D f2 = new SinFunction1D();
            FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);

            // Add the constant a to the function f
            FunctionAddition1D fAdd = new FunctionAddition1D(f, new PolynomialFunction1D(new float[] { a }));

            float[] distances = new float[m.length];

            // For each point mi in m, find the closest point on fAdd to mi
            for (int i = 0; i < m.length; i++) {
                distances[i] = findClosestPoint(m[i][0], m[i][1], xRange[i][0], xRange[i][1], fAdd, norm,
                        filename + (i + 1));
            }

            // Calculate the score s(a)
            float score = calculateGlobalScore(distances, norm); // Change the norm used to optimize the score here.

            // If the score is better than the best score, update the best score and the
            // best value of a
            if (score < bestScore) {
                bestScore = score;
                bestA = a;
            }
        }

        // Print the best score and the best value of a
        System.out.println("Best score: " + bestScore);
        System.out.println("Best value of a: " + bestA);
    }

    // This approach uses the Steepest Descent method to find the closest point on f
    public static void approach2() {
        // Define the five points m
        float[][] m = { { -5, 10 }, { -2, 5 }, { 0, 10 }, { 2, 12 }, { 3, -18 } };

        // Define the range of x values to search
        float[][] xRange = { { -5, -3 }, { -4, -2 }, { -2, -2 }, { 1, 3 }, { 2, 6 } };

        // Define the range of a values to search
        float[] aRange = { -10, 10 };

        // Define the function f = x^2 * Sin(x)
        PolynomialFunction1D f1 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        SinFunction1D f2 = new SinFunction1D();
        FunctionMultiplication1D f = new FunctionMultiplication1D(f1, f2);

        // Define the score function
        Function1D scoreFunction = new Function1D() {
            @Override
            public float func(float a) {
                // Add the constant a to the function f
                FunctionAddition1D fAdd = new FunctionAddition1D(f, new PolynomialFunction1D(new float[] { a }));

                float[] distances = new float[m.length];

                // For each point mi in m, find the closest point on fAdd to mi
                for (int i = 0; i < m.length; i++) {
                    distances[i] = findClosestPoint(m[i][0], m[i][1], xRange[i][0], xRange[i][1], fAdd, 1,
                            "output3_p4_" + (i + 1));
                }

                // Calculate the score s(a)
                return calculateGlobalScore(distances, 1);
            }
        };

        // Create an optimizer
        Optimizer1D_steepestDescent optimizer = new Optimizer1D_steepestDescent(scoreFunction, aRange[0]);

        // Minimize the score function
        OptimizerRecord1D record = optimizer.minimize(aRange[0], aRange[1], 0.01f, 1000);

        // The best value of a is the one that minimizes the score function
        float bestA = record.optX;
        float bestScore = record.optVal;

        // Print the best score and the best value of a
        System.out.println("Best score: " + bestScore + " at a = " + bestA);
    }

    public static float findClosestPoint(float mX, float mY, float minX, float maxX, Function1D f, int norm,
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

        // Compute the distance between m and the closest point on f
        float distance = (float) Math.sqrt(Math.pow(closestX - mX, 2) + Math.pow(closestY - mY, 2));

        // Return the distance
        return distance;
    }

    public static float calculateGlobalScore(float[] distances, int norm) {
        float s = 0;
        switch (norm) {
            case 1:
                for (float distance : distances) {
                    s += Math.abs(distance);
                }
                break;
            case 2:
                for (float distance : distances) {
                    s += Math.pow(distance, 2);
                }
                s = (float) Math.sqrt(s);
                break;
            case 3:
                for (float distance : distances) {
                    if (Math.abs(distance) > s) {
                        s = Math.abs(distance);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid norm: " + norm);
        }
        return s;
    }
}
