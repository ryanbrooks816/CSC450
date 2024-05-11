package csc450lib.calc;

/**
 * Represents a one-dimensional function that calculates the Euclidean distance
 * between a point (x, f(x)) and a given point (mX, mY).
 */
public class Norm2Function1D extends Function1D {

    private Function1D f;
    private float mX;
    private float mY;

    /**
     * Constructs a new Norm2Function1D object.
     * 
     * @param f  the Function1D object representing the function
     * @param mX the x-coordinate of the center of the function
     * @param mY the y-coordinate of the center of the function
     */
    public Norm2Function1D(Function1D f, float mX, float mY) {
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
        return (float) Math.sqrt(Math.pow(mX - x, 2) + Math.pow(mY - y, 2));
    }

    /**
     * Returns the expression for the function in Mathematica format.
     *
     * @return the expression for the function in Mathematica format
     */
    @Override
    public String getExpressionMMA() {
        return "Sqrt[(" + mX + " - x)^2 + (" + mY + " - f[x])^2]";
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