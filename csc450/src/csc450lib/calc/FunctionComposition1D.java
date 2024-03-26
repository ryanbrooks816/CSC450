package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional function obtained by composing two other
 * functions such as F(G(x)) where F and G are functions and x is the input to.
 */
public class FunctionComposition1D extends Function1D {
    private Function1D f;
    private Function1D g;

    /**
     * Constructs a FunctionComposition1D object with the given functions.
     * 
     * @param f The first function to be composed.
     * @param g The second function to be composed.
     */
    public FunctionComposition1D(Function1D f, Function1D g) {
        super();
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionComposition1D object with the given functions and domain
     * of definition.
     * 
     * @param f    The first function to be composed.
     * @param g    The second function to be composed.
     * @param xmin The minimum value of the domain of definition.
     * @param xmax The maximum value of the domain of definition.
     */
    public FunctionComposition1D(Function1D f, Function1D g, float xmin, float xmax) {
        super(xmin, xmax);
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionComposition1D object with the given functions and domain
     * of definition.
     * 
     * @param f      The first function to be composed.
     * @param g      The second function to be composed.
     * @param domain The domain of definition.
     */
    public FunctionComposition1D(Function1D f, Function1D g, DomainOfDefinition domain) {
        super(domain);
        this.f = f;
        this.g = g;
    }

    /**
     * Evaluates the composed function at the given value.
     * 
     * @param x The input value.
     * @return The output value of the composed function.
     */
    public float func(float x) {
        return f.func(g.func(x));
    }

    /**
     * Returns the mathematical expression of the composed function in Mathematica
     * format.
     *
     * @return The composed function expression in Mathematica format.
     */
    public String getExpressionMMA() {
        String gExpression = g.getExpressionMMA();
        String fExpression = f.getExpressionMMA();

        gExpression = gExpression.replace("f[x_] = ", "");
        fExpression = fExpression.replace("f[x_] = ", "");

        // Replace "x" in the expression for f with the expression for g
        fExpression = fExpression.replace("x", "(" + gExpression + ")");

        return "f[x_] = " + fExpression;
    }

    /**
     * Calculates the derivative of composed functions at the given input.
     *
     * @param x the point at which to evaluate the second derivative
     * @return the result of the composed functions' derivative evaluation
     */
    @Override
    public float dfunc(float x) {
        return f.dfunc(g.dfunc(x));
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
     * Calculates the second derivative of the composed functions at a given point.
     *
     * @param x the point at which to evaluate the second derivative
     * @return the value of the second derivative at the given point
     */
    public float d2func(float x) {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        return f.d2func(g.d2func(x));
    }

    /**
     * Checks if there is an expresson for the second derivative or not.
     * 
     * @return True if there is an expression for the derivative, false otherwise.
     */
    public boolean secondDerivativeIsExact() {
        return true;
    }
}