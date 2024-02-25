package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional sin function.
 * Sin function of the form f(x) = a * sin(b * x + c) + d
 */
public class SinFunction1D extends Function1D {

    private float a;
    private float b;
    private float c;
    private float d;
    private String mma = null;

    /**
     * Constructs a SinFunction1D object with the given coefficients.
     *
     * @param a The coefficient 'a' in the sin function equation.
     * @param b The coefficient 'b' in the sin function equation.
     * @param c The constant term 'c' in the sin function equation.
     * @param d The constant term 'd' in the sin function equation.
     */
    public SinFunction1D(float a, float b, float c, float d) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Constructs a SinFunction1D object with the given coefficients and set
     * xmin and xmax
     *
     * @param a    The coefficient 'a' in the sin function equation.
     * @param b    The coefficient 'b' in the sin function equation.
     * @param c    The constant term 'c' in the sin function equation.
     * @param d    The constant term 'd' in the sin function equation.
     * @param xmin The lower bound of the function's domain of definition.
     * @param xmax The upper bound of the function's domain of definition.
     */
    public SinFunction1D(float a, float b, float c, float d, float xmin, float xmax) {
        super(xmin, xmax);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Constructs a SinFunction1D object with the given coefficients and
     * domain of definition.
     *
     * @param a The coefficient 'a' in the sin function equation.
     * @param b The coefficient 'b' in the sin function equation.
     * @param c The constant term 'c' in the sin function equation.
     * @param d The domain of definition for the function.
     */
    public SinFunction1D(float a, float b, float c, float d, DomainOfDefinition domain) {
        super(domain);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Evaluates the sin function at the given input.
     *
     * @param t the input value
     * @return the result of the sin function evaluation
     */
    @Override
    public float func(float t) {
        if (!isDefinedAt(t)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        return (float) (a * Math.sin(b * t + c) + d);
    }

    /**
     * Returns the mathematical expression of the exponential function in
     * Mathematica syntax.
     *
     * @return the mathematical expression of the exponential function
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

        this.mma += "Sin[";

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
}
