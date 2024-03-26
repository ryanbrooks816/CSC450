package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

public abstract class FunctionND {

    private int n;

    // Declares the dimension of the function’s input set; considers functions
    // defined over the entire set R^n.

    public FunctionND(int n) {
        this.n = n;
    }

    // If the length of the input array is not equal to the number of dimensions of
    // the function, throw an exception.
    public abstract float func(float[] x) throws CSC450Exception;

}
