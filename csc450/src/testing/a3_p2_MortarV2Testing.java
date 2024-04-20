package testing;

import csc450lib.calc.FunctionND;
import csc450lib.calc.FunctionNDfrom1D;
import csc450lib.exceptions.CSC450Exception;

public class a3_p2_MortarV2Testing {
    public static void main(String[] args) {
        // Define a 2D function
        FunctionND fn = new FunctionND(2) {
            @Override
            public float func(float[] a) throws CSC450Exception {
                return a[0] * a[0] + a[1] * a[1]; // This function returns the square of the Euclidean distance from the
                                                  // origin
            }
        };

        // Define the origin and direction of the U axis
        float[] x0 = { 0.0f, 0.0f };
        float[] uv = { 1.0f, 1.0f };

        // Create a 1D function from the 2D function
        FunctionNDfrom1D fn1D = new FunctionNDfrom1D(fn, x0, uv);

        // Use the 1D function
        float u = 1.0f;
        float result = 0;
        try {
            result = fn1D.func(u);
        } catch (CSC450Exception e) {
            e.printStackTrace();
        }
        System.out.println("Result: " + result);
    }
}
