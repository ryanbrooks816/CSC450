package csc450lib.calc;

/**
 * This class represents a bisection method implementation of the
 * NonlinearSolverID interface.
 * It provides a method to solve a nonlinear equation using the bisection
 * method.
 */
public class NonlinearSolverID_bisection extends NonlinearSolverID {

    /**
     * Constructs a new instance of the NonlinearSolverID_bisection class.
     */
    public NonlinearSolverID_bisection() {
    }

    /**
     * Solves a nonlinear equation using the bisection method.
     * 
     * @param f         the function to solve
     * @param a         the lower bound of the interval
     * @param b         the upper bound of the interval
     * @param n         the maximum number of iterations
     * @param tolerance the tolerance for convergence
     * @return a NonlinearSolverRecord1D object containing the solution and
     *         convergence information
     * @throws IllegalArgumentException if the function values at the endpoints have
     *                                  the same sign
     */
    @Override
    public NonlinearSolverRecord1D solve(Function1D f, float a, float b, int n, float tolerance) {
        float fa = f.func(a);
        float fb = f.func(b);

        // Determine if the function values at the endpoints have opposite signs
        if (fa * fb > 0) {
            throw new IllegalArgumentException("The function values at the endpoints must have opposite signs.");
        }

        float c, fc;
        for (int i = 0; i < n; i++) {
            c = (a + b) / 2;
            fc = f.func(c);

            if (Math.abs(b - a) < tolerance) {
                return new NonlinearSolverRecord1D(c, fc, i, true);
            }

            // Update the interval, moving the endpoint with the same sign as the midpoint
            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }

        // Return the record with the current values after the specified number of
        // iterations
        return new NonlinearSolverRecord1D((a + b) / 2, f.func((a + b) / 2), n, false);
    }
}
