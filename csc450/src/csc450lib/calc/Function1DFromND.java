package csc450lib.calc;

import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

public class Function1DFromND {

    protected FunctionND fn;
    protected float[] x0;
    protected float[] uv;

    // It declares the ND function (2D in our case) used to generate the 1D
    // function, the point in ND space to use as origin of the new U axis, and a
    // direction vector for the U axis. This constructor should throw an exception
    // if the different dimensions don’t agree.
    public Function1DFromND(FunctionND fn, float[] x0, float[] uv) throws CSC450Exception {
        if (fn.getN() != 2 || x0.length != 2 || uv.length != 2) {
            throw new CSC450Exception(CSC450ErrorCode.ARRAY_LENGTH_DOES_NOT_EQUAL_FUNCTION_DIMENSION);
        }
        this.fn = fn;
        this.x0 = x0;
        this.uv = uv;
    }

    // It uses the value of u to compute the corresponding n-dimensional vector x to
    // send to fn.
    public float func(float u) {
        float[] x = new float[2];
        x[0] = x0[0] + u * uv[0]; // X = X0 + u cos θ
        x[1] = x0[1] + u * uv[1]; // Y = Y0 + u sin θ
        return fn.func(x);
    }
}
