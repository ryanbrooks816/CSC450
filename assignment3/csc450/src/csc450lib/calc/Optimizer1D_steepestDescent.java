package csc450lib.calc;

// import java.io.FileWriter;
// import java.io.IOException;

public class Optimizer1D_steepestDescent extends Optimizer1D {

    private float delta = 0.01f;
    private float startX;

    // x* is to be a local miniumum of function1d f is that the derivative of f at
    // x* is 0.
    // If the derivative of f at x* is > 0, then we move down the slope of f to the
    // next step
    // If the derivative of f at x* is < 0, then we move up the slope of f to the
    // next step
    // Algorithm will consist of starting at point x0. At step k, determine the
    // derivative of f at x_{k-1}.
    // If the derivative is positive, take delta x to the left. If it is negative
    // take delta x to the right.
    // Stop when it has converged, meaning that the derivative of f at x_{k-1} is 0
    // within a tolerance.

    public Optimizer1D_steepestDescent(Function1D f, float startX) {
        super(f);
        this.startX = startX;
    }

    public Optimizer1D_steepestDescent(Function1D f, float startX, float delta) {
        super(f);
        this.startX = startX;
        this.delta = delta;
    }

    @Override
    public OptimizerRecord1D minimize(float xMin, float xMax, float tol, int maxIters) {
        float x = this.startX;
        Function1D f = getFunction();
        float df = f.dfunc(x);
        int numIters = 0;

        while (Math.abs(df) > tol && numIters < maxIters) {
            if (df > 0) {
                x -= delta;
            } else {
                x += delta;
            }
            df = f.dfunc(x);
            numIters++;

            // Write x and y values to CSV file
            // try (FileWriter writer = new FileWriter("output.csv", true)) {
            // writer.append(String.valueOf(x));
            // writer.append(",");
            // writer.append(String.valueOf(f.func(x)));
            // writer.append("\n");
            // } catch (IOException e) {
            // e.printStackTrace();
            // }
        }

        OptimizerRecord1D record = new OptimizerRecord1D();
        record.optX = x;
        record.optVal = f.func(x);
        record.numIters = numIters;
        record.isSuccess = Math.abs(df) <= tol;
        return record;
    }

}
