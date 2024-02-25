package csc450lib.calc;

/**
 * Represents an abstract function of one variable and defines a valid domain
 * for all Function1D objects.
 */
public abstract class Function1D {
    private float xmin;
    private float xmax;

    // Function1D(): The default constructor for a function defined over the entire
    // float range.
    public Function1D() {
        xmin = Float.NEGATIVE_INFINITY;
        xmax = Float.POSITIVE_INFINITY;
    }

    // Function1D(float xmin, float xmax): The constructor for a function defined
    // over the float range ]xmin, xmax[.
    public Function1D(float xmin, float xmax) {
        this.xmin = xmin;
        this.xmax = xmax;
    }

    // Function1D(DomainOfDefinition d): The constructor for a function defined over
    // particular domain (more on that later). A DomainOfDefinition is a data type
    // that is a domain of the function defined as ]x_min, x_max[
    public Function1D(DomainOfDefinition d) {
        this.xmin = d.getLowerBound();
        this.xmax = d.getUpperBound();
    }

    // public abstract float func(float x): This method returns the value taken by
    // the function at x and must be implemented by a subclass.
    public abstract float func(float x);

    // public abstract String getExpressionMMA(): This method returns the function’s
    // equation in the form of a Mathematica-compatible expression. If the function
    // doesn’t have an equation (we will encounter quite a few of these this
    // semester), the
    // method should return a null reference.
    public abstract String getExpressionMMA();

    // boolean isDefinedAt(float x): returns true if x belongs to the function
    // domain of definition, and false otherwise.
    public boolean isDefinedAt(float x) {
        return x > xmin && x < xmax;
    }

    // float getLowerBound(s): this method returns the lower bound of the function’s
    // domain of definition.
    public float getLowerBound() {
        return xmin;
    }

    // float getUpperBound(): this method returns the upper bound of the function’s
    // domain of definition.
    public float getUpperBound() {
        return xmax;
    }
}