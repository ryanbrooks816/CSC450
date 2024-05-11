package csc450lib.calc;

public abstract class Optimizer1D {

    private Function1D f;

    public Optimizer1D(Function1D f) {
        this.f = f;
    }

    public void setFunction(Function1D f) {
        this.f = f;
    }

    public Function1D getFunction() {
        return f;
    }

    // Launches a search for a local minimum in the interval [xmin, xmax] starting
    // from the initial point startX with a tolerance of tol and a maximum number of
    // iterations of maxIters.
    public abstract OptimizerRecord1D minimize(float xMin, float xMax, float tol, int maxIters);
}
