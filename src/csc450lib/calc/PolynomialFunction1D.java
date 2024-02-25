package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional polynomial function.
 * A polynomial function of degree n is a function pn : R −→ R
 * x −→ p_n(x) = a_0 + a_1 * x + a_2 * x^2 + · · · + a_n * x^n,
 * where the a_i are constant real coefficients.
 * Its domain and range is the entire real line.
 */
public class PolynomialFunction1D extends Function1D {

    private float[] a;
    private String mma = null;

    /**
     * Constructs a polynomial function with the given coefficients.
     * The coefficients are stored in the array 'a'.
     * The function is defined over the entire real line.
     *
     * @param a the coefficients of the polynomial function
     */
    public PolynomialFunction1D(float[] a) {
        super();
        if (a.length == 0) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        } else {
            this.a = a;
        }
    }

    /**
     * Constructs a polynomial function with the given coefficients and domain.
     * The coefficients are stored in the array 'a'.
     * The function is defined over the specified domain.
     *
     * @param a    the coefficients of the polynomial function
     * @param xmin the minimum value of the domain
     * @param xmax the maximum value of the domain
     */
    public PolynomialFunction1D(float[] a, float xmin, float xmax) {
        super(xmin, xmax);
        if (a.length == 0) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        } else {
            this.a = a;
        }
    }

    /**
     * Constructs a polynomial function with the given coefficients and domain.
     * The coefficients are stored in the array 'a'.
     * The function is defined over the specified domain.
     *
     * @param a the coefficients of the polynomial function
     * @param d the domain of definition for the function
     */
    public PolynomialFunction1D(float[] a, DomainOfDefinition d) {
        super(d);
        if (a.length == 0) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        } else {
            this.a = a;
        }
    }

    /**
     * Calculates the value of the polynomial function at the given point 'x'.
     *
     * @param x the point at which to evaluate the function
     * @return the value of the function at 'x'
     * @throws CSC450Exception if the function is not defined at 'x'
     */
    @Override
    public float func(float x) {
        float result = 0;

        if (isDefinedAt(x)) {
            // Loop through the coefficients and calculate the value of the polynomial
            if (a.length == 1) {
                result = a[0];
            } else
                for (int i = 0; i < a.length; i++) {
                    result += a[i] * Math.pow(x, i);
                }
        } else {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }

        return result;
    }

    /**
     * Calculates the value of the polynomial function using Horner's form at the
     * given point 'x'.
     *
     * @param x the point at which to evaluate the function
     * @return the value of the function at 'x' using Horner's form
     * @throws CSC450Exception if the function is not defined at 'x'
     */
    public float hornersForm(float x) {
        float result = 0;

        // The equation of the polynomial function can be reformulated as
        // pn(x) = a0 + x(a1 + x(a2 + x(a3 + · · · x(a_{n−1} + x*a_n)))· · ·)),
        // which is known as the Horner form of the equation.
        if (isDefinedAt(x)) {
            result = a[a.length - 1];
            for (int i = a.length - 2; i >= 0; i--) {
                result = result * x + a[i];
            }

        } else {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }

        return result;
    }

    /**
     * Returns the equation of the polynomial function in the form of a
     * Mathematica-compatible expression.
     * If the function doesn't have an equation, returns null.
     *
     * @return the equation of the polynomial function as a Mathematica-compatible
     *         expression
     */
    public String getExpressionMMA() {
        this.mma = "f[x_] = ";

        if (this.a == null) {
            return null;
        }

        StringBuilder expression = new StringBuilder("f[x_] = ");

        for (int i = a.length - 1; i >= 0; i--) {
            float coefficient = this.a[i];

            if (coefficient == 0) {
                continue;
            }

            if (i < this.a.length - 1) {
                expression.append(" + ");
            }

            if (coefficient != 1 || i == 0) {
                expression.append(coefficient);
            }

            if (i > 0) {
                if (coefficient != 1) {
                    expression.append("*");
                }
                expression.append("x");
            }

            if (i > 1) {
                expression.append("^" + i);
            }
        }

        this.mma = expression.toString();
        return this.mma;
    }
}