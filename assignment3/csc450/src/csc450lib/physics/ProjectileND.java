package csc450lib.physics;

import java.io.FileWriter;
import java.io.IOException;

import csc450lib.calc.Function1D;
import csc450lib.calc.FunctionND;
import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * The ProjectileND class represents a projectile in n-dimensional space.
 * It calculates the trajectory of the projectile and simulates its motion.
 */
public class ProjectileND {
    private float[] position;
    private float[] velocity;
    private static final float GRAVITY = -9.81f;
    private FunctionND ground;

    public ProjectileND(float[] position, float[] velocity, FunctionND ground) {
        this.position = position;
        this.velocity = velocity;
        this.ground = ground;
    }

    public ProjectileND(float[] position, float theta, float phi, float V, FunctionND ground) {
        this.position = position;
        // Convert to Cartesian coordinates
        this.velocity = new float[] {
                (float) (V * Math.cos(theta) * Math.cos(phi)),
                (float) (V * Math.sin(theta) * Math.cos(phi)),
                (float) (V * Math.sin(phi))
        };
        this.ground = ground;
    }

    public float[] func(float t) {
        float[] positionAtTime = new float[3];

        // X(t) = Vx*t + X0
        positionAtTime[0] = this.velocity[0] * t + this.position[0];
        // Y(t) = Vy*t + Y0
        positionAtTime[1] = this.velocity[1] * t + this.position[1];
        // Z(t) = 1/2*-g*t^2 + Vz*t + Z0
        positionAtTime[2] = 0.5f * GRAVITY * t * t + this.velocity[2] * t + this.position[2];

        return positionAtTime;
    }

    public float[] simulateTrjaectory(String filename) throws IOException, CSC450Exception {
        float defaultTimestep = 0.01f; // Default value
        float[] endingPos = simulateTrjaectory(filename, defaultTimestep);
        return endingPos;
    }

    public float[] simulateTrjaectory(String filename, float timestep) throws IOException, CSC450Exception {
        float currentPosition[] = this.position;

        // Check to make sure the starting point is above the ground
        if (currentPosition[2] < ground.func(new float[] { currentPosition[0], currentPosition[1] })) {
            throw new CSC450Exception(CSC450ErrorCode.STARTING_POINT_BELOW_GROUND);
        }

        FileWriter csvWriter = new FileWriter(filename);
        // Write the header and initial position at time = 0
        csvWriter.append("X,Y,Z\n");
        csvWriter.append(currentPosition[0] + "," + currentPosition[1] + "," + currentPosition[2] + "\n");

        float time = timestep;

        while (true) {
            currentPosition = this.func(time);
            // Check if projectile has hit the ground
            float Z = ground.func(new float[] { currentPosition[0], currentPosition[1] });
            csvWriter.append(
                    currentPosition[0] + "," + currentPosition[1] + "," + currentPosition[2] + "\n");
            if (currentPosition[2] <= Z) {
                break;
            }

            time += timestep;
        }

        csvWriter.flush();
        csvWriter.close();

        // Return the ending position
        return currentPosition;
    }

    public void simulateTrjaectory1D(String filename, Function1D f) throws IOException, CSC450Exception {
        float defaultTimestep = 0.01f; // Default value
        simulateTrjaectory1D(filename, f, defaultTimestep);
    }

    public void simulateTrjaectory1D(String filename, Function1D f, float timestep) throws IOException {
        float currentPosition = this.position[2]; // Z position

        // Check to make sure the starting point is above the ground
        if (currentPosition < ground.func(new float[] { this.position[0], this.position[1] })) {
            throw new CSC450Exception(CSC450ErrorCode.STARTING_POINT_BELOW_GROUND);
        }

        FileWriter csvWriter = new FileWriter(filename);
        // Write the header and initial position at time = 0
        csvWriter.append("time,Z\n");
        csvWriter.append("0.0," + currentPosition + "\n");

        float time = timestep;

        while (true) {
            currentPosition = f.func(time);
            csvWriter.append(time + "," + currentPosition + "\n");
            // Check if projectile has hit the ground
            if (currentPosition <= 0) {
                break;
            }
            time += timestep;
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
