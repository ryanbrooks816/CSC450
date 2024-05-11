package csc450lib.calc;

public class OptimizerRecord1D {
    public float optX; // the location of the minimum found (if any)
    public float optVal; // the value of the function at the minimum found (if any)
    public int numIters; // the number of iterations performed
    public boolean isSuccess; // indicates whether the search ended because it had found a local minimum
                              // (within the tolerance constraint iof the search).
}
