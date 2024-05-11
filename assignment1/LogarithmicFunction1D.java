package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a logarithmic function in one dimension.
 */
public class LogarithmicFunction1D extends Function1D {

    private float a;
    private float b;
    private float c;
    private String mma = null;

    /**
     * Constructs a LogarithmicFunction1D object with the given coefficients.
     *
     * @param a The coefficient 'a' in the logarithmic function equation.
     * @param b The base 'b' in the logarithmic function equation.
     * @param c The constant term 'c' in the logarithmic function equation.
     */
    public LogarithmicFunction1D(float a, float b, float c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Constructs a LogarithmicFunction1D object with the given coefficients and set
     * xmin and xmax
     *
     * @param a    The coefficient 'a' in the logarithmic function equation.
     * @param b    The base 'b' in the logarithmic function equation.
     * @param c    The constant term 'c' in the logarithmic function equation.
     * @param xmin The lower bound of the function's domain of definition.
     * @param xmax The upper bound of the function's domain of definition.
     */
    public LogarithmicFunction1D(float a, float b, float c, float xmin, float xmax) {
        super(xmin, xmax);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Constructs a LogarithmicFunction1D object with the given coefficients and
     * domain of definition.
     *
     * @param a The coefficient 'a' in the logarithmic function equation.
     * @param b The base 'b' in the logarithmic function equation.
     * @param c The constant term 'c' in the logarithmic function equation.
     * @param d The domain of definition for the function.
     */
    public LogarithmicFunction1D(float a, float b, float c, DomainOfDefinition d) {
        super(d);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Evaluates the logarithmic function at the given value of 'x'.
     *
     * @param x The value of 'x' at which to evaluate the function.
     * @return The result of evaluating the logarithmic function at 'x'.
     * @throws CSC450Exception If the function is not defined at the evaluation
     *                         point.
     */
    @Override
    public float func(float x) throws CSC450Exception {
        float result = 0;

        if (isDefinedAt(x) && x > 0) {
            result = (float) (a * Math.log(x) / Math.log(b) + c);
        } else {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }

        return result;
    }

    /**
     * Returns the function's equation in the form of a Mathematica-compatible
     * expression.
     * If the function doesn't have an equation, this method returns a null
     * reference.
     *
     * @return The function's equation in Mathematica-compatible expression form.
     */
    @Override
    public String getExpressionMMA() {
        this.mma = "f[x_] = " + this.a + " * log[" + this.b + ", x" + "] + " + this.c;

        return this.mma;
    }
}
