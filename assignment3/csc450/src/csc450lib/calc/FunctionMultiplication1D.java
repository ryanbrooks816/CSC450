package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional function that is the multiplication of two other
 * one-dimensional functions.
 */
public class FunctionMultiplication1D extends Function1D {
    private Function1D f;
    private Function1D g;

    /**
     * Constructs a FunctionMultiplication1D object with the given functions.
     *
     * @param f The first function to be multiplied.
     * @param g The second function to be multiplied.
     */
    public FunctionMultiplication1D(Function1D f, Function1D g) {
        super();
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionMultiplication1D object with the given functions and
     * domain of definition.
     *
     * @param f    The first function to be multiplied.
     * @param g    The second function to be multiplied.
     * @param xmin The minimum value of the domain of definition.
     * @param xmax The maximum value of the domain of definition.
     */
    public FunctionMultiplication1D(Function1D f, Function1D g, float xmin, float xmax) {
        super(xmin, xmax);
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionMultiplication1D object with the given functions and
     * domain of definition.
     *
     * @param f      The first function to be multiplied.
     * @param g      The second function to be multiplied.
     * @param domain The domain of definition.
     */
    public FunctionMultiplication1D(Function1D f, Function1D g, DomainOfDefinition domain) {
        super(domain);
        this.f = f;
        this.g = g;
    }

    /**
     * Evaluates the function at the given value of x.
     *
     * @param x The value at which to evaluate the function.
     * @return The result of multiplying the values of the two functions at x.
     */
    public float func(float x) {
        return f.func(x) * g.func(x);
    }

    /**
     * Returns the mathematical expression of the function in Mathematica syntax.
     *
     * @return The mathematical expression of the function.
     */
    public String getExpressionMMA() {
        return f.getExpressionMMA() + " * " + g.getExpressionMMA().replace("f[x_] = ", "");
    }

    /**
     * Returns the derivative of the function at the given value of x.
     *
     * @param x The value at which to evaluate the derivative.
     * @return The derivative of the function at x.
     * @throws CSC450Exception if the function is not defined at the evaluation
     *                         point
     */
    public float dfunc(float x) {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }

        return f.func(x) * g.dfunc(x) + f.dfunc(x) * g.func(x);
    }

    /**
     * Calculates the derivative of the polynomial function approximation at a given
     * point.
     * Default step of h = 0.0001 is used.
     *
     * @param x the point at which to calculate the derivative
     * @return the derivative of the polynomial function approximation at the given
     *         point
     */
    public float dfuncApproximation(float x) {
        return super.dfunc(x);
    }

    /**
     * Checks if there is an expresson for the derivative or not.
     * 
     * @return True; there is an expression for the derivative of this function.
     */
    public boolean derivativeIsExact() {
        return true;
    }

    /**
     * Returns whether the second derivative of the function is exact.
     *
     * @return true if the second derivative is exact, false otherwise.
     */
    @Override
    public boolean secondDerivativeIsExact() {
        return false;
    }
}
