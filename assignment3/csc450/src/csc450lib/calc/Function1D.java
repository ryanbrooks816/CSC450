package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents an abstract function of one variable and defines a valid domain
 * for all Function1D objects.
 */
public abstract class Function1D {
    private float xmin;
    private float xmax;

    /**
     * The default constructor for a function defined over the entire float range.
     */
    public Function1D() {
        xmin = Float.NEGATIVE_INFINITY;
        xmax = Float.POSITIVE_INFINITY;
    }

    /**
     * The constructor for a function defined over the float range ]xmin, xmax[.
     * 
     * @param xmin The lower bound of the function's domain of definition.
     * @param xmax The upper bound of the function's domain of definition.
     */
    public Function1D(float xmin, float xmax) {
        this.xmin = xmin;
        this.xmax = xmax;
    }

    /**
     * The constructor for a function defined over particular domain.
     * 
     * @param d The domain of definition for the function.
     */
    public Function1D(DomainOfDefinition d) {
        this.xmin = d.getLowerBound();
        this.xmax = d.getUpperBound();
    }

    /**
     * Returns the value taken by the function at x.
     * 
     * @param x The input value.
     * @return The value of the function at x.
     */
    public abstract float func(float x);

    /**
     * Returns the function's equation in the form of a Mathematica-compatible
     * expression.
     * If the function doesn't have an equation, the method should return a null
     * reference.
     * 
     * @return The function's equation in Mathematica-compatible expression.
     */
    public String getExpressionMMA() {
        return null;
    }

    /**
     * Checks if x belongs to the function's domain of definition.
     * 
     * @param x The input value.
     * @return True if x belongs to the function's domain of definition, false
     *         otherwise.
     */
    public boolean isDefinedAt(float x) {
        return x > xmin && x < xmax;
    }

    /**
     * Returns the lower bound of the function's domain of definition.
     * 
     * @return The lower bound of the function's domain of definition.
     */
    public float getLowerBound() {
        return xmin;
    }

    /**
     * Returns the upper bound of the function's domain of definition.
     * 
     * @return The upper bound of the function's domain of definition.
     */
    public float getUpperBound() {
        return xmax;
    }

    /**
     * Calculates the derivative of the function at x.
     * 
     * @param x The input value.
     * @return The derivative of the function at x.
     * @throws CSC450Exception If the function is not defined at the evaluation
     *                         point.
     */
    public float dfunc(float x) throws CSC450Exception {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        } else {
            float h = 0.0001f;
            return (func(x + h) - func(x - h)) / (2 * h);
        }
    }

    /**
     * Checks if there is an expresson for the derivative or not.
     * 
     * @return True if there is an expression for the derivative, false otherwise.
     */
    public boolean derivativeIsExact() {
        return false;
    }

    /**
     * Calculates the second derivative of the function at x.
     * 
     * @param x The input value.
     * @return The value of the second derivative at x.
     * @throws CSC450Exception If the function is not defined at the evaluation
     *                         point.
     */
    public float d2func(float x) throws CSC450Exception {
        if (!isDefinedAt(x)) {
            throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
        } else {
            float h = 0.0001f;
            return (dfunc(x + h) - dfunc(x - h)) / (2 * h);
        }
    }

    /**
     * Checks if there is an expresson for the second derivative or not.
     * 
     * @return True if there is an expression for the derivative, false otherwise.
     */
    public boolean secondDerivativeIsExact() {
        return false;
    }
}