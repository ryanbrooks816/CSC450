package csc450lib.calc;

/**
 * Represents a record for a 1D nonlinear solver.
 */
public class NonlinearSolverRecord1D {

    public float xStar;
    public float valStar;
    public int numIter;
    public boolean isSuccess;

    /**
     * Constructs a NonlinearSolverRecord1D object with the specified values.
     * 
     * @param xStar     the estimate of the location of the zero when the search
     *                  terminated
     * @param valStar   the value taken by the function at xStar
     * @param numIter   the number of iterations until the search terminated
     * @param isSuccess a boolean variable indicating whether the search terminated
     *                  successfully or
     *                  because of too many iterations
     */
    public NonlinearSolverRecord1D(float xStar, float valStar, int numIter, boolean isSuccess) {
        this.xStar = xStar;
        this.valStar = valStar;
        this.numIter = numIter;
        this.isSuccess = isSuccess;
    }
}
