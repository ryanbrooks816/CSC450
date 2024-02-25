package csc450lib.calc;

/**
 * Represents a one-dimensional function that is the multiplication of two other
 * one-dimensional functions.
 */
public class FunctionMultiplication1D extends Function1D {
    private Function1D f;
    private Function1D g;

    /**
     * Constructs a FunctionMultiplication1D object with the given functions.
     *
     * @param f The first function to be multiplied.
     * @param g The second function to be multiplied.
     */
    public FunctionMultiplication1D(Function1D f, Function1D g) {
        super();
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionMultiplication1D object with the given functions and
     * domain of definition.
     *
     * @param f    The first function to be multiplied.
     * @param g    The second function to be multiplied.
     * @param xmin The minimum value of the domain of definition.
     * @param xmax The maximum value of the domain of definition.
     */
    public FunctionMultiplication1D(Function1D f, Function1D g, float xmin, float xmax) {
        super(xmin, xmax);
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionMultiplication1D object with the given functions and
     * domain of definition.
     *
     * @param f      The first function to be multiplied.
     * @param g      The second function to be multiplied.
     * @param domain The domain of definition.
     */
    public FunctionMultiplication1D(Function1D f, Function1D g, DomainOfDefinition domain) {
        super(domain);
        this.f = f;
        this.g = g;
    }

    /**
     * Evaluates the function at the given value of x.
     *
     * @param x The value at which to evaluate the function.
     * @return The result of multiplying the values of the two functions at x.
     */
    public float func(float x) {
        return f.func(x) * g.func(x);
    }

    /**
     * Returns the mathematical expression of the function in Mathematica syntax.
     *
     * @return The mathematical expression of the function.
     */
    public String getExpressionMMA() {
        return f.getExpressionMMA() + " * " + g.getExpressionMMA().replace("f[x_] = ", "");
    }
}
