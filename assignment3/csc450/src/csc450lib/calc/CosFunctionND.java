package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a multi-dimensional cosine function.
 */
public class CosFunctionND extends FunctionND {
    private float[][] coefficients;

    /**
     * Constructs a CosFunctionND object with the specified dimension and
     * coefficients.
     *
     * @param n            the dimension of the function
     * @param coefficients the coefficients for each dimension
     */
    public CosFunctionND(int n, float[][] coefficients) {
        super(n);
        this.coefficients = coefficients;
    }

    /**
     * Calculates the value of the cosine function at the given point.
     *
     * @param x the point at which to evaluate the function
     * @return the value of the function at the given point
     * @throws CSC450Exception if the length of the input array does not match the
     *                         dimension of the function
     */
    @Override
    public float func(float[] x) throws CSC450Exception {
        if (x.length != getN()) {
            throw new CSC450Exception(CSC450ErrorCode.ARRAY_LENGTH_DOES_NOT_EQUAL_FUNCTION_DIMENSION);
        }

        float result = 0;
        for (int i = 0; i < getN(); i++) {
            float a = coefficients[i][0];
            float b = coefficients[i][1];
            float c = coefficients[i][2];
            float d = coefficients[i][3];
            result += a * Math.cos(b * x[i] + c) + d;
        }
        return result;
    }
}