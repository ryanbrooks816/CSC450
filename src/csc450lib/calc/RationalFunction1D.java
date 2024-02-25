package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional rational function.
 * A rational function is defined as the ratio of two Function1D objects,
 * where the numerator and denominator are both Function1D objects.
 */
public class RationalFunction1D extends Function1D {
    private Function1D numerator;
    private Function1D denominator;
    private String mma = null;

    /**
     * Constructs a RationalFunction1D object with the given numerator and
     * denominator.
     * 
     * @param numerator   The numerator Function1D object.
     * @param denominator The denominator Function1D object.
     */
    public RationalFunction1D(Function1D numerator, Function1D denominator) {
        super();
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Constructs a RationalFunction1D object with the given numerator, denominator,
     * and domain of definition.
     * 
     * @param numerator   The numerator Function1D object.
     * @param denominator The denominator Function1D object.
     * @param d           The domain of definition.
     */
    public RationalFunction1D(Function1D numerator, Function1D denominator, DomainOfDefinition d) {
        super(d);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Constructs a RationalFunction1D object with the given numerator, denominator,
     * and x-axis limits.
     * 
     * @param numerator   The numerator Function1D object.
     * @param denominator The denominator Function1D object.
     * @param xmin        The minimum x-axis limit.
     * @param xmax        The maximum x-axis limit.
     */
    public RationalFunction1D(Function1D numerator, Function1D denominator, float xmin, float xmax) {
        super(xmin, xmax);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Evaluates the rational function at the given x-coordinate.
     * 
     * @param x The x-coordinate.
     * @return The value of the rational function at the given x-coordinate.
     * @throws CSC450Exception If the function is not defined at the evaluation
     *                         point or the denominator is zero.
     */
    public float func(float x) throws CSC450Exception {
        if ((denominator.func(x) == 0) || !isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        }
        return numerator.func(x) / denominator.func(x);
    }

    /**
     * Returns the mathematical expression of the rational function in Mathematica
     * syntax.
     * 
     * @return The mathematical expression of the rational function in Mathematica
     *         syntax.
     */
    public String getExpressionMMA() {
        if (mma == null) {
            mma = "f[x_] = (" + numerator.getExpressionMMA().replace("f[x_] = ", "") + ")/("
                    + denominator.getExpressionMMA().replace("f[x_] = ", "") + ")";
        }
        return mma;
    }
}
