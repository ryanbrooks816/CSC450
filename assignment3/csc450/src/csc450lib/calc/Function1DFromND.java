package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * This abstract class represents a 1D function derived from an n-dimensional
 * function.
 * It allows computing the value of the function at a given point in the 1D
 * space.
 */

public abstract class Function1DFromND extends Function1D {

    protected FunctionND fn;
    protected float[] x0;
    protected float[] uv;

    /**
     * Constructs a Function1DFromND object with the specified n-dimensional
     * function, origin point, and direction vector.
     * Throws an exception if the dimensions of the function, origin point, and
     * direction vector don't match.
     *
     * @param fn the n-dimensional function used to generate the 1D function
     * @param x0 the initial point in n-dimensional space to use as the origin of
     *           the new U axis
     * @param uv the direction vector for the U axis
     * @throws CSC450Exception if the dimensions don't match
     */
    public Function1DFromND(FunctionND fn, float[] x0, float[] uv) throws CSC450Exception {
        if (x0.length != uv.length || x0.length != fn.getN()) {
            throw new CSC450Exception(CSC450ErrorCode.ARRAY_LENGTH_DOES_NOT_EQUAL_FUNCTION_DIMENSION);
        }
        this.fn = fn;
        this.x0 = x0;
        this.uv = uv;
    }

    /**
     * Represents solving a one-dimensional function at step u derived from a
     * multi-dimensional unction.
     * 
     * @param u the step at which to solve the function
     * @return the value of the function at the specified step
     * @throws CSC450Exception if the function cannot be solved
     */
    public abstract float func(float u) throws CSC450Exception;
}
