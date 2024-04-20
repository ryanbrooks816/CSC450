package csc450lib.calc;

public class Optimizer1D_gss extends Optimizer1D {

    private float golden_ratio = 1.61803398875f;

    public Optimizer1D_gss(Function1D f) {
        super(f);
    }

    @Override
    public OptimizerRecord1D minimize(float xMin, float xMax, float tol, int maxIters) {
        Function1D f = getFunction();
        int numIters = 1;
        boolean isSuccess = false;

        System.out.println("xMin: " + xMin + " | xMax: " + xMax + " | midX: " + ((xMax - xMin) / 2));

        while ((xMax - xMin) > tol && numIters < maxIters) {
            // Find the bracket
            float x1 = xMax - ((xMax - xMin) / golden_ratio);
            float x2 = xMin + ((xMax - xMin) / golden_ratio);

            float f1 = f.func(x1);
            float f2 = f.func(x2);

            // If f1 < f2, then the minimum is in the interval [xMin, x2]
            // If f1 > f2, then the minimum is in the interval [x1, xMax]
            if (f1 < f2) {
                xMax = x2;
            } else {
                xMin = x1;
            }

            System.out.println("xMin: " + xMin + " | xMax: " + xMax + " | midX: " + ((xMax - xMin) / 2));

            // End if the interval is within the tolerance
            if (xMax - xMin < tol) {
                isSuccess = true;
                break;
            } else {
                numIters++;
            }
        }

        OptimizerRecord1D record = new OptimizerRecord1D();
        record.optX = (xMax + xMin) / 2; // Minimum is the midpoint of the interval
        record.optVal = f.func(record.optX);
        record.numIters = numIters;
        record.isSuccess = isSuccess;
        return record;
    }
}
