package csc450lib.calc;

/**
 * Represents the domain of definition for a mathematical function.
 * The domain of definition specifies the valid input values for the domain of
 * the function.
 */
public class DomainOfDefinition {
    private float lowerBound;
    private float upperBound;

    /**
     * Constructs a new DomainOfDefinition object with default lower and upper
     * bounds.
     * The default lower bound is negative infinity and the default upper bound is
     * positive infinity.
     */
    public DomainOfDefinition() {
        this.lowerBound = Float.NEGATIVE_INFINITY;
        this.upperBound = Float.POSITIVE_INFINITY;
    }

    /**
     * Constructs a new DomainOfDefinition object with the specified lower and upper
     * bounds.
     * 
     * @param lowerBound the lower bound of the domain
     * @param upperBound the upper bound of the domain
     */
    public DomainOfDefinition(float lowerBound, float upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Returns the lower bound of the domain.
     * 
     * @return the lower bound
     */
    public float getLowerBound() {
        return lowerBound;
    }

    /**
     * Returns the upper bound of the domain.
     * 
     * @return the upper bound
     */
    public float getUpperBound() {
        return upperBound;
    }

    /**
     * Sets the lower bound of the domain.
     * 
     * @param lowerBound the new lower bound
     */
    public void setLowerBound(float lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * Sets the upper bound of the domain.
     * 
     * @param upperBound the new upper bound
     */
    public void setUpperBound(float upperBound) {
        this.upperBound = upperBound;
    }
}
