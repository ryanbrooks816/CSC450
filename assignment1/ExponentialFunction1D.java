package csc450lib.calc;

/**
 * Represents a one-dimensional exponential function.
 */
public class ExponentialFunction1D extends Function1D {

    private float p0;
    private float a;
    private String mma = null;

    /**
     * Constructs a new ExponentialFunction1D object with the specified parameters.
     *
     * @param p0 the initial value of the function
     * @param a  the base of the exponential function
     */
    public ExponentialFunction1D(float p0, float a) {
        this.p0 = p0;
        this.a = a;
    }

    /**
     * Evaluates the exponential function at the given input.
     *
     * @param t the input exponentiation value
     * @return the result of the exponential function evaluation
     */
    @Override
    public float func(float t) {
        return (float) (p0 * Math.pow(a, t));
    }

    /**
     * Returns the mathematical expression of the exponential function in
     * Mathematica syntax.
     *
     * @return the mathematical expression of the exponential function
     */
    @Override
    public String getExpressionMMA() {
        this.mma = "f[x_] = " + this.p0 + " * " + this.a + "^t";

        return this.mma;
    }
}
