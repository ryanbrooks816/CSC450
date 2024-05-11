package csc450lib.calc;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class implements the Newton-Raphson method for solving a 1D nonlinear
 * equation.
 * It extends the abstract class NonlinearSolverID.
 */
public class NonlinearSolver1D_NewtonRaphson extends NonlinearSolverID {

    /**
     * Constructs a new instance of NonlinearSolver1D_NewtonRaphson.
     */
    public NonlinearSolver1D_NewtonRaphson() {
    }

    /**
     * Solves a 1D nonlinear equation using the Newton-Raphson method.
     *
     * @param f         the function to solve
     * @param a         the lower bound of the search interval
     * @param b         the upper bound of the search interval
     * @param n         the maximum number of iterations
     * @param tolerance the tolerance for convergence
     * @return a NonlinearSolverRecord1D object containing the solution and
     *         convergence information
     * @throws IllegalArgumentException if the Newton-Raphson method leaves the
     *                                  search interval
     */
    @Override
    public NonlinearSolverRecord1D solve(Function1D f, float a, float b, int n, float tolerance) {
        float x = (a + b) / 2; // Initial guess between the interval

        // Of the form x1 = x - f(x) / f'(x)
        float fx = f.func(x);
        float dfx = f.dfunc(x);
        float x1 = x - fx / dfx;
        float fx1 = f.func(x1);
        int i = 0;
        // Handle divergence with checks on tolerance and number of iterations
        while (Math.abs(x1 - x) > tolerance && i < n) {
            if (x1 < a || x1 > b) {
                throw new IllegalArgumentException("The Newton-Raphson method has left the search interval.");
            }
            x = x1;
            fx = fx1;
            dfx = f.dfunc(x);
            x1 = x - fx / dfx;
            fx1 = f.func(x1);
            i++;
        }
        NonlinearSolverRecord1D record = new NonlinearSolverRecord1D(x1, fx1, i, true);
        return record;
    }

    /**
     * Solves a 1D nonlinear equation using the Newton-Raphson method and outputs
     * the resulkt to a CSV file in the format of x,y.
     *
     * @param f         the function to solve
     * @param a         the lower bound of the search interval
     * @param b         the upper bound of the search interval
     * @param n         the maximum number of iterations
     * @param tolerance the tolerance for convergence
     * @param filename  the name of the file to write the results to
     * @return a NonlinearSolverRecord1D object containing the solution and
     *         convergence information
     * @throws IllegalArgumentException if the Newton-Raphson method leaves the
     *                                  search interval
     */
    public NonlinearSolverRecord1D solveAndOutputResults(Function1D f, float a, float b, int n, float tolerance,
            String filename) {
        float x = (a + b) / 2; // Initial guess between the interval

        // Of the form x1 = x - f(x) / f'(x)
        float fx = f.func(x);
        float dfx = f.dfunc(x);
        float x1 = x - fx / dfx;
        float fx1 = f.func(x1);
        int i = 0;

        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Iteration,X,FX\n"); // CSV header

            // Handle divergence with checks on tolerance and number of iterations
            while (Math.abs(x1 - x) > tolerance && i < n) {
                if (x1 < a || x1 > b) {
                    throw new IllegalArgumentException("The Newton-Raphson method has left the search interval.");
                }
                x = x1;
                fx = fx1;
                dfx = f.dfunc(x);
                x1 = x - fx / dfx;
                fx1 = f.func(x1);

                // Write the current iteration, x, and f(x) to the CSV file
                writer.append(i + "," + x + "," + fx + "\n");

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        NonlinearSolverRecord1D record = new NonlinearSolverRecord1D(x1, fx1, i, true);
        return record;
    }
}
