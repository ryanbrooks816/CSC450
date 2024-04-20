package csc450lib.calc;

public class Optimizer1D_parabolic extends Optimizer1D {

    public Optimizer1D_parabolic(Function1D f) {
        super(f);
    }

    @Override
    public OptimizerRecord1D minimize(float xMin, float xMax, float tol, int maxIters) {
        Function1D f = getFunction();
        float x1 = xMin;
        float x2 = (xMin + xMax) / 2;
        float x3 = xMax;
        float fx1 = f.func(x1);
        float fx2 = f.func(x2);
        float fx3 = f.func(x3);

        int numIters = 1;

        while (numIters < maxIters) {
            // Calculate the parabolic minimum
            float a = ((fx2 - fx1) / (x2 - x1) - (fx3 - fx1) / (x3 - x1)) / (x2 - x3); // (x1, f1), (x2, f2), (x3, f3)
                                                                                       // must not be colinear, throw
                                                                                       // undefined evaluation
            float b = (fx2 - fx1) / (x2 - x1) - a * (x2 + x1);
            float xOpt = -b / (2 * a);

            // Calculate the function value at the optimal x
            float fxOpt = f.func(xOpt);

            // Check if the difference between the old and new guess for the minimum is less
            // than the tolerance
            if (Math.abs(xOpt - x2) < tol) {
                break;
            }

            // If the optimal x is within the interval [x1, x3]
            if (xOpt > x1 && xOpt < x3) {
                if (xOpt < x2) {
                    x3 = x2;
                    fx3 = fx2;
                } else {
                    x1 = x2;
                    fx1 = fx2;
                }
            } else {
                // If the optimal x is not within the interval [x1, x3]
                xOpt = (x1 + x3) / 2;
                fxOpt = f.func(xOpt);
                if (xOpt < x2) {
                    x3 = x2;
                    fx3 = fx2;
                } else {
                    x1 = x2;
                    fx1 = fx2;
                }
            }

            x2 = xOpt;
            fx2 = fxOpt;
            numIters++;
        }

        OptimizerRecord1D record = new OptimizerRecord1D();
        record.optX = x2;
        record.optVal = fx2;
        record.numIters = numIters;
        record.isSuccess = true;
        return record;
    }
}