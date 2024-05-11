package csc450lib.calc;

/**
 * This class represents a secant method implementation of the NonlinearSolverID
 * interface.
 * It provides a way to solve for the root of a function within a given interval
 * using the secant method.
 */
public class NonlinearSolverID_secant extends NonlinearSolverID {

    /**
     * Solves for the root of a function using the secant method.
     *
     * @param f         the function to solve for
     * @param a         the lower bound of the search interval
     * @param b         the upper bound of the search interval
     * @param n         the maximum number of iterations
     * @param tolerance the tolerance value for convergence
     * @return a NonlinearSolverRecord1D object containing the root estimate and
     *         other information
     * @throws IllegalArgumentException if the secant method leaves the search
     *                                  interval
     */
    @Override
    public NonlinearSolverRecord1D solve(Function1D f, float a, float b, int n, float tolerance) {
        // Initialize x0 and x1 (initial points) to the endpoints of the interval [a, b]
        float x0 = a;
        float x1 = b;

        for (int i = 0; i < n; i++) {
            float fx0 = f.func(x0);
            float fx1 = f.func(x1);

            if (Math.abs(fx1) < tolerance) {
                return new NonlinearSolverRecord1D(x1, fx1, i, true);
            }

            // Calculate the next estimate x2 using the secant method formula
            // x_k+1 = x_k - f(x_k) * (x_k - x_k−1) / (f(x_k) - f(x_k−1))
            float x2 = x1 - fx1 * (x1 - x0) / (fx1 - fx0);

            // If x2 is outside the interval [a, b], throw an exception
            if (x2 < a || x2 > b) {
                throw new IllegalArgumentException("The secant method has left the search interval.");
            }

            // Update x0 and x1 for the next iteration
            x0 = x1;
            x1 = x2;
        }

        // If we've reached the maximum number of iterations without finding a root,
        // return the last estimate
        return new NonlinearSolverRecord1D(x1, f.func(x1), n, false);
    }
}
