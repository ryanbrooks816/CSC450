package csc450lib.calc;

import csc450lib.exceptions.CSC450Exception;

/**
 * The abstract class FunctionND represents a function defined over the
 * n-dimensional input set R^n.
 */
public abstract class FunctionND {

    private int n;

    /**
     * Constructs a FunctionND object with the specified number of dimensions.
     *
     * @param n the number of dimensions of the function's input set
     */
    public FunctionND(int n) {
        this.n = n;
    }

    /**
     * Calculates the value of the function for the given input array.
     *
     * @param x the input array representing the coordinates in the n-dimensional
     *          space
     * @return the value of the function for the given input array
     * @throws CSC450Exception if the length of the input array is not equal to the
     *                         number of dimensions of the function
     */
    public abstract float func(float[] x) throws CSC450Exception;

    /**
     * Returns the number of dimensions of the function's input set.
     *
     * @return the number of dimensions of the function's input set
     */
    public int getN() {
        return n;
    }
}
