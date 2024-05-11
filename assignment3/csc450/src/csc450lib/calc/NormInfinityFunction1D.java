package csc450lib.calc;

/**
 * Represents a one-dimensional function that calculates the infinity norm.
 * The infinity norm is defined as the maximum absolute difference between the
 * given function and a specified point (mX, mY).
 */
public class NormInfinityFunction1D extends Function1D {

    private Function1D f;
    private float mX;
    private float mY;

    /**
     * Represents a one-dimensional function for calculating the infinity norm.
     */
    public NormInfinityFunction1D(Function1D f, float mX, float mY) {
        this.f = f;
        this.mX = mX;
        this.mY = mY;
    }

    /**
     * Calculates the value of the function at a given point.
     *
     * @param x the input value for the function
     * @return the calculated value of the function
     */
    @Override
    public float func(float x) {
        float y = f.func(x);
        return Math.max(Math.abs(mX - x), Math.abs(mY - y));
    }

    /**
     * Returns the expression in Mathematica syntax for calculating the maximum
     * absolute difference between the x-coordinate and the function value at x.
     *
     * @return the expression in Mathematica syntax
     */
    @Override
    public String getExpressionMMA() {
        return "Max[Abs[" + mX + " - x], Abs[" + mY + " - f[x]]]";
    }

    /**
     * Returns whether the derivative of this function is exact.
     *
     * @return true if the derivative is exact, false otherwise
     */
    @Override
    public boolean derivativeIsExact() {
        return false;
    }

    /**
     * Returns whether the second derivative of the function is exact.
     *
     * @return true if the second derivative is exact, false otherwise
     */
    @Override
    public boolean secondDerivativeIsExact() {
        return false;
    }
}