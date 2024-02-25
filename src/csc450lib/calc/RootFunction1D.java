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
        return this.a * (float) Math.pow(this.b * x + this.c, 1 / power) + this.d;
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

}
