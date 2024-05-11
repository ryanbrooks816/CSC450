package csc450lib.calc;

/**
 * Represents a one-dimensional function obtained by composing two other
 * functions such as F(G(x)) where F and G are functions and x is the input to.
 */
public class FunctionComposition1D extends Function1D {
    private Function1D f;
    private Function1D g;

    /**
     * Constructs a FunctionComposition1D object with the given functions.
     * 
     * @param f The first function to be composed.
     * @param g The second function to be composed.
     */
    public FunctionComposition1D(Function1D f, Function1D g) {
        super();
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionComposition1D object with the given functions and domain
     * of definition.
     * 
     * @param f    The first function to be composed.
     * @param g    The second function to be composed.
     * @param xmin The minimum value of the domain of definition.
     * @param xmax The maximum value of the domain of definition.
     */
    public FunctionComposition1D(Function1D f, Function1D g, float xmin, float xmax) {
        super(xmin, xmax);
        this.f = f;
        this.g = g;
    }

    /**
     * Constructs a FunctionComposition1D object with the given functions and domain
     * of definition.
     * 
     * @param f      The first function to be composed.
     * @param g      The second function to be composed.
     * @param domain The domain of definition.
     */
    public FunctionComposition1D(Function1D f, Function1D g, DomainOfDefinition domain) {
        super(domain);
        this.f = f;
        this.g = g;
    }

    /**
     * Evaluates the composed function at the given value.
     * 
     * @param x The input value.
     * @return The output value of the composed function.
     */
    public float func(float x) {
        return f.func(g.func(x));
    }

    /**
     * Returns the mathematical expression of the composed function in Mathematica
     * format.
     *
     * @return The composed function expression in Mathematica format.
     */
    public String getExpressionMMA() {
        String gExpression = g.getExpressionMMA();
        String fExpression = f.getExpressionMMA();

        gExpression = gExpression.replace("f[x_] = ", "");
        fExpression = fExpression.replace("f[x_] = ", "");

        // Replace "x" in the expression for f with the expression for g
        fExpression = fExpression.replace("x", "(" + gExpression + ")");

        return "f[x_] = " + fExpression;
    }
}