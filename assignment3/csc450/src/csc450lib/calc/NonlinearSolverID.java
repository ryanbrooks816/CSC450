package csc450lib.calc;

/**
 * The NonlinearSolverID class is an abstract class that represents a nonlinear
 * solver for identifying roots of a function.
 */
public abstract class NonlinearSolverID {
    /**
     * Constructs a NonlinearSolverID object.
     */
    public NonlinearSolverID() {
    }

    /**
     * Solves the given function for a root within the specified range.
     * 
     * @param f         the function for which the root will be identified
     * @param a         the lower bound of the range
     * @param b         the upper bound of the range
     * @param n         the maximum number of iterations
     * @param tolerance the desired tolerance for the root
     * @return a NonlinearSolverRecord1D object containing the root and additional
     *         information
     */
    public abstract NonlinearSolverRecord1D solve(Function1D f, float a, float b, int n, float tolerance);
}
