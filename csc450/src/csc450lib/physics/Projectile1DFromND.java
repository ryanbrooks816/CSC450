package csc450lib.physics;

import java.io.FileWriter;
import java.io.IOException;

import csc450lib.calc.Function1D;
import csc450lib.calc.Function1DFromND;
import csc450lib.calc.FunctionND;
import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a one-dimensional projectile motion simulation derived from a
 * multi-dimensional function.
 * The class extends the Function1DFromND class and provides methods to simulate
 * the trajectory of the projectile.
 */
public class Projectile1DFromND extends Function1DFromND {

    public Projectile1DFromND(FunctionND fn, float[] x0, float[] uv) {
        super(fn, x0, uv);
    }

    @Override
    public float func(float u) {
        float[] x = new float[x0.length];
        for (int i = 0; i < x0.length; i++) {
            x[i] = x0[i] + u * uv[i];
        }
        return fn.func(x);
    }

    public void simulateTrjaectory(String filename, Function1D f) throws IOException, CSC450Exception {
        float du = 0.01f; // Default value
        simulateTrjaectory(filename, du, f);
    }

    public void simulateTrjaectory(String filename, float du, Function1D f) throws IOException {
        float currentPosition = this.func(0); // Z position

        // Check to make sure the starting point is above the ground
        if (currentPosition < this.fn.func(new float[] { this.x0[0], this.x0[1] })) {
            throw new CSC450Exception(CSC450ErrorCode.STARTING_POINT_BELOW_GROUND);
        }

        FileWriter csvWriter = new FileWriter(filename);
        // Write the header and initial position at time = 0
        csvWriter.append("X,Y\n");
        csvWriter.append("0.0," + currentPosition + "\n");
        float u = du;

        while (true) {
            currentPosition = f.func(u);
            csvWriter.append(u + "," + currentPosition + "\n");
            // Check if projectile has hit the ground
            if (currentPosition <= 0) {
                break;
            }
            u += du;
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
