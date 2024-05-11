package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a polynomial function in n-dimensional space.
 */
public class PolynomialFunctionND extends FunctionND {
    private float[][] coefficients;
    // Coefficients are expected in a 2D array, where its length matches how many
    // dimensions there are. The coffeicients in each sub-array represents
    // the coffcients for that diemensions, and their member's power is determined
    // by the index.

    /**
     * Constructs a PolynomialFunctionND object with the specified dimension and
     * coefficients.
     *
     * @param n            the dimension of the function
     * @param coefficients the coefficients of the polynomial function.
     */
    public PolynomialFunctionND(int n, float[][] coefficients) {
        super(n);
        this.coefficients = coefficients;
    }

    /**
     * Evaluates the polynomial function at the given point.
     *
     * @param x the point at which to evaluate the function
     * @return the value of the polynomial function at the given point.
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
            for (int j = 0; j < coefficients[i].length; j++) {
                if (coefficients[i][j] != 0) {
                    result += coefficients[i][j] * Math.pow(x[i], j);
                }
            }
        }
        return result;
    }
}