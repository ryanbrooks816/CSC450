package csc450lib.calc;

/**
 * Represents a one-dimensional function that calculates the norm-1 distance
 * between a given point (x, f(x))
 * and a reference point (mX, mY).
 */
public class Norm1Function1D extends Function1D {

    private Function1D f;
    private float mX;
    private float mY;

    /**
     * Represents a 1D normalization function.
     */
    public Norm1Function1D(Function1D f, float mX, float mY) {
        this.f = f;
        this.mX = mX;
        this.mY = mY;
    }

    /**
     * Calculates the value of the function at a given point.
     * 
     * @param x the input value for the function
     * @return the calculated value of the function at the given point
     */
    @Override
    public float func(float x) {
        float y = f.func(x);
        return (float) (Math.abs(mX - x) + Math.abs(mY - y));
    }

    /**
     * Returns the expression in Mathematica syntax for the Norm1Function1D.
     * The expression is calculated as "Abs[mX - x] + Abs[mY - f[x]]".
     *
     * @return the expression in Mathematica syntax
     */
    @Override
    public String getExpressionMMA() {
        return "Abs[" + mX + " - x] + Abs[" + mY + " - f[x]]";
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
     * @return true if the second derivative is exact, false otherwise.
     */
    @Override
    public boolean secondDerivativeIsExact() {
        return false;
    }
}