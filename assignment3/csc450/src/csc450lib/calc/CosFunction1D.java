package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional cos function.
 * Sin function of the form f(x) = a * cos(b * x + c) + d
 */
public class CosFunction1D extends Function1D {

    private float a;
    private float b;
    private float c;
    private float d;
    private String mma = null;

    /**
     * Constructs a new instance of the SinFunction1D class with default values of
     * the parent function.
     */
    public CosFunction1D() {
        super();
        this.a = 1;
        this.b = 1;
        this.c = 0;
        this.d = 0;
    }

    /**
     * Constructs a CosFunction1D object with the given coefficients.
     *
     * @param a The coefficient 'a' in the cosine function equation.
     * @param b The coefficient 'b' in the cosine function equation.
     * @param c The constant term 'c' in the cosine function equation.
     * @param d The constant term 'd' in the cosine function equation.
     */
    public CosFunction1D(float a, float b, float c, float d) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Constructs a CosFunction1D object with the given coefficients and set
     * xmin and xmax.
     *
     * @param a    The coefficient 'a' in the cosine function equation.
     * @param b    The coefficient 'b' in the cosine function equation.
     * @param c    The constant term 'c' in the cosine function equation.
     * @param d    The constant term 'd' in the cosine function equation.
     * @param xmin The lower bound of the function's domain of definition.
     * @param xmax The upper bound of the function's domain of definition.
     */
    public CosFunction1D(float a, float b, float c, float d, float xmin, float xmax) {
        super(xmin, xmax);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Constructs a CosFunction1D object with the given coefficients and
     * domain of definition.
     *
     * @param a The coefficient 'a' in the cosine function equation.
     * @param b The coefficient 'b' in the cosine function equation.
     * @param c The constant term 'c' in the cosine function equation.
     * @param d The domain of definition for the function.
     */
    public CosFunction1D(float a, float b, float c, float d, DomainOfDefinition domain) {
        super(domain);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Evaluates the cosine function at the given input.
     *
     * @param t the input value
     * @return the result of the cosine function evaluation
     */
    @Override
    public float func(float x) {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        return (float) (a * Math.cos(b * x + c) + d);
    }

    /**
     * Returns the mathematical expression of the cosine function in
     * Mathematica syntax.
     *
     * @return the mathematical expression of the cosine function
     */
    @Override
    public String getExpressionMMA() {
        this.mma = "f[x_] = ";

        if (a == 0) {
            // If d is 0, then the function is 0 anyway
            return this.mma += d;
        }

        if (a != 1) {
            this.mma += a + " * ";
        }

        this.mma += "Cos[";

        if (b == 0) {
            // If c is 0, then the function is 0 anyway
            this.mma += c + "]";
            if (d != 0) {
                this.mma += " + " + d;
            }
            return this.mma;
        }

        if (b == 1) {
            this.mma += "x";
        } else {
            this.mma += b + " * x";
        }

        if (c != 0) {
            this.mma += " + " + c;
        }

        this.mma += "]";

        if (d != 0) {
            this.mma += " + " + d;
        }

        return this.mma;
    }

    /**
     * Calculates the derivative of the cos function at the given input.
     * Default step of h = 0.0001 is used.
     *
     * @param x the input value
     * @return the result of the cos function derivative evaluation
     */
    @Override
    public float dfunc(float x) {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        return (float) (-a * b * Math.sin(b * x + c));
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
     * Calculates the second derivative of the cosine function at a given point.
     * 
     * @param x the value at which to evaluate the second derivative
     * @return the value of the second derivative at the given point
     * @throws CSC450Exception if the function is not defined at the evaluation
     *                         point
     */
    public float d2func(float x) {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        return (float) (-a * b * b * Math.cos(b * x + c));
    }

    /**
     * Calculates the second derivative of the sine function approximation at a
     * given point.
     * Default step of h = 0.0001 is used.
     * 
     * @param x The point at which to evaluate the second derivative.
     * @return The value of the second derivative at the given point.
     */
    public float d2funcApproximation(float x) {
        return super.d2func(x);
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
