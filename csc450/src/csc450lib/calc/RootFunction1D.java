package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional root function.
 */
public class RootFunction1D extends Function1D {

    private float a;
    private float b;
    private float c;
    private float d;
    private float power;
    private String mma = null;

    /**
     * Constructs a square root RootFunction1D object with default values of the
     * parent function.
     */
    public RootFunction1D() {
        super();
        this.a = 1;
        this.b = 1;
        this.c = 0;
        this.d = 0;
        this.power = 2;
    }

    /**
     * Constructs a square root RootFunction1D object with default values of the
     * parent function.
     */
    public RootFunction1D(float power) {
        super();
        this.a = 1;
        this.b = 1;
        this.c = 0;
        this.d = 0;
        this.power = power;
    }

    /**
     * Constructs a RootFunction1D object with the specified coefficients and power.
     *
     * @param a     the coefficient a
     * @param b     the coefficient b
     * @param c     the coefficient c
     * @param d     the coefficient d
     * @param power the power of the root function
     */
    public RootFunction1D(float a, float b, float c, float d, float power) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.power = power;
    }

    /**
     * Constructs a RootFunction1D object with the specified coefficients, power,
     * and domain of definition.
     *
     * @param a     the coefficient a
     * @param b     the coefficient b
     * @param c     the coefficient c
     * @param d     the coefficient d
     * @param power the power of the root function
     * @param xmin  the minimum value of the domain of definition
     * @param xmax  the maximum value of the domain of definition
     */
    public RootFunction1D(float a, float b, float c, float d, float power, float xmin, float xmax) {
        super(xmin, xmax);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.power = power;
    }

    /**
     * Constructs a RootFunction1D object with the specified coefficients, power,
     * and domain of definition.
     *
     * @param a      the coefficient a
     * @param b      the coefficient b
     * @param c      the coefficient c
     * @param d      the coefficient d
     * @param power  the power of the root function
     * @param domain the domain of definition
     */
    public RootFunction1D(float a, float b, float c, float d, float power, DomainOfDefinition domain) {
        super(domain);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.power = power;
    }

    /**
     * Evaluates the root function at the specified value of x.
     *
     * @param x the value of x
     * @return the result of the root function evaluation
     */
    public float func(float x) {
        if (!isDefinedAt(x) || x <= 0) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        return this.a * (float) Math.pow(this.b * x + this.c, (1 / power)) + this.d;
    }

    /**
     * Returns the mathematical expression of the root function in Mathematica
     * syntax.
     *
     * @return the mathematical expression of the root function
     */
    public String getExpressionMMA() {
        if (mma == null) {
            mma = "f[x_] = ";
            if (a != 1) {
                mma += a + " * ";
            }
            mma += "(";
            if (b != 1) {
                mma += b + " * ";
            }
            mma += "x";
            if (c != 0) {
                mma += " + " + c;
            }
            mma += ")^(1/" + power + ")";
            if (d != 0) {
                mma += " + " + d;
            }
        }
        return mma;
    }

    /**
     * Calculates the derivative of the sin function at the given input.
     *
     * @param x the input value
     * @return the result of the sin function derivative evaluation
     */
    @Override
    public float dfunc(float x) {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        float dpow = (1 / power) - 1;
        return (float) (a * Math.pow(b * x + c, dpow) * (b / power));
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
     * Calculates the second derivative of the function at a given point.
     *
     * @param x the point at which to evaluate the second derivative
     * @return the value of the second derivative at the given point
     * @throws CSC450Exception if the function is not defined at the evaluation
     *                         point
     */
    public float d2func(float x) {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        float dpow1 = (1 / power) - 1;
        float dpow2 = (dpow1) - 1;
        return (float) (a * Math.pow(b * x + c, dpow2) * (b * b * (1 / power) * ((1 / power) - 1)));
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
